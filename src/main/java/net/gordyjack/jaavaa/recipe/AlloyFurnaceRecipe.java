package net.gordyjack.jaavaa.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.*;
import net.minecraft.registry.*;
import net.minecraft.world.*;

import java.util.*;

public record AlloyFurnaceRecipe(int burnTime, float experience, ItemStack inputStack1, ItemStack inputStack2, ItemStack output)
        implements Recipe<AlloyFurnaceRecipeInput> {

    @Override
    public boolean matches(AlloyFurnaceRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }
        return (input.getStackInSlot(0).itemMatches(inputStack1.getRegistryEntry()) && input.getStackInSlot(1).itemMatches(inputStack2.getRegistryEntry())) ||
                (input.getStackInSlot(0).itemMatches(inputStack2.getRegistryEntry()) && input.getStackInSlot(1).itemMatches(inputStack1.getRegistryEntry()));
    }

    @Override
    public ItemStack craft(AlloyFurnaceRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<AlloyFurnaceRecipeInput>> getSerializer() {
        return JAAVAARecipes.ALLOY_FURNACE_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<AlloyFurnaceRecipeInput>> getType() {
        return JAAVAARecipes.ALLOY_FURNACE_RECIPE_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(List.of(Optional.of(Ingredient.ofItem(inputStack1.getItem())), Optional.of(Ingredient.ofItem(inputStack2.getItem()))));
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return JAAVAARecipes.ALLOY_FURNACE_RECIPE_CATEGORY;
    }

    public ItemStack getResult() {
        return output;
    }

    public static class Serializer implements RecipeSerializer<AlloyFurnaceRecipe> {
        public static final MapCodec<AlloyFurnaceRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.INT.fieldOf("burnTime").forGetter(AlloyFurnaceRecipe::burnTime),
                Codec.FLOAT.fieldOf("experience").forGetter(AlloyFurnaceRecipe::experience),
                ItemStack.CODEC.fieldOf("inputStack1").forGetter(AlloyFurnaceRecipe::inputStack1),
                ItemStack.CODEC.fieldOf("inputStack2").forGetter(AlloyFurnaceRecipe::inputStack2),
                ItemStack.CODEC.fieldOf("result").forGetter(AlloyFurnaceRecipe::output)
        ).apply(inst, AlloyFurnaceRecipe::new));
        public static final PacketCodec<RegistryByteBuf, AlloyFurnaceRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.INTEGER, AlloyFurnaceRecipe::burnTime,
                        PacketCodecs.FLOAT, AlloyFurnaceRecipe::experience,
                        ItemStack.PACKET_CODEC, AlloyFurnaceRecipe::inputStack1,
                        ItemStack.PACKET_CODEC, AlloyFurnaceRecipe::inputStack2,
                        ItemStack.PACKET_CODEC, AlloyFurnaceRecipe::output,
                        AlloyFurnaceRecipe::new);

        @Override
        public MapCodec<AlloyFurnaceRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, AlloyFurnaceRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}