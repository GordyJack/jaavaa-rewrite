package net.gordyjack.jaavaa.enchantment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.gordyjack.jaavaa.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;

public record CurseOfUnbridledDestructionEffect(EnchantmentLevelBasedValue level)
        implements EnchantmentEntityEffect {
    public static final MapCodec<CurseOfUnbridledDestructionEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(CurseOfUnbridledDestructionEffect::level)
            ).apply(instance, CurseOfUnbridledDestructionEffect::new)
    );

    //TODO: It seems like the block is getting broken before world.breakBlock() can be called in apply().
    // I might need to use an event handler instead of an enchantment effect.
    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        JAAVAA.log("Curse of Unbridled Destruction effect applied", 'e');
        if (user instanceof PlayerEntity player && !player.isCreative()) {
            JAAVAA.log("Player is not in creative mode", 'e');
            BlockPos blockPos = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);
            Block currentBlock = world.getBlockState(blockPos).getBlock();
            ItemStack tool = player.getMainHandStack();
            if (world.breakBlock(blockPos, false, player)) {
                JAAVAA.log("Block broken", 'e');
                if (tool.isDamageable()) {
                    tool.damage(1, player);
                }
                currentBlock.onBreak(world, blockPos, world.getBlockState(blockPos), player);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
