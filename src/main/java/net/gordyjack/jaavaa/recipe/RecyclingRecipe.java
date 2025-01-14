package net.gordyjack.jaavaa.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.*;
import net.minecraft.recipe.input.*;
import net.minecraft.registry.*;
import net.minecraft.world.*;

public record RecyclingRecipe(float experience, ItemStack inputStack, ItemStack outputStack)
implements Recipe<SingleStackRecipeInput> {
    /**
     * {@return whether this recipe matches the contents inside the {@code inventory} in the given {@code world}}
     *
     * <p>The {@code world} currently is only used by the map cloning recipe to
     * prevent duplication of explorer maps.
     *
     * @param input
     * @param world the input world
     */
    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        if (world.isClient) {
            return false;
        }
        return input.getStackInSlot(0).itemMatches(inputStack.getRegistryEntry());
    }
    /**
     * Crafts this recipe.
     *
     * <p>This method does not perform side effects on the {@code inventory}.
     *
     * <p>This method should return a new item stack on each call.
     *
     * @param input
     * @param registries
     * @return the resulting item stack
     */
    @Override
    public ItemStack craft(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return outputStack.copy();
    }
    /**
     * {@return the serializer associated with this recipe}
     */
    @Override
    public RecipeSerializer<? extends Recipe<SingleStackRecipeInput>> getSerializer() {
        return JAAVAARecipes.Serializers.RECYCLING;
    }
    /**
     * {@return the type of this recipe}
     *
     * <p>The {@code type} in the recipe JSON format is the {@linkplain
     * #getSerializer() serializer} instead.
     */
    @Override
    public RecipeType<? extends Recipe<SingleStackRecipeInput>> getType() {
        return JAAVAARecipes.Types.RECYCLING;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(Ingredient.ofItem(inputStack.getItem()));
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return JAAVAARecipes.Categories.RECYCLING;
    }

    public static class Serializer implements RecipeSerializer<RecyclingRecipe> {
        public static final MapCodec<RecyclingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.FLOAT.fieldOf("experience").forGetter(RecyclingRecipe::experience),
                ItemStack.CODEC.fieldOf("inputStack").forGetter(RecyclingRecipe::inputStack),
                ItemStack.CODEC.fieldOf("result").forGetter(RecyclingRecipe::outputStack)
        ).apply(inst, RecyclingRecipe::new));
        public static final PacketCodec<RegistryByteBuf, RecyclingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.FLOAT, RecyclingRecipe::experience,
                        ItemStack.PACKET_CODEC, RecyclingRecipe::inputStack,
                        ItemStack.PACKET_CODEC, RecyclingRecipe::outputStack,
                        RecyclingRecipe::new);

        @Override
        public MapCodec<RecyclingRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, RecyclingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
