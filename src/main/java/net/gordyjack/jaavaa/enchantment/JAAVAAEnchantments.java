package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.enchantment.effect.value.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;

import java.util.*;

public class JAAVAAEnchantments {
    public static final List<RegistryKey<Enchantment>> ENCHANTMENTS = new ArrayList<>();
    public static final RegistryKey<Enchantment> CURSE_OF_THE_CAPRICIOUS = of("curse_of_the_capricious");
    public static final RegistryKey<Enchantment> CURSE_OF_UNBRIDLED_DESTRUCTION = of("curse_of_unbridled_destruction");

    private static RegistryKey<Enchantment> of(String id) {
        var key = RegistryKey.of(RegistryKeys.ENCHANTMENT, JAAVAA.id(id));
        ENCHANTMENTS.add(key);
        return key;
    }
    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, CURSE_OF_THE_CAPRICIOUS, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                1, 1,
                Enchantment.constantCost(21),
                Enchantment.constantCost(75),
                5,
                AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.BLOCK_EXPERIENCE,
                        new SetEnchantmentEffect(EnchantmentLevelBasedValue.constant(0.0f)))
        );
        register(registerable, CURSE_OF_UNBRIDLED_DESTRUCTION, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                1, 1,
                Enchantment.constantCost(21),
                Enchantment.constantCost(75),
                5,
                AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET))
                .exclusiveSet(enchantments.getOrThrow(JAAVAATags.Other.UNBRIDLED_DESTRUCTION_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.HIT_BLOCK,
                        new CurseOfUnbridledDestructionEffect(EnchantmentLevelBasedValue.constant(0.0f)))
        );

    }
    public static void init() {
        JAAVAA.log("Initializing JAAVAA enchantments");
        Effects.init();
    }

    public static class Effects {
        public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_OF_UNBRIDLED_DESTRUCTION_EFFECT =
                register("curse_of_unbridled_destruction", CurseOfUnbridledDestructionEffect.CODEC);


        private static MapCodec<? extends EnchantmentEntityEffect> register(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
            return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, JAAVAA.id(name), codec);
        }
        protected static void init() {
            JAAVAA.log("Initializing JAAVAA enchantment effects");
        }
    }
}
