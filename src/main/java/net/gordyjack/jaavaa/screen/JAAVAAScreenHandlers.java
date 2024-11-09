package net.gordyjack.jaavaa.screen;

import com.fasterxml.jackson.annotation.*;
import net.fabricmc.fabric.api.screenhandler.v1.*;
import net.gordyjack.jaavaa.*;
import net.minecraft.registry.*;
import net.minecraft.resource.featuretoggle.*;
import net.minecraft.screen.*;
import net.minecraft.util.math.*;

public class JAAVAAScreenHandlers {
    public static final ScreenHandlerType<AlloyFurnaceBlockEntityScreenHandler> ALLOY_FURNACE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, JAAVAA.id("alloy_furnace_screen_handler"),
                    new ExtendedScreenHandlerType<>(AlloyFurnaceBlockEntityScreenHandler::new, BlockPos.PACKET_CODEC));
    
    public static void init() {
        JAAVAA.log("Initializing screen handlers");
    }
}
