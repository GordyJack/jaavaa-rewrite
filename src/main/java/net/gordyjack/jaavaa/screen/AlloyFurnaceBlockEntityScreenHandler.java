package net.gordyjack.jaavaa.screen;

import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import net.minecraft.util.math.*;

public class AlloyFurnaceBlockEntityScreenHandler extends ScreenHandler {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;
    private final Inventory INV;
    private final PropertyDelegate PROPERTY_DELEGATE;


    public AlloyFurnaceBlockEntityScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(3));
    }
    public AlloyFurnaceBlockEntityScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(JAAVAAScreenHandlers.ALLOY_FURNACE_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 3);
        this.INV = (Inventory) blockEntity;
        this.PROPERTY_DELEGATE = propertyDelegate;
        
        this.addSlot(new Slot(INV, 0, 53, 17));
        this.addSlot(new Slot(INV, 1, 107, 17));
        this.addSlot(new Slot(INV, 2, 79, 57) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    //TODO: Fix quickMove. Cannot shift-click items from output slot.
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack() && invSlot!=OUTPUT_SLOT) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.INV.size()) {
                if (!this.insertItem(originalStack, this.INV.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.INV.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.INV.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 92 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 150));
        }
    }
    public boolean isCrafting() {
        return this.PROPERTY_DELEGATE.get(0) > 0;
    }
    public boolean isLit() {
        return this.PROPERTY_DELEGATE.get(2) == 1;
    }
    public int getScaledArrowProgress() {
        int progress = this.PROPERTY_DELEGATE.get(0);
        int maxProgress = this.PROPERTY_DELEGATE.get(1); // Max Progress
        int arrowPixelSize = 36; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }
}
