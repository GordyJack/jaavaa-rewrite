package net.gordyjack.jaavaa;

import net.fabricmc.api.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.enchantment.*;
import net.gordyjack.jaavaa.event.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.potion.*;
import net.gordyjack.jaavaa.recipe.*;
import net.gordyjack.jaavaa.screen.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;
import org.slf4j.*;

public class JAAVAA implements ModInitializer {
	public static final String MOD_ID = "jaavaa";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ErrorReporter ERROR_REPORTER = new ErrorReporter() {
		@Override
		public ErrorReporter makeChild(Context context) {
			return this;
		}
		@Override
		public void report(Error error) {
			log(error.getMessage(), 'e');
		}
	};

	@Override
	public void onInitialize() {
		log("Initializing JAAVAA");
		
		JAAVAABlocks.init();
		JAAVAAItems.init();
		JAAVAAEventHandler.init();

		JAAVAAPotions.init();
		JAAVAAStatusEffects.init();

		JAAVAABlockEntityTypes.init();
		JAAVAAComponents.init();
		JAAVAAEnchantments.init();
		JAAVAARecipes.init();
		JAAVAAPotionRecipeBuilder.build();
		JAAVAAScreenHandlers.init();

		JAAVAALootTableEventsHandler.init();
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
	public static int seconds(Number seconds) {
		return Math.round(seconds.floatValue() * 20);
	}
	public static int minutes(Number minutes) {
		return seconds(minutes.floatValue() * 60);
	}
	public static Identifier idFromItem(ItemConvertible item) {
		return Registries.ITEM.getId(item.asItem());
	}
}