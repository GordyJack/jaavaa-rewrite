package net.gordyjack.jaavaa.item.custom;

import net.gordyjack.jaavaa.data.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import java.util.*;
import java.util.stream.*;

public class SimpleMagnetItem extends Item {
    final double MAX_RANGE;
    final float VELOCITY_MULT;
    public SimpleMagnetItem(Settings settings, double maxRange, float velocity) {
        super(settings);
        this.MAX_RANGE = maxRange;
        this.VELOCITY_MULT = velocity;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("§7Magnet: §b" + (getEnabled(stack) ? "Active" : "Inactive")));
        tooltip.add(Text.literal("§7Sneak & Use to " + (getEnabled(stack) ? "deactivate" : "activate")));
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return getEnabled(stack);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && getEnabled(stack) && !player.isSneaking()) {
            for (ItemEntity itemEntity : this.getInputItemEntities(world, player.getBlockPos())) {
                if (itemEntity.isAlive() && !itemEntity.cannotPickup()) {
                    final float velocityScale = 30.0f / this.VELOCITY_MULT;
                    itemEntity.setVelocity(
                            (player.getX() - itemEntity.getX()) / velocityScale,
                            ((player.getY() + 0.5f) - itemEntity.getY()) / (velocityScale / 1.5),
                            (player.getZ() - itemEntity.getZ()) / velocityScale);
                    itemEntity.velocityDirty = true;
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld && user.isSneaking() && hand == Hand.MAIN_HAND) {
            ItemStack stack = user.getStackInHand(hand);
            toggleEnabled(stack);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5f, 2.0f);
            return ActionResult.SUCCESS;
        }
        return super.use(world, user, hand);
    }
    private List<ItemEntity> getInputItemEntities(World world, BlockPos pos) {
        VoxelShape shape = VoxelShapes.fullCube();
        return shape.getBoundingBoxes().stream().flatMap(
                box -> world.getEntitiesByClass(ItemEntity.class,
                        box.offset(pos.getX(), pos.getY(), pos.getZ()).expand(this.MAX_RANGE),
                        EntityPredicates.VALID_ENTITY).stream()).collect(Collectors.toList());
    }

    private static boolean getEnabled(ItemStack stack) {
        if (stack.contains(JAAVAAComponents.Types.MAGNET_ENABLED)) {
            return stack.get(JAAVAAComponents.Types.MAGNET_ENABLED);
        }
        return false;
    }
    private static void setEnabled(ItemStack stack, boolean enabled) {
        stack.set(JAAVAAComponents.Types.MAGNET_ENABLED, enabled);
    }
    private static void toggleEnabled(ItemStack stack) {
        boolean currentEnabled = getEnabled(stack);
        setEnabled(stack, !currentEnabled);
    }
}
