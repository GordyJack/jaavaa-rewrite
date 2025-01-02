package net.gordyjack.jaavaa.data;

import net.gordyjack.jaavaa.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.consume.*;

import java.util.*;

import static net.gordyjack.jaavaa.JAAVAA.*;
import static net.minecraft.component.type.ConsumableComponents.*;

public class JAAVAAComponents {
    public static final ConsumableComponent MALUM_STELLAE_INCANTATAE_CONSUMABLE = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(List.of(
                    new StatusEffectInstance(StatusEffects.REGENERATION, minutes(1), 3),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, minutes(5), 1),
                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, minutes(5), 0),
                    new StatusEffectInstance(StatusEffects.ABSORPTION, minutes(2), 1))
            )).consumeSeconds(0.5f).build();
    public static final DamageResistantComponent FIRE_AND_EXPLOSION_RESISTANT =
            new DamageResistantComponent(JAAVAATags.Other.IS_EXPLOSION_OR_FIRE);    public static final FoodComponent MALUM_STELLAE_INCANTATAE_FOOD =
            new FoodComponent.Builder().nutrition(20).saturationModifier(2.0f).alwaysEdible().build();

    public static void init() {
        JAAVAA.log("Initializing JAAVAA components");
    }
}
