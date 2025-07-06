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
        getOrCreateTagBuilder(JAAVAATags.Blocks.BLOCKTANTS).add(
                JAAVAABlocks.BLOCKTANTS.keySet().toArray(new Block[0])
        );
        getOrCreateTagBuilder(JAAVAATags.Blocks.CAPRICIOUS_BLOCKS)
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
        getOrCreateTagBuilder(JAAVAATags.Blocks.DEEPSLATE_CRAFTABLES).add(
                Blocks.DEEPSLATE,
                Blocks.POLISHED_DEEPSLATE,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE
        );
        getOrCreateTagBuilder(JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL);
        getOrCreateTagBuilder(JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL);
        getOrCreateTagBuilder(JAAVAATags.Blocks.PAXEL_MINEABLE)
                .addOptionalTag(BlockTags.AXE_MINEABLE)
                .addOptionalTag(BlockTags.HOE_MINEABLE)
                .addOptionalTag(BlockTags.PICKAXE_MINEABLE)
                .addOptionalTag(BlockTags.SHOVEL_MINEABLE);
        //Vanilla Tags
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS).add(
                JAAVAABlocks.STARSTEEL_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE).add(
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.STARSTEEL_BLOCKTANT
        );
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
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
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                Blocks.GLASS,
                Blocks.GLOWSTONE,
                Blocks.RESIN_BLOCK,
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.AMETHYST_BLOCKTANT,
                JAAVAABlocks.ANDESITE_BLOCKTANT,
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
                JAAVAABlocks.REDSTONE_BLOCKTANT,
                JAAVAABlocks.RED_NETHER_BRICK_BLOCKTANT,
                JAAVAABlocks.RESIN_BLOCKTANT,
                JAAVAABlocks.RESIN_BRICKS_BLOCKTANT,
                JAAVAABlocks.SANDSTONE_BLOCKTANT,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE_BLOCKTANT,
                JAAVAABlocks.SMOOTH_STONE_BLOCKTANT,
                JAAVAABlocks.STONE_BLOCKTANT,
                JAAVAABlocks.STONE_BRICKS_BLOCKTANT,
                JAAVAABlocks.STARSTEEL_BLOCKTANT
        );
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
                JAAVAABlocks.QUICKSAND,

                JAAVAABlocks.COARSE_DIRT_BLOCKTANT,
                JAAVAABlocks.DIRT_BLOCKTANT
        );
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE
        );
        getOrCreateTagBuilder(BlockTags.SAND).add(
                JAAVAABlocks.QUICKSAND
        );
        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE).add(
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE
        );
    }
}
