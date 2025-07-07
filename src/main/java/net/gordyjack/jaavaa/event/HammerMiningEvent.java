package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.player.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class HammerMiningEvent implements PlayerBlockBreakEvents.Before {
    // Done with the help of https://github.com/CoFH/CoFHCore/blob/c23d117dcd3b3b3408a138716b15507f709494cd/src/main/java/cofh/core/event/AreaEffectEvents.java
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos,
                                    BlockState state, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = player.getMainHandStack();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayerEntity serverPlayer) {
            if(HARVESTED_BLOCKS.contains(pos) || !JAAVAAUtils.isToolCorrectForBlock(mainHandItem, state)) {
                return true;
            }
            var comps = hammer.getComponents();
            int range = comps.contains(JAAVAAComponents.Types.HAMMER_RANGE) ? comps.get(JAAVAAComponents.Types.HAMMER_RANGE) : 0;
            for(BlockPos position : HammerItem.getBlocksToBeDestroyed(range, pos, serverPlayer)) {
                if(pos == position || !hammer.isCorrectForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
        }

        return true;
    }
}
