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
        getOrCreateTagBuilder(JAAVAATags.Blocks.DEEPSLATE_CRAFTABLES).add(
                Blocks.DEEPSLATE,
                Blocks.POLISHED_DEEPSLATE,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE
        );
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
                JAAVAABlocks.SMOOTH_QUARTZ_MINI_BLOCK,
                JAAVAABlocks.REDSTONE_MINI_BLOCK,
                JAAVAABlocks.RED_NETHER_BRICK_MINI_BLOCK,
                JAAVAABlocks.SANDSTONE_MINI_BLOCK,
                JAAVAABlocks.SMOOTH_SANDSTONE_MINI_BLOCK,
                JAAVAABlocks.STONE_MINI_BLOCK,
                JAAVAABlocks.SMOOTH_STONE_MINI_BLOCK,
                JAAVAABlocks.STONE_BRICKS_MINI_BLOCK,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE_MINI_BLOCK,
                JAAVAABlocks.STARSTEEL_BLOCK_MINI_BLOCK
        );
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
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
//        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
//                JAAVAABlocks.DIRT_MINI_BLOCK,
//                JAAVAABlocks.COARSE_DIRT_MINI_BLOCK
//        );
        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE).add(
                JAAVAABlocks.STARSTEEL_BLOCK,
                JAAVAABlocks.STARSTEEL_GLASS,
                JAAVAABlocks.STARSTEEL_GLASS_PANE,

                JAAVAABlocks.STARSTEEL_BLOCK_MINI_BLOCK
        );
    }
}
