package net.gordyjack.jaavaa.utils;

import net.gordyjack.jaavaa.data.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.stream.*;

public class JAAVAAUtils {
    public static boolean damageBreakable(ItemStack itemStack, int damage, ServerWorld world, ServerPlayerEntity player) {
        if (itemStack.isDamageable()) {
            itemStack.damage(damage, world, player, item ->
                    player.playSoundToPlayer(
                            SoundEvents.ENTITY_ITEM_BREAK.value(),
                            SoundCategory.PLAYERS,
                            1.0F,
                            player.getSoundPitch()));
            return true;
        }
        return false;
    }
    public static void dropItem(ServerWorld serverWorld, BlockPos pos, Item item) {
        dropItem(serverWorld, pos, item, 1);
    }
    public static void dropItem(ServerWorld serverWorld, BlockPos pos, Item item, int count) {
        dropItemStack(serverWorld, pos, new ItemStack(item, count));
    }
    public static void dropItemStack(ServerWorld serverWorld, BlockPos pos, ItemStack itemStack) {
        serverWorld.addEntities(Stream.of(new ItemEntity(
                serverWorld,
                pos.getX() + 0.5f,
                pos.getY() + 0.5f,
                pos.getZ() + 0.5f,
                itemStack))
        );
    }
    public static boolean isToolCorrectForBlock(ItemStack tool, BlockState blockState) {
        return isToolCorrectForBlock(tool, blockState, true);
    }
    public static boolean isToolCorrectForBlock(ItemStack tool, BlockState blockState, boolean testMaterial) {
        TagKey<Block> toolBlocks = TagKey.of(RegistryKeys.BLOCK, Identifier.of(""));
        HashMap<TagKey<Item>, TagKey<Block>> mineableBlocksMap = getMineableBlocksMap();
        for (TagKey<Item> itemTag : mineableBlocksMap.keySet()) {
            if (tool.isIn(itemTag)) toolBlocks = mineableBlocksMap.get(itemTag);
        }
        boolean isToolCorrectForBlock = blockState.isIn(toolBlocks);

        boolean isMaterialCorrectForBlock = false;
        if (!testMaterial) {
            isMaterialCorrectForBlock = true;
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_WOODEN)) {
            isMaterialCorrectForBlock = !blockState.isIn(BlockTags.INCORRECT_FOR_WOODEN_TOOL);
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_STONE)) {
            isMaterialCorrectForBlock = !blockState.isIn(BlockTags.INCORRECT_FOR_STONE_TOOL);
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_GOLD)) {
            isMaterialCorrectForBlock = !blockState.isIn(BlockTags.INCORRECT_FOR_GOLD_TOOL);
        }else if (tool.isIn(JAAVAATags.Items.TOOLS_IRON)) {
            isMaterialCorrectForBlock = !blockState.isIn(BlockTags.INCORRECT_FOR_IRON_TOOL);
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_DIAMOND)) {
            isMaterialCorrectForBlock = !blockState.isIn(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_NETHERITE)) {
            isMaterialCorrectForBlock = !blockState.isIn(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_STARSTEEL)) {
            isMaterialCorrectForBlock = !blockState.isIn(JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL);
        } else if (tool.isIn(JAAVAATags.Items.TOOLS_VOIDIUM)) {
            isMaterialCorrectForBlock = !blockState.isIn(JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL);
        }
        return isMaterialCorrectForBlock && isToolCorrectForBlock;
    }

    private static @NotNull HashMap<TagKey<Item>, TagKey<Block>> getMineableBlocksMap() {
        HashMap<TagKey<Item>, TagKey<Block>> mineableBlocksMap = new HashMap<>();
        mineableBlocksMap.put(ItemTags.AXES, BlockTags.AXE_MINEABLE);
        mineableBlocksMap.put(JAAVAATags.Items.HAMMERS, BlockTags.PICKAXE_MINEABLE);
        mineableBlocksMap.put(ItemTags.HOES, BlockTags.HOE_MINEABLE);
        mineableBlocksMap.put(ItemTags.PICKAXES, BlockTags.PICKAXE_MINEABLE);
        mineableBlocksMap.put(ItemTags.SHOVELS, BlockTags.SHOVEL_MINEABLE);
        mineableBlocksMap.put(JAAVAATags.Items.PAXELS, JAAVAATags.Blocks.PAXEL_MINEABLE);
        return mineableBlocksMap;
    }
}
