package net.gordyjack.jaavaa.screen;

import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.slot.*;

public class OutputSlot extends Slot {
    public OutputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }
}
