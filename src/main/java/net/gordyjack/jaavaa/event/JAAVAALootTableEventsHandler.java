package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.loot.v3.*;
import net.gordyjack.jaavaa.*;
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

public class JAAVAALootTableEventsHandler {
    public static void init() {
        JAAVAA.log("Initializing JAAVAA Loot Table Events Handler");
        LootTableEvents.MODIFY.register((lootTable, lootManager, id, supplier) -> {
            if (lootTable == LootTables.BASTION_BRIDGE_CHEST
                    || lootTable == LootTables.BASTION_HOGLIN_STABLE_CHEST
                    || lootTable == LootTables.BASTION_OTHER_CHEST) {
                modifyBastionLootTables(lootManager);
            }
            if (lootTable == LootTables.BASTION_TREASURE_CHEST) {
                modifyBastionTreasureLootTable(lootManager);
            }
            if (lootTable == tableOf("entities/allay")) {
                modifyEntityAllayLootTable(lootManager, supplier);
            }
            if (lootTable == tableOf("entities/shulker")) {
                modifyEntityShulkerLootTable(lootManager, supplier);
            }
            if (lootTable == tableOf("entities/wither")) {
                modifyEntityWitherLootTable(lootManager, supplier);
            }
        });
    }
    private static void modifyBastionLootTables(LootTable.Builder lootManager) {
        var lootPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(JAAVAAItems.TOOL_OF_THE_ANCIENTS)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                        .conditionally(RandomChanceLootCondition.builder(0.1f)))
                .with(ItemEntry.builder(JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                        .conditionally(RandomChanceLootCondition.builder(0.01f)));
        lootManager.pool(lootPool);
    }
    private static void modifyBastionTreasureLootTable(LootTable.Builder lootManager) {
        var lootPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(JAAVAAItems.TOOL_OF_THE_ANCIENTS)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                        .conditionally(RandomChanceLootCondition.builder(0.5f)))
                .with(ItemEntry.builder(JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                        .conditionally(RandomChanceLootCondition.builder(0.1f)));
        lootManager.pool(lootPool);
    }
    private static void modifyEntityAllayLootTable(LootTable.Builder lootManager, RegistryWrapper.WrapperLookup supplier) {
        var lootPool = LootPool.builder()
                .rolls(UniformLootNumberProvider.create(1, 2))
                .conditionally(KilledByPlayerLootCondition.builder()) // Only when killed by player
                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(supplier, 0.1f, 0.1f))
                .with(ItemEntry.builder(JAAVAAItems.ALLAY_ESSENCE));
        lootManager.pool(lootPool);
    }
    private static void modifyEntityShulkerLootTable(LootTable.Builder lootManager, RegistryWrapper.WrapperLookup supplier) {
        var lootPool = LootPool.builder()
                .rolls(UniformLootNumberProvider.create(0, 1))
                .conditionally(KilledByPlayerLootCondition.builder()) // Only when killed by player
                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(supplier, 0.1f, 0.1f))
                .with(ItemEntry.builder(JAAVAAItems.SHULKER_PEARL));
        lootManager.pool(lootPool);
    }
    private static void modifyEntityWitherLootTable(LootTable.Builder lootManager, RegistryWrapper.WrapperLookup supplier) {
        var lootPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(KilledByPlayerLootCondition.builder()) // Only when killed by player
                .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(supplier, 0.05f, 0.025f))
                .conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createDimension(World.NETHER)))
                .with(ItemEntry.builder(JAAVAAItems.STARSTEEL_INGOT)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 4)))
                        .weight(9))
                .with(ItemEntry.builder(JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                        .weight(1));
        lootManager.pool(lootPool);
    }
    private static RegistryKey<LootTable> tableOf(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(id));
    }
}
