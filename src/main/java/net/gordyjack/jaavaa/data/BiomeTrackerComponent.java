package net.gordyjack.jaavaa.data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.registry.*;
import net.minecraft.util.math.*;
import net.minecraft.world.biome.*;

public record BiomeTrackerComponent(RegistryKey<Biome> targetBiome, BlockPos biomePosition) {
    public static final Codec<BiomeTrackerComponent> CODEC = RecordCodecBuilder.create(i ->
            i.group(
                    RegistryKey.createCodec(RegistryKeys.BIOME).fieldOf("target_biome").forGetter(BiomeTrackerComponent::targetBiome),
                    BlockPos.CODEC.optionalFieldOf("biome_position", BlockPos.ORIGIN).forGetter(BiomeTrackerComponent::biomePosition)
            ).apply(i, BiomeTrackerComponent::new)
    );

//    public static synchronized BlockPos findClosestBiome(World world, Entity user, int maxChunkRadius,
//                                            RegistryKey<Biome> targetBiome) {
//        // 1. Try server-side optimized search
//        if (world instanceof ServerWorld server) {
//            var pair = server.locateBiome(
//                    entry -> entry.matchesKey(targetBiome),
//                    user.getBlockPos(), maxChunkRadius, 8, 16
//            );
//            if (pair != null) {
//                return pair.getFirst();
//            }
//        }
//        return BlockPos.ORIGIN;
//    }
}
