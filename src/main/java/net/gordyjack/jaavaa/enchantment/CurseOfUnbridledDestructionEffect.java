package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
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
        if (user instanceof ServerPlayerEntity player && !player.isCreative()) {
            BlockPos blockPos = new BlockPos((int) Math.floor(pos.x), (int) Math.floor(pos.y), (int) Math.floor(pos.z));
            BlockState currentState = world.getBlockState(blockPos);
            ItemStack tool = player.getMainHandStack();

            if (JAAVAABlockUtils.isToolCorrectForBlock(tool.getItem(), currentState)
                    && world.breakBlock(blockPos, false, player)) {
                if (tool.isDamageable()) {
                    tool.damage(2, world, player, item ->
                            player.playSoundToPlayer(item.getBreakSound(), SoundCategory.PLAYERS, 1.0F, player.getSoundPitch())
                    );
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
