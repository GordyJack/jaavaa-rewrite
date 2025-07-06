package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.entity.damage.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;

import java.util.concurrent.*;

public class JAAVAADamageTypeTagProvider extends FabricTagProvider<DamageType> {
    public JAAVAADamageTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getTagBuilder(JAAVAATags.Other.IS_EXPLOSION_OR_FIRE)
                .addOptionalTag(DamageTypeTags.IS_EXPLOSION.id())
                .addOptionalTag(DamageTypeTags.IS_FIRE.id());
    }
}
