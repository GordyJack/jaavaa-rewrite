package net.gordyjack.jaavaa.mixin;

import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
    @Inject(method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", at = @At("HEAD"), cancellable = true)
    private static void connectsTo(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(JAAVAABlocks.ADVANCED_REPEATER)) {
            Direction direction = state.get(AdvancedRepeaterBlock.FACING);
            cir.setReturnValue(direction == dir || direction.getOpposite() == dir);
        }
    }
}