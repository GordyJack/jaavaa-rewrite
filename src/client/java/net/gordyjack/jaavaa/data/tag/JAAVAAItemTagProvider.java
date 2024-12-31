package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;

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
    }
}
