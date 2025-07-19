package net.gordyjack.jaavaa.mixin.client;

import com.mojang.serialization.*;
import net.fabricmc.api.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.property.*;
import net.minecraft.client.render.item.property.numeric.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Environment(EnvType.CLIENT)
@Mixin(NumericProperties.class)
public abstract class NumericPropertiesMixin {
    // Shadow the private mapper
    @Shadow @Final
    public static net.minecraft.util.dynamic.Codecs.IdMapper<Identifier, MapCodec<? extends NumericProperty>> ID_MAPPER;

    /**
     * Run right after vanilla registers its own numeric-property codecs.
     */
    @Inject(method = "bootstrap()V", at = @At("RETURN"))
    private static void onBootstrap(CallbackInfo ci) {
        // Give your state a stable ID and its MapCodec
        ID_MAPPER.put(
                JAAVAA.id("biome_compass"),
                BiomeCompassProperty.CODEC
        );
        ID_MAPPER.put(
                JAAVAA.id("structure_compass"),
                StructureCompassProperty.CODEC
        );
    }
}
