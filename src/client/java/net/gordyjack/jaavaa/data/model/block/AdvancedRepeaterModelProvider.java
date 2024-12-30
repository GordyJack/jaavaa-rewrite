package net.gordyjack.jaavaa.data.model.block;

import com.google.gson.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class AdvancedRepeaterModelProvider implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final FabricDataOutput OUTPUT;

    private static final JsonObject base = createRepeaterBaseModel();
    private static final JsonObject repeaterLock = createRepeaterLockModel();

    public AdvancedRepeaterModelProvider(FabricDataOutput output) {
        this.OUTPUT = output;
    }
    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> returns = new ArrayList<>();
        List<JsonObject> models = createAdvancedRepeaterModels();
        JAAVAA.log("Creating " + models.size() + " models for advanced_repeater...");
        for (JsonObject model : models) {
            String modelName = model.get("name").getAsString();
            JAAVAA.log("Creating model: " + modelName);
            Path modelPath = OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName + ".json");
            returns.add(DataProvider.writeToPath(writer, GSON.toJsonTree(model), modelPath));
        }
        return CompletableFuture.allOf(returns.toArray(CompletableFuture[]::new));
    }
    @Override
    public String getName() {
        return "";
    }
    private static List<JsonObject> createAdvancedRepeaterModels() {
        List<JsonObject> models = new ArrayList<>();

        String name = "advanced_repeater";
        List<String> modelNames = getUniqueModelNames(name);
        for (String modelName : modelNames) {
            JsonObject model = new JsonObject();
            model.addProperty("name", modelName);
            model.addProperty("ambientOcclusion", false);
            model.addProperty("parent", "block");
            JsonObject textures = new JsonObject();
            textures.addProperty("side", JAAVAA.id("block/smooth_polished_deepslate").toString());
            JsonArray elements = new JsonArray();
            elements.add(base);
            //Provide textures for powered.
            boolean powered = modelName.contains("_on");
            if (powered) {
                textures.addProperty("particle", JAAVAA.id("block/" + name + "_on").toString());
                textures.addProperty("top", JAAVAA.id("block/" + name + "_on").toString());
                textures.addProperty("torch", Identifier.ofVanilla("block/redstone_torch").toString());
            } else {
                textures.addProperty("particle", JAAVAA.id("block/" + name).toString());
                textures.addProperty("top", JAAVAA.id("block/" + name).toString());
                textures.addProperty("torch", Identifier.ofVanilla("block/redstone_torch_off").toString());
            }
            //Provides models and textures if locked.
            if (modelName.contains("_locked")) {
                textures.addProperty("lock", Identifier.ofVanilla("block/bedrock").toString());
                elements.add(repeaterLock);
            }
            float initialX = 2.0f, pulseInitialZ = 3.0f, delayInitialZ = 11.0f, posFactor = 12.0f/6.0f;
            float pulse = modelName.matches(".*_p[1-5].*") ?
                    Integer.parseInt(modelName.substring(modelName.indexOf("_p") + 2, modelName.indexOf("_p") + 3)) : 0;
            float delay = modelName.matches(".*_d[1-5].*") ?
                    Integer.parseInt(modelName.substring(modelName.indexOf("_d") + 2, modelName.indexOf("_d") + 3)) : 0;
            JsonObject pulseTorchModel = createTorchModel(initialX + (pulse * posFactor), pulseInitialZ);
            elements.add(pulseTorchModel);
            if (powered) {
                List<JsonObject> pulseHalos = createTorchModelHalos(initialX + (pulse * posFactor), pulseInitialZ);
                for (var halo : pulseHalos) {
                    elements.add(halo);
                }
            }
            JsonObject delayTorchModel = createTorchModel(initialX + (delay * posFactor), delayInitialZ);
            elements.add(delayTorchModel);
            if (powered) {
                List<JsonObject> delayHalos = createTorchModelHalos(initialX + (delay * posFactor), delayInitialZ);
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
    @SafeVarargs
    private static <T extends Number> JsonArray arrayOf(T... values) {
        JsonArray array = new JsonArray();
        for (T value : values) {
            array.add(value);
        }
        return array;
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
    private static JsonObject createRepeaterBaseModel() {
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(0, 0, 0));
        model.add("to", arrayOf(16, 2, 16));
        var faces = new JsonObject();
        faces.add("north", createFace("#side","north", 0, 0, 16, 16, 0));
        faces.add("south", createFace("#side","south", 0, 0, 16, 16, 0));
        faces.add("west", createFace("#side","west", 0, 0, 16, 16, 0));
        faces.add("east", createFace("#side","east", 0, 0, 16, 16, 0));
        faces.add("up", createFace("#top", null, 0, 0, 16, 16, 0));
        faces.add("down", createFace("#side", "down", 0, 0, 16, 16, 0));
        model.add("faces", faces);
        return model;
    }
    private static JsonObject createRepeaterLockModel() {
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(2, 2, 7));
        model.add("to", arrayOf(14, 4, 9));
        JsonObject faces = new JsonObject();
        faces.add("north", createFace("#lock", null, 2, 2, 14, 4, 0));
        faces.add("south", createFace("#lock", null, 2, 2, 14, 4, 0));
        faces.add("west", createFace("#lock", null, 2, 2, 14, 4, 0));
        faces.add("east", createFace("#lock", null, 2, 2, 14, 4, 0));
        faces.add("up", createFace("#lock", null, 2, 4, 14, 8, 0));
        faces.add("down", createFace("#lock", null, 2, 4, 14, 8, 0));
        model.add("faces", faces);
        return model;
    }
    private static JsonObject createTorchModel(float startX, float startZ) {
        JsonObject model = new JsonObject();
        model.add("from", arrayOf(startX, 2, startZ));
        model.add("to", arrayOf(startX + 2, 7, startZ + 2));
        JsonObject faces = new JsonObject();
        faces.add("north", createFace("#torch", null, 7, 6, 9, 11, 0));
        faces.add("south", createFace("#torch", null, 7, 6, 9, 11, 0));
        faces.add("west", createFace("#torch", null, 7, 6, 9, 11, 0));
        faces.add("east", createFace("#torch", null, 7, 6, 9, 11, 0));
        faces.add("up", createFace("#torch", null, 7, 6, 9, 8, 0));
        faces.add("down", createFace("#torch", null, 7, 6, 9, 11, 0));
        model.add("faces", faces);
        return model;
    }
    //TODO: Fix the halos coordinates.
    private static List<JsonObject> createTorchModelHalos(float startX, float startZ) {
        List<JsonObject> models = new ArrayList<>();

        JsonObject upModel = new JsonObject();
        upModel.add("from", arrayOf(startX - 0.5, 3.5, startZ - 0.5));
        upModel.add("to", arrayOf(startX + 1.5, 4.5, startZ + 1.5));
        upModel.addProperty("shade", false);
        JsonObject upFace = new JsonObject();
        upFace.add("up", createFace("#torch", null, 8, 5, 9, 6, 0));
        upModel.add("faces", upFace);
        models.add(upModel);

        JsonObject downModel = new JsonObject();
        downModel.add("from", arrayOf(startX - 0.5, 11.5, startZ - 0.5));
        downModel.add("to", arrayOf(startX + 1.5, 12.5, startZ + 1.5));
        downModel.addProperty("shade", false);
        JsonObject downFace = new JsonObject();
        downFace.add("down", createFace("#torch", null, 8, 5, 9, 6, 0));
        downModel.add("faces", downFace);
        models.add(downModel);

        JsonObject northModel = new JsonObject();
        northModel.add("from", arrayOf(startX - 0.5, 4.5, startZ - 0.5));
        northModel.add("to", arrayOf(startX + 1.5, 11.5, startZ + 1.5));
        northModel.addProperty("shade", false);
        JsonObject northFace = new JsonObject();
        northFace.add("north", createFace("#torch", null, 8, 5, 9, 6, 0));
        northModel.add("faces", northFace);
        models.add(northModel);

        JsonObject southModel = new JsonObject();
        southModel.add("from", arrayOf(startX - 0.5, 4.5, startZ - 0.5));
        southModel.add("to", arrayOf(startX + 1.5, 11.5, startZ + 1.5));
        southModel.addProperty("shade", false);
        JsonObject southFace = new JsonObject();
        southFace.add("south", createFace("#torch", null, 8, 5, 9, 6, 0));
        southModel.add("faces", southFace);
        models.add(southModel);

        JsonObject westModel = new JsonObject();
        westModel.add("from", arrayOf(startX - 0.5, 4.5, startZ - 0.5));
        westModel.add("to", arrayOf(startX + 1.5, 11.5, startZ + 1.5));
        westModel.addProperty("shade", false);
        JsonObject westFace = new JsonObject();
        westFace.add("west", createFace("#torch", null, 8, 5, 9, 6, 0));
        westModel.add("faces", westFace);
        models.add(westModel);

        JsonObject eastModel = new JsonObject();
        eastModel.add("from", arrayOf(startX - 0.5, 4.5, startZ - 0.5));
        eastModel.add("to", arrayOf(startX + 1.5, 11.5, startZ + 1.5));
        eastModel.addProperty("shade", false);
        JsonObject eastFace = new JsonObject();
        eastFace.add("east", createFace("#torch", null, 8, 5, 9, 6, 0));
        eastModel.add("faces", eastFace);
        models.add(eastModel);

        return models;
    }
    private static List<String> getUniqueModelNames(String name) {
        List<Integer> delays = JAAVAABlockProperties.DELAY.getValues();
        List<Integer> pulses = JAAVAABlockProperties.PULSE.getValues();

        List<String> modelNames = new ArrayList<>();
        for (boolean powered : new boolean[] {false, true}) {
            for (boolean locked : new boolean[] {false, true}) {
                for (int delay = delays.getFirst(); delay <= delays.getLast(); delay++) {
                    for (int pulse = pulses.getFirst(); pulse <= pulses.getLast(); pulse++) {
                        String idPath = name;
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
}
