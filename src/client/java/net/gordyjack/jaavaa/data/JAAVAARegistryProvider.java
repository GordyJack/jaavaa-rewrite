package net.gordyjack.jaavaa.data;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.registry.*;

import java.util.concurrent.*;

public class JAAVAARegistryProvider extends FabricDynamicRegistryProvider {
    public JAAVAARegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        entries.addAll(wrapperLookup.getOrThrow(RegistryKeys.ENCHANTMENT));
    }
    @Override
    public String getName() {
        return "JAAVAA Enchantment Provider";
    }
}
