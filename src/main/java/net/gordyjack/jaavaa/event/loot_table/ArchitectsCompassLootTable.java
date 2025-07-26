package net.gordyjack.jaavaa.event.loot_table;

import net.fabricmc.fabric.api.loot.v3.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.registry.*;

public class ArchitectsCompassLootTable implements LootTableEvents.Modify {
    @Override
    public void modifyLootTable(RegistryKey<LootTable> lootTable, LootTable.Builder lootManager, LootTableSource lootTableSource, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (LootTables.getAll().contains(lootTable)) {
            lootManager.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(JAAVAAItems.ARCHITECTS_COMPASS)
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                            .conditionally(RandomChanceLootCondition.builder(0.001f))
                    )
            );
        }
    }
}
