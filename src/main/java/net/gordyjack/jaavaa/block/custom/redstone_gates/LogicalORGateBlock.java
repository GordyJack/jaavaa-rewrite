package net.gordyjack.jaavaa.block.custom.redstone_gates;

import com.mojang.serialization.*;
import net.minecraft.block.*;
import net.minecraft.state.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class LogicalORGateBlock extends AbstractAdvancedRedstoneGateBlock{
    //Constants
    private static final MapCodec<LogicalORGateBlock> CODEC = createCodec(LogicalORGateBlock::new);
    private static final int UPDATE_DELAY = 2;

    //Constructor
    public LogicalORGateBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, false)
                        .with(POWER, 0)
        );
    }

    //Overrides
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, POWER);
    }
    @Override
    protected int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        return this.getPower(world, pos, state);
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        Direction side1 = facing.rotateYCounterclockwise();
        Direction side2 = facing.rotateYClockwise();
        int side1Power = world.getEmittedRedstonePower(pos.offset(side1), side1);
        int side2Power = world.getEmittedRedstonePower(pos.offset(side2), side2);
        boolean hasPower = side1Power > 0 || side2Power > 0;
        return hasPower ? Math.max(side1Power, side2Power) : 0;
    }
    @Override
    protected BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        return state
                .with(POWERED, this.hasPower(world, pos, state))
                .with(POWER, this.calculateOutputPower(world, pos, state));
    }
    @Override
    protected boolean hasStateChanged(World world, BlockPos pos, BlockState state) {
        return state.get(POWERED) != this.hasPower(world, pos, state)
                || state.get(POWER) != this.calculateOutputPower(world, pos, state);
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
    protected void updateTarget(World world, BlockPos pos, BlockState state) {
        super.updateTarget(world, pos, state);
    }
}
