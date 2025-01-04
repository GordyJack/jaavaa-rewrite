package net.gordyjack.jaavaa.data.recipe;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.data.recipe.*;
import net.minecraft.item.*;
import net.minecraft.recipe.book.*;
import net.minecraft.registry.*;

import java.util.*;
import java.util.concurrent.*;

public class JAAVAARecipeProvider extends FabricRecipeProvider {
    public JAAVAARecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryEntryLookup<Item> registryLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);
                this.createAdvancedGateRecipes();
                this.createMaterialsRecipes();
                this.createMiscRecipes();
                this.offerEncasedPillarRecipes(Items.QUARTZ_PILLAR, Items.REDSTONE_BLOCK, JAAVAABlocks.QUARTZ_ENCASED_REDSTONE_PILLAR);
                this.offerEncasedPillarRecipes(Items.ANCIENT_DEBRIS, Items.REDSTONE_BLOCK, JAAVAABlocks.ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR);
                this.offerSmelting(
                        Items.POLISHED_DEEPSLATE, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE,
                        0.1F, 200, JAAVAA.idFromItem(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE).toString());
            }
            private void offerSmelting(ItemConvertible item, RecipeCategory category, ItemConvertible result, float experience, int cookingTime, String group) {
                offerSmelting(List.of(item), category, result, experience, cookingTime, group);
            }
            private void offerEncasedPillarRecipes(ItemConvertible casing, ItemConvertible infill, ItemConvertible output) {
                this.createShaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                        .input('C', casing)
                        .input('I', infill)
                        .pattern("CCC")
                        .pattern("III")
                        .pattern("CCC")
                        .group(JAAVAA.idFromItem(output).toString())
                        .criterion(hasItem(casing), conditionsFromItem(casing))
                        .criterion(hasItem(infill), conditionsFromItem(infill))
                        .offerTo(this.exporter, RegistryKey.of(RegistryKeys.RECIPE, JAAVAA.id(JAAVAA.idFromItem(output).getPath() + "_h")));
                this.createShaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                        .input('C', casing)
                        .input('I', infill)
                        .pattern("CIC")
                        .pattern("CIC")
                        .pattern("CIC")
                        .group(JAAVAA.idFromItem(output).toString())
                        .criterion(hasItem(casing), conditionsFromItem(casing))
                        .criterion(hasItem(infill), conditionsFromItem(infill))
                        .offerTo(this.exporter, RegistryKey.of(RegistryKeys.RECIPE, JAAVAA.id(JAAVAA.idFromItem(output).getPath() + "_v")));
            }

            private void createAdvancedGateRecipes() {
                this.createShaped(RecipeCategory.MISC, JAAVAABlocks.ADDER)
                        .input('R', Items.REDSTONE)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('C', Items.COMPARATOR)
                        .pattern(" R ")
                        .pattern("RCR")
                        .pattern(" D ")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.ADDER).toString())
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.MISC, JAAVAABlocks.ADVANCED_REPEATER)
                        .input('R', Items.REDSTONE)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('G', Items.REPEATER)
                        .pattern(" R ")
                        .pattern("RGR")
                        .pattern(" D ")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.ADVANCED_REPEATER).toString())
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.MISC, JAAVAABlocks.DECODER)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('C', Items.COMPARATOR)
                        .pattern("RTR")
                        .pattern("TCT")
                        .pattern("RDR")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.DECODER).toString())
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(this.exporter);
            }
            private void createMaterialsRecipes() {
                this.createShaped(RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, 2)
                        .input('I', Items.NETHERITE_INGOT)
                        .input('S', Items.NETHER_STAR)
                        .pattern(" I ")
                        .pattern("ISI")
                        .pattern(" I ")
                        .group(JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_INGOT).toString())
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .criterion(hasItem(JAAVAAItems.STARSTEEL_INGOT), conditionsFromItem(JAAVAAItems.STARSTEEL_INGOT))
                        .offerTo(this.exporter, RegistryKey.of(RegistryKeys.RECIPE, JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_INGOT)));
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.STARSTEEL_NUGGET, RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT,
                        "starsteel_ingot_from_nugget", JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_INGOT).toString(),
                        "starsteel_nugget_from_ingot", JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_NUGGET).toString());
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, RecipeCategory.MISC, JAAVAABlocks.STARSTEEL_BLOCK,
                        "starsteel_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.STARSTEEL_BLOCK).toString(),
                        "starsteel_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_INGOT).toString());
            }
            private void createMiscRecipes() {
                this.createShaped(RecipeCategory.MISC, JAAVAABlocks.ALLOY_FURNACE)
                        .input('I', Items.IRON_INGOT)
                        .input('B', Items.BLAST_FURNACE)
                        .input('N', Items.NETHERITE_INGOT)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .pattern("IDI")
                        .pattern("DND")
                        .pattern("IBI")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.ALLOY_FURNACE).toString())
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .criterion(hasItem(Items.BLAST_FURNACE), conditionsFromItem(Items.BLAST_FURNACE))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.MISC, JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                        .input('G', Items.ENCHANTED_GOLDEN_APPLE)
                        .input('S', JAAVAAItems.STARSTEEL_INGOT)
                        .input('D', Items.DRAGON_BREATH)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .input('P', JAAVAAItems.SHULKER_PEARL)
                        .input('W', Items.WITHER_ROSE)
                        .pattern("DSP")
                        .pattern("SGS")
                        .pattern("ASW")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MALUM_STELLAE_INCANTATAE).toString())
                        .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE), conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .criterion(hasItem(Items.DRAGON_BREATH), conditionsFromItem(Items.DRAGON_BREATH))
                        .criterion(hasItem(JAAVAAItems.ALLAY_ESSENCE), conditionsFromItem(JAAVAAItems.ALLAY_ESSENCE))
                        .criterion(hasItem(JAAVAAItems.SHULKER_PEARL), conditionsFromItem(JAAVAAItems.SHULKER_PEARL))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.STARSTEEL_GLASS, 4)
                        .input('G', Items.GLASS)
                        .input('S', JAAVAAItems.STARSTEEL_INGOT)
                        .input('N', JAAVAAItems.STARSTEEL_NUGGET)
                        .pattern("NGN")
                        .pattern("GSG")
                        .pattern("NGN")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.STARSTEEL_GLASS).toString())
                        .criterion(hasItem(JAAVAAItems.STARSTEEL_INGOT), conditionsFromItem(JAAVAAItems.STARSTEEL_INGOT))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.STARSTEEL_GLASS_PANE, 16)
                        .input('G', JAAVAABlocks.STARSTEEL_GLASS)
                        .pattern("GGG")
                        .pattern("GGG")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.STARSTEEL_GLASS_PANE).toString())
                        .criterion(hasItem(JAAVAABlocks.STARSTEEL_GLASS), conditionsFromItem(JAAVAABlocks.STARSTEEL_GLASS))
                        .offerTo(this.exporter);
            }
        };
    }
    @Override
    public String getName() {
        return "crafting_recipe_provider";
    }
}
