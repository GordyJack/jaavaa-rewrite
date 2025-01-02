package net.gordyjack.jaavaa.mixin;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    private void modifyHasGlintForEternalPotions(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof PotionItem potionItem) {
            cir.setReturnValue(potionItem.getName(stack).getString().toLowerCase().contains("eternal") || stack.hasEnchantments());
        }
    }
}
