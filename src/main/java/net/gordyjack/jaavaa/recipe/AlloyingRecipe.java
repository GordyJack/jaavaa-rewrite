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

public record AlloyingRecipe(int burnTime, float experience, ItemStack inputStack1, ItemStack inputStack2, ItemStack output)
        implements Recipe<AlloyingRecipeInput> {

    @Override
    public boolean matches(AlloyingRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }
        ItemStack item1 = input.getStackInSlot(0);
        ItemStack item2 = input.getStackInSlot(1);
        boolean item1Matches1 = item1.itemMatches(inputStack1.getRegistryEntry());
        boolean item1Matches2 = item1.itemMatches(inputStack2.getRegistryEntry());
        boolean item2Matches1 = item2.itemMatches(inputStack1.getRegistryEntry());
        boolean item2Matches2 = item2.itemMatches(inputStack2.getRegistryEntry());
        return (item1Matches1 && item2Matches2) || (item1Matches2 && item2Matches1);
    }
    @Override
    public ItemStack craft(AlloyingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }
    @Override
    public RecipeSerializer<? extends Recipe<AlloyingRecipeInput>> getSerializer() {
        return JAAVAARecipes.Serializers.ALLOYING;
    }
    @Override
    public RecipeType<? extends Recipe<AlloyingRecipeInput>> getType() {
        return JAAVAARecipes.Types.ALLOYING;
    }
    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(List.of(Optional.of(Ingredient.ofItem(inputStack1.getItem())), Optional.of(Ingredient.ofItem(inputStack2.getItem()))));
    }
    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return JAAVAARecipes.Categories.ALLOYING;
    }
    public ItemStack getResult() {
        return output;
    }

    public static class Serializer implements RecipeSerializer<AlloyingRecipe> {
        public static final MapCodec<AlloyingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.INT.fieldOf("burnTime").forGetter(AlloyingRecipe::burnTime),
                Codec.FLOAT.fieldOf("experience").forGetter(AlloyingRecipe::experience),
                ItemStack.CODEC.fieldOf("inputStack1").forGetter(AlloyingRecipe::inputStack1),
                ItemStack.CODEC.fieldOf("inputStack2").forGetter(AlloyingRecipe::inputStack2),
                ItemStack.CODEC.fieldOf("result").forGetter(AlloyingRecipe::output)
        ).apply(inst, AlloyingRecipe::new));
        public static final PacketCodec<RegistryByteBuf, AlloyingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.INTEGER, AlloyingRecipe::burnTime,
                        PacketCodecs.FLOAT, AlloyingRecipe::experience,
                        ItemStack.PACKET_CODEC, AlloyingRecipe::inputStack1,
                        ItemStack.PACKET_CODEC, AlloyingRecipe::inputStack2,
                        ItemStack.PACKET_CODEC, AlloyingRecipe::output,
                        AlloyingRecipe::new);

        @Override
        public MapCodec<AlloyingRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, AlloyingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}