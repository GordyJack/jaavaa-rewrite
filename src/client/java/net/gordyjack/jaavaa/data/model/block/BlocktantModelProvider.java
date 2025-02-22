package net.gordyjack.jaavaa.data.model.block;

import com.google.gson.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import org.jetbrains.annotations.*;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class BlocktantModelProvider implements DataProvider {
    private final FabricDataOutput OUTPUT;

    public BlocktantModelProvider(FabricDataOutput output) {
        this.OUTPUT = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> returns = new ArrayList<>();
        List<JsonObject> baseModels = generateBaseBlocktantModels();
        for (var model : baseModels) {
            String modelName = "blocktant_" + model.get("position").getAsString();
            Path modelPath = this.OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName + ".json");
            returns.add(DataProvider.writeToPath(writer, model, modelPath));
        }
        for (Blocktant blocktant : JAAVAABlocks.BLOCKTANTS.keySet()) {
            for (JsonObject model : generateBlocktantModels(JAAVAABlocks.BLOCKTANTS.get(blocktant))) {
                String posString = model.get("position").getAsString();
                String modelName = JAAVAA.idFromItem(blocktant).getPath();
                Path blockModelPath = this.OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName  + "_" + posString + ".json");
                if (posString.equals("00000001")) {
                    Path itemModelPath = this.OUTPUT.getPath().resolve("assets/jaavaa/items/" + modelName + ".json");
                    JsonObject item = new JsonObject();
                    JsonObject itemModel = new JsonObject();
                    itemModel.addProperty("type", "minecraft:model");
                    itemModel.addProperty("model", "jaavaa:block/" + modelName + "_00000001");
                    item.add("model", itemModel);
                    returns.add(DataProvider.writeToPath(writer, item, itemModelPath));
                }
                returns.add(DataProvider.writeToPath(writer, model, blockModelPath));
            }
        }
        return CompletableFuture.allOf(returns.toArray(CompletableFuture[]::new));
    }
    @Override
    public String getName() {
        return "Blocktant Model Provider";
    }
    @SafeVarargs
    private static <T extends Number> JsonArray arrayOf(T... values) {
        JsonArray array = new JsonArray();
        for (T value : values) {
            array.add(value);
        }
        return array;
    }
    private static JsonObject createCornerFace(String texture, int quadrant) {
        return switch (quadrant) {
            case 1 -> createFace(texture, null, 0, 0, 8, 8, 0);
            case 2 -> createFace(texture, null, 0, 8, 8, 16, 0);
            case 3 -> createFace(texture, null, 8, 0, 16, 8, 0);
            case 4 -> createFace(texture, null, 8, 8, 16, 16, 0);
            default -> throw new IllegalStateException("Unexpected value: " + quadrant);
        };
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
    private static List<JsonObject> generateBaseBlocktantModels() {
        List<JsonObject> models = new ArrayList<>();
        for (String posString : getPosStrings()) {
            int pos = Integer.parseInt(posString, 2);

            JsonObject model = new JsonObject();
            model.addProperty("parent", "block/block");
            model.addProperty("position", posString);
            JsonObject textures = new JsonObject();
            textures.addProperty("particle", "#side");
            model.add("textures", textures);
            JsonArray elements = new JsonArray();
            if ((pos & 0b00000001) != 0) elements.add(generateBlocktant(0b00000001));
            if ((pos & 0b00000010) != 0) elements.add(generateBlocktant(0b00000010));
            if ((pos & 0b00000100) != 0) elements.add(generateBlocktant(0b00000100));
            if ((pos & 0b00001000) != 0) elements.add(generateBlocktant(0b00001000));
            if ((pos & 0b00010000) != 0) elements.add(generateBlocktant(0b00010000));
            if ((pos & 0b00100000) != 0) elements.add(generateBlocktant(0b00100000));
            if ((pos & 0b01000000) != 0) elements.add(generateBlocktant(0b01000000));
            if ((pos & 0b10000000) != 0) elements.add(generateBlocktant(0b10000000));
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private static List<String> getPosStrings() {
        List<String> posStrings = new ArrayList<>();
        for (int i = 0b00000001; i <= 0b11111111; i++) {
            String posString = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
            posStrings.add(posString);
        }
        return posStrings;
    }
    private static JsonObject generateBlocktant(int position) {
        JsonObject model00000001 = new JsonObject();
        model00000001.add("from", arrayOf(0, 0, 0));
        model00000001.add("to", arrayOf(8, 8, 8));
        JsonObject faces00000001 = new JsonObject();
        faces00000001.add("down", createCornerFace("#down", 2));
        faces00000001.add("up", createCornerFace("#up", 1));
        faces00000001.add("north", createCornerFace("#side", 4));
        faces00000001.add("south", createCornerFace("#side", 2));
        faces00000001.add("west", createCornerFace("#side", 2));
        faces00000001.add("east", createCornerFace("#side", 4));
        model00000001.add("faces", faces00000001);

        JsonObject model00000010 = new JsonObject();
        model00000010.add("from", arrayOf(8, 0, 0));
        model00000010.add("to", arrayOf(16, 8, 8));
        JsonObject faces00000010 = new JsonObject();
        faces00000010.add("down", createCornerFace("#down", 4));
        faces00000010.add("up", createCornerFace("#up", 3));
        faces00000010.add("north", createCornerFace("#side", 2));
        faces00000010.add("south", createCornerFace("#side", 4));
        faces00000010.add("west", createCornerFace("#side", 2));
        faces00000010.add("east", createCornerFace("#side", 4));
        model00000010.add("faces", faces00000010);

        JsonObject model00000100 = new JsonObject();
        model00000100.add("from", arrayOf(0, 0, 8));
        model00000100.add("to", arrayOf(8, 8, 16));
        JsonObject faces00000100 = new JsonObject();
        faces00000100.add("down", createCornerFace("#down", 1));
        faces00000100.add("up", createCornerFace("#up", 2));
        faces00000100.add("north", createCornerFace("#side", 4));
        faces00000100.add("south", createCornerFace("#side", 2));
        faces00000100.add("west", createCornerFace("#side", 4));
        faces00000100.add("east", createCornerFace("#side", 2));
        model00000100.add("faces", faces00000100);

        JsonObject model00001000 = new JsonObject();
        model00001000.add("from", arrayOf(8, 0, 8));
        model00001000.add("to", arrayOf(16, 8, 16));
        JsonObject faces00001000 = new JsonObject();
        faces00001000.add("down", createCornerFace("#down", 3));
        faces00001000.add("up", createCornerFace("#up", 4));
        faces00001000.add("north", createCornerFace("#side", 2));
        faces00001000.add("south", createCornerFace("#side", 4));
        faces00001000.add("west", createCornerFace("#side", 4));
        faces00001000.add("east", createCornerFace("#side", 2));
        model00001000.add("faces", faces00001000);

        JsonObject model00010000 = new JsonObject();
        model00010000.add("from", arrayOf(0, 8, 0));
        model00010000.add("to", arrayOf(8, 16, 8));
        JsonObject faces00010000 = new JsonObject();
        faces00010000.add("down", createCornerFace("#down", 2));
        faces00010000.add("up", createCornerFace("#up", 1));
        faces00010000.add("north", createCornerFace("#side", 3));
        faces00010000.add("south", createCornerFace("#side", 1));
        faces00010000.add("west", createCornerFace("#side", 1));
        faces00010000.add("east", createCornerFace("#side", 3));
        model00010000.add("faces", faces00010000);

        JsonObject model00100000 = new JsonObject();
        model00100000.add("from", arrayOf(8, 8, 0));
        model00100000.add("to", arrayOf(16, 16, 8));
        JsonObject faces00100000 = new JsonObject();
        faces00100000.add("down", createCornerFace("#down", 4));
        faces00100000.add("up", createCornerFace("#up", 3));
        faces00100000.add("north", createCornerFace("#side", 1));
        faces00100000.add("south", createCornerFace("#side", 3));
        faces00100000.add("west", createCornerFace("#side", 1));
        faces00100000.add("east", createCornerFace("#side", 3));
        model00100000.add("faces", faces00100000);

        JsonObject model01000000 = new JsonObject();
        model01000000.add("from", arrayOf(0, 8, 8));
        model01000000.add("to", arrayOf(8, 16, 16));
        JsonObject faces01000000 = new JsonObject();
        faces01000000.add("down", createCornerFace("#down", 1));
        faces01000000.add("up", createCornerFace("#up", 2));
        faces01000000.add("north", createCornerFace("#side", 3));
        faces01000000.add("south", createCornerFace("#side", 1));
        faces01000000.add("west", createCornerFace("#side", 3));
        faces01000000.add("east", createCornerFace("#side", 1));
        model01000000.add("faces", faces01000000);

        JsonObject model10000000 = new JsonObject();
        model10000000.add("from", arrayOf(8, 8, 8));
        model10000000.add("to", arrayOf(16, 16, 16));
        JsonObject faces10000000 = new JsonObject();
        faces10000000.add("down", createCornerFace("#down", 3));
        faces10000000.add("up", createCornerFace("#up", 4));
        faces10000000.add("north", createCornerFace("#side", 1));
        faces10000000.add("south", createCornerFace("#side", 3));
        faces10000000.add("west", createCornerFace("#side", 3));
        faces10000000.add("east", createCornerFace("#side", 1));
        model10000000.add("faces", faces10000000);

        return switch (position) {
            case 0b00000001 -> model00000001;
            case 0b00000010 -> model00000010;
            case 0b00000100 -> model00000100;
            case 0b00001000 -> model00001000;
            case 0b00010000 -> model00010000;
            case 0b00100000 -> model00100000;
            case 0b01000000 -> model01000000;
            case 0b10000000 -> model10000000;
            default -> throw new IllegalStateException("Unexpected value: " + position);
        };
    }
    private static List<JsonObject> generateBlocktantModels(@NotNull Block parentBlock) {
        List<JsonObject> models = new ArrayList<>();
        String parentIdPath = JAAVAA.idFromItem(parentBlock).toString().replace(":", ":block/");
        for (String posString : getPosStrings()) {
            String subIdPath =  "jaavaa:block/blocktant_" + posString;
            JsonObject model = new JsonObject();
            model.addProperty("parent", subIdPath);
            model.addProperty("position", posString);
            JsonObject textures = new JsonObject();
            textures.addProperty("down", parentIdPath);
            textures.addProperty("up", parentIdPath);
            textures.addProperty("side", parentIdPath);
            model.add("textures", textures);
            models.add(model);
        }
        return models;
    }
}
