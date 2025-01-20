package net.gordyjack.jaavaa.utils;

import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.tag.*;

public class JAAVAABlockUtils {
    public static boolean isToolCorrectForBlock(Item tool, BlockState blockState) {
        boolean isAxeMineable = blockState.isIn(BlockTags.AXE_MINEABLE);
        boolean isHoeMineable = blockState.isIn(BlockTags.HOE_MINEABLE);
        boolean isPickMineable = blockState.isIn(BlockTags.PICKAXE_MINEABLE);
        boolean isShovelMineable = blockState.isIn(BlockTags.SHOVEL_MINEABLE);
        boolean isPaxelMineable = blockState.isIn(JAAVAATags.Blocks.PAXEL_MINEABLE);
        return switch (tool) {
            case AxeItem ignored -> isAxeMineable;
            case HoeItem ignored -> isHoeMineable;
            case PickaxeItem ignored -> isPickMineable;
            case ShovelItem ignored -> isShovelMineable;
            case PaxelItem ignored -> isPaxelMineable;
            default -> false;
        };
    }
}
