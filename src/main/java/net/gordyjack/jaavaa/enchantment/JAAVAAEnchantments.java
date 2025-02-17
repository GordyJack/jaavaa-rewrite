package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.enchantment.effect.entity.*;
import net.minecraft.enchantment.effect.value.*;
import net.minecraft.loot.context.*;
import net.minecraft.particle.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.sound.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;
import java.util.function.*;

public class JAAVAAEnchantments {
    public static final List<RegistryKey<Enchantment>> ENCHANTMENTS = new ArrayList<>();
    public static final RegistryKey<Enchantment> CURSE_OF_THE_CAPRICIOUS = of("curse_of_the_capricious");
    public static final RegistryKey<Enchantment> CURSE_OF_PERSISTENCE = of("curse_of_persistence");
    public static final RegistryKey<Enchantment> CURSE_OF_UNBRIDLED_DESTRUCTION = of("curse_of_unbridled_destruction");
    public static final RegistryKey<Enchantment> PACTED = of("pacted");

    private static RegistryKey<Enchantment> of(String id) {
        var key = RegistryKey.of(RegistryKeys.ENCHANTMENT, JAAVAA.id(id));
        ENCHANTMENTS.add(key);
        return key;
    }
    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
    public static void bootstrap(Registerable<Enchantment> registerable) {
        var blocks = registerable.getRegistryLookup(RegistryKeys.BLOCK);
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, CURSE_OF_THE_CAPRICIOUS, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                1, 5,
                Enchantment.leveledCost(18, 5),
                Enchantment.leveledCost(100, 5),
                8,
                AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.BLOCK_EXPERIENCE,
                        new SetEnchantmentEffect(EnchantmentLevelBasedValue.constant(0.0f)))
        );
        register(registerable, CURSE_OF_PERSISTENCE, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                        1, 5,
                        Enchantment.leveledCost(18, 5),
                        Enchantment.leveledCost(100, 5),
                        8,
                        AttributeModifierSlot.HAND))
                .addEffect(EnchantmentEffectComponentTypes.HIT_BLOCK,
                        new ExplodeEnchantmentEffect(
                                true,
                                Optional.empty(),
                                Optional.of(EnchantmentLevelBasedValue.constant(0)),
                                blocks.getOptional(BlockTags.AIR).map(Function.identity()),
                                Vec3d.ZERO,
                                EnchantmentLevelBasedValue.linear(2, 1),
                                false,
                                World.ExplosionSourceType.TRIGGER,
                                ParticleTypes.EXPLOSION,
                                ParticleTypes.EXPLOSION_EMITTER,
                                SoundEvents.ENTITY_GENERIC_EXPLODE
                        ))
        );
        register(registerable, CURSE_OF_UNBRIDLED_DESTRUCTION, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                2, 1,
                Enchantment.constantCost(25),
                Enchantment.constantCost(75),
                6,
                AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(JAAVAATags.Other.UNBRIDLED_DESTRUCTION_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.HIT_BLOCK,
                        new CurseOfUnbridledDestructionEffect(EnchantmentLevelBasedValue.constant(0.0f)))
        );
        register(registerable, PACTED, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                2, 1,
                Enchantment.constantCost(25),
                Enchantment.constantCost(75),
                4,
                AttributeModifierSlot.ANY))
        );

    }
    public static void init() {
        JAAVAA.log("Initializing JAAVAA enchantments");
        Effects.init();
    }

    public static class Components {
        public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentEntityEffect>>> ITEM_DAMAGE = register(
                "jaavaa_item_damage", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentEntityEffect.CODEC, LootContextTypes.ENCHANTED_ITEM).listOf())
        );

        private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
            return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, id, builderOperator.apply(ComponentType.builder()).build());
        }
        protected static void init() {
            JAAVAA.log("Initializing JAAVAA enchantment components");
        }
    }
    public static class Effects {
        public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_OF_UNBRIDLED_DESTRUCTION_EFFECT =
                registerEntityEffect("curse_of_unbridled_destruction", CurseOfUnbridledDestructionEffect.CODEC);
        public static final MapCodec<? extends EnchantmentEntityEffect> PACTED_EFFECT =
                registerEntityEffect("pacted", PactedEffect.CODEC);


        private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
            return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, JAAVAA.id(name), codec);
        }
        public static MapCodec<? extends EnchantmentValueEffect> registerValueEffect(String name, MapCodec<? extends EnchantmentValueEffect> codec) {
            return Registry.register(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE, JAAVAA.id(name), codec);
        }
        protected static void init() {
            JAAVAA.log("Initializing JAAVAA enchantment effects");
        }
    }
}
