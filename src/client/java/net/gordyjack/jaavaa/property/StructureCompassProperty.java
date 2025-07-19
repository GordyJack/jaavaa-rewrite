package net.gordyjack.jaavaa.property;

import com.mojang.serialization.*;
import net.fabricmc.api.*;
import net.minecraft.client.render.item.property.numeric.*;
import net.minecraft.client.world.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import org.jetbrains.annotations.*;

@Environment(EnvType.CLIENT)
public class StructureCompassProperty implements NumericProperty {
    public static final MapCodec<StructureCompassProperty> CODEC =
            StructureCompassState.CODEC.xmap(StructureCompassProperty::new, property -> property.state);
    private final StructureCompassState state;

    public StructureCompassProperty(boolean wobble, StructureCompassState.Target target) {
        this(new StructureCompassState(wobble, target));
    }

    private StructureCompassProperty(StructureCompassState state) {
        this.state = state;
    }

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        return this.state.getValue(stack, world, holder, seed);
    }

    @Override
    public MapCodec<StructureCompassProperty> getCodec() {
        return CODEC;
    }
}
