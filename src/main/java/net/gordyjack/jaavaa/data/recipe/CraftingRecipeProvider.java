package net.gordyjack.jaavaa.data.recipe;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.*;
import net.minecraft.registry.*;

import java.util.concurrent.*;

public class CraftingRecipeProvider extends FabricRecipeProvider {
    public CraftingRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryEntryLookup<Item> registryLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAABlocks.ALLOY_FURNACE)
                        .input('I', Items.IRON_INGOT)
                        .input('B', Items.BLAST_FURNACE)
                        .input('N', Items.NETHERITE_INGOT)
                        .input('D', Items.POLISHED_DEEPSLATE)
                        .pattern("IDI")
                        .pattern("DND")
                        .pattern("IBI")
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .criterion(hasItem(Items.BLAST_FURNACE), conditionsFromItem(Items.BLAST_FURNACE))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(registryLookup, RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, 2)
                        .input('I', Items.NETHERITE_INGOT)
                        .input('S', Items.NETHER_STAR)
                        .pattern(" I ")
                        .pattern("ISI")
                        .pattern(" I ")
                        .group("starsteel_ingot")
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .offerTo(exporter);
                offerReversibleCompactingRecipesWithCompactingRecipeGroup(RecipeCategory.MISC, JAAVAAItems.STARSTEEL_NUGGET,
                        RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, "starsteel_ingot_from_nugget", "starsteel_ingot");
                offerReversibleCompactingRecipesWithReverseRecipeGroup(RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT,
                        RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.STARSTEEL_BLOCK, "starsteel_ingot_from_block", "starsteel_ingot");
            }
        };
    }
    @Override
    public String getName() {
        return "crafting_recipe_provider";
    }
}
