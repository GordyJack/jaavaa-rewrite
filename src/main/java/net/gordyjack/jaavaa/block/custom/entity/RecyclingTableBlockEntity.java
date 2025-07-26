package net.gordyjack.jaavaa.block.custom.entity;

import net.fabricmc.fabric.api.screenhandler.v1.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.recipe.*;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.*;
import net.minecraft.screen.*;
import net.minecraft.server.network.*;
import net.minecraft.storage.*;
import net.minecraft.text.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class RecyclingTableBlockEntity
        extends BlockEntity
        implements SidedInventory, ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    //Constants
    private static final int INPUT = 0;
    private static final int OUTPUT_1 = 1;
    private static final int OUTPUT_2 = 2;
    private static final int OUTPUT_3 = 3;
    private static final int OUTPUT_4 = 4;
    private static final int[] OUTPUTS = {OUTPUT_1, OUTPUT_2, OUTPUT_3, OUTPUT_4};
    private static final int SIZE = 5;
    private static final int DEFAULT_MAX_PROGRESS = 200;
    //Final Instance Fields
    private final DefaultedList<ItemStack> INV = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
    private final PropertyDelegate PROPERTY_DELEGATE;
    //Instance Fields
    private int progress = 0;
    private int maxProgress = DEFAULT_MAX_PROGRESS;

    //Constructor
    public RecyclingTableBlockEntity(BlockPos pos, BlockState state) {
        super(JAAVAABlockEntityTypes.RECYCLING_TABLE_BLOCK_ENTITY_TYPE, pos, state);
        this.PROPERTY_DELEGATE = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RecyclingTableBlockEntity.this.progress;
                    case 1 -> RecyclingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RecyclingTableBlockEntity.this.progress = value;
                    case 1 -> RecyclingTableBlockEntity.this.maxProgress = value;
                }
            }
            @Override
            public int size() {
                return 2;
            }
        };
    }

    //Overrides
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot >= OUTPUT_1 && slot <= OUTPUT_4;
    }
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return slot == INPUT;
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RecyclingEntityScreenHandler(syncId, playerInventory, this, PROPERTY_DELEGATE);
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("container.jaavaa.recycling_table");
    }
    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.INV;
    }
    @Override
    protected void readData(ReadView view) {
        Inventories.readData(view, this.INV);
        this.progress = view.getInt("recycling_table.progress", 0);
        this.maxProgress = view.getInt("recycling_table.max_progress", DEFAULT_MAX_PROGRESS);
        super.readData(view);
    }
    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, this.INV);
        view.putInt("recycling_table.progress", this.progress);
        view.putInt("recycling_table.max_progress", this.maxProgress);
    }

    //Methods
    /**
     * Ticks the recycling table block entity.
     * @param world
     * @param pos
     * @param state
     */
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if(hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);
            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }
    /**
     * Checks if there is a valid recipe for the current input stack.
     * It retrieves the current recipe and checks if it can craft all output items.
     * @return true if a valid recipe exists and all outputs can be crafted, false otherwise
     */
    private boolean hasRecipe() {
        Optional<RecipeEntry<RecyclingRecipe>> recipeEntry = getCurrentRecipe();
        if (recipeEntry.isEmpty()) {
            return false;
        }
        RecyclingRecipe recipe = recipeEntry.get().value();
        List<ItemStack> outputs = recipe.outputs();
        this.maxProgress = recipe.crushTime();

        // Simulate craft to ensure no leftover remains
        return canCraftAllOutputs(outputs);
    }
    /**
     * Checks if all output items can be crafted with the current inventory state.
     * It simulates the distribution of output items across the output slots.
     * @param outputs the list of output ItemStacks from the current recipe
     * @return true if all outputs can be crafted, false otherwise
     */
    private boolean canCraftAllOutputs(List<ItemStack> outputs) {
        List<ItemStack> sim = new ArrayList<>();
        for (int slot : OUTPUTS) sim.add(getStack(slot).copy());

        for (ItemStack output : outputs) {
            if (distribute(output.copy(), sim) > 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Crafts the item based on the current recipe.
     * It removes the input stack from the input slot and distributes the output stacks across the output slots.
     * If the input stack is insufficient, it will not craft anything.
     */
    private void craftItem() {
        var recipe = getCurrentRecipe().get().value();
        removeStack(INPUT, recipe.inputStack().getCount());
        for (ItemStack output : recipe.outputs()) {
            distribute(output, null);
        }
    }
    /**
     * Distributes the output item stack across the available output slots.
     * It fills existing partial stacks first, then uses empty slots for any remaining items.
     * @param output the ItemStack to distribute
     * @param sim an optional list of ItemStacks to simulate the distribution without modifying the actual inventory
     *            if null, the actual inventory will be modified
     * @return the number of items that could not be distributed (remainder)
     */
    private int distribute(ItemStack output, @Nullable List<ItemStack> sim) {
        int remainder   = output.getCount();
        int maxCount    = output.getMaxCount();

        // 1. Fill existing partial stacks
        for (int i = 0; i < OUTPUTS.length && remainder > 0; i++) {
            ItemStack existing = sim != null ? sim.get(i) : getStack(OUTPUTS[i]);
            if (!existing.isEmpty()
                && existing.getItem() == output.getItem()
                && existing.getCount() < maxCount) {

                int space = maxCount - existing.getCount();
                int toAdd = Math.min(space, remainder);
                existing.setCount(existing.getCount() + toAdd);
                if (sim != null) sim.set(i, existing);
                else setStack(OUTPUTS[i], existing);
                remainder -= toAdd;
            }
        }
        // 2. Use empty slots for leftovers
        for (int i = 0; i < OUTPUTS.length && remainder > 0; i++) {
            ItemStack existing = sim != null ? sim.get(i) : getStack(OUTPUTS[i]);
            if (existing.isEmpty()) {
                int toAdd = Math.min(maxCount, remainder);
                ItemStack newStack = new ItemStack(output.getItem(), toAdd);
                if (sim != null) sim.set(i, newStack);
                else setStack(OUTPUTS[i], newStack);
                remainder -= toAdd;
            }
        }
        return remainder;
    }

    /**
     * Retrieves the current recycling recipe based on the input stack.
     * @return An Optional containing the RecipeEntry if a matching recipe is found, otherwise an empty Optional.
     */
    private Optional<RecipeEntry<RecyclingRecipe>> getCurrentRecipe() {
        return ((ServerRecipeManager)this.getWorld().getRecipeManager())
                .getFirstMatch(JAAVAARecipes.Types.RECYCLING, new SingleStackRecipeInput(INV.get(INPUT)), this.getWorld());
    }
    //Progress interaction
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }
    private void increaseCraftingProgress() {
        this.progress++;
    }
    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = this.getCurrentRecipe().isPresent() ? this.getCurrentRecipe().get().value().crushTime() : DEFAULT_MAX_PROGRESS;
    }
}
