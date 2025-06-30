package net.gordyjack.jaavaa;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.render.*;

public class JAAVAAClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		JAAVAA.log("Initializing JAAVAA client");
		HandledScreens.register(JAAVAAScreenHandlers.ALLOY_FURNACE_SCREEN_HANDLER, AlloyFurnaceBlockEntityScreen::new);
		HandledScreens.register(JAAVAAScreenHandlers.RECYCLING_SCREEN_HANDLER, RecyclingScreen::new);

		// RenderTranslucentLayers
		BlockRenderLayerMap.INSTANCE.putBlock(JAAVAABlocks.STARSTEEL_GLASS, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(JAAVAABlocks.STARSTEEL_GLASS_PANE, RenderLayer.getTranslucent());

		TooltipHelpers.addTooltips();

//		if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
//			JAAVAA.log("REI detected, adding plugin");
//			new JAAVAAREIPlugin();
//		}
	}
	private static class TooltipHelpers {
		private static void addMobNetItemTooltip() {
//			ItemTooltipCallback.EVENT.register((ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> lines) -> {
//				if (stack.getItem() instanceof MobNetItem item) {
//					if (MobNetItem.hasCapturedEntity(stack)) {
//						// first line: entity identifier when shift held
//						if (Screen.hasShiftDown()) {
//							lines.add(Text.literal(MobNetItem.getCapturedEntity(stack, ).toString()));
//						} else {
//							// default tooltip
//							lines.add(Text.literal("Captured Mob: §b" +
//									(item.getCapturedEntity().hasCustomName()
//											? item.getCapturedEntity().getCustomName().getString()
//											: Text.translatable(item.getCapturedEntityType().getTranslationKey()).getString())
//							));
//							lines.add(Text.literal("§8Hold ⇧ for details."));
//						}
//					} else {
//						//lines.add(Text.literal("§8Right click on a mob to capture it."));
//					}
//				}
//			});
		}
		protected static void addTooltips() {
			addMobNetItemTooltip();
		}
	}
}