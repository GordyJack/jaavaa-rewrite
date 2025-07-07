package net.gordyjack.jaavaa.data.model;

import net.fabricmc.fabric.api.client.datagen.v1.provider.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.block.enums.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.*;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

import java.util.*;

import static net.minecraft.client.data.BlockStateModelGenerator.*;

public class JAAVAAModelProvider extends FabricModelProvider {
    private static final BlockStateVariantMap<ModelVariantOperator> SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS =
            BlockStateVariantMap.operations(Properties.HORIZONTAL_FACING)
                    .register(Direction.SOUTH, NO_OP)
                    .register(Direction.WEST, ROTATE_Y_90)
                    .register(Direction.NORTH, ROTATE_Y_180)
                    .register(Direction.EAST, ROTATE_Y_270);

    public JAAVAAModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmGen) {
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.QUICKSAND);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.RAW_VOIDIUM);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.STARSTEEL_BLOCK);
        bsmGen.registerGlassAndPane(JAAVAABlocks.STARSTEEL_GLASS, JAAVAABlocks.STARSTEEL_GLASS_PANE);
        bsmGen.registerCooker(JAAVAABlocks.ALLOY_FURNACE, TexturedModel.ORIENTABLE);

        bsmGen.blockStateCollector.accept(generateAdderState());
        bsmGen.blockStateCollector.accept(generateAdjustableState());
        bsmGen.blockStateCollector.accept(generateAdvancedRepeaterState());
        bsmGen.blockStateCollector.accept(generateDecoderState());
        bsmGen.blockStateCollector.accept(generateRecyclingTableState());
        for (Blocktant block : JAAVAABlocks.BLOCKTANTS.keySet()) {
            registerBlocktantModel(bsmGen, block);
        }

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
            if (item instanceof BlockItem) continue;
            if (item.getDefaultStack().isIn(JAAVAATags.Items.TOOLS_STARSTEEL)) {
                imGen.registerWithBrokenCondition(item);
                continue;
            }
            imGen.register(item, Models.GENERATED);
        }
    }
    private BlockModelDefinitionCreator generateAdderState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.ADDER)
                .with(BlockStateVariantMap.models(Properties.POWERED,
                                JAAVAABlockProperties.LEFT_POWERED,
                                JAAVAABlockProperties.BACK_POWERED,
                                JAAVAABlockProperties.RIGHT_POWERED)
                        .generate((on, left, back, right) -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (on && (left || back || right)) {
                        stringBuilder.append("_on");
                        if (left) stringBuilder.append("_l");
                        if (back) stringBuilder.append("_b");
                        if (right) stringBuilder.append("_r");
                    }
                    return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.ADDER, stringBuilder.toString()));
                }))
                .coordinate(SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS);
    }
    private BlockModelDefinitionCreator generateAdjustableState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP)
                .with(BlockStateVariantMap.models(JAAVAABlockProperties.LUMINANCE).generate((luminance) -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (luminance > 0) {
                        stringBuilder.append("_" + luminance);
                    }
                    return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP, stringBuilder.toString()));
                }));
    }
    private BlockModelDefinitionCreator generateAdvancedRepeaterState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.ADVANCED_REPEATER)
                .with(BlockStateVariantMap.models(Properties.POWERED,
                        Properties.LOCKED,
                        JAAVAABlockProperties.DELAY,
                        JAAVAABlockProperties.PULSE)
                        .generate((on, locked, delay, pulse) -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (locked) {
                        stringBuilder.append("_locked");
                    }
                    if (on) {
                        stringBuilder.append("_on");
                    }
                    if (delay > 0) {
                        stringBuilder.append("_d" + delay);
                    }
                    if (pulse > 0) {
                        stringBuilder.append("_p" + pulse);
                    }
                    return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.ADVANCED_REPEATER, stringBuilder.toString()));
                }))
                .coordinate(SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS);
    }
    private BlockModelDefinitionCreator generateDecoderState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.DECODER)
                .with(BlockStateVariantMap.models(Properties.POWERED,
                        JAAVAABlockProperties.DECODER_MODE,
                        JAAVAABlockProperties.DECODER_TARGET)
                        .generate((on, mode, target) -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (on) {
                        stringBuilder.append("_on");
                        stringBuilder.append(switch (target) {
                            case LEFT -> "_l";
                            case RIGHT -> "_r";
                            case FRONT -> "_f";
                            default -> "";
                        });
                    }
                    if (mode == DecoderMode.DEMUX) {
                        stringBuilder.append("_demux");
                    }
                    return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.DECODER, stringBuilder.toString()));
                }))
                .coordinate(SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS);
    }
    private BlockModelDefinitionCreator generateRecyclingTableState() {
        return VariantsBlockModelDefinitionCreator.of(
                JAAVAABlocks.RECYCLING_TABLE,
                BlockStateModelGenerator.createWeightedVariant(TextureMap.getId(JAAVAABlocks.RECYCLING_TABLE))
        ).coordinate(
                BlockStateVariantMap.operations(Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING)
                        .register(BlockFace.FLOOR, Direction.NORTH, NO_OP)
                        .register(BlockFace.FLOOR, Direction.EAST, ROTATE_Y_90)
                        .register(BlockFace.FLOOR, Direction.SOUTH, ROTATE_Y_180)
                        .register(BlockFace.FLOOR, Direction.WEST, ROTATE_Y_270)
                        .register(BlockFace.WALL, Direction.NORTH, ROTATE_X_90)
                        .register(BlockFace.WALL, Direction.EAST, ROTATE_X_90.then(ROTATE_Y_90))
                        .register(BlockFace.WALL, Direction.SOUTH, ROTATE_X_90.then(ROTATE_Y_180))
                        .register(BlockFace.WALL, Direction.WEST, ROTATE_X_90.then(ROTATE_Y_270))
                        .register(BlockFace.CEILING, Direction.SOUTH, ROTATE_X_180)
                        .register(BlockFace.CEILING, Direction.WEST, ROTATE_X_180.then(ROTATE_Y_90))
                        .register(BlockFace.CEILING, Direction.NORTH, ROTATE_X_180.then(ROTATE_Y_180))
                        .register(BlockFace.CEILING, Direction.EAST, ROTATE_X_180.then(ROTATE_Y_270))
        );
    }
    private void registerEncasedPillarModel(BlockStateModelGenerator bsmGen, Block block,
                                            Identifier casing, Identifier edge, Identifier end) {
        bsmGen.registerAxisRotated(block, TexturedModel.makeFactory((block1) -> {
            TextureMap textureMap = TextureMap.all(casing);
            textureMap.put(TextureKey.EDGE, edge);
            textureMap.put(TextureKey.END, end);
            return textureMap;
        }, new Model(Optional.of(JAAVAA.id("block/encased_pillar")), Optional.empty(),
                TextureKey.SIDE, TextureKey.EDGE, TextureKey.END)));
    }
    private void registerBlocktantModel(BlockStateModelGenerator bsmGen, Blocktant blocktant) {
        String idPath = "block/" + JAAVAA.idFromItem(blocktant.asItem()).getPath();
        BlockStateVariantMap.SingleProperty<WeightedVariant, Integer> variantMap =
                BlockStateVariantMap.models(JAAVAABlockProperties.BLOCKTANT_POSITION);

        for (int position = 0b00000001; position <= 0b11111111; position++) {
            String posString = String.format("%8s", Integer.toBinaryString(position)).replace(' ', '0');
            String subIdPath = idPath + "_" + posString;
            Identifier modelId = JAAVAA.id(subIdPath);
            WeightedVariant variant = BlockStateModelGenerator.createWeightedVariant(modelId);
            variantMap.register(position, variant);
        }
        bsmGen.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(blocktant).with(variantMap));
    }
}
