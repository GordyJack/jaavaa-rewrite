package net.gordyjack.jaavaa.screen;

import net.gordyjack.jaavaa.block.custom.entity.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import net.minecraft.util.math.*;

public class AlloyFurnaceBlockEntityScreenHandler extends ScreenHandler {
    private final Inventory INV;
    private final AlloyFurnaceBlockEntity ALLOY_FURNACE_BLOCK_ENTITY;
    
    public AlloyFurnaceBlockEntityScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos));
    }
    public AlloyFurnaceBlockEntityScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity) {
        super(JAAVAAScreenHandlers.ALLOY_FURNACE_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 3);
        this.INV = (Inventory) blockEntity;
        this.ALLOY_FURNACE_BLOCK_ENTITY = ((AlloyFurnaceBlockEntity) blockEntity);
        
        this.addSlot(new Slot(INV, 0, 53, 9));
        this.addSlot(new Slot(INV, 1, 107, 9));
        this.addSlot(new Slot(INV, 2, 79, 49));
        
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return !player.isSneaking();
    }
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
    public boolean isLit() {
        return this.ALLOY_FURNACE_BLOCK_ENTITY.isLit();
    }
}
