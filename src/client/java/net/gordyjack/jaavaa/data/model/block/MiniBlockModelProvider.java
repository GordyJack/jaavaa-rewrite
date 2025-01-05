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

public class MiniBlockModelProvider implements DataProvider {
    private final FabricDataOutput OUTPUT;

    public MiniBlockModelProvider(FabricDataOutput output) {
        this.OUTPUT = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> returns = new ArrayList<>();
        List<JsonObject> baseModels = generateBaseMiniBlockModels();
        for (var model : baseModels) {
//            if (model.get("position").getAsString().equals("00000001")
//                    || model.get("position").getAsString().equals("00000010")
//                    || model.get("position").getAsString().equals("00000100")
//                    || model.get("position").getAsString().equals("00001000")
//                    || model.get("position").getAsString().equals("00010000")
//                    || model.get("position").getAsString().equals("00100000")
//                    || model.get("position").getAsString().equals("01000000")
//                    || model.get("position").getAsString().equals("10000000")) {
//                JAAVAA.log(model.toString(), 'e');
//            }
            String modelName = "mini_block_" + model.get("position").getAsString();
            Path modelPath = OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName + ".json");
            returns.add(DataProvider.writeToPath(writer, model, modelPath));
        }
        for (var model : generateMiniBlockModels((MiniBlock) JAAVAABlocks.STONE_MINI_BLOCK, Blocks.STONE)) {
            String modelName = JAAVAA.idFromItem(JAAVAABlocks.STONE_MINI_BLOCK).getPath() + "_" + model.get("position").getAsString();
            Path modelPath = OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName + ".json");
            returns.add(DataProvider.writeToPath(writer, model, modelPath));
        }
        for (var model : generateMiniBlockModels((MiniBlock) JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE_MINI_BLOCK, JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE)) {
            String modelName = JAAVAA.idFromItem(JAAVAABlocks.SMOOTH_POLISHED_DEEPSLATE_MINI_BLOCK).getPath() + "_" + model.get("position").getAsString();
            Path modelPath = OUTPUT.getPath().resolve("assets/jaavaa/models/block/" + modelName + ".json");
            returns.add(DataProvider.writeToPath(writer, model, modelPath));
        }
        return CompletableFuture.allOf(returns.toArray(CompletableFuture[]::new));
    }
    @Override
    public String getName() {
        return "Mini Block Model Provider";
    }
    private List<JsonObject> generateBaseMiniBlockModels() {
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
            if ((pos & 0b00000001) != 0) elements.add(generateMiniBlock(0b00000001));
            if ((pos & 0b00000010) != 0) elements.add(generateMiniBlock(0b00000010));
            if ((pos & 0b00000100) != 0) elements.add(generateMiniBlock(0b00000100));
            if ((pos & 0b00001000) != 0) elements.add(generateMiniBlock(0b00001000));
            if ((pos & 0b00010000) != 0) elements.add(generateMiniBlock(0b00010000));
            if ((pos & 0b00100000) != 0) elements.add(generateMiniBlock(0b00100000));
            if ((pos & 0b01000000) != 0) elements.add(generateMiniBlock(0b01000000));
            if ((pos & 0b10000000) != 0) elements.add(generateMiniBlock(0b10000000));
            model.add("elements", elements);
            models.add(model);
        }
        return models;
    }
    private JsonObject generateMiniBlock(int position) {
        JsonObject model00000001 = new JsonObject();
        model00000001.add("from", arrayOf(0, 0, 0));
        model00000001.add("to", arrayOf(8, 8, 8));
        JsonObject faces00000001 = new JsonObject();
        faces00000001.add("down", createFace("#down", null, 0, 0, 8, 8, 0));
        faces00000001.add("up", createFace("#up", null, 0, 0, 8, 8, 0));
        faces00000001.add("north", createFace("#side", null, 0, 0, 8, 8, 0));
        faces00000001.add("south", createFace("#side", null, 0, 0, 8, 8, 0));
        faces00000001.add("west", createFace("#side", null, 0, 0, 8, 8, 0));
        faces00000001.add("east", createFace("#side", null, 0, 0, 8, 8, 0));
        model00000001.add("faces", faces00000001);

        JsonObject model00000010 = new JsonObject();
        model00000010.add("from", arrayOf(8, 0, 0));
        model00000010.add("to", arrayOf(16, 8, 8));
        JsonObject faces00000010 = new JsonObject();
        faces00000010.add("down", createFace("#down", null, 8, 0, 16, 8, 0));
        faces00000010.add("up", createFace("#up", null, 8, 0, 16, 8, 0));
        faces00000010.add("north", createFace("#side", null, 8, 0, 16, 8, 0));
        faces00000010.add("south", createFace("#side", null, 8, 0, 16, 8, 0));
        faces00000010.add("west", createFace("#side", null, 8, 0, 16, 8, 0));
        faces00000010.add("east", createFace("#side", null, 8, 0, 16, 8, 0));
        model00000010.add("faces", faces00000010);

        JsonObject model00000100 = new JsonObject();
        model00000100.add("from", arrayOf(0, 0, 8));
        model00000100.add("to", arrayOf(8, 8, 16));
        JsonObject faces00000100 = new JsonObject();
        faces00000100.add("down", createFace("#down", null, 0, 0, 8, 8, 0));
        faces00000100.add("up", createFace("#up", null, 0, 0, 8, 8, 0));
        faces00000100.add("north", createFace("#side", null, 0, 0, 8, 8, 0));
        faces00000100.add("south", createFace("#side", null, 0, 0, 8, 8, 0));
        faces00000100.add("west", createFace("#side", null, 0, 0, 8, 8, 0));
        faces00000100.add("east", createFace("#side", null, 0, 0, 8, 8, 0));
        model00000100.add("faces", faces00000100);

        JsonObject model00001000 = new JsonObject();
        model00001000.add("from", arrayOf(8, 0, 8));
        model00001000.add("to", arrayOf(16, 8, 16));
        JsonObject faces00001000 = new JsonObject();
        faces00001000.add("down", createFace("#down", null, 8, 0, 16, 8, 0));
        faces00001000.add("up", createFace("#up", null, 8, 0, 16, 8, 0));
        faces00001000.add("north", createFace("#side", null, 8, 0, 16, 8, 0));
        faces00001000.add("south", createFace("#side", null, 8, 0, 16, 8, 0));
        faces00001000.add("west", createFace("#side", null, 8, 0, 16, 8, 0));
        faces00001000.add("east", createFace("#side", null, 8, 0, 16, 8, 0));
        model00001000.add("faces", faces00001000);

        JsonObject model00010000 = new JsonObject();
        model00010000.add("from", arrayOf(0, 8, 0));
        model00010000.add("to", arrayOf(8, 16, 8));
        JsonObject faces00010000 = new JsonObject();
        faces00010000.add("down", createFace("#down", null, 0, 8, 8, 16, 0));
        faces00010000.add("up", createFace("#up", null, 0, 8, 8, 16, 0));
        faces00010000.add("north", createFace("#side", null, 0, 8, 8, 16, 0));
        faces00010000.add("south", createFace("#side", null, 0, 8, 8, 16, 0));
        faces00010000.add("west", createFace("#side", null, 0, 8, 8, 16, 0));
        faces00010000.add("east", createFace("#side", null, 0, 8, 8, 16, 0));
        model00010000.add("faces", faces00010000);

        JsonObject model00100000 = new JsonObject();
        model00100000.add("from", arrayOf(8, 8, 0));
        model00100000.add("to", arrayOf(16, 16, 8));
        JsonObject faces00100000 = new JsonObject();
        faces00100000.add("down", createFace("#down", null, 8, 8, 16, 16, 0));
        faces00100000.add("up", createFace("#up", null, 8, 8, 16, 16, 0));
        faces00100000.add("north", createFace("#side", null, 8, 8, 16, 16, 0));
        faces00100000.add("south", createFace("#side", null, 8, 8, 16, 16, 0));
        faces00100000.add("west", createFace("#side", null, 8, 8, 16, 16, 0));
        faces00100000.add("east", createFace("#side", null, 8, 8, 16, 16, 0));
        model00100000.add("faces", faces00100000);

        JsonObject model01000000 = new JsonObject();
        model01000000.add("from", arrayOf(0, 8, 8));
        model01000000.add("to", arrayOf(8, 16, 16));
        JsonObject faces01000000 = new JsonObject();
        faces01000000.add("down", createFace("#down", null, 0, 8, 8, 16, 0));
        faces01000000.add("up", createFace("#up", null, 0, 8, 8, 16, 0));
        faces01000000.add("north", createFace("#side", null, 0, 8, 8, 16, 0));
        faces01000000.add("south", createFace("#side", null, 0, 8, 8, 16, 0));
        faces01000000.add("west", createFace("#side", null, 0, 8, 8, 16, 0));
        faces01000000.add("east", createFace("#side", null, 0, 8, 8, 16, 0));
        model01000000.add("faces", faces01000000);

        JsonObject model10000000 = new JsonObject();
        model10000000.add("from", arrayOf(8, 8, 8));
        model10000000.add("to", arrayOf(16, 16, 16));
        JsonObject faces10000000 = new JsonObject();
        faces10000000.add("down", createFace("#down", null, 8, 8, 16, 16, 0));
        faces10000000.add("up", createFace("#up", null, 8, 8, 16, 16, 0));
        faces10000000.add("north", createFace("#side", null, 8, 8, 16, 16, 0));
        faces10000000.add("south", createFace("#side", null, 8, 8, 16, 16, 0));
        faces10000000.add("west", createFace("#side", null, 8, 8, 16, 16, 0));
        faces10000000.add("east", createFace("#side", null, 8, 8, 16, 16, 0));
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
    public static List<JsonObject> generateMiniBlockElements() {
        List<JsonObject> models = new ArrayList<>();

        // Iterate over all positions in x, y, z
        for (int y = 0; y <= 8; y += 8) {
            for (int z = 0; z <= 8; z += 8) {
                for (int x = 0; x <= 8; x += 8) {
                    JsonObject model = new JsonObject();

                    // Define "from" and "to" dynamically based on x, y, z
                    model.add("from", arrayOf(x, y, z));
                    model.add("to", arrayOf(x + 8, y + 8, z + 8));

                    JsonObject faces = new JsonObject();
                    faces.add("down", createFace("#down", "down", 0, 0, 8, 8, 0));
                    faces.add("up", createFace("#up", "up", 0, 0, 8, 8, 0));
                    faces.add("north", createFace("#side", "north", 0, 0, 8, 8, 0));
                    faces.add("south", createFace("#side", "south", 0, 0, 8, 8, 0));
                    faces.add("west", createFace("#side", "west", 0, 0, 8, 8, 0));
                    faces.add("east", createFace("#side", "east", 0, 0, 8, 8, 0));
                    model.add("faces", faces);

                    models.add(model);
                }
            }
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
    private static List<JsonObject> generateMiniBlockModels(@NotNull MiniBlock miniBlock, @NotNull Block parentBlock) {
        List<JsonObject> models = new ArrayList<>();
        String parentIdPath = JAAVAA.idFromItem(parentBlock).toString().replace(":", ":block/");
        for (String posString : getPosStrings()) {
            String subIdPath =  "jaavaa:block/mini_block_" + posString;
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
}
