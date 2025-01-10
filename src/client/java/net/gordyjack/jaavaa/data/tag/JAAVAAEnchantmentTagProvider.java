package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.enchantment.*;
import net.minecraft.enchantment.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;

import java.util.concurrent.*;

public class JAAVAAEnchantmentTagProvider extends FabricTagProvider<Enchantment> {
    public JAAVAAEnchantmentTagProvider(FabricDataOutput output, CompletableFuture< RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(EnchantmentTags.MINING_EXCLUSIVE_SET).addOptional(
                JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS
        );
    }
}
