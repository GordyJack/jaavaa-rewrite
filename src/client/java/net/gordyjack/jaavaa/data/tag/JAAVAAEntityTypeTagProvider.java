package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.entity.*;
import net.minecraft.registry.*;

import java.util.concurrent.*;

public class JAAVAAEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {
    public JAAVAAEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENTITY_TYPE, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(JAAVAATags.Other.QUICKSAND_WALKABLE_MOBS)
                .add(
                        EntityType.ALLAY,
                        EntityType.ARMADILLO,
                        EntityType.CAMEL,
                        EntityType.CAVE_SPIDER,
                        EntityType.SPIDER,
                        EntityType.HUSK
                );
    }
}
