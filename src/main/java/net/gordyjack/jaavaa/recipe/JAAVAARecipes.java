package net.gordyjack.jaavaa.recipe;

import net.gordyjack.jaavaa.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.*;
import net.minecraft.registry.*;

public final class JAAVAARecipes {
    public static void init() {
        JAAVAA.log("Initializing JAAVAA Recipes");
        Categories.init();
        Properties.init();
        Serializers.init();
        Types.init();
    }

    public static final class Categories {
        public static final RecipeBookCategory ALLOYING = register("alloying");
        public static final RecipeBookCategory RECYCLING = register("recycling");

        private static RecipeBookCategory register(String name) {
            return Registry.register(Registries.RECIPE_BOOK_CATEGORY, JAAVAA.id(name), new RecipeBookCategory());
        }
        private static void init() {
            JAAVAA.log("Initializing JAAVAA Recipe Categories");
        }
    }
    public static final class Properties {
        public static final RegistryKey<RecipePropertySet> RECYCLING = of("recycling");

        private static RegistryKey<RecipePropertySet> of(String id) {
            return RegistryKey.of(RecipePropertySet.REGISTRY, JAAVAA.id(id));
        }
        private static void init() {
            JAAVAA.log("Initializing JAAVAA Recipe Properties");
        }
    }
    public static final class Serializers {
        public static final RecipeSerializer<AlloyingRecipe> ALLOYING =
                register("alloying", new AlloyingRecipe.Serializer());
        public static final RecipeSerializer<RecyclingRecipe> RECYCLING =
                register("recycling", new RecyclingRecipe.Serializer());

        private static <T extends Recipe<?>> RecipeSerializer<T> register(String name, RecipeSerializer<T> serializer) {
            return Registry.register(Registries.RECIPE_SERIALIZER, JAAVAA.id(name), serializer);
        }
        private static void init() {
            JAAVAA.log("Initializing JAAVAA Recipe Serializers");
        }
    }
    public static final class Types {
        public static final RecipeType<AlloyingRecipe> ALLOYING = register("alloying");
        public static final RecipeType<RecyclingRecipe> RECYCLING = register("recycling");

        private static <T extends Recipe<?>> RecipeType<T> register(String name) {
            return Registry.register(Registries.RECIPE_TYPE, JAAVAA.id(name), new RecipeType<>() {
                @Override
                public String toString() {
                    return name;
                }
            });
        }
        private static void init() {
            JAAVAA.log("Initializing JAAVAA Recipe Types");
        }
    }
}
