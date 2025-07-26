package net.gordyjack.jaavaa.data.recipe;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.recipe.*;
import net.minecraft.block.*;
import net.minecraft.data.recipe.*;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.structure.*;

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
                this.createAlloyingRecipes();
                this.createArchitectsCompassRecipes();
                this.createBiomeCompassRecipes();
                this.createEquipmentSetRecipes();
                this.createMiscRecipes();
                this.createRecyclingRecipes();
                this.createHammerRecipes();
                this.createMagnetRecipes();
                this.createShapedMaterialsRecipes();
                this.createMobNetRecipes();
                this.createSmithingRecipes();
                this.createToolsetRecipes();
                this.offerEncasedPillarRecipes(Items.QUARTZ_PILLAR, Items.REDSTONE_BLOCK, JAAVAABlocks.QUARTZ_ENCASED_REDSTONE_PILLAR);
                this.offerEncasedPillarRecipes(Items.ANCIENT_DEBRIS, Items.REDSTONE_BLOCK, JAAVAABlocks.ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR);
                for (Blocktant block : JAAVAABlocks.BLOCKTANTS.keySet()) {
                    this.offerBlocktantRecipe(block, JAAVAABlocks.BLOCKTANTS.get(block));
                }
                this.offerSmelting(
                        Items.POLISHED_DEEPSLATE, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE,
                        0.1F, 200, JAAVAA.idFromItem(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE).toString());
            }

            private void createAdvancedGateRecipes() {
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.ADDER)
                        .input('R', Items.REDSTONE)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('G', JAAVAABlocks.LOGICAL_OR_GATE)
                        .input('Q', JAAVAAItems.QUICKSILVER_INGOT)
                        .pattern(" R ")
                        .pattern("RGR")
                        .pattern("DQD")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.ADDER).toString())
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .criterion(hasItem(JAAVAAItems.QUICKSILVER_INGOT), conditionsFromItem(JAAVAAItems.QUICKSILVER_INGOT))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.ADVANCED_REPEATER)
                        .input('R', Items.REDSTONE)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('G', Items.REPEATER)
                        .input('Q', JAAVAAItems.QUICKSILVER_INGOT)
                        .pattern(" R ")
                        .pattern("RGR")
                        .pattern("DQD")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.ADVANCED_REPEATER).toString())
                        .criterion(hasItem(Items.REPEATER), conditionsFromItem(Items.REPEATER))
                        .criterion(hasItem(JAAVAAItems.QUICKSILVER_INGOT), conditionsFromItem(JAAVAAItems.QUICKSILVER_INGOT))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.DECODER)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('C', Items.COMPARATOR)
                        .input('Q', JAAVAAItems.QUICKSILVER_INGOT)
                        .pattern(" T ")
                        .pattern("TCT")
                        .pattern("DQD")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.DECODER).toString())
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .criterion(hasItem(JAAVAAItems.QUICKSILVER_INGOT), conditionsFromItem(JAAVAAItems.QUICKSILVER_INGOT))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.LOGICAL_AND_GATE)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('S', Items.STONE)
                        .pattern(" T ")
                        .pattern("TRT")
                        .pattern("SSS")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.LOGICAL_AND_GATE).toString())
                        .criterion(hasItem(Items.REDSTONE_TORCH), conditionsFromItem(Items.REDSTONE_TORCH))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.LOGICAL_OR_GATE)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('S', Items.STONE)
                        .pattern("RTR")
                        .pattern("SSS")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.LOGICAL_OR_GATE).toString())
                        .criterion(hasItem(Items.REDSTONE_TORCH), conditionsFromItem(Items.REDSTONE_TORCH))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.LOGICAL_XOR_GATE)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('S', Items.STONE)
                        .pattern("RTR")
                        .pattern("TTT")
                        .pattern("SSS")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.LOGICAL_XOR_GATE).toString())
                        .criterion(hasItem(Items.REDSTONE_TORCH), conditionsFromItem(Items.REDSTONE_TORCH))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.RANDOMIZER)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('L', JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP)
                        .input('Q', JAAVAAItems.QUICKSILVER_INGOT)
                        .pattern("TRL")
                        .pattern("DQD")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.RANDOMIZER).toString())
                        .criterion(hasItem(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP), conditionsFromItem(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP))
                        .criterion(hasItem(JAAVAAItems.QUICKSILVER_INGOT), conditionsFromItem(JAAVAAItems.QUICKSILVER_INGOT))
                        .offerTo(this.exporter, recipeKeyOf("randomizer_1"));
                this.createShaped(RecipeCategory.REDSTONE, JAAVAABlocks.RANDOMIZER)
                        .input('R', Items.REDSTONE)
                        .input('T', Items.REDSTONE_TORCH)
                        .input('D', JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                        .input('L', JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP)
                        .input('Q', JAAVAAItems.QUICKSILVER_INGOT)
                        .pattern("LRT")
                        .pattern("DQD")
                        .group(JAAVAA.idFromItem(JAAVAABlocks.RANDOMIZER).toString())
                        .criterion(hasItem(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP), conditionsFromItem(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP))
                        .criterion(hasItem(JAAVAAItems.QUICKSILVER_INGOT), conditionsFromItem(JAAVAAItems.QUICKSILVER_INGOT))
                        .offerTo(this.exporter, recipeKeyOf("randomizer_2"));
            }
            private void createAlloyingRecipes() {
                //Vanilla alts
                this.offerAlloyingRecipe(200, 0.7f, Items.NETHERITE_SCRAP, 2, Items.GOLD_INGOT, 1, Items.NETHERITE_INGOT, 1);
                this.offerAlloyingRecipe(150, 0.5f, Items.SAND, 1, Items.AMETHYST_SHARD, 2, Items.TINTED_GLASS, 2);

                //Alloys
                this.offerAlloyingRecipe(200, 0.9f, Items.GOLD_INGOT, 1, Items.IRON_INGOT, 1, JAAVAAItems.AURON_INGOT, 2);
                this.offerAlloyingRecipe(1800, 8.1f, Blocks.GOLD_BLOCK, 1, Blocks.IRON_BLOCK, 1, JAAVAABlocks.AURON_BLOCK, 2);
                this.offerAlloyingRecipe(200, 1.0f, Items.BLAZE_ROD, 1, Items.BREEZE_ROD, 1, JAAVAAItems.FUSED_ROD, 1);
                this.offerAlloyingRecipe(200, 0.9f, Items.COPPER_INGOT, 1, Items.GOLD_INGOT, 1, JAAVAAItems.CUPAUREUM_INGOT, 2);
                this.offerAlloyingRecipe(1800, 8.1f, Blocks.COPPER_BLOCK, 1, Blocks.GOLD_BLOCK, 1, JAAVAABlocks.CUPAUREUM_BLOCK, 2);
                this.offerAlloyingRecipe(200, 0.9f, Items.COPPER_INGOT, 1, Items.IRON_INGOT, 1, JAAVAAItems.CUPERUM_INGOT, 2);
                this.offerAlloyingRecipe(1800, 8.1f, Blocks.COPPER_BLOCK, 1, Blocks.IRON_BLOCK, 1, JAAVAABlocks.CUPERUM_BLOCK, 2);
                this.offerAlloyingRecipe(1800, 9.0f, Items.NETHER_STAR, 1, Items.NETHERITE_BLOCK, 1, JAAVAABlocks.STARSTEEL_BLOCK, 1);
                this.offerAlloyingRecipe(800, 2.0f, Items.COAL, 1, Items.IRON_INGOT, 3, JAAVAAItems.STEEL_INGOT, 2, "from_coal");
                this.offerAlloyingRecipe(1200, 3.0f, Items.CHARCOAL, 1, Items.IRON_INGOT, 3, JAAVAAItems.STEEL_INGOT, 3, "from_charcoal");
                //Dirty Ore Recipes
                this.offerAlloyingRecipe(300, 0.5f, Items.RAW_COPPER, 1, Blocks.SAND, 1, Items.COPPER_INGOT, 2);
                this.offerAlloyingRecipe(300, 0.5f, Items.RAW_IRON, 1, Blocks.SAND, 1, Items.IRON_INGOT, 2);
                this.offerAlloyingRecipe(300, 0.5f, Items.RAW_GOLD, 1, Blocks.SAND, 1, Items.GOLD_INGOT, 2);
            }
            private void createArchitectsCompassRecipes() {
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.ANCIENT_CITIES, JAAVAATags.Items.ATTUNEABLE_ITEMS_ANCIENT_CITY);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.BASTIONS, JAAVAATags.Items.ATTUNEABLE_ITEMS_BASTION);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.DESERT_PYRAMIDS, Items.CHISELED_SANDSTONE);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.END_CITIES, JAAVAATags.Items.ATTUNEABLE_ITEMS_END_CITY);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.IGLOOS, Items.SNOW_BLOCK);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.JUNGLE_TEMPLES, Items.MOSSY_COBBLESTONE);
                offerArchitectsCompassAttunementRecipe(StructureTags.MINESHAFT, Items.RAIL);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.NETHER_FORTRESSES, Items.NETHER_BRICKS);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.NETHER_FOSSILS, Items.BONE_BLOCK);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.OCEAN_MONUMENTS, JAAVAATags.Items.ATTUNEABLE_ITEMS_OCEAN_MONUMENT);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.PILLAGER_OUTPOSTS, JAAVAATags.Items.ATTUNEABLE_ITEMS_PILLAGER_OUTPOST);
                offerArchitectsCompassAttunementRecipe(StructureTags.RUINED_PORTAL, JAAVAATags.Items.ATTUNEABLE_ITEMS_RUINED_PORTAL);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.RUINS, Items.BRUSH);
                offerArchitectsCompassAttunementRecipe(StructureTags.SHIPWRECK, Items.WATER_BUCKET);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.STRONGHOLDS, Items.ENDER_EYE);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.TRIAL_CHAMBERS, JAAVAATags.Items.ATTUNEABLE_ITEMS_TRIAL_CHAMBER);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.WITCH_HUTS, JAAVAATags.Items.ATTUNEABLE_ITEMS_WITCH_HUT);
                offerArchitectsCompassAttunementRecipe(JAAVAATags.Structures.WOODLAND_MANSIONS, JAAVAATags.Items.ATTUNEABLE_ITEMS_WOODLAND_MANSION);
                offerArchitectsCompassAttunementRecipe(StructureTags.VILLAGE, Items.EMERALD);
            }
            private void createBiomeCompassRecipes() {
                createShaped(RecipeCategory.TOOLS, JAAVAAItems.BIOME_COMPASS)
                        .input('C', Items.COMPASS)
                        .input('S', Items.AMETHYST_SHARD)
                        .input('A', JAAVAAItems.AURON_INGOT)
                        .pattern(" S ")
                        .pattern("ACA")
                        .pattern(" S ")
                        .group(JAAVAA.idFromItem(JAAVAAItems.BIOME_COMPASS).toString())
                        .criterion(hasItem(Items.COMPASS), conditionsFromItem(Items.COMPASS))
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .criterion(hasItem(JAAVAAItems.AURON_INGOT), conditionsFromItem(JAAVAAItems.AURON_INGOT))
                        .offerTo(this.exporter);
                offerBiomeCompassAttunementRecipe(BiomeKeys.BADLANDS, JAAVAATags.Items.ATTUNEABLE_ITEMS_BADLANDS);
                offerBiomeCompassAttunementRecipe(BiomeKeys.BAMBOO_JUNGLE, Items.BAMBOO);
                offerBiomeCompassAttunementRecipe(BiomeKeys.BIRCH_FOREST, JAAVAATags.Items.ATTUNEABLE_ITEMS_BIRCH_FOREST);
                offerBiomeCompassAttunementRecipe(BiomeKeys.CHERRY_GROVE, JAAVAATags.Items.ATTUNEABLE_ITEMS_CHERRY_GROVE);
                offerBiomeCompassAttunementRecipe(BiomeKeys.DARK_FOREST, JAAVAATags.Items.ATTUNEABLE_ITEMS_DARK_FOREST);
                offerBiomeCompassAttunementRecipe(BiomeKeys.DEEP_DARK, JAAVAATags.Items.ATTUNEABLE_ITEMS_DEEP_DARK);
                offerBiomeCompassAttunementRecipe(BiomeKeys.DEEP_FROZEN_OCEAN, Items.BLUE_ICE);
                offerBiomeCompassAttunementRecipe(BiomeKeys.DEEP_OCEAN, Items.KELP);
                offerBiomeCompassAttunementRecipe(BiomeKeys.DESERT, JAAVAATags.Items.ATTUNEABLE_ITEMS_DESERT);
                offerBiomeCompassAttunementRecipe(BiomeKeys.DRIPSTONE_CAVES, JAAVAATags.Items.ATTUNEABLE_ITEMS_DRIPSTONE_CAVES);
                offerBiomeCompassAttunementRecipe(BiomeKeys.FLOWER_FOREST, JAAVAATags.Items.ATTUNEABLE_ITEMS_FLOWER_FOREST);
                offerBiomeCompassAttunementRecipe(BiomeKeys.FROZEN_PEAKS, Items.ICE);
                offerBiomeCompassAttunementRecipe(BiomeKeys.ICE_SPIKES, Items.PACKED_ICE);
                offerBiomeCompassAttunementRecipe(BiomeKeys.JAGGED_PEAKS, Items.GOAT_HORN);
                offerBiomeCompassAttunementRecipe(BiomeKeys.JUNGLE, JAAVAATags.Items.ATTUNEABLE_ITEMS_JUNGLE);
                offerBiomeCompassAttunementRecipe(BiomeKeys.LUSH_CAVES, JAAVAATags.Items.ATTUNEABLE_ITEMS_LUSH_CAVES);
                offerBiomeCompassAttunementRecipe(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, Items.PODZOL);
                offerBiomeCompassAttunementRecipe(BiomeKeys.PALE_GARDEN, JAAVAATags.Items.ATTUNEABLE_ITEMS_PALE_GARDEN);
                offerBiomeCompassAttunementRecipe(BiomeKeys.PLAINS, JAAVAATags.Items.ATTUNEABLE_ITEMS_PLAINS);
                offerBiomeCompassAttunementRecipe(BiomeKeys.MANGROVE_SWAMP, JAAVAATags.Items.ATTUNEABLE_ITEMS_MANGROVE_SWAMP);
                offerBiomeCompassAttunementRecipe(BiomeKeys.MUSHROOM_FIELDS, JAAVAATags.Items.ATTUNEABLE_ITEMS_MUSHROOM_FIELDS);
                offerBiomeCompassAttunementRecipe(BiomeKeys.SAVANNA, JAAVAATags.Items.ATTUNEABLE_ITEMS_SAVANNA);
                offerBiomeCompassAttunementRecipe(BiomeKeys.SNOWY_SLOPES, Items.POWDER_SNOW_BUCKET);
                offerBiomeCompassAttunementRecipe(BiomeKeys.SNOWY_PLAINS, Items.SNOWBALL);
                offerBiomeCompassAttunementRecipe(BiomeKeys.SNOWY_TAIGA, Items.SNOW_BLOCK);
                offerBiomeCompassAttunementRecipe(BiomeKeys.STONY_PEAKS, Items.STONE);
                offerBiomeCompassAttunementRecipe(BiomeKeys.SUNFLOWER_PLAINS, Items.SUNFLOWER);
                offerBiomeCompassAttunementRecipe(BiomeKeys.SWAMP, Items.SLIME_BALL);
                offerBiomeCompassAttunementRecipe(BiomeKeys.TAIGA, JAAVAATags.Items.ATTUNEABLE_ITEMS_TAIGA);
                offerBiomeCompassAttunementRecipe(BiomeKeys.WARM_OCEAN, JAAVAATags.Items.ATTUNEABLE_ITEMS_WARM_OCEAN);
                offerBiomeCompassAttunementRecipe(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, Items.GRAVEL);
            }
            private void createEquipmentSetRecipes() {
                this.offerEquipmentSetRecipes(JAAVAAItems.AURON_INGOT, JAAVAAItems.AURON_HELMET, JAAVAAItems.AURON_CHESTPLATE, JAAVAAItems.AURON_LEGGINGS, JAAVAAItems.AURON_BOOTS);
                this.offerEquipmentSetRecipes(JAAVAAItems.STEEL_INGOT, JAAVAAItems.STEEL_HELMET, JAAVAAItems.STEEL_CHESTPLATE, JAAVAAItems.STEEL_LEGGINGS, JAAVAAItems.STEEL_BOOTS);
            }
            private void createHammerRecipes() {
                offerHammerRecipePair(JAAVAAItems.HAMMER_IRON, Blocks.IRON_BLOCK, "iron");
                offerHammerRecipePair(JAAVAAItems.HAMMER_AURON, JAAVAABlocks.AURON_BLOCK, "auron");
                offerHammerRecipePair(JAAVAAItems.HAMMER_CUPAUREUM, JAAVAABlocks.CUPAUREUM_BLOCK, "cupaureum");
                offerHammerRecipePair(JAAVAAItems.HAMMER_CUPERUM, JAAVAABlocks.CUPERUM_BLOCK, "cuperum");
                offerHammerRecipePair(JAAVAAItems.HAMMER_GOLD, Blocks.GOLD_BLOCK, "gold");
                offerHammerRecipePair(JAAVAAItems.HAMMER_STEEL, JAAVAABlocks.STEEL_BLOCK, "steel");
                offerHammerRecipePair(JAAVAAItems.HAMMER_DIAMOND, Blocks.DIAMOND_BLOCK, "diamond");

                this.offerNetheriteUpgradeRecipe(JAAVAAItems.HAMMER_DIAMOND, RecipeCategory.TOOLS, JAAVAAItems.HAMMER_NETHERITE);
                this.offerRecyclingRecipe(800, JAAVAAItems.HAMMER_NETHERITE, new ItemStack(Items.HEAVY_CORE), new ItemStack(Items.NETHERITE_INGOT));
                this.offerStarsteelUpgradeRecipe(JAAVAAItems.HAMMER_NETHERITE, RecipeCategory.TOOLS, JAAVAAItems.HAMMER_STARSTEEL);
                this.offerRecyclingRecipe(800, JAAVAAItems.HAMMER_STARSTEEL, new ItemStack(Items.HEAVY_CORE), new ItemStack(JAAVAAItems.STARSTEEL_INGOT), new ItemStack(JAAVAAItems.FUSED_ROD));
                //offerDragonsteelUpgradeRecipe
                //recycleDragonsteel
                //offerVoidiumUpgradeRecipe
                this.offerRecyclingRecipe(800, JAAVAAItems.HAMMER_VOIDIUM, new ItemStack(Items.HEAVY_CORE), new ItemStack(JAAVAABlocks.RAW_VOIDIUM), new ItemStack(JAAVAAItems.FUSED_ROD));
            }
            private void createMagnetRecipes() {
                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MAGNET_IRON, 1)
                        .input('I', Items.IRON_INGOT)
                        .input('R', Items.REDSTONE)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .pattern("R R")
                        .pattern("IAI")
                        .pattern("III")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MAGNET_IRON).toString())
                        .criterion(hasItem(JAAVAAItems.ALLAY_ESSENCE), conditionsFromItem(JAAVAAItems.ALLAY_ESSENCE))
                        .offerTo(this.exporter);
                this.offerRecyclingRecipe(JAAVAAItems.MAGNET_IRON, new ItemStack(Items.IRON_INGOT, 5));

                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MAGNET_GOLD, 1)
                        .input('G', Items.GOLD_INGOT)
                        .input('R', Items.REDSTONE)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .input('M', JAAVAAItems.MAGNET_IRON)
                        .pattern("RGA")
                        .pattern("GMG")
                        .pattern("AGR")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MAGNET_GOLD).toString())
                        .criterion(hasItem(JAAVAAItems.MAGNET_IRON), conditionsFromItem(JAAVAAItems.MAGNET_IRON))
                        .offerTo(this.exporter, recipeKeyOf("magnet_golden_1"));
                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MAGNET_GOLD, 1)
                        .input('G', Items.GOLD_INGOT)
                        .input('R', Items.REDSTONE)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .input('M', JAAVAAItems.MAGNET_IRON)
                        .pattern("AGR")
                        .pattern("GMG")
                        .pattern("RGA")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MAGNET_GOLD).toString())
                        .criterion(hasItem(JAAVAAItems.MAGNET_IRON), conditionsFromItem(JAAVAAItems.MAGNET_IRON))
                        .offerTo(this.exporter, recipeKeyOf("magnet_golden_2"));
                this.offerRecyclingRecipe(JAAVAAItems.MAGNET_GOLD, new ItemStack(Items.GOLD_INGOT, 4));

                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MAGNET_DIAMOND, 1)
                        .input('D', Items.DIAMOND)
                        .input('R', Items.REDSTONE)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .input('M', JAAVAAItems.MAGNET_IRON)
                        .pattern("RDA")
                        .pattern("DMD")
                        .pattern("ADR")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MAGNET_DIAMOND).toString())
                        .criterion(hasItem(JAAVAAItems.MAGNET_GOLD), conditionsFromItem(JAAVAAItems.MAGNET_GOLD))
                        .offerTo(this.exporter, recipeKeyOf("magnet_diamond_1"));
                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MAGNET_DIAMOND, 1)
                        .input('D', Items.DIAMOND)
                        .input('R', Items.REDSTONE)
                        .input('A', JAAVAAItems.ALLAY_ESSENCE)
                        .input('M', JAAVAAItems.MAGNET_IRON)
                        .pattern("ADR")
                        .pattern("DMD")
                        .pattern("RDA")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MAGNET_DIAMOND).toString())
                        .criterion(hasItem(JAAVAAItems.MAGNET_GOLD), conditionsFromItem(JAAVAAItems.MAGNET_GOLD))
                        .offerTo(this.exporter, recipeKeyOf("magnet_diamond_2"));
                this.offerRecyclingRecipe(JAAVAAItems.MAGNET_DIAMOND, new ItemStack(Items.DIAMOND, 4));

                this.offerNetheriteUpgradeRecipe(JAAVAAItems.MAGNET_DIAMOND, RecipeCategory.TOOLS, JAAVAAItems.MAGNET_NETHERITE);
                this.offerRecyclingRecipe(JAAVAAItems.MAGNET_NETHERITE, Items.NETHERITE_INGOT);
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
                this.createShaped(RecipeCategory.MISC, JAAVAAItems.HAPPY_GHAST_PACK)
                        .input('G', Blocks.DRIED_GHAST)
                        .input('E', Items.ELYTRA)
                        .input('N', JAAVAAItems.STARSTEEL_NUGGET)
                        .input('I', JAAVAAItems.STARSTEEL_INGOT)
                        .input('S', JAAVAAItems.SHULKER_PEARL)
                        .input('L', Items.LEAD)
                        .pattern("IGI")
                        .pattern("LSL")
                        .pattern("NEN")
                        .group(JAAVAA.idFromItem(JAAVAAItems.HAPPY_GHAST_PACK).toString())
                        .criterion(hasItem(Blocks.DRIED_GHAST), conditionsFromItem(Blocks.DRIED_GHAST))
                        .criterion(hasItem(Items.ELYTRA), conditionsFromItem(Items.ELYTRA))
                        .criterion(hasItem(JAAVAAItems.SHULKER_PEARL), conditionsFromItem(JAAVAAItems.SHULKER_PEARL))
                        .criterion(hasItem(JAAVAAItems.STARSTEEL_INGOT), conditionsFromItem(JAAVAAItems.STARSTEEL_INGOT))
                        .criterion(hasItem(JAAVAAItems.STARSTEEL_NUGGET), conditionsFromItem(JAAVAAItems.STARSTEEL_NUGGET))
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
                this.createShaped(RecipeCategory.MISC, JAAVAABlocks.RECYCLING_TABLE)
                        .input('A', Blocks.ANVIL)
                        .input('G', Blocks.GRINDSTONE)
                        .input('D', Items.DEEPSLATE_BRICKS)
                        .pattern(" G ")
                        .pattern("DAD")
                        .criterion(hasItem(Blocks.ANVIL), conditionsFromItem(Blocks.ANVIL))
                        .criterion(hasItem(Blocks.GRINDSTONE), conditionsFromItem(Blocks.GRINDSTONE))
                        .offerTo(this.exporter);
            }
            private void createMobNetRecipes() {
                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_WOOD, 1)
                        .input('B', Items.BAMBOO)
                        .input('S', Items.STRING)
                        .pattern(" SB")
                        .pattern(" BS")
                        .pattern("B  ")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_WOOD).toString())
                        .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                        .offerTo(this.exporter, recipeKeyOf("bamboo_mob_net_1"));
                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_WOOD, 1)
                        .input('B', Items.BAMBOO)
                        .input('S', Items.STRING)
                        .pattern("BS ")
                        .pattern("SB ")
                        .pattern("  B")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_WOOD).toString())
                        .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                        .offerTo(this.exporter, recipeKeyOf("bamboo_mob_net_2"));
                this.offerRecyclingRecipe(JAAVAAItems.MOB_NET_WOOD, new ItemStack(Items.BAMBOO, 2));

                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_STONE, 1)
                        .input('N', JAAVAAItems.MOB_NET_WOOD)
                        .input('S', Items.STICK)
                        .input('C', ItemTags.STONE_CRAFTING_MATERIALS)
                        .pattern("CSC")
                        .pattern("SNS")
                        .pattern("CSC")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_STONE).toString())
                        .criterion(hasItem(JAAVAAItems.MOB_NET_WOOD), conditionsFromItem(JAAVAAItems.MOB_NET_WOOD))
                        .offerTo(this.exporter);
                this.offerRecyclingRecipe(JAAVAAItems.MOB_NET_STONE, new ItemStack(Items.COBBLESTONE, 4));

                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_GOLD, 1)
                        .input('N', JAAVAAItems.MOB_NET_STONE)
                        .input('S', Items.STICK)
                        .input('G', ItemTags.GOLD_TOOL_MATERIALS)
                        .pattern("GSG")
                        .pattern("SNS")
                        .pattern("GSG")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_GOLD).toString())
                        .criterion(hasItem(JAAVAAItems.MOB_NET_STONE), conditionsFromItem(JAAVAAItems.MOB_NET_STONE))
                        .offerTo(this.exporter);
                this.offerRecyclingRecipe(JAAVAAItems.MOB_NET_GOLD, new ItemStack(Items.GOLD_INGOT, 4));

                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_IRON, 1)
                        .input('N', JAAVAAItems.MOB_NET_STONE)
                        .input('S', Items.STICK)
                        .input('I', ItemTags.IRON_TOOL_MATERIALS)
                        .pattern("ISI")
                        .pattern("SNS")
                        .pattern("ISI")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_IRON).toString())
                        .criterion(hasItem(JAAVAAItems.MOB_NET_STONE), conditionsFromItem(JAAVAAItems.MOB_NET_STONE))
                        .offerTo(this.exporter);
                this.offerRecyclingRecipe(JAAVAAItems.MOB_NET_IRON, new ItemStack(Items.IRON_INGOT, 4));

                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_DIAMOND, 1)
                        .input('N', JAAVAAItems.MOB_NET_IRON)
                        .input('S', Items.STICK)
                        .input('D', ItemTags.DIAMOND_TOOL_MATERIALS)
                        .pattern("DSD")
                        .pattern("SNS")
                        .pattern("DSD")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_DIAMOND).toString())
                        .criterion(hasItem(JAAVAAItems.MOB_NET_IRON), conditionsFromItem(JAAVAAItems.MOB_NET_IRON))
                        .offerTo(this.exporter, recipeKeyOf("diamond_mob_net_from_iron"));
                this.createShaped(RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_DIAMOND, 1)
                        .input('N', JAAVAAItems.MOB_NET_GOLD)
                        .input('S', Items.STICK)
                        .input('D', ItemTags.DIAMOND_TOOL_MATERIALS)
                        .pattern("DSD")
                        .pattern("SNS")
                        .pattern("DSD")
                        .group(JAAVAA.idFromItem(JAAVAAItems.MOB_NET_DIAMOND).toString())
                        .criterion(hasItem(JAAVAAItems.MOB_NET_GOLD), conditionsFromItem(JAAVAAItems.MOB_NET_GOLD))
                        .offerTo(this.exporter, recipeKeyOf("diamond_mob_net_from_gold"));
                this.offerRecyclingRecipe(JAAVAAItems.MOB_NET_DIAMOND, new ItemStack(Items.DIAMOND, 4));

                this.offerNetheriteUpgradeRecipe(JAAVAAItems.MOB_NET_DIAMOND, RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_NETHERITE);
                this.offerRecyclingRecipe(JAAVAAItems.MOB_NET_NETHERITE, Items.NETHERITE_INGOT);
            }
            private void createRecyclingRecipes() {
                //Equipment
                //Wood & Leather
                //Tools
                this.offerRecyclingRecipe(Items.WOODEN_AXE, new ItemStack(Items.STICK, 6));
                this.offerRecyclingRecipe(Items.WOODEN_HOE, new ItemStack(Items.STICK, 4));
                this.offerRecyclingRecipe(Items.WOODEN_PICKAXE, new ItemStack(Items.STICK, 6));
                this.offerRecyclingRecipe(Items.WOODEN_SHOVEL, new ItemStack(Items.STICK, 2));
                this.offerRecyclingRecipe(Items.WOODEN_SWORD, new ItemStack(Items.STICK, 4));
                //Armor
                this.offerRecyclingRecipe(400, Items.LEATHER_HELMET, new ItemStack(Items.LEATHER, 5));
                this.offerRecyclingRecipe(400, Items.LEATHER_CHESTPLATE, new ItemStack(Items.LEATHER, 8));
                this.offerRecyclingRecipe(400, Items.LEATHER_LEGGINGS, new ItemStack(Items.LEATHER, 7));
                this.offerRecyclingRecipe(400, Items.LEATHER_BOOTS, new ItemStack(Items.LEATHER, 4));
                this.offerRecyclingRecipe(600, Items.LEATHER_HORSE_ARMOR, new ItemStack(Items.LEATHER, 7));
                //Stone & Chain
                //Tools
                this.offerRecyclingRecipe(Items.STONE_AXE, new ItemStack(Items.COBBLESTONE, 3));
                this.offerRecyclingRecipe(Items.STONE_HOE, new ItemStack(Items.COBBLESTONE, 2));
                this.offerRecyclingRecipe(Items.STONE_PICKAXE, new ItemStack(Items.COBBLESTONE, 3));
                this.offerRecyclingRecipe(Items.STONE_SHOVEL, Items.COBBLESTONE);
                this.offerRecyclingRecipe(Items.STONE_SWORD, new ItemStack(Items.COBBLESTONE, 2));
                //Armor
                this.offerRecyclingRecipe(400, Items.CHAINMAIL_HELMET, new ItemStack(Items.IRON_NUGGET, 20));
                this.offerRecyclingRecipe(400, Items.CHAINMAIL_CHESTPLATE, new ItemStack(Items.IRON_NUGGET, 32));
                this.offerRecyclingRecipe(400, Items.CHAINMAIL_LEGGINGS, new ItemStack(Items.IRON_NUGGET, 28));
                this.offerRecyclingRecipe(400, Items.CHAINMAIL_BOOTS, new ItemStack(Items.IRON_NUGGET, 16));
                //Iron
                //Tools
                this.offerRecyclingRecipe(Items.IRON_AXE, new ItemStack(Items.IRON_INGOT, 3));
                this.offerRecyclingRecipe(Items.IRON_HOE, new ItemStack(Items.IRON_INGOT, 2));
                this.offerRecyclingRecipe(Items.IRON_PICKAXE, new ItemStack(Items.IRON_INGOT, 3));
                this.offerRecyclingRecipe(Items.IRON_SHOVEL, Items.IRON_INGOT);
                this.offerRecyclingRecipe(Items.IRON_SWORD, new ItemStack(Items.IRON_INGOT, 2));
                //Armor
                this.offerRecyclingRecipe(400, Items.IRON_HELMET, new ItemStack(Items.IRON_INGOT, 5));
                this.offerRecyclingRecipe(400, Items.IRON_CHESTPLATE, new ItemStack(Items.IRON_INGOT, 8));
                this.offerRecyclingRecipe(400, Items.IRON_LEGGINGS, new ItemStack(Items.IRON_INGOT, 7));
                this.offerRecyclingRecipe(400, Items.IRON_BOOTS, new ItemStack(Items.IRON_INGOT, 4));
                this.offerRecyclingRecipe(600, Items.IRON_HORSE_ARMOR, new ItemStack(Items.IRON_INGOT, 7));
                //Gold
                //Tools
                this.offerRecyclingRecipe(Items.GOLDEN_AXE, new ItemStack(Items.GOLD_INGOT, 3));
                this.offerRecyclingRecipe(Items.GOLDEN_HOE, new ItemStack(Items.GOLD_INGOT, 2));
                this.offerRecyclingRecipe(Items.GOLDEN_PICKAXE, new ItemStack(Items.GOLD_INGOT, 3));
                this.offerRecyclingRecipe(Items.GOLDEN_SHOVEL, Items.GOLD_INGOT);
                this.offerRecyclingRecipe(Items.GOLDEN_SWORD, new ItemStack(Items.GOLD_INGOT, 2));
                //Armor
                this.offerRecyclingRecipe(400, Items.GOLDEN_HELMET, new ItemStack(Items.GOLD_INGOT, 5));
                this.offerRecyclingRecipe(400, Items.GOLDEN_CHESTPLATE, new ItemStack(Items.GOLD_INGOT, 8));
                this.offerRecyclingRecipe(400, Items.GOLDEN_LEGGINGS, new ItemStack(Items.GOLD_INGOT, 7));
                this.offerRecyclingRecipe(400, Items.GOLDEN_BOOTS, new ItemStack(Items.GOLD_INGOT, 4));
                this.offerRecyclingRecipe(600, Items.GOLDEN_HORSE_ARMOR, new ItemStack(Items.GOLD_INGOT, 7));
                //Diamond
                //Tools
                this.offerRecyclingRecipe(Items.DIAMOND_AXE, new ItemStack(Items.DIAMOND, 3));
                this.offerRecyclingRecipe(Items.DIAMOND_HOE, new ItemStack(Items.DIAMOND, 2));
                this.offerRecyclingRecipe(Items.DIAMOND_PICKAXE, new ItemStack(Items.DIAMOND, 3));
                this.offerRecyclingRecipe(Items.DIAMOND_SHOVEL, Items.DIAMOND);
                this.offerRecyclingRecipe(Items.DIAMOND_SWORD, new ItemStack(Items.DIAMOND, 2));
                //Armor
                this.offerRecyclingRecipe(400, Items.DIAMOND_HELMET, new ItemStack(Items.DIAMOND, 5));
                this.offerRecyclingRecipe(400, Items.DIAMOND_CHESTPLATE, new ItemStack(Items.DIAMOND, 8));
                this.offerRecyclingRecipe(400, Items.DIAMOND_LEGGINGS,new ItemStack(Items.DIAMOND, 7));
                this.offerRecyclingRecipe(400, Items.DIAMOND_BOOTS, new ItemStack(Items.DIAMOND, 4));
                this.offerRecyclingRecipe(600, Items.DIAMOND_HORSE_ARMOR, new ItemStack(Items.DIAMOND, 7));
                //Netherite
                final ItemStack netheriteIngot = new ItemStack(Items.NETHERITE_INGOT);
                //Tools
                this.offerRecyclingRecipe(300, Items.NETHERITE_AXE, netheriteIngot, new ItemStack(Items.DIAMOND, 3));
                this.offerRecyclingRecipe(300, Items.NETHERITE_HOE, netheriteIngot, new ItemStack(Items.DIAMOND, 2));
                this.offerRecyclingRecipe(300, Items.NETHERITE_PICKAXE, netheriteIngot, new ItemStack(Items.DIAMOND, 3));
                this.offerRecyclingRecipe(300, Items.NETHERITE_SHOVEL, netheriteIngot, new ItemStack(Items.DIAMOND));
                this.offerRecyclingRecipe(300, Items.NETHERITE_SWORD, netheriteIngot, new ItemStack(Items.DIAMOND, 2));
                this.offerRecyclingRecipe(300, JAAVAAItems.TOOL_OF_THE_ANCIENTS, new ItemStack(Items.NETHERITE_INGOT, 2));
                //Armor
                this.offerRecyclingRecipe(400, Items.NETHERITE_HELMET, netheriteIngot, new ItemStack(Items.DIAMOND, 5));
                this.offerRecyclingRecipe(400, Items.NETHERITE_CHESTPLATE, netheriteIngot, new ItemStack(Items.DIAMOND, 8));
                this.offerRecyclingRecipe(400, Items.NETHERITE_LEGGINGS, netheriteIngot, new ItemStack(Items.DIAMOND, 7));
                this.offerRecyclingRecipe(400, Items.NETHERITE_BOOTS, netheriteIngot, new ItemStack(Items.DIAMOND, 4));
                //Starsteel
                //Tools
                this.offerRecyclingRecipe(300, JAAVAAItems.STARSTEEL_SWORD, new ItemStack(JAAVAAItems.STARSTEEL_INGOT), new ItemStack(Items.NETHERITE_SWORD));
                this.offerRecyclingRecipe(300, JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL, new ItemStack(JAAVAAItems.STARSTEEL_INGOT), new ItemStack(JAAVAAItems.TOOL_OF_THE_ANCIENTS));
                this.offerRecyclingRecipe(300, JAAVAAItems.STARSTEEL_UPGRADE_SMITHING_TEMPLATE, JAAVAAItems.STARSTEEL_NUGGET);
                //Misc
                this.offerRecyclingRecipe(Items.CROSSBOW, new ItemStack(Items.IRON_INGOT, 3));
                this.offerRecyclingRecipe(Items.ELYTRA, new ItemStack(Items.PHANTOM_MEMBRANE, 2));
                this.offerRecyclingRecipe(400, Items.MACE, Items.HEAVY_CORE);
                this.offerRecyclingRecipe(Items.SHIELD, Items.IRON_INGOT);
                this.offerRecyclingRecipe(Items.TRIDENT, new ItemStack(Items.PRISMARINE_SHARD, 3));
                this.offerRecyclingRecipe(Items.WARPED_FUNGUS_ON_A_STICK, Items.WARPED_FUNGUS);

                //Blocks
                //Dye Stacks
                final ItemStack black = new ItemStack(Items.BLACK_DYE);
                final ItemStack blue = new ItemStack(Items.BLUE_DYE);
                final ItemStack brown = new ItemStack(Items.BROWN_DYE);
                final ItemStack cyan = new ItemStack(Items.CYAN_DYE);
                final ItemStack gray = new ItemStack(Items.GRAY_DYE);
                final ItemStack green = new ItemStack(Items.GREEN_DYE);
                final ItemStack lightBlue = new ItemStack(Items.LIGHT_BLUE_DYE);
                final ItemStack lightGray = new ItemStack(Items.LIGHT_GRAY_DYE);
                final ItemStack lime = new ItemStack(Items.LIME_DYE);
                final ItemStack magenta = new ItemStack(Items.MAGENTA_DYE);
                final ItemStack orange = new ItemStack(Items.ORANGE_DYE);
                final ItemStack pink = new ItemStack(Items.PINK_DYE);
                final ItemStack purple = new ItemStack(Items.PURPLE_DYE);
                final ItemStack red = new ItemStack(Items.RED_DYE);
                final ItemStack white = new ItemStack(Items.WHITE_DYE);
                final ItemStack yellow = new ItemStack(Items.YELLOW_DYE);
                //Shulkers
                final ItemStack shulkerShells = new ItemStack(Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(300, Items.SHULKER_BOX, shulkerShells);
                this.offerRecyclingRecipe(300, Items.BLACK_SHULKER_BOX, shulkerShells, black);
                this.offerRecyclingRecipe(300, Items.BLUE_SHULKER_BOX, shulkerShells, blue);
                this.offerRecyclingRecipe(300, Items.BROWN_SHULKER_BOX, shulkerShells, brown);
                this.offerRecyclingRecipe(300, Items.CYAN_SHULKER_BOX, shulkerShells, cyan);
                this.offerRecyclingRecipe(300, Items.GRAY_SHULKER_BOX, shulkerShells, gray);
                this.offerRecyclingRecipe(300, Items.GREEN_SHULKER_BOX, shulkerShells, green);
                this.offerRecyclingRecipe(300, Items.LIGHT_BLUE_SHULKER_BOX, shulkerShells, lightBlue);
                this.offerRecyclingRecipe(300, Items.LIGHT_GRAY_SHULKER_BOX, shulkerShells, lightGray);
                this.offerRecyclingRecipe(300, Items.LIME_SHULKER_BOX, shulkerShells, lime);
                this.offerRecyclingRecipe(300, Items.MAGENTA_SHULKER_BOX, shulkerShells, magenta);
                this.offerRecyclingRecipe(300, Items.ORANGE_SHULKER_BOX, shulkerShells, orange);
                this.offerRecyclingRecipe(300, Items.PINK_SHULKER_BOX, shulkerShells, pink);
                this.offerRecyclingRecipe(300, Items.PURPLE_SHULKER_BOX, shulkerShells, purple);
                this.offerRecyclingRecipe(300, Items.RED_SHULKER_BOX, shulkerShells, red);
                this.offerRecyclingRecipe(300, Items.WHITE_SHULKER_BOX, shulkerShells, white);
                this.offerRecyclingRecipe(300, Items.YELLOW_SHULKER_BOX, shulkerShells, yellow);
                //Wool
                final ItemStack woolStrings = new ItemStack(Items.STRING, 4);
                this.offerRecyclingRecipe(Items.BLACK_WOOL, woolStrings, black);
                this.offerRecyclingRecipe(Items.BLUE_WOOL, woolStrings, blue);
                this.offerRecyclingRecipe(Items.BROWN_WOOL, woolStrings, brown);
                this.offerRecyclingRecipe(Items.CYAN_WOOL, woolStrings, cyan);
                this.offerRecyclingRecipe(Items.GRAY_WOOL, woolStrings, gray);
                this.offerRecyclingRecipe(Items.GREEN_WOOL, woolStrings, green);
                this.offerRecyclingRecipe(Items.LIGHT_BLUE_WOOL, woolStrings, lightBlue);
                this.offerRecyclingRecipe(Items.LIGHT_GRAY_WOOL, woolStrings, lightGray);
                this.offerRecyclingRecipe(Items.LIME_WOOL, woolStrings, lime);
                this.offerRecyclingRecipe(Items.MAGENTA_WOOL, woolStrings, magenta);
                this.offerRecyclingRecipe(Items.ORANGE_WOOL, woolStrings, orange);
                this.offerRecyclingRecipe(Items.PINK_WOOL, woolStrings, pink);
                this.offerRecyclingRecipe(Items.PURPLE_WOOL, woolStrings, purple);
                this.offerRecyclingRecipe(Items.RED_WOOL, woolStrings, red);
                this.offerRecyclingRecipe(Items.WHITE_WOOL, woolStrings, white);
                this.offerRecyclingRecipe(Items.YELLOW_WOOL, woolStrings, yellow);
                //Concrete
                this.offerRecyclingRecipe(Items.BLACK_CONCRETE, Items.BLACK_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.BLUE_CONCRETE, Items.BLUE_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.BROWN_CONCRETE, Items.BROWN_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.CYAN_CONCRETE, Items.CYAN_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.GRAY_CONCRETE, Items.GRAY_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.GREEN_CONCRETE, Items.GREEN_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.LIME_CONCRETE, Items.LIME_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.ORANGE_CONCRETE, Items.ORANGE_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.PINK_CONCRETE, Items.PINK_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.PURPLE_CONCRETE, Items.PURPLE_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.RED_CONCRETE, Items.RED_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.WHITE_CONCRETE, Items.WHITE_CONCRETE_POWDER);
                this.offerRecyclingRecipe(Items.YELLOW_CONCRETE, Items.YELLOW_CONCRETE_POWDER);
                //Concrete Powder
                ItemStack concretePowderGravels = new ItemStack(Items.GRAVEL, 4);
                ItemStack concretePowderSand = new ItemStack(Items.SAND, 4);
                this.offerRecyclingRecipe(800, new ItemStack(Items.BLACK_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, black);
                this.offerRecyclingRecipe(800, new ItemStack(Items.BLUE_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, blue);
                this.offerRecyclingRecipe(800, new ItemStack(Items.BROWN_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, brown);
                this.offerRecyclingRecipe(800, new ItemStack(Items.CYAN_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, cyan);
                this.offerRecyclingRecipe(800, new ItemStack(Items.GRAY_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, gray);
                this.offerRecyclingRecipe(800, new ItemStack(Items.GREEN_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, green);
                this.offerRecyclingRecipe(800, new ItemStack(Items.LIGHT_BLUE_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, lightBlue);
                this.offerRecyclingRecipe(800, new ItemStack(Items.LIGHT_GRAY_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, lightGray);
                this.offerRecyclingRecipe(800, new ItemStack(Items.LIME_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, lime);
                this.offerRecyclingRecipe(800, new ItemStack(Items.MAGENTA_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, magenta);
                this.offerRecyclingRecipe(800, new ItemStack(Items.ORANGE_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, orange);
                this.offerRecyclingRecipe(800, new ItemStack(Items.PINK_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, pink);
                this.offerRecyclingRecipe(800, new ItemStack(Items.PURPLE_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, purple);
                this.offerRecyclingRecipe(800, new ItemStack(Items.RED_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, red);
                this.offerRecyclingRecipe(800, new ItemStack(Items.WHITE_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, white);
                this.offerRecyclingRecipe(800, new ItemStack(Items.YELLOW_CONCRETE_POWDER, 8), concretePowderGravels, concretePowderSand, yellow);
                //Terracotta
                final ItemStack terracottaBricks = new ItemStack(Items.BRICK, 8);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.BLACK_TERRACOTTA, 8), terracottaBricks, black);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.BLACK_GLAZED_TERRACOTTA, 8), terracottaBricks, black);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.BLUE_TERRACOTTA, 8), terracottaBricks, blue);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.BLUE_GLAZED_TERRACOTTA, 8), terracottaBricks, blue);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.BROWN_TERRACOTTA, 8), terracottaBricks, brown);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.BROWN_GLAZED_TERRACOTTA, 8), terracottaBricks, brown);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.CYAN_TERRACOTTA, 8), terracottaBricks, cyan);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.CYAN_GLAZED_TERRACOTTA, 8), terracottaBricks, cyan);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.GRAY_TERRACOTTA, 8), terracottaBricks, gray);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.GRAY_GLAZED_TERRACOTTA, 8), terracottaBricks, gray);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.GREEN_TERRACOTTA, 8), terracottaBricks, green);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.GREEN_GLAZED_TERRACOTTA, 8), terracottaBricks, green);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.LIGHT_BLUE_TERRACOTTA, 8), terracottaBricks, lightBlue);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.LIGHT_BLUE_GLAZED_TERRACOTTA, 8), terracottaBricks, lightBlue);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.LIGHT_GRAY_TERRACOTTA, 8), terracottaBricks, lightGray);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.LIGHT_GRAY_GLAZED_TERRACOTTA, 8), terracottaBricks, lightGray);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.LIME_TERRACOTTA, 8), terracottaBricks, lime);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.LIME_GLAZED_TERRACOTTA, 8), terracottaBricks, lime);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.MAGENTA_TERRACOTTA, 8), terracottaBricks, magenta);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.MAGENTA_GLAZED_TERRACOTTA, 8), terracottaBricks, magenta);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.ORANGE_TERRACOTTA, 8), terracottaBricks, orange);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.ORANGE_GLAZED_TERRACOTTA, 8), terracottaBricks, orange);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.PINK_TERRACOTTA, 8), terracottaBricks, pink);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.PINK_GLAZED_TERRACOTTA, 8), terracottaBricks, pink);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.PURPLE_TERRACOTTA, 8), terracottaBricks, purple);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.PURPLE_GLAZED_TERRACOTTA, 8), terracottaBricks, purple);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.RED_TERRACOTTA, 8), terracottaBricks, red);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.RED_GLAZED_TERRACOTTA, 8), terracottaBricks, red);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.WHITE_TERRACOTTA, 8), terracottaBricks, white);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.WHITE_GLAZED_TERRACOTTA, 8), terracottaBricks, white);
                this.offerRecyclingRecipe(1200, new ItemStack(Items.YELLOW_TERRACOTTA, 8), terracottaBricks, yellow);
                this.offerRecyclingRecipe(1600, new ItemStack(Items.YELLOW_GLAZED_TERRACOTTA, 8), terracottaBricks, yellow);
                //Stained Glass
                final ItemStack stainedGlassSands = new ItemStack(Items.SAND, 8);
                this.offerRecyclingRecipe(800, new ItemStack(Items.BLACK_STAINED_GLASS, 8), stainedGlassSands, black);
                this.offerRecyclingRecipe(800, new ItemStack(Items.BLUE_STAINED_GLASS, 8), stainedGlassSands, blue);
                this.offerRecyclingRecipe(800, new ItemStack(Items.BROWN_STAINED_GLASS, 8), stainedGlassSands, brown);
                this.offerRecyclingRecipe(800, new ItemStack(Items.CYAN_STAINED_GLASS, 8), stainedGlassSands, cyan);
                this.offerRecyclingRecipe(800, new ItemStack(Items.GRAY_STAINED_GLASS, 8), stainedGlassSands, gray);
                this.offerRecyclingRecipe(800, new ItemStack(Items.GREEN_STAINED_GLASS, 8), stainedGlassSands, green);
                this.offerRecyclingRecipe(800, new ItemStack(Items.LIGHT_BLUE_STAINED_GLASS, 8), stainedGlassSands, lightBlue);
                this.offerRecyclingRecipe(800, new ItemStack(Items.LIGHT_GRAY_STAINED_GLASS, 8), stainedGlassSands, lightGray);
                this.offerRecyclingRecipe(800, new ItemStack(Items.LIME_STAINED_GLASS, 8), stainedGlassSands, lime);
                this.offerRecyclingRecipe(800, new ItemStack(Items.MAGENTA_STAINED_GLASS, 8), stainedGlassSands, magenta);
                this.offerRecyclingRecipe(800, new ItemStack(Items.ORANGE_STAINED_GLASS, 8), stainedGlassSands, orange);
                this.offerRecyclingRecipe(800, new ItemStack(Items.PINK_STAINED_GLASS, 8), stainedGlassSands, pink);
                this.offerRecyclingRecipe(800, new ItemStack(Items.PURPLE_STAINED_GLASS, 8), stainedGlassSands, purple);
                this.offerRecyclingRecipe(800, new ItemStack(Items.RED_STAINED_GLASS, 8), stainedGlassSands, red);
                this.offerRecyclingRecipe(800, new ItemStack(Items.WHITE_STAINED_GLASS, 8), stainedGlassSands, white);
                this.offerRecyclingRecipe(800, new ItemStack(Items.YELLOW_STAINED_GLASS, 8), stainedGlassSands, yellow);
                //Smithing Templates
                final ItemStack smithingTemplateDiamonds = new ItemStack(Items.DIAMOND, 7);
                this.offerRecyclingRecipe(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                this.offerRecyclingRecipe(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, smithingTemplateDiamonds);
                //Misc
                this.offerRecyclingRecipe(400, new ItemStack(Items.ANCIENT_DEBRIS, 2), new ItemStack(Items.NETHERITE_SCRAP, 3));
                this.offerRecyclingRecipe(Items.NETHERITE_INGOT, new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.NETHERITE_SCRAP, 2));
                this.offerRecyclingRecipe(300, JAAVAABlocks.ALLOY_FURNACE, new ItemStack(Items.BLAST_FURNACE), new ItemStack(Items.IRON_INGOT, 4), new ItemStack(Items.NETHERITE_INGOT));
                this.offerRecyclingRecipe(300, JAAVAABlocks.RECYCLING_TABLE, new ItemStack(Items.GRINDSTONE), new ItemStack(Items.ANVIL));
            }
            private void createShapedMaterialsRecipes() {
                this.createShaped(RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, 4)
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
                        RecipeCategory.MISC, JAAVAAItems.AURON_NUGGET, RecipeCategory.MISC, JAAVAAItems.AURON_INGOT,
                        "auron_ingot_from_nugget", JAAVAA.idFromItem(JAAVAAItems.AURON_INGOT).toString(),
                        "auron_nugget_from_ingot", JAAVAA.idFromItem(JAAVAAItems.AURON_NUGGET).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.AURON_INGOT, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.AURON_BLOCK,
                        "auron_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.AURON_BLOCK).toString(),
                        "auron_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.AURON_INGOT).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.QUICKSILVER_NUGGET, RecipeCategory.MISC, JAAVAAItems.QUICKSILVER_INGOT,
                        "quicksilver_ingot_from_nugget", JAAVAA.idFromItem(JAAVAAItems.QUICKSILVER_INGOT).toString(),
                        "quicksilver_nugget_from_ingot", JAAVAA.idFromItem(JAAVAAItems.QUICKSILVER_NUGGET).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.QUICKSILVER_INGOT, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.QUICKSILVER_BLOCK,
                        "quicksilver_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.QUICKSILVER_BLOCK).toString(),
                        "quicksilver_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.QUICKSILVER_INGOT).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.CUPAUREUM_INGOT, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.CUPAUREUM_BLOCK,
                        "cupaureum_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.CUPAUREUM_BLOCK).toString(),
                        "cupaureum_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.CUPAUREUM_INGOT).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.STARSTEEL_NUGGET, RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT,
                        "starsteel_ingot_from_nugget", JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_INGOT).toString(),
                        "starsteel_nugget_from_ingot", JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_NUGGET).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.STARSTEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.STARSTEEL_BLOCK,
                        "starsteel_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.STARSTEEL_BLOCK).toString(),
                        "starsteel_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.STARSTEEL_INGOT).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.STEEL_NUGGET, RecipeCategory.MISC, JAAVAAItems.STEEL_INGOT,
                        "steel_ingot_from_nugget", JAAVAA.idFromItem(JAAVAAItems.STEEL_INGOT).toString(),
                        "steel_nugget_from_ingot", JAAVAA.idFromItem(JAAVAAItems.STEEL_NUGGET).toString()
                );
                this.offerReversibleCompactingRecipes(
                        RecipeCategory.MISC, JAAVAAItems.STEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.STEEL_BLOCK,
                        "steel_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.STEEL_BLOCK).toString(),
                        "steel_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.STEEL_INGOT).toString()
                );
            }
            private void createSmithingRecipes() {
                this.offerSmithingTemplateCopyingRecipe(JAAVAAItems.STARSTEEL_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT);
                this.offerStarsteelUpgradeRecipe(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, JAAVAAItems.STARSTEEL_SWORD);
                this.offerStarsteelUpgradeRecipe(JAAVAAItems.TOOL_OF_THE_ANCIENTS, RecipeCategory.TOOLS, JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL);
            }
            private void createToolsetRecipes() {
                this.offerToolsetRecipes(JAAVAAItems.AURON_INGOT, JAAVAAItems.AURON_SWORD, JAAVAAItems.AURON_SHOVEL,
                        JAAVAAItems.AURON_PICKAXE, JAAVAAItems.AURON_AXE, JAAVAAItems.AURON_HOE);
                this.offerToolsetRecipes(JAAVAAItems.CUPAUREUM_INGOT, JAAVAAItems.CUPAUREUM_SWORD, JAAVAAItems.CUPAUREUM_SHOVEL,
                        JAAVAAItems.CUPAUREUM_PICKAXE, JAAVAAItems.CUPAUREUM_AXE, JAAVAAItems.CUPAUREUM_HOE);
                this.offerToolsetRecipes(JAAVAAItems.STEEL_INGOT, JAAVAAItems.STEEL_SWORD, JAAVAAItems.STEEL_SHOVEL,
                        JAAVAAItems.STEEL_PICKAXE, JAAVAAItems.STEEL_AXE, JAAVAAItems.STEEL_HOE);
            }
            private void offerAlloyingRecipe(int burnTime, float experience,
                                             ItemConvertible input1, int input1Count,
                                             ItemConvertible input2, int input2Count,
                                             ItemConvertible output, int outputCount) {
               this.offerAlloyingRecipe(burnTime, experience, input1, input1Count, input2, input2Count, output, outputCount, "");
            }
            private void offerAlloyingRecipe(int burnTime, float experience,
                                             ItemConvertible input1, int input1Count,
                                             ItemConvertible input2, int input2Count,
                                             ItemConvertible output, int outputCount,
                                             String suffix) {
                String outputName = JAAVAA.idFromItem(output).getPath();
                var input1Stack = new ItemStack(input1, input1Count);
                var input2Stack = new ItemStack(input2, input2Count);
                var outputStack = new ItemStack(output, outputCount);
                recipeExporter.accept(RegistryKey.of(
                                RegistryKeys.RECIPE, JAAVAA.id("alloying_" + outputName + (!suffix.isEmpty() ? "_" + suffix : ""))),
                        new AlloyingRecipe(burnTime, experience, input1Stack, input2Stack, outputStack),
                        null);
            }
            private void offerArchitectsCompassAttunementRecipe(TagKey<Structure> targetStructure, ItemConvertible attunementItem) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.ARCHITECTS_COMPASS);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET, targetStructure);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.ARCHITECTS_COMPASS)
                        .input(attunementItem)
                        .group(JAAVAA.idFromItem(JAAVAAItems.ARCHITECTS_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.ARCHITECTS_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("architects_compass_attunement_" + targetStructure.id().getPath()));
            }
            private void offerArchitectsCompassAttunementRecipe(TagKey<Structure> targetStructure, TagKey<Item> attunementTag) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.ARCHITECTS_COMPASS);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET, targetStructure);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.ARCHITECTS_COMPASS)
                        .input(attunementTag)
                        .group(JAAVAA.idFromItem(JAAVAAItems.ARCHITECTS_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.ARCHITECTS_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("architects_compass_attunement_" + targetStructure.id().getPath()));
            }
            private void offerBiomeCompassAttunementRecipe(RegistryKey<Biome> targetBiome, ItemConvertible attunementItem) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.BIOME_COMPASS);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_BIOME_TARGET, targetBiome);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.BIOME_COMPASS)
                        .input(attunementItem)
                        .group(JAAVAA.idFromItem(JAAVAAItems.BIOME_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.BIOME_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("biome_compass_attunement_" + targetBiome.getValue().getPath()));
            }
            private void offerBiomeCompassAttunementRecipe(RegistryKey<Biome> targetBiome, TagKey<Item> attunementTag) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.BIOME_COMPASS);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_BIOME_TARGET, targetBiome);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.BIOME_COMPASS)
                        .input(attunementTag)
                        .group(JAAVAA.idFromItem(JAAVAAItems.BIOME_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.BIOME_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("biome_compass_attunement_" + targetBiome.getValue().getPath()));
            }
            private void offerBlocktantRecipe(ItemConvertible blocktant, ItemConvertible parentBlock) {
                for (int i = 1; i <= 8; i++) {
                    this.createShapeless(RecipeCategory.BUILDING_BLOCKS, blocktant, i * 8)
                            .input(parentBlock, i)
                            .input(JAAVAAItems.STARSTEEL_NUGGET)
                            .group(JAAVAA.idFromItem(blocktant).toString())
                            .criterion(hasItem(parentBlock), conditionsFromItem(parentBlock))
                            .offerTo(this.exporter, RegistryKey.of(RegistryKeys.RECIPE, JAAVAA.id(JAAVAA.idFromItem(blocktant).getPath() + "_" + i)));
                }
                this.createShapeless(RecipeCategory.BUILDING_BLOCKS, parentBlock)
                        .input(blocktant, 8)
                        .group(JAAVAA.idFromItem(parentBlock).toString())
                        .criterion(hasItem(blocktant), conditionsFromItem(blocktant))
                        .offerTo(this.exporter, RegistryKey.of(RegistryKeys.RECIPE, JAAVAA.id(JAAVAA.idFromItem(parentBlock).getPath() + "_from_mini_blocks")));
                this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, blocktant, parentBlock, 8);
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
            private void offerEquipmentSetRecipes(ItemConvertible material,
                                                  ItemConvertible helmetItem, ItemConvertible chestplateItem,
                                                  ItemConvertible legItem, ItemConvertible bootItem) {
                this.createShaped(RecipeCategory.COMBAT, helmetItem)
                        .input('#', material)
                        .pattern("###")
                        .pattern("# #")
                        .group(JAAVAA.idFromItem(helmetItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.COMBAT, chestplateItem)
                        .input('#', material)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .group(JAAVAA.idFromItem(chestplateItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.COMBAT, legItem)
                        .input('#', material)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .group(JAAVAA.idFromItem(legItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.COMBAT, bootItem)
                        .input('#', material)
                        .pattern("# #")
                        .pattern("# #")
                        .group(JAAVAA.idFromItem(bootItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);

                this.offerRecyclingRecipe(400, helmetItem, new ItemStack(material, 5));
                this.offerRecyclingRecipe(400, chestplateItem, new ItemStack(material, 8));
                this.offerRecyclingRecipe(400, legItem, new ItemStack(material, 7));
                this.offerRecyclingRecipe(400, bootItem, new ItemStack(material, 4));
            }
            private void offerHammerRecipePair(Item hammer, ItemConvertible head, String material) {
                Item rodItem = material.equals("diamond") ? JAAVAAItems.FUSED_ROD : JAAVAAItems.IRON_ROD;
                this.createShaped(RecipeCategory.TOOLS, hammer)
                        .input('C', Items.HEAVY_CORE)
                        .input('H', head)
                        .input('R', rodItem)
                        .pattern(" HC")
                        .pattern(" RH")
                        .pattern("R  ")
                        .group(JAAVAA.idFromItem(hammer).toString())
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .criterion(hasItem(rodItem), conditionsFromItem(rodItem))
                        .offerTo(this.exporter, recipeKeyOf("hammer_" + material + "_1"));
                this.createShaped(RecipeCategory.TOOLS, hammer)
                        .input('C', Items.HEAVY_CORE)
                        .input('H', head)
                        .input('R', rodItem)
                        .pattern("CH ")
                        .pattern("HR ")
                        .pattern("  R")
                        .group(JAAVAA.idFromItem(hammer).toString())
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .criterion(hasItem(rodItem), conditionsFromItem(rodItem))
                        .offerTo(this.exporter, recipeKeyOf("hammer_" + material + "_2"));

                this.offerRecyclingRecipe(800, hammer, new ItemStack(Items.HEAVY_CORE), new ItemStack(head, 2), new ItemStack(rodItem));
            }
            private void offerRecyclingRecipe(ItemConvertible input, ItemConvertible output) {
                this.offerRecyclingRecipe(input, new ItemStack(output));
            }
            private void offerRecyclingRecipe(ItemConvertible input, ItemStack... outputs) {
                this.offerRecyclingRecipe(200, new ItemStack(input), outputs);
            }
            private void offerRecyclingRecipe(int crushTime, ItemConvertible input, ItemConvertible output) {
                this.offerRecyclingRecipe(crushTime, input, new ItemStack(output));
            }
            private void offerRecyclingRecipe(int crushTime, ItemConvertible input, ItemStack... outputs) {
                this.offerRecyclingRecipe(crushTime, new ItemStack(input), outputs);
            }
            private void offerRecyclingRecipe(int crushTime, ItemStack input, ItemStack... outputs) {
                List<ItemStack> outputList = Arrays.stream(outputs).toList();
                String inputName = JAAVAA.idFromItem(input.getItem()).getPath();
                recipeExporter.accept(RegistryKey.of(
                                RegistryKeys.RECIPE, JAAVAA.id("recycling_" + inputName)),
                        new RecyclingRecipe(crushTime, input, outputList),
                        null);
            }
            private void offerSmelting(ItemConvertible item, RecipeCategory category, ItemConvertible result, float experience, int cookingTime, String group) {
                offerSmelting(List.of(item), category, result, experience, cookingTime, group);
            }
            private void offerStarsteelUpgradeRecipe(Item input, RecipeCategory category, Item result) {
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(JAAVAAItems.STARSTEEL_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(input),
                                this.ingredientFromTag(JAAVAATags.Items.STARSTEEL_MATERIALS),
                                category,
                                result
                        )
                        .criterion("has_starsteel_ingot", this.conditionsFromTag(JAAVAATags.Items.STARSTEEL_MATERIALS))
                        .offerTo(this.exporter, getItemPath(result) + "_smithing");
            }
            private void offerToolsetRecipes(ItemConvertible material, ItemConvertible swordItem, ItemConvertible shovelItem,
                                             ItemConvertible pickaxeItem, ItemConvertible axeItem, ItemConvertible hoeItem) {
                this.createShaped(RecipeCategory.COMBAT, swordItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("M")
                        .pattern("M")
                        .pattern("S")
                        .group(JAAVAA.idFromItem(swordItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.TOOLS, shovelItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("M")
                        .pattern("S")
                        .pattern("S")
                        .group(JAAVAA.idFromItem(shovelItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.TOOLS, pickaxeItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("MMM")
                        .pattern(" S ")
                        .pattern(" S ")
                        .group(JAAVAA.idFromItem(pickaxeItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter);
                this.createShaped(RecipeCategory.TOOLS, axeItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("MM")
                        .pattern("MS")
                        .pattern(" S")
                        .group(JAAVAA.idFromItem(axeItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter, recipeKeyOf(JAAVAA.idFromItem(axeItem).getPath() + "_1"));
                this.createShaped(RecipeCategory.TOOLS, axeItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("MM")
                        .pattern("SM")
                        .pattern("S ")
                        .group(JAAVAA.idFromItem(axeItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter, recipeKeyOf(JAAVAA.idFromItem(axeItem).getPath() + "_2"));
                this.createShaped(RecipeCategory.TOOLS, hoeItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("MM")
                        .pattern(" S")
                        .pattern(" S")
                        .group(JAAVAA.idFromItem(hoeItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter, recipeKeyOf(JAAVAA.idFromItem(hoeItem).getPath() + "_1"));
                this.createShaped(RecipeCategory.TOOLS, hoeItem)
                        .input('M', material)
                        .input('S', Items.STICK)
                        .pattern("MM")
                        .pattern("S ")
                        .pattern("S ")
                        .group(JAAVAA.idFromItem(hoeItem).toString())
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(this.exporter, recipeKeyOf(JAAVAA.idFromItem(hoeItem).getPath() + "_2"));

                this.offerRecyclingRecipe(swordItem, new ItemStack(material, 2));
                this.offerRecyclingRecipe(shovelItem, material);
                this.offerRecyclingRecipe(pickaxeItem, new ItemStack(material, 3));
                this.offerRecyclingRecipe(axeItem, new ItemStack(material, 3));
                this.offerRecyclingRecipe(hoeItem, new ItemStack(material, 2));
            }
        };
    }
    @Override
    public String getName() {
        return "crafting_recipe_provider";
    }
    private static RegistryKey<Recipe<?>> recipeKeyOf(String name) {
        return RegistryKey.of(RegistryKeys.RECIPE, JAAVAA.id(name));
    }
}
