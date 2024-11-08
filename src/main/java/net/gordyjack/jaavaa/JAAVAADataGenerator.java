package net.gordyjack.jaavaa;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.data.lang.*;
import net.minecraft.data.*;

public class JAAVAADataGenerator implements DataGeneratorEntrypoint {
	public DataProvider enUSProvider;
	public DataProvider modelProvider;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		enUSProvider = pack.addProvider(ENUSProvider::new);
		modelProvider = pack.addProvider(ModelProvider::new);
	}
}
