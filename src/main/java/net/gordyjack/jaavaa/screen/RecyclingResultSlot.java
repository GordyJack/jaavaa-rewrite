package net.gordyjack.jaavaa.screen;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.slot.*;

//TODO: I feel like this probably isn't done. But it's working for now.
public class RecyclingResultSlot extends Slot {
    private final Inventory INPUT;
    private final PlayerEntity PLAYER;
    private int amount;

    public RecyclingResultSlot(PlayerEntity player, Inventory inputInventory, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.PLAYER = player;
        this.INPUT = inputInventory;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }
    @Override
    public boolean disablesDynamicDisplay() {
        return true;
    }
    @Override
    protected void onCrafted(ItemStack stack, int amount) {
        this.amount += amount;
        this.onCrafted(stack);
    }
    @Override
    protected void onCrafted(ItemStack stack) {
        if (this.amount > 0) {
            stack.onCraftByPlayer(this.PLAYER.getWorld(), this.PLAYER, this.amount);
        }
        this.amount = 0;
    }
    @Override
    protected void onTake(int amount) {
        this.amount += amount;
    }
    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        stack.onCraftByPlayer(player.getWorld(), player, stack.getCount());
        this.onCrafted(stack);
        this.INPUT.getStack(0).decrement(1);
        this.INPUT.markDirty();
        this.markDirty();
    }
    @Override
    public ItemStack takeStack(int amount) {
        if (this.hasStack()) {
            this.amount = this.amount + Math.min(amount, this.getStack().getCount());
        }
        return super.takeStack(amount);
    }
}
