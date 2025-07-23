package net.gordyjack.jaavaa.data.model;

import net.fabricmc.fabric.api.client.datagen.v1.provider.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.gordyjack.jaavaa.block.enums.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.*;
import net.gordyjack.jaavaa.property.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.*;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.item.*;
import net.minecraft.item.equipment.*;
import net.minecraft.registry.*;
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
    private ItemModelGenerator imGen = null;

    public JAAVAAModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmGen) {
        bsmGen.registerCooker(JAAVAABlocks.ALLOY_FURNACE, TexturedModel.ORIENTABLE);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.AURON_BLOCK);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.CUPAUREUM_BLOCK);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.CUPERUM_BLOCK);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.QUICKSAND);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.RAW_VOIDIUM);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.STARSTEEL_BLOCK);
        bsmGen.registerGlassAndPane(JAAVAABlocks.STARSTEEL_GLASS, JAAVAABlocks.STARSTEEL_GLASS_PANE);
        bsmGen.registerSimpleCubeAll(JAAVAABlocks.STEEL_BLOCK);

        bsmGen.blockStateCollector.accept(generateAdderState());
        bsmGen.blockStateCollector.accept(generateAdjustableState());
        bsmGen.blockStateCollector.accept(generateAdvancedRepeaterState());
        bsmGen.blockStateCollector.accept(generateDecoderState());
        bsmGen.blockStateCollector.accept(generateLogicalANDGateState());
        bsmGen.blockStateCollector.accept(generateLogicalORGateState());
        bsmGen.blockStateCollector.accept(generateLogicalXORGateState());
        bsmGen.blockStateCollector.accept(generateRandomizerState());
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
        this.imGen = imGen;
        //Food
        this.registerGeneratedItemModel(JAAVAAItems.MALUM_STELLAE_INCANTATAE);
        //Materials
        this.registerGeneratedItemModel(JAAVAAItems.ALLAY_ESSENCE);
        this.registerGeneratedItemModel(JAAVAAItems.AURON_INGOT);
        this.registerGeneratedItemModel(JAAVAAItems.AURON_NUGGET);
        this.registerGeneratedItemModel(JAAVAAItems.CUPAUREUM_INGOT);
        this.registerGeneratedItemModel(JAAVAAItems.CUPERUM_INGOT);
        this.registerRodItemModel(JAAVAAItems.FUSED_ROD);
        this.registerRodItemModel(JAAVAAItems.IRON_ROD);
        this.registerGeneratedItemModel(JAAVAAItems.QUICKSILVER_INGOT);
        this.registerGeneratedItemModel(JAAVAAItems.QUICKSILVER_NUGGET);
        this.registerGeneratedItemModel(JAAVAAItems.SHULKER_PEARL);
        this.registerGeneratedItemModel(JAAVAAItems.STARSTEEL_INGOT);
        this.registerGeneratedItemModel(JAAVAAItems.STARSTEEL_NUGGET);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_INGOT);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_NUGGET);
        //Misc
        this.registerGeneratedItemModel(JAAVAAItems.STARSTEEL_UPGRADE_SMITHING_TEMPLATE);
        //Equipment/Armor
        //Vanilla-like Armor
        this.registerEquipmentSetModels(JAAVAAEquipmentAssetKeys.AURON, JAAVAAItems.AURON_HELMET, JAAVAAItems.AURON_CHESTPLATE, JAAVAAItems.AURON_LEGGINGS, JAAVAAItems.AURON_BOOTS);
        this.registerEquipmentSetModels(JAAVAAEquipmentAssetKeys.STEEL, JAAVAAItems.STEEL_HELMET, JAAVAAItems.STEEL_CHESTPLATE, JAAVAAItems.STEEL_LEGGINGS, JAAVAAItems.STEEL_BOOTS);
        //Mod Equipment
        //TODO: this.registerHappyGhastPackModel();
        //Tools
        //Vanilla-like Tools
        this.registerGeneratedItemModel(JAAVAAItems.AURON_SWORD);
        this.registerGeneratedItemModel(JAAVAAItems.AURON_SHOVEL);
        this.registerGeneratedItemModel(JAAVAAItems.AURON_PICKAXE);
        this.registerGeneratedItemModel(JAAVAAItems.AURON_AXE);
        this.registerGeneratedItemModel(JAAVAAItems.AURON_HOE);
        this.registerGeneratedItemModel(JAAVAAItems.CUPAUREUM_SWORD);
        this.registerGeneratedItemModel(JAAVAAItems.CUPAUREUM_SHOVEL);
        this.registerGeneratedItemModel(JAAVAAItems.CUPAUREUM_PICKAXE);
        this.registerGeneratedItemModel(JAAVAAItems.CUPAUREUM_AXE);
        this.registerGeneratedItemModel(JAAVAAItems.CUPAUREUM_HOE);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_SWORD);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_SHOVEL);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_PICKAXE);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_AXE);
        this.registerGeneratedItemModel(JAAVAAItems.STEEL_HOE);
        this.registerGeneratedItemModel(JAAVAAItems.STARSTEEL_SWORD, true);
        //Mod Tools
        //this.registerStructureCompass(JAAVAAItems.ARCHITECTS_COMPASS);
        this.registerBiomeCompass(JAAVAAItems.BIOME_COMPASS);
        //Hammers
        this.registerHammerItemModel(JAAVAAItems.HAMMER_IRON, TextureMap.getId(Blocks.IRON_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_AURON, TextureMap.getId(JAAVAABlocks.AURON_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_CUPAUREUM, TextureMap.getId(JAAVAABlocks.CUPAUREUM_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_CUPERUM, TextureMap.getId(JAAVAABlocks.CUPERUM_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_GOLD, TextureMap.getId(Blocks.GOLD_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_STEEL, TextureMap.getId(JAAVAABlocks.STEEL_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_DIAMOND, TextureMap.getId(Blocks.DIAMOND_BLOCK));
        this.registerHammerItemModel(JAAVAAItems.HAMMER_NETHERITE, TextureMap.getId(Blocks.NETHERITE_BLOCK), TextureMap.getId(Blocks.DIAMOND_BLOCK), JAAVAAItems.FUSED_ROD);
        this.registerHammerItemModel(JAAVAAItems.HAMMER_STARSTEEL, TextureMap.getId(JAAVAABlocks.STARSTEEL_BLOCK), TextureMap.getId(Blocks.NETHERITE_BLOCK), JAAVAAItems.FUSED_ROD);
        this.registerHammerItemModel(JAAVAAItems.HAMMER_VOIDIUM, TextureMap.getId(JAAVAABlocks.RAW_VOIDIUM), TextureMap.getId(JAAVAABlocks.STARSTEEL_BLOCK), JAAVAAItems.FUSED_ROD);
        //Magnets
        this.registerGeneratedItemModel(JAAVAAItems.MAGNET_IRON);
        this.registerGeneratedItemModel(JAAVAAItems.MAGNET_GOLD);
        this.registerGeneratedItemModel(JAAVAAItems.MAGNET_DIAMOND);
        this.registerGeneratedItemModel(JAAVAAItems.MAGNET_NETHERITE);
        //Mob Nets
        this.registerGeneratedItemModel(JAAVAAItems.MOB_NET_WOOD);
        this.registerGeneratedItemModel(JAAVAAItems.MOB_NET_STONE);
        this.registerGeneratedItemModel(JAAVAAItems.MOB_NET_GOLD);
        this.registerGeneratedItemModel(JAAVAAItems.MOB_NET_IRON);
        this.registerGeneratedItemModel(JAAVAAItems.MOB_NET_DIAMOND);
        this.registerGeneratedItemModel(JAAVAAItems.MOB_NET_NETHERITE);
        //Tool of the Ancients
        this.registerGeneratedItemModel(JAAVAAItems.TOOL_OF_THE_ANCIENTS);
        this.registerGeneratedItemModel(JAAVAAItems.TOOL_OF_THE_ANCIENTS_STARSTEEL, true);
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
    //Blocks
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
    private BlockModelDefinitionCreator generateLogicalANDGateState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.LOGICAL_AND_GATE)
                .with(BlockStateVariantMap.models(Properties.POWERED,
                        JAAVAABlockProperties.LEFT_POWERED,
                        JAAVAABlockProperties.RIGHT_POWERED)
                        .generate((on, left, right) -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            if (on) {
                                stringBuilder.append("_on");
                            } else {
                                if (left) {
                                    stringBuilder.append("_l");
                                } else if (right) {
                                    stringBuilder.append("_r");
                                }
                            }
                            return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.LOGICAL_AND_GATE, stringBuilder.toString()));
                        }))
                .coordinate(SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS);
    }
    private BlockModelDefinitionCreator generateLogicalORGateState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.LOGICAL_OR_GATE)
                .with(BlockStateVariantMap.models(Properties.POWERED)
                        .generate((on) -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            if (on) {
                                stringBuilder.append("_on");
                            }
                            return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.LOGICAL_OR_GATE, stringBuilder.toString()));
                        }))
                .coordinate(SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS);
    }
    private BlockModelDefinitionCreator generateLogicalXORGateState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.LOGICAL_XOR_GATE)
                .with(BlockStateVariantMap.models(Properties.POWERED,
                        JAAVAABlockProperties.LEFT_POWERED,
                        JAAVAABlockProperties.RIGHT_POWERED)
                        .generate((powered, left, right) -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            if (!((powered && left && right)
                                    || (powered && !left && !right)
                                    || (!powered && ((left || right) && !(left && right))))) {
                                if (powered) {
                                    stringBuilder.append("_on");
                                }
                                if (left) {
                                    stringBuilder.append("_l");
                                }
                                if (right) {
                                    stringBuilder.append("_r");
                                }
                            }
                            return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.LOGICAL_XOR_GATE, stringBuilder.toString()));
                        }))
                .coordinate(SOUTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS);
    }
    private BlockModelDefinitionCreator generateRandomizerState() {
        return VariantsBlockModelDefinitionCreator.of(JAAVAABlocks.RANDOMIZER)
                .with(BlockStateVariantMap.models(Properties.POWERED, Properties.POWER)
                        .generate((on, power) -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            if (on && power > 0) {
                                stringBuilder.append("_on_" + power);
                            }
                            return createWeightedVariant(TextureMap.getSubId(JAAVAABlocks.RANDOMIZER, stringBuilder.toString()));
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
    private void registerEquipmentSetModels(RegistryKey<EquipmentAsset> equipmentKey, Item helmetItem, Item chestplateItem, Item legItem, Item bootItem) {
        this.imGen.registerArmor(helmetItem, equipmentKey, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        this.imGen.registerArmor(chestplateItem, equipmentKey, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        this.imGen.registerArmor(legItem, equipmentKey, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        this.imGen.registerArmor(bootItem, equipmentKey, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
    }
    private void registerGeneratedItemModel(Item item) {
        this.registerGeneratedItemModel(item, false);
    }
    private void registerGeneratedItemModel(Item item, boolean withBrokenCondition) {
        if (withBrokenCondition) {
            this.imGen.registerWithBrokenCondition(item);
        } else {
            this.imGen.register(item, Models.GENERATED);
        }
    }
    private void registerHammerItemModel(Item hammer, Identifier material) {
        this.registerHammerItemModel(hammer, material, material);
    }
    private void registerHammerItemModel(Item hammer, Identifier material, Item rod) {
        this.registerHammerItemModel(hammer, material, material, rod);
    }
    private void registerHammerItemModel(Item hammer, Identifier head, Identifier ring) {
        this.registerHammerItemModel(hammer, head, ring, JAAVAAItems.IRON_ROD);
    }
    private void registerHammerItemModel(Item hammer, Identifier head, Identifier ring, Item rod) {
        this.registerHammerItemModel(hammer, head, head, ring, TextureMap.getId(rod));
    }
    private void registerHammerItemModel(Item hammer,
                                         Identifier band, Identifier head, Identifier ring, Identifier rod) {
        TextureKey bandKey = TextureKey.of("band");
        TextureKey headKey = TextureKey.of("head");
        TextureKey ringKey = TextureKey.of("ring");
        TextureKey rodKey = TextureKey.of("rod");
        TextureMap map = TextureMap.texture(head);
        map.put(bandKey, band);
        map.put(headKey, head);
        map.put(ringKey, ring);
        map.put(rodKey, rod);
        Model model = new Model(Optional.of(JAAVAA.id("item/hammer_base")), Optional.empty(), bandKey, headKey, ringKey, rodKey);
        model.upload(hammer, map, imGen.modelCollector);
        this.imGen.register(hammer);
    }
    private void registerRodItemModel(Item rodItem) {
        this.registerRodItemModel(rodItem, TextureMap.getId(rodItem));
    }
    private void registerRodItemModel(Item rodItem, Identifier rod) {
        TextureKey rodKey = TextureKey.of("rod");
        TextureMap map = TextureMap.texture(rod);
        map.put(rodKey, rod);
        Model model = new Model(Optional.of(JAAVAA.id("item/rod_base")), Optional.empty(), rodKey);
        model.upload(rodItem, map, this.imGen.modelCollector);
        this.imGen.register(rodItem);
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
    //Items
    private void registerBiomeCompass(Item item) {
        List<RangeDispatchItemModel.Entry> list = this.imGen.createCompassRangeDispatchEntries(item);
        this.imGen.output
                .accept(
                        item,
                        ItemModels.condition(
                                ItemModels.hasComponentProperty(JAAVAAComponents.Types.COMPASS_TARGET_POSITION),
                                ItemModels.condition(
                                        ItemModels.hasComponentProperty(JAAVAAComponents.Types.COMPASS_BIOME_TARGET),
                                        ItemModels.rangeDispatch(new BiomeCompassProperty(true, BiomeCompassState.Target.BIOME), 32.0F, list),
                                        ItemModels.rangeDispatch(new BiomeCompassProperty(true, BiomeCompassState.Target.NONE), 32.0F, list)),
                                ItemModels.rangeDispatch(new BiomeCompassProperty(true, BiomeCompassState.Target.NONE), 32.0F, list)
                        )
                );
    }
    private void registerStructureCompass(Item item) {
        List<RangeDispatchItemModel.Entry> list = this.imGen.createCompassRangeDispatchEntries(item);
        this.imGen.output
                .accept(
                        item,
                        ItemModels.condition(
                                ItemModels.hasComponentProperty(JAAVAAComponents.Types.COMPASS_TARGET_POSITION),
                                ItemModels.condition(
                                        ItemModels.hasComponentProperty(JAAVAAComponents.Types.COMPASS_STRUCTURE_TARGET),
                                        ItemModels.rangeDispatch(new StructureCompassProperty(true, StructureCompassState.Target.STRUCTURE), 32.0F, list),
                                        ItemModels.rangeDispatch(new StructureCompassProperty(true, StructureCompassState.Target.NONE), 32.0F, list)),
                                ItemModels.rangeDispatch(new StructureCompassProperty(true, StructureCompassState.Target.NONE), 32.0F, list)
                        )
                );
    }
}
