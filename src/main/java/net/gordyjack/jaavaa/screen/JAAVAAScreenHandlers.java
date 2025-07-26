package net.gordyjack.jaavaa.screen;

import net.fabricmc.fabric.api.screenhandler.v1.*;
import net.gordyjack.jaavaa.*;
import net.minecraft.registry.*;
import net.minecraft.resource.featuretoggle.*;
import net.minecraft.screen.*;
import net.minecraft.util.math.*;

import java.util.*;

public class JAAVAAScreenHandlers {
    public static final List<ScreenHandlerType<? extends ScreenHandler>> SCREEN_HANDLERS = new ArrayList<>();
    public static final ScreenHandlerType<AlloyFurnaceBlockEntityScreenHandler> ALLOY_FURNACE_SCREEN_HANDLER =
            registerCustom("alloy_furnace_screen_handler", new ExtendedScreenHandlerType<>(AlloyFurnaceBlockEntityScreenHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<RecyclingEntityScreenHandler> RECYCLING_SCREEN_HANDLER =
            registerCustom("recycling_table_screen_handler", new ExtendedScreenHandlerType<>(RecyclingEntityScreenHandler::new, BlockPos.PACKET_CODEC));

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return registerCustom(id, new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }
    private static <T extends ScreenHandler> ScreenHandlerType<T> registerCustom(String id, ScreenHandlerType<T> handler) {
        ScreenHandlerType<T> registeredHandler = Registry.register(Registries.SCREEN_HANDLER, JAAVAA.id(id), handler);
        SCREEN_HANDLERS.add(registeredHandler);
        return registeredHandler;
    }
    public static void init() {
        JAAVAA.log("Initializing screen handlers");
    }
}
