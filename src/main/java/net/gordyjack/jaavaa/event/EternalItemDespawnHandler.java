package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

//TODO: Possibly move the EternalItem.preventDespawning to this class and switch to testing for a component instead of a custom item class.
public class EternalItemDespawnHandler {
    public static void init() {
        JAAVAA.log("Initializing Eternal Item Despawn Handler");
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ItemEntity itemEntity) {
                ItemStack stack = itemEntity.getStack();
                if (stack.getItem() instanceof EternalItem) {
                    EternalItem.preventDespawning(itemEntity);
                } else if (stack.getItem() instanceof EternalBlockItem) {
                    EternalBlockItem.preventDespawning(itemEntity);
                } else if (stack.getItem() instanceof PotionItem potionItem) {
                    if (potionItem.getName(stack).toString().toLowerCase().contains("eternal")) {
                        EternalItem.preventDespawning(itemEntity);
                    }
                }
            }
        });
    }
}