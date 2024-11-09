package net.gordyjack.jaavaa.recipe;

import net.gordyjack.jaavaa.JAAVAA;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class JAAVAARecipes {
    public static final RecipeSerializer<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, JAAVAA.id("alloying"), new AlloyFurnaceRecipe.Serializer());
    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, JAAVAA.id("alloying"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "alloying";
                }
            });
    public static final RecipeBookCategory ALLOY_FURNACE_RECIPE_CATEGORY = createCategory("alloying");

    private static RecipeBookCategory createCategory(String name) {
        return Registry.register(Registries.RECIPE_BOOK_CATEGORY, JAAVAA.id(name), new RecipeBookCategory());
    }
    public static void init() {
        JAAVAA.log("Initializing JAAVAA Recipes");
    }
}
