package net.gordyjack.jaavaa.mixin.client;

import net.gordyjack.jaavaa.*;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    /**
     * Replace the hard-coded "40" level cap with Integer.MAX_VALUE,
     * so the "Too Expensive" branch never triggers.
     */
    @ModifyConstant(
            method = "drawForeground(Lnet/minecraft/client/gui/DrawContext;II)V",
            constant = @Constant(intValue = 40, ordinal = 0)
    )
    private int removeTooExpensiveThreshold(int original) {
        JAAVAA.log("AnvilScreenMixin: Replacing level cost threshold from 40 to Integer.MAX_VALUE", 'e');
        return Integer.MAX_VALUE;
    }
}
