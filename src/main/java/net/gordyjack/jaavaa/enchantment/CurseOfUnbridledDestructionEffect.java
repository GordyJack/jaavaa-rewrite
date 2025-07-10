package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;

//TODO: Hammers don't work correctly with this enchantment. Need to fix.
public record CurseOfUnbridledDestructionEffect(EnchantmentLevelBasedValue level)
        implements EnchantmentEntityEffect {
    public static final MapCodec<CurseOfUnbridledDestructionEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(CurseOfUnbridledDestructionEffect::level)
            ).apply(instance, CurseOfUnbridledDestructionEffect::new)
    );
    @Override
    public void apply(ServerWorld serverWorld, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof ServerPlayerEntity player && !player.isCreative()) {
            BlockPos hitPos = new BlockPos((int) Math.floor(pos.x), (int) Math.floor(pos.y), (int) Math.floor(pos.z));
            BlockState currentState = serverWorld.getBlockState(hitPos);
            ItemStack tool = player.getMainHandStack();

            if (tool.getItem() instanceof HammerItem hammer) {
                var blocksToDestroy = HammerItem.getBlocksToBeDestroyed(hammer.getComponents().get(JAAVAAComponents.Types.HAMMER_RANGE), hitPos, player);
                for (var blockPos : blocksToDestroy) {
                    mineBlock(serverWorld, player, tool, currentState, blockPos);
                }
            } else {
                mineBlock(serverWorld, player, tool, currentState, hitPos);
            }
        }
    }

    private static void mineBlock(ServerWorld serverWorld, ServerPlayerEntity player, ItemStack tool, BlockState currentState, BlockPos blockPos) {
        if (JAAVAAUtils.isToolCorrectForBlock(tool, currentState, false)
                && serverWorld.breakBlock(blockPos, false, player)) {
            currentState.getBlock().onBreak(serverWorld, blockPos, serverWorld.getBlockState(blockPos), player);
            JAAVAAUtils.damageBreakable(tool, 3, serverWorld, player);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
