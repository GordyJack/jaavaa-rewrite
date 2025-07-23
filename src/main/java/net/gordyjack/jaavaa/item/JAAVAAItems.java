package net.gordyjack.jaavaa.item;

import com.google.common.collect.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.gordyjack.jaavaa.mixin.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.item.equipment.*;
import net.minecraft.nbt.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.sound.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.biome.*;

import java.util.*;
import java.util.function.*;

public interface JAAVAAItems {
    ArrayList<Item> ITEMS = new ArrayList<>();
    //Items
    //Food
    Item MALUM_STELLAE_INCANTATAE = register("malum_stellae_incantatae",
            EternalItem::new, new Item.Settings().rarity(Rarity.EPIC)
                    .food(JAAVAAComponents.MALUM_STELLAE_INCANTATAE_FOOD, JAAVAAComponents.MALUM_STELLAE_INCANTATAE_CONSUMABLE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.literal("Malum Stellae Incantatae"),
                                    Text.literal("Lorem ipsom dolor sit amet"),
                                    Text.literal("consectetur adipiscing elit"),
                                    Text.literal("sed do eiusmod tempor incididunt"))))
    );
    //Materials
    Item ALLAY_ESSENCE = register("allay_essence");
    Item AURON_INGOT = register("auron_ingot");
    Item AURON_NUGGET = register("auron_nugget");
    Item CUPAUREUM_INGOT = register("cupaureum_ingot");
    Item CUPERUM_INGOT = register("cuperum_ingot");
    Item FUSED_ROD = register("fused_rod");
    Item IRON_ROD = register("iron_rod");
    Item QUICKSILVER_INGOT = register("quicksilver_ingot");
    Item QUICKSILVER_NUGGET = register("quicksilver_nugget");
    Item SHULKER_PEARL = register("shulker_pearl");
    Item STARSTEEL_INGOT = register("starsteel_ingot",
            EternalItem::new, new Item.Settings().rarity(Rarity.UNCOMMON)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    Item STARSTEEL_NUGGET = register("starsteel_nugget",
            EternalItem::new, new Item.Settings().rarity(Rarity.UNCOMMON)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    Item STEEL_INGOT = register("steel_ingot");
    Item STEEL_NUGGET = register("steel_nugget");
    //Misc
    Item STARSTEEL_UPGRADE_SMITHING_TEMPLATE = register("starsteel_upgrade_smithing_template",
            JAAVAAItems::createStarsteelUpgradeSmithingTemplate,
            new Item.Settings().rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    //Equipment/Armor
    //Vanilla-like Armor
    Item AURON_HELMET = register("auron_helmet",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.AURON, EquipmentType.HELMET)
    );
    Item AURON_CHESTPLATE = register("auron_chestplate",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.AURON, EquipmentType.CHESTPLATE)
    );
    Item AURON_LEGGINGS = register("auron_leggings",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.AURON, EquipmentType.LEGGINGS)
    );
    Item AURON_BOOTS = register("auron_boots",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.AURON, EquipmentType.BOOTS)
    );
    Item STEEL_HELMET = register("steel_helmet",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.STEEL, EquipmentType.HELMET)
    );
    Item STEEL_CHESTPLATE = register("steel_chestplate",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.STEEL, EquipmentType.CHESTPLATE)
    );
    Item STEEL_LEGGINGS = register("steel_leggings",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.STEEL, EquipmentType.LEGGINGS)
    );
    Item STEEL_BOOTS = register("steel_boots",
            Item::new,
            new Item.Settings().armor(JAAVAAArmorMaterials.STEEL, EquipmentType.BOOTS)
    );
    //Mod Equipment
    Item HAPPY_GHAST_PACK = register("happy_ghast_pack", //TODO: Add custom model.
            Item::new,
            new Item.Settings()
                    .maxDamage(1024)
                    .rarity(Rarity.EPIC)
                    .component(JAAVAAComponents.Types.FLYER, Unit.INSTANCE)
                    .component(DataComponentTypes.GLIDER, Unit.INSTANCE)
                    .component(
                            DataComponentTypes.EQUIPPABLE,
                            EquippableComponent.builder(EquipmentSlot.CHEST)
                                    .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA)
                                    .model(JAAVAAEquipmentAssetKeys.HAPPY_GHAST_HARNESS)
                                    .damageOnHurt(false)
                                    .build()
                    )
                    .repairable(STARSTEEL_NUGGET)
    );
    //Tools
    //Vanilla-like Tools
    //TODO add textures for Auron tools and add auron armor.
    Item AURON_SWORD = register("auron_sword",
            Item::new,
            new Item.Settings().sword(JAAVAAToolMaterials.AURON, 3.0f, -2.4f)
    );
    Item AURON_SHOVEL = register("auron_shovel",
            settings -> new ShovelItem(JAAVAAToolMaterials.AURON, 1.5F, -3.0F, settings)
    );
    Item AURON_PICKAXE = register("auron_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(JAAVAAToolMaterials.AURON, 1.0f, -2.8f)
    );
    Item AURON_AXE = register("auron_axe",
            settings -> new AxeItem(JAAVAAToolMaterials.AURON, 6.0f, -3.0f, settings)
    );
    Item AURON_HOE = register("auron_hoe",
            settings -> new HoeItem(JAAVAAToolMaterials.AURON, -2.0f, -1.0f, settings)
    );
    Item CUPAUREUM_SWORD = register("cupaureum_sword",
            Item::new,
            new Item.Settings().sword(JAAVAAToolMaterials.CUPAUREUM, 3.0f, -2.4f)
    );
    Item CUPAUREUM_SHOVEL = register("cupaureum_shovel",
            settings -> new ShovelItem(JAAVAAToolMaterials.CUPAUREUM, 1.5F, -3.0F, settings)
    );
    Item CUPAUREUM_PICKAXE = register("cupaureum_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(JAAVAAToolMaterials.CUPAUREUM, 1.0f, -2.8f)
    );
    Item CUPAUREUM_AXE = register("cupaureum_axe",
            settings -> new AxeItem(JAAVAAToolMaterials.CUPAUREUM, 6.0f, -3.0f, settings)
    );
    Item CUPAUREUM_HOE = register("cupaureum_hoe",
            settings -> new HoeItem(JAAVAAToolMaterials.CUPAUREUM, -2.0f, -1.0f, settings)
    );
    Item STEEL_SWORD = register("steel_sword",
            Item::new,
            new Item.Settings().sword(JAAVAAToolMaterials.STEEL, 3.0f, -2.4f)
    );
    Item STEEL_SHOVEL = register("steel_shovel",
            settings -> new ShovelItem(JAAVAAToolMaterials.STEEL, 1.5F, -3.0F, settings)
    );
    Item STEEL_PICKAXE = register("steel_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(JAAVAAToolMaterials.STEEL, 1.0f, -2.8f)
    );
    Item STEEL_AXE = register("steel_axe",
            settings -> new AxeItem(JAAVAAToolMaterials.STEEL, 6.0f, -3.0f, settings)
    );
    Item STEEL_HOE = register("steel_hoe",
            settings -> new HoeItem(JAAVAAToolMaterials.STEEL, -2.0f, -1.0f, settings)
    );
    //TODO: Add Texture \/ Copy from Netherite sword, change blade color, add sparkle effect. Create broken texture as well that has no animation.
    Item STARSTEEL_SWORD = register("starsteel_sword",
            settings -> new Item(settings.sword(JAAVAAToolMaterials.STARSTEEL, 3, -2.4f)),
            new Item.Settings().rarity(Rarity.RARE).maxCount(1)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    //Mod Tools
    Item ARCHITECTS_COMPASS = register("architects_compass",
            StructureCompassItem::new,
            new Item.Settings().maxCount(1)
                    .component(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET, StructureTags.VILLAGE)
                    .component(JAAVAAComponents.Types.COMPASS_TARGET_POSITION, null)
    );
    Item BIOME_COMPASS = register("biome_compass",
            BiomeCompassItem::new,
            new Item.Settings().maxCount(1)
                    .component(JAAVAAComponents.Types.COMPASS_BIOME_TARGET, BiomeKeys.THE_VOID)
                    .component(JAAVAAComponents.Types.COMPASS_TARGET_POSITION, null)
    );
    //Hammers
    Item HAMMER_IRON = register("iron_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(ToolMaterial.IRON), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_AURON = register("auron_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(JAAVAAToolMaterials.AURON), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_CUPAUREUM = register("cupaureum_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(JAAVAAToolMaterials.CUPAUREUM), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_CUPERUM = register("cuperum_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(JAAVAAToolMaterials.CUPERUM), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_GOLD = register("golden_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(ToolMaterial.GOLD), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_STEEL = register("steel_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(JAAVAAToolMaterials.STEEL), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 2)
    );
    Item HAMMER_DIAMOND = register("diamond_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(ToolMaterial.DIAMOND), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 2)
    );
    Item HAMMER_NETHERITE = register("netherite_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(ToolMaterial.NETHERITE), settings),
            new Item.Settings().fireproof().component(JAAVAAComponents.Types.HAMMER_RANGE, 3)
    );
    Item HAMMER_STARSTEEL = register("starsteel_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(JAAVAAToolMaterials.STARSTEEL), settings),
            new Item.Settings().rarity(Rarity.RARE).component(JAAVAAComponents.Types.HAMMER_RANGE, 4)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    Item HAMMER_VOIDIUM = register("voidium_hammer",
            settings -> new HammerItem(JAAVAAToolMaterials.getHammerMaterial(JAAVAAToolMaterials.VOIDIUM), settings),
            new Item.Settings().rarity(Rarity.RARE).component(JAAVAAComponents.Types.HAMMER_RANGE, 5)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    //Magnets
    Item MAGNET_IRON = register("iron_magnet",
            settings -> new SimpleMagnetItem(settings, 3, 1),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false)
    );
    Item MAGNET_GOLD = register("golden_magnet",
            settings -> new SimpleMagnetItem(settings, 6, 2),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false)
    );
    Item MAGNET_DIAMOND = register("diamond_magnet",
            settings -> new SimpleMagnetItem(settings, 9, 3),
            new Item.Settings().maxCount(1).component(JAAVAAComponents.Types.MAGNET_ENABLED, false)
    );
    Item MAGNET_NETHERITE = register("netherite_magnet",
            settings -> new SimpleMagnetItem(settings, 15, 5),
            new Item.Settings().maxCount(1).fireproof().component(JAAVAAComponents.Types.MAGNET_ENABLED, false)
    );
    //Mob Nets
    Item MOB_NET_WOOD = register("bamboo_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.WOOD),
            new Item.Settings().maxDamage(59).maxCount(1)
                    .component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, new NbtCompound()))
    );
    Item MOB_NET_STONE = register("stone_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.STONE),
            new Item.Settings().maxDamage(131).maxCount(1)
                    .component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, new NbtCompound()))
    );
    Item MOB_NET_GOLD = register("golden_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.GOLD),
            new Item.Settings().maxDamage(32).maxCount(1)
                    .component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, new NbtCompound()))
    );
    Item MOB_NET_IRON = register("iron_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.IRON),
            new Item.Settings().maxDamage(250).maxCount(1)
                    .component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, new NbtCompound()))
    );
    Item MOB_NET_DIAMOND = register("diamond_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.DIAMOND),
            new Item.Settings().maxDamage(1561).maxCount(1)
                    .component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, new NbtCompound()))
    );
    Item MOB_NET_NETHERITE = register("netherite_mob_net",
            settings -> new MobNetItem(settings, ToolMaterial.NETHERITE),
            new Item.Settings().maxDamage(2031).maxCount(1).fireproof()
                    .component(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(null, new NbtCompound()))
    );
    //TODO: Add Starsteel weapons and items. Or make MALUM_STELLAE_INCANTATAE act like an enchanted book and give any tool/weapon/armor it's rarity and DamageResistantComponent and make it eternal. Should be able to be done by adding a custom enchantment.
    Item TOOL_OF_THE_ANCIENTS = register("tool_of_the_ancients",
            settings -> new PaxelItem(ToolMaterial.NETHERITE, 5.0f, -1.5f, settings),
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxCount(1).maxDamage(3072)
    );
    //TODO: Add Texture \/
    Item TOOL_OF_THE_ANCIENTS_STARSTEEL = register("starsteel_tool_of_the_ancients",
            settings -> new PaxelItem(JAAVAAToolMaterials.STARSTEEL, 5.0f, -1.5f, settings),
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxCount(1).maxDamage(4096)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );

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
        return register(path, Item::new);
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
    static void init() {
        JAAVAA.log("Initializing items");

        //Adding Items to main Item ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_ITEMS).register(entries -> {
            //Food
            entries.add(MALUM_STELLAE_INCANTATAE);
            //Materials
            entries.add(ALLAY_ESSENCE);
            entries.add(AURON_INGOT);
            entries.add(AURON_NUGGET);
            entries.add(CUPAUREUM_INGOT);
            entries.add(CUPERUM_INGOT);
            entries.add(FUSED_ROD);
            entries.add(IRON_ROD);
            entries.add(QUICKSILVER_INGOT);
            entries.add(QUICKSILVER_NUGGET);
            entries.add(SHULKER_PEARL);
            entries.add(STARSTEEL_INGOT);
            entries.add(STARSTEEL_NUGGET);
            entries.add(STEEL_INGOT);
            entries.add(STEEL_NUGGET);
            //Misc
            entries.add(STARSTEEL_UPGRADE_SMITHING_TEMPLATE);
            //Equipment/Armor
            //Vanilla-like Armor
            entries.add(AURON_HELMET);
            entries.add(AURON_CHESTPLATE);
            entries.add(AURON_LEGGINGS);
            entries.add(AURON_BOOTS);
            entries.add(STEEL_HELMET);
            entries.add(STEEL_CHESTPLATE);
            entries.add(STEEL_LEGGINGS);
            entries.add(STEEL_BOOTS);
            //Mod Equipment
            entries.add(HAPPY_GHAST_PACK);
            //Tools
            //Vanilla-like Tools
            entries.add(AURON_SWORD);
            entries.add(AURON_SHOVEL);
            entries.add(AURON_PICKAXE);
            entries.add(AURON_AXE);
            entries.add(AURON_HOE);
            entries.add(CUPAUREUM_SWORD);
            entries.add(CUPAUREUM_SHOVEL);
            entries.add(CUPAUREUM_PICKAXE);
            entries.add(CUPAUREUM_AXE);
            entries.add(CUPAUREUM_HOE);
            entries.add(STEEL_SWORD);
            entries.add(STEEL_SHOVEL);
            entries.add(STEEL_PICKAXE);
            entries.add(STEEL_AXE);
            entries.add(STEEL_HOE);

            entries.add(STARSTEEL_SWORD);
            //Mod Tools
            entries.add(ARCHITECTS_COMPASS);
            entries.add(BIOME_COMPASS);
            //Hammers
            entries.add(HAMMER_IRON);
            entries.add(HAMMER_AURON);
            entries.add(HAMMER_CUPAUREUM);
            entries.add(HAMMER_CUPERUM);
            entries.add(HAMMER_GOLD);
            entries.add(HAMMER_STEEL);
            entries.add(HAMMER_DIAMOND);
            entries.add(HAMMER_NETHERITE);
            entries.add(HAMMER_STARSTEEL);
            entries.add(HAMMER_VOIDIUM);
            //Magnets
            entries.add(MAGNET_IRON);
            entries.add(MAGNET_GOLD);
            entries.add(MAGNET_DIAMOND);
            entries.add(MAGNET_NETHERITE);
            //Mob Nets
            entries.add(MOB_NET_WOOD);
            entries.add(MOB_NET_STONE);
            entries.add(MOB_NET_GOLD);
            entries.add(MOB_NET_IRON);
            entries.add(MOB_NET_DIAMOND);
            entries.add(MOB_NET_NETHERITE);
            //Tools of the Ancients
            entries.add(TOOL_OF_THE_ANCIENTS);
            entries.add(TOOL_OF_THE_ANCIENTS_STARSTEEL);
        });
    }
    interface JAAVAAArmorMaterials {
        ArmorMaterial AURON = new ArmorMaterial(
                20,
                createDefenseMap(2, 5, 6, 2, 7),
                20,
                SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                0.5f,
                0.0f,
                JAAVAATags.Items.AURON_MATERIALS,
                JAAVAAEquipmentAssetKeys.AURON
        );
        ArmorMaterial STEEL = new ArmorMaterial(
                24,
                createDefenseMap(2, 5, 6, 2, 7),
                9,
                SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                1.0F,
                0.05F,
                JAAVAATags.Items.STEEL_MATERIALS,
                JAAVAAEquipmentAssetKeys.STEEL
        );
        private static Map<EquipmentType, Integer> createDefenseMap(int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense, int bodyDefense) {
            return Maps.newEnumMap(
                    Map.of(
                            EquipmentType.BOOTS,
                            bootsDefense,
                            EquipmentType.LEGGINGS,
                            leggingsDefense,
                            EquipmentType.CHESTPLATE,
                            chestplateDefense,
                            EquipmentType.HELMET,
                            helmetDefense,
                            EquipmentType.BODY,
                            bodyDefense
                    )
            );
        }
    }
    interface JAAVAAToolMaterials {
        ToolMaterial AURON = new ToolMaterial(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                512, 8.0f, 1.0f, 20,
                JAAVAATags.Items.AURON_MATERIALS
        );
        ToolMaterial CUPAUREUM = new ToolMaterial(
                BlockTags.INCORRECT_FOR_GOLD_TOOL,
                128, 9.0f, 1.5f, 15,
                JAAVAATags.Items.CUPAUREUM_MATERIALS
        );
        ToolMaterial CUPERUM = new ToolMaterial(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                384, 7.5f, 1.0f, 15,
                JAAVAATags.Items.CUPERUM_MATERIALS
        );
        ToolMaterial STARSTEEL = new ToolMaterial(
                JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL,
                3072, 12.0f, 5.0f, 20,
                JAAVAATags.Items.STARSTEEL_MATERIALS
        );
        ToolMaterial STEEL = new ToolMaterial(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                768, 7.0f, 2.5f, 12,
                JAAVAATags.Items.STEEL_MATERIALS
        );
        ToolMaterial VOIDIUM = new ToolMaterial(
                JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL,
                8192, 24, 10.0f, 30,
                JAAVAATags.Items.VOIDIUM_MATERIALS
        );
        static ToolMaterial getModifiedDurability(ToolMaterial baseMaterial, int newDurability) {
            return new ToolMaterial(
                    baseMaterial.incorrectBlocksForDrops(),
                    newDurability,
                    baseMaterial.speed(),
                    baseMaterial.attackDamageBonus(),
                    baseMaterial.enchantmentValue(),
                    baseMaterial.repairItems()
            );
        }
        static ToolMaterial getModifiedSpeed(ToolMaterial baseMaterial, float newSpeed) {
            return new ToolMaterial(
                    baseMaterial.incorrectBlocksForDrops(),
                    baseMaterial.durability(),
                    newSpeed,
                    baseMaterial.attackDamageBonus(),
                    baseMaterial.enchantmentValue(),
                    baseMaterial.repairItems()
            );
        }
        static ToolMaterial getHammerMaterial(ToolMaterial baseMaterial) {
            return getModifiedDurability(getModifiedSpeed(baseMaterial, baseMaterial.speed() * 0.75f), (int) (baseMaterial.durability() * 7.5));
        }
    }
}
