package net.gordyjack.jaavaa.item;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.gordyjack.jaavaa.mixin.*;
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
    //TODO: Add texture for fused rod. Something like a damascus blaze/breeze rod hybrid. Maybe even animate some swirling patterns.
    public static final Item FUSED_ROD = register("fused_rod",
            Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item HAMMER_IRON = register("iron_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.IRON), 9.0F, -3.6F, settings),
            new Item.Settings().maxCount(1).maxDamage(2500).component(JAAVAAComponents.Types.HAMMER_RANGE, 1));
    public static final Item HAMMER_GOLD = register("golden_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.GOLD), 9.0F, -3.6F, settings),
            new Item.Settings().maxCount(1).maxDamage(320).component(JAAVAAComponents.Types.HAMMER_RANGE, 1));
    public static final Item HAMMER_DIAMOND = register("diamond_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.DIAMOND), 9.0F, -3.6F, settings),
            new Item.Settings().maxCount(1).maxDamage(15610).component(JAAVAAComponents.Types.HAMMER_RANGE, 2));
    public static final Item HAMMER_NETHERITE = register("netherite_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.NETHERITE), 9.0F, -3.6F, settings),
            new Item.Settings().maxCount(1).maxDamage(20310).component(JAAVAAComponents.Types.HAMMER_RANGE, 3).fireproof());
    public static final Item HAMMER_STARSTEEL = register("starsteel_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterials.STARSTEEL), 9.0F, -3.6F, settings),
            new Item.Settings().maxCount(1).maxDamage(30720).rarity(Rarity.RARE).component(JAAVAAComponents.Types.HAMMER_RANGE, 4)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT));
    public static final Item MALUM_STELLAE_INCANTATAE = register("malum_stellae_incantatae",
            EternalItem::new, new Item.Settings().rarity(Rarity.EPIC)
                    .food(JAAVAAComponents.MALUM_STELLAE_INCANTATAE_FOOD, JAAVAAComponents.MALUM_STELLAE_INCANTATAE_CONSUMABLE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.literal("Malum Stellae Incantatae"),
                                    Text.literal("Lorem ipsom dolor sit amet"),
                                    Text.literal("consectetur adipiscing elit"),
                                    Text.literal("sed do eiusmod tempor incididunt")))));
    public static final Item MAGNET_IRON = register("iron_magnet",
            settings -> new SimpleMagnetItem(settings, 3, 1),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false));
    public static final Item MAGNET_GOLD = register("golden_magnet",
            settings -> new SimpleMagnetItem(settings, 6, 2),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false));
    public static final Item MAGNET_DIAMOND = register("diamond_magnet",
            settings -> new SimpleMagnetItem(settings, 9, 3),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false));
    public static final Item MAGNET_NETHERITE = register("netherite_magnet",
            settings -> new SimpleMagnetItem(settings, 15, 5),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false));
    public static final Item MOB_NET_WOOD = register("bamboo_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.WOOD),
            new Item.Settings().maxDamage(59).maxCount(1).component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, NbtComponent.DEFAULT)));
    public static final Item MOB_NET_STONE = register("stone_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.STONE),
            new Item.Settings().maxDamage(131).maxCount(1).component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, NbtComponent.DEFAULT)));
    public static final Item MOB_NET_GOLD = register("golden_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.GOLD),
            new Item.Settings().maxDamage(32).maxCount(1).component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, NbtComponent.DEFAULT)));
    public static final Item MOB_NET_IRON = register("iron_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.IRON),
            new Item.Settings().maxDamage(250).maxCount(1).component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, NbtComponent.DEFAULT)));
    public static final Item MOB_NET_DIAMOND = register("diamond_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.DIAMOND),
            new Item.Settings().maxDamage(1561).maxCount(1).component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, NbtComponent.DEFAULT)));
    public static final Item MOB_NET_NETHERITE = register("netherite_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.NETHERITE),
            new Item.Settings().maxDamage(2031).maxCount(1).fireproof().component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, NbtComponent.DEFAULT)));
    public static final Item SHULKER_PEARL = register("shulker_pearl",
            Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item STARSTEEL_INGOT = register("starsteel_ingot",
            EternalItem::new, STARSTEEL_DEFAULT_SETTINGS);
    public static final Item STARSTEEL_NUGGET = register("starsteel_nugget",
            EternalItem::new, STARSTEEL_DEFAULT_SETTINGS);
    //TODO: Add Texture \/ Copy from Netherite sword, change blade color, add sparkle effect. Create broken texture as well that has no animation.
    public static final Item STARSTEEL_SWORD = register("starsteel_sword",
            settings -> new Item(settings.sword(ToolMaterials.STARSTEEL, 3, -2.4f)),
            new Item.Settings().rarity(Rarity.RARE).maxCount(1)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT));
    public static final Item STARSTEEL_UPGRADE_SMITHING_TEMPLATE = register("starsteel_upgrade_smithing_template",
            JAAVAAItems::createStarsteelUpgradeSmithingTemplate,
            new Item.Settings().rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT));
    //TODO: Add Starsteel weapons and items. Or make MALUM_STELLAE_INCANTATAE act like an enchanted book and give any tool/weapon/armor it's rarity and DamageResistantComponent and make it eternal. Should be able to be done by adding a custom enchantment.
    public static final Item TOOL_OF_THE_ANCIENTS = register("tool_of_the_ancients",
            settings -> new PaxelItem(ToolMaterial.NETHERITE, 5.0f, -1.5f, settings),
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxCount(1).maxDamage(3072));
    //TODO: Add Texture \/
    public static final Item STARSTEEL_TOOL_OF_THE_ANCIENTS = register("starsteel_tool_of_the_ancients",
            settings -> new PaxelItem(ToolMaterials.STARSTEEL, 5.0f, -1.5f, settings),
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxCount(1).maxDamage(4096)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT));

    //Methods
    private static SmithingTemplateItem createStarsteelUpgradeSmithingTemplate(Item.Settings settings) {
        return new SmithingTemplateItem(
                Text.translatable(Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.applies_to"))).formatted(Formatting.BLUE),
                Text.translatable(Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.ingredients"))).formatted(Formatting.BLUE),
                Text.translatable(Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.base_slot_description"))),
                Text.translatable(Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.additions_slot_description"))),
                SmithingTemplateItemAccessor.getNetheriteUpgradeEmptyBaseSlotTextures(),
                SmithingTemplateItemAccessor.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
                settings);
    }
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
        ToolMaterials.init();

        //Adding Items to main Item ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_ITEMS).register(entries -> {
            entries.add(MALUM_STELLAE_INCANTATAE);
            entries.add(ALLAY_ESSENCE);
            entries.add(FUSED_ROD);
            entries.add(SHULKER_PEARL);
            entries.add(STARSTEEL_INGOT);
            entries.add(STARSTEEL_NUGGET);
            entries.add(STARSTEEL_SWORD);
            entries.add(STARSTEEL_UPGRADE_SMITHING_TEMPLATE);
            entries.add(TOOL_OF_THE_ANCIENTS);
            entries.add(STARSTEEL_TOOL_OF_THE_ANCIENTS);
            entries.add(HAMMER_IRON);
            entries.add(HAMMER_GOLD);
            entries.add(HAMMER_DIAMOND);
            entries.add(HAMMER_NETHERITE);
            entries.add(HAMMER_STARSTEEL);
            entries.add(MAGNET_IRON);
            entries.add(MAGNET_GOLD);
            entries.add(MAGNET_DIAMOND);
            entries.add(MAGNET_NETHERITE);
            entries.add(MOB_NET_WOOD);
            entries.add(MOB_NET_STONE);
            entries.add(MOB_NET_GOLD);
            entries.add(MOB_NET_IRON);
            entries.add(MOB_NET_DIAMOND);
            entries.add(MOB_NET_NETHERITE);
        });
        //Adding Items to Vanilla ItemGroups
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.NETHERITE_SWORD, STARSTEEL_SWORD);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.ENCHANTED_GOLDEN_APPLE, MALUM_STELLAE_INCANTATAE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.SHULKER_SHELL, SHULKER_PEARL);
            entries.addAfter(SHULKER_PEARL, ALLAY_ESSENCE);
            entries.addAfter(Items.NETHERITE_INGOT, STARSTEEL_INGOT);
            entries.addAfter(Items.GOLD_NUGGET, STARSTEEL_NUGGET);
            entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, STARSTEEL_UPGRADE_SMITHING_TEMPLATE);
            entries.add(FUSED_ROD);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.NETHERITE_HOE, TOOL_OF_THE_ANCIENTS);
            entries.addAfter(TOOL_OF_THE_ANCIENTS, STARSTEEL_TOOL_OF_THE_ANCIENTS);
            entries.add(HAMMER_IRON);
            entries.add(HAMMER_GOLD);
            entries.add(HAMMER_DIAMOND);
            entries.add(HAMMER_NETHERITE);
            entries.add(HAMMER_STARSTEEL);
            entries.add(MAGNET_IRON);
            entries.add(MAGNET_GOLD);
            entries.add(MAGNET_DIAMOND);
            entries.add(MAGNET_NETHERITE);
            entries.add(MOB_NET_WOOD);
            entries.add(MOB_NET_STONE);
            entries.add(MOB_NET_GOLD);
            entries.add(MOB_NET_IRON);
            entries.add(MOB_NET_DIAMOND);
            entries.add(MOB_NET_NETHERITE);
        });
    }

    public static class ToolMaterials {
        public static final ToolMaterial STARSTEEL = new ToolMaterial(
                JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL,
                3072, 12.0f, 5.0f, 20,
                JAAVAATags.Items.STARSTEEL_TOOL_MATERIALS
        );
        public static final ToolMaterial VOIDIUM = new ToolMaterial(
                JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL,
                8192, 24, 10.0f, 30,
                JAAVAATags.Items.VOIDIUM_TOOL_MATERIALS
        );
        public static ToolMaterial getModifiedDurability(ToolMaterial baseMaterial, int newDurability) {
            return new ToolMaterial(
                    baseMaterial.incorrectBlocksForDrops(),
                    newDurability,
                    baseMaterial.speed(),
                    baseMaterial.attackDamageBonus(),
                    baseMaterial.enchantmentValue(),
                    baseMaterial.repairItems()
            );
        }
        public static ToolMaterial getModifiedSpeed(ToolMaterial baseMaterial, float newSpeed) {
            return new ToolMaterial(
                    baseMaterial.incorrectBlocksForDrops(),
                    baseMaterial.durability(),
                    newSpeed,
                    baseMaterial.attackDamageBonus(),
                    baseMaterial.enchantmentValue(),
                    baseMaterial.repairItems()
            );
        }
        public static ToolMaterial getHammerMaterial(ToolMaterial baseMaterial) {
            return getModifiedDurability(getModifiedSpeed(baseMaterial, baseMaterial.speed() * 0.75f), (int) (baseMaterial.durability() * 7.5));
        }
        public static void init() {
            JAAVAA.log("Initializing tool materials");
        }
    }
}
