package net.gordyjack.jaavaa.block.custom;

import com.mojang.datafixers.*;
import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.custom.entity.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.particle.*;
import net.minecraft.screen.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class AlloyFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<AlloyFurnaceBlock> CODEC = createCodec(AlloyFurnaceBlock::new);
    
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
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean lit = state.get(LIT);
        
        boolean northLava = world.getBlockState(pos.north()).isOf(Blocks.LAVA);
        boolean southLava = world.getBlockState(pos.south()).isOf(Blocks.LAVA);
        boolean eastLava = world.getBlockState(pos.east()).isOf(Blocks.LAVA);
        boolean westLava = world.getBlockState(pos.west()).isOf(Blocks.LAVA);
        boolean upLava = world.getBlockState(pos.up()).isOf(Blocks.LAVA);
        boolean downLava = world.getBlockState(pos.down()).isOf(Blocks.LAVA);
        boolean lavaExists = northLava || southLava || eastLava || westLava || upLava || downLava;
        
        if (!lit && lavaExists) {
            world.setBlockState(pos, state.with(LIT, true));
        } else if (lit && !lavaExists) {
            world.setBlockState(pos, state.with(LIT, false));
        }
        
        super.scheduledTick(state, world, pos, random);
    }
}
