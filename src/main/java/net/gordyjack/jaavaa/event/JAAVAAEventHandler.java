package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.fabricmc.fabric.api.event.player.*;
import net.fabricmc.fabric.api.loot.v3.*;
import net.gordyjack.jaavaa.event.loot_table.*;

public final class JAAVAAEventHandler {
    public static void init() {
        // Events
        PlayerBlockBreakEvents.BEFORE.register(new HammerMiningEvent());
        PlayerBlockBreakEvents.BEFORE.register(new CurseOfTheCapriciousEvent());
        ServerEntityEvents.ENTITY_LOAD.register(new EternalItemDespawnEvent());
        ServerEntityEvents.EQUIPMENT_CHANGE.register(new FlyerEquipEvent());

        // Loot Table Events
        LootTableEvents.MODIFY.register(new ArchitectsCompassLootTable());
        LootTableEvents.MODIFY.register(new BastionLootTables());
        LootTableEvents.MODIFY.register(new EntityLootTables());
    }
}
