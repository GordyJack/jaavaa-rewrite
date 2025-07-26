package net.gordyjack.jaavaa.recipe;

import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.*;
import net.minecraft.registry.*;

import java.util.*;

/**
 * A recipe is an arrangement of items in an inventory that can
 * yield a product item stack. <strong>Recipes are not used on the client side</strong>;
 * the server syncs to the client a {@link RecipeDisplayEntry},
 * which is used instead.
 *
 * <p>Recipes are loaded by and stored in the {@link ServerRecipeManager}. They
 * are part of the server's data packs. Hence, recipes should not be stored,
 * as they may become obsolete after reloads.
 *
 * <p>{@link RecipeEntry} is a pair of the recipe and its ID ({@linkplain
 * net.minecraft.registry.RegistryKey a registry key}). The ID can be used to
 * refer to recipes in saved data. However, the client does not receive the ID of
 * recipes.
 *
 * <p>A few of the methods in this class are dedicated to crafting recipes
 * or recipe books. Users can have stub implementations if they do not use
 * those functionalities.
 */
public interface MultiOutputRecipe<T extends RecipeInput> extends Recipe<T> {
    /**
     * Crafts this recipe.
     *
     * <p>This method does not perform side effects on the {@code inventory}.
     *
     * <p>This method should return a new item stack on each call.
     *
     * @return the resulting item stack
     */
    List<ItemStack> craftStacks(T input, RegistryWrapper.WrapperLookup registries);
}
