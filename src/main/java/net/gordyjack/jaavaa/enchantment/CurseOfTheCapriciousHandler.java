package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
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
                    && JAAVAAUtils.isToolCorrectForBlock(tool, state)
                    && serverWorld.breakBlock(pos, false, player)) {
                state.getBlock().onBreak(world, pos, state, player);
                List<Block> blockList = serverWorld.getRegistryManager().getOptional(RegistryKeys.BLOCK).get().stream()
                        .filter(block ->
                                block.getDefaultState().isIn(JAAVAATags.Blocks.CAPRICIOUS_BLOCKS)
                                        && JAAVAAUtils.isToolCorrectForBlock(tool, block.getDefaultState())
                        ).toList();
                Block randomBlock = blockList.get(new Random().nextInt(blockList.size()));
                JAAVAAUtils.damageBreakable(tool, 4, serverWorld, (ServerPlayerEntity) player);
                JAAVAAUtils.dropItem(serverWorld, pos, randomBlock.asItem());
                return false;
            }
            return true;
        });
    }
}
