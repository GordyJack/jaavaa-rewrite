package net.gordyjack.jaavaa.mixin;

import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
    @Inject(method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", at = @At("HEAD"), cancellable = true)
    private static void connectsTo(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(JAAVAABlocks.ADVANCED_REPEATER) || state.isOf(JAAVAABlocks.RANDOMIZER)) {
            Direction direction = state.get(AbstractRedstoneGateBlock.FACING);
            cir.setReturnValue(direction == dir || direction.getOpposite() == dir);
        }
        if (state.isOf(JAAVAABlocks.LOGICAL_OR_GATE)) {
            Direction direction = state.get(AbstractRedstoneGateBlock.FACING);
            cir.setReturnValue(direction == dir || direction.rotateYCounterclockwise() == dir || direction.rotateYClockwise() == dir);
        }
    }
}