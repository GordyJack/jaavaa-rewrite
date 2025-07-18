package net.gordyjack.jaavaa.item.custom;

import com.mojang.datafixers.util.Pair;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;

import java.util.function.*;

public class BiomeCompassItem
        extends Item {

    public BiomeCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        var biome = stack.get(JAAVAAComponents.Types.BIOME_COMPASS_TARGET);
        var biomePosition = stack.get(JAAVAAComponents.Types.BIOME_COMPASS_POSITION);
        if (biome != null) {
            textConsumer.accept(Text.literal("§7Attuned to: §b" + biome.getValue().getPath()));
            if (biomePosition != null){
                textConsumer.accept(Text.literal("§7Biome found at: §e" + biomePosition.toShortString()));
            } else {
                textConsumer.accept(Text.literal("§7No biome matching §b" + biome.getValue().getPath() + "§7 found."));
            }
        }
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.get(JAAVAAComponents.Types.BIOME_COMPASS_POSITION) != null;
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!(world instanceof ServerWorld serverWorld)) return ActionResult.PASS;
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem() instanceof BiomeCompassItem) {
            if (user.isSneaking()) {
                stack.remove(JAAVAAComponents.Types.BIOME_COMPASS_POSITION);
                return ActionResult.SUCCESS_SERVER;
            } else if (stack.get(JAAVAAComponents.Types.BIOME_COMPASS_POSITION) == null) {
                stack.set(JAAVAAComponents.Types.BIOME_COMPASS_POSITION,
                        this.locateBiome(serverWorld, user, stack.get(JAAVAAComponents.Types.BIOME_COMPASS_TARGET))
                );
                return ActionResult.SUCCESS_SERVER;
            }
        }
        return super.use(world, user, hand);
    }
    private BlockPos locateBiome(ServerWorld serverWorld, PlayerEntity player, RegistryKey<Biome> targetBiome) {
        Pair<BlockPos, RegistryEntry<Biome>> posBiomePair = serverWorld.locateBiome(biomeRegistryEntry -> biomeRegistryEntry.matchesKey(targetBiome),
                player.getBlockPos(), 256/*Blocks*/ * 16/*to chunks*/, 32, 64);
        return posBiomePair.getFirst() != null ? posBiomePair.getFirst() : null;
    }
}
