package net.gordyjack.jaavaa.mixin;

import net.minecraft.component.type.*;
import net.minecraft.entity.effect.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(PotionContentsComponent.class)
public class PotionContentsComponentMixin {
    @Inject(method = "mixColors", at = @At("HEAD"), cancellable = true)
    private static void mixColors(Iterable<StatusEffectInstance> effects, CallbackInfoReturnable<OptionalInt> cir) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int totalWeight = 0;

        for (StatusEffectInstance effect : effects) {
            int color = effect.getEffectType().value().getColor();
            int weight = effect.getAmplifier() + 1;
            totalRed += weight * ColorHelper.getRed(color);
            totalGreen += weight * ColorHelper.getGreen(color);
            totalBlue += weight * ColorHelper.getBlue(color);
            totalWeight += weight;
        }

        cir.setReturnValue(totalWeight == 0 ? OptionalInt.empty() :
                OptionalInt.of(ColorHelper.getArgb(
                        totalRed / totalWeight,
                        totalGreen / totalWeight,
                        totalBlue / totalWeight)
                )
        );
    }
}
