package net.gordyjack.jaavaa.item;

import com.ibm.icu.impl.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

import java.util.*;
import java.util.function.*;

public class JAAVAAItems {
    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static final Item ALLAY_ESSENCE = register("allay_essence", Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item STARSTEEL_INGOT = register("starsteel_ingot", Item::new, new Item.Settings().rarity(Rarity.RARE));
    public static final Item STARSTEEL_NUGGET = register("starsteel_nugget", Item::new, new Item.Settings().rarity(Rarity.RARE));

    public static final Item ADDER_ITEM = register("adder", settings -> new BlockItem(JAAVAABlocks.ADDER_BLOCK, settings));
    public static final Item DECODER_ITEM = register("decoder", settings -> new BlockItem(JAAVAABlocks.DECODER_BLOCK, settings));
    
    private static Item register(String path) {
        return register(path, Item::new, new Item.Settings());
    }
    private static Item register(String path, Function<Item.Settings, Item> factory) {
        return register(path, factory, new Item.Settings());
    }
    private static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = Items.register(RegistryKey.of(RegistryKeys.ITEM, JAAVAA.id(path)), factory, settings);
        ITEMS.add(item);
        return item;
    }
    public static void init() {
        JAAVAA.log("Initializing items");
        
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_ITEMS).register(entries -> {
            entries.add(ALLAY_ESSENCE);
            entries.add(STARSTEEL_INGOT);
            entries.add(STARSTEEL_NUGGET);
        });
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_BLOCKS).register(entries -> {
            entries.add(DECODER_ITEM);
        });
    }
}
