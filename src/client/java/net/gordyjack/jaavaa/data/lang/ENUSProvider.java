package net.gordyjack.jaavaa.data.lang;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import org.apache.commons.lang3.text.*;

import java.nio.file.*;
import java.util.concurrent.*;

@SuppressWarnings({"deprecation", "OptionalGetWithoutIsPresent"})
public class ENUSProvider
extends FabricLanguageProvider{
    public ENUSProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
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
            translationBuilder.add(item, getTranslatedName(item));
        }
        for (RegistryKey<ItemGroup> group : JAAVAAItemGroups.ITEM_GROUPS) {
            translationBuilder.add(("itemGroup." + group.getValue()).replace(':', '.'), getTranslatedName(group.getValue().getPath()));
        }
        
        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/jaavaa/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
    private String getTranslatedName(ItemConvertible itemConvertible) {
        return getTranslatedName(itemConvertible.asItem().getTranslationKey());
    }
    private String getTranslatedName(String name) {
        name = name.substring(name.lastIndexOf('.') + 1);
        name = name.replaceAll("_block(?!s)", "");
        name = name.replace('_', ' ');
        name = WordUtils.capitalizeFully(name);
        name = name.replace("Jaavaa", "JAAVAA");
        return name;
    }
}
