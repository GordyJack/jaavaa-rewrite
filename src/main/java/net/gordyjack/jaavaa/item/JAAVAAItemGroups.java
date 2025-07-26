package net.gordyjack.jaavaa.item;

import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;

import java.util.*;

public final class JAAVAAItemGroups {
    public static final ArrayList<RegistryKey<ItemGroup>> ITEM_GROUPS = new ArrayList<>();

    //ItemGroups
    public static final RegistryKey<ItemGroup> JAAVAA_BLOCKS =
            registerItemGroup("jaavaa_blocks", JAAVAABlocks.STARSTEEL_BLOCK, 0);
    public static final RegistryKey<ItemGroup> JAAVAA_ITEMS =
            registerItemGroup("jaavaa_items", JAAVAAItems.STARSTEEL_INGOT, 1);
    public static final RegistryKey<ItemGroup> JAAVAA_BLOCKTANTS =
            registerItemGroup("jaavaa_blocktants", JAAVAABlocks.STONE_BLOCKTANT, 2);
    public static final RegistryKey<ItemGroup> JAAVAA_REDSTONE =
            registerItemGroup("jaavaa_redstone", JAAVAABlocks.DECODER, 3);

    //Methods
    /**
     * Registers an ItemGroup with the given name and icon.
     * @param name The name of the ItemGroup
     * @param icon The icon of the ItemGroup
     * @return The registered ItemGroup
     */
    private static RegistryKey<ItemGroup> registerItemGroup(String name, ItemConvertible icon, int column) {
        RegistryKey<ItemGroup> returnKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, JAAVAA.id(name));
        Registry.register(Registries.ITEM_GROUP, returnKey,
                ItemGroup.create(ItemGroup.Row.TOP, column)
                        .displayName(Text.translatable("itemGroup.jaavaa." + name))
                        .icon(() -> new ItemStack(icon)).build());
        ITEM_GROUPS.add(returnKey);
        return returnKey;
    }
}
