package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.data.*;
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
        getTagBuilder(JAAVAATags.Other.UNBRIDLED_DESTRUCTION_EXCLUSIVE_SET)
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION.getValue())
                .addOptional(Enchantments.EFFICIENCY.getValue())
                .addOptionalTag(EnchantmentTags.MINING_EXCLUSIVE_SET.id());

        getTagBuilder(EnchantmentTags.CURSE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_PERSISTENCE.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION.getValue());
        getTagBuilder(EnchantmentTags.ON_RANDOM_LOOT)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_PERSISTENCE.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION.getValue());
        getTagBuilder(EnchantmentTags.MINING_EXCLUSIVE_SET)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION.getValue());
        getTagBuilder(EnchantmentTags.NON_TREASURE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue());
        getTagBuilder(EnchantmentTags.TRADEABLE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_PERSISTENCE.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION.getValue());
        getTagBuilder(EnchantmentTags.TREASURE)
                .addOptional(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_PERSISTENCE.getValue())
                .addOptional(JAAVAAEnchantments.CURSE_OF_UNBRIDLED_DESTRUCTION.getValue());
    }
}
