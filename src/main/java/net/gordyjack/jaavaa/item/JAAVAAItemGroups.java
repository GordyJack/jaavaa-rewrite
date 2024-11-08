package net.gordyjack.jaavaa.item;

import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;

import java.util.*;

public class JAAVAAItemGroups {
    public static final ArrayList<RegistryKey<ItemGroup>> ITEM_GROUPS = new ArrayList<>();
    public static final RegistryKey<ItemGroup> JAAVAA_BLOCKS = registerItemGroup("jaavaa_blocks", JAAVAABlocks.STARSTEEL_BLOCK);
    public static final RegistryKey<ItemGroup> JAAVAA_ITEMS = registerItemGroup("jaavaa_items", JAAVAAItems.STARSTEEL_INGOT);
    
    private static int currentColumn = 0;
    private static RegistryKey<ItemGroup> registerItemGroup(String name, ItemConvertible icon) {
        var returnKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, JAAVAA.id(name));
        Registry.register(Registries.ITEM_GROUP, returnKey,
                ItemGroup.create(ItemGroup.Row.TOP, currentColumn++)
                        .displayName(Text.translatable("itemGroup.jaavaa." + name))
                        .icon(() -> new ItemStack(icon)).build());
        ITEM_GROUPS.add(returnKey);
        return returnKey;
    }
}
