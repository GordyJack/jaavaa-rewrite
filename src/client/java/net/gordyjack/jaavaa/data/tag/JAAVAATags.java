package net.gordyjack.jaavaa.data.tag;

import net.gordyjack.jaavaa.JAAVAA;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class JAAVAATags {
    public static class Blocks {
        public static final TagKey<Block> DEEPSLATE_CRAFTABLES = createTag("deepslate_craftables");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, JAAVAA.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DEEPSLATE_CRAFTABLES = createTag("deepslate_craftables");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, JAAVAA.id(name));
        }
    }
}
