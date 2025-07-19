package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.structure.*;

import java.util.concurrent.*;

public class JAAVAAStructureTagProvider extends FabricTagProvider<Structure> {
    public JAAVAAStructureTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.STRUCTURE, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.builder(JAAVAATags.Structures.ANCIENT_CITIES)
                .add(StructureKeys.ANCIENT_CITY);
        this.builder(JAAVAATags.Structures.BASTIONS)
                .add(StructureKeys.BASTION_REMNANT);
        this.builder(JAAVAATags.Structures.DESERT_PYRAMIDS)
                .add(StructureKeys.DESERT_PYRAMID);
        this.builder(JAAVAATags.Structures.END_CITIES)
                .add(StructureKeys.END_CITY);
        this.builder(JAAVAATags.Structures.IGLOOS)
                .add(StructureKeys.IGLOO);
        this.builder(JAAVAATags.Structures.JUNGLE_TEMPLES)
                .add(StructureKeys.JUNGLE_PYRAMID);
        this.builder(JAAVAATags.Structures.NETHER_FORTRESSES)
                .add(StructureKeys.FORTRESS);
        this.builder(JAAVAATags.Structures.NETHER_FOSSILS)
                .add(StructureKeys.NETHER_FOSSIL);
        this.builder(JAAVAATags.Structures.OCEAN_MONUMENTS)
                .add(StructureKeys.MONUMENT);
        this.builder(JAAVAATags.Structures.PILLAGER_OUTPOSTS)
                .add(StructureKeys.PILLAGER_OUTPOST);
        this.builder(JAAVAATags.Structures.RUINS)
                .add(StructureKeys.TRAIL_RUINS)
                .add(StructureKeys.OCEAN_RUIN_COLD)
                .add(StructureKeys.OCEAN_RUIN_WARM);
        this.builder(JAAVAATags.Structures.STRONGHOLDS)
                .add(StructureKeys.STRONGHOLD);
        this.builder(JAAVAATags.Structures.WITCH_HUTS)
                .add(StructureKeys.SWAMP_HUT);
        this.builder(JAAVAATags.Structures.TRIAL_CHAMBERS)
                .add(StructureKeys.TRIAL_CHAMBERS);
        this.builder(JAAVAATags.Structures.WOODLAND_MANSIONS)
                .add(StructureKeys.MANSION);
    }
    private static RegistryKey<RegistryKey<Structure>> getStructureKey(CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, RegistryKey<Structure> structure) {
        try {
            var registryKeys = registriesFuture.get().streamAllRegistryKeys().toList();
            for (var key : registryKeys) {
                if (key.isOf(RegistryKeys.STRUCTURE)) {
                    JAAVAA.log(key.getValue().toString() + " | " + structure.toString(), 'e');
                    if (key.getValue().toString().equals(structure.toString())) {
                        //return (RegistryKey<Structure>) key;
                    }
                }
            }
            return null;
        } catch (Exception ignored) {
            return null;
        }
    }
}
