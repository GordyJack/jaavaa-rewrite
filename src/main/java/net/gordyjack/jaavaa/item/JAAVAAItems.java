package net.gordyjack.jaavaa.item;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.gordyjack.jaavaa.mixin.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
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
    Item FUSED_ROD = register("fused_rod");
    Item QUICKSILVER_INGOT = register("quicksilver_ingot");
    Item QUICKSILVER_NUGGET = register("quicksilver_nugget");
    Item ROSE_GOLD_INGOT = register("rose_gold_ingot");
    Item SHULKER_PEARL = register("shulker_pearl");
    Item STARSTEEL_INGOT = register("starsteel_ingot",
            EternalItem::new, new Item.Settings().rarity(Rarity.UNCOMMON)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    Item STARSTEEL_NUGGET = register("starsteel_nugget",
            EternalItem::new, new Item.Settings().rarity(Rarity.UNCOMMON)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    //Misc
    Item STARSTEEL_UPGRADE_SMITHING_TEMPLATE = register("starsteel_upgrade_smithing_template",
            JAAVAAItems::createStarsteelUpgradeSmithingTemplate,
            new Item.Settings().rarity(Rarity.RARE)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    //Tools
    //Vanilla-like Tools
    //TODO add textures for Auron tools and add auron armor.
    Item AURON_SWORD = register("auron_sword",
            Item::new,
            new Item.Settings().sword(ToolMaterials.AURON, 3.0f, -2.4f)
    );
    Item AURON_SHOVEL = register("auron_shovel",
            settings -> new ShovelItem(ToolMaterials.AURON, 1.5F, -3.0F, settings)
    );
    Item AURON_PICKAXE = register("auron_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(ToolMaterials.AURON, 1.0f, -2.8f)
    );
    Item AURON_AXE = register("auron_axe",
            settings -> new AxeItem(ToolMaterials.AURON, 6.0f, -3.0f, settings)
    );
    Item AURON_HOE = register("auron_hoe",
            settings -> new HoeItem(ToolMaterials.AURON, -2.0f, -1.0f, settings)
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
    Item HAPPY_GHAST_HARNESS = register("happy_ghast_harness", //TODO: Add custom model.
            Item::new,
            new Item.Settings()
                    .maxDamage(1024)
                    .rarity(Rarity.EPIC)
                    .component(JAAVAAComponents.Types.FLYER, Unit.INSTANCE)
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
    Item HAMMER_IRON = register("iron_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.IRON), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_AURON = register("auron_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterials.AURON), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_GOLD = register("golden_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.GOLD), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 1)
    );
    Item HAMMER_DIAMOND = register("diamond_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.DIAMOND), settings),
            new Item.Settings().component(JAAVAAComponents.Types.HAMMER_RANGE, 2)
    );
    Item HAMMER_NETHERITE = register("netherite_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterial.NETHERITE), settings),
            new Item.Settings().fireproof().component(JAAVAAComponents.Types.HAMMER_RANGE, 3)
    );
    Item HAMMER_STARSTEEL = register("starsteel_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterials.STARSTEEL), settings),
            new Item.Settings().rarity(Rarity.RARE).component(JAAVAAComponents.Types.HAMMER_RANGE, 4)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    Item HAMMER_VOIDIUM = register("voidium_hammer",
            settings -> new HammerItem(ToolMaterials.getHammerMaterial(ToolMaterials.VOIDIUM), settings),
            new Item.Settings().rarity(Rarity.RARE).component(JAAVAAComponents.Types.HAMMER_RANGE, 5)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );

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
    //TODO: Add Texture \/ Copy from Netherite sword, change blade color, add sparkle effect. Create broken texture as well that has no animation.
    Item STARSTEEL_SWORD = register("starsteel_sword",
            settings -> new Item(settings.sword(ToolMaterials.STARSTEEL, 3, -2.4f)),
            new Item.Settings().rarity(Rarity.RARE).maxCount(1)
                    .component(DataComponentTypes.DAMAGE_RESISTANT, JAAVAAComponents.FIRE_AND_EXPLOSION_RESISTANT)
    );
    //TODO: Add Starsteel weapons and items. Or make MALUM_STELLAE_INCANTATAE act like an enchanted book and give any tool/weapon/armor it's rarity and DamageResistantComponent and make it eternal. Should be able to be done by adding a custom enchantment.
    Item TOOL_OF_THE_ANCIENTS = register("tool_of_the_ancients",
            settings -> new PaxelItem(ToolMaterial.NETHERITE, 5.0f, -1.5f, settings),
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxCount(1).maxDamage(3072)
    );
    //TODO: Add Texture \/
    Item TOOL_OF_THE_ANCIENTS_STARSTEEL = register("starsteel_tool_of_the_ancients",
            settings -> new PaxelItem(ToolMaterials.STARSTEEL, 5.0f, -1.5f, settings),
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
        ToolMaterials.init();

        //Adding Items to main Item ItemGroup
        ItemGroupEvents.modifyEntriesEvent(JAAVAAItemGroups.JAAVAA_ITEMS).register(entries -> {
            entries.add(MALUM_STELLAE_INCANTATAE);
            entries.add(ALLAY_ESSENCE);
            entries.add(ARCHITECTS_COMPASS);
            entries.add(AURON_INGOT);
            entries.add(AURON_NUGGET);
            entries.add(BIOME_COMPASS);
            entries.add(FUSED_ROD);
            entries.add(HAPPY_GHAST_HARNESS);
            entries.add(HAMMER_IRON);
            entries.add(HAMMER_AURON);
            entries.add(HAMMER_GOLD);
            entries.add(HAMMER_DIAMOND);
            entries.add(HAMMER_NETHERITE);
            entries.add(HAMMER_STARSTEEL);
            entries.add(HAMMER_VOIDIUM);
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
            entries.add(QUICKSILVER_INGOT);
            entries.add(QUICKSILVER_NUGGET);
            entries.add(ROSE_GOLD_INGOT);
            entries.add(SHULKER_PEARL);
            entries.add(STARSTEEL_INGOT);
            entries.add(STARSTEEL_NUGGET);
            entries.add(STARSTEEL_SWORD);
            entries.add(STARSTEEL_UPGRADE_SMITHING_TEMPLATE);
            entries.add(TOOL_OF_THE_ANCIENTS);
            entries.add(TOOL_OF_THE_ANCIENTS_STARSTEEL);

            entries.add(AURON_SWORD);
            entries.add(AURON_SHOVEL);
            entries.add(AURON_PICKAXE);
            entries.add(AURON_AXE);
            entries.add(AURON_HOE);
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
            entries.add(AURON_INGOT);
            entries.add(AURON_NUGGET);
            entries.add(FUSED_ROD);
            entries.add(ROSE_GOLD_INGOT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.NETHERITE_HOE, TOOL_OF_THE_ANCIENTS);
            entries.addAfter(TOOL_OF_THE_ANCIENTS, TOOL_OF_THE_ANCIENTS_STARSTEEL);
            entries.add(HAMMER_IRON);
            entries.add(HAMMER_AURON);
            entries.add(HAMMER_GOLD);
            entries.add(HAMMER_DIAMOND);
            entries.add(HAMMER_NETHERITE);
            entries.add(HAMMER_STARSTEEL);
            entries.add(HAMMER_VOIDIUM);
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

    interface ToolMaterials {
        ToolMaterial AURON = new ToolMaterial(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                384, 8.0f, 1.0f, 20,
                JAAVAATags.Items.AURON_TOOL_MATERIALS
        );
        ToolMaterial STARSTEEL = new ToolMaterial(
                JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL,
                3072, 12.0f, 5.0f, 20,
                JAAVAATags.Items.STARSTEEL_TOOL_MATERIALS
        );
        ToolMaterial VOIDIUM = new ToolMaterial(
                JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL,
                8192, 24, 10.0f, 30,
                JAAVAATags.Items.VOIDIUM_TOOL_MATERIALS
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
        static void init() {
            JAAVAA.log("Initializing tool materials");
        }
    }
}
