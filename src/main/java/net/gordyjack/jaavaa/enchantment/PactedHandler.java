package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;

public class PactedHandler {
    public static void init() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos,
                                               state, blockEntity) -> {
            ItemStack tool = player.getInventory().getMainHandStack();
            if (world instanceof ServerWorld
                    && tool.getEnchantments().getEnchantments().toString().contains("jaavaa:pacted")
                    && tool.isDamageable()) {
                if (tool.shouldBreak()) {
                    tool.setDamage(tool.getMaxDamage() - 2);
                }
            }
        });
    }
}
