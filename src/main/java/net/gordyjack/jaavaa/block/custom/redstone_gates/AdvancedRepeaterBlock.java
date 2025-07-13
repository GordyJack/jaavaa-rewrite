package net.gordyjack.jaavaa.block.custom.redstone_gates;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.tick.*;

//TODO: Update the properties textures and models to show when the delay and pulse (independently) are active.
public class AdvancedRepeaterBlock extends AbstractRedstoneGateBlock {
    public static final MapCodec<AdvancedRepeaterBlock> CODEC = AdvancedRepeaterBlock.createCodec(AdvancedRepeaterBlock::new);
    public static final BooleanProperty LOCKED = Properties.LOCKED;
    public static final IntProperty DELAY = JAAVAABlockProperties.DELAY;
    public static final IntProperty PULSE = JAAVAABlockProperties.PULSE;

    public AdvancedRepeaterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, false)
                        .with(LOCKED, false)
                        .with(DELAY, 0)
                        .with(PULSE, 0)
        );
    }

    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState blockState = super.getPlacementState(ctx);
        return blockState.with(LOCKED, this.isLocked(world, pos, blockState));
    }
    @Override
    protected boolean getSideInputFromGatesOnly() {
        return true;
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
                                                   BlockPos pos, Direction direction, BlockPos neighborPos,
                                                   BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !this.canPlaceAbove(world, neighborPos, neighborState)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return !world.isClient() && direction.getAxis() != state.get(FACING).getAxis()
                    ? state.with(LOCKED, this.isLocked(world, pos, state))
                    : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }
    protected int getPulseLengthInternal(BlockState state) {
        int pulse = state.get(PULSE);
        return 1 << pulse;
        // Potential pulse lengths: // 0 (1 tick), 1 (2 ticks), 2 (4 ticks), 3 (8 ticks), 4 (16 ticks), 5 (32 ticks), 6 (64 ticks), 7 (128 ticks)
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        int delay = state.get(DELAY);
        return 1 << delay;
        // Potential update delays: // 0 (1 tick), 1 (2 ticks), 2 (4 ticks), 3 (8 ticks), 4 (16 ticks), 5 (32 ticks), 6 (64 ticks), 7 (128 ticks)
    }
    @Override
    public boolean isLocked(WorldView world, BlockPos pos, BlockState state) {
        return this.getMaxInputLevelSides(world, pos, state) > 0;
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        }
        Direction facing = state.get(FACING);
        double xPos = hit.getPos().x - pos.getX();
        double zPos = hit.getPos().z - pos.getZ();
        boolean adjustDelay = switch (facing) {
            case NORTH -> zPos <= 0.5;
            case SOUTH -> zPos > 0.5;
            case WEST -> xPos <= 0.5;
            case EAST -> xPos > 0.5;
            default -> false;
        };
        boolean adjustPulse = !adjustDelay;
        if (adjustDelay) {
            world.setBlockState(pos, state.cycle(DELAY), Block.NOTIFY_ALL);
        }
        if (adjustPulse) {
            world.setBlockState(pos, state.cycle(PULSE), Block.NOTIFY_ALL);
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.isLocked(world, pos, state)) {
            return;
        }
        boolean powered = state.get(POWERED);
        boolean shouldBePowered = this.hasPower(world, pos, state);
        if (powered && !shouldBePowered) {
            world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_LISTENERS);
        } else if (!powered) {
            world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_LISTENERS);
            if (!shouldBePowered) {
                world.scheduleBlockTick(pos, this, this.getPulseLengthInternal(state), TickPriority.VERY_HIGH);
            }
        }
    }
    @Override
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        if (this.isLocked(world, pos, state)) {
            return;
        }
        boolean powered = state.get(POWERED);
        boolean shouldBePowered = this.hasPower(world, pos, state);
        if (powered != shouldBePowered && !world.getBlockTickScheduler().isTicking(pos, this)) {
            TickPriority tickPriority = TickPriority.HIGH;
            if (this.isTargetNotAligned(world, pos, state)) {
                tickPriority = TickPriority.EXTREMELY_HIGH;
            } else if (powered) {
                tickPriority = TickPriority.VERY_HIGH;
            }
            if (!powered) {
                world.scheduleBlockTick(pos, this, this.getUpdateDelayInternal(state), tickPriority);
            } else {
                world.scheduleBlockTick(pos, this, this.getPulseLengthInternal(state), tickPriority);
            }
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, LOCKED, DELAY, PULSE);
    }
}
