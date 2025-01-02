package net.gordyjack.jaavaa.potion;

import net.fabricmc.fabric.api.registry.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;

public class JAAVAAPotionRecipeBuilder {
    private static final Item TIER_1_UPGRADE = Items.GLOWSTONE_DUST;
    private static final Item TIER_2_UPGRADE = JAAVAAItems.ALLAY_ESSENCE;
    private static final Item TIER_3_UPGRADE = JAAVAAItems.SHULKER_PEARL;
    private static final Item TIER_4_UPGRADE = JAAVAAItems.STARSTEEL_INGOT;
    private static final Item LENGTH_UPGRADE = Items.REDSTONE;
    private static final Item INFINITE_UPGRADE = Items.GOLDEN_APPLE;
    private static final Item ETERNAL_UPGRADE = JAAVAAItems.MALUM_STELLAE_INCANTATAE;
    private static final Item INVERTER_ITEM = Items.FERMENTED_SPIDER_EYE;

    public static void build() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            // Positive Effects
            //Absorption Potion
            builder.registerRecipes(Items.GOLDEN_APPLE, JAAVAAPotions.ABSORPTION_1_POTION);
            // Power up the absorption potion
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.ABSORPTION_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.ABSORPTION_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.ABSORPTION_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.ABSORPTION_5_POTION);
            // Lengthen the absorption potion
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.ABSORPTION_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.ABSORPTION_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.ABSORPTION_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.ABSORPTION_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.ABSORPTION_5_POTION_LONG);
            // Power up the lengthened absorption potion
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.ABSORPTION_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.ABSORPTION_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.ABSORPTION_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.ABSORPTION_5_POTION_LONG);
            // Infinite Absorption Potion
            builder.registerPotionRecipe(JAAVAAPotions.ABSORPTION_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_ABSORPTION_POTION);
            // Eternal Absorption Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_ABSORPTION_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_ABSORPTION_POTION);

            // Glowing Potion
            builder.registerRecipes(Items.GLOW_INK_SAC, JAAVAAPotions.GLOWING_1_POTION);
            builder.registerRecipes(Items.GLOW_BERRIES, JAAVAAPotions.GLOWING_1_POTION);
            // Lengthen the glowing potion
            builder.registerPotionRecipe(JAAVAAPotions.GLOWING_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.GLOWING_1_POTION_LONG);
            // Eternal Glowing Potion
            builder.registerPotionRecipe(JAAVAAPotions.GLOWING_1_POTION_LONG, ETERNAL_UPGRADE, JAAVAAPotions.INFINITE_GLOWING_POTION);

            // Haste Potion
            builder.registerPotionRecipe(Potions.SWIFTNESS, Items.HONEY_BOTTLE, JAAVAAPotions.HASTE_1_POTION);
            builder.registerPotionRecipe(Potions.STRONG_SWIFTNESS, Items.HONEY_BOTTLE, JAAVAAPotions.HASTE_2_POTION);
            builder.registerPotionRecipe(Potions.LONG_SWIFTNESS, Items.HONEY_BOTTLE, JAAVAAPotions.HASTE_1_POTION_LONG);
            // Power up the haste potion
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.HASTE_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.HASTE_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.HASTE_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.HASTE_5_POTION);
            // Lengthen the haste potion
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.HASTE_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.HASTE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.HASTE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.HASTE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.HASTE_5_POTION_LONG);
            // Power up the lengthened haste potion
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.HASTE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.HASTE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.HASTE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.HASTE_5_POTION_LONG);
            // Infinite Haste Potion
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_HASTE_POTION);
            // Eternal Haste Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_HASTE_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_HASTE_POTION);

            // Resistance Potion
            builder.registerRecipes(Items.DIAMOND, JAAVAAPotions.RESISTANCE_1_POTION);
            // Power up the resistance potion
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.RESISTANCE_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.RESISTANCE_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.RESISTANCE_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.RESISTANCE_5_POTION);
            // Lengthen the resistance potion
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.RESISTANCE_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.RESISTANCE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.RESISTANCE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.RESISTANCE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.RESISTANCE_5_POTION_LONG);
            // Power up the lengthened resistance potion
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.RESISTANCE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.RESISTANCE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.RESISTANCE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.RESISTANCE_5_POTION_LONG);
            // Infinite Resistance Potion
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_RESISTANCE_POTION);
            // Eternal Resistance Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_RESISTANCE_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_RESISTANCE_POTION);

            // Saturation Potion
            builder.registerRecipes(Items.BROWN_MUSHROOM, JAAVAAPotions.SATURATION_1_POTION);
            // Power up the saturation potion
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.SATURATION_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.SATURATION_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.SATURATION_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.SATURATION_5_POTION);
            // Lengthen the saturation potion
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.SATURATION_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.SATURATION_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.SATURATION_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.SATURATION_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.SATURATION_5_POTION_LONG);
            // Power up the lengthened saturation potion
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.SATURATION_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.SATURATION_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.SATURATION_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.SATURATION_5_POTION_LONG);
            // Infinite Saturation Potion
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_SATURATION_POTION);
            // Eternal Saturation Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_SATURATION_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_SATURATION_POTION);

            // Negative Effects
            // Bad Luck Potion
            builder.registerPotionRecipe(Potions.LUCK, INVERTER_ITEM, JAAVAAPotions.BAD_LUCK_1_POTION);
            // Lengthen the bad luck potion
            builder.registerPotionRecipe(JAAVAAPotions.BAD_LUCK_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.BAD_LUCK_1_POTION_LONG);
            // Eternal Bad Luck Potion
            builder.registerPotionRecipe(JAAVAAPotions.BAD_LUCK_1_POTION, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_BAD_LUCK_POTION);

            // Blindness Potion
            builder.registerRecipes(Items.INK_SAC, JAAVAAPotions.BLINDNESS_1_POTION);
            builder.registerRecipes(Items.BLACK_DYE, JAAVAAPotions.BLINDNESS_1_POTION);
            // Lengthen the blindness potion
            builder.registerPotionRecipe(JAAVAAPotions.BLINDNESS_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.BLINDNESS_1_POTION_LONG);
            // Eternal Blindness Potion
            builder.registerPotionRecipe(JAAVAAPotions.BLINDNESS_1_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_BLINDNESS_POTION);

            // Darkness Potion
            builder.registerPotionRecipe(Potions.NIGHT_VISION, INVERTER_ITEM, JAAVAAPotions.DARKNESS_1_POTION);
            // Lengthen the darkness potion
            builder.registerPotionRecipe(JAAVAAPotions.DARKNESS_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.DARKNESS_1_POTION_LONG);
            // Eternal Darkness Potion
            builder.registerPotionRecipe(JAAVAAPotions.DARKNESS_1_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_DARKNESS_POTION);

            // Hunger Potion;
            builder.registerRecipes(Items.ROTTEN_FLESH, JAAVAAPotions.HUNGER_1_POTION);
            // Power up the hunger potion
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.HUNGER_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.HUNGER_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.HUNGER_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.HUNGER_5_POTION);
            // Lengthen the hunger potion
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.HUNGER_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.HUNGER_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.HUNGER_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.HUNGER_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.HUNGER_5_POTION_LONG);
            // Power up the lengthened hunger potion
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.HUNGER_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.HUNGER_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.HUNGER_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.HUNGER_5_POTION_LONG);
            // Infinite Hunger Potion
            builder.registerPotionRecipe(JAAVAAPotions.HUNGER_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_HUNGER_POTION);
            // Eternal Hunger Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_HUNGER_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_HUNGER_POTION);
            //Inverts
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_1_POTION, INVERTER_ITEM, JAAVAAPotions.HUNGER_1_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_2_POTION, INVERTER_ITEM, JAAVAAPotions.HUNGER_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_3_POTION, INVERTER_ITEM, JAAVAAPotions.HUNGER_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_4_POTION, INVERTER_ITEM, JAAVAAPotions.HUNGER_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_5_POTION, INVERTER_ITEM, JAAVAAPotions.HUNGER_5_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_1_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.HUNGER_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_2_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.HUNGER_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_3_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.HUNGER_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_4_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.HUNGER_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.SATURATION_5_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.HUNGER_5_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_SATURATION_POTION, INVERTER_ITEM, JAAVAAPotions.INFINITE_HUNGER_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.ETERNAL_SATURATION_POTION, INVERTER_ITEM, JAAVAAPotions.ETERNAL_HUNGER_POTION);

            // Levitation Potion
            builder.registerRecipes(JAAVAAItems.SHULKER_PEARL, JAAVAAPotions.LEVITATION_1_POTION);
            // Power up the levitation potion
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.LEVITATION_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.LEVITATION_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.LEVITATION_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.LEVITATION_5_POTION);
            // Lengthen the levitation potion
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.LEVITATION_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.LEVITATION_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.LEVITATION_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.LEVITATION_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.LEVITATION_5_POTION_LONG);
            // Power up the lengthened levitation potion
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.LEVITATION_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.LEVITATION_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.LEVITATION_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.LEVITATION_5_POTION_LONG);
            // Infinite Levitation Potion
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_LEVITATION_POTION);
            // Eternal Levitation Potion
            builder.registerPotionRecipe(JAAVAAPotions.LEVITATION_5_POTION_LONG, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_LEVITATION_POTION);
            // Inverters
            builder.registerPotionRecipe(Potions.SLOW_FALLING, INVERTER_ITEM, JAAVAAPotions.LEVITATION_1_POTION);
            builder.registerPotionRecipe(Potions.LONG_SLOW_FALLING, INVERTER_ITEM, JAAVAAPotions.LEVITATION_1_POTION_LONG);

            // Mining Fatigue Potion
            builder.registerRecipes(Items.SPONGE, JAAVAAPotions.MINING_FATIGUE_1_POTION);
            builder.registerRecipes(Items.WET_SPONGE, JAAVAAPotions.MINING_FATIGUE_1_POTION);
            // Power up the mining fatigue potion
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.MINING_FATIGUE_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.MINING_FATIGUE_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.MINING_FATIGUE_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.MINING_FATIGUE_5_POTION);
            // Lengthen the mining fatigue potion
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.MINING_FATIGUE_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.MINING_FATIGUE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.MINING_FATIGUE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.MINING_FATIGUE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.MINING_FATIGUE_5_POTION_LONG);
            // Power up the lengthened mining fatigue potion
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.MINING_FATIGUE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.MINING_FATIGUE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.MINING_FATIGUE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.MINING_FATIGUE_5_POTION_LONG);
            // Infinite Mining Fatigue Potion
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_MINING_FATIGUE_POTION);
            // Eternal Mining Fatigue Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_MINING_FATIGUE_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_MINING_FATIGUE_POTION);
            // Inverters
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_1_POTION, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_1_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_2_POTION, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_3_POTION, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_4_POTION, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_5_POTION, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_5_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_1_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_2_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_3_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_4_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_5_POTION_LONG, INVERTER_ITEM, JAAVAAPotions.MINING_FATIGUE_5_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_HASTE_POTION, INVERTER_ITEM, JAAVAAPotions.INFINITE_MINING_FATIGUE_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.ETERNAL_HASTE_POTION, INVERTER_ITEM, JAAVAAPotions.ETERNAL_MINING_FATIGUE_POTION);

            // Nausea Potion
            builder.registerPotionRecipe(Potions.WATER_BREATHING, INVERTER_ITEM, JAAVAAPotions.NAUSEA_1_POTION);
            builder.registerPotionRecipe(Potions.LONG_WATER_BREATHING, INVERTER_ITEM, JAAVAAPotions.NAUSEA_1_POTION_LONG);
            // Lengthen the nausea potion
            builder.registerPotionRecipe(JAAVAAPotions.NAUSEA_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.NAUSEA_1_POTION_LONG);
            // Eternal Nausea Potion
            builder.registerPotionRecipe(JAAVAAPotions.NAUSEA_1_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_NAUSEA_POTION);

            // Wither Potion
            builder.registerPotionRecipe(Potions.REGENERATION, Items.WITHER_ROSE, JAAVAAPotions.WITHER_1_POTION);
            builder.registerPotionRecipe(Potions.STRONG_REGENERATION, Items.WITHER_ROSE, JAAVAAPotions.WITHER_2_POTION);
            builder.registerPotionRecipe(Potions.LONG_REGENERATION, Items.WITHER_ROSE, JAAVAAPotions.WITHER_1_POTION_LONG);
            // Power up the wither potion
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_1_POTION, TIER_1_UPGRADE, JAAVAAPotions.WITHER_2_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_2_POTION, TIER_2_UPGRADE, JAAVAAPotions.WITHER_3_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_3_POTION, TIER_3_UPGRADE, JAAVAAPotions.WITHER_4_POTION);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_4_POTION, TIER_4_UPGRADE, JAAVAAPotions.WITHER_5_POTION);
            // Lengthen the wither potion
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.WITHER_1_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_2_POTION, LENGTH_UPGRADE, JAAVAAPotions.WITHER_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_3_POTION, LENGTH_UPGRADE, JAAVAAPotions.WITHER_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_4_POTION, LENGTH_UPGRADE, JAAVAAPotions.WITHER_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_5_POTION, LENGTH_UPGRADE, JAAVAAPotions.WITHER_5_POTION_LONG);
            // Power up the lengthened wither potion
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_1_POTION_LONG, TIER_1_UPGRADE, JAAVAAPotions.WITHER_2_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_2_POTION_LONG, TIER_2_UPGRADE, JAAVAAPotions.WITHER_3_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_3_POTION_LONG, TIER_3_UPGRADE, JAAVAAPotions.WITHER_4_POTION_LONG);
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_4_POTION_LONG, TIER_4_UPGRADE, JAAVAAPotions.WITHER_5_POTION_LONG);
            // Infinite Wither Potion
            builder.registerPotionRecipe(JAAVAAPotions.WITHER_5_POTION_LONG, INFINITE_UPGRADE, JAAVAAPotions.INFINITE_WITHER_POTION);
            // Eternal Wither Potion
            builder.registerPotionRecipe(JAAVAAPotions.INFINITE_WITHER_POTION, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_WITHER_POTION);
        });
    }
}
