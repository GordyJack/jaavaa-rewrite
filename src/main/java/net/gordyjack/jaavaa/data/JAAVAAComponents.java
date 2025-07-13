package net.gordyjack.jaavaa.data;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.potion.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.consume.*;
import net.minecraft.registry.*;

import java.util.*;
import java.util.function.*;

import static net.gordyjack.jaavaa.JAAVAA.*;
import static net.minecraft.component.type.ConsumableComponents.*;

public class JAAVAAComponents {
    public static final ConsumableComponent MALUM_STELLAE_INCANTATAE_CONSUMABLE = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(List.of(
                    new StatusEffectInstance(StatusEffects.REGENERATION, seconds(30), 9),
                    new StatusEffectInstance(StatusEffects.REGENERATION, minutes(10), 4),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, seconds(30), 4),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, minutes(10), 1),
                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, minutes(10), 0),
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, minutes(10), 4),
                    new StatusEffectInstance(StatusEffects.ABSORPTION, minutes(5), 4),
                    new StatusEffectInstance(JAAVAAStatusEffects.IMPENDING_DOOM, StatusEffectInstance.INFINITE, 0, false, false, true)
            ))).consumeSeconds(0.5f).build();
    public static final DamageResistantComponent FIRE_AND_EXPLOSION_RESISTANT =
            new DamageResistantComponent(JAAVAATags.Other.IS_EXPLOSION_OR_FIRE);
    public static final FoodComponent MALUM_STELLAE_INCANTATAE_FOOD =
            new FoodComponent.Builder().nutrition(20).saturationModifier(2.0f).alwaysEdible().build();

    public static void init() {
        Types.registerDataComponentTypes();
        JAAVAA.log("Initializing JAAVAA components");
    }
    public static class Types {
        public static final ComponentType<Integer> HAMMER_RANGE =
                register("hammer_range", builder -> builder.codec(Codec.INT));
        public static final ComponentType<Boolean> MAGNET_ENABLED =
                register("enabled", builder -> builder.codec(Codec.BOOL));
        public static final ComponentType<CapturedMobComponent> MOB_NET_ENTITY =
                register("mob_net_entity", builder -> builder.codec(CapturedMobComponent.CODEC));
        private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
            return Registry.register(Registries.DATA_COMPONENT_TYPE, JAAVAA.id(name),
                    builderOperator.apply(ComponentType.builder()).build());
        }
        public static void registerDataComponentTypes() {
            JAAVAA.log("Registering Data Component Types for " + JAAVAA.MOD_ID);
        }
    }
}
