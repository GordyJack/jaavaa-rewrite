package net.gordyjack.jaavaa;

import net.fabricmc.api.ClientModInitializer;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.client.gui.screen.ingame.*;

public class JAAVAAClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		JAAVAA.log("Initializing JAAVAA client");
		HandledScreens.register(JAAVAAScreenHandlers.ALLOY_FURNACE_SCREEN_HANDLER, AlloyFurnaceBlockEntityScreen::new);
	}
}