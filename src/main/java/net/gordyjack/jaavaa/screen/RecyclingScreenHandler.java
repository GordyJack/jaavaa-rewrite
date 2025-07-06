package net.gordyjack.jaavaa.screen;

import net.gordyjack.jaavaa.recipe.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class RecyclingScreenHandler extends ScreenHandler {
    //Fields
    private final Inventory INPUT = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            RecyclingScreenHandler.this.onContentChanged(this);
        }
    };
    private final CraftingResultInventory RESULT = new CraftingResultInventory() {
        @Override
        public void markDirty() {
            super.markDirty();
            RecyclingScreenHandler.this.sendContentUpdates();
        }
    };
    private final ScreenHandlerContext CONTEXT;
    private final World WORLD;

    private final int INPUT_SLOT = 0;
    private final int OUTPUT_SLOT = 1;
    private final int PLAYER_INVENTORY_START = OUTPUT_SLOT + 1; //2
    private final int HOTBAR_START = PLAYER_INVENTORY_START + 27; //29
    private final int PLAYER_INVENTORY_END = HOTBAR_START - 1; //28
    private final int HOTBAR_END = HOTBAR_START + 8; //38

    //Constructors
    public RecyclingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }
    public RecyclingScreenHandler(int syncId, PlayerInventory playerInventory, final ScreenHandlerContext context) {
        super(JAAVAAScreenHandlers.RECYCLING_SCREEN_HANDLER, syncId);
        this.CONTEXT = context;
        this.WORLD = playerInventory.player.getWorld();

        this.addSlot(new Slot(INPUT, 0, 49, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return RecyclingScreenHandler.this.isInput(stack);
            }
        });
        this.addSlot(new RecyclingResultSlot(playerInventory.player, INPUT, RESULT, 0, 129, 34) {
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);
                RecyclingScreenHandler.this.CONTEXT.run((world, pos) -> {
                    if (world instanceof ServerWorld serverWorld) {
                        try {
                            float experience = ((RecyclingRecipe)RESULT.getLastRecipe().value()).experience();
                            ExperienceOrbEntity.spawn(serverWorld, Vec3d.ofCenter(pos), (int)(experience * 10));
                        } catch (NullPointerException e) {
                            //Do nothing
                        }
                    }
                });
            }
        });
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    //Overrides
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.INPUT.canPlayerUse(player);
    }
    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.CONTEXT.run((world, pos) -> this.dropInventory(player, this.INPUT));
    }
    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.INPUT) {
            this.updateResult();
            this.sendContentUpdates();
        }
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();

            boolean isInventorySlot = slotIndex >= PLAYER_INVENTORY_START && slotIndex <= PLAYER_INVENTORY_END;
            boolean isHotbarSlot = slotIndex >= HOTBAR_START && slotIndex <= HOTBAR_END;

            if (slotIndex == OUTPUT_SLOT) {
                this.CONTEXT.run((world, pos) -> itemStack2.getItem().onCraftByPlayer(itemStack2, player));
                if (!this.insertItem(itemStack2, PLAYER_INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(itemStack2, itemStack);
            } else if (slotIndex == INPUT_SLOT && !this.insertItem(itemStack2, PLAYER_INVENTORY_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            } else if (this.INPUT.getStack(0).isEmpty() && !this.insertItem(itemStack2, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            } else if (isInventorySlot && !this.insertItem(itemStack2, HOTBAR_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            } else if (isHotbarSlot && !this.insertItem(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }
            slot.markDirty();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }

    private void updateResult() {
        var recipeInput = this.createRecipeInput();
        Optional<RecipeEntry<RecyclingRecipe>> optional = this.WORLD instanceof ServerWorld serverWorld ?
                serverWorld.getRecipeManager().getFirstMatch(JAAVAARecipes.Types.RECYCLING, recipeInput, serverWorld)
                : Optional.empty();
        optional.ifPresentOrElse(recipe -> {
            ItemStack itemStack = (recipe.value()).craft(recipeInput, this.WORLD.getRegistryManager());
            this.RESULT.setLastRecipe(recipe);
            this.RESULT.setStack(0, itemStack);
        }, () -> {
            this.RESULT.setLastRecipe(null);
            this.RESULT.setStack(0, ItemStack.EMPTY);
        });
    }
    private SingleStackRecipeInput createRecipeInput() {
        return this.createRecipeInput(this.INPUT.getStack(0));
    }
    private SingleStackRecipeInput createRecipeInput(ItemStack stack) {
        return new SingleStackRecipeInput(stack);
    }
    private void decrementInput() {
        this.decrementInput(1);
    }
    private void decrementInput(int count) {
        ItemStack itemStack = this.INPUT.getStack(INPUT_SLOT);
        if (!itemStack.isEmpty()) {
            itemStack.decrement(count);
            this.INPUT.setStack(INPUT_SLOT, itemStack);
            this.INPUT.markDirty();
        }
    }
    public boolean isEmpty() {
        return this.INPUT.isEmpty() && this.RESULT.isEmpty();
    }
    private boolean isInput(ItemStack stack) {
        if (this.WORLD instanceof ServerWorld serverWorld) {
            return serverWorld.getRecipeManager()
                    .getFirstMatch(JAAVAARecipes.Types.RECYCLING, this.createRecipeInput(stack), serverWorld)
                    .isPresent();
        }
        return false;
    }
    public boolean hasRecipe() {
        var inputStack = this.INPUT.getStack(INPUT_SLOT);
        var outputStack = this.RESULT.getStack(OUTPUT_SLOT);
        return !inputStack.isEmpty() && !outputStack.isEmpty();
    }
}
