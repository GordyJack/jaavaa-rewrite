package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.enchantment.*;

public class CurseOfTheCapriciousHandler {
    public static void init() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (player.getInventory().getMainHandStack().getEnchantments().getEnchantments().contains(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS)) {
                JAAVAA.log("Curse of the Capricious activated", 'w');
                world.breakBlock(pos, true, player);
            }
        });
    }
}
