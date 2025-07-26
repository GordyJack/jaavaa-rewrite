package net.gordyjack.jaavaa.item;

import net.gordyjack.jaavaa.*;
import net.minecraft.item.equipment.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

public final class JAAVAAEquipmentAssetKeys{
    private static final RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));

    public static final RegistryKey<EquipmentAsset> AURON = register("auron");
    public static final RegistryKey<EquipmentAsset> STEEL = register("steel");
    public static final RegistryKey<EquipmentAsset> HAPPY_GHAST_HARNESS = register("happy_ghast_harness");

    private static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(REGISTRY_KEY, JAAVAA.id(name));
    }
    public static void init() {
        JAAVAA.log("Registering Equipment Asset Keys");
    }
}
