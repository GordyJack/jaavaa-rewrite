package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;

import java.util.concurrent.*;

public class JAAVAAItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public JAAVAAItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(JAAVAATags.Items.DEEPSLATE_CRAFTABLES).add(
                Items.DEEPSLATE,
                Items.POLISHED_DEEPSLATE,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE.asItem()
        );
        getOrCreateTagBuilder(JAAVAATags.Items.PAXELS).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(JAAVAATags.Items.RECYCLABLE)
                .addOptionalTag(ItemTags.AXES)
                .addOptionalTag(ItemTags.HOES)
                .addOptionalTag(ItemTags.PICKAXES)
                .addOptionalTag(ItemTags.SHOVELS)
                .addOptionalTag(ItemTags.SWORDS)
                .addOptionalTag(ItemTags.CHEST_ARMOR)
                .addOptionalTag(ItemTags.LEG_ARMOR)
                .addOptionalTag(ItemTags.FOOT_ARMOR)
                .addOptionalTag(ItemTags.HEAD_ARMOR).add(
                        Items.ELYTRA,
                        Items.MACE,
                        Items.TRIDENT
                );
        getOrCreateTagBuilder(JAAVAATags.Items.STARSTEEL_TOOL_MATERIALS).add(
                JAAVAAItems.STARSTEEL_INGOT
        );
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_STARSTEEL).add(
                JAAVAAItems.STARSTEEL_SWORD,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(JAAVAATags.Items.VOIDIUM_TOOL_MATERIALS);
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_VOIDIUM);
        //Common Tags
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_WOODEN).add(
                Items.WOODEN_AXE,
                Items.WOODEN_HOE,
                Items.WOODEN_PICKAXE,
                Items.WOODEN_SHOVEL,
                Items.WOODEN_SWORD
        );
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_STONE).add(
                Items.STONE_AXE,
                Items.STONE_HOE,
                Items.STONE_PICKAXE,
                Items.STONE_SHOVEL,
                Items.STONE_SWORD
        );
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_GOLD).add(
                Items.GOLDEN_AXE,
                Items.GOLDEN_HOE,
                Items.GOLDEN_PICKAXE,
                Items.GOLDEN_SHOVEL,
                Items.GOLDEN_SWORD
        );
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_IRON).add(
                Items.IRON_AXE,
                Items.IRON_HOE,
                Items.IRON_PICKAXE,
                Items.IRON_SHOVEL,
                Items.IRON_SWORD
        );
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_DIAMOND).add(
                Items.DIAMOND_AXE,
                Items.DIAMOND_HOE,
                Items.DIAMOND_PICKAXE,
                Items.DIAMOND_SHOVEL,
                Items.DIAMOND_SWORD
        );
        getOrCreateTagBuilder(JAAVAATags.Items.TOOLS_NETHERITE).add(
                Items.NETHERITE_AXE,
                Items.NETHERITE_HOE,
                Items.NETHERITE_PICKAXE,
                Items.NETHERITE_SHOVEL,
                Items.NETHERITE_SWORD,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS
        );
        //Vanilla Tags
        getOrCreateTagBuilder(ItemTags.AXES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.HOES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.SWORDS).add(
                JAAVAAItems.STARSTEEL_SWORD,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.STARSTEEL_TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
        getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
    }
}
