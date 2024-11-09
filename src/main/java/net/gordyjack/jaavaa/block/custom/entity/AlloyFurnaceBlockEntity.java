package net.gordyjack.jaavaa.block.custom.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.screen.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

public class AlloyFurnaceBlockEntity
        extends LockableContainerBlockEntity
        implements SidedInventory, RecipeUnlocker, RecipeInputProvider, ExtendedScreenHandlerFactory {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int SIZE = 3;
    private final BlockPos BLOCK_POS;
    private final BlockState BLOCK_STATE;
    private RecipeEntry<?> lastRecipe;
    
    private final DefaultedList<ItemStack> INVENTORY = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
    
    public AlloyFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(JAAVAABlockEntityTypes.ALLOY_FURNACE_BLOCK_ENTITY_TYPE, blockPos, blockState);
        this.BLOCK_POS = blockPos;
        this.BLOCK_STATE = blockState;
    }
    @Override
    protected Text getContainerName() {
        return Text.translatable("container.jaavaa.alloy_furnace");
    }
    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.INVENTORY;
    }
    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.INVENTORY.set(i, inventory.get(i));
        }
    }
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AlloyFurnaceBlockEntityScreenHandler(syncId, playerInventory, this.pos);
    }
    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if (dir != null) {
            return (slot == INPUT_SLOT_1 && dir == Direction.UP) || (slot == INPUT_SLOT_2 && dir.getAxis().isHorizontal());
        }
        return false;
    }
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot==OUTPUT_SLOT && dir==Direction.DOWN;
    }
    @Override
    public int size() {
        return SIZE;
    }
    @Override
    public void provideRecipeInputs(RecipeFinder finder) {
        finder.addInput(this.INVENTORY.get(INPUT_SLOT_1));
        finder.addInput(this.INVENTORY.get(INPUT_SLOT_2));
    }
    @Override
    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {
        this.lastRecipe = recipe;
    }
    @Override
    public @Nullable RecipeEntry<?> getLastRecipe() {
        return this.lastRecipe;
    }
    public boolean isLit() {
        return this.BLOCK_STATE.get(AlloyFurnaceBlock.LIT);
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }
}
