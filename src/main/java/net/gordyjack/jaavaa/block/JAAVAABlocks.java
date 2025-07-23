package net.gordyjack.jaavaa.block;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.block.custom.redstone_gates.*;
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

public interface JAAVAABlocks {
    ArrayList<Block> BLOCKS = new ArrayList<>();
    Map<Blocktant, Block> BLOCKTANTS = new HashMap<>();
    Item.Settings STARSTEEL_DEFAULT_SETTINGS =
            new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT);

    // Blocks
    Block AURON_BLOCK = registerBlock("auron_block", Block::new,
            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.PALE_YELLOW)
    );
    Block QUICKSAND = registerBlock("quicksand", QuicksandBlock::new,
            AbstractBlock.Settings.copy(Blocks.SAND)
                    .sounds(BlockSoundGroup.SAND)
                    .strength(0.5F)
    );
    Block QUICKSILVER_BLOCK = registerBlock("quicksilver_block", Block::new,
            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
    );
    Block RAW_VOIDIUM = registerBlock("raw_voidium", Block::new,
            AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS)
                    .mapColor(MapColor.BLACK)
                    .requiresTool()
    );
    Block ROSE_GOLD_BLOCK = registerBlock("rose_gold_block", Block::new,
            AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK)
                    .mapColor(MapColor.RAW_IRON_PINK)
    );
    Block SMOOTH_POLISHED_DEEPSLATE = registerBlock("smooth_polished_deepslate", Block::new,
            AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE)
    );
    Block STARSTEEL_BLOCK = registerBlock("starsteel_block", Block::new,
            AbstractBlock.Settings.create()
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .strength(50.0F, 1200.0F),
            EternalBlockItem::new, STARSTEEL_DEFAULT_SETTINGS
    );
    Block STARSTEEL_GLASS = registerBlock("starsteel_glass", TransparentBlock::new,
            AbstractBlock.Settings.copy(Blocks.GLASS)
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .strength(1.0F, 1200.0F),
            EternalBlockItem::new, STARSTEEL_DEFAULT_SETTINGS
    );
    Block STARSTEEL_GLASS_PANE = registerBlock("starsteel_glass_pane", PaneBlock::new,
            AbstractBlock.Settings.copy(Blocks.GLASS_PANE)
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .strength(1.0F, 1200.0F),
            EternalBlockItem::new, STARSTEEL_DEFAULT_SETTINGS
    );
    Block STEEL_BLOCK = registerBlock("steel_block", Block::new,
            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                    .strength(8.0f, 12.0f)
                    .mapColor(MapColor.DEEPSLATE_GRAY)
    );
    // Functional Blocks
    Block ALLOY_FURNACE = registerBlock("alloy_furnace", AlloyFurnaceBlock::new,
            AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AlloyFurnaceBlock.LIT) ? 13 : 0)
                    .sounds(BlockSoundGroup.STONE)
                    .strength(3.5F),
            new Item.Settings().fireproof()
    );
    Block RECYCLING_TABLE = registerBlock("recycling_table", RecyclingTableBlock::new,
            AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS)
                    .sounds(BlockSoundGroup.STONE),
            new Item.Settings().fireproof()
    );
    // Redstone Blocks
    Block ADJUSTABLE_REDSTONE_LAMP = registerBlock("adjustable_redstone_lamp", AdjustableRedstoneLampBlock::new,
            AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AdjustableRedstoneLampBlock.LUMINANCE))
                    .sounds(BlockSoundGroup.GLASS)
                    .strength(0.3F)
    );
    Block ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR = registerBlock("ancient_debris_encased_redstone_pillar", EncasedRedstoneBlock::new,
            AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS),
            new Item.Settings().fireproof()
    );
    Block QUARTZ_ENCASED_REDSTONE_PILLAR = registerBlock("quartz_encased_redstone_pillar", EncasedRedstoneBlock::new,
            AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)
    );
    // Redstone Gates
    Block ADDER = registerBlock("adder", AdderBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
    );
    Block ADVANCED_REPEATER = registerBlock("advanced_repeater", AdvancedRepeaterBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
    );
    Block DECODER = registerBlock("decoder", DecoderBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
    );
    Block RANDOMIZER = registerBlock("randomizer", RandomizerBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
                    .luminance(state -> state.get(RandomizerBlock.POWER))
    );
    //Logical Gates
    Block LOGICAL_AND_GATE = registerBlock("logical_and_gate", LogicalANDGateBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
    );
    Block LOGICAL_OR_GATE = registerBlock("logical_or_gate", LogicalORGateBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
    );
    Block LOGICAL_XOR_GATE = registerBlock("logical_xor_gate", LogicalXORGateBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER)
    );
    // Blocktants
    Block AMETHYST_BLOCKTANT =
            registerBlocktant("amethyst_blocktant", Blocks.AMETHYST_BLOCK);
    Block ANDESITE_BLOCKTANT =
            registerBlocktant("andesite_blocktant", Blocks.ANDESITE);
    Block BRICKS_BLOCKTANT =
            registerBlocktant("bricks_blocktant", Blocks.BRICKS);
    Block COAL_BLOCKTANT =
            registerBlocktant("coal_blocktant", Blocks.COAL_BLOCK);
    Block COARSE_DIRT_BLOCKTANT =
            registerBlocktant("coarse_dirt_blocktant", Blocks.COARSE_DIRT);
    Block COBBLESTONE_BLOCKTANT =
            registerBlocktant("cobblestone_blocktant", Blocks.COBBLESTONE);
    Block DARK_PRISMARINE_BLOCKTANT =
            registerBlocktant("dark_prismarine_blocktant", Blocks.DARK_PRISMARINE);
    Block DEEPSLATE_BLOCKTANT =
            registerBlocktant("deepslate_blocktant", Blocks.DEEPSLATE);
    Block DEEPSLATE_TILES_BLOCKTANT =
            registerBlocktant("deepslate_tiles_blocktant", Blocks.DEEPSLATE_TILES);
    Block DIAMOND_BLOCKTANT =
            registerBlocktant("diamond_blocktant", Blocks.DIAMOND_BLOCK);
    Block DIORITE_BLOCKTANT =
            registerBlocktant("diorite_blocktant", Blocks.DIORITE);
    Block DIRT_BLOCKTANT =
            registerBlocktant("dirt_blocktant", Blocks.DIRT);
    Block END_STONE_BLOCKTANT =
            registerBlocktant("end_stone_blocktant", Blocks.END_STONE);
    Block END_STONE_BRICKS_BLOCKTANT =
            registerBlocktant("end_stone_bricks_blocktant", Blocks.END_STONE_BRICKS);
    Block EMERALD_BLOCKTANT =
            registerBlocktant("emerald_blocktant", Blocks.EMERALD_BLOCK);
    Block GLOWSTONE_BLOCKTANT =
            registerBlocktant("glowstone_blocktant", Blocks.GLOWSTONE);
    Block GOLD_BLOCKTANT =
            registerBlocktant("gold_blocktant", Blocks.GOLD_BLOCK);
    Block GRANITE_BLOCKTANT =
            registerBlocktant("granite_blocktant", Blocks.GRANITE);
    Block IRON_BLOCKTANT =
            registerBlocktant("iron_blocktant", Blocks.IRON_BLOCK);
    Block LAPIS_LAZULI_BLOCKTANT =
            registerBlocktant("lapis_lazuli_blocktant", Blocks.LAPIS_BLOCK);
    Block NETHER_BRICK_BLOCKTANT =
            registerBlocktant("nether_brick_blocktant", Blocks.NETHER_BRICKS);
    Block NETHERITE_BLOCKTANT =
            registerBlocktant("netherite_blocktant", Blocks.NETHERITE_BLOCK);
    Block OBSIDIAN_BLOCKTANT =
            registerBlocktant("obsidian_blocktant", Blocks.OBSIDIAN);
    Block POLISHED_ANDESITE_BLOCKTANT =
            registerBlocktant("polished_andesite_blocktant", Blocks.POLISHED_ANDESITE);
    Block POLISHED_DEEPSLATE_BLOCKTANT =
            registerBlocktant("polished_deepslate_blocktant", Blocks.POLISHED_DEEPSLATE);
    Block POLISHED_DIORITE_BLOCKTANT =
            registerBlocktant("polished_diorite_blocktant", Blocks.POLISHED_DIORITE);
    Block POLISHED_GRANITE_BLOCKTANT =
            registerBlocktant("polished_granite_blocktant", Blocks.POLISHED_GRANITE);
    Block PRISMARINE_BLOCKTANT =
            registerBlocktant("prismarine_blocktant", Blocks.PRISMARINE);
    Block PRISMARINE_BRICKS_BLOCKTANT =
            registerBlocktant("prismarine_bricks_blocktant", Blocks.PRISMARINE_BRICKS);
    Block PURPUR_BLOCKTANT =
            registerBlocktant("purpur_blocktant", Blocks.PURPUR_BLOCK);
    Block QUARTZ_BLOCKTANT =
            registerBlocktant("quartz_blocktant", Blocks.QUARTZ_BLOCK);
//    Block SMOOTH_QUARTZ_BLOCKTANT =
//            registerMiniBlock("smooth_quartz_blocktant", Blocks.SMOOTH_QUARTZ);
    Block RAW_COPPER_BLOCKTANT =
            registerBlocktant("raw_copper_blocktant", Blocks.RAW_COPPER_BLOCK);
    Block RAW_IRON_BLOCKTANT =
            registerBlocktant("raw_iron_blocktant", Blocks.RAW_IRON_BLOCK);
    Block RAW_GOLD_BLOCKTANT =
            registerBlocktant("raw_gold_blocktant", Blocks.RAW_GOLD_BLOCK);
    Block REDSTONE_BLOCKTANT =
            registerBlocktant("redstone_blocktant", Blocks.REDSTONE_BLOCK);
    Block RED_NETHER_BRICK_BLOCKTANT =
            registerBlocktant("red_nether_brick_blocktant", Blocks.RED_NETHER_BRICKS);
    Block RESIN_BLOCKTANT =
            registerBlocktant("resin_blocktant", Blocks.RESIN_BLOCK);
    Block RESIN_BRICKS_BLOCKTANT =
            registerBlocktant("resin_bricks_blocktant", Blocks.RESIN_BRICKS);
    Block SANDSTONE_BLOCKTANT =
            registerBlocktant("sandstone_blocktant", Blocks.SANDSTONE);
//    Block SMOOTH_SANDSTONE_BLOCKTANT =
//            registerMiniBlock("smooth_sandstone_blocktant", Blocks.SMOOTH_SANDSTONE);
    Block SMOOTH_STONE_BLOCKTANT =
            registerBlocktant("smooth_stone_blocktant", Blocks.SMOOTH_STONE);
    Block STONE_BLOCKTANT =
            registerBlocktant("stone_blocktant", Blocks.STONE);
    Block STONE_BRICKS_BLOCKTANT =
            registerBlocktant("stone_bricks_blocktant", Blocks.STONE_BRICKS);
    Block OAK_PLANKS_BLOCKTANT =
            registerBlocktant("oak_planks_blocktant", Blocks.OAK_PLANKS);
    Block SPRUCE_PLANKS_BLOCKTANT =
            registerBlocktant("spruce_planks_blocktant", Blocks.SPRUCE_PLANKS);
    Block BIRCH_PLANKS_BLOCKTANT =
            registerBlocktant("birch_planks_blocktant", Blocks.BIRCH_PLANKS);
    Block JUNGLE_PLANKS_BLOCKTANT =
            registerBlocktant("jungle_planks_blocktant", Blocks.JUNGLE_PLANKS);
    Block ACACIA_PLANKS_BLOCKTANT =
            registerBlocktant("acacia_planks_blocktant", Blocks.ACACIA_PLANKS);
    Block DARK_OAK_PLANKS_BLOCKTANT =
            registerBlocktant("dark_oak_planks_blocktant", Blocks.DARK_OAK_PLANKS);
    Block CRIMSON_PLANKS_BLOCKTANT =
            registerBlocktant("crimson_planks_blocktant", Blocks.CRIMSON_PLANKS);
    Block WARPED_PLANKS_BLOCKTANT =
            registerBlocktant("warped_planks_blocktant", Blocks.WARPED_PLANKS);
    Block MANGROVE_PLANKS_BLOCKTANT =
            registerBlocktant("mangrove_planks_blocktant", Blocks.MANGROVE_PLANKS);
    Block CHERRY_PLANKS_BLOCKTANT =
            registerBlocktant("cherry_planks_blocktant", Blocks.CHERRY_PLANKS);
    Block PALE_OAK_PLANKS_BLOCKTANT =
            registerBlocktant("pale_oak_planks_blocktant", Blocks.PALE_OAK_PLANKS);
    Block BAMBOO_PLANKS_BLOCKTANT =
            registerBlocktant("bamboo_planks_blocktant", Blocks.BAMBOO_PLANKS);
    Block BAMBOO_MOSAIC_BLOCKTANT =
            registerBlocktant("bamboo_mosaic_blocktant", Blocks.BAMBOO_MOSAIC);

    Block AURON_BLOCKTANT =
            registerBlocktant("auron_blocktant", JAAVAABlocks.AURON_BLOCK);
    Block QUICKSILVER_BLOCKTANT =
            registerBlocktant("quicksilver_blocktant", JAAVAABlocks.QUICKSILVER_BLOCK);
    Block RAW_VOIDIUM_BLOCKTANT =
            registerBlocktant("raw_voidium_blocktant", JAAVAABlocks.RAW_VOIDIUM);
    Block ROSE_GOLD_BLOCKTANT =
            registerBlocktant("rose_gold_blocktant", JAAVAABlocks.ROSE_GOLD_BLOCK);
    Block SMOOTH_POLISHED_DEEPSLATE_BLOCKTANT =
            registerBlocktant("smooth_polished_deepslate_blocktant", JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE);
    Block STARSTEEL_BLOCKTANT =
            registerBlocktant("starsteel_blocktant", JAAVAABlocks.STARSTEEL_BLOCK);
    Block STEEL_BLOCKTANT =
            registerBlocktant("steel_blocktant", JAAVAABlocks.STEEL_BLOCK);

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
    private static Blocktant registerBlocktant(String path, Block parentBlock) {
        final Blocktant BLOCKTANT = (Blocktant) registerBlock(path, Blocktant::new,
                AbstractBlock.Settings.copy(parentBlock).requires());
        BLOCKTANTS.put(BLOCKTANT, parentBlock);
        return BLOCKTANT;
    }
    /**
     * Initializes all blocks.
     */
    static void init() {
        JAAVAA.log("Initializing blocks");

        //Adding Blocks to main Block ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_BLOCKS).register(entries -> {
            entries.add(ALLOY_FURNACE);
            entries.add(RECYCLING_TABLE);

            entries.add(QUICKSAND);
            entries.add(RAW_VOIDIUM);

            entries.add(AURON_BLOCK);
            entries.add(QUICKSILVER_BLOCK);
            entries.add(ROSE_GOLD_BLOCK);
            entries.add(STARSTEEL_BLOCK);
            entries.add(STEEL_BLOCK);

            entries.add(SMOOTH_POLISHED_DEEPSLATE);
            entries.add(STARSTEEL_GLASS);
            entries.add(STARSTEEL_GLASS_PANE);
        });
        //Adding Blocks to main Redstone ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_REDSTONE).register(entries -> {
            entries.add(ADJUSTABLE_REDSTONE_LAMP);
            entries.add(ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR);
            entries.add(QUARTZ_ENCASED_REDSTONE_PILLAR);
            entries.add(ADDER);
            entries.add(ADVANCED_REPEATER);
            entries.add(DECODER);
            entries.add(RANDOMIZER);
            entries.add(LOGICAL_AND_GATE);
            entries.add(LOGICAL_OR_GATE);
            entries.add(LOGICAL_XOR_GATE);
        });
        //Adding Blocks to mini Block ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_BLOCKTANTS).register(entries -> {
            entries.add(ACACIA_PLANKS_BLOCKTANT);
            entries.add(AMETHYST_BLOCKTANT);
            entries.add(ANDESITE_BLOCKTANT);
            entries.add(AURON_BLOCKTANT);
            entries.add(BAMBOO_MOSAIC_BLOCKTANT);
            entries.add(BAMBOO_PLANKS_BLOCKTANT);
            entries.add(BIRCH_PLANKS_BLOCKTANT);
            entries.add(BRICKS_BLOCKTANT);
            entries.add(CHERRY_PLANKS_BLOCKTANT);
            entries.add(COAL_BLOCKTANT);
            entries.add(COARSE_DIRT_BLOCKTANT);
            entries.add(COBBLESTONE_BLOCKTANT);
            entries.add(CRIMSON_PLANKS_BLOCKTANT);
            entries.add(DARK_OAK_PLANKS_BLOCKTANT);
            entries.add(DARK_PRISMARINE_BLOCKTANT);
            entries.add(DEEPSLATE_BLOCKTANT);
            entries.add(DEEPSLATE_TILES_BLOCKTANT);
            entries.add(DIAMOND_BLOCKTANT);
            entries.add(DIORITE_BLOCKTANT);
            entries.add(DIRT_BLOCKTANT);
            entries.add(EMERALD_BLOCKTANT);
            entries.add(END_STONE_BLOCKTANT);
            entries.add(END_STONE_BRICKS_BLOCKTANT);
            entries.add(GLOWSTONE_BLOCKTANT);
            entries.add(GOLD_BLOCKTANT);
            entries.add(GRANITE_BLOCKTANT);
            entries.add(IRON_BLOCKTANT);
            entries.add(JUNGLE_PLANKS_BLOCKTANT);
            entries.add(LAPIS_LAZULI_BLOCKTANT);
            entries.add(MANGROVE_PLANKS_BLOCKTANT);
            entries.add(NETHER_BRICK_BLOCKTANT);
            entries.add(NETHERITE_BLOCKTANT);
            entries.add(OAK_PLANKS_BLOCKTANT);
            entries.add(OBSIDIAN_BLOCKTANT);
            entries.add(PALE_OAK_PLANKS_BLOCKTANT);
            entries.add(POLISHED_ANDESITE_BLOCKTANT);
            entries.add(POLISHED_DEEPSLATE_BLOCKTANT);
            entries.add(POLISHED_DIORITE_BLOCKTANT);
            entries.add(POLISHED_GRANITE_BLOCKTANT);
            entries.add(PRISMARINE_BLOCKTANT);
            entries.add(PRISMARINE_BRICKS_BLOCKTANT);
            entries.add(PURPUR_BLOCKTANT);
            entries.add(QUARTZ_BLOCKTANT);
            entries.add(QUICKSILVER_BLOCKTANT);
            entries.add(RAW_COPPER_BLOCKTANT);
            entries.add(RAW_GOLD_BLOCKTANT);
            entries.add(RAW_IRON_BLOCKTANT);
            entries.add(RAW_VOIDIUM_BLOCKTANT);
            entries.add(REDSTONE_BLOCKTANT);
            entries.add(RED_NETHER_BRICK_BLOCKTANT);
            entries.add(RESIN_BLOCKTANT);
            entries.add(RESIN_BRICKS_BLOCKTANT);
            entries.add(ROSE_GOLD_BLOCKTANT);
            entries.add(SANDSTONE_BLOCKTANT);
            entries.add(SMOOTH_POLISHED_DEEPSLATE_BLOCKTANT);
            entries.add(SMOOTH_STONE_BLOCKTANT);
            entries.add(SPRUCE_PLANKS_BLOCKTANT);
            entries.add(STARSTEEL_BLOCKTANT);
            entries.add(STEEL_BLOCKTANT);
            entries.add(STONE_BLOCKTANT);
            entries.add(STONE_BRICKS_BLOCKTANT);
            entries.add(WARPED_PLANKS_BLOCKTANT);
        });
    }
}
