package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.entity.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.sound.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import net.minecraft.world.tick.*;
import org.jetbrains.annotations.*;

public class AlloyFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<AlloyFurnaceBlock> CODEC = createCodec(AlloyFurnaceBlock::new);
    public static final String TITLE_KEY = "jaavaa.container.alloy_furnace";

    public AlloyFurnaceBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> getCodec() {
        return CODEC;
    }
    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof AlloyFurnaceBlockEntity alloyFurnaceBlockEntity) {
            player.openHandledScreen(alloyFurnaceBlockEntity);
        }
    }
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyFurnaceBlockEntity(pos, state);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double soundX = pos.getX() + 0.5;
            double soundY = pos.getY();
            double soundZ = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(soundX, soundY, soundZ, SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double facingDirectionOffset = 0.52;
            double nonFacingDirectionOffset = random.nextDouble() * 0.6 - 0.3;
            double particleXOffset = axis == Direction.Axis.X ? direction.getOffsetX() * facingDirectionOffset : nonFacingDirectionOffset;
            double particleYOffset = random.nextDouble() * 9.0 / 16.0;
            double particleZOffset = axis == Direction.Axis.Z ? direction.getOffsetZ() * facingDirectionOffset : nonFacingDirectionOffset;
            world.addParticle(ParticleTypes.SMOKE, soundX + particleXOffset, soundY + particleYOffset, soundZ + particleZOffset, 0.0, 0.0, 0.0);
        }
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var state = this.getLitState(this.getDefaultState(), ctx.getWorld(), ctx.getBlockPos());
        return state.with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return this.getLitState(state, (World) world, pos);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, JAAVAABlockEntityTypes.ALLOY_FURNACE_BLOCK_ENTITY_TYPE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
    private BlockState getLitState(BlockState state, World world, BlockPos pos) {
        boolean northLava = world.getBlockState(pos.north()).isOf(Blocks.LAVA);
        boolean southLava = world.getBlockState(pos.south()).isOf(Blocks.LAVA);
        boolean eastLava = world.getBlockState(pos.east()).isOf(Blocks.LAVA);
        boolean westLava = world.getBlockState(pos.west()).isOf(Blocks.LAVA);
        boolean upLava = world.getBlockState(pos.up()).isOf(Blocks.LAVA);
        boolean downLava = world.getBlockState(pos.down()).isOf(Blocks.LAVA);
        boolean lavaExists = northLava || southLava || eastLava || westLava || upLava || downLava;

        return lavaExists ? state.with(LIT, true) : state.with(LIT, false);
    }
}
