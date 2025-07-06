package net.gordyjack.jaavaa.item.custom;

import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;

import java.util.*;

public class HammerItem extends PickaxeItem {
    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos hitPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            switch (blockHit.getSide()) {
                case DOWN, UP -> {
                    for(int x = -range; x <= range; x++) {
                        for(int z = -range; z <= range; z++) {
                            positions.add(new BlockPos(hitPos.getX() + x, hitPos.getY(), hitPos.getZ() + z));
                        }
                    }
                }
                case NORTH, SOUTH -> {
                    for(int x = -range; x <= range; x++) {
                        for(int y = -1; y <= range * 2 - 1; y++) {
                            positions.add(new BlockPos(hitPos.getX() + x, hitPos.getY() + y, hitPos.getZ()));
                        }
                    }
                }
                case EAST, WEST -> {
                    for(int z = -range; z <= range; z++) {
                        for(int y = -1; y <= range * 2 - 1; y++) {
                            positions.add(new BlockPos(hitPos.getX(), hitPos.getY() + y, hitPos.getZ() + z));
                        }
                    }
                }
            }
        }
        return positions;
    }
}
