package net.gordyjack.jaavaa.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class JAAVAAItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public JAAVAAItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(JAAVAATags.Items.DEEPSLATE_CRAFTABLES)
                .add(Items.DEEPSLATE, Items.POLISHED_DEEPSLATE);
    }
}
