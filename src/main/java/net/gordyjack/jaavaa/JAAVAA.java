package net.gordyjack.jaavaa;

import net.fabricmc.api.ModInitializer;

import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.recipe.JAAVAARecipes;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JAAVAA implements ModInitializer {
	public static final String MOD_ID = "jaavaa";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		log("Initializing JAAVAA");
		
		JAAVAABlocks.init();
		JAAVAAItems.init();
		
		JAAVAABlockEntityTypes.init();
		JAAVAARecipes.init();
		JAAVAAScreenHandlers.init();
	}
	
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID + ":" + path);
	}
	public static void log(String message) {
		log(message, 'i');
	}
	public static void log(String message, char type) {
        switch (type) {
            case 'i' -> LOGGER.info(message);
			case 'd' -> LOGGER.debug(message);
            case 'w' -> LOGGER.warn(message);
            case 'e' -> LOGGER.error(message);
            default -> throw new IllegalArgumentException("Invalid log type: " + type);
        }
	}
}