package net.gordyjack.jaavaa.potion.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.*;
import net.minecraft.server.world.*;

import java.util.*;

public class ImpendingDoomEffect extends StatusEffect {
    public ImpendingDoomEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        Collection<StatusEffectInstance> effects = entity.getStatusEffects();
        if (effects.size() == 1) {
            StatusEffectInstance effect = effects.iterator().next();
            if (effect.getEffectType().getIdAsString().equals("jaavaa:impending_doom")) {
                if (!(entity instanceof PlayerEntity playerEntity) ||
                        !(playerEntity.isCreative() || playerEntity.isSpectator())) {
                    entity.kill(world);
                }
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
