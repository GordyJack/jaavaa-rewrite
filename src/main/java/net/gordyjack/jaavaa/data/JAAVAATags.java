package net.gordyjack.jaavaa.data;

import net.gordyjack.jaavaa.JAAVAA;
import net.minecraft.block.Block;
import net.minecraft.entity.damage.*;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class JAAVAATags {
    public static class Blocks {
        public static final TagKey<Block> CAPRICIOUS_BLOCKS = createTag("capricious_blocks");
        public static final TagKey<Block> DEEPSLATE_CRAFTABLES = createTag("deepslate_craftables");
        public static final TagKey<Block> PAXEL_MINEABLE = createTag("paxel_mineable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, JAAVAA.id(name));
        }
    }
    public static class Items {
        public static final TagKey<Item> DEEPSLATE_CRAFTABLES = createTag("deepslate_craftables");
        public static final TagKey<Item> RECYCLABLE = createTag("recyclable");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, JAAVAA.id(name));
        }
    }
    public static class Other {
        public static final TagKey<DamageType> IS_EXPLOSION_OR_FIRE = TagKey.of(RegistryKeys.DAMAGE_TYPE, JAAVAA.id("is_explosion_or_fire"));
    }
}
