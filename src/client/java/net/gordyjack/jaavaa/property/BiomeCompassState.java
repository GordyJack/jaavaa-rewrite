package net.gordyjack.jaavaa.property;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.fabricmc.api.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.client.render.item.property.numeric.*;
import net.minecraft.client.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import org.jetbrains.annotations.*;

@Environment(EnvType.CLIENT)
public class BiomeCompassState extends NeedleAngleState {
    public static final MapCodec<BiomeCompassState> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Codec.BOOL.optionalFieldOf("wobble", true).forGetter(BiomeCompassState::hasWobble),
                            BiomeCompassState.Target.CODEC.fieldOf("target").forGetter(BiomeCompassState::getTarget)
                    )
                    .apply(instance, BiomeCompassState::new)
    );
    private final NeedleAngleState.Angler aimedAngler;
    private final NeedleAngleState.Angler aimlessAngler;
    private final BiomeCompassState.Target target;
    private final Random random = Random.create();

    public BiomeCompassState(boolean wobble, BiomeCompassState.Target target) {
        super(wobble);
        this.aimedAngler = this.createAngler(0.8F);
        this.aimlessAngler = this.createAngler(0.8F);
        this.target = target;
    }

    @Override
    protected float getAngle(ItemStack stack, ClientWorld world, int seed, Entity user) {
        GlobalPos globalPos = this.target.getPosition(world, stack, user);
        long l = world.getTime();
        return !canPointTo(user, globalPos) ? this.getAimlessAngle(seed, l) : this.getAngleTo(user, l, globalPos.pos());
    }

    private float getAimlessAngle(int seed, long time) {
        if (this.aimlessAngler.shouldUpdate(time)) {
            this.aimlessAngler.update(time, this.random.nextFloat());
        }

        float f = this.aimlessAngler.getAngle() + scatter(seed) / 2.1474836E9F;
        return MathHelper.floorMod(f, 1.0F);
    }

    private float getAngleTo(Entity entity, long time, BlockPos pos) {
        float f = (float)getAngleTo(entity, pos);
        float g = getBodyYaw(entity);
        float h;
        if (entity instanceof PlayerEntity playerEntity && playerEntity.isMainPlayer() && playerEntity.getWorld().getTickManager().shouldTick()) {
            if (this.aimedAngler.shouldUpdate(time)) {
                this.aimedAngler.update(time, 0.5F - (g - 0.25F));
            }

            h = f + this.aimedAngler.getAngle();
        } else {
            h = 0.5F - (g - 0.25F - f);
        }

        return MathHelper.floorMod(h, 1.0F);
    }

    private static boolean canPointTo(Entity entity, @Nullable GlobalPos pos) {
        return pos != null && pos.dimension() == entity.getWorld().getRegistryKey() && !(pos.pos().getSquaredDistance(entity.getPos()) < 1.0E-5F);
    }

    private static double getAngleTo(Entity entity, BlockPos pos) {
        Vec3d vec3d = Vec3d.ofCenter(pos);
        return Math.atan2(vec3d.getZ() - entity.getZ(), vec3d.getX() - entity.getX()) / (float) (Math.PI * 2);
    }

    private static float getBodyYaw(Entity entity) {
        return MathHelper.floorMod(entity.getBodyYaw() / 360.0F, 1.0F);
    }

    /**
     * Scatters a seed by integer overflow in multiplication onto the whole
     * int domain.
     */
    private static int scatter(int seed) {
        return seed * 1327217883;
    }

    protected BiomeCompassState.Target getTarget() {
        return this.target;
    }

    @Environment(EnvType.CLIENT)
    public enum Target implements StringIdentifiable {
        NONE("none") {
            @Nullable
            @Override
            public GlobalPos getPosition(ClientWorld world, ItemStack stack, Entity holder) {
                return null;
            }
        },
        BIOME("biome") {
            @Nullable
            @Override
            public GlobalPos getPosition(ClientWorld world, ItemStack stack, Entity holder) {
                return GlobalPos.create(world.getRegistryKey(), stack.getOrDefault(JAAVAAComponents.Types.COMPASS_TARGET_POSITION, BlockPos.ORIGIN));
            }
        };

        public static final Codec<BiomeCompassState.Target> CODEC = StringIdentifiable.createCodec(BiomeCompassState.Target::values);
        private final String name;

        Target(final String name) {
            this.name = name;
        }

        public String asString() {
            return this.name;
        }

        @Nullable
        abstract GlobalPos getPosition(ClientWorld world, ItemStack stack, Entity holder);
    }
}
