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
    private static final int PLAYER_INVENTORY_START = OUTPUT_SLOT + 1; //2
    private static final int PLAYER_INVENTORY_END = PLAYER_INVENTORY_START + 27; //29
    private static final int HOTBAR_START = PLAYER_INVENTORY_END + 1; //30
    private static final int HOTBAR_END = HOTBAR_START + 8; //38
    private final ScreenHandlerContext CONTEXT;
    private final Inventory INV;
    private final PropertyDelegate PROPERTY_DELEGATE;


    public AlloyFurnaceBlockEntityScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(3));
    }
    public AlloyFurnaceBlockEntityScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(JAAVAAScreenHandlers.ALLOY_FURNACE_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 3);
        this.CONTEXT = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());
        this.INV = (Inventory) blockEntity;
        this.PROPERTY_DELEGATE = propertyDelegate;
        
        this.addSlot(new Slot(INV, INPUT_SLOT_1, 53, 17));
        this.addSlot(new Slot(INV, INPUT_SLOT_2, 107, 17));
        this.addSlot(new OutputSlot(INV, OUTPUT_SLOT, 79, 57));
        
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        boolean isInventorySlot = slotIndex >= PLAYER_INVENTORY_START && slotIndex <= PLAYER_INVENTORY_END;
        boolean isHotbarSlot = slotIndex >= HOTBAR_START && slotIndex <= HOTBAR_END;

        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (slotIndex == OUTPUT_SLOT) {
                this.CONTEXT.run((world, pos) -> originalStack.getItem().onCraftByPlayer(originalStack, player));
                if (!this.insertItem(originalStack, PLAYER_INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, newStack);
            } else if ((slotIndex == INPUT_SLOT_1 || slotIndex == INPUT_SLOT_2) && !this.insertItem(originalStack, PLAYER_INVENTORY_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            } else if (this.INV.getStack(INPUT_SLOT_1).isEmpty() && !this.insertItem(originalStack, INPUT_SLOT_1, INPUT_SLOT_1 + 1, false)) {
                return ItemStack.EMPTY;
            } else if (this.INV.getStack(INPUT_SLOT_2).isEmpty() && !this.insertItem(originalStack, INPUT_SLOT_2, INPUT_SLOT_2 + 1, false)) {
                return ItemStack.EMPTY;
            } else if (isInventorySlot && !this.insertItem(originalStack, HOTBAR_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            } else if (isHotbarSlot && !this.insertItem(originalStack, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, false)) {
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
