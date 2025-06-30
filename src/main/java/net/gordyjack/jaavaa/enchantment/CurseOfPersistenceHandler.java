package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.explosion.*;

import java.util.*;

public class CurseOfPersistenceHandler {
    // Track which players are currently mining
    private static final Map<UUID, Boolean> currentMiningPlayers = new HashMap<>();
    private static Map<UUID, Boolean> lastMiningPlayers = new HashMap<>();
    private static final Map<UUID, Long> lastMiningTime = new HashMap<>(); // Track the last time a player mined a block

    public static void init() {
        // Listen for when a player starts mining
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (world instanceof ServerWorld) {
                lastMiningPlayers = currentMiningPlayers; // Update lastMiningPlayers
                currentMiningPlayers.put(player.getUuid(), true); // Mark player as mining
                lastMiningTime.put(player.getUuid(), world.getTime()); // Update the last mining time
                JAAVAA.log("Player " + player.getName() + " is mining", 'e');
            }
            return ActionResult.PASS;
        });

        // Listen for player ticks to detect when mining stops
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            long currentTime = world.getTime();
            for (ServerPlayerEntity player : world.getPlayers()) {
                UUID playerId = player.getUuid();
                boolean isMining = currentMiningPlayers.getOrDefault(playerId, false);
                boolean wasMining = lastMiningPlayers.getOrDefault(playerId, false);
                long lastMined = lastMiningTime.getOrDefault(playerId, 0L);

                if (isMining && !wasMining) {
                    JAAVAA.log("Player " + player.getName() + " started mining", 'e');
                    continue;
                }
                // Update lastMiningTime if the player is still mining
                if (isMining && wasMining) {
                    lastMiningTime.put(playerId, currentTime); // Continuously update lastMiningTime while mining
                }

                // If the player was mining but hasn't mined in the last 2 ticks, trigger the explosion
                if (wasMining && !isMining && currentTime - lastMined > 5) {
                    currentMiningPlayers.put(playerId, false); // Mark player as not mining
                    JAAVAA.log("Player " + player.getName() + " stopped mining", 'e');

                    // Check for Curse of Persistence and trigger explosion
                    ItemStack tool = player.getMainHandStack();
                    Registry<Enchantment> enchantmentRegistry = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                    Optional<RegistryEntry.Reference<Enchantment>> enchantmentEntryOptional = enchantmentRegistry.getEntry(JAAVAAEnchantments.CURSE_OF_PERSISTENCE.getValue());

                    if (enchantmentEntryOptional.isPresent() && tool.getEnchantments().getEnchantments().contains(enchantmentEntryOptional.get())) {
                        int enchantmentLevel = tool.getEnchantments().getLevel(enchantmentEntryOptional.get());

                        if (!player.isCreative() && enchantmentLevel > 0) {
                            JAAVAA.log("Curse of Persistence triggered", 'e');

                            // Create an explosion that doesn't destroy blocks but affects the player
                            world.createExplosion(
                                    player, // Entity causing the explosion
                                    null,
                                    new ExplosionBehavior() {
                                        @Override
                                        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                                            return false;
                                        }
                                    },
                                    player.getPos(),
                                    enchantmentLevel, // Explosion power based on enchantment level
                                    false, // No fire
                                    World.ExplosionSourceType.TNT // Use TNT to ensure player is damaged
                            );

                            // Damage the tool
                            JAAVAAUtils.damageBreakable(tool, enchantmentLevel, world, player);
                        }
                    }
                }
            }
        });
    }
}