package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.MapCodec;
import net.gordyjack.jaavaa.block.JAAVAABlockProperties;
import net.gordyjack.jaavaa.block.enums.DecoderMode;
import net.gordyjack.jaavaa.block.enums.DecoderTarget;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.OrientationHelper;
import net.minecraft.world.block.WireOrientation;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class DecoderBlock extends AbstractRedstoneGateBlock{
    //Codec
    public static final MapCodec<DecoderBlock> CODEC = createCodec(DecoderBlock::new);
    //Properties
    public static final EnumProperty<DecoderMode> MODE = JAAVAABlockProperties.DECODER_MODE;
    public static final EnumProperty<DecoderTarget> TARGET = JAAVAABlockProperties.DECODER_TARGET;
    public static final IntProperty POWER = Properties.POWER;

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
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        return this.getUpdatedState(world, pos, state);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
                                                   BlockPos pos, Direction direction, BlockPos neighborPos,
                                                   BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return !world.isClient() ? this.updateState((World) world, pos, state)
                    : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }
    @Override
    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return state.get(POWER);
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        return state.get(POWER);
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return switch (state.get(MODE)) {
            case DEMUX -> 0;
            case DECODE -> 2;
        };
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
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock,
                                  @Nullable WireOrientation wireOrientation, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
        if (!world.isClient && state.canPlaceAt(world, pos)) {
            this.updateState(world, pos, state);
        }
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            state = state.cycle(MODE);
            state = this.updateState(world, pos, state);
            float f = state.get(MODE) == DecoderMode.DEMUX ? 0.5F : 0.55F;
            world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            return ActionResult.SUCCESS;
        }
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
            world.addParticle(DustParticleEffect.DEFAULT, pX + oX, pY, pZ + oZ, 0.0, 0.0, 0.0);
        }
    }
    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            BlockState updatedState = this.updateState(world, pos, state);
            if (updatedState != state) {
                world.setBlockState(pos, updatedState, Block.NOTIFY_LISTENERS);
            }
        }
    }
    @Override
    protected void updateTarget(World world, BlockPos pos, BlockState state) {
        Direction facingDirection = state.get(FACING);
        Direction targetDirection = switch (state.get(TARGET)) {
            case NONE -> Direction.NORTH;
            case LEFT -> facingDirection.rotateYCounterclockwise();
            case FRONT -> facingDirection;
            case RIGHT -> facingDirection.rotateYClockwise();
        };
        BlockPos blockPos = pos.offset(targetDirection.getOpposite());
        WireOrientation wireOrientation = OrientationHelper.getEmissionOrientation(world, targetDirection.getOpposite(), Direction.UP);
        world.updateNeighbor(blockPos, this, wireOrientation);
        world.updateNeighborsExcept(blockPos, this, targetDirection, wireOrientation);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, MODE, TARGET, POWER);
    }

    //Methods
    /**
     * Calculates the power level of the block
     * @param state the state of the block
     * @param inputPower the power level of the input
     * @return the power level of the block
     */
    private int calculateOutputPower(BlockState state, int inputPower) {
        return switch (state.get(MODE)) {
            case DEMUX -> inputPower;
            case DECODE -> 15;
        };
    }
    /**
     * Gets the power level of the input to the block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     * @return the power level of the input
     */
    private int getInputPower(World world, BlockPos pos, BlockState state) {
        Direction inputDirection = state.get(FACING);
        BlockPos offset = pos.offset(inputDirection);
        return world.getEmittedRedstonePower(offset, inputDirection);
    }
    /**
     * Updates the state of the block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     * @return the updated state
     */
    private BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        int inputPower = this.getInputPower(world, pos, state);
        DecoderTarget target;
        if (inputPower > 0 && inputPower <=5) {
            target = DecoderTarget.LEFT;
        } else if (inputPower > 5 && inputPower <= 10) {
            target = DecoderTarget.FRONT;
        } else if (inputPower > 10 && inputPower <= 15) {
            target = DecoderTarget.RIGHT;
        } else {
            target = DecoderTarget.NONE;
        }
        boolean powered = inputPower > 0;
        int outputPower = this.calculateOutputPower(state, inputPower);

        return state
                .with(TARGET, target)
                .with(POWERED, powered)
                .with(POWER, outputPower);
    }
    /**
     * Updates the target block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     */
    private BlockState updateState(World world, BlockPos pos, BlockState state) {
        state = this.getUpdatedState(world, pos, state);
        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
        this.updateTarget(world, pos, state);
        return state;
    }
}
