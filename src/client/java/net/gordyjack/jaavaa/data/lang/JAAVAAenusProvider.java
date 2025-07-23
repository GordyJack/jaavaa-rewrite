package net.gordyjack.jaavaa.data.lang;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.enchantment.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.potion.*;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.block.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import net.minecraft.screen.*;
import net.minecraft.util.*;
import org.apache.commons.lang3.text.*;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings({"deprecation", "OptionalGetWithoutIsPresent"})
public class JAAVAAenusProvider
extends FabricLanguageProvider{
    public JAAVAAenusProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        for (Block block : JAAVAABlocks.BLOCKS) {
            if (block == JAAVAABlocks.AURON_BLOCK) {
                translationBuilder.add(block, "Block of Auron");
                continue;
            }
            if (block == JAAVAABlocks.CUPAUREUM_BLOCK) {
                translationBuilder.add(block, "Block of Cupaureum");
                continue;
            }
            if (block == JAAVAABlocks.CUPERUM_BLOCK) {
                translationBuilder.add(block, "Block of Cuperum");
                continue;
            }
            if (block == JAAVAABlocks.QUICKSILVER_BLOCK) {
                translationBuilder.add(block, "Block of Quicksilver");
                continue;
            }
            if (block == JAAVAABlocks.STARSTEEL_BLOCK) {
                translationBuilder.add(block, "Block of Starsteel");
                continue;
            }
            if (block == JAAVAABlocks.STEEL_BLOCK) {
                translationBuilder.add(block, "Block of Steel");
                continue;
            }
            if (block == JAAVAABlocks.LOGICAL_AND_GATE) {
                translationBuilder.add(block, "Logical AND Gate");
                continue;
            }
            if (block == JAAVAABlocks.LOGICAL_OR_GATE) {
                translationBuilder.add(block, "Logical OR Gate");
                continue;
            }
            if (block == JAAVAABlocks.LOGICAL_XOR_GATE) {
                translationBuilder.add(block, "Logical XOR Gate");
                continue;
            }
            if (block instanceof Blocktant) {
                List<Block> blockOfs = List.of(
                        JAAVAABlocks.AMETHYST_BLOCKTANT, JAAVAABlocks.COAL_BLOCKTANT, JAAVAABlocks.DIAMOND_BLOCKTANT,
                        JAAVAABlocks.EMERALD_BLOCKTANT, JAAVAABlocks.GOLD_BLOCKTANT, JAAVAABlocks.IRON_BLOCKTANT,
                        JAAVAABlocks.LAPIS_LAZULI_BLOCKTANT, JAAVAABlocks.NETHERITE_BLOCKTANT, JAAVAABlocks.QUARTZ_BLOCKTANT,
                        JAAVAABlocks.RAW_COPPER_BLOCKTANT, JAAVAABlocks.RAW_GOLD_BLOCKTANT, JAAVAABlocks.RAW_IRON_BLOCKTANT,
                        JAAVAABlocks.REDSTONE_BLOCKTANT, JAAVAABlocks.RESIN_BLOCKTANT, JAAVAABlocks.STARSTEEL_BLOCKTANT);
                if (blockOfs.contains(block)) {
                    translationBuilder.add(block, "Block of " + getTranslatedName(block, false));
                    continue;
                }
                translationBuilder.add(block, getTranslatedName(block, false));
                continue;
            }
            translationBuilder.add(block, getTranslatedName(block));
        }
        for(Item item : JAAVAAItems.ITEMS) {
            translationBuilder.add(item, getTranslatedName(item));
        }
        List<String> effectNames = new ArrayList<>();
        for (var potionEntry : JAAVAAPotions.POTION_ENTRIES) {
            String id = potionEntry.getIdAsString();
            String potionName = id.substring(id.indexOf(':') + 1);
            String effectName = potionName.replaceFirst("(_\\d.*)", "");

            String preType = "item.minecraft.";
            String postType = ".effect." + effectName;

            if (effectNames.contains(effectName)) {
                continue;
            }

            translationBuilder.add( preType + "potion" + postType, "Potion of " + getTranslatedName(effectName));
            translationBuilder.add( preType + "splash_potion" + postType, "Splash Potion of " + getTranslatedName(effectName));
            translationBuilder.add( preType + "lingering_potion" + postType, "Lingering Potion of " + getTranslatedName(effectName));
            translationBuilder.add( preType + "tipped_arrow" + postType, "Arrow of " + getTranslatedName(effectName));
            effectNames.add(effectName);
        }
        for (RegistryKey<ItemGroup> group : JAAVAAItemGroups.ITEM_GROUPS) {
            translationBuilder.add(("itemGroup." + group.getValue()).replace(':', '.'), getTranslatedName(group.getValue().getPath(), false));
        }
        for (RegistryEntry<StatusEffect> effect : JAAVAAStatusEffects.EFFECTS) {
            String effectId = effect.getIdAsString();
            String effectName = effectId.substring(effectId.indexOf(':') + 1);
            translationBuilder.add("effect.jaavaa." + effectName, getTranslatedName(effectName));
        }
        for (ScreenHandlerType<? extends ScreenHandler> handler : JAAVAAScreenHandlers.SCREEN_HANDLERS) {
            Identifier handlerId = Registries.SCREEN_HANDLER.getId(handler);
            String handlerName = handlerId != null ? handlerId.getPath() : "null";
            handlerName = handlerName.replace("_screen_handler", "");
            translationBuilder.add("container.jaavaa." + handlerName, getTranslatedName(handlerName));
        }
        for (var enchantment : JAAVAAEnchantments.ENCHANTMENTS) {
            String enchantmentName = enchantment.getValue().toString();
            enchantmentName = enchantmentName.substring(enchantmentName.indexOf(':') + 1);
            translationBuilder.add("enchantment.jaavaa." + enchantmentName, getTranslatedName(enchantmentName));
        }

        translationBuilder.add(
                Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.additions_slot_description")),
                "Add Starsteel Ingot");
        translationBuilder.add(
                Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.applies_to")),
                "Netherite Equipment");
        translationBuilder.add(
                Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.ingredients")),
                "Starsteel Ingot");
        translationBuilder.add(
                Util.createTranslationKey("item", JAAVAA.id("smithing_template.starsteel_upgrade.base_slot_description")),
                "Add netherite armor, weapon, or tool");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/jaavaa/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            JAAVAA.log("Failed to add existing language file!", 'e');
        }
    }
    private static String getTranslatedName(ItemConvertible itemConvertible) {
        return getTranslatedName(itemConvertible.asItem().getTranslationKey(), true);
    }
    private static String getTranslatedName(ItemConvertible itemConvertible, boolean filterBlock) {
        return getTranslatedName(itemConvertible.asItem().getTranslationKey(), filterBlock);
    }
    private static String getTranslatedName(String name) {
        return getTranslatedName(name, true);
    }
    private static String getTranslatedName(String name, boolean filterBlock) {
        name = name.substring(name.lastIndexOf('.') + 1); // Remove namespace
        if (filterBlock) name = name.replaceAll("_block(?!s)", ""); // Remove block suffix
        name = name.replace('-', '_'); // Replace hyphens with underscores
        name = name.replace('_', ' '); // Replace underscores with spaces
        name = WordUtils.capitalizeFully(name); // Capitalize each word
        // Special cases
        name = name.replace("Jaavaa", "JAAVAA");
        name = name.replace(" Of ", " of ");
        name = name.replace(" The ", " the ");
        return name;
    }
}
