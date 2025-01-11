package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.enchantment.*;
import net.gordyjack.jaavaa.enchantment.effect.*;

public class CurseOfTheCapriciousHandler {
    public static void init() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            JAAVAA.log("Curse of the Capricious handler activated", 'e');
            JAAVAA.log("Player: " + player.getName(), 'e');
            JAAVAA.log("Position: " + pos, 'e');
            JAAVAA.log("Inventory: " + player.getInventory().getMainHandStack().getEnchantments().getEnchantments().toString(), 'e');
            if (player.getInventory().getMainHandStack().getEnchantments().getEnchantments().toString().contains("curse_of_the_capricious")) {
                JAAVAA.log("Curse of the Capricious activated", 'e');
                CurseOfTheCapriciousEffect.onBlockMined(player.getInventory().getMainHandStack(), world, pos);
            }
        });
    }
}
