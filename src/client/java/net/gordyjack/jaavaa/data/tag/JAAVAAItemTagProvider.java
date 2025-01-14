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
        //Vanilla Tags
        getOrCreateTagBuilder(ItemTags.AXES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.HOES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS
        );
        getOrCreateTagBuilder(ItemTags.SWORDS).add(
                JAAVAAItems.TOOL_OF_THE_ANCIENTS
        );
    }
}
