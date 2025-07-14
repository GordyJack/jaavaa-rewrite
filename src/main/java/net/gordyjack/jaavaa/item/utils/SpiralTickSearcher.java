package net.gordyjack.jaavaa.item.utils;

import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;

import java.util.*;

public class SpiralTickSearcher {
    private final World world;
    private final Entity player;
    private final RegistryKey<Biome> targetBiome;
    private final List<BlockPos> positions;  // all centers to check
    private final int perTick;               // how many to check each tick
    private int index = 0;

    /**
     * @param world         the world to search
     * @param player        the entity around which to search
     * @param target        the biome to find
     * @param maxChunkRad   how many chunks out to scan (in X/Z)
     */
    public SpiralTickSearcher(World world,
                                     Entity player,
                                     RegistryKey<Biome> target,
                                     int maxChunkRad) {
        this.world = world;
        this.player = player;
        this.targetBiome = target;
        this.positions = buildPositions(maxChunkRad);

        int rings = maxChunkRad + 1;
        this.perTick = Math.max(1, positions.size() / (8 * rings));
    }

    private List<BlockPos> buildPositions(int maxRad) {
        if (player == null || world == null) {
            return Collections.emptyList(); // no player or world, nothing to search
        }
        int cx0 = player.getBlockX() >> 4;
        int cz0 = player.getBlockZ() >> 4;
        int minY = world.getBottomY();
        int maxY = world.getTopYInclusive();
        List<BlockPos> list = new ArrayList<>();

        // Spiral out in chunk-ring order
        for (int r = 0; r <= maxRad; r++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (Math.abs(dx) != r && Math.abs(dz) != r) continue;
                    int cx = cx0 + dx, cz = cz0 + dz;
                    // center X/Z of chunk:
                    int x = (cx << 4) + 8;
                    int z = (cz << 4) + 8;
                    // now add the center of each vertical 16‐block section
                    for (int y = minY + 8; y < maxY; y += 16) {
                        list.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return list;
    }

    /**
     * Call this each tick. Returns the first matching pos once found, else null
     * after the whole list has been scanned.
     */
    public BlockPos tick() {
        int end = Math.min(positions.size(), index + perTick);
        for (; index < end; index++) {
            BlockPos pos = positions.get(index);
            // safe lookup: world.getBiome never returns null
            if (world.getBiome(pos).matchesKey(targetBiome)) {
                return pos;
            }
        }
        // once we've stepped through all positions, we're done
        return index >= positions.size() ? null : BlockPos.ORIGIN;
    }

    /** true once scan has completed (found or exhausted) */
    public boolean isDone() {
        return index >= positions.size();
    }

    public int currentIndex() {
        return index;
    }
    public int maxIndex() {
        return positions.size();
    }
}