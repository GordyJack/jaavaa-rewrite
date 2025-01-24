package net.gordyjack.jaavaa.utils;

import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.tag.*;

public class JAAVAABlockUtils {
    public static boolean isToolCorrectForBlock(ItemStack tool, BlockState blockState) {
        return isToolCorrectForBlock(tool, blockState, true);
    }
    public static boolean isToolCorrectForBlock(ItemStack tool, BlockState blockState, boolean testMaterial) {
        TagKey<Block> toolBlocks = switch (tool.getItem()) {
            case AxeItem ignored -> BlockTags.AXE_MINEABLE;
            case HoeItem ignored -> BlockTags.HOE_MINEABLE;
            case PickaxeItem ignored -> BlockTags.PICKAXE_MINEABLE;
            case ShovelItem ignored -> BlockTags.SHOVEL_MINEABLE;
            default -> JAAVAATags.Blocks.PAXEL_MINEABLE;
        };
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
        }
        JAAVAA.log("Tool: " + tool + " Block: " + blockState + " isToolCorrectForBlock: " + isToolCorrectForBlock + " isMaterialCorrectForBlock: " + isMaterialCorrectForBlock, 'e');

        return isMaterialCorrectForBlock && isToolCorrectForBlock;
    }
}
