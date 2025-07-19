package net.gordyjack.jaavaa.data;

import net.gordyjack.jaavaa.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.util.*;
import net.minecraft.world.gen.structure.*;

public class JAAVAATags {
    public static class Blocks {
        public static final TagKey<Block> BLOCKTANTS = createTag("blocktants");
        public static final TagKey<Block> CAPRICIOUS_BLOCKS = createTag("capricious_blocks");
        public static final TagKey<Block> DEEPSLATE_CRAFTABLES = createTag("deepslate_craftables");
        public static final TagKey<Block> INCORRECT_FOR_STARSTEEL_TOOL = createTag("incorrect_for_starsteel_tool");
        public static final TagKey<Block> INCORRECT_FOR_VOIDIUM_TOOL = createTag("incorrect_for_voidium_tool");
        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, JAAVAA.id(name));
        }
    }
    public static class Entity {
        public static final TagKey<EntityType<?>> CAPTURABLE_MOBS_WOOD = createTag("caputrable_mobs_wood");
        public static final TagKey<EntityType<?>> CAPTURABLE_MOBS_STONE = createTag("capturable_mobs_stone");
        public static final TagKey<EntityType<?>> CAPTURABLE_MOBS_IRON = createTag("capturable_mobs_iron");
        public static final TagKey<EntityType<?>> CAPTURABLE_MOBS_DIAMOND = createTag("capturable_mobs_diamond");
        public static final TagKey<EntityType<?>> CAPTURABLE_MOBS_NETHERITE = createTag("capturable_mobs_netherite");
        public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE_MOBS = createTag("quicksand_walkable_mobs");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, JAAVAA.id(name));
        }
    }
    public static class Items {
        //Mod Tags
        //Biome Attunement Tags
        public static final TagKey<Item> ATTUNEABLE_ITEMS_BADLANDS = createTag("attuneable/badlands");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_BIRCH_FOREST = createTag("attuneable/birch_forest");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_CHERRY_GROVE = createTag("attuneable/cherry_grove");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_DARK_FOREST = createTag("attuneable/dark_forest");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_DEEP_DARK = createTag("attuneable/deep_dark");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_DESERT = createTag("attuneable/desert");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_DRIPSTONE_CAVES = createTag("attuneable/dripstone_caves");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_FLOWER_FOREST = createTag("attuneable/flower_forest");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_JUNGLE = createTag("attuneable/jungle");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_LUSH_CAVES = createTag("attuneable/lush_caves");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_PALE_GARDEN = createTag("attuneable/pale_garden");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_PLAINS = createTag("attuneable/plains");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_MANGROVE_SWAMP = createTag("attuneable/mangrove_swamp");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_MUSHROOM_FIELDS = createTag("attuneable/mushroom_fields");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_SAVANNA = createTag("attuneable/savanna");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_TAIGA = createTag("attuneable/taiga");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_WARM_OCEAN = createTag("attuneable/warm_ocean");
        //Structure Attunement Tags
        public static final TagKey<Item> ATTUNEABLE_ITEMS_ANCIENT_CITY = createTag("attuneable/ancient_city");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_BASTION = createTag("attuneable/bastion");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_END_CITY = createTag("attuneable/end_city");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_OCEAN_MONUMENT = createTag("attuneable/ocean_monument");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_PILLAGER_OUTPOST = createTag("attuneable/pillager_outpost");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_RUINED_PORTAL = createTag("attuneable/ruined_portal");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_TRIAL_CHAMBER = createTag("attuneable/trial_chamber");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_WITCH_HUT = createTag("attuneable/witch_hut");
        public static final TagKey<Item> ATTUNEABLE_ITEMS_WOODLAND_MANSION = createTag("attuneable/woodland_mansion");
        //Other Mod Tags
        public static final TagKey<Item> DEEPSLATE_CRAFTABLES = createTag("deepslate_craftables");
        public static final TagKey<Item> HAMMERS = createTag("hammers");
        public static final TagKey<Item> MAGNETS = createTag("magnets");
        public static final TagKey<Item> MOB_NETS = createTag("mob_nets");
        public static final TagKey<Item> PAXELS = createTag("paxels");
        public static final TagKey<Item> STARSTEEL_TOOL_MATERIALS = createTag("starsteel_tool_materials");
        public static final TagKey<Item> VOIDIUM_TOOL_MATERIALS = createTag("voidium_tool_materials");
        public static final TagKey<Item> TOOLS_STARSTEEL = createTag("tools/starsteel");
        public static final TagKey<Item> TOOLS_VOIDIUM = createTag("tools/voidium");
        //Common tags
        public static final TagKey<Item> TOOLS_WOODEN = createTagOf("c", "tools/wooden");
        public static final TagKey<Item> TOOLS_STONE = createTagOf("c", "tools/stone");
        public static final TagKey<Item> TOOLS_GOLD = createTagOf("c", "tools/gold");
        public static final TagKey<Item> TOOLS_IRON = createTagOf("c", "tools/iron");
        public static final TagKey<Item> TOOLS_DIAMOND = createTagOf("c", "tools/diamond");
        public static final TagKey<Item> TOOLS_NETHERITE = createTagOf("c", "tools/netherite");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, JAAVAA.id(name));
        }
        public static final TagKey<Item> createTagOf(String namespace, String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, name));
        }
    }
    public static class Structures {
        public static final TagKey<Structure> ANCIENT_CITIES = createTag("ancient_cities");
        public static final TagKey<Structure> BASTIONS = createTag("bastions");
        public static final TagKey<Structure> DESERT_PYRAMIDS = createTag("desert_pyramids");
        public static final TagKey<Structure> END_CITIES = createTag("end_cities");
        public static final TagKey<Structure> IGLOOS = createTag("igloos");
        public static final TagKey<Structure> JUNGLE_TEMPLES = createTag("jungle_temples");
        public static final TagKey<Structure> NETHER_FORTRESSES = createTag("nether_fortresses");
        public static final TagKey<Structure> NETHER_FOSSILS = createTag("nether_fossils");
        public static final TagKey<Structure> OCEAN_MONUMENTS = createTag("ocean_monuments");
        public static final TagKey<Structure> PILLAGER_OUTPOSTS = createTag("pillager_outposts");
        public static final TagKey<Structure> RUINS = createTag("ruins");
        public static final TagKey<Structure> STRONGHOLDS = createTag("strongholds");
        public static final TagKey<Structure> TRIAL_CHAMBERS = createTag("trial_chambers");
        public static final TagKey<Structure> WITCH_HUTS = createTag("witch_huts");
        public static final TagKey<Structure> WOODLAND_MANSIONS = createTag("woodland_mansions");

        private static TagKey<Structure> createTag(String name) {
            return TagKey.of(RegistryKeys.STRUCTURE, JAAVAA.id(name));
        }
    }
    public static class Other {
        public static final TagKey<DamageType> IS_EXPLOSION_OR_FIRE =
                TagKey.of(RegistryKeys.DAMAGE_TYPE, JAAVAA.id("is_explosion_or_fire"));
        public static final TagKey<Enchantment> UNBRIDLED_DESTRUCTION_EXCLUSIVE_SET =
                TagKey.of(RegistryKeys.ENCHANTMENT, JAAVAA.id("exclusive_set/unbridled_destruction"));
    }
}
