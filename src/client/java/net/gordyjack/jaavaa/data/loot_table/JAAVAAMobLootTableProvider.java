package net.gordyjack.jaavaa.data.loot_table;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;
import net.minecraft.util.context.*;

import java.util.concurrent.*;
import java.util.function.*;

public class JAAVAAMobLootTableProvider extends SimpleFabricLootTableProvider {
    private final RegistryWrapper.WrapperLookup WRAPPER_LOOKUP;
    public JAAVAAMobLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup, ContextType contextType) {
        super(output, registryLookup, contextType);
        try {
            this.WRAPPER_LOOKUP = registryLookup.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        // Define the Allay's loot table
        RegistryKey<LootTable> allayLootTableKey =
                RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/allay"));
        LootTable.Builder allayLootTableBuilder = LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 2))
                        .conditionally(KilledByPlayerLootCondition.builder()) // Only when killed by player
                        .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(this.WRAPPER_LOOKUP, 0.1f, 0.1f)) // 5% base chance, +2% per Looting level
                        .with(ItemEntry.builder(JAAVAAItems.ALLAY_ESSENCE))
                );
        // Register the loot table
        lootTableBiConsumer.accept(allayLootTableKey, allayLootTableBuilder);    }
}
