package net.gordyjack.jaavaa.data.loot_table;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.minecraft.block.*;
import net.minecraft.loot.*;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.predicate.*;
import net.minecraft.registry.*;

import java.util.*;
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
            } else if (block instanceof Blocktant) {
                this.addDrop(block, this.blocktantDrops(block));
            } else {
                this.addDrop(block);
            }
        }
    }
    public LootTable.Builder blocktantDrops(Block blocktant) {
        List<Integer> positionsList = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            positionsList.add(i);
        }
        return LootTable.builder().pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(this.applyExplosionDecay(blocktant, ItemEntry.builder(blocktant).apply(positionsList, blocktantPosition ->
                                SetCountLootFunction.builder(ConstantLootNumberProvider.create((float) Integer.bitCount(blocktantPosition)))
                                        .conditionally(BlockStatePropertyLootCondition.builder(blocktant)
                                                .properties(StatePredicate.Builder.create().exactMatch(Blocktant.POSITION, blocktantPosition)))))));
    }
}
