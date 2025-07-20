package net.gordyjack.jaavaa.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class FlyerEquipEvent implements ServerEntityEvents.EquipmentChange {
    @Override
    public void onChange(LivingEntity livingEntity, EquipmentSlot equipmentSlot, ItemStack previous, ItemStack next) {
        if (livingEntity instanceof PlayerEntity player && equipmentSlot == EquipmentSlot.CHEST) {
            if (next.get(JAAVAAComponents.Types.FLYER) != null) {
                player.getAbilities().allowFlying = true;
                player.sendAbilitiesUpdate();
            } else {
                player.getAbilities().allowFlying = false;
                player.sendAbilitiesUpdate();
            }
        }
    }
}
