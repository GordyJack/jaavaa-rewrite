package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;

import java.util.concurrent.*;

public class JAAVAABlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public JAAVAABlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //Mod Tags
        valueLookupBuilder(JAAVAATags.Blocks.BLOCKTANTS).add(
                JAAVAABlocks.BLOCKTANTS.keySet().toArray(new Block[0])
        );
        valueLookupBuilder(JAAVAATags.Blocks.CAPRICIOUS_BLOCKS)
                .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD)
                .addOptionalTag(BlockTags.DIRT).add(
                        Blocks.SAND,
                        Blocks.RED_SAND,
                        Blocks.CLAY,
                        Blocks.GRAVEL,
                        Blocks.SANDSTONE,
                        Blocks.RED_SANDSTONE,
                        Blocks.CALCITE,
                        Blocks.RAW_COPPER_BLOCK,
                        Blocks.RAW_IRON_BLOCK,
                        Blocks.RAW_GOLD_BLOCK
                ).addOptionalTag(BlockTags.BADLANDS_TERRACOTTA).add(
                        Blocks.PRISMARINE,
                        Blocks.DARK_PRISMARINE,
                        Blocks.SEA_LANTERN
                )
                .addOptionalTag(BlockTags.BASE_STONE_NETHER)
                .addOptionalTag(BlockTags.NYLIUM)
                .addOptionalTag(BlockTags.WART_BLOCKS).add(
                        Blocks.SOUL_SAND,
                        Blocks.SOUL_SOIL,
                        Blocks.CRYING_OBSIDIAN,
                        Blocks.OBSIDIAN,
                        Blocks.END_STONE
                ).addOptionalTag(BlockTags.OVERWORLD_NATURAL_LOGS).add(
                        Blocks.CRIMSON_STEM,
                        Blocks.WARPED_STEM
                )
                .addOptionalTag(BlockTags.COAL_ORES)
                .addOptionalTag(BlockTags.COPPER_ORES)
                .addOptionalTag(BlockTags.DIAMOND_ORES)
                .addOptionalTag(BlockTags.EMERALD_ORES)
                .addOptionalTag(BlockTags.GOLD_ORES)
                .addOptionalTag(BlockTags.IRON_ORES)
                .addOptionalTag(BlockTags.LAPIS_ORES)
                .addOptionalTag(BlockTags.REDSTONE_ORES).add(
                        Blocks.NETHER_QUARTZ_ORE,
                        Blocks.ANCIENT_DEBRIS,
                        Blocks.GLOWSTONE,
                        Blocks.OCHRE_FROGLIGHT,
                        Blocks.PEARLESCENT_FROGLIGHT,
                        Blocks.VERDANT_FROGLIGHT,
                        Blocks.AMETHYST_BLOCK,
                        JAAVAABlocks.QUICKSAND,
                        JAAVAABlocks.RAW_VOIDIUM
                );
        valueLookupBuilder(JAAVAATags.Blocks.DEEPSLATE_CRAFTABLES).add(
                Blocks.DEEPSLATE,
                Blocks.POLISHED_DEEPSLATE,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE
        );
        valueLookupBuilder(JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL);
        valueLookupBuilder(JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL);
        valueLookupBuilder(JAAVAATags.Blocks.PAXEL_MINEABLE)
                .addOptionalTag(BlockTags.AXE_MINEABLE)
                .addOptionalTag(BlockTags.HOE_MINEABLE)
                .addOptionalTag(BlockTags.PICKAXE_MINEABLE)
                .addOptionalTag(BlockTags.SHOVEL_MINEABLE);
        //Vanilla Tags
        valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS).add(
                JAAVAABlocks.AURON_BLOCK,
                JAAVAABlocks.QUICKSILVER_BLOCK,
                JAAVAABlocks.CUPAUREUM_BLOCK,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STEEL_BLOCK
        );
        valueLookupBuilder(BlockTags.DRAGON_IMMUNE).add(
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.STARSTEEL_BLOCKTANT
        );
        valueLookupBuilder(BlockTags.AXE_MINEABLE).add(
                JAAVAABlocks.ACACIA_PLANKS_BLOCKTANT,
                JAAVAABlocks.BAMBOO_MOSAIC_BLOCKTANT,
                JAAVAABlocks.BAMBOO_PLANKS_BLOCKTANT,
                JAAVAABlocks.BIRCH_PLANKS_BLOCKTANT,
                JAAVAABlocks.CRIMSON_PLANKS_BLOCKTANT,
                JAAVAABlocks.DARK_OAK_PLANKS_BLOCKTANT,
                JAAVAABlocks.JUNGLE_PLANKS_BLOCKTANT,
                JAAVAABlocks.OAK_PLANKS_BLOCKTANT,
                JAAVAABlocks.SPRUCE_PLANKS_BLOCKTANT,
                JAAVAABlocks.WARPED_PLANKS_BLOCKTANT
        );
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(
                Blocks.GLASS,
                Blocks.GLOWSTONE,
                JAAVAABlocks.AURON_BLOCK,
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.CUPAUREUM_BLOCK,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,
                JAAVAABlocks.STEEL_BLOCK,

                JAAVAABlocks.AMETHYST_BLOCKTANT,
                JAAVAABlocks.ANDESITE_BLOCKTANT,
                JAAVAABlocks.AURON_BLOCKTANT,
                JAAVAABlocks.BRICKS_BLOCKTANT,
                JAAVAABlocks.COAL_BLOCKTANT,
                JAAVAABlocks.COBBLESTONE_BLOCKTANT,
                JAAVAABlocks.DARK_PRISMARINE_BLOCKTANT,
                JAAVAABlocks.DEEPSLATE_BLOCKTANT,
                JAAVAABlocks.DEEPSLATE_TILES_BLOCKTANT,
                JAAVAABlocks.DIAMOND_BLOCKTANT,
                JAAVAABlocks.DIORITE_BLOCKTANT,
                JAAVAABlocks.EMERALD_BLOCKTANT,
                JAAVAABlocks.END_STONE_BLOCKTANT,
                JAAVAABlocks.END_STONE_BRICKS_BLOCKTANT,
                JAAVAABlocks.GLOWSTONE_BLOCKTANT,
                JAAVAABlocks.GOLD_BLOCKTANT,
                JAAVAABlocks.GRANITE_BLOCKTANT,
                JAAVAABlocks.IRON_BLOCKTANT,
                JAAVAABlocks.LAPIS_LAZULI_BLOCKTANT,
                JAAVAABlocks.NETHER_BRICK_BLOCKTANT,
                JAAVAABlocks.NETHERITE_BLOCKTANT,
                JAAVAABlocks.OBSIDIAN_BLOCKTANT,
                JAAVAABlocks.POLISHED_ANDESITE_BLOCKTANT,
                JAAVAABlocks.POLISHED_DEEPSLATE_BLOCKTANT,
                JAAVAABlocks.POLISHED_DIORITE_BLOCKTANT,
                JAAVAABlocks.POLISHED_GRANITE_BLOCKTANT,
                JAAVAABlocks.PRISMARINE_BLOCKTANT,
                JAAVAABlocks.PRISMARINE_BRICKS_BLOCKTANT,
                JAAVAABlocks.PURPUR_BLOCKTANT,
                JAAVAABlocks.QUARTZ_BLOCKTANT,
                JAAVAABlocks.RAW_COPPER_BLOCKTANT,
                JAAVAABlocks.RAW_GOLD_BLOCKTANT,
                JAAVAABlocks.RAW_IRON_BLOCKTANT,
                JAAVAABlocks.RAW_VOIDIUM_BLOCKTANT,
                JAAVAABlocks.REDSTONE_BLOCKTANT,
                JAAVAABlocks.RED_NETHER_BRICK_BLOCKTANT,
                JAAVAABlocks.CUPAUREUM_BLOCKTANT,
                JAAVAABlocks.SANDSTONE_BLOCKTANT,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE_BLOCKTANT,
                JAAVAABlocks.SMOOTH_STONE_BLOCKTANT,
                JAAVAABlocks.STONE_BLOCKTANT,
                JAAVAABlocks.STONE_BRICKS_BLOCKTANT,
                JAAVAABlocks.STARSTEEL_BLOCKTANT
        );
        valueLookupBuilder(BlockTags.SHOVEL_MINEABLE).add(
                JAAVAABlocks.QUICKSAND,

                JAAVAABlocks.COARSE_DIRT_BLOCKTANT,
                JAAVAABlocks.DIRT_BLOCKTANT
        );
        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE
        );
        valueLookupBuilder(BlockTags.SAND).add(
                JAAVAABlocks.QUICKSAND
        );
        valueLookupBuilder(BlockTags.WITHER_IMMUNE).add(
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE
        );
    }
}
