package net.gordyjack.jaavaa.item.custom;

import net.gordyjack.jaavaa.data.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.registry.tag.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.structure.*;

import java.util.function.*;

public class StructureCompassItem
        extends Item {

    public StructureCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        var structure = stack.get(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET);
        var structurePosition = stack.get(JAAVAAComponents.Types.COMPASS_TARGET_POSITION);
        if (structure != null) {
            textConsumer.accept(Text.literal("§7Attuned to: §b" + structure.id().getPath()));
            if (structurePosition != null){
                textConsumer.accept(Text.literal("§7Structure found at: §e" + structurePosition.toShortString()));
            } else {
                textConsumer.accept(Text.literal("§7No structure matching §b" + structure.id().getPath() + "§7 found."));
            }
        }
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.get(JAAVAAComponents.Types.COMPASS_TARGET_POSITION) != null;
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!(world instanceof ServerWorld serverWorld)) return ActionResult.PASS;
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem() instanceof StructureCompassItem) {
            if (user.isSneaking()) {
                stack.remove(JAAVAAComponents.Types.COMPASS_TARGET_POSITION);
                return ActionResult.SUCCESS_SERVER;
            } else if (stack.get(JAAVAAComponents.Types.COMPASS_TARGET_POSITION) == null) {
                stack.set(JAAVAAComponents.Types.COMPASS_TARGET_POSITION,
                        this.locateStructure(serverWorld, user, stack.get(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET))
                );
                return ActionResult.SUCCESS_SERVER;
            }
        }
        return super.use(world, user, hand);
    }
    private BlockPos locateStructure(ServerWorld serverWorld, PlayerEntity player, TagKey<Structure> targetStructure) {
        return serverWorld.locateStructure(
                targetStructure, player.getBlockPos(), 256/*Blocks*/ * 16/*to chunks*/, false
        );
    }
}
