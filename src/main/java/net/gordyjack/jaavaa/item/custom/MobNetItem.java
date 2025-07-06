package net.gordyjack.jaavaa.item.custom;

import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

import java.util.function.*;

public class MobNetItem extends Item {
    private final ToolMaterial MATERIAL;
    private String entityName;

    public MobNetItem(Settings settings, ToolMaterial material) {
        super(settings);
        this.MATERIAL = material;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if (hasCapturedEntity(stack)) {
            textConsumer.accept(Text.literal("Captured Mob: §b" + entityName));
            textConsumer.accept(Text.literal("§7Right click on a block to release mob"));
        } else {
            textConsumer.accept(Text.literal("§7Right Click on a Mob to capture it."));
        }
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return hasCapturedEntity(stack);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        if(context.getWorld() instanceof ServerWorld serverWorld) {
            var pos = context.getBlockPos();
            pos = pos.add(context.getSide().getVector());
            if (this.releaseEntity(stack, serverWorld, pos)) {
                stack.damage(1, context.getPlayer());
                context.getPlayer().setStackInHand(context.getHand(), stack);
                return ActionResult.SUCCESS_SERVER;
            }
        }
        return super.useOnBlock(context);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(!user.getWorld().isClient() && !hasCapturedEntity(stack)) {
            TagKey<EntityType<?>> mobs = TagKey.of(RegistryKeys.ENTITY_TYPE, JAAVAA.id(null));
            if (this.MATERIAL == ToolMaterial.WOOD) {
                mobs = JAAVAATags.Entity.CAPTURABLE_MOBS_WOOD;
            } else if (this.MATERIAL == ToolMaterial.STONE) {
                mobs = JAAVAATags.Entity.CAPTURABLE_MOBS_STONE;
            } else if (this.MATERIAL == ToolMaterial.IRON) {
                mobs = JAAVAATags.Entity.CAPTURABLE_MOBS_IRON;
            } else if (this.MATERIAL == ToolMaterial.DIAMOND || this.MATERIAL == ToolMaterial.GOLD) {
                mobs = JAAVAATags.Entity.CAPTURABLE_MOBS_DIAMOND;
            } else if (this.MATERIAL == ToolMaterial.NETHERITE) {
                mobs = JAAVAATags.Entity.CAPTURABLE_MOBS_NETHERITE;
            }
            if (mobs != null && entity.getType().isIn(mobs)) {
                this.captureEntity(stack, entity);
                user.setStackInHand(hand, stack);
                this.entityName = entity.getName().getString();
                return ActionResult.SUCCESS_SERVER;
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    private void captureEntity(ItemStack stack, LivingEntity entity) {
        setCapturedEntity(stack, entity);
        entity.discard();
    }
    private boolean releaseEntity(ItemStack stack, ServerWorld serverWorld, BlockPos pos) {
        LivingEntity capturedEntity = getCapturedEntity(stack, serverWorld);
        if (capturedEntity != null) {
            capturedEntity.setPos(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);
            var spawnedEntity = capturedEntity.getType().spawn(serverWorld, pos, SpawnReason.SPAWN_ITEM_USE);
            spawnedEntity.copyFrom(capturedEntity);
            removeCapturedEntity(stack);
            return true;
        }
        return false;
    }
    private static LivingEntity getCapturedEntity(ItemStack stack, ServerWorld world) {
        // Ensure the component is present
        if (!stack.contains(JAAVAAComponents.Types.MOB_NET_ENTITY)) {
            return null;
        }
        CapturedMobComponent comp = stack.get(JAAVAAComponents.Types.MOB_NET_ENTITY);
        Identifier id = comp.entityId();
        if (id == null) {
            return null;
        }
        NbtComponent mobData = comp.entityNbt();
        EntityType<?> type = Registries.ENTITY_TYPE.get(id);
        if (type == null) {
            return null;
        }
        // Create and restore the entity without handling spawn events
        Entity mob = type.create(world, SpawnReason.SPAWN_ITEM_USE);
        mob.setComponent(DataComponentTypes.ENTITY_DATA, mobData);
        return (LivingEntity) mob;
    }
    private static EntityType<?> getCapturedEntityType(ItemStack stack) {
        if (!stack.contains(JAAVAAComponents.Types.MOB_NET_ENTITY)) {
            return null;
        }
        CapturedMobComponent comp = stack.get(JAAVAAComponents.Types.MOB_NET_ENTITY);
        Identifier id = comp.entityId();
        if (id == null) {
            return null;
        }
        return Registries.ENTITY_TYPE.get(comp.entityId());
    }
    public static boolean hasCapturedEntity(ItemStack stack) {
        return getCapturedEntityType(stack) != null;
    }
    private static void removeCapturedEntity(ItemStack stack) {
        stack.set(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(
                null,
                null
        ));
    }
    private static void setCapturedEntity(ItemStack stack, LivingEntity entity) {
        NbtComponent data = entity.get(DataComponentTypes.ENTITY_DATA);
        stack.set(JAAVAAComponents.Types.MOB_NET_ENTITY, new CapturedMobComponent(
                Registries.ENTITY_TYPE.getId(entity.getType()),
                data
        ));
    }
}
