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
        valueLookupBuilder(JAAVAATags.Items.DEEPSLATE_CRAFTABLES).add(
                Items.DEEPSLATE,
                Items.POLISHED_DEEPSLATE,
                JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE.asItem()
        );
        valueLookupBuilder(JAAVAATags.Items.HAMMERS).add(
                JAAVAAItems.HAMMER_IRON,
                JAAVAAItems.HAMMER_GOLD,
                JAAVAAItems.HAMMER_DIAMOND,
                JAAVAAItems.HAMMER_NETHERITE,
                JAAVAAItems.HAMMER_STARSTEEL
        );
        valueLookupBuilder(JAAVAATags.Items.MAGNETS).add(
                JAAVAAItems.MAGNET_IRON,
                JAAVAAItems.MAGNET_GOLD,
                JAAVAAItems.MAGNET_DIAMOND,
                JAAVAAItems.MAGNET_DIAMOND
        );
        valueLookupBuilder(JAAVAATags.Items.MOB_NETS).add(
                JAAVAAItems.MOB_NET_WOOD,
                JAAVAAItems.MOB_NET_STONE,
                JAAVAAItems.MOB_NET_IRON,
                JAAVAAItems.MOB_NET_GOLD,
                JAAVAAItems.MOB_NET_DIAMOND,
                JAAVAAItems.MOB_NET_NETHERITE
        );
        valueLookupBuilder(JAAVAATags.Items.PAXELS).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL
        );
        valueLookupBuilder(JAAVAATags.Items.STARSTEEL_TOOL_MATERIALS).add(
                JAAVAAItems.STARSTEEL_INGOT
        );
        valueLookupBuilder(JAAVAATags.Items.TOOLS_STARSTEEL).add(
                JAAVAAItems.STARSTEEL_SWORD,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL,
                JAAVAAItems.HAMMER_STARSTEEL
        );
        valueLookupBuilder(JAAVAATags.Items.VOIDIUM_TOOL_MATERIALS);
        valueLookupBuilder(JAAVAATags.Items.TOOLS_VOIDIUM);
        //Common Tags
        valueLookupBuilder(JAAVAATags.Items.TOOLS_WOODEN).add(
                Items.WOODEN_AXE,
                Items.WOODEN_HOE,
                Items.WOODEN_PICKAXE,
                Items.WOODEN_SHOVEL,
                Items.WOODEN_SWORD,
                JAAVAAItems.MOB_NET_WOOD
        );
        valueLookupBuilder(JAAVAATags.Items.TOOLS_STONE).add(
                Items.STONE_AXE,
                Items.STONE_HOE,
                Items.STONE_PICKAXE,
                Items.STONE_SHOVEL,
                Items.STONE_SWORD,
                JAAVAAItems.MOB_NET_STONE
        );
        valueLookupBuilder(JAAVAATags.Items.TOOLS_GOLD).add(
                Items.GOLDEN_AXE,
                Items.GOLDEN_HOE,
                Items.GOLDEN_PICKAXE,
                Items.GOLDEN_SHOVEL,
                Items.GOLDEN_SWORD,
                JAAVAAItems.HAMMER_GOLD,
                JAAVAAItems.MAGNET_GOLD,
                JAAVAAItems.MOB_NET_GOLD
        );
        valueLookupBuilder(JAAVAATags.Items.TOOLS_IRON).add(
                Items.IRON_AXE,
                Items.IRON_HOE,
                Items.IRON_PICKAXE,
                Items.IRON_SHOVEL,
                Items.IRON_SWORD,
                JAAVAAItems.HAMMER_IRON,
                JAAVAAItems.MAGNET_IRON,
                JAAVAAItems.MOB_NET_IRON
        );
        valueLookupBuilder(JAAVAATags.Items.TOOLS_DIAMOND).add(
                Items.DIAMOND_AXE,
                Items.DIAMOND_HOE,
                Items.DIAMOND_PICKAXE,
                Items.DIAMOND_SHOVEL,
                Items.DIAMOND_SWORD,
                JAAVAAItems.HAMMER_DIAMOND,
                JAAVAAItems.MAGNET_DIAMOND,
                JAAVAAItems.MOB_NET_DIAMOND
        );
        valueLookupBuilder(JAAVAATags.Items.TOOLS_NETHERITE).add(
                Items.NETHERITE_AXE,
                Items.NETHERITE_HOE,
                Items.NETHERITE_PICKAXE,
                Items.NETHERITE_SHOVEL,
                Items.NETHERITE_SWORD,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.HAMMER_NETHERITE,
                JAAVAAItems.MAGNET_NETHERITE,
                JAAVAAItems.MOB_NET_NETHERITE
        );
        //Vanilla Tags
        valueLookupBuilder(ItemTags.AXES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL
        );
        valueLookupBuilder(ItemTags.HOES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL
        );
        valueLookupBuilder(ItemTags.PICKAXES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL
        );
        valueLookupBuilder(ItemTags.SHOVELS).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL
        );
        valueLookupBuilder(ItemTags.SWORDS).add(
                JAAVAAItems.STARSTEEL_SWORD,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS,
                JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL
        );
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.HAMMERS)
                .addOptionalTag(JAAVAATags.Items.MAGNETS)
                .addOptionalTag(JAAVAATags.Items.MOB_NETS)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.HAMMERS)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
        valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addOptionalTag(JAAVAATags.Items.HAMMERS)
                .addOptionalTag(JAAVAATags.Items.PAXELS);
    }
}
