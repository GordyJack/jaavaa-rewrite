package net.gordyjack.jaavaa.property;

import com.mojang.serialization.*;
import net.fabricmc.api.*;
import net.minecraft.client.render.item.property.numeric.*;
import net.minecraft.client.world.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import org.jetbrains.annotations.*;

@Environment(EnvType.CLIENT)
public class BiomeCompassProperty implements NumericProperty {
    public static final MapCodec<BiomeCompassProperty> CODEC =
            BiomeCompassState.CODEC.xmap(BiomeCompassProperty::new, property -> property.state);
    private final BiomeCompassState state;

    public BiomeCompassProperty(boolean wobble, BiomeCompassState.Target target) {
        this(new BiomeCompassState(wobble, target));
    }

    private BiomeCompassProperty(BiomeCompassState state) {
        this.state = state;
    }

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        return this.state.getValue(stack, world, holder, seed);
    }

    @Override
    public MapCodec<BiomeCompassProperty> getCodec() {
        return CODEC;
    }
}
