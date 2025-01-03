package net.gordyjack.jaavaa.potion.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.server.world.*;

public class FatalPoisonEffect extends PoisonStatusEffect {
    public FatalPoisonEffect() {
        super(StatusEffectCategory.HARMFUL, 8889187);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.damage(world, entity.getDamageSources().magic(), 1.0F);
        return true;
    }
}
