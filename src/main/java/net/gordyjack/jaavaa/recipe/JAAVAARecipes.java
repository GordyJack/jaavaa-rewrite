package net.gordyjack.jaavaa.recipe;

import net.gordyjack.jaavaa.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.*;
import net.minecraft.registry.*;

public class JAAVAARecipes {
    public static final RecipeBookCategory ALLOY_FURNACE_RECIPE_CATEGORY = registerCategory("alloying");
    public static final RecipeSerializer<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE_SERIALIZER =
            registerSerializer("alloying", new AlloyFurnaceRecipe.Serializer());
    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE_TYPE = registerType("alloying");

    public static final RecipeBookCategory RECYCLING_RECIPE_CATEGORY = registerCategory("recycling");
    public static final RecipeSerializer<RecyclingRecipe> RECYCLING_RECIPE_SERIALIZER =
            registerSerializer("recycling", new RecyclingRecipe.Serializer());
    public static final RecipeType<RecyclingRecipe> RECYCLING_RECIPE_TYPE = registerType("recycling");

    private static RecipeBookCategory registerCategory(String name) {
        return Registry.register(Registries.RECIPE_BOOK_CATEGORY, JAAVAA.id(name), new RecipeBookCategory());
    }
    private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(String name, RecipeSerializer<T> serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, JAAVAA.id(name), serializer);
    }
    private static <T extends Recipe<?>> RecipeType<T> registerType(String name) {
        return Registry.register(Registries.RECIPE_TYPE, JAAVAA.id(name), new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }
    public static void init() {
        JAAVAA.log("Initializing JAAVAA Recipes");
    }
}
