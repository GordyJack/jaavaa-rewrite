package net.gordyjack.jaavaa.item;

import net.gordyjack.jaavaa.*;
import net.minecraft.item.equipment.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

public interface JAAVAAEquipmentAssetKeys{
    RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));

    RegistryKey<EquipmentAsset> AURON = register("auron");
    RegistryKey<EquipmentAsset> STEEL = register("steel");
    RegistryKey<EquipmentAsset> HAPPY_GHAST_HARNESS = register("happy_ghast_harness");

    static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(REGISTRY_KEY, JAAVAA.id(name));
    }
    static void init() {
        JAAVAA.log("Registering Equipment Asset Keys");
    }
}
