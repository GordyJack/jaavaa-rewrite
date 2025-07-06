package net.gordyjack.jaavaa.block.custom.entity;

import net.fabricmc.fabric.api.screenhandler.v1.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.recipe.*;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.recipe.*;
import net.minecraft.screen.*;
import net.minecraft.server.network.*;
import net.minecraft.storage.*;
import net.minecraft.text.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class AlloyFurnaceBlockEntity
        extends BlockEntity
        implements SidedInventory, ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int SIZE = 3;
    private static final int DEFAULT_MAX_PROGRESS = 200;
    private final DefaultedList<ItemStack> INV = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
    private final PropertyDelegate PROPERTY_DELEGATE;
    private int progress = 0;
    private int maxProgress = DEFAULT_MAX_PROGRESS;
    private boolean isLit = false;

    public AlloyFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(JAAVAABlockEntityTypes.ALLOY_FURNACE_BLOCK_ENTITY_TYPE, blockPos, blockState);
        this.isLit = blockState.get(AlloyFurnaceBlock.LIT);
        this.PROPERTY_DELEGATE = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.progress;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxProgress;
                    case 2 -> AlloyFurnaceBlockEntity.this.isLit ? 1 : 0;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.progress = value;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> AlloyFurnaceBlockEntity.this.isLit = value == 1;
                }
            }
            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }
    /**
     * Returns the title of this screen handler; will be a part of the open
     * screen packet sent to the client.
     */
    @Override
    public Text getDisplayName() {
        return Text.translatable("container.jaavaa.alloy_furnace");
    }
    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     *
     * @return the item list
     */
    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.INV;
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlloyFurnaceBlockEntityScreenHandler(syncId, playerInventory, this, PROPERTY_DELEGATE);
    }
    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, this.INV);
        view.putInt("alloy_furnace.progress", this.progress);
        view.putInt("alloy_furnace.max_progress", this.maxProgress);
        view.putBoolean("alloy_furnace.is_lit", this.isLit);
    }
    @Override
    protected void readData(ReadView view) {
        Inventories.readData(view, this.INV);
        this.progress = view.getInt("alloy_furnace.progress", 0);
        this.maxProgress = view.getInt("alloy_furnace.max_progress", DEFAULT_MAX_PROGRESS);
        this.isLit = view.getBoolean("alloy_furnace.is_lit", false);
        super.readData(view);
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if (state.get(AlloyFurnaceBlock.LIT) != this.isLit) {
            this.isLit = state.get(AlloyFurnaceBlock.LIT);
        }
        if(hasRecipe() && isOutputNotFull() && isLit) {
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
    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = this.getCurrentRecipe().isPresent() ? this.getCurrentRecipe().get().value().burnTime() : DEFAULT_MAX_PROGRESS;
    }
    private void craftItem() {
        var recipe = getCurrentRecipe().get().value();
        ItemStack input1 = recipe.inputStack1();
        ItemStack input2 = recipe.inputStack2();
        int input1Count = input1.getCount();
        int input2Count = input2.getCount();

        boolean input1is1 = this.getStack(INPUT_SLOT_1).getItem() == input1.getItem();
        this.removeStack(INPUT_SLOT_1, input1is1 ? input1Count : input2Count);
        this.removeStack(INPUT_SLOT_2, input1is1 ? input2Count : input1Count);
        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.output().getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + recipe.output().getCount()));
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }
    private void increaseCraftingProgress() {
        this.progress++;
    }
    private boolean isOutputNotFull() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
    private boolean hasRecipe() {
        Optional<RecipeEntry<AlloyingRecipe>> recipeEntry = getCurrentRecipe();
        if(recipeEntry.isEmpty()) {
            return false;
        }
        AlloyingRecipe recipe = recipeEntry.get().value();
        ItemStack output = recipe.output();
        this.maxProgress = recipe.burnTime();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<AlloyingRecipe>> getCurrentRecipe() {
        return ((ServerRecipeManager)this.getWorld().getRecipeManager())
                .getFirstMatch(JAAVAARecipes.Types.ALLOYING, new AlloyingRecipeInput(INV.get(INPUT_SLOT_1), INV.get(INPUT_SLOT_2)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
