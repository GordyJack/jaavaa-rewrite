package net.gordyjack.jaavaa.item;

import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;

import java.util.*;

public class JAAVAAItemGroups {
    public static final ArrayList<RegistryKey<ItemGroup>> ITEM_GROUPS = new ArrayList<>();
    private static int currentColumn = 0;

    //ItemGroups
    public static final RegistryKey<ItemGroup> JAAVAA_BLOCKS =
            registerItemGroup("jaavaa_blocks", JAAVAABlocks.STARSTEEL_BLOCK);
    public static final RegistryKey<ItemGroup> JAAVAA_ITEMS =
            registerItemGroup("jaavaa_items", JAAVAAItems.MALUM_STELLAE_INCANTATAE);
    public static final RegistryKey<ItemGroup> JAAVAA_MINI_BLOCKS =
            registerItemGroup("jaavaa_mini_blocks", JAAVAABlocks.STONE_MINI_BLOCK);
    public static final RegistryKey<ItemGroup> JAAVAA_REDSTONE =
            registerItemGroup("jaavaa_redstone", JAAVAABlocks.DECODER);

    //Methods
    /**
     * Registers an ItemGroup with the given name and icon.
     * @param name The name of the ItemGroup
     * @param icon The icon of the ItemGroup
     * @return The registered ItemGroup
     */
    private static RegistryKey<ItemGroup> registerItemGroup(String name, ItemConvertible icon) {
        RegistryKey<ItemGroup> returnKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, JAAVAA.id(name));
        Registry.register(Registries.ITEM_GROUP, returnKey,
                ItemGroup.create(ItemGroup.Row.TOP, currentColumn++)
                        .displayName(Text.translatable("itemGroup.jaavaa." + name))
                        .icon(() -> new ItemStack(icon)).build());
        ITEM_GROUPS.add(returnKey);
        return returnKey;
    }
}
