package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class EternalItemDespawnHandler {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ItemEntity itemEntity) {
                ItemStack stack = itemEntity.getStack();
                if (stack.getItem() instanceof EternalItem) {
                    EternalItem.preventDespawning(itemEntity);
                } else if (stack.getItem() instanceof EternalBlockItem) {
                    EternalBlockItem.preventDespawning(itemEntity);
                }
            }
        });
    }
}