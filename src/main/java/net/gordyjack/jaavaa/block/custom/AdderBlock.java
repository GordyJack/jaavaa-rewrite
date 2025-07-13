package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.tick.*;

public class AdderBlock extends AbstractRedstoneGateBlock {
    //Codec
    public static final MapCodec<AdderBlock> CODEC = createCodec(AdderBlock::new);
    //Properties
    public static final BooleanProperty LEFT_POWERED = JAAVAABlockProperties.LEFT_POWERED;
    public static final BooleanProperty BACK_POWERED = JAAVAABlockProperties.BACK_POWERED;
    public static final BooleanProperty RIGHT_POWERED = JAAVAABlockProperties.RIGHT_POWERED;
    public static final IntProperty POWER = Properties.POWER;
    //Constants
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, BACK_POWERED, LEFT_POWERED, RIGHT_POWERED, POWER);
    }
    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
//    @Override
//    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        BlockState state = super.getPlacementState(ctx);
//        return this.getUpdatedState(ctx.getWorld(), ctx.getBlockPos(), state);
//    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        return this.calculateOutputPower(world, pos, state);
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
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
//    @Override
//    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
//        super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
//        if (!world.isClient && state.canPlaceAt(world, pos)) {
//            this.updateState(world, pos, state);
//        }
//    }
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
            world.addParticleClient(DustParticleEffect.DEFAULT, d + h, e, f + i, 0.0, 0.0, 0.0);
        }
    }
    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient()) {
            if (hasStateChanged(state, world, pos)) {
                updateState(world, pos, state);
            }
        }
    }
    @Override
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld serverWorld && hasStateChanged(state, serverWorld, pos) && !world.getBlockTickScheduler().isTicking(pos, this)){
            TickPriority tickPriority = this.isTargetNotAligned(serverWorld, pos, state) ? TickPriority.HIGH : TickPriority.NORMAL;
            serverWorld.scheduleBlockTick(pos, this, this.getUpdateDelayInternal(state), tickPriority);
        }
    }
    //Methods
    private int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);
        return Math.min(inputPowers[0] + inputPowers[1] + inputPowers[2], 15);
    }
    private int[] getInputPowers(World world, BlockPos pos, BlockState state) {
        Direction inputDirection = state.get(FACING);
        Direction leftInputDirection = inputDirection.rotateYClockwise();
        Direction rightInputDirection = inputDirection.rotateYCounterclockwise();

        int backInputPower = world.getEmittedRedstonePower(pos.offset(inputDirection), inputDirection);
        int leftInputPower = world.getEmittedRedstonePower(pos.offset(leftInputDirection), leftInputDirection);
        int rightInputPower = world.getEmittedRedstonePower(pos.offset(rightInputDirection), rightInputDirection);

        return new int[]{backInputPower, leftInputPower, rightInputPower};
    }
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
    private boolean hasStateChanged(BlockState state, ServerWorld world, BlockPos pos) {
        return state.get(POWERED) != this.hasPower(world, pos, state)
                || state.get(POWER) != this.getPower(world, pos, state);
    }
    private void updateState(World world, BlockPos pos, BlockState state) {
        state = this.getUpdatedState(world, pos, state);
        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
        this.updateTarget(world, pos, state);
    }
}
