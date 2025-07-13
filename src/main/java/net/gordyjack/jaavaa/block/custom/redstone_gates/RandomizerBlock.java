package net.gordyjack.jaavaa.block.custom.redstone_gates;

import com.mojang.serialization.*;
import net.minecraft.block.*;
import net.minecraft.particle.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

//TODO: add mode to where you can have it constantly change signal strengths while powered.
public class RandomizerBlock extends AbstractAdvancedRedstoneGateBlock {
    public static final MapCodec<RandomizerBlock> CODEC = createCodec(RandomizerBlock::new);
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final IntProperty POWER = Properties.POWER;
    private static final int UPDATE_DELAY = 2;

    public RandomizerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(POWERED, false)
                        .with(POWER, 0)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, POWER);
    }
    @Override
    protected int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        return this.hasPower(world, pos, state) ? world.getRandom().nextBetween(1, 15) : 0;
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
    protected BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
        return state
                .with(POWERED, this.hasPower(world, pos, state))
                .with(POWER, this.calculateOutputPower(world, pos, state));
    }
    @Override
    protected boolean hasStateChanged(World world, BlockPos pos, BlockState state) {
        return state.get(POWERED) != this.hasPower(world, pos, state);
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
            world.addParticleClient(DustParticleEffect.DEFAULT, d + h, e, f + i, 0.0, 0.0, 0.0);
        }
    }
}
