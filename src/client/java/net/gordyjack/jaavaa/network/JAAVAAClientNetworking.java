package net.gordyjack.jaavaa.network;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.*;
import net.fabricmc.fabric.api.client.networking.v1.*;

public class JAAVAAClientNetworking {
    public static int last_VD = -1;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (minecraftClient.getNetworkHandler() == null || minecraftClient.player == null) return;
            int vd = minecraftClient.options.getViewDistance().getValue();
            if (vd == last_VD) return; // No change, no need to send packet
            ClientPlayNetworking.send(new RenderDistanceC2SPacket(vd));
        });
    }
}
