package net.gordyjack.jaavaa.data.model;

import net.fabricmc.fabric.api.client.datagen.v1.provider.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.block.*;
import net.minecraft.client.data.*;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

import java.util.*;

public class JAAVAAModelProvider extends FabricModelProvider {
    public JAAVAAModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmGen) {
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.EXAMPLE_BLOCK);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.STARSTEEL_BLOCK);
        bsmGen.registerCooker(JAAVAABlocks.ALLOY_FURNACE, TexturedModel.ORIENTABLE);

        bsmGen.blockStateCollector.accept(generateAdjustableState(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP, "adjustable_redstone_lamp"));
        bsmGen.blockStateCollector.accept(generateAdvancedRepeaterState(JAAVAABlocks.ADVANCED_REPEATER_BLOCK, "advanced_repeater"));

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
            if (!(item instanceof BlockItem)) {
                imGen.register(item, Models.GENERATED);
            }
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
    private VariantsBlockStateSupplier generateAdvancedRepeaterState(Block block, String name) {
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(block);
        BlockStateVariantMap.QuintupleProperty<Direction, Boolean, Boolean, Integer, Integer> variantMap =
                BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.POWERED, Properties.LOCKED, JAAVAABlockProperties.DELAY, JAAVAABlockProperties.PULSE);

        List<Integer> delays = JAAVAABlockProperties.DELAY.getValues();
        List<Integer> pulses = JAAVAABlockProperties.PULSE.getValues();

        for (Direction facing : Direction.Type.HORIZONTAL) {
            for (boolean powered : new boolean[] {false, true}) {
                for (boolean locked : new boolean[] {false, true}) {
                    for (int delay = delays.getFirst(); delay <= delays.getLast(); delay++) {
                        for (int pulse = pulses.getFirst(); pulse <= pulses.getLast(); pulse++) {
                            String idPath = "block/" + name;
                            if (locked) {
                                idPath += "_locked";
                            }
                            if (powered) {
                                idPath += "_on";
                            }
                            if (delay > 0) {
                                idPath += "_d" + delay;
                            }
                            if (pulse > 0) {
                                idPath += "_p" + pulse;
                            }
                            Identifier modelId = JAAVAA.id(idPath);
                            VariantSettings.Rotation yRotation = switch (facing) {
                                case NORTH -> VariantSettings.Rotation.R180;
                                case SOUTH -> VariantSettings.Rotation.R0;
                                case WEST -> VariantSettings.Rotation.R90;
                                case EAST -> VariantSettings.Rotation.R270;
                                default -> throw new IllegalStateException("Unexpected value: " + facing);
                            };
                            BlockStateVariant variant = BlockStateVariant.create()
                                    .put(VariantSettings.MODEL, modelId)
                                    .put(VariantSettings.Y, yRotation);
                            variantMap.register(facing, powered, locked, delay, pulse, variant);
                        }
                    }
                }
            }
        }
        return variantSupplier.coordinate(variantMap);
    }
    private void registerEncasedPillarModel(BlockStateModelGenerator bsmGen, Block block, Identifier casing, Identifier edge, Identifier end) {
        bsmGen.registerAxisRotated(block, TexturedModel.makeFactory((block1) -> {
            TextureMap textureMap = TextureMap.all(casing);
            textureMap.put(TextureKey.EDGE, edge);
            textureMap.put(TextureKey.END, end);
            return textureMap;
        }, new Model(Optional.of(JAAVAA.id("block/encased_pillar")), Optional.empty(),
                TextureKey.SIDE, TextureKey.EDGE, TextureKey.END)));
    }
}
