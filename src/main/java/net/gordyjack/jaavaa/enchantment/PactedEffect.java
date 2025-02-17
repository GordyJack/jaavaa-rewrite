package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;

public record PactedEffect(EnchantmentLevelBasedValue level)
implements EnchantmentEntityEffect {
    public static final MapCodec<PactedEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(PactedEffect::level)
            ).apply(instance, PactedEffect::new)
    );
    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        var item = context.stack();
        if (item.isDamageable() && item.getDamage() == item.getMaxDamage() - 1) {
            item.setDamage(item.getMaxDamage() - 2);
        }
    }
    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
