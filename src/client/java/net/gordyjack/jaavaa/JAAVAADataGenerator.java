package net.gordyjack.jaavaa;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.data.lang.*;
import net.gordyjack.jaavaa.data.loot_table.*;
import net.gordyjack.jaavaa.data.model.*;
import net.gordyjack.jaavaa.data.model.block.*;
import net.gordyjack.jaavaa.data.recipe.*;
import net.gordyjack.jaavaa.data.tag.*;
import net.gordyjack.jaavaa.enchantment.*;
import net.minecraft.data.*;
import net.minecraft.registry.*;

public class JAAVAADataGenerator implements DataGeneratorEntrypoint {
	public DataProvider advancedRepeaterModelProvider;
	public DataProvider blockLootTableProvider;
	public DataProvider enchantmentProvider;
	public DataProvider enUSProvider;
	public DataProvider miniBlockModelProvider;
	public DataProvider mobLootTableProvider;
	public DataProvider structureLootTableProvider;
	public DataProvider modelProvider;
	public DataProvider recipeProvider;
	public DataProvider tagBlockProvider;
	public DataProvider tagDamageTypeProvider;
	public DataProvider tagEntityProvider;
	public DataProvider tagEnchantmentProvider;
	public DataProvider tagItemProvider;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		enUSProvider = pack.addProvider(JAAVAAenusProvider::new);
		modelProvider = pack.addProvider(JAAVAAModelProvider::new);
		advancedRepeaterModelProvider = pack.addProvider(AdvancedRepeaterModelProvider::new);
		miniBlockModelProvider = pack.addProvider(MiniBlockModelProvider::new);
		enchantmentProvider = pack.addProvider(JAAVAARegistryProvider::new);
		recipeProvider = pack.addProvider(JAAVAARecipeProvider::new);
		tagBlockProvider = pack.addProvider(JAAVAABlockTagProvider::new);
		tagDamageTypeProvider = pack.addProvider(JAAVAADamageTypeTagProvider::new);
		tagEntityProvider = pack.addProvider(JAAVAAEntityTypeTagProvider::new);
		tagEnchantmentProvider = pack.addProvider(JAAVAAEnchantmentTagProvider::new);
		tagItemProvider = pack.addProvider(JAAVAAItemTagProvider::new);

		blockLootTableProvider = pack.addProvider((FabricDataGenerator.Pack.Factory<JAAVAABlockLootTableProvider>) output -> new JAAVAABlockLootTableProvider(
				output,
				fabricDataGenerator.getRegistries()));
//		mobLootTableProvider = pack.addProvider((FabricDataGenerator.Pack.Factory<JAAVAAMobLootTableProvider>) output -> new JAAVAAMobLootTableProvider(
//				output,
//				fabricDataGenerator.getRegistries()));
//		structureLootTableProvider = pack.addProvider((FabricDataGenerator.Pack.Factory<JAAVAAStructureLootTableProvider>) output -> new JAAVAAStructureLootTableProvider(
//				output,
//				fabricDataGenerator.getRegistries()));
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, JAAVAAEnchantments::bootstrap);
	}
}
