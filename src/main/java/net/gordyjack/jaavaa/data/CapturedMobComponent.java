package net.gordyjack.jaavaa.data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

public record CapturedMobComponent(@Nullable Identifier entityId, NbtCompound entityNbt) {
    public static final Codec<CapturedMobComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("entity_id").forGetter(CapturedMobComponent::entityId),
            NbtCompound.CODEC.fieldOf("entity_nbt").forGetter(CapturedMobComponent::entityNbt)
    ).apply(instance, CapturedMobComponent::new));
}