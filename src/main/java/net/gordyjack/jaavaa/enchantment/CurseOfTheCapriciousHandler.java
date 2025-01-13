package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.random.*;

import java.util.stream.*;

public class CurseOfTheCapriciousHandler {
    public static void init() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (world instanceof ServerWorld serverWorld
                    && player.getInventory().getMainHandStack().getEnchantments().getEnchantments().toString().contains("curse_of_the_capricious")
                    && serverWorld.breakBlock(pos, false, player)) {
                state.getBlock().onBreak(world, pos, state, player);
                Block randomBlock = Registries.BLOCK.getRandom(Random.create()).get().value();
                serverWorld.addEntities(Stream.of(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, new ItemStack(randomBlock.asItem()))));
                return true;
            }
            return false;
        });
    }
}
