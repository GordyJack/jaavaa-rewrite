package net.gordyjack.jaavaa.block.custom.redstone_gates;

import net.minecraft.block.*;
import net.minecraft.server.world.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.tick.*;

public abstract class AbstractAdvancedRedstoneGateBlock
        extends AbstractRedstoneGateBlock {
    //Constants
    public static final IntProperty POWER = Properties.POWER;
    //Constructor
    public AbstractAdvancedRedstoneGateBlock(Settings settings) {
        super(settings);
    }
    //Override Default Methods
    @Override
    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return state.get(POWER);
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
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient()) {
            if (hasStateChanged(world, pos, state)) {
                updateState(world, pos, state);
            }
        }
    }
    @Override
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld serverWorld && hasStateChanged(serverWorld, pos, state) && !world.getBlockTickScheduler().isTicking(pos, this)){
            TickPriority tickPriority = this.isTargetNotAligned(serverWorld, pos, state) ? TickPriority.HIGH : TickPriority.NORMAL;
            serverWorld.scheduleBlockTick(pos, this, this.getUpdateDelayInternal(state), tickPriority);
        }
    }
    //Abstract Methods
    protected abstract int calculateOutputPower(World world, BlockPos pos, BlockState state);
    protected abstract BlockState getUpdatedState(World world, BlockPos pos, BlockState state);
    protected abstract boolean hasStateChanged(World world, BlockPos pos, BlockState state);
    //Default Methods
    protected void updateState(World world, BlockPos pos, BlockState state) {
        state = this.getUpdatedState(world, pos, state);
        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
        this.updateTarget(world, pos, state);
    }
}
