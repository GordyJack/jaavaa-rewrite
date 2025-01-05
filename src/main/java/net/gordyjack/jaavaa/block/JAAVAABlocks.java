package net.gordyjack.jaavaa.block;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.block.*;
import net.minecraft.component.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class JAAVAABlocks {
    public static final ArrayList<Block> BLOCKS = new ArrayList<>();
    public static final Map<MiniBlock, Block> MINI_BLOCKS = new HashMap<>();
    public static final Item.Settings STARSTEEL_DEFAULT_SETTINGS =
            new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT);

    // Blocks
    public static final Block SMOOTH_POLISHED_DEEPSLATE = registerBlock("smooth_polished_deepslate", Block::new,
            AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE));
    public static final Block STARSTEEL_BLOCK = registerBlock("starsteel_block",
            Block::new, AbstractBlock.Settings.create()
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .strength(50.0F, 1200.0F),
            EternalBlockItem::new, STARSTEEL_DEFAULT_SETTINGS);
    public static final Block STARSTEEL_GLASS = registerBlock("starsteel_glass",
            TransparentBlock::new, AbstractBlock.Settings.copy(Blocks.GLASS)
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .strength(1.0F, 1200.0F),
            EternalBlockItem::new, STARSTEEL_DEFAULT_SETTINGS);
    public static final Block STARSTEEL_GLASS_PANE = registerBlock("starsteel_glass_pane",
            PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GLASS_PANE)
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .strength(1.0F, 1200.0F),
            EternalBlockItem::new, STARSTEEL_DEFAULT_SETTINGS);
    // Functional Blocks
    public static final Block ALLOY_FURNACE = registerBlock("alloy_furnace",
            AlloyFurnaceBlock::new, AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AlloyFurnaceBlock.LIT) ? 13 : 0)
                    .sounds(BlockSoundGroup.STONE)
                    .strength(3.5F),
            new Item.Settings().fireproof());
    public static final Block RECYCLING_TABLE = registerBlock("recycling_table",
            RecyclingTableBlock::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS)
                    .requiresTool(),
            new Item.Settings().fireproof());
    // Redstone Gates
    public static final Block ADDER = registerBlock("adder",
            AdderBlock::new, AbstractBlock.Settings.copy(Blocks.REPEATER));
    public static final Block ADVANCED_REPEATER = registerBlock("advanced_repeater",
            AdvancedRepeaterBlock::new, AbstractBlock.Settings.copy(Blocks.REPEATER));
    public static final Block DECODER = registerBlock("decoder",
            DecoderBlock::new, AbstractBlock.Settings.copy(Blocks.REPEATER));
    // Redstone Blocks
    public static final Block ADJUSTABLE_REDSTONE_LAMP = registerBlock("adjustable_redstone_lamp", AdjustableRedstoneLampBlock::new,
            AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AdjustableRedstoneLampBlock.LUMINANCE))
                    .sounds(BlockSoundGroup.GLASS)
                    .strength(0.3F));
    public static final Block ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR = registerBlock("ancient_debris_encased_redstone_pillar",
            EncasedRedstoneBlock::new, AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS),
            new Item.Settings().fireproof());
    public static final Block QUARTZ_ENCASED_REDSTONE_PILLAR = registerBlock("quartz_encased_redstone_pillar", EncasedRedstoneBlock::new,
            AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK));
    // Mini Blocks
    public static final Block ANDESITE_MINI_BLOCK =
            registerMiniBlock("andesite_mini_block", Blocks.ANDESITE);
    public static final Block POLISHED_ANDESITE_MINI_BLOCK =
            registerMiniBlock("polished_andesite_mini_block", Blocks.POLISHED_ANDESITE);
    public static final Block BRICKS_MINI_BLOCK =
            registerMiniBlock("bricks_mini_block", Blocks.BRICKS);
    public static final Block COAL_MINI_BLOCK =
            registerMiniBlock("coal_mini_block", Blocks.COAL_BLOCK);
    public static final Block COBBLESTONE_MINI_BLOCK =
            registerMiniBlock("cobblestone_mini_block", Blocks.COBBLESTONE);
    public static final Block DEEPSLATE_MINI_BLOCK =
            registerMiniBlock("deepslate_mini_block", Blocks.DEEPSLATE);
    public static final Block POLISHED_DEEPSLATE_MINI_BLOCK =
            registerMiniBlock("polished_deepslate_mini_block", Blocks.POLISHED_DEEPSLATE);
    public static final Block DEEPSLATE_TILES_MINI_BLOCK =
            registerMiniBlock("deepslate_tiles_mini_block", Blocks.DEEPSLATE_TILES);
    public static final Block DIAMOND_MINI_BLOCK =
            registerMiniBlock("diamond_mini_block", Blocks.DIAMOND_BLOCK);
    public static final Block DIORITE_MINI_BLOCK =
            registerMiniBlock("diorite_mini_block", Blocks.DIORITE);
    public static final Block POLISHED_DIORITE_MINI_BLOCK =
            registerMiniBlock("polished_diorite_mini_block", Blocks.POLISHED_DIORITE);
    public static final Block DIRT_MINI_BLOCK =
            registerMiniBlock("dirt_mini_block", Blocks.DIRT);
    public static final Block COARSE_DIRT_MINI_BLOCK =
            registerMiniBlock("coarse_dirt_mini_block", Blocks.COARSE_DIRT);
    public static final Block END_STONE_MINI_BLOCK =
            registerMiniBlock("end_stone_mini_block", Blocks.END_STONE);
    public static final Block END_STONE_BRICKS_MINI_BLOCK =
            registerMiniBlock("end_stone_bricks_mini_block", Blocks.END_STONE_BRICKS);
    public static final Block EMERALD_MINI_BLOCK =
            registerMiniBlock("emerald_mini_block", Blocks.EMERALD_BLOCK);
    public static final Block GLOWSTONE_MINI_BLOCK =
            registerMiniBlock("glowstone_mini_block", Blocks.GLOWSTONE);
    public static final Block GOLD_MINI_BLOCK =
            registerMiniBlock("gold_mini_block", Blocks.GOLD_BLOCK);
    public static final Block GRANITE_MINI_BLOCK =
            registerMiniBlock("granite_mini_block", Blocks.GRANITE);
    public static final Block POLISHED_GRANITE_MINI_BLOCK =
            registerMiniBlock("polished_granite_mini_block", Blocks.POLISHED_GRANITE);
    public static final Block IRON_MINI_BLOCK =
            registerMiniBlock("iron_mini_block", Blocks.IRON_BLOCK);
    public static final Block LAPIS_LAZULI_MINI_BLOCK =
            registerMiniBlock("lapis_lazuli_mini_block", Blocks.LAPIS_BLOCK);
    public static final Block NETHER_BRICK_MINI_BLOCK =
            registerMiniBlock("nether_brick_mini_block", Blocks.NETHER_BRICKS);
    public static final Block NETHERITE_MINI_BLOCK =
            registerMiniBlock("netherite_mini_block", Blocks.NETHERITE_BLOCK);
    public static final Block OBSIDIAN_MINI_BLOCK =
            registerMiniBlock("obsidian_mini_block", Blocks.OBSIDIAN);
    public static final Block PRISMARINE_MINI_BLOCK =
            registerMiniBlock("prismarine_mini_block", Blocks.PRISMARINE);
    public static final Block PRISMARINE_BRICKS_MINI_BLOCK =
            registerMiniBlock("prismarine_bricks_mini_block", Blocks.PRISMARINE_BRICKS);
    public static final Block DARK_PRISMARINE_MINI_BLOCK =
            registerMiniBlock("dark_prismarine_mini_block", Blocks.DARK_PRISMARINE);
    public static final Block PURPUR_MINI_BLOCK =
            registerMiniBlock("purpur_mini_block", Blocks.PURPUR_BLOCK);
    public static final Block QUARTZ_MINI_BLOCK =
            registerMiniBlock("quartz_mini_block", Blocks.QUARTZ_BLOCK);
    public static final Block SMOOTH_QUARTZ_MINI_BLOCK =
            registerMiniBlock("smooth_quartz_mini_block", Blocks.SMOOTH_QUARTZ);
    public static final Block REDSTONE_MINI_BLOCK =
            registerMiniBlock("redstone_mini_block", Blocks.REDSTONE_BLOCK);
    public static final Block RED_NETHER_BRICK_MINI_BLOCK =
            registerMiniBlock("red_nether_brick_mini_block", Blocks.RED_NETHER_BRICKS);
    public static final Block SANDSTONE_MINI_BLOCK =
            registerMiniBlock("sandstone_mini_block", Blocks.SANDSTONE);
    public static final Block SMOOTH_SANDSTONE_MINI_BLOCK =
            registerMiniBlock("smooth_sandstone_mini_block", Blocks.SMOOTH_SANDSTONE);
    public static final Block STONE_MINI_BLOCK =
            registerMiniBlock("stone_mini_block", Blocks.STONE);
    public static final Block SMOOTH_STONE_MINI_BLOCK =
            registerMiniBlock("smooth_stone_mini_block", Blocks.SMOOTH_STONE);
    public static final Block STONE_BRICKS_MINI_BLOCK =
            registerMiniBlock("stone_bricks_mini_block", Blocks.STONE_BRICKS);
    public static final Block SMOOTH_POLISHED_DEEPSLATE_MINI_BLOCK =
            registerMiniBlock("smooth_polished_deepslate_mini_block", JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE);
    public static final Block STARSTEEL_BLOCK_MINI_BLOCK =
            registerMiniBlock("starsteel_block_mini_block", JAAVAABlocks.STARSTEEL_BLOCK);

    //Methods
    private static Block registerBlock(String path, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings) {
        return registerBlock(path, blockFactory, blockSettings, BlockItem::new, new Item.Settings());
    }
    private static Block registerBlock(String path, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings,
                                       Item.Settings itemSettings) {
        return registerBlock(path, blockFactory, blockSettings, null, itemSettings);
    }
    private static Block registerBlock(String path, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings,
                                       BiFunction<Block, Item.Settings, Item> blockItemFactory) {
        return registerBlock(path, blockFactory, blockSettings, blockItemFactory, null);
    }
    private static Block registerBlockWithoutItem(String path, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings) {
        return registerBlock(path, blockFactory, blockSettings, null, null);
    }
    private static Block registerBlock(String path, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings,
                                       @Nullable BiFunction<Block, Item.Settings, Item> blockItemFactory, @Nullable Item.Settings itemSettings) {
        final Block BLOCK = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, JAAVAA.id(path)), blockFactory, blockSettings);
        if (!(blockItemFactory == null && itemSettings == null)) {
            blockItemFactory = blockItemFactory == null ? BlockItem::new : blockItemFactory;
            itemSettings = itemSettings == null ? new Item.Settings() : itemSettings;
            Items.register(BLOCK, blockItemFactory, itemSettings);
        }
        BLOCKS.add(BLOCK);
        return BLOCK;
    }
    private static Block registerMiniBlock(String path, Block parentBlock) {
        final MiniBlock BLOCK = (MiniBlock) registerBlock(path, MiniBlock::new, AbstractBlock.Settings.copy(parentBlock));
        MINI_BLOCKS.put(BLOCK, parentBlock);
        return BLOCK;
    }
    /**
     * Initializes all blocks.
     */
    public static void init() {
        JAAVAA.log("Initializing blocks");

        //Adding Blocks to main Block ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_BLOCKS).register(entries -> {
            entries.add(ALLOY_FURNACE);
            entries.add(SMOOTH_POLISHED_DEEPSLATE);
            entries.add(STARSTEEL_BLOCK);
            entries.add(STARSTEEL_GLASS);
            entries.add(STARSTEEL_GLASS_PANE);
            entries.add(RECYCLING_TABLE);
        });
        //Adding Blocks to main Redstone ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_REDSTONE).register(entries -> {
            entries.add(ADDER);
            entries.add(ADVANCED_REPEATER);
            entries.add(DECODER);
            entries.add(ADJUSTABLE_REDSTONE_LAMP);
            entries.add(ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR);
            entries.add(QUARTZ_ENCASED_REDSTONE_PILLAR);
        });
        //Adding Blocks to mini Block ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_MINI_BLOCKS).register(entries -> {
            for (Block block : MINI_BLOCKS.keySet())
                entries.add(block);
        });
        //Adding Blocks to vanilla ItemGroups
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(SMOOTH_POLISHED_DEEPSLATE);
            entries.add(STARSTEEL_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addAfter(Items.RED_STAINED_GLASS, STARSTEEL_GLASS);
            entries.addAfter(Items.RED_STAINED_GLASS_PANE, STARSTEEL_GLASS_PANE);
        });
    }
}
