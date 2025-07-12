package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.enums.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.block.*;
import net.minecraft.world.tick.*;

public class DecoderBlock extends AbstractRedstoneGateBlock{
    //Codec
    public static final MapCodec<DecoderBlock> CODEC = createCodec(DecoderBlock::new);
    //Properties
    public static final EnumProperty<DecoderMode> MODE = JAAVAABlockProperties.DECODER_MODE;
    public static final EnumProperty<DecoderTarget> TARGET = JAAVAABlockProperties.DECODER_TARGET;
    public static final IntProperty POWER = Properties.POWER;
    //Constants
    private static final int UPDATE_DELAY = 2;

    //Constructor
    public DecoderBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, Boolean.FALSE)
                        .with(MODE, DecoderMode.DEMUX)
                        .with(TARGET, DecoderTarget.NONE)
                        .with(POWER, 0)
        );
    }

    //Overrides
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, MODE, TARGET, POWER);
    }
    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
    @Override
    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return state.get(POWER);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
                                                   BlockPos pos, Direction direction, BlockPos neighborPos,
                                                   BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return state;
        }
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return UPDATE_DELAY;
    }
    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (!state.get(POWERED)) {
            return 0;
        }
        Direction inputDirection = state.get(FACING);
        Direction left = inputDirection.rotateYCounterclockwise();
        Direction right = inputDirection.rotateYClockwise();

        int outputPower = this.getOutputLevel(world, pos, state);

        switch (state.get(TARGET)) {
            case LEFT -> {
                if (direction == left) return outputPower;
            }
            case FRONT -> {
                if (direction == inputDirection) return outputPower;
            }
            case RIGHT -> {
                if (direction == right) return outputPower;
            }
        }
        return 0;
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        }
        state = state.cycle(MODE);
        this.updateState(world, pos, state);
        float f = state.get(MODE) == DecoderMode.DEMUX ? 0.5F : 0.55F;
        world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3F, f);
        return ActionResult.SUCCESS;
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            Direction outputDirection = state.get(FACING);
            double pX = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double pY = pos.getY() + 0.4 + (random.nextDouble() - 0.5) * 0.2;
            double pZ = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;

            pY = state.get(MODE) == DecoderMode.DEMUX ? pY - 0.125 : pY;

            outputDirection = switch (state.get(TARGET)) {
                case LEFT -> outputDirection.rotateYCounterclockwise();
                case FRONT, NONE -> outputDirection;
                case RIGHT -> outputDirection.rotateYClockwise();
            };
            float pOffset = -5.0F / 16.0F;
            double oX = pOffset * outputDirection.getOffsetX();
            double oZ = pOffset * outputDirection.getOffsetZ();
            world.addParticleClient(DustParticleEffect.DEFAULT, pX + oX, pY, pZ + oZ, 0.0, 0.0, 0.0);
        }
    }
    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state != this.getUpdatedState(world, pos, state)) {
            this.updateState(world, pos, state);
        }
    }
    @Override
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (!world.getBlockTickScheduler().isTicking(pos, this)) {
            TickPriority tickPriority = TickPriority.HIGH;
            if (this.isTargetNotAligned(world, pos, state)) {
                tickPriority = TickPriority.EXTREMELY_HIGH;
            } else if (state.get(POWERED)) {
                tickPriority = TickPriority.VERY_HIGH;
            }
            world.scheduleBlockTick(pos, this, this.getUpdateDelayInternal(state), tickPriority);
        }
    }
    @Override
    protected void updateTarget(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            JAAVAA.log("Skipping updateTarget on client side", 'e');
            return;
        }

        Direction facingDirection = state.get(FACING);
        Direction targetDirection = switch (state.get(TARGET)) {
            case NONE -> Direction.UP;
            case LEFT -> facingDirection.rotateYClockwise();
            case FRONT -> facingDirection.getOpposite();
            case RIGHT -> facingDirection.rotateYCounterclockwise();
        };
        JAAVAA.log("facingDirection: " + facingDirection + " | targetDirection: " + targetDirection
                + " | get(TARGET): " + state.get(TARGET) + " | getTarget(): " + this.getTarget(world, pos, state), 'e');
        BlockPos blockPos = pos.offset(targetDirection);
        WireOrientation wireOrientation = OrientationHelper.getEmissionOrientation(world, targetDirection, Direction.UP);
        world.updateNeighbor(blockPos, this, wireOrientation);
        JAAVAA.log("Updated neighbor: " + world.getBlockState(blockPos).getBlock().getName().getString() + " at " + blockPos.toShortString());
        world.updateNeighborsExcept(blockPos, this, targetDirection.getOpposite(), wireOrientation);
        for (Direction direction : Direction.values()) {
            if (direction == targetDirection.getOpposite()) continue;
            BlockPos neighborPos = blockPos.offset(direction);
            JAAVAA.log("Updated neighbor of: " + world.getBlockState(blockPos).getBlock().getName().getString() + " at " + blockPos.toShortString() + ": "
                    + world.getBlockState(neighborPos).getBlock().getName().getString() + " at " + neighborPos.toShortString(), 'e');
        }
        //Comment out the following to observe the correct behaviour of a "decoder clock"
        if (this.isOff(state)) {
            JAAVAA.log("Target is NONE, updating neighbors always");
            world.updateNeighborsAlways(pos, this, null);
            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = pos.offset(direction);
                JAAVAA.log("Updated neighbor: " + world.getBlockState(neighborPos).getBlock().getName().getString() + " at " + neighborPos.toShortString(), 'e');
            }
        }
    }

    //Methods
    private int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        int inputPower = this.getPower(world, pos, state);
        if (inputPower <= 0) return 0;
        return switch (state.get(MODE)) {
            case DEMUX -> inputPower;
            case DECODE -> 15;
        };
    }
    private DecoderTarget getTarget(World world, BlockPos pos, BlockState state) {
        return switch (this.getPower(world, pos, state)) {
            case 1, 2, 3, 4, 5 -> DecoderTarget.LEFT;
            case 6, 7, 8, 9, 10 -> DecoderTarget.FRONT;
            case 11, 12, 13, 14, 15 -> DecoderTarget.RIGHT;
            default -> DecoderTarget.NONE; // Should not happen
        };
    }
    private BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        boolean powered = this.getPower(world, pos, state) > 0;
        int outputPower = this.calculateOutputPower(world, pos, state);
        DecoderTarget target = this.getTarget(world, pos, state);
        return state
                .with(POWERED, powered)
                .with(POWER, outputPower)
                .with(TARGET, target);
    }
    private boolean isOff(BlockState state) {
        return !state.get(POWERED) && state.get(POWER) == 0 && state.get(TARGET) == DecoderTarget.NONE;
    }
    private void updateState(World world, BlockPos pos, BlockState state) {
        BlockState updatedState = this.getUpdatedState(world, pos, state);
        world.setBlockState(pos, updatedState, Block.NOTIFY_LISTENERS);
        this.updateTarget(world, pos, updatedState);
    }
}
