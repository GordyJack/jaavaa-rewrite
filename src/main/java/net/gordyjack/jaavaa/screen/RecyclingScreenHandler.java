package net.gordyjack.jaavaa.screen;

import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;

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
    private final Inventory RESULT = new CraftingResultInventory();
    private final ScreenHandlerContext CONTEXT;
    private final PlayerEntity PLAYER;

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
        this.PLAYER = playerInventory.player;

        this.addSlot(new Slot(INPUT, 0, 49, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(JAAVAATags.Items.RECYCLABLE);
            }
        });
        this.addSlot(new Slot(RESULT, 0, 129, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                Random random = new Random();
                context.run((world, pos) -> {
                    if (world instanceof ServerWorld) {
                        ExperienceOrbEntity.spawn((ServerWorld)world, Vec3d.ofCenter(pos), random.nextInt(0, 5));
                    }
                });
                RecyclingScreenHandler.this.INPUT.setStack(0, ItemStack.EMPTY);
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
        }
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        //EasyRecycling.logError("quickMove");
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasStack()) {
            //EasyRecycling.logError("hasStack");
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();

            boolean INVENTORY_SLOT = slotIndex >= PLAYER_INVENTORY_START && slotIndex <= PLAYER_INVENTORY_END;
            boolean HOTBAR_SLOT = slotIndex >= HOTBAR_START && slotIndex <= HOTBAR_END;

            if (slotIndex == OUTPUT_SLOT) {
                //EasyRecycling.logError("OUTPUT_SLOT");
                if (!this.insertItem(itemStack2, PLAYER_INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(itemStack2, itemStack);
            } else if (slotIndex == INPUT_SLOT && !this.insertItem(itemStack2, PLAYER_INVENTORY_START, HOTBAR_END, false)) {
                //EasyRecycling.logError("INPUT_SLOT");
                return ItemStack.EMPTY;
            } else if (this.INPUT.getStack(0).isEmpty() && !this.insertItem(itemStack2, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                //EasyRecycling.logError("INPUT EMPTY");
                return ItemStack.EMPTY;
            } else if (INVENTORY_SLOT && !this.insertItem(itemStack2, HOTBAR_START, HOTBAR_END, false)) {
                //EasyRecycling.logError("INVENTORY_SLOT");
                return ItemStack.EMPTY;
            } else if (HOTBAR_SLOT && !this.insertItem(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, false)) {
                //EasyRecycling.logError("HOTBAR_SLOT");
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                //EasyRecycling.logError("itemStack2 Empty");
                slot.setStack(ItemStack.EMPTY);
            } else {
                //EasyRecycling.logError("markDirty");
                slot.markDirty();
            }
            if (itemStack2.getCount() == itemStack.getCount()) {
                //EasyRecycling.logError("EQUALS");
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }

    //TODO: Switch this to use a recipe system.
    private void updateResult() {
        ItemStack input = this.INPUT.getStack(0);
        Item inputItem = input.getItem();
        Item outputItem = Items.AIR;

        if (!input.equals(ItemStack.EMPTY)) {

            boolean wood = itemIs("wood", inputItem);
            boolean stone = itemIs("stone", inputItem);
            boolean iron = itemIs("iron", inputItem) || itemIs("chainmail", inputItem);
            boolean gold = itemIs("gold", inputItem);
            boolean diamond = itemIs("diamond", inputItem);
            boolean netherite = itemIs("netherite", inputItem) || inputItem == JAAVAAItems.TOOL_OF_THE_ANCIENTS;
            boolean leather = itemIs("leather", inputItem);
            boolean turtle = itemIs("turtle", inputItem);

            boolean axe = inputItem instanceof AxeItem;
            boolean hoe = inputItem instanceof HoeItem;
            boolean pickaxe = inputItem instanceof PickaxeItem;
            boolean shovel = inputItem instanceof ShovelItem;
            boolean sword = inputItem instanceof SwordItem;
            boolean paxel = inputItem instanceof PaxelItem;

            boolean helmet = itemIs("helmet", inputItem);
            boolean chestplate = itemIs("chestplate", inputItem);
            boolean legging = itemIs("legging", inputItem);
            boolean boot = itemIs("boot", inputItem);

            int itemCount = 0;

            if (inputItem.equals(Items.ELYTRA)) {
                itemCount = 2;
                outputItem = Items.PHANTOM_MEMBRANE;
            } else if (inputItem.equals(Items.MACE)) {
                itemCount = 1;
                outputItem = Items.HEAVY_CORE;
            } else if (inputItem.equals(Items.TRIDENT)) {
                itemCount = 3;
                outputItem = Items.PRISMARINE_SHARD;
            } else if (axe || pickaxe) itemCount = 3;
            else if (hoe || sword) itemCount = 2;
            else if (shovel) itemCount = 1;
            else if (paxel) itemCount = 5;
            else if (helmet) itemCount = 5;
            else if (chestplate) itemCount = 8;
            else if (legging) itemCount = 7;
            else if (boot) itemCount = 4;

            if (wood) {
                itemCount *= 2;
                outputItem = Items.STICK;
            } else if (stone) outputItem = Items.COBBLESTONE;
            else if (leather) outputItem = Items.LEATHER;
            else if (iron) outputItem = Items.IRON_INGOT;
            else if (gold) outputItem = Items.GOLD_INGOT;
            else if (diamond) outputItem = Items.DIAMOND;
            else if (netherite) {
                itemCount = 1;
                outputItem = Items.NETHERITE_INGOT;
            } else if (turtle) outputItem = Items.TURTLE_SCUTE;
            itemCount *= (int) ((double) (input.getMaxDamage() - input.getDamage()) / input.getMaxDamage());

            this.RESULT.setStack(0, itemCount > 0 ? new ItemStack(outputItem, itemCount) : ItemStack.EMPTY);
        } else {
            this.RESULT.setStack(0, ItemStack.EMPTY);
        }
        this.sendContentUpdates();
    }
    private boolean itemIs(String type, Item item) {
        return item.getTranslationKey().contains(type);
    }
}
