package net.gordyjack.jaavaa.block.custom.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.recipe.AlloyingRecipe;
import net.gordyjack.jaavaa.recipe.AlloyingRecipeInput;
import net.gordyjack.jaavaa.recipe.JAAVAARecipes;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.*;

import java.util.Optional;

public class AlloyFurnaceBlockEntity
        extends BlockEntity
        implements SidedInventory, ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int SIZE = 3;
    private static final int DEFAULT_MAX_PROGRESS = 200;
    private final BlockPos BLOCK_POS;
    private BlockState blockState;
    private final DefaultedList<ItemStack> INV = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
    private final PropertyDelegate PROPERTY_DELEGATE;
    private int progress = 0;
    private int maxProgress = 200;

    public AlloyFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(JAAVAABlockEntityTypes.ALLOY_FURNACE_BLOCK_ENTITY_TYPE, blockPos, blockState);
        this.BLOCK_POS = blockPos;
        this.blockState = blockState;
        this.PROPERTY_DELEGATE = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.progress;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: AlloyFurnaceBlockEntity.this.progress = value;
                    case 1: AlloyFurnaceBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public boolean isLit() {
        return this.blockState.get(AlloyFurnaceBlock.LIT);
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
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.INV, registryLookup);
        nbt.putInt("alloy_furnace.progress", this.progress);
        nbt.putInt("alloy_furnace.max_progress", this.maxProgress);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, this.INV, registryLookup);
        this.progress = nbt.getInt("alloy_furnace.progress");
        this.maxProgress = nbt.getInt("alloy_furnace.max_progress");
        super.readNbt(nbt, registryLookup);
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe() && canInsertIntoOutputSlot() && isLit()) {
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
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }
    private void craftItem() {
        Optional<RecipeEntry<AlloyingRecipe>> recipe = getCurrentRecipe();
        ItemStack input1 = recipe.get().value().inputStack1();
        ItemStack input2 = recipe.get().value().inputStack2();
        int input1Count = input1.getCount();
        int input2Count = input2.getCount();

        boolean input1is1 = this.getStack(INPUT_SLOT_1).getItem() == input1.getItem();
        this.removeStack(INPUT_SLOT_1, input1is1 ? input1Count : input2Count);
        this.removeStack(INPUT_SLOT_2, input1is1 ? input2Count : input1Count);
        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().output().getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + recipe.get().value().output().getCount()));
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }
    private void increaseCraftingProgress() {
        this.progress++;
    }
    private boolean canInsertIntoOutputSlot() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
    private boolean hasRecipe() {
        Optional<RecipeEntry<AlloyingRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().getResult();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<AlloyingRecipe>> getCurrentRecipe() {
        return ((ServerRecipeManager)this.getWorld().getRecipeManager())
                .getFirstMatch(JAAVAARecipes.Types.ALLOY_FURNACE, new AlloyingRecipeInput(INV.get(INPUT_SLOT_1), INV.get(INPUT_SLOT_2)), this.getWorld());
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
