package net.gordyjack.jaavaa.item;

import net.gordyjack.jaavaa.*;
import net.minecraft.item.equipment.*;
import net.minecraft.registry.*;

import static net.minecraft.item.equipment.EquipmentAssetKeys.register;

public interface JAAVAAEquipmentAssetKeys{
    RegistryKey<EquipmentAsset> AURON = register("auron");
    RegistryKey<EquipmentAsset> HAPPY_GHAST_HARNESS = register("happy_ghast_harness");

    static void init() {
        JAAVAA.log("Registering Equipment Asset Keys");
    }
}
