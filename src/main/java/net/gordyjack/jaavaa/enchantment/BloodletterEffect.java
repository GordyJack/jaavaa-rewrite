package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.gordyjack.jaavaa.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;

public record BloodletterEffect(EnchantmentLevelBasedValue level)
implements EnchantmentEntityEffect {
    public static final MapCodec<BloodletterEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    net.minecraft.enchantment.EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(BloodletterEffect::level)
            ).apply(instance, BloodletterEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        // Add health to the attacker based on the damage dealt to the victim.
        if (user instanceof LivingEntity victim && context.owner() instanceof LivingEntity attacker) {
            float attackerMaxHealth = attacker.getMaxHealth();
            float victimHealthDelta = victim.getMaxHealth() - victim.getHealth();
            float healPercentage = level().getValue(level) / 100.0f;
            float healthToAdd = Math.min(victimHealthDelta * healPercentage, attackerMaxHealth / 5.0f);
            // Log all the values for debugging purposes.
            JAAVAA.log("attackerMaxHealth: " + attackerMaxHealth + " | victimHealthDelta: " + victimHealthDelta + " | healPercentage: " + healPercentage + " | healthToAdd: " + healthToAdd);
            attacker.heal(healthToAdd);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
