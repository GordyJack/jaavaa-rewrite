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
    public static boolean isToolCorrectForBlock(ItemStack tool, BlockState blockState, boolean shouldTestMaterial) {
        // Get the appropriate tool tag to test the blockstate against.
        TagKey<Block> toolBlocks = TagKey.of(RegistryKeys.BLOCK, Identifier.of("namespace:tool_tag"));
        for (TagKey<Item> tag : getToolsMineableBlocksMap().keySet()) {
            if (tool.isIn(tag)) {
                toolBlocks = getToolsMineableBlocksMap().get(tag);
                break;
            }
        }

        // Test the blockstate against the tool types mineable blocks and early exit if incorrect.
        if (!blockState.isIn(toolBlocks)) return false;

        // Early exit if no material test is required.
        if (!shouldTestMaterial) return true;

        // Get the appropriate tool material tag to test the blockstate against.
        TagKey<Block> materialBlocks = TagKey.of(RegistryKeys.BLOCK, Identifier.of("namespace:material_tag"));
        for (TagKey<Item> tag : getMaterialsMineableBlocksMap().keySet()) {
            if (tool.isIn(tag)) {
                materialBlocks = getMaterialsMineableBlocksMap().get(tag);
                break;
            }
        }
        // Test the blockstate against the materials mineable blocks and return the result.
        return blockState.isIn(materialBlocks);
    }

    private static @NotNull HashMap<TagKey<Item>, TagKey<Block>> getMaterialsMineableBlocksMap() {
        HashMap<TagKey<Item>, TagKey<Block>> mineableBlocksMap = new HashMap<>();
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_WOODEN, BlockTags.INCORRECT_FOR_WOODEN_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_STONE, BlockTags.INCORRECT_FOR_STONE_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_IRON, BlockTags.INCORRECT_FOR_IRON_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_GOLD, BlockTags.INCORRECT_FOR_GOLD_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_DIAMOND, BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_NETHERITE, BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_STARSTEEL, JAAVAATags.Blocks.INCORRECT_FOR_STARSTEEL_TOOL);
        mineableBlocksMap.put(JAAVAATags.Items.TOOLS_VOIDIUM, JAAVAATags.Blocks.INCORRECT_FOR_VOIDIUM_TOOL);
        return mineableBlocksMap;
    }
    private static @NotNull HashMap<TagKey<Item>, TagKey<Block>> getToolsMineableBlocksMap() {
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
