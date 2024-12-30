package net.gordyjack.jaavaa.block;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;

import java.util.*;
import java.util.function.*;

public class JAAVAABlocks {
    public static final ArrayList<Block> BLOCKS = new ArrayList<>();

    //Blocks
    public static final Block ADDER_BLOCK = register("adder",
            AdderBlock::new, AbstractBlock.Settings.copy(Blocks.REPEATER), false);
    public static final Block ADJUSTABLE_REDSTONE_LAMP = register("adjustable_redstone_lamp",
            AdjustableRedstoneLampBlock::new, AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AdjustableRedstoneLampBlock.LUMINANCE))
                    .sounds(BlockSoundGroup.GLASS)
                    .strength(0.3F));
    public static final Block ADVANCED_REPEATER_BLOCK = register("advanced_repeater",
            AdvancedRepeaterBlock::new, AbstractBlock.Settings.copy(Blocks.REPEATER), false);
    public static final Block ALLOY_FURNACE = register("alloy_furnace",
            AlloyFurnaceBlock::new, AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AlloyFurnaceBlock.LIT) ? 13 : 0)
                    .sounds(BlockSoundGroup.STONE)
                    .strength(3.5F));
    public static final Block ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR = register("ancient_debris_encased_redstone_pillar",
            EncasedRedstoneBlock::new, AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS));
    public static final Block DECODER_BLOCK = register("decoder",
            DecoderBlock::new, AbstractBlock.Settings.copy(Blocks.REPEATER), false);
    public static final Block EXAMPLE_BLOCK = register("test_block",
            Block::new, AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(0.8F));
    public static final Block SMOOTH_POLISHED_DEEPSLATE = register("smooth_polished_deepslate",
            Block::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE));
    public static final Block STARSTEEL_BLOCK = register("starsteel_block",
            Block::new, AbstractBlock.Settings.create()
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .strength(50.0F, 1200.0F),
            Rarity.RARE);
    public static final Block QUARTZ_ENCASED_REDSTONE_PILLAR = register("quartz_encased_redstone_pillar",
            EncasedRedstoneBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK));
    public static final Block WITHER_PROOF_GLASS = register("wither_proof_glass",
            Block::new, AbstractBlock.Settings.copy(Blocks.GLASS)
                    .strength(1.0F, 1200.0F));

    //Methods
    /**
     * Registers a Block and BlockItem with the given path and default settings.
     * @param path The path of the Block
     * @param factory The factory function for the Block
     * @param settings The settings for the Block
     * @return The registered Block
     */
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory,
                                  AbstractBlock.Settings settings) {
        return register(path, factory, settings, Rarity.COMMON, true);
    }
    /**
     * Registers a Block and BlockItem with the given path, factory, and settings.
     * @param path The path of the Block
     * @param factory The factory function for the Block
     * @param settings The settings for the Block
     * @param rarity The rarity of the BlockItem
     * @return The registered Block
     */
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory,
                                  AbstractBlock.Settings settings, Rarity rarity) {
        return register(path, factory, settings, rarity, true);
    }
    /**
     * Registers a Block with the given path, factory, settings, rarity, and whether it should have a BlockItem.
     * @param path The path of the Block
     * @param factory The factory function for the Block
     * @param settings The settings for the Block
     * @param shouldHaveItem Whether the Block should have a BlockItem
     * @return The registered Block
     */
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory,
                                  AbstractBlock.Settings settings, boolean shouldHaveItem) {
        return register(path, factory, settings, Rarity.COMMON, shouldHaveItem);
    }
    /**
     * Registers a Block with the given path, factory, settings, rarity, and whether it should have a BlockItem.
     * @param path The path of the Block
     * @param factory The factory function for the Block
     * @param settings The settings for the Block
     * @param rarity The rarity of the BlockItem
     * @param shouldHaveItem Whether the Block should have a BlockItem
     * @return The registered Block
     */
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory,
                                  AbstractBlock.Settings settings, Rarity rarity, boolean shouldHaveItem) {
        final Block block = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, JAAVAA.id(path)), factory, settings);
        if (shouldHaveItem) {
            Items.register(block, new Item.Settings().rarity(rarity));
        }
        BLOCKS.add(block);
        return block;
    }
    /**
     * Initializes all blocks.
     */
    public static void init() {
        JAAVAA.log("Initializing blocks");

        //Adding Blocks to main Block ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_BLOCKS).register(entries -> {
            entries.add(EXAMPLE_BLOCK);
            entries.add(SMOOTH_POLISHED_DEEPSLATE);
            entries.add(STARSTEEL_BLOCK);
            entries.add(ALLOY_FURNACE);
        });
        //Adding Blocks to main Redstone ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_REDSTONE).register(entries -> {
            entries.add(ADJUSTABLE_REDSTONE_LAMP);
            entries.add(ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR);
            entries.add(QUARTZ_ENCASED_REDSTONE_PILLAR);
        });
    }
}
