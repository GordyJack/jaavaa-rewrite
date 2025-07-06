package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.fabricmc.fabric.api.event.player.*;

public class JAAVAAEventHandler {
    public static void init() {
        PlayerBlockBreakEvents.BEFORE.register(new HammerMiningEvent());
        PlayerBlockBreakEvents.BEFORE.register(new CurseOfTheCapriciousEvent());
        ServerEntityEvents.ENTITY_LOAD.register(new EternalItemDespawnEvent());
    }
}
