package net.gordyjack.jaavaa.data.recipe;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
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
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAAItems.ADDER_ITEM)
                        .input('R', Items.REDSTONE)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('C', Items.COMPARATOR)
                        .pattern(" R ")
                        .pattern("RCR")
                        .pattern(" D ")
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAAItems.ADVANCED_REPEATER_ITEM)
                        .input('R', Items.REDSTONE)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('G', Items.REPEATER)
                        .pattern(" R ")
                        .pattern("RGR")
                        .pattern(" D ")
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAABlocks.ALLOY_FURNACE)
                        .input('I', Items.IRON_INGOT)
                        .input('B', Items.BLAST_FURNACE)
                        .input('N', Items.NETHERITE_INGOT)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .pattern("IDI")
                        .pattern("DND")
                        .pattern("IBI")
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .criterion(hasItem(Items.BLAST_FURNACE), conditionsFromItem(Items.BLAST_FURNACE))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAAItems.DECODER_ITEM)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('C', Items.COMPARATOR)
                        .pattern("RTR")
                        .pattern("TCT")
                        .pattern("RDR")
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                        .input('G', Items.ENCHANTED_GOLDEN_APPLE)
                        .input('S', JAAVAAItems.STARSTEEL_INGOT)
                        .input('D', Items.DRAGON_BREATH)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .input('P', JAAVAAItems.SHULKER_PEARL)
                        .input('W', Items.WITHER_ROSE)
                        .pattern("DSP")
                        .pattern("SGS")
                        .pattern("ASW")
                        .group("jaavaa:malum_stellae_incantatae")
                        .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE), conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .criterion(hasItem(Items.DRAGON_BREATH), conditionsFromItem(Items.DRAGON_BREATH))
                        .criterion(hasItem(JAAVAAItems.ALLAY_ESSENCE), conditionsFromItem(JAAVAAItems.ALLAY_ESSENCE))
                        .criterion(hasItem(JAAVAAItems.SHULKER_PEARL), conditionsFromItem(JAAVAAItems.SHULKER_PEARL))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, 2)
                        .input('I', Items.NETHERITE_INGOT)
                        .input('S', Items.NETHER_STAR)
                        .pattern(" I ")
                        .pattern("ISI")
                        .pattern(" I ")
                        .group("jaavaa:starsteel_ingot")
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .offerTo(exporter);
                offerReversibleCompactingRecipesWithCompactingRecipeGroup(RecipeCategory.MISC, JAAVAAItems.STARSTEEL_NUGGET,
                        RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, "starsteel_ingot_from_nugget", "jaavaa:starsteel_ingot");
                offerReversibleCompactingRecipesWithReverseRecipeGroup(RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT,
                        RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.STARSTEEL_BLOCK, "starsteel_ingot_from_block", "jaavaa:starsteel_ingot");
                ArrayList<ItemConvertible> polishedDeepslate = new ArrayList<>();
                polishedDeepslate.add(Items.POLISHED_DEEPSLATE);
                offerSmelting(polishedDeepslate, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE,
                        0.1F, 200, "jaavaa:smooth_polished_deepslate");
            }
        };
    }
    @Override
    public String getName() {
        return "crafting_recipe_provider";
    }
}
