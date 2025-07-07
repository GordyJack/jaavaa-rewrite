package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.enchantment.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CurseOfTheCapriciousEvent implements PlayerBlockBreakEvents.Before {
    /**
     * Called before a block is broken and allows cancelling the block breaking.
     *
     * <p>Implementations should not modify the world or assume the block break has completed or failed.</p>
     *
     * @param world       the world in which the block is broken
     * @param player      the player breaking the block
     * @param pos         the position at which the block is broken
     * @param state       the block state <strong>before</strong> the block is broken
     * @param blockEntity the block entity <strong>before</strong> the block is broken, can be {@code null}
     * @return {@code false} to cancel block breaking action, or {@code true} to pass to next listener
     */
    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        ItemStack tool = player.getStackInHand(Hand.MAIN_HAND);
        if (world instanceof ServerWorld serverWorld
                && !player.isCreative()
                && tool.getEnchantments().getEnchantments().toString().contains("jaavaa:curse_of_the_capricious")
                && !(state.getBlock() instanceof Blocktant)
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
            JAAVAAUtils.dropItem(serverWorld, pos, randomBlock.asItem(), dropCount);
            JAAVAAUtils.damageBreakable(tool, 1 + dropCount, serverWorld, (ServerPlayerEntity) player);
            return false;
        }
        return true;
    }
}
