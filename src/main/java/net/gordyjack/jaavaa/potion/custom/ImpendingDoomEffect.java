package net.gordyjack.jaavaa.potion.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.server.world.*;

//TODO: Add a custom icon texture for this effect.
public class ImpendingDoomEffect extends StatusEffect {
    public ImpendingDoomEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        var effects = entity.getStatusEffects();
        if (effects.size() == 1) {
            var effect = effects.iterator().next();
            if (effect.getEffectType().getIdAsString().equals("jaavaa:impending_doom")) {
                entity.kill(world);
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
