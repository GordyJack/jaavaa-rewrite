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
	}
}