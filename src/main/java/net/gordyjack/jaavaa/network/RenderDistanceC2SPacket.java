package net.gordyjack.jaavaa.network;

import net.gordyjack.jaavaa.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.network.packet.*;

public record RenderDistanceC2SPacket(int renderDistance) implements CustomPayload {
    public static final CustomPayload.Id<RenderDistanceC2SPacket> PACKET_ID = new CustomPayload.Id<>(JAAVAA.id("viewdist_sync"));
    public static final PacketCodec<PacketByteBuf, RenderDistanceC2SPacket> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, RenderDistanceC2SPacket::renderDistance, RenderDistanceC2SPacket::new
    ).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
