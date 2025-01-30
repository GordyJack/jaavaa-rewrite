package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;

import java.util.*;

public class CurseOfTheCapriciousHandler {
    public static void init() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos,
                                                state, blockEntity) -> {
            ItemStack tool = player.getInventory().getMainHandStack();
            if (world instanceof ServerWorld serverWorld
                    && !player.isCreative()
                    && tool.getEnchantments().getEnchantments().toString().contains("jaavaa:curse_of_the_capricious")
                    && !(state.getBlock() instanceof MiniBlock)
                    && JAAVAAUtils.isToolCorrectForBlock(tool, state)
                    && serverWorld.breakBlock(pos, false, player)) {
                state.getBlock().onBreak(world, pos, state, player);
                List<Block> blockList = serverWorld.getRegistryManager().getOptional(RegistryKeys.BLOCK).get().stream()
                        .filter(block ->
                                block.getDefaultState().isIn(JAAVAATags.Blocks.CAPRICIOUS_BLOCKS)
                                        && JAAVAAUtils.isToolCorrectForBlock(tool, block.getDefaultState())
                        ).toList();
                Block randomBlock = blockList.get(new Random().nextInt(blockList.size()));


                Registry<Enchantment> enchantmentRegistry =
                        world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                Optional<RegistryEntry.Reference<Enchantment>> enchantmentEntryOptional =
                        enchantmentRegistry.getEntry(JAAVAAEnchantments.CURSE_OF_THE_CAPRICIOUS.getValue());
                int enchantmentLevel = enchantmentEntryOptional.map(enchantmentReference ->
                        tool.getEnchantments().getLevel(enchantmentReference)).orElse(1);

                int dropCount = enchantmentLevel == 1 ? 1 : new Random().nextInt(enchantmentLevel) + 1;
                int damageCount = new Random().nextInt(1, 5) * dropCount;

                JAAVAAUtils.damageBreakable(tool, damageCount, serverWorld, (ServerPlayerEntity) player);
                JAAVAAUtils.dropItem(serverWorld, pos, randomBlock.asItem(), dropCount);
                return false;
            }
            return true;
        });
    }
}
