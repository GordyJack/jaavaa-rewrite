// File: src/main/java/net/gordyjack/jaavaa/mixin/MixinAnvilScreenHandler.java
package net.gordyjack.jaavaa.mixin;

import net.minecraft.screen.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @ModifyConstant(
            method = "updateResult",
            constant = @Constant(intValue = 40)
    )
    private int modifyLevelCost(int original) {
        return Integer.MAX_VALUE;
    }
}