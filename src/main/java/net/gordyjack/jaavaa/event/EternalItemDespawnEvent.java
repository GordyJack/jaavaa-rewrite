package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;

public class EternalItemDespawnEvent implements ServerEntityEvents.Load {
    @Override
    public void onLoad(Entity entity, ServerWorld serverWorld) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack stack = itemEntity.getStack();
            if (stack.getItem() instanceof EternalItem || stack.getItem() instanceof EternalBlockItem) {
                itemEntity.setNeverDespawn();
            } else if (stack.getItem() instanceof PotionItem potionItem) {
                if (potionItem.getName(stack).toString().toLowerCase().contains("eternal")) {
                    itemEntity.setNeverDespawn();
                }
            }
        }
    }
}