package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.explosion.*;

import java.util.*;

public class CurseOfPersistenceHandler {
    public static void init() {
        PlayerBlockBreakEvents.CANCELED.register((world, player, pos,
                                                state, blockEntity) -> {
            JAAVAA.log("Block break canceled", 'e');
            ItemStack tool = player.getInventory().getMainHandStack();
            Registry<Enchantment> enchantmentRegistry =
                    world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            Optional<RegistryEntry.Reference<Enchantment>> enchantmentEntryOptional =
                    enchantmentRegistry.getEntry(JAAVAAEnchantments.CURSE_OF_PERSISTENCE.getValue());
            int enchantmentLevel = enchantmentEntryOptional.map(enchantmentReference ->
                    tool.getEnchantments().getLevel(enchantmentReference)).orElse(1);
            if (world instanceof ServerWorld serverWorld
                    && !player.isCreative()
                    && enchantmentLevel > 0) {
                JAAVAA.log("Curse of Persistence triggered", 'e');

                serverWorld.createExplosion(
                        player,
                        Explosion.createDamageSource(world, player),
                        new ExplosionBehavior() {
                            @Override
                            public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                                return false;
                            }

                            @Override
                            public float getKnockbackModifier(Entity entity) {
                                return 0.0f;
                            }
                        },
                        pos.toCenterPos(),
                        enchantmentLevel,
                        false,
                        World.ExplosionSourceType.TRIGGER
                );
                JAAVAAUtils.damageBreakable(tool, enchantmentLevel, serverWorld, (ServerPlayerEntity) player);
            }
        });
    }
}
