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

import java.util.*;

public record RecyclingRecipe(int crushTime, ItemStack inputStack, List<ItemStack> outputs)
implements MultiOutputRecipe<SingleStackRecipeInput> {
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
        boolean countMatches = input.getStackInSlot(0).getCount() >= inputStack.getCount();
        boolean itemMatches = input.getStackInSlot(0).itemMatches(inputStack.getRegistryEntry());
        return countMatches && itemMatches;
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
        return null;
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
    public List<ItemStack> craftStacks(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return List.of(outputs.get(0).copy(), outputs.size() == 2 ? outputs.get(1) : ItemStack.EMPTY);
    }
    /**
     * {@return the serializer associated with this recipe}
     */
    @Override
    public RecipeSerializer<? extends MultiOutputRecipe<SingleStackRecipeInput>> getSerializer() {
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
                Codec.INT.fieldOf("crushTime").forGetter(RecyclingRecipe::crushTime),
                ItemStack.CODEC.fieldOf("inputStack").forGetter(RecyclingRecipe::inputStack),
                Codec.list(ItemStack.CODEC, 1, 4).fieldOf("outputs").forGetter(RecyclingRecipe::outputs)
        ).apply(inst, RecyclingRecipe::new));
        public static final PacketCodec<RegistryByteBuf, RecyclingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.INTEGER, RecyclingRecipe::crushTime,
                        ItemStack.PACKET_CODEC, RecyclingRecipe::inputStack,
                        PacketCodecs.codec(Codec.list(ItemStack.CODEC, 1, 4)), RecyclingRecipe::outputs,
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
