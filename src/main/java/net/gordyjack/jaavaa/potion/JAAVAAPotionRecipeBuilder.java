package net.gordyjack.jaavaa.potion;

import net.fabricmc.fabric.api.registry.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;

public class JAAVAAPotionRecipeBuilder {
    private static final Item TIER_1_UPGRADE = Items.GLOWSTONE_DUST;
    private static final Item TIER_2_UPGRADE = JAAVAAItems.ALLAY_ESSENCE;
    private static final Item TIER_3_UPGRADE = JAAVAAItems.ALLAY_ESSENCE;
    private static final Item TIER_4_UPGRADE = JAAVAAItems.ALLAY_ESSENCE;
    private static final Item LENGTH_UPGRADE = Items.REDSTONE;
    private static final Item ETERNAL_UPGRADE = JAAVAAItems.STARSTEEL_BLOCK_ITEM;
    private static final Item INVERTER_ITEM = Items.FERMENTED_SPIDER_EYE;

    public static void build() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            // Glowing Potion
            builder.registerRecipes(Items.GLOW_INK_SAC, JAAVAAPotions.GLOWING_1_POTION);
            builder.registerRecipes(Items.GLOW_BERRIES, JAAVAAPotions.GLOWING_1_POTION);
            // Lengthen the glowing potion
            builder.registerPotionRecipe(JAAVAAPotions.GLOWING_1_POTION, LENGTH_UPGRADE, JAAVAAPotions.GLOWING_1_POTION_LONG);
            // Eternal Glowing Potion
            builder.registerPotionRecipe(JAAVAAPotions.GLOWING_1_POTION_LONG, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_GLOWING_POTION);

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
            // Eternal Haste Potion
            builder.registerPotionRecipe(JAAVAAPotions.HASTE_5_POTION_LONG, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_HASTE_POTION);

            // Mining Fatigue Potion
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
            builder.registerPotionRecipe(JAAVAAPotions.ETERNAL_HASTE_POTION, INVERTER_ITEM, JAAVAAPotions.ETERNAL_MINING_FATIGUE_POTION);
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
            // Eternal Mining Fatigue Potion
            builder.registerPotionRecipe(JAAVAAPotions.MINING_FATIGUE_5_POTION_LONG, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_MINING_FATIGUE_POTION);

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
            // Eternal Resistance Potion
            builder.registerPotionRecipe(JAAVAAPotions.RESISTANCE_5_POTION_LONG, ETERNAL_UPGRADE, JAAVAAPotions.ETERNAL_RESISTANCE_POTION);
        });
    }
}
