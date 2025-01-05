package net.gordyjack.jaavaa.data.lang;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
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
            if (block == JAAVAABlocks.STARSTEEL_BLOCK) {
                translationBuilder.add(block, "Block of Starsteel");
                continue;
            }
            translationBuilder.add(block, getTranslatedName(block));
        }
        for(Item item : JAAVAAItems.ITEMS) {
            if (item == JAAVAABlocks.STARSTEEL_BLOCK.asItem()) {
                translationBuilder.add(item, "Block of Starsteel");
                continue;
            }
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
            translationBuilder.add(("itemGroup." + group.getValue()).replace(':', '.'), getTranslatedName(group.getValue().getPath()));
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


        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/jaavaa/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            JAAVAA.log("Failed to add existing language file!", 'e');
        }
    }
    private static String getTranslatedName(ItemConvertible itemConvertible) {
        return getTranslatedName(itemConvertible.asItem().getTranslationKey());
    }
    private static String getTranslatedName(String name) {
        name = name.substring(name.lastIndexOf('.') + 1);
        name = name.replaceAll("_block(?!s)", "");
        name = name.replace('-', '_');
        name = name.replace('_', ' ');
        name = WordUtils.capitalizeFully(name);
        name = name.replace("Jaavaa", "JAAVAA");
        return name;
    }
}
