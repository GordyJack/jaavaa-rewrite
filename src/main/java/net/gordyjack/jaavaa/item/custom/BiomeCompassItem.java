package net.gordyjack.jaavaa.item.custom;

import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.utils.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class BiomeCompassItem
        extends Item {
    private SpiralTickSearcher searcher;
    public boolean searching = false;

    public BiomeCompassItem(Settings settings) {
        super(settings);
        this.searcher = new SpiralTickSearcher(null, null, BiomeKeys.THE_VOID, 512); // Placeholder, will be set in inventoryTick
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        var biome = stack.get(JAAVAAComponents.Types.BIOME_COMPASS_TARGET);
        var biomePosition = stack.get(JAAVAAComponents.Types.BIOME_COMPASS_POSITION);
        if (biome != null) {
            textConsumer.accept(Text.literal("§7Attuned to: §b" + biome.getValue().getPath()));
            if (biomePosition != null){
                textConsumer.accept(Text.literal("§7Biome found at: §e" + biomePosition.toShortString()));
            } else if (searching) {
                textConsumer.accept(Text.literal("§7Searching... §e%" + (int)((searcher.currentIndex() / (float) searcher.maxIndex()) * 100)));
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
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (stack.getItem() instanceof BiomeCompassItem && searching) {
            BlockPos newBiomePosition = searcher.tick();
            if (newBiomePosition == null) {
                stack.remove(JAAVAAComponents.Types.BIOME_COMPASS_POSITION);
                searching = false; // Stop searching if no position found
            } else if (newBiomePosition != BlockPos.ORIGIN) {
                stack.set(JAAVAAComponents.Types.BIOME_COMPASS_POSITION, newBiomePosition);
                searching = false;
            }
        }
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return ActionResult.PASS;
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem() instanceof BiomeCompassItem) {
            stack.remove(JAAVAAComponents.Types.BIOME_COMPASS_POSITION);
            this.searcher = new SpiralTickSearcher(world, user, stack.get(JAAVAAComponents.Types.BIOME_COMPASS_TARGET), 128);
            searching = true;
            return ActionResult.SUCCESS_SERVER;
        }
        return super.use(world, user, hand);
    }
}
