package net.gordyjack.jaavaa.item.custom;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class EternalBlockItem extends BlockItem {
    public EternalBlockItem(Block block, Settings settings) {
        super(block, settings);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
    public static void preventDespawning(ItemEntity entity) {
        entity.setNeverDespawn(); // Prevent despawning.
    }
}
