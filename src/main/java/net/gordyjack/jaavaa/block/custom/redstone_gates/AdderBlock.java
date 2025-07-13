package net.gordyjack.jaavaa.block.custom.redstone_gates;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.particle.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public class AdderBlock extends AbstractAdvancedRedstoneGateBlock {
    //Codec
    public static final MapCodec<AdderBlock> CODEC = createCodec(AdderBlock::new);
    //Properties
    public static final BooleanProperty LEFT_POWERED = JAAVAABlockProperties.LEFT_POWERED;
    public static final BooleanProperty BACK_POWERED = JAAVAABlockProperties.BACK_POWERED;
    public static final BooleanProperty RIGHT_POWERED = JAAVAABlockProperties.RIGHT_POWERED;
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
    protected int calculateOutputPower(World world, BlockPos pos, BlockState state) {
        int[] inputPowers = this.getInputPowers(world, pos, state);
        return Math.min(inputPowers[0] + inputPowers[1] + inputPowers[2], 15);
    }
    @Override
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return CODEC;
    }
    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        return this.calculateOutputPower(world, pos, state);
    }
    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return UPDATE_DELAY;
    }
    @Override
    protected BlockState getUpdatedState(World world, BlockPos pos, BlockState state) {
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
    @Override
    protected boolean hasStateChanged(World world, BlockPos pos, BlockState state) {
        return state.get(POWERED) != this.hasPower(world, pos, state)
                || state.get(POWER) != this.getPower(world, pos, state);
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
    //Methods
    private int[] getInputPowers(World world, BlockPos pos, BlockState state) {
        Direction inputDirection = state.get(FACING);
        Direction leftInputDirection = inputDirection.rotateYClockwise();
        Direction rightInputDirection = inputDirection.rotateYCounterclockwise();

        int backInputPower = world.getEmittedRedstonePower(pos.offset(inputDirection), inputDirection);
        int leftInputPower = world.getEmittedRedstonePower(pos.offset(leftInputDirection), leftInputDirection);
        int rightInputPower = world.getEmittedRedstonePower(pos.offset(rightInputDirection), rightInputDirection);

        return new int[]{backInputPower, leftInputPower, rightInputPower};
    }
}
