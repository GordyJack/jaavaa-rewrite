package net.gordyjack.jaavaa.data;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.block.*;
import net.minecraft.data.client.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmGen) {
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.EXAMPLE_BLOCK);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.STARSTEEL_BLOCK);
        bsmGen.blockStateCollector.accept(generateAdjustableState(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP, "adjustable_redstone_lamp"));
        registerEncasedPillarModel(bsmGen, JAAVAABlocks.QUARTZ_ENCASED_REDSTONE_PILLAR,
                Identifier.ofVanilla("block/quartz_pillar"),
                Identifier.ofVanilla("block/quartz_pillar_top"),
                Identifier.ofVanilla("block/redstone_block"));
        registerEncasedPillarModel(bsmGen, JAAVAABlocks.ANCIENT_DEBRIS_ENCASED_REDSTONE_PILLAR,
                Identifier.ofVanilla("block/ancient_debris_side"),
                Identifier.ofVanilla("block/ancient_debris_top"),
                Identifier.ofVanilla("block/redstone_block"));
    }
    @Override
    public void generateItemModels(ItemModelGenerator imGen) {
        for(Item item : JAAVAAItems.ITEMS) {
            imGen.register(item, Models.GENERATED);
        }
    }
    
    private VariantsBlockStateSupplier generateAdjustableState(Block block, String name) {
        String idPath = "block/" + name;
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(block);
        BlockStateVariantMap.SingleProperty<Integer> variantMap = BlockStateVariantMap.create(JAAVAABlockProperties.LUMINANCE);
        
        for (int luminance = 0; luminance <= 15; luminance++) {
            Identifier modelId = luminance == 0 ? JAAVAA.id(idPath) : JAAVAA.id(idPath + "_" + luminance);
            BlockStateVariant variant = BlockStateVariant.create().put(VariantSettings.MODEL, modelId);
            variantMap.register(luminance, variant);
        }
        return variantSupplier.coordinate(variantMap);
    }
    private void registerEncasedPillarModel(BlockStateModelGenerator bsmGen, Block block, Identifier casing, Identifier edge, Identifier end) {
        bsmGen.registerAxisRotated(block, TexturedModel.makeFactory((block1) -> {
            TextureMap textureMap = TextureMap.all(casing);
            textureMap.put(TextureKey.EDGE, edge);
            textureMap.put(TextureKey.END, end);
            return textureMap;
        }, new Model(Optional.of(JAAVAA.id("block/encased_pillar")), Optional.empty(), TextureKey.SIDE, TextureKey.EDGE, TextureKey.END)));
    }
}
