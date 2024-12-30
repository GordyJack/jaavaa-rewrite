package net.gordyjack.jaavaa;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.data.lang.*;
import net.gordyjack.jaavaa.data.loot_table.*;
import net.gordyjack.jaavaa.data.model.*;
import net.gordyjack.jaavaa.data.model.block.*;
import net.gordyjack.jaavaa.data.recipe.*;
import net.gordyjack.jaavaa.data.tag.*;
import net.minecraft.data.*;
import net.minecraft.loot.context.*;

public class JAAVAADataGenerator implements DataGeneratorEntrypoint {
	public DataProvider advancedRepeaterModelProvider;
	public DataProvider enUSProvider;
	public DataProvider mobLootTableProvider;
	public DataProvider modelProvider;
	public DataProvider recipeProvider;
	public DataProvider tagBlockProvider;
	public DataProvider tagItemProvider;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		enUSProvider = pack.addProvider(ENUSProvider::new);
		modelProvider = pack.addProvider(ModelProvider::new);
		advancedRepeaterModelProvider = pack.addProvider(AdvancedRepeaterModelProvider::new);
		recipeProvider = pack.addProvider(CraftingRecipeProvider::new);
		tagBlockProvider = pack.addProvider(JAAVAABlockTagProvider::new);
		tagItemProvider = pack.addProvider(JAAVAAItemTagProvider::new);

		mobLootTableProvider = pack.addProvider((FabricDataGenerator.Pack.Factory<MobLootTableProvider>) output -> new MobLootTableProvider(
				output,
				fabricDataGenerator.getRegistries(),
				LootContextTypes.ENTITY));
	}
}
