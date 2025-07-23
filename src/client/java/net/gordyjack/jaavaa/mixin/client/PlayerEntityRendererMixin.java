package net.gordyjack.jaavaa.mixin.client;

import net.minecraft.client.render.entity.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private static void jaavaa$onPlayerEntityRenderer(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        // This mixin is used to modify the player entity renderer.
        // Currently, it does not modify anything, but it can be used for future modifications.
        // If you want to cancel the player entity renderer, you can do so here.
        // ci.cancel();

    }
}
