package net.gordyjack.jaavaa.event.loot_table;

import net.fabricmc.fabric.api.loot.v3.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class EntityLootTables implements LootTableEvents.Modify {
    @Override
    public void modifyLootTable(RegistryKey<LootTable> lootTable, LootTable.Builder lootManager, LootTableSource lootTableSource, RegistryWrapper.WrapperLookup supplier) {
        if (lootTable == tableOfVanilla("entities/allay")) {
            lootManager.pool(LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1, 2))
                    .conditionally(KilledByPlayerLootCondition.builder()) // Only when killed by player
                    .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(supplier, 0.1f, 0.1f))
                    .with(ItemEntry.builder(JAAVAAItems.ALLAY_ESSENCE))
            );
        }
        if (lootTable == tableOfVanilla("entities/shulker")) {
            lootManager.pool(LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(0, 1))
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(supplier, 0.1f, 0.1f))
                    .with(ItemEntry.builder(JAAVAAItems.SHULKER_PEARL))
            );
        }
        if (lootTable == tableOfVanilla("entities/wither")) {
            lootManager.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1)
                    ).conditionally(KilledByPlayerLootCondition.builder()
                    ).conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(supplier, 0.05f, 0.025f)
                    ).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createDimension(World.NETHER))
                    ).with(ItemEntry.builder(JAAVAAItems.STARSTEEL_INGOT)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 4)))
                            .weight(5)
                    ).with(ItemEntry.builder(JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                            .weight(1)
                    )
            );
        }
    }
    private static RegistryKey<LootTable> tableOfVanilla(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla(id));
    }
}
