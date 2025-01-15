package net.gordyjack.jaavaa.enchantment;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.server.world.*;

import java.util.*;
import java.util.stream.*;

public class CurseOfTheCapriciousHandler {
    public static void init() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos,
                                                state, blockEntity) -> {
            if (world instanceof ServerWorld serverWorld
                    && !player.isCreative()
                    && player.getInventory().getMainHandStack().getEnchantments().getEnchantments().toString().contains("jaavaa:curse_of_the_capricious")
                    && serverWorld.breakBlock(pos, false, player)) {
                state.getBlock().onBreak(world, pos, state, player);

                Stream<Block> blockRegistryStream = serverWorld.getRegistryManager().getOptional(RegistryKeys.BLOCK).get().stream();
                List<Block> blockList = blockRegistryStream.filter(block -> block.getDefaultState().isIn(JAAVAATags.Blocks.CAPRICIOUS_BLOCKS)).toList();
                Block randomBlock = blockList.get(new Random().nextInt(blockList.size()));

                serverWorld.addEntities(Stream.of(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, new ItemStack(randomBlock.asItem()))));
                return false;
            }
            return true;
        });
    }
}
