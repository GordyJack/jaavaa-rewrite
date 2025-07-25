package net.gordyjack.jaavaa.block.custom;

import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.block.*;
import net.minecraft.world.event.*;
import net.minecraft.world.explosion.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class AdjustableRedstoneLampBlock
        extends Block {
    public static final IntProperty LUMINANCE = JAAVAABlockProperties.LUMINANCE;
    public static final BooleanProperty POWERED = Properties.POWERED;

    public AdjustableRedstoneLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LUMINANCE, 0).with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LUMINANCE, POWERED);
    }
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (oldState.getBlock() != state.getBlock() && world instanceof ServerWorld serverWorld) {
            this.update(state, serverWorld, pos);
        }
    }
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.update(state, world, pos);
    }
    private void update(BlockState state, ServerWorld world, BlockPos pos) {
        int power = world.getReceivedRedstonePower(pos);
        if (state.get(POWERED) || power > 0) {
            state = state.with(LUMINANCE, power).with(POWERED, power > 0);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
    }
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(LUMINANCE);
    }
    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.scheduleBlockTick(pos, this, 1);
        }
    }
    @Override
    protected void onExploded(BlockState state, ServerWorld world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger) {
        if (explosion.getDestructionType() == Explosion.DestructionType.TRIGGER_BLOCK && !world.isClient()) {
            this.cycleLuminance(state, world, pos);
        }
        super.onExploded(state, world, pos, explosion, stackMerger);
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(POWERED)) {
            return ActionResult.PASS;
        }
        if (world.isClient()) {
            state.cycle(LUMINANCE);
            int power = state.get(LUMINANCE);
            float alpha = power / 15.0F;
            world.addParticle(new DustParticleEffect(DustParticleEffect.RED, alpha), hit.getPos().x, hit.getPos().y, hit.getPos().z, 0, 0, 0);
            return ActionResult.SUCCESS;
        }
        this.cycleLuminance(state, world, pos);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        return ActionResult.CONSUME;
    }
    private void cycleLuminance(BlockState state, World world, BlockPos pos) {
        state = state.cycle(LUMINANCE);
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        world.updateNeighborsAlways(pos, this);
    }
}
