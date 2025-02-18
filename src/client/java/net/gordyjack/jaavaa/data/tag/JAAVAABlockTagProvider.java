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
                        Blocks.RESIN_BLOCK,
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

                JAAVAABlocks.STARSTEEL_BLOCK_MINI_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                Blocks.GLASS,
                Blocks.GLOWSTONE,
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.ANDESITE_MINI_BLOCK,
                JAAVAABlocks.POLISHED_ANDESITE_MINI_BLOCK,
                JAAVAABlocks.BRICKS_MINI_BLOCK,
                JAAVAABlocks.COAL_MINI_BLOCK,
                JAAVAABlocks.COBBLESTONE_MINI_BLOCK,
                JAAVAABlocks.DEEPSLATE_MINI_BLOCK,
                JAAVAABlocks.POLISHED_DEEPSLATE_MINI_BLOCK,
                JAAVAABlocks.DEEPSLATE_TILES_MINI_BLOCK,
                JAAVAABlocks.DIAMOND_MINI_BLOCK,
                JAAVAABlocks.DIORITE_MINI_BLOCK,
                JAAVAABlocks.POLISHED_DIORITE_MINI_BLOCK,
                JAAVAABlocks.END_STONE_MINI_BLOCK,
                JAAVAABlocks.END_STONE_BRICKS_MINI_BLOCK,
                JAAVAABlocks.EMERALD_MINI_BLOCK,
                JAAVAABlocks.GLOWSTONE_MINI_BLOCK,
                JAAVAABlocks.GOLD_MINI_BLOCK,
                JAAVAABlocks.GRANITE_MINI_BLOCK,
                JAAVAABlocks.POLISHED_GRANITE_MINI_BLOCK,
                JAAVAABlocks.IRON_MINI_BLOCK,
                JAAVAABlocks.LAPIS_LAZULI_MINI_BLOCK,
                JAAVAABlocks.NETHER_BRICK_MINI_BLOCK,
                JAAVAABlocks.NETHERITE_MINI_BLOCK,
                JAAVAABlocks.OBSIDIAN_MINI_BLOCK,
                JAAVAABlocks.PRISMARINE_MINI_BLOCK,
                JAAVAABlocks.PRISMARINE_BRICKS_MINI_BLOCK,
                JAAVAABlocks.DARK_PRISMARINE_MINI_BLOCK,
                JAAVAABlocks.PURPUR_MINI_BLOCK,
                JAAVAABlocks.QUARTZ_MINI_BLOCK,
                //JAAVAABlocks.SMOOTH_QUARTZ_MINI_BLOCK,
                JAAVAABlocks.REDSTONE_MINI_BLOCK,
                JAAVAABlocks.RED_NETHER_BRICK_MINI_BLOCK,
                JAAVAABlocks.SANDSTONE_MINI_BLOCK,
                //JAAVAABlocks.SMOOTH_SANDSTONE_MINI_BLOCK,
                JAAVAABlocks.STONE_MINI_BLOCK,
                JAAVAABlocks.SMOOTH_STONE_MINI_BLOCK,
                JAAVAABlocks.STONE_BRICKS_MINI_BLOCK,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE_MINI_BLOCK,
                JAAVAABlocks.STARSTEEL_BLOCK_MINI_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.NETHERITE_MINI_BLOCK,
                JAAVAABlocks.OBSIDIAN_MINI_BLOCK,
                JAAVAABlocks.STARSTEEL_BLOCK_MINI_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(
                JAAVAABlocks.DIAMOND_MINI_BLOCK,
                JAAVAABlocks.EMERALD_MINI_BLOCK,
                JAAVAABlocks.GOLD_MINI_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(
                JAAVAABlocks.IRON_MINI_BLOCK,
                JAAVAABlocks.LAPIS_LAZULI_MINI_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.SAND).add(
                JAAVAABlocks.QUICKSAND
        );
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
                JAAVAABlocks.QUICKSAND
        );
        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE).add(
                JAAVAABlocks.RAW_VOIDIUM,
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.STARSTEEL_BLOCK_MINI_BLOCK
        );
    }
}
