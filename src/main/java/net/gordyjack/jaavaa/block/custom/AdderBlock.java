package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.MapCodec;
import net.gordyjack.jaavaa.block.JAAVAABlockProperties;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
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
    public static final BooleanProperty LEFT_POWERED = JAAVAABlockProperties.LEFT_POWERED;
    public static final BooleanProperty BACK_POWERED = JAAVAABlockProperties.BACK_POWERED;
    public static final BooleanProperty RIGHT_POWERED = JAAVAABlockProperties.RIGHT_POWERED;
    public static final IntProperty POWER = Properties.POWER;
    private static final int UPDATE_DELAY = 2;
    //Constructor
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
        return this.getUpdatedState(ctx.getWorld(), ctx.getBlockPos(), state);
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        return state.get(POWER);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            this.update((World) world, pos, state);
            return this.getUpdatedState((World) world, pos, state);
        }
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return UPDATE_DELAY;
    }
    @Override
    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return state.get(POWER);
    }
    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.update(world, pos, state);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, BACK_POWERED, LEFT_POWERED, RIGHT_POWERED, POWER);
    }
    //Methods
    /**
     * Gets the power level of each input to the block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     * @return an array of the power levels of the inputs in the order of back, left, right
     */
    private int[] getInputPowers(World world, BlockPos pos, BlockState state) {
        Direction inputDirection = state.get(FACING);
        Direction leftInputDirection = inputDirection.rotateYClockwise();
        Direction rightInputDirection = inputDirection.rotateYCounterclockwise();

        int backInputPower = world.getBlockState(pos.offset(inputDirection)).getWeakRedstonePower(world, pos.offset(inputDirection), inputDirection);
        int leftInputPower = world.getBlockState(pos.offset(leftInputDirection)).getWeakRedstonePower(world, pos.offset(leftInputDirection), leftInputDirection);
        int rightInputPower = world.getBlockState(pos.offset(rightInputDirection)).getWeakRedstonePower(world, pos.offset(rightInputDirection), rightInputDirection);

        return new int[]{backInputPower, leftInputPower, rightInputPower};
    }
    /**
     * Calculates the power level of the block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     * @return the power level of the block
     */
    private int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);
        return Math.min(inputPowers[0] + inputPowers[1] + inputPowers[2], 15);
    }
    /**
     * Updates the state of the block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     * @return the updated state
     */
    private BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);

        boolean backPowered = inputPowers[0] > 0;
        boolean leftPowered = inputPowers[1] > 0;
        boolean rightPowered = inputPowers[2] > 0;
        boolean powered = backPowered || leftPowered || rightPowered;

        int outputPower = this.calculateOutputPower(world, pos, state);

        return state
                .with(BACK_POWERED, backPowered)
                .with(LEFT_POWERED, leftPowered)
                .with(RIGHT_POWERED, rightPowered)
                .with(POWERED, powered)
                .with(POWER, outputPower);
    }
    /**
     * Updates the target block
     * @param world the world
     * @param pos the position of the block
     * @param state the state of the block
     */
    private void update(World world, BlockPos pos, BlockState state) {
        state = this.getUpdatedState(world, pos, state);
        world.setBlockState(pos, state);
        this.updateTarget(world, pos, state);
    }
}
