package net.gordyjack.jaavaa.recipe;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sun.jdi.connect.Connector;
import net.gordyjack.jaavaa.JAAVAA;
import net.minecraft.command.argument.serialize.IntegerArgumentSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

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
        ItemStack result = output.copy();
        return result;
    }

    @Override
    public RecipeSerializer<? extends Recipe<AlloyFurnaceRecipeInput>> getSerializer() {
        RecipeSerializer<? extends Recipe<AlloyFurnaceRecipeInput>> serializer = JAAVAARecipes.ALLOY_FURNACE_RECIPE_SERIALIZER;
        return serializer;
    }

    @Override
    public RecipeType<? extends Recipe<AlloyFurnaceRecipeInput>> getType() {
        RecipeType<? extends Recipe<AlloyFurnaceRecipeInput>> type = JAAVAARecipes.ALLOY_FURNACE_RECIPE_TYPE;
        return type;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        IngredientPlacement placement = IngredientPlacement.forMultipleSlots(List.of(Optional.of(Ingredient.ofItem(inputStack1.getItem())), Optional.of(Ingredient.ofItem(inputStack2.getItem()))));
        return placement;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        RecipeBookCategory category = JAAVAARecipes.ALLOY_FURNACE_RECIPE_CATEGORY;
        return category;
    }

    public ItemStack getResult() {
        ItemStack result = output;
        return result;
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