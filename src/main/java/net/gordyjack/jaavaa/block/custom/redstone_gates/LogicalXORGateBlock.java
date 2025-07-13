package net.gordyjack.jaavaa.block.custom.redstone_gates;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class LogicalXORGateBlock extends AbstractAdvancedRedstoneGateBlock{
    //Constants
    private static final MapCodec<LogicalXORGateBlock> CODEC = createCodec(LogicalXORGateBlock::new);
    private static final int UPDATE_DELAY = 2;
    //Properties
    public static final BooleanProperty LEFT_POWERED = JAAVAABlockProperties.LEFT_POWERED;
    public static final BooleanProperty RIGHT_POWERED = JAAVAABlockProperties.RIGHT_POWERED;

    //Constructor
    public LogicalXORGateBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, false)
                        .with(POWER, 0)
                        .with(LEFT_POWERED, false)
                        .with(RIGHT_POWERED, false)
        );
    }

    //Overrides
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, POWER, LEFT_POWERED, RIGHT_POWERED);
    }
    @Override
    protected int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        return this.getPower(world, pos, state);
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);
        boolean hasPower = (inputPowers[0] > 0 || inputPowers[1] > 0) && !(inputPowers[0] > 0 && inputPowers[1] > 0);
        return hasPower ? Math.max(inputPowers[0], inputPowers[1]) : 0;
    }
    @Override
    protected BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);
        return state
                .with(POWERED, this.hasPower(world, pos, state))
                .with(POWER, this.calculateOutputPower(world, pos, state))
                .with(LEFT_POWERED, inputPowers[0] > 0)
                .with(RIGHT_POWERED, inputPowers[1] > 0);

    }
    @Override
    protected boolean hasStateChanged(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);
        return state.get(POWERED) != this.hasPower(world, pos, state)
                || state.get(POWER) != this.calculateOutputPower(world, pos, state)
                || state.get(LEFT_POWERED) != inputPowers[0] > 0
                || state.get(RIGHT_POWERED) != inputPowers[1] > 0;
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
    //Methods
    private int[] getInputPowers(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        Direction left = facing.rotateYClockwise();
        Direction right = facing.rotateYCounterclockwise();
        int leftPower = world.getEmittedRedstonePower(pos.offset(left), left);
        int rightPower = world.getEmittedRedstonePower(pos.offset(right), right);
        return new int[]{leftPower, rightPower};
    }
}
