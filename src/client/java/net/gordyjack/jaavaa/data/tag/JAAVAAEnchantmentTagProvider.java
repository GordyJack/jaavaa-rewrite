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
        getOrCreateTagBuilder(EnchantmentTags.CURSE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS)
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION);
        getOrCreateTagBuilder(EnchantmentTags.ON_RANDOM_LOOT)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS)
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION);
        getOrCreateTagBuilder(EnchantmentTags.MINING_EXCLUSIVE_SET)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS)
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION);
        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS);
        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS)
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION);
        getOrCreateTagBuilder(EnchantmentTags.TREASURE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS)
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION);
    }
}
