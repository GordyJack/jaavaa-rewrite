package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.MapCodec;
import net.gordyjack.jaavaa.JAAVAA;
import net.gordyjack.jaavaa.block.JAAVAABlockProperties;
import net.gordyjack.jaavaa.block.enums.DecoderMode;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

//TODO: Adder is not updating appropriately in game. Or at all really. Need to put together a solution based on the comparator's function.
public class AdderBlock extends AbstractRedstoneGateBlock {
    public static final MapCodec<AdderBlock> CODEC = createCodec(AdderBlock::new);
    public static final BooleanProperty BACK_POWERED = JAAVAABlockProperties.BACK_POWERED;
    public static final BooleanProperty LEFT_POWERED = JAAVAABlockProperties.LEFT_POWERED;
    public static final BooleanProperty RIGHT_POWERED = JAAVAABlockProperties.RIGHT_POWERED;

    public AdderBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, Boolean.FALSE)
                        .with(BACK_POWERED, Boolean.FALSE)
                        .with(LEFT_POWERED, Boolean.FALSE)
                        .with(RIGHT_POWERED, Boolean.FALSE)
        );
    }
    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        Direction inputDirection = state.get(FACING);
        return this.getWeakRedstonePower(state, world, pos, inputDirection);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            Direction inputDirection = state.get(FACING);
            Direction leftInputDirection = inputDirection.rotateYClockwise();
            Direction rightInputDirection = inputDirection.rotateYCounterclockwise();

            int inputPower = world.getBlockState(pos.offset(inputDirection)).getWeakRedstonePower(world, pos.offset(inputDirection), inputDirection);
            int leftInputPower = world.getBlockState(pos.offset(leftInputDirection)).getWeakRedstonePower(world, pos.offset(leftInputDirection), leftInputDirection);
            int rightInputPower = world.getBlockState(pos.offset(rightInputDirection)).getWeakRedstonePower(world, pos.offset(rightInputDirection), rightInputDirection);

            state = state
                    .with(BACK_POWERED, inputPower > 0)
                    .with(LEFT_POWERED, leftInputPower > 0)
                    .with(RIGHT_POWERED, rightInputPower > 0);

            return state;
        }
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }
    @Override
    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        Direction inputDirection = state.get(FACING);
        Direction leftInputDirection = inputDirection.rotateYCounterclockwise();
        Direction rightInputDirection = inputDirection.rotateYClockwise();

        int inputPower = world.getBlockState(pos.offset(inputDirection)).getWeakRedstonePower(world, pos.offset(inputDirection), inputDirection);
        int leftInputPower = world.getBlockState(pos.offset(leftInputDirection)).getWeakRedstonePower(world, pos.offset(leftInputDirection), leftInputDirection);
        int rightInputPower = world.getBlockState(pos.offset(rightInputDirection)).getWeakRedstonePower(world, pos.offset(rightInputDirection), rightInputDirection);

        return Math.min(inputPower + leftInputPower + rightInputPower, 15);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, BACK_POWERED, LEFT_POWERED, RIGHT_POWERED);
    }
}
