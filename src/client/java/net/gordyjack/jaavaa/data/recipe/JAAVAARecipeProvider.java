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
                this.offerAlloyingRecipe(200, 0.9f, Items.COPPER_INGOT, 1, Items.GOLD_INGOT, 1, JAAVAAItems.ROSE_GOLD_INGOT, 2);
                this.offerAlloyingRecipe(1800, 8.1f, Blocks.COPPER_BLOCK, 1, Blocks.GOLD_BLOCK, 1, JAAVAABlocks.ROSE_GOLD_BLOCK, 2);
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
                this.offerEquipmentSetRecipes(JAAVAAItems.STEEL_INGOT, JAAVAAItems.STEEL_HELMET, JAAVAAItems.STEEL_CHESTPLATE, JAAVAAItems.STEEL_LEGGINGS, JAAVAAItems.STEEL_BOOTS);
            }
            private void createHammerRecipes() {
                offerHammerRecipePair(JAAVAAItems.HAMMER_IRON, Blocks.IRON_BLOCK, "iron");
                offerHammerRecipePair(JAAVAAItems.HAMMER_AURON, JAAVAABlocks.AURON_BLOCK, "auron");
                offerHammerRecipePair(JAAVAAItems.HAMMER_ROSE_GOLD, JAAVAABlocks.ROSE_GOLD_BLOCK, "rose_gold");
                offerHammerRecipePair(JAAVAAItems.HAMMER_GOLD, Blocks.GOLD_BLOCK, "gold");
                offerHammerRecipePair(JAAVAAItems.HAMMER_STEEL, JAAVAABlocks.STEEL_BLOCK, "steel");
                offerHammerRecipePair(JAAVAAItems.HAMMER_DIAMOND, Blocks.DIAMOND_BLOCK, "diamond");

                this.offerNetheriteUpgradeRecipe(JAAVAAItems.HAMMER_DIAMOND, RecipeCategory.TOOLS, JAAVAAItems.HAMMER_NETHERITE);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.HAMMER_NETHERITE, Items.HEAVY_CORE, 1);
                this.offerStarsteelUpgradeRecipe(JAAVAAItems.HAMMER_NETHERITE, RecipeCategory.TOOLS, JAAVAAItems.HAMMER_STARSTEEL);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.HAMMER_STARSTEEL, Items.HEAVY_CORE, 1);
                //offerDragonsteelUpgradeRecipe
                //recycleDragonsteel
                //offerVoidiumUpgradeRecipe
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.HAMMER_VOIDIUM, Items.HEAVY_CORE, 1);
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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MAGNET_IRON, Items.IRON_INGOT, 5);

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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MAGNET_GOLD, Items.GOLD_INGOT, 4);

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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MAGNET_DIAMOND, Items.DIAMOND, 4);

                this.offerNetheriteUpgradeRecipe(JAAVAAItems.MAGNET_DIAMOND, RecipeCategory.TOOLS, JAAVAAItems.MAGNET_NETHERITE);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MAGNET_NETHERITE, Items.NETHERITE_INGOT, 1);
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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MOB_NET_WOOD, Items.BAMBOO, 2);

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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MOB_NET_STONE, Items.COBBLESTONE, 4);

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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MOB_NET_GOLD, Items.GOLD_INGOT, 4);

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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MOB_NET_IRON, Items.IRON_INGOT, 4);

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
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MOB_NET_DIAMOND, Items.DIAMOND, 4);

                this.offerNetheriteUpgradeRecipe(JAAVAAItems.MOB_NET_DIAMOND, RecipeCategory.TOOLS, JAAVAAItems.MOB_NET_NETHERITE);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.MOB_NET_NETHERITE, Items.NETHERITE_INGOT, 1);
            }
            private void createRecyclingRecipes() {
                //Equipment
                //Wood & Leather
                //Tools
                this.offerRecyclingRecipe(0.1f, Items.WOODEN_AXE, Items.STICK, 6);
                this.offerRecyclingRecipe(0.1f, Items.WOODEN_HOE, Items.STICK, 4);
                this.offerRecyclingRecipe(0.1f, Items.WOODEN_PICKAXE, Items.STICK, 6);
                this.offerRecyclingRecipe(0.1f, Items.WOODEN_SHOVEL, Items.STICK, 2);
                this.offerRecyclingRecipe(0.1f, Items.WOODEN_SWORD, Items.STICK, 4);
                //Armor
                this.offerRecyclingRecipe(0.1f, Items.LEATHER_HELMET, Items.LEATHER, 5);
                this.offerRecyclingRecipe(0.1f, Items.LEATHER_CHESTPLATE, Items.LEATHER, 8);
                this.offerRecyclingRecipe(0.1f, Items.LEATHER_LEGGINGS, Items.LEATHER, 7);
                this.offerRecyclingRecipe(0.1f, Items.LEATHER_BOOTS, Items.LEATHER, 4);
                this.offerRecyclingRecipe(0.1f, Items.LEATHER_HORSE_ARMOR, Items.LEATHER, 7);
                //Stone & Chain
                //Tools
                this.offerRecyclingRecipe(0.1f, Items.STONE_AXE, Items.COBBLESTONE, 3);
                this.offerRecyclingRecipe(0.1f, Items.STONE_HOE, Items.COBBLESTONE, 2);
                this.offerRecyclingRecipe(0.1f, Items.STONE_PICKAXE, Items.COBBLESTONE, 3);
                this.offerRecyclingRecipe(0.1f, Items.STONE_SHOVEL, Items.COBBLESTONE, 1);
                this.offerRecyclingRecipe(0.1f, Items.STONE_SWORD, Items.COBBLESTONE, 2);
                //Armor
                this.offerRecyclingRecipe(0.1f, Items.CHAINMAIL_HELMET, Items.IRON_NUGGET, 20);
                this.offerRecyclingRecipe(0.1f, Items.CHAINMAIL_CHESTPLATE, Items.IRON_NUGGET, 32);
                this.offerRecyclingRecipe(0.1f, Items.CHAINMAIL_LEGGINGS, Items.IRON_NUGGET, 28);
                this.offerRecyclingRecipe(0.1f, Items.CHAINMAIL_BOOTS, Items.IRON_NUGGET, 16);
                //Iron
                //Tools
                this.offerRecyclingRecipe(0.1f, Items.IRON_AXE, Items.IRON_INGOT, 3);
                this.offerRecyclingRecipe(0.1f, Items.IRON_HOE, Items.IRON_INGOT, 2);
                this.offerRecyclingRecipe(0.1f, Items.IRON_PICKAXE, Items.IRON_INGOT, 3);
                this.offerRecyclingRecipe(0.1f, Items.IRON_SHOVEL, Items.IRON_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.IRON_SWORD, Items.IRON_INGOT, 2);
                //Armor
                this.offerRecyclingRecipe(0.1f, Items.IRON_HELMET, Items.IRON_INGOT, 5);
                this.offerRecyclingRecipe(0.1f, Items.IRON_CHESTPLATE, Items.IRON_INGOT, 8);
                this.offerRecyclingRecipe(0.1f, Items.IRON_LEGGINGS, Items.IRON_INGOT, 7);
                this.offerRecyclingRecipe(0.1f, Items.IRON_BOOTS, Items.IRON_INGOT, 4);
                this.offerRecyclingRecipe(0.1f, Items.IRON_HORSE_ARMOR, Items.IRON_INGOT, 7);
                //Gold
                //Tools
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_AXE, Items.GOLD_INGOT, 3);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_HOE, Items.GOLD_INGOT, 2);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_PICKAXE, Items.GOLD_INGOT, 3);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_SHOVEL, Items.GOLD_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_SWORD, Items.GOLD_INGOT, 2);
                //Armor
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_HELMET, Items.GOLD_INGOT, 5);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_CHESTPLATE, Items.GOLD_INGOT, 8);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_LEGGINGS, Items.GOLD_INGOT, 7);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_BOOTS, Items.GOLD_INGOT, 4);
                this.offerRecyclingRecipe(0.1f, Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT, 7);
                //Diamond
                //Tools
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_AXE, Items.DIAMOND, 3);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_HOE, Items.DIAMOND, 2);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_PICKAXE, Items.DIAMOND, 3);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_SHOVEL, Items.DIAMOND, 1);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_SWORD, Items.DIAMOND, 2);
                //Armor
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_HELMET, Items.DIAMOND, 5);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_CHESTPLATE, Items.DIAMOND, 8);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_LEGGINGS, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_BOOTS, Items.DIAMOND, 4);
                this.offerRecyclingRecipe(0.1f, Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND, 7);
                //Netherite
                //Tools
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_AXE, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_HOE, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_PICKAXE, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_SHOVEL, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_SWORD, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.TOOL_OF_THE_ANCIENTS, Items.NETHERITE_INGOT, 2);
                //Armor
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_HELMET, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_LEGGINGS, Items.NETHERITE_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_BOOTS, Items.NETHERITE_INGOT, 1);
                //Starsteel
                //Tools
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.STARSTEEL_SWORD, JAAVAAItems.STARSTEEL_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL, JAAVAAItems.STARSTEEL_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, JAAVAAItems.STARSTEEL_UPGRADE_SMITHING_TEMPLATE, JAAVAAItems.STARSTEEL_NUGGET, 1);

                //Misc
                this.offerRecyclingRecipe(0.1f, Items.CROSSBOW, Items.IRON_INGOT, 3);
                this.offerRecyclingRecipe(0.1f, Items.ELYTRA, Items.PHANTOM_MEMBRANE, 2);
                this.offerRecyclingRecipe(0.1f, Items.MACE, Items.HEAVY_CORE, 1);
                this.offerRecyclingRecipe(0.1f, Items.SHIELD, Items.IRON_INGOT, 1);
                this.offerRecyclingRecipe(0.1f, Items.TRIDENT, Items.PRISMARINE_SHARD, 3);
                this.offerRecyclingRecipe(0.1f, Items.WARPED_FUNGUS_ON_A_STICK, Items.WARPED_FUNGUS, 1);

                //Blocks
                //Shulkers
                this.offerRecyclingRecipe(0.1f, Items.SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.BLACK_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.LIME_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.PINK_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.RED_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_SHULKER_BOX, Items.SHULKER_SHELL, 2);
                //Wool
                this.offerRecyclingRecipe(0.1f, Items.BLACK_WOOL, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_WOOL, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_WOOL, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_WOOL, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_WOOL, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_WOOL, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_WOOL, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_WOOL, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_WOOL, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_WOOL, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_WOOL, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_WOOL, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_WOOL, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_WOOL, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_WOOL, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_WOOL, Items.YELLOW_DYE, 1);
                //Concrete
                this.offerRecyclingRecipe(0.1f, Items.BLACK_CONCRETE, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLACK_CONCRETE_POWDER, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_CONCRETE, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_CONCRETE_POWDER, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_CONCRETE, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_CONCRETE_POWDER, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_CONCRETE, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_CONCRETE_POWDER, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_CONCRETE, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_CONCRETE_POWDER, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_CONCRETE, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_CONCRETE_POWDER, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_CONCRETE, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_CONCRETE_POWDER, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_CONCRETE, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_CONCRETE, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_CONCRETE_POWDER, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_CONCRETE, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_CONCRETE_POWDER, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_CONCRETE, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_CONCRETE_POWDER, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_CONCRETE, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_CONCRETE_POWDER, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_CONCRETE, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_CONCRETE_POWDER, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_CONCRETE, Items.YELLOW_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_CONCRETE_POWDER, Items.YELLOW_DYE, 1);
                //Terracotta
                this.offerRecyclingRecipe(0.1f, Items.BLACK_TERRACOTTA, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLACK_GLAZED_TERRACOTTA, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_TERRACOTTA, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_GLAZED_TERRACOTTA, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_TERRACOTTA, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_GLAZED_TERRACOTTA, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_TERRACOTTA, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_GLAZED_TERRACOTTA, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_TERRACOTTA, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_GLAZED_TERRACOTTA, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_TERRACOTTA, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_GLAZED_TERRACOTTA, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_TERRACOTTA, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_GLAZED_TERRACOTTA, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_TERRACOTTA, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_GLAZED_TERRACOTTA, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_TERRACOTTA, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_GLAZED_TERRACOTTA, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_TERRACOTTA, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_GLAZED_TERRACOTTA, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_TERRACOTTA, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_GLAZED_TERRACOTTA, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_TERRACOTTA, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_GLAZED_TERRACOTTA, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_TERRACOTTA, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_GLAZED_TERRACOTTA, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_TERRACOTTA, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_GLAZED_TERRACOTTA, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_TERRACOTTA, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_GLAZED_TERRACOTTA, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_TERRACOTTA, Items.YELLOW_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_GLAZED_TERRACOTTA, Items.YELLOW_DYE, 1);
                //Stained Glass
                this.offerRecyclingRecipe(0.1f, Items.BLACK_STAINED_GLASS, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLACK_STAINED_GLASS_PANE, Items.BLACK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_STAINED_GLASS, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BLUE_STAINED_GLASS_PANE, Items.BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_STAINED_GLASS, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.BROWN_STAINED_GLASS_PANE, Items.BROWN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_STAINED_GLASS, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.CYAN_STAINED_GLASS_PANE, Items.CYAN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_STAINED_GLASS, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GRAY_STAINED_GLASS_PANE, Items.GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_STAINED_GLASS, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.GREEN_STAINED_GLASS_PANE, Items.GREEN_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_BLUE_STAINED_GLASS_PANE, Items.LIGHT_BLUE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_STAINED_GLASS, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIGHT_GRAY_STAINED_GLASS_PANE, Items.LIGHT_GRAY_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_STAINED_GLASS, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.LIME_STAINED_GLASS_PANE, Items.LIME_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_STAINED_GLASS, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.MAGENTA_STAINED_GLASS_PANE, Items.MAGENTA_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_STAINED_GLASS, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.ORANGE_STAINED_GLASS_PANE, Items.ORANGE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_STAINED_GLASS, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PINK_STAINED_GLASS_PANE, Items.PINK_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_STAINED_GLASS, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.PURPLE_STAINED_GLASS_PANE, Items.PURPLE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_STAINED_GLASS, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.RED_STAINED_GLASS_PANE, Items.RED_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_STAINED_GLASS, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.WHITE_STAINED_GLASS_PANE, Items.WHITE_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_STAINED_GLASS, Items.YELLOW_DYE, 1);
                this.offerRecyclingRecipe(0.1f, Items.YELLOW_STAINED_GLASS_PANE, Items.YELLOW_DYE, 1);

                //Misc
                this.offerRecyclingRecipe(0.1f, Items.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP, 2);

                //Smithing Templates
                this.offerRecyclingRecipe(0.1f, Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, Items.DIAMOND, 7);
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND, 7);

                //Uncrafting
                this.offerRecyclingRecipe(0.1f, Items.NETHERITE_INGOT, Items.NETHERITE_SCRAP, 4);
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
                        RecipeCategory.MISC, JAAVAAItems.ROSE_GOLD_INGOT, RecipeCategory.BUILDING_BLOCKS, JAAVAABlocks.ROSE_GOLD_BLOCK,
                        "rose_gold_block_from_ingot", JAAVAA.idFromItem(JAAVAABlocks.ROSE_GOLD_BLOCK).toString(),
                        "rose_gold_ingot_from_block", JAAVAA.idFromItem(JAAVAAItems.ROSE_GOLD_INGOT).toString()
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
                this.offerStarsteelUpgradeRecipe(Items.NETHERITE_SWORD, RecipeCategory.TOOLS, JAAVAAItems.STARSTEEL_SWORD);
                this.offerStarsteelUpgradeRecipe(JAAVAAItems.TOOL_OF_THE_ANCIENTS, RecipeCategory.TOOLS, JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL);
            }
            private void createToolsetRecipes() {
                this.offerToolsetRecipes(JAAVAAItems.AURON_INGOT, JAAVAAItems.AURON_SWORD, JAAVAAItems.AURON_SHOVEL,
                        JAAVAAItems.AURON_PICKAXE, JAAVAAItems.AURON_AXE, JAAVAAItems.AURON_HOE);
                this.offerToolsetRecipes(JAAVAAItems.ROSE_GOLD_INGOT, JAAVAAItems.ROSE_GOLD_SWORD, JAAVAAItems.ROSE_GOLD_SHOVEL,
                        JAAVAAItems.ROSE_GOLD_PICKAXE, JAAVAAItems.ROSE_GOLD_AXE, JAAVAAItems.ROSE_GOLD_HOE);
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
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.ARCHITECTS_COMPASS, 1);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET, targetStructure);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.ARCHITECTS_COMPASS)
                        .input(attunementItem)
                        .group(JAAVAA.idFromItem(JAAVAAItems.ARCHITECTS_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.ARCHITECTS_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("architects_compass_attunement_" + targetStructure.id().getPath()));
            }
            private void offerArchitectsCompassAttunementRecipe(TagKey<Structure> targetStructure, TagKey<Item> attunementTag) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.ARCHITECTS_COMPASS, 1);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET, targetStructure);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.ARCHITECTS_COMPASS)
                        .input(attunementTag)
                        .group(JAAVAA.idFromItem(JAAVAAItems.ARCHITECTS_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.ARCHITECTS_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("architects_compass_attunement_" + targetStructure.id().getPath()));
            }
            private void offerBiomeCompassAttunementRecipe(RegistryKey<Biome> targetBiome, ItemConvertible attunementItem) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.BIOME_COMPASS, 1);
                attunedCompass.set(JAAVAAComponents.Types.COMPASS_BIOME_TARGET, targetBiome);
                this.createShapeless(RecipeCategory.TOOLS, attunedCompass)
                        .input(JAAVAAItems.BIOME_COMPASS)
                        .input(attunementItem)
                        .group(JAAVAA.idFromItem(JAAVAAItems.BIOME_COMPASS).toString())
                        .criterion(hasItem(JAAVAAItems.BIOME_COMPASS), conditionsFromItem(JAAVAAItems.BIOME_COMPASS))
                        .offerTo(this.exporter, recipeKeyOf("biome_compass_attunement_" + targetBiome.getValue().getPath()));
            }
            private void offerBiomeCompassAttunementRecipe(RegistryKey<Biome> targetBiome, TagKey<Item> attunementTag) {
                ItemStack attunedCompass = new ItemStack(JAAVAAItems.BIOME_COMPASS, 1);
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
                this.createShapeless(RecipeCategory.BUILDING_BLOCKS, parentBlock, 1)
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

                this.offerRecyclingRecipe(0.1f, helmetItem, material, 5);
                this.offerRecyclingRecipe(0.1f, chestplateItem, material, 8);
                this.offerRecyclingRecipe(0.1f, legItem, material, 7);
                this.offerRecyclingRecipe(0.1f, bootItem, material, 4);
            }

            private void offerHammerRecipePair(Item hammer, ItemConvertible head, String material) {
                this.createShaped(RecipeCategory.TOOLS, hammer)
                        .input('C', Items.HEAVY_CORE)
                        .input('H', head)
                        .input('R', JAAVAAItems.FUSED_ROD)
                        .pattern(" HC")
                        .pattern(" RH")
                        .pattern("R  ")
                        .group(JAAVAA.idFromItem(hammer).toString())
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .criterion(hasItem(JAAVAAItems.FUSED_ROD), conditionsFromItem(JAAVAAItems.FUSED_ROD))
                        .offerTo(this.exporter, recipeKeyOf("hammer_" + material + "_1"));
                this.createShaped(RecipeCategory.TOOLS, hammer)
                        .input('C', Items.HEAVY_CORE)
                        .input('H', head)
                        .input('R', JAAVAAItems.FUSED_ROD)
                        .pattern("CH ")
                        .pattern("HR ")
                        .pattern("  R")
                        .group(JAAVAA.idFromItem(hammer).toString())
                        .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                        .criterion(hasItem(JAAVAAItems.FUSED_ROD), conditionsFromItem(JAAVAAItems.FUSED_ROD))
                        .offerTo(this.exporter, recipeKeyOf("hammer_" + material + "_2"));

                this.offerRecyclingRecipe(0.1f, hammer, Items.HEAVY_CORE, 1);
            }
            private void offerRecyclingRecipe(float experience, ItemConvertible input, ItemConvertible output, int outputCount) {
                String inputName = JAAVAA.idFromItem(input).getPath();
                var inputStack = new ItemStack(input, 1);
                var outputStack = new ItemStack(output, outputCount);
                recipeExporter.accept(RegistryKey.of(
                                RegistryKeys.RECIPE, JAAVAA.id("recycling_" + inputName)),
                        new RecyclingRecipe(experience, inputStack, outputStack),
                        null);
            }
            private void offerSmelting(ItemConvertible item, RecipeCategory category, ItemConvertible result, float experience, int cookingTime, String group) {
                offerSmelting(List.of(item), category, result, experience, cookingTime, group);
            }
            private void offerStarsteelUpgradeRecipe(Item input, RecipeCategory category, Item result) {
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(JAAVAAItems.STARSTEEL_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(input),
                                this.ingredientFromTag(JAAVAATags.Items.STARSTEEL_TOOL_MATERIALS),
                                category,
                                result
                        )
                        .criterion("has_starsteel_ingot", this.conditionsFromTag(JAAVAATags.Items.STARSTEEL_TOOL_MATERIALS))
                        .offerTo(this.exporter, getItemPath(result) + "_smithing");
            }
            private void offerToolsetRecipes(ItemConvertible material, ItemConvertible swordItem, ItemConvertible shovelItem,
                                             ItemConvertible pickaxeItem, ItemConvertible axeItem, ItemConvertible hoeItem) {
                this.createShaped(RecipeCategory.TOOLS, swordItem)
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

                this.offerRecyclingRecipe(0.1f, swordItem, material, 2);
                this.offerRecyclingRecipe(0.1f, shovelItem, material, 1);
                this.offerRecyclingRecipe(0.1f, pickaxeItem, material, 3);
                this.offerRecyclingRecipe(0.1f, axeItem, material, 3);
                this.offerRecyclingRecipe(0.1f, hoeItem, material, 2);
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
