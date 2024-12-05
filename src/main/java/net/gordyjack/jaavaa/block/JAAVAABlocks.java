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

    //TODO: Add textures and states for ADDER_BLOCK and DECODER_BLOCK
    public static final Block ADDER_BLOCK = register("adder", AdderBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER), Rarity.COMMON, false);
    public static final Block ADJUSTABLE_REDSTONE_LAMP = register("adjustable_redstone_lamp", AdjustableRedstoneLampBlock::new,
            AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AdjustableRedstoneLampBlock.LUMINANCE))
                    .sounds(BlockSoundGroup.GLASS)
                    .strength(0.3F));
    public static final Block ALLOY_FURNACE = register("alloy_furnace", AlloyFurnaceBlock::new,
            AbstractBlock.Settings.create()
                    .luminance(state -> state.get(AlloyFurnaceBlock.LIT) ? 13 : 0)
                    .sounds(BlockSoundGroup.STONE)
                    .strength(3.5F));
    public static final Block ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR = register("ancient_debris_encased_redstone_pillar", EncasedRedstoneBlock::new,
            AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS));
    public static final Block DECODER_BLOCK = register("decoder", DecoderBlock::new,
            AbstractBlock.Settings.copy(Blocks.REPEATER), Rarity.COMMON, false);
    public static final Block EXAMPLE_BLOCK = register("test_block", Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(0.8F));
    public static final Block SMOOTH_POLISHED_DEEPSLATE = register("smooth_polished_deepslate", Block::new,
            AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE));
    public static final Block STARSTEEL_BLOCK = register("starsteel_block", Block::new,
            AbstractBlock.Settings.create()
                    .allowsSpawning(Blocks::never)
                    .luminance(state -> 2)
                    .mapColor(MapColor.WHITE_GRAY)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .strength(50.0F, 1200.0F),
            Rarity.RARE);
    public static final Block QUARTZ_ENCASED_REDSTONE_PILLAR = register("quartz_encased_redstone_pillar", EncasedRedstoneBlock::new,
            AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK));
    
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register(path, factory, settings, Rarity.COMMON, true);
    }
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings,
                                  Rarity rarity) {
        return register(path, factory, settings, rarity, true);
    }
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings,
                                  Rarity rarity, boolean shouldHaveItem) {
        final Block block = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, JAAVAA.id(path)), factory, settings);
        if (shouldHaveItem) {
            Items.register(block, new Item.Settings().rarity(rarity));
        }
        BLOCKS.add(block);
        return block;
    }
    public static void init() {
        JAAVAA.log("Initializing blocks");
        
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_BLOCKS).register(entries -> {
            entries.add(EXAMPLE_BLOCK);
            entries.add(STARSTEEL_BLOCK);
            entries.add(ADJUSTABLE_REDSTONE_LAMP);
            entries.add(ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR);
            entries.add(QUARTZ_ENCASED_REDSTONE_PILLAR);
        });
    }
}
