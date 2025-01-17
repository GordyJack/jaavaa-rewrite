package net.gordyjack.jaavaa.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record AlloyingRecipeInput(ItemStack input1, ItemStack input2)
implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch(slot) {
            case 0 -> input1;
            case 1 -> input2;
            default -> ItemStack.EMPTY;
        };
    }
    @Override
    public int size() {
        return 2;
    }
}
