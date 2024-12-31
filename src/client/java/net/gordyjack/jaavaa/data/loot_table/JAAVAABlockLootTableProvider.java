package net.gordyjack.jaavaa.data.loot_table;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.block.*;
import net.minecraft.registry.*;

import java.util.concurrent.*;

public class JAAVAABlockLootTableProvider
        extends FabricBlockLootTableProvider {
    public JAAVAABlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (Block block : JAAVAABlocks.BLOCKS) {
            String blockKey = block.getTranslationKey();
            if (blockKey.contains("glass")) {
                this.addDrop(block,  this.dropsWithSilkTouch(block));
            } else if (block instanceof AbstractRedstoneGateBlock) {
                this.addDrop(block);
            } else {
                this.addDrop(block);
            }
        }
    }
}
