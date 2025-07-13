package net.gordyjack.jaavaa.block.custom.redstone_gates;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.enums.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.particle.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.block.*;

public class DecoderBlock extends AbstractAdvancedRedstoneGateBlock{
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
    protected int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        int inputPower = this.getPower(world, pos, state);
        if (inputPower <= 0) return 0;
        return switch (state.get(MODE)) {
            case DEMUX -> inputPower;
            case DECODE -> 15;
        };
    }
    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return UPDATE_DELAY;
    }
    @Override
    BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        return state
                .with(POWERED, this.hasPower(world, pos, state))
                .with(POWER, this.calculateOutputPower(world, pos, state))
                .with(TARGET, this.getTarget(world, pos, state));
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
    boolean hasStateChanged(World world, BlockPos pos, BlockState state) {
        return state.get(POWERED) != this.hasPower(world, pos, state)
                || state.get(POWER) != this.calculateOutputPower(world, pos, state)
                || state.get(TARGET) != this.getTarget(world, pos, state);
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
    protected void updateTarget(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) { // Skip update on client side
            return;
        }
        Direction facingDirection = state.get(FACING);
        Direction leftDirection = facingDirection.rotateYClockwise();
        Direction frontDirection = facingDirection.getOpposite();
        Direction rightDirection = facingDirection.rotateYCounterclockwise();
        // Update the wire orientations for the left, front, and right directions
        WireOrientation leftWireOrientation = OrientationHelper.getEmissionOrientation(world, leftDirection, Direction.UP);
        WireOrientation frontWireOrientation = OrientationHelper.getEmissionOrientation(world, frontDirection, Direction.UP);
        WireOrientation rightWireOrientation = OrientationHelper.getEmissionOrientation(world, rightDirection, Direction.UP);
        // Update the neighbors in the left, front, and right directions
        world.updateNeighbor(pos.offset(leftDirection), this, leftWireOrientation);
        world.updateNeighbor(pos.offset(frontDirection), this, frontWireOrientation);
        world.updateNeighbor(pos.offset(rightDirection), this, rightWireOrientation);
        // Determine the target direction based on the current state
        Direction targetDirection = switch (state.get(TARGET)) {
            case NONE -> Direction.UP;
            case LEFT -> leftDirection;
            case FRONT -> frontDirection;
            case RIGHT -> rightDirection;
        };
        // Update the neighbors of the target
        BlockPos blockPos = pos.offset(targetDirection);
        WireOrientation wireOrientation = OrientationHelper.getEmissionOrientation(world, targetDirection, Direction.UP);
        world.updateNeighborsExcept(blockPos, this, targetDirection.getOpposite(), wireOrientation);
    }

    //Methods
    private DecoderTarget getTarget(World world, BlockPos pos, BlockState state) {
        return switch (this.getPower(world, pos, state)) {
            case 1, 2, 3, 4, 5 -> DecoderTarget.LEFT;
            case 6, 7, 8, 9, 10 -> DecoderTarget.FRONT;
            case 11, 12, 13, 14, 15 -> DecoderTarget.RIGHT;
            default -> DecoderTarget.NONE; // Should not happen
        };
    }
}
