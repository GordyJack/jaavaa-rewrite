package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.block.*;
import net.minecraft.block.pattern.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;

public record CurseOfUnbridledDestructionEffect(EnchantmentLevelBasedValue level)
        implements EnchantmentEntityEffect {
    public static final MapCodec<CurseOfUnbridledDestructionEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(CurseOfUnbridledDestructionEffect::level)
            ).apply(instance, CurseOfUnbridledDestructionEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof PlayerEntity player && !player.isCreative()) {
            BlockPos blockPos = new BlockPos((int) Math.floor(pos.x), (int) Math.floor(pos.y), (int) Math.floor(pos.z));
            BlockState currentState = world.getBlockState(blockPos);
            ItemStack tool = player.getMainHandStack();

            boolean isPaxelCorrect = tool.getItem() instanceof PaxelItem && currentState.isIn(JAAVAATags.Blocks.PAXEL_MINEABLE);

            if ((tool.canBreak(new CachedBlockPosition(world, blockPos, true)) || isPaxelCorrect)
                    && world.breakBlock(blockPos, false, player)) {
                if (tool.isDamageable()) {
                    tool.damage(2, player);
                }
                currentState.getBlock().onBreak(world, blockPos, world.getBlockState(blockPos), player);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
