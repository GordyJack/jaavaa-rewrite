package net.gordyjack.jaavaa;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.data.lang.*;
import net.gordyjack.jaavaa.data.recipe.*;
import net.gordyjack.jaavaa.data.tags.*;
import net.minecraft.data.*;

public class JAAVAADataGenerator implements DataGeneratorEntrypoint {
	public DataProvider enUSProvider;
	public DataProvider modelProvider;
	public DataProvider recipeProvider;
	public DataProvider tagBlockProvider;
	public DataProvider tagItemProvider;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		enUSProvider = pack.addProvider(ENUSProvider::new);
		modelProvider = pack.addProvider(ModelProvider::new);
		recipeProvider = pack.addProvider(CraftingRecipeProvider::new);
		tagBlockProvider = pack.addProvider(JAAVAABlockTagProvider::new);
		tagItemProvider = pack.addProvider(JAAVAAItemTagProvider::new);
	}
}
