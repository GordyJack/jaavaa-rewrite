package net.gordyjack.jaavaa.network;

import net.fabricmc.fabric.api.networking.v1.*;
import net.gordyjack.jaavaa.*;

public class JAAVAAServerNetworking {
    public static int clientRenderDistance = -1; // Client's last known render distance

    public static void init() {
        JAAVAA.log("Registering JAAVAA Packets");
        // Register packets here if needed
        PayloadTypeRegistry.playC2S().register(RenderDistanceC2SPacket.PACKET_ID, RenderDistanceC2SPacket.PACKET_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(RenderDistanceC2SPacket.PACKET_ID,
                (packet, context) -> clientRenderDistance = packet.renderDistance());
    }
}
