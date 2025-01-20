package net.gordyjack.jaavaa.item;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

import java.util.*;
import java.util.function.*;

public class JAAVAAItems {
    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static final Item.Settings STARSTEEL_DEFAULT_SETTINGS =
            new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT);

    //Items
    public static final Item ALLAY_ESSENCE = register("allay_essence",
            Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item MALUM_STELLAE_INCANTATAE = register("malum_stellae_incantatae",
            EternalItem::new, new Item.Settings().rarity(Rarity.EPIC)
                    .food(JAAVAAComponents.MALUM_STELLAE_INCANTATAE_FOOD, JAAVAAComponents.MALUM_STELLAE_INCANTATAE_CONSUMABLE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.literal("Malum Stellae Incantatae"),
                                    Text.literal("Lorem ipsom dolor sit amet"),
                                    Text.literal("consectetur adipiscing elit"),
                                    Text.literal("sed do eiusmod tempor incididunt")))));
    public static final Item SHULKER_PEARL = register("shulker_pearl",
            Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item STARSTEEL_INGOT = register("starsteel_ingot",
            EternalItem::new, STARSTEEL_DEFAULT_SETTINGS);
    public static final Item STARSTEEL_NUGGET = register("starsteel_nugget",
            EternalItem::new, STARSTEEL_DEFAULT_SETTINGS);
    //TODO: Add Starsteel weapons and items. Or make MALUM_STELLAE_INCANTATAE act like an enchanted book and give any tool/weapon/armor it's rarity and DamageResistantComponent and make it eternal. Should be able to be done by adding a custom enchantment.
    public static final Item TOOL_OF_THE_ANCIENTS = register("tool_of_the_ancients",
            settings -> new PaxelItem(ToolMaterial.NETHERITE, 5.0f, -1.5f, settings),
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxCount(1).maxDamage(3000));

    //Methods
    /**
     * Registers an item with the given path and default settings.
     * @param path The path of the item
     * @return The registered item
     */
    private static Item register(String path) {
        return register(path, Item::new, new Item.Settings());
    }
    /**
     * Registers an item with the given path, factory, and default settings.
     * @param path The path of the item
     * @param factory The factory function for the item
     * @return The registered item
     */
    private static Item register(String path, Function<Item.Settings, Item> factory) {
        return register(path, factory, new Item.Settings());
    }
    /**
     * Registers an item with the given path, factory, and settings.
     * @param path The path of the item
     * @param factory The factory function for the item
     * @param settings The settings for the item
     * @return The registered item
     */
    private static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = Items.register(RegistryKey.of(RegistryKeys.ITEM, JAAVAA.id(path)), factory, settings);
        ITEMS.add(item);
        return item;
    }
    /**
     * Initializes all items.
     */
    public static void init() {
        JAAVAA.log("Initializing items");

        //Adding Items to main Item ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_ITEMS).register(entries -> {
            entries.add(MALUM_STELLAE_INCANTATAE);
            entries.add(ALLAY_ESSENCE);
            entries.add(SHULKER_PEARL);
            entries.add(STARSTEEL_INGOT);
            entries.add(STARSTEEL_NUGGET);
            entries.add(TOOL_OF_THE_ANCIENTS);
        });
    }
}
