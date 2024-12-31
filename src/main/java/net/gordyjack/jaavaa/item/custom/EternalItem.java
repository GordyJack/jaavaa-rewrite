package net.gordyjack.jaavaa.item.custom;

import net.minecraft.entity.*;
import net.minecraft.item.*;

public class EternalItem extends Item {
    public EternalItem(Settings settings) {
        super(settings);
    }
    public static void preventDespawning(ItemEntity entity) {
        entity.setNeverDespawn(); // Prevent despawning.
    }
}
