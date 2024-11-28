package net.gordyjack.jaavaa.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gordyjack.jaavaa.block.JAAVAABlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class JAAVAABlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public JAAVAABlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(JAAVAATags.Blocks.DEEPSLATE_CRAFTABLES)
                .add(Blocks.DEEPSLATE, Blocks.POLISHED_DEEPSLATE);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(JAAVAABlocks.STARSTEEL_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(JAAVAABlocks.STARSTEEL_BLOCK);
    }
}
