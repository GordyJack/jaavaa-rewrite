package net.gordyjack.jaavaa.utils;

import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;

public class JAAVAAItemUtils {
    public static boolean damageBreakable(ItemStack itemStack, int damage, ServerWorld world, ServerPlayerEntity player) {
        if (itemStack.isDamageable()) {
            itemStack.damage(damage, world, player, item ->
                    player.playSoundToPlayer(
                            item.getBreakSound(),
                            SoundCategory.PLAYERS,
                            1.0F,
                            player.getSoundPitch()));
        }
        return false;
    }
}
