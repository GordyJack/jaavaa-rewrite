package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.MapCodec;
import net.gordyjack.jaavaa.block.JAAVAABlockProperties;
import net.gordyjack.jaavaa.block.enums.DecoderMode;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class DecoderBlock extends AbstractRedstoneGateBlock{
    public static final MapCodec<DecoderBlock> CODEC = createCodec(DecoderBlock::new);
    public static final Property<DecoderMode> MODE = JAAVAABlockProperties.DECODER_MODE;
    public static final BooleanProperty FRONT_POWERED = JAAVAABlockProperties.FRONT_POWERED;
    public static final BooleanProperty LEFT_POWERED = JAAVAABlockProperties.LEFT_POWERED;
    public static final BooleanProperty RIGHT_POWERED = JAAVAABlockProperties.RIGHT_POWERED;

    public DecoderBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, Boolean.FALSE)
                        .with(MODE, DecoderMode.DECODE)
                        .with(FRONT_POWERED, Boolean.FALSE)
                        .with(LEFT_POWERED, Boolean.FALSE)
                        .with(RIGHT_POWERED, Boolean.FALSE)
        );
    }

    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.FAIL;
        }
        DecoderMode mode = state.get(MODE);
        switch(mode) {
            case DECODE -> {
                world.setBlockState(pos, state.with(MODE, DecoderMode.DEMUX));
                return ActionResult.SUCCESS;
            }
            case DEMUX -> {
                world.setBlockState(pos, state.with(MODE, DecoderMode.DECODE));
                return ActionResult.SUCCESS;
            }
            default -> {
                return super.onUse(state, world, pos, player, hit);
            }
        }
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        return super.getPower(world, pos, state);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            Direction inputDirection = state.get(FACING);
            Direction left = inputDirection.rotateYCounterclockwise();
            Direction right = inputDirection.rotateYClockwise();

            int leftPower = state.getWeakRedstonePower(world, pos, left);
            int straightPower = state.getWeakRedstonePower(world, pos, inputDirection);
            int rightPower = state.getWeakRedstonePower(world, pos, right);

            state = state
                    .with(FRONT_POWERED, Boolean.FALSE)
                    .with(LEFT_POWERED, Boolean.FALSE)
                    .with(RIGHT_POWERED, Boolean.FALSE);
            if (leftPower > 0) {
                state = state.with(LEFT_POWERED, Boolean.TRUE);
            }
            if (straightPower > 0) {
                state = state.with(FRONT_POWERED, Boolean.TRUE);
            }
            if (rightPower > 0) {
                state = state.with(RIGHT_POWERED, Boolean.TRUE);
            }
            return state;
        }
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return switch (state.get(MODE)) {
            case DECODE -> 2;
            case DEMUX -> 0;
        };
    }

    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return 0;
        }
        Direction inputDirection = state.get(FACING);
        BlockState inputState = world.getBlockState(pos.offset(inputDirection));
        int inputPower = inputState.getWeakRedstonePower(world, pos.offset(inputDirection), inputDirection);
        if (inputPower == 0) {
            return 0;
        }
        Direction left = inputDirection.rotateYCounterclockwise();
        Direction right = inputDirection.rotateYClockwise();
        boolean leftPowered = inputPower > 0 && inputPower <=5;
        boolean straightPowered = inputPower > 5 && inputPower <= 10;
        boolean rightPowered = inputPower > 10 && inputPower <= 15;
        int outputPower = switch (state.get(MODE)) {
            case DECODE -> 15;
            case DEMUX -> inputPower;
        };
        if (direction == inputDirection && straightPowered) {
            return outputPower;
        }
        if (direction == left && leftPowered) {
            return outputPower;
        }
        if (direction == right && rightPowered) {
            return outputPower;
        }
        return 0;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            Direction direction = state.get(FACING);
            double d = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double e = pos.getY() + 0.4 + (random.nextDouble() - 0.5) * 0.2;
            double f = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            float g = -5.0F;

            g /= 16.0F;
            double h = g * direction.getOffsetX();
            double i = g * direction.getOffsetZ();
            world.addParticle(DustParticleEffect.DEFAULT, d + h, e, f + i, 0.0, 0.0, 0.0);
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, MODE, FRONT_POWERED, LEFT_POWERED, RIGHT_POWERED);
    }
}
