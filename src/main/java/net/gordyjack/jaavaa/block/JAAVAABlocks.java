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
    public static final Item.Settings STARSTEEL_DEFAULT_SETTINGS =
            new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT);

    // Blocks
    public static final Block ALLOY_FURNACE = registerBlock("alloy_furnace",
            AlloyFurnaceBlock::new, AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AlloyFurnaceBlock.LIT) ? 13 : 0)
                    .sounds(BlockSoundGroup.STONE)
                    .strength(3.5F),
            new Item.Settings().fireproof());
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
