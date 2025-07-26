package net.gordyjack.jaavaa.screen;

import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import net.minecraft.util.math.*;

import java.util.*;

public class RecyclingEntityScreenHandler
        extends ScreenHandler {
    //Constants
    private static final int INPUT = 0;
    private static final int OUTPUT_1 = 1;
    private static final int OUTPUT_2 = 2;
    private static final int OUTPUT_3 = 3;
    private static final int OUTPUT_4 = 4;
    private static final int SIZE = 5;
    private static final int PLAYER_INVENTORY_START = SIZE; //5
    private static final int PLAYER_INVENTORY_END = PLAYER_INVENTORY_START + 27; //32
    private static final int HOTBAR_START = PLAYER_INVENTORY_END + 1; //33
    private static final int HOTBAR_END = HOTBAR_START + 8; //41
    //Final Instance Fields
    private final ScreenHandlerContext CONTEXT;
    private final Inventory INV;
    private final PropertyDelegate PROPERTY_DELEGATE;

    public RecyclingEntityScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(3));
    }
    public RecyclingEntityScreenHandler(int syncId, PlayerInventory playerInventory,
                                                BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(JAAVAAScreenHandlers.RECYCLING_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 3);
        this.CONTEXT = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());
        this.INV = (Inventory) blockEntity;
        this.PROPERTY_DELEGATE = propertyDelegate;

        this.addSlot(new Slot(INV, INPUT, 26, 34));
        this.addSlot(new OutputSlot(INV, OUTPUT_1, 116, 25));
        this.addSlot(new OutputSlot(INV, OUTPUT_2, 134, 25));
        this.addSlot(new OutputSlot(INV, OUTPUT_3, 116, 43));
        this.addSlot(new OutputSlot(INV, OUTPUT_4, 134, 43));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    //Overrides
    /**
     * {@return whether the screen handler can be used}
     *
     * <p>Subclasses should call #canUse(ScreenHandlerContext, PlayerEntity, Block)}
     * or implement the check itself. The implementation should check that the player is near the screen handler's
     * source position (e.g. block position) and that the source (e.g. block) is not destroyed.
     *
     * @param player
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.INV.canPlayerUse(player);
    }
    /**
     * Quick-moves the stack at {@code slot} to other slots of the screen handler that belong to a different inventory
     * or another section of the same inventory. For example, items can be quick-moved between a chest's slots and the
     * player inventory or between the main player inventory and the hotbar.
     *
     * <p>Subclasses should call {@link #insertItem}, and if the insertion was successful,
     * clear the slot (if the stack is exhausted) or mark it as dirty. See the vanilla subclasses for basic
     * implementation.
     *
     * <p>Quick-moving is also known as "shift-clicking" since it's usually triggered
     * using <kbd>Shift</kbd>+<kbd>left click</kbd>.
     *
     * @param player
     * @param slotIndex   the index of the slot to quick-move from
     * @return {@link ItemStack#EMPTY} when no stack can be transferred, otherwise the original stack
     *
     * @see #insertItem
     */
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack tempStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            tempStack = slotStack.copy();

            boolean isInventorySlot = slotIndex >= PLAYER_INVENTORY_START && slotIndex <= PLAYER_INVENTORY_END;
            boolean isHotbarSlot = slotIndex >= HOTBAR_START && slotIndex <= HOTBAR_END;

            int[] outputSlots = {OUTPUT_1, OUTPUT_2, OUTPUT_3, OUTPUT_4};
            if (Arrays.stream(outputSlots).anyMatch(outputSlot -> outputSlot == slotIndex)) {
                this.CONTEXT.run((world, pos) -> slotStack.getItem().onCraftByPlayer(slotStack, player));
                if (!this.insertItem(slotStack, PLAYER_INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(slotStack, tempStack);
            } else if (slotIndex == INPUT && !this.insertItem(slotStack, PLAYER_INVENTORY_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            } else if (this.INV.getStack(INPUT).isEmpty() && !this.insertItem(slotStack, INPUT, INPUT + 1, false)) {
                return ItemStack.EMPTY;
            } else if (isInventorySlot && !this.insertItem(slotStack, HOTBAR_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            } else if (isHotbarSlot && !this.insertItem(slotStack, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }
            slot.markDirty();
            if (slotStack.getCount() == tempStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, slotStack);
        }
        return tempStack;
    }

    //Methods
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    public boolean isCrafting() {
        return this.PROPERTY_DELEGATE.get(0) > 0;
    }
    public int getScaledArrowProgress() {
        int progress = this.PROPERTY_DELEGATE.get(0);
        int maxProgress = this.PROPERTY_DELEGATE.get(1); // Max Progress
        int arrowPixelSize = 51; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }
}
