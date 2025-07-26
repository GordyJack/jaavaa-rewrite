package net.gordyjack.jaavaa.event.loot_table;

import net.fabricmc.fabric.api.loot.v3.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.registry.*;

public class BastionLootTables implements LootTableEvents.Modify {
    @Override
    public void modifyLootTable(RegistryKey<LootTable> lootTable, LootTable.Builder lootManager, LootTableSource lootTableSource, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (lootTable == LootTables.BASTION_BRIDGE_CHEST
                || lootTable == LootTables.BASTION_HOGLIN_STABLE_CHEST
                || lootTable == LootTables.BASTION_OTHER_CHEST) {
            lootManager.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1)
                    ).with(ItemEntry.builder(JAAVAAItems.TOOL_OF_THE_ANCIENTS)
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                            .conditionally(RandomChanceLootCondition.builder(0.1f))
                    ).with(ItemEntry.builder(JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                            .conditionally(RandomChanceLootCondition.builder(0.01f))
                    )
            );
        }
        if (lootTable == LootTables.BASTION_TREASURE_CHEST) {
            lootManager.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1)
                    ).with(ItemEntry.builder(JAAVAAItems.TOOL_OF_THE_ANCIENTS)
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                            .conditionally(RandomChanceLootCondition.builder(0.5f))
                    ).with(ItemEntry.builder(JAAVAAItems.MALUM_STELLAE_INCANTATAE)
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                            .conditionally(RandomChanceLootCondition.builder(0.1f))
                    )
            );
        }
    }
}
