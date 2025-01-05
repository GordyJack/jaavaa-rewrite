package net.gordyjack.jaavaa.data.model;

import net.fabricmc.fabric.api.client.datagen.v1.provider.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.block.enums.*;
import net.gordyjack.jaavaa.item.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.*;
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
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.STARSTEEL_BLOCK);
        bsmGen.registerGlassAndPane(JAAVAABlocks.STARSTEEL_GLASS, JAAVAABlocks.STARSTEEL_GLASS_PANE);
        bsmGen.registerCooker(JAAVAABlocks.ALLOY_FURNACE, TexturedModel.ORIENTABLE);

        //TODO:Add custom model generation for the Adder, Decoder, Recycling Table, and Adjustable Lamp similar to the Advanced Repeater.
        bsmGen.blockStateCollector.accept(generateAdderState());
        bsmGen.blockStateCollector.accept(generateAdjustableState());
        bsmGen.blockStateCollector.accept(generateAdvancedRepeaterState());
        bsmGen.blockStateCollector.accept(generateDecoderState());
        bsmGen.blockStateCollector.accept(generateRecyclingTableState());
        for (MiniBlock block : JAAVAABlocks.MINI_BLOCKS.keySet()) {
            registerMiniBlockModel(bsmGen, block);
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
            imGen.register(item, Models.GENERATED);
        }
    }
    private VariantsBlockStateSupplier generateAdderState() {
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(JAAVAABlocks.ADDER);
        BlockStateVariantMap.QuintupleProperty<Direction, Boolean, Boolean, Boolean, Boolean> variantMap =
                BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.POWERED, JAAVAABlockProperties.LEFT_POWERED, JAAVAABlockProperties.BACK_POWERED, JAAVAABlockProperties.RIGHT_POWERED);
        for (Direction facing : Properties.HORIZONTAL_FACING.getValues()) {
            for (boolean powered : Properties.POWERED.getValues()) {
                for (var left : JAAVAABlockProperties.LEFT_POWERED.getValues()) {
                    for (var back : JAAVAABlockProperties.BACK_POWERED.getValues()) {
                        for (var right : JAAVAABlockProperties.RIGHT_POWERED.getValues()) {
                            String idPath = "block/adder";
                            if (powered && (left || back || right)) {
                                idPath += "_on_";
                                if (left) idPath += "l";
                                if (back) idPath += "b";
                                if (right) idPath += "r";
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
                            variantMap.register(facing, powered, left, back, right, variant);
                        }
                    }
                }
            }
        }
        return variantSupplier.coordinate(variantMap);
    }
    private VariantsBlockStateSupplier generateAdjustableState() {
        String idPath = "block/adjustable_redstone_lamp";
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(JAAVAABlocks.ADJUSTABLE_REDSTONE_LAMP);
        BlockStateVariantMap.SingleProperty<Integer> variantMap = BlockStateVariantMap.create(JAAVAABlockProperties.LUMINANCE);
        
        for (int luminance = 0; luminance <= 15; luminance++) {
            Identifier modelId = luminance == 0 ? JAAVAA.id(idPath) : JAAVAA.id(idPath + "_" + luminance);
            BlockStateVariant variant = BlockStateVariant.create().put(VariantSettings.MODEL, modelId);
            variantMap.register(luminance, variant);
        }
        return variantSupplier.coordinate(variantMap);
    }
    private VariantsBlockStateSupplier generateAdvancedRepeaterState() {
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(JAAVAABlocks.ADVANCED_REPEATER);
        BlockStateVariantMap.QuintupleProperty<Direction, Boolean, Boolean, Integer, Integer> variantMap =
                BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.POWERED, Properties.LOCKED, JAAVAABlockProperties.DELAY, JAAVAABlockProperties.PULSE);

        List<Integer> delays = JAAVAABlockProperties.DELAY.getValues();
        List<Integer> pulses = JAAVAABlockProperties.PULSE.getValues();

        for (Direction facing : Direction.Type.HORIZONTAL) {
            for (boolean powered : new boolean[] {false, true}) {
                for (boolean locked : new boolean[] {false, true}) {
                    for (int delay = delays.getFirst(); delay <= delays.getLast(); delay++) {
                        for (int pulse = pulses.getFirst(); pulse <= pulses.getLast(); pulse++) {
                            String idPath = "block/advanced_repeater";
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
    private VariantsBlockStateSupplier generateDecoderState() {
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(JAAVAABlocks.DECODER);
        BlockStateVariantMap.QuadrupleProperty<Direction, Boolean, DecoderMode, DecoderTarget> variantMap =
                BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.POWERED, JAAVAABlockProperties.DECODER_MODE, JAAVAABlockProperties.DECODER_TARGET);

        for (Direction facing : Properties.HORIZONTAL_FACING.getValues()) {
            for (boolean powered : Properties.POWERED.getValues()) {
                for (DecoderMode mode : JAAVAABlockProperties.DECODER_MODE.getValues()) {
                    for (DecoderTarget target : JAAVAABlockProperties.DECODER_TARGET.getValues()) {
                        String idPath = "block/decoder";
                        if (powered) idPath += "_on";
                        if (mode == DecoderMode.DEMUX) idPath += "_demux";
                        if (powered) idPath += switch (target) {
                            case LEFT -> "_l";
                            case FRONT -> "_f";
                            case RIGHT -> "_r";
                            default -> "";
                        };
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
                        variantMap.register(facing, powered, mode, target, variant);
                    }
                }
            }
        }
        return variantSupplier.coordinate(variantMap);
    }
    private VariantsBlockStateSupplier generateRecyclingTableState() {
        return VariantsBlockStateSupplier.create(
                JAAVAABlocks.RECYCLING_TABLE, BlockStateVariant.create().put(
                        VariantSettings.MODEL, ModelIds.getBlockModelId(JAAVAABlocks.RECYCLING_TABLE)
                )
        ).coordinate(
                BlockStateVariantMap.create(Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING)
                        .register(BlockFace.FLOOR, Direction.NORTH, BlockStateVariant.create())
                        .register(BlockFace.FLOOR, Direction.EAST, BlockStateVariant.create()
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(BlockFace.FLOOR, Direction.SOUTH, BlockStateVariant.create()
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(BlockFace.FLOOR, Direction.WEST, BlockStateVariant.create()
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(BlockFace.WALL, Direction.NORTH, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R90))
                        .register(BlockFace.WALL, Direction.EAST, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(BlockFace.WALL, Direction.SOUTH, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(BlockFace.WALL, Direction.WEST, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(BlockFace.CEILING, Direction.SOUTH, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R180))
                        .register(BlockFace.CEILING, Direction.WEST, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(BlockFace.CEILING, Direction.NORTH, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(BlockFace.CEILING, Direction.EAST, BlockStateVariant.create()
                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)));
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
    private void registerMiniBlockModel(BlockStateModelGenerator bsmGen, MiniBlock miniBlock) {
        String idPath = "block/" + JAAVAA.idFromItem(miniBlock.asItem()).getPath();
        //bsmGen.registerItemModel(miniBlock.asItem(), bsmGen.uploadBlockItemModel(miniBlock.asItem(), miniBlock));
        VariantsBlockStateSupplier variantSupplier = VariantsBlockStateSupplier.create(miniBlock);
        BlockStateVariantMap.SingleProperty<Integer> variantMap =
                BlockStateVariantMap.create(JAAVAABlockProperties.MINI_BLOCK_POSITION);

        for (int position = 0b00000001; position <= 0b11111111; position++) {
            String posString = String.format("%8s", Integer.toBinaryString(position)).replace(' ', '0');
            String subIdPath = idPath + "_" + posString;
            Identifier modelId = JAAVAA.id(subIdPath);
            BlockStateVariant variant = BlockStateVariant.create().put(VariantSettings.MODEL, modelId);
            variantMap.register(position, variant);
        }
        bsmGen.blockStateCollector.accept(variantSupplier.coordinate(variantMap));
    }
    public final void registerPane(BlockStateModelGenerator bsmGen, Block glassBlock, Block glassPane) {
        TextureMap textureMap = TextureMap.paneAndTopForEdge(glassBlock, glassPane);
        Identifier identifier = Models.TEMPLATE_GLASS_PANE_POST.upload(glassPane, textureMap, bsmGen.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_GLASS_PANE_SIDE.upload(glassPane, textureMap, bsmGen.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_GLASS_PANE_SIDE_ALT.upload(glassPane, textureMap, bsmGen.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_GLASS_PANE_NOSIDE.upload(glassPane, textureMap, bsmGen.modelCollector);
        Identifier identifier5 = Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT.upload(glassPane, textureMap, bsmGen.modelCollector);
        Item item = glassPane.asItem();
        bsmGen.registerItemModel(item, bsmGen.uploadBlockItemModel(item, glassBlock));
        bsmGen.blockStateCollector
                .accept(
                        MultipartBlockStateSupplier.create(glassPane)
                                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                                .with(
                                        When.create().set(Properties.EAST, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                )
                                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3))
                                .with(
                                        When.create().set(Properties.WEST, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                )
                                .with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4))
                                .with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5))
                                .with(
                                        When.create().set(Properties.SOUTH, false),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier5).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                )
                                .with(
                                        When.create().set(Properties.WEST, false),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                )
                );
    }
}
