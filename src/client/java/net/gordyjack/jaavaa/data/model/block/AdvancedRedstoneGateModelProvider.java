package net.gordyjack.jaavaa.data.model.block;

import com.google.gson.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.redstone_gates.*;
import net.gordyjack.jaavaa.block.enums.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class AdvancedRedstoneGateModelProvider implements DataProvider {
    private final FabricDataOutput OUTPUT;

    private static final JsonObject base = createGateBaseModel();

    public AdvancedRedstoneGateModelProvider(FabricDataOutput output) {
        this.OUTPUT = output;
    }
    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> returns = new ArrayList<>();
        for (JsonObject model : createAdderModels()) returns.add(writeModel(writer, model));
        for (JsonObject model : createAdvancedRepeaterModels()) returns.add(writeModel(writer, model));
        for (JsonObject model : createDecoderModels()) returns.add(writeModel(writer, model));
        for (JsonObject model : createLogicalANDGateModels()) returns.add(writeModel(writer, model));
        for (JsonObject model : createLogicalORGateModels()) returns.add(writeModel(writer, model));
        for (JsonObject model : createLogicalXORGateModels()) returns.add(writeModel(writer, model));
        for (JsonObject model : createRandomizerModels()) returns.add(writeModel(writer, model));
        return CompletableFuture.allOf(returns.toArray(CompletableFuture[]::new));
    }
    @Override
    public String getName() {
        return "Advanced Redstone Gate Model Provider";
    }
    @SafeVarargs
    private static <T extends Number> JsonArray arrayOf(T... values) {
        JsonArray array = new JsonArray();
        for (T value : values) {
            array.add(value);
        }
        return array;
    }
    private static List<JsonObject> createAdderModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "adder";
        List<String> modelNames = generateUniqueAdderModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);
            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            boolean powered = modelName.contains("_on");
            boolean leftPowered = modelName.contains("_l");
            boolean backPowered = modelName.contains("_b");
            boolean rightPowered = modelName.contains("_r");
            String powerString = (powered ? "_on" : "") + (leftPowered ? "_l" : "")
                    + (backPowered ? "_b" : "") + (rightPowered ? "_r" : "");
            textures.addProperty("particle", JAAVAA.id("block/" + name + powerString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + powerString).toString());
            textures.addProperty("side", JAAVAA.id("block/smooth_polished_deepslate").toString());
            textures.addProperty("unlit", Identifier.ofVanilla("block/redstone_torch_off").toString());
            if (powered) textures.addProperty("lit", Identifier.ofVanilla("block/redstone_torch").toString());
            model.add("textures", textures);
            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Torches
            JsonObject frontTorch = createTorchModel(7.0f, 2.0f, powered ? "#lit" : "#unlit", false);
            JsonObject leftTorch = createTorchModel(2.0f, 7.0f, powered && leftPowered ? "#lit" : "#unlit", true);
            JsonObject backTorch = createTorchModel(7.0f, 12.0f, powered && backPowered ? "#lit" : "#unlit", true);
            JsonObject rightTorch = createTorchModel(12.0f, 7.0f, powered && rightPowered ? "#lit" : "#unlit", true);
            elements.add(frontTorch);
            elements.add(leftTorch);
            elements.add(backTorch);
            elements.add(rightTorch);
            //Halos
            if (powered) {
                for (var halo : createTorchHaloModels(7.0f, 2.0f, "#lit", false)) elements.add(halo); //Front Halos
                if (leftPowered) for (var halo : createTorchHaloModels(2.0f, 7.0f, "#lit", true)) elements.add(halo);
                if (backPowered) for (var halo : createTorchHaloModels(7.0f, 12.0f, "#lit", true)) elements.add(halo);
                if (rightPowered) for (var halo : createTorchHaloModels(12.0f, 7.0f, "#lit", true)) elements.add(halo);
            }
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<JsonObject> createAdvancedRepeaterModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "advanced_repeater";
        List<String> modelNames = generateUniqueAdvancedRepeaterModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);

            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            textures.addProperty("side", JAAVAA.id("block/smooth_polished_deepslate").toString());
            boolean powered = modelName.contains("_on");
            String pString = powered ? "_on" : "";
            textures.addProperty("particle", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("torch", Identifier.ofVanilla("block/redstone_torch" + (powered ? "" : "_off")).toString());

            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);

            //Provides models and textures if locked.
            if (modelName.contains("_locked")) {
                textures.addProperty("lock", Identifier.ofVanilla("block/bedrock").toString());
                elements.add(createRepeaterLockModel());
            }

            //Torch maths.
            int textureLength = 12; //The length of the area of the texture for the torch to move in pixels.
            int torchWidth = 2; //The width of the torch in pixels.
            int torchPositions = 8; //The number of positions the torch can be in.
            float initialX = 2.0f; //The initial x position of the torches.
            float pulseInitialZ = 3.0f; //The initial z position of the pulse torch.
            float delayInitialZ = 11.0f; //The initial z position of the delay torch.
            float availableLength = textureLength - torchWidth; //The available length for the torches to move.
            float posFactor = availableLength / (torchPositions - 1); //The factor to multiply the position by to get the x position.
            float pulse = modelName.matches(".*_p[1-7].*") ? //Gets the position index of the pulse torch from the model name.
                    Integer.parseInt(modelName.substring(modelName.indexOf("_p") + 2, modelName.indexOf("_p") + 3)) : 0;
            float delay = modelName.matches(".*_d[1-7].*") ? //Gets the position index of the delay torch from the model name.
                    Integer.parseInt(modelName.substring(modelName.indexOf("_d") + 2, modelName.indexOf("_d") + 3)) : 0;

            JsonObject pulseTorchModel = createTorchModel(initialX + (pulse * posFactor), pulseInitialZ);
            elements.add(pulseTorchModel);

            JsonObject delayTorchModel = createTorchModel(initialX + (delay * posFactor), delayInitialZ);
            elements.add(delayTorchModel);

            if (powered) {
                List<JsonObject> pulseHalos = createTorchHaloModels(initialX + (pulse * posFactor), pulseInitialZ);
                for (var halo : pulseHalos) {
                    elements.add(halo);
                }
                List<JsonObject> delayHalos = createTorchHaloModels(initialX + (delay * posFactor), delayInitialZ);
                for (var halo : delayHalos) {
                    elements.add(halo);
                }
            }
            model.add("textures", textures);
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<JsonObject> createDecoderModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "decoder";
        List<String> modelNames = generateUniqueDecoderModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);
            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            boolean powered = modelName.contains("_on");
            String pString = powered ? "_on" : "";
            textures.addProperty("particle", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("side", JAAVAA.id("block/smooth_polished_deepslate").toString());
            textures.addProperty("unlit", Identifier.ofVanilla("block/redstone_torch_off").toString());
            if (powered) textures.addProperty("lit", Identifier.ofVanilla("block/redstone_torch").toString());
            model.add("textures", textures);
            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Torches
            boolean tall = !modelName.contains("_demux");
            boolean left = modelName.contains("_l");
            boolean front = modelName.contains("_f");
            boolean right = modelName.contains("_r");
            String key = powered ? "#lit" : "#unlit";
            JsonObject leftRearTorch = createTorchModel(3.0f, 12.0f, key);
            JsonObject rightRearTorch = createTorchModel(11.0f, 12.0f, key);
            JsonObject leftTorch = createTorchModel(2.0f, 7.0f, powered && left ? "#lit" : "#unlit", tall);
            JsonObject frontTorch = createTorchModel(7.0f, 2.0f, powered && front ? "#lit" : "#unlit", tall);
            JsonObject rightTorch = createTorchModel(12.0f, 7.0f, powered && right ? "#lit" : "#unlit", tall);
            elements.add(leftRearTorch);
            elements.add(rightRearTorch);
            elements.add(leftTorch);
            elements.add(frontTorch);
            elements.add(rightTorch);
            //Halos
            if (powered) {
                //Rear Torch Halos
                for (JsonObject halo : createTorchHaloModels(3.0f, 12.0f, "#lit")) elements.add(halo); //Left Rear Torch Halos
                for (JsonObject halo : createTorchHaloModels(11.0f, 12.0f, "#lit")) elements.add(halo); //Right Rear Torch Halos
                //Target Torch Halos
                List<JsonObject> targetTorchHalos = left ? createTorchHaloModels(2.0f, 7.0f, "#lit", tall) :
                        front ? createTorchHaloModels(7.0f, 2.0f, "#lit", tall) :
                                right ? createTorchHaloModels(12.0f, 7.0f, "#lit", tall) : List.of();
                for (JsonObject halo : targetTorchHalos) elements.add(halo);
            }
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<JsonObject> createLogicalANDGateModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "logical_and_gate";
        List<String> modelNames = generateUniqueLogicalANDGateModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);
            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            boolean powered = modelName.contains("_on");
            boolean left = modelName.contains("_l");
            boolean right = modelName.contains("_r");
            String pString = powered ? "_on" : "";
            String lString = left ? "_l" : "";
            String rString = right ? "_r" : "";
            textures.addProperty("particle", JAAVAA.id("block/" + name + pString + lString + rString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + pString + lString + rString).toString());
            textures.addProperty("side", Identifier.ofVanilla("block/smooth_stone").toString());
            textures.addProperty("unlit", Identifier.ofVanilla("block/redstone_torch_off").toString());
            if (powered || left || right) textures.addProperty("lit", Identifier.ofVanilla("block/redstone_torch").toString());
            model.add("textures", textures);
            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Torches
            elements.add(createTorchModel(7.0f, 2.0f, powered ? "#lit" : "#unlit", false)); //Front Torch
            elements.add(createTorchModel(1.0f, 7.0f, left || powered ? "#lit" : "#unlit", true)); //Left Torch
            elements.add(createTorchModel(13.0f, 7.0f, right || powered ? "#lit" : "#unlit", true)); //Right Torch
            if (powered) {
                for (JsonObject halo : createTorchHaloModels(7.0f, 2.0f, "#lit", false))
                    elements.add(halo); //Front Torch Halos
            }
            if (left || powered) {
                for (JsonObject halo : createTorchHaloModels(1.0f, 7.0f, "#lit", true))
                    elements.add(halo); //Left Torch Halos
            }
            if (right || powered) {
                for (JsonObject halo : createTorchHaloModels(13.0f, 7.0f, "#lit", true))
                    elements.add(halo); //Right Torch Halos
            }
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<JsonObject> createLogicalORGateModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "logical_or_gate";
        List<String> modelNames = generateUniqueLogicalORGateModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);
            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            boolean powered = modelName.contains("_on");
            String pString = powered ? "_on" : "";
            textures.addProperty("particle", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("side", Identifier.ofVanilla("block/smooth_stone").toString());
            textures.addProperty("unlit", Identifier.ofVanilla("block/redstone_torch_off").toString());
            if (powered) textures.addProperty("lit", Identifier.ofVanilla("block/redstone_torch").toString());
            model.add("textures", textures);
            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Torches
            String key = powered ? "#lit" : "#unlit";
            elements.add(createTorchModel(7.0f, 2.0f, key, false)); //Front Torch
            if (powered) {
                for (JsonObject halo : createTorchHaloModels(7.0f, 2.0f, "#lit", false))
                    elements.add(halo); //Front Torch Halos
            }
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<JsonObject> createLogicalXORGateModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "logical_xor_gate";
        List<String> modelNames = generateUniqueLogicalXORGateModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);
            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            boolean powered = modelName.contains("_on");
            boolean left = modelName.contains("_l");
            boolean right = modelName.contains("_r");
            String pString = powered ? "_on" : "";
            String lString = left ? "_l" : "";
            String rString = right ? "_r" : "";
            textures.addProperty("particle", JAAVAA.id("block/" + name + pString + lString + rString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + pString + lString + rString).toString());
            textures.addProperty("side", Identifier.ofVanilla("block/smooth_stone").toString());
            textures.addProperty("unlit", Identifier.ofVanilla("block/redstone_torch_off").toString());
            if (powered || left || right) textures.addProperty("lit", Identifier.ofVanilla("block/redstone_torch").toString());
            model.add("textures", textures);
            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Torches
            elements.add(createTorchModel(7.0f, 2.0f, powered ? "#lit" : "#unlit", false)); //Front Torch
            elements.add(createTorchModel(7.0f, 7.0f, powered ? "#lit" : "#unlit", true)); //Middle Torch
            elements.add(createTorchModel(1.0f, 7.0f, left ? "#lit" : "#unlit", true)); //Left Torch
            elements.add(createTorchModel(13.0f, 7.0f, right ? "#lit" : "#unlit", true)); //Right Torch
            if (powered) {
                for (JsonObject halo : createTorchHaloModels(7.0f, 2.0f, "#lit", false))
                    elements.add(halo); //Front Torch Halos
                for (JsonObject halo : createTorchHaloModels(7.0f, 7.0f, "#lit", true))
                    elements.add(halo); //Middle Torch Halos
            }
            if (left) {
                for (JsonObject halo : createTorchHaloModels(1.0f, 7.0f, "#lit", true))
                    elements.add(halo); //Left Torch Halos
            }
            if (right) {
                for (JsonObject halo : createTorchHaloModels(13.0f, 7.0f, "#lit", true))
                    elements.add(halo); //Right Torch Halos
            }
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<JsonObject> createRandomizerModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "randomizer";
        List<String> modelNames = generateUniqueRandomizerModelNames();
        for (String modelName : modelNames) {
            //Initial setup for the model.
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName); //The name of the model. Unused by the game, just for organization.
            model.addProperty("parent", "block/thin_block"); //The parent model to inherit from. Enables using a 3d model as the item model.
            model.addProperty("ambientocclusion", false);
            //Provide textures for the models.
            JsonObject textures = new JsonObject();
            boolean powered = modelName.contains("_on");
            String pString = powered ? "_on" : "";
            textures.addProperty("particle", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("top", JAAVAA.id("block/" + name + pString).toString());
            textures.addProperty("side", JAAVAA.id("block/smooth_polished_deepslate").toString());
            textures.addProperty("lamp", JAAVAA.id("block/adjustable_redstone_lamp").toString());
            if (powered) {
                textures.addProperty("lit", Identifier.ofVanilla("block/redstone_torch").toString());
            } else {
                textures.addProperty("unlit", Identifier.ofVanilla("block/redstone_torch_off").toString());
            }
            model.add("textures", textures);
            //Creates the elements for the model.
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Torches
            String key = powered ? "#lit" : "#unlit";
            elements.add(createTorchModel(7.0f, 2.0f, key, false)); //Front Torch
            if (powered) {
                for (JsonObject halo : createTorchHaloModels(7.0f, 2.0f, "#lit", false)) elements.add(halo);
            }
            int power = powered ? Integer.parseInt(modelName.substring(modelName.lastIndexOf('_') + 1)) : 0;
            elements.add(createLampModel(6.0f, 10.0f, power)); //Lamp
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static JsonObject createGateBaseModel() {
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(0, 0, 0));
        model.add("to", arrayOf(16, 2, 16));
        var faces = new JsonObject();
        faces.add("north", createFace("#side","north", 0, 14, 16, 16, 0));
        faces.add("south", createFace("#side","south", 0, 14, 16, 16, 0));
        faces.add("west", createFace("#side","west", 0, 14, 16, 16, 0));
        faces.add("east", createFace("#side","east", 0, 14, 16, 16, 0));
        faces.add("up", createFace("#top", null, 0, 0, 16, 16, 0));
        faces.add("down", createFace("#side", "down", 0, 0, 16, 16, 0));
        model.add("faces", faces);
        return model;
    }
    private static JsonObject createFace(String texture, @Nullable String cullface, int u1, int v1, int u2, int v2, int r) {
        JsonObject face = new JsonObject();
        face.addProperty("texture", texture);
        face.add("uv", arrayOf(u1, v1, u2, v2));
        if (cullface != null) {
            face.addProperty("cullface", cullface);
        }
        if (r != 0 && r % 90 == 0) {
            face.addProperty("rotation", r);
        }
        return face;
    }
    private static JsonObject createLampModel(float startX, float startZ, int power) {
        float startY = 0.0f;
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(startX, startY, startZ));
        model.add("to", arrayOf(startX + 4.0f, startY + 4.0f, startZ + 4.0f));
        JsonObject faces = new JsonObject();
        faces.add("north", createFace("#lamp", null, 0, power, 16, power + 1, 0));
        faces.add("south", createFace("#lamp", null, 0, power, 16, power + 1, 0));
        faces.add("west", createFace("#lamp", null, 0, power, 16, power + 1, 0));
        faces.add("east", createFace("#lamp", null, 0, power, 16, power + 1, 0));
        faces.add("up", createFace("#lamp", null, 0, power, 16, power + 1, 0));
        model.add("faces", faces);
        return model;
    }
    private static JsonObject createRepeaterLockModel() {
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(2, 2, 7));
        model.add("to", arrayOf(14, 4, 9));
        JsonObject faces = new JsonObject();
        faces.add("north", createFace("#lock", null, 2, 7, 14, 9, 0));
        faces.add("south", createFace("#lock", null, 2, 7, 14, 9, 0));
        faces.add("west", createFace("#lock", null, 6, 7, 8, 9, 0));
        faces.add("east", createFace("#lock", null, 6, 7, 8, 9, 0));
        faces.add("up", createFace("#lock", null, 7, 2, 9, 14, 90));
        model.add("faces", faces);
        return model;
    }
    private static JsonObject createTorchModel(float startX, float startZ) {
        return createTorchModel(startX, startZ, "#torch");
    }
    private static JsonObject createTorchModel(float startX, float startZ, String key) {
        return createTorchModel(startX, startZ, key, true);
    }
    private static JsonObject createTorchModel(float startX, float startZ, String key, boolean tall) {
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(startX, 2, startZ));
        model.add("to", arrayOf(startX + 2, tall ? 7 : 5, startZ + 2));
        JsonObject faces = new JsonObject();
        int u1 = 7;
        int v1 = 6;
        int u2 = 9;
        int v2 = tall ? 11 : 9;
        int r = 0;
        faces.add("north", createFace(key, null, u1, v1, u2, v2, r));
        faces.add("south", createFace(key, null, u1, v1, u2, v2, r));
        faces.add("west", createFace(key, null, u1, v1, u2, v2, r));
        faces.add("east", createFace(key, null, u1, v1, u2, v2, r));
        faces.add("up", createFace(key, null, u1, v1, u2, u2 - 1, r));
        faces.add("down", createFace(key, null, u1, v1, u2, v2, r));
        model.add("faces", faces);
        return model;
    }
    private static List<JsonObject> createTorchHaloModels(float startX, float startZ) {
        return createTorchHaloModels(startX, startZ, "#torch");
    }
    private static List<JsonObject> createTorchHaloModels(float startX, float startZ, String key) {
        return createTorchHaloModels(startX, startZ, key, true);
    }
    private static List<JsonObject> createTorchHaloModels(float startX, float startZ, String key, boolean tall) {
        List<JsonObject> models = new ArrayList<>();

        float haloStartX = startX - 0.5f;
        float haloStartY = tall ? 4.5f : 2.5f;
        float haloStartZ = startZ - 0.5f;
        float haloEndX = startX + 2.5f;
        float haloEndY = haloStartY + 3.0f;
        float haloEndZ = startZ + 2.5f;

        JsonObject upModel = new JsonObject();
        upModel.add("from", arrayOf(haloStartX, haloStartY, haloStartZ));
        upModel.add("to", arrayOf(haloEndX, haloStartY, haloEndZ));
        upModel.addProperty("shade", false);
        JsonObject upFace = new JsonObject();
        upFace.add("up", createFace(key, null, 8, 5, 9, 6, 0));
        upModel.add("faces", upFace);
        models.add(upModel);

        JsonObject downModel = new JsonObject();
        downModel.add("from", arrayOf(haloStartX, haloEndY, haloStartZ));
        downModel.add("to", arrayOf(haloEndX, haloEndY, haloEndZ));
        downModel.addProperty("shade", false);
        JsonObject downFace = new JsonObject();
        downFace.add("down", createFace(key, null, 8, 5, 9, 6, 0));
        downModel.add("faces", downFace);
        models.add(downModel);

        JsonObject northModel = new JsonObject();
        northModel.add("from", arrayOf(haloStartX, haloStartY, haloStartZ));
        northModel.add("to", arrayOf(haloEndX, haloEndY, haloStartZ));
        northModel.addProperty("shade", false);
        JsonObject northFace = new JsonObject();
        northFace.add("south", createFace(key, null, 8, 5, 9, 6, 0));
        northModel.add("faces", northFace);
        models.add(northModel);

        JsonObject southModel = new JsonObject();
        southModel.add("from", arrayOf(haloStartX, haloStartY, haloEndZ));
        southModel.add("to", arrayOf(haloEndX, haloEndY, haloEndZ));
        southModel.addProperty("shade", false);
        JsonObject southFace = new JsonObject();
        southFace.add("north", createFace(key, null, 8, 5, 9, 6, 0));
        southModel.add("faces", southFace);
        models.add(southModel);

        JsonObject westModel = new JsonObject();
        westModel.add("from", arrayOf(haloStartX, haloStartY, haloStartZ));
        westModel.add("to", arrayOf(haloStartX, haloEndY, haloEndZ));
        westModel.addProperty("shade", false);
        JsonObject westFace = new JsonObject();
        westFace.add("east", createFace(key, null, 8, 5, 9, 6, 0));
        westModel.add("faces", westFace);
        models.add(westModel);

        JsonObject eastModel = new JsonObject();
        eastModel.add("from", arrayOf(haloEndX, haloStartY, haloStartZ));
        eastModel.add("to", arrayOf(haloEndX, haloEndY, haloEndZ));
        eastModel.addProperty("shade", false);
        JsonObject eastFace = new JsonObject();
        eastFace.add("west", createFace(key, null, 8, 5, 9, 6, 0));
        eastModel.add("faces", eastFace);
        models.add(eastModel);

        return models;
    }
    private static List<String> generateUniqueAdderModelNames() {
        List<String> modelNames = new ArrayList<>();
        for (boolean powered : new boolean[] {false, true}) {
            for (boolean leftPowered : new boolean[] {false, true}) {
                for (boolean backPowered : new boolean[] {false, true}) {
                    for (boolean rightPowered : new boolean[] {false, true}) {
                        String idPath = "adder";
                        if (powered) {
                            idPath += "_on";
                            if (leftPowered) {
                                idPath += "_l";
                            }
                            if (backPowered) {
                                idPath += "_b";
                            }
                            if (rightPowered) {
                                idPath += "_r";
                            }
                        }
                        modelNames.add(idPath);
                    }
                }
            }
        }
        return modelNames.stream().distinct().toList();
    }
    private static List<String> generateUniqueAdvancedRepeaterModelNames() {
        List<Integer> delays = JAAVAABlockProperties.DELAY.getValues();
        List<Integer> pulses = JAAVAABlockProperties.PULSE.getValues();

        List<String> modelNames = new ArrayList<>();
        for (boolean powered : new boolean[] {false, true}) {
            for (boolean locked : new boolean[] {false, true}) {
                for (int delay = delays.getFirst(); delay <= delays.getLast(); delay++) {
                    for (int pulse = pulses.getFirst(); pulse <= pulses.getLast(); pulse++) {
                        String idPath = "advanced_repeater";
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
                        modelNames.add(idPath);
                    }
                }
            }
        }
        return modelNames.stream().distinct().toList();
    }
    private static List<String> generateUniqueDecoderModelNames() {
        List<DecoderMode> modes = JAAVAABlockProperties.DECODER_MODE.getValues();
        List<DecoderTarget> targets = JAAVAABlockProperties.DECODER_TARGET.getValues();

        List<String> modelNames = new ArrayList<>();
        for (boolean powered : new boolean[] {false, true}) {
            for (DecoderMode mode : modes) {
                for (DecoderTarget target : targets) {
                    String idPath = "decoder";
                    if (powered) {
                        idPath += "_on";
                        if (target != DecoderTarget.NONE) {
                            idPath += "_" + target.name().substring(0, 1).toLowerCase();
                        } else {
                            continue;
                        }
                    }
                    if (mode == DecoderMode.DEMUX) {
                        idPath += "_demux";
                    }
                    modelNames.add(idPath);
                }
            }
        }
        return modelNames.stream().distinct().toList();
    }
    private static List<String> generateUniqueLogicalANDGateModelNames() {
        List<String> modelNames = new ArrayList<>();
        for (boolean powered : new boolean[] {false, true}) {
            for (boolean left : new boolean[] {false, true}) {
                for (boolean right : new boolean[] {false, true}) {
                    String idPath = "logical_and_gate";
                    if (powered) {
                        idPath += "_on";
                    } else {
                        if (left) {
                            idPath += "_l";
                        } else if (right) {
                            idPath += "_r";
                        }
                    }
                    modelNames.add(idPath);
                }
            }
        }
        return modelNames.stream().distinct().toList();
    }
    private static List<String> generateUniqueLogicalORGateModelNames() {
        List<String> modelNames = new ArrayList<>();
        modelNames.add("logical_or_gate");
        modelNames.add("logical_or_gate_on");
        return(modelNames);
    }
    private static List<String> generateUniqueLogicalXORGateModelNames() {
        List<String> modelNames = new ArrayList<>();
        for (boolean powered : new boolean[] {false, true}) {
            for (boolean left : new boolean[] {false, true}) {
                for (boolean right : new boolean[] {false, true}) {
                    String idPath = "logical_xor_gate";
                    if (powered && left && right) continue; //Invalid State
                    if (powered && !left && !right) continue; //Invalid State
                    if (!powered && ((left || right) && !(left && right))) continue; //Invalid State
                    if (powered) {
                        idPath += "_on";
                    }
                    if (left) {
                        idPath += "_l";
                    }
                    if (right) {
                        idPath += "_r";
                    }
                    modelNames.add(idPath);
                }
            }
        }
        return modelNames.stream().distinct().toList();
    }
    private static List<String> generateUniqueRandomizerModelNames() {
        List<String> names = new ArrayList<>();
        for (boolean on : new Boolean[]{false, true}) {
            for (int power : RandomizerBlock.POWER.getValues()) {
                String idPath = "randomizer";
                if (on && power > 0) {
                    idPath += "_on_" + power;
                }
                if(!names.contains(idPath)) names.add(idPath);
            }
        }
        return names;
    }
    private CompletableFuture<?> writeModel(DataWriter writer, JsonObject model) {
        String modelName = model.get("name").getAsString();
        Path modelPath = this.OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName + ".json");
        return DataProvider.writeToPath(writer, model, modelPath);
    }
}
