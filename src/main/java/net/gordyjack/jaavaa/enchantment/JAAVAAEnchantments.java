package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.enchantment.effect.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.enchantment.effect.value.*;
import net.minecraft.loot.context.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;

import java.util.*;
import java.util.function.*;

public class JAAVAAEnchantments {
    public static final RegistryKey<Enchantment> CURSE_OF_THE_CAPRICIOUS = of("curse_of_the_capricious");

    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, JAAVAA.id(id));
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
                Enchantment.leveledCost(21, 75),
                Enchantment.leveledCost(75, 75),
                5,
                AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.BLOCK_EXPERIENCE,
                        new SetEnchantmentEffect(EnchantmentLevelBasedValue.linear(1, 1)))
                .addEffect(ComponentTypes.CURSE_OF_THE_CAPRICIOUS_COMPONENT,
                        new CurseOfTheCapriciousEffect(EnchantmentLevelBasedValue.linear(1, 1)))
        );
    }
    public static void init() {
        JAAVAA.log("Initializing JAAVAA enchantments");
        Effects.init();
        ComponentTypes.init();
    }

    public static class Effects {
        public static final MapCodec<CurseOfTheCapriciousEffect> CURSE_OF_THE_CAPRICIOUS_EFFECT = register("curse_of_the_capricious", CurseOfTheCapriciousEffect.CODEC);

        private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
            return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, JAAVAA.id(id), codec);
        }
        protected static void init() {
            JAAVAA.log("Initializing JAAVAA enchantment effects");
        }
    }
    public static class ComponentTypes {
        public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentEntityEffect>>> CURSE_OF_THE_CAPRICIOUS_COMPONENT =
                register("curse_of_the_capricious", builder ->
                        builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentEntityEffect.CODEC, LootContextTypes.BLOCK).listOf()));

        private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
            return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, id, builderOperator.apply(ComponentType.builder()).build());
        }
        protected static void init() {
            JAAVAA.log("Initializing JAAVAA enchantment component types");
        }
    }
}
