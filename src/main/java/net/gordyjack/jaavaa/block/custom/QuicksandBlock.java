package net.gordyjack.jaavaa.block.custom;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import java.util.*;

//TODO: Add suffocation if in quicksand for too long. Looks like I might need a Mixin for that since it seems powdered snow freezing is handled in Entity.class
public class QuicksandBlock
        extends ColoredFallingBlock
        implements FluidDrainable {
    private static final VoxelShape FALLING_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9F, 1.0);

    public QuicksandBlock(Settings settings) {
        this(new ColorCode(14406560), settings);
    }
    public QuicksandBlock(ColorCode color, Settings settings) {
        super(color, settings);
    }

    // Powdered Snow-like behaviour.
    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return true;
    }
    @Override
    protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entityShapeContext) {
            Entity entity = entityShapeContext.getEntity();
            if (entity != null) {
                if (entity.fallDistance > 2.5F) {
                    return FALLING_SHAPE;
                }

                boolean bl = entity instanceof FallingBlockEntity;
                if (bl || canWalkOnQuicksand(entity) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending()) {
                    return super.getCollisionShape(state, world, pos, context);
                }
            }
        }

        return VoxelShapes.empty();
    }
    @Override
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }
    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.9F, 1.5, 0.9F));
            if (world.isClient) {
                Random random = world.getRandom();
                boolean bl = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                if (bl && random.nextBoolean()) {
                    world.addParticle(
                            DustParticleEffect.DEFAULT,
                            entity.getX(),
                            pos.getY() + 1,
                            entity.getZ(),
                            MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F,
                            0.05F,
                            MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F
                    );
                }
            }
        }
        if (world instanceof ServerWorld serverWorld) {
            if (entity.isOnFire()
                    && (serverWorld.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || entity instanceof PlayerEntity)
                    && entity.canModifyAt(serverWorld, pos)) {
                world.breakBlock(pos, false);
            }
            entity.setOnFire(false);
        }
    }
    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!((double)fallDistance < 4.0) && entity instanceof LivingEntity livingEntity) {
            LivingEntity.FallSounds fallSounds = livingEntity.getFallSounds();
            SoundEvent soundEvent = (double)fallDistance < 7.0 ? fallSounds.small() : fallSounds.big();
            entity.playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    public static boolean canWalkOnQuicksand(Entity entity) {
        if (entity.getType().isIn(JAAVAATags.Entity.QUICKSAND_WALKABLE_MOBS)) {
            return true;
        } else {
            return entity instanceof LivingEntity living
                    && living.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS);
        }
    }
    // FluidDrainable
    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW);
    }
    @Override
    public ItemStack tryDrainFluid(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL_AND_REDRAW);
        if (!world.isClient()) {
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
        }

        return new ItemStack(Items.POWDER_SNOW_BUCKET);
    }
}
