package net.gordyjack.jaavaa.data.tag;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.gordyjack.jaavaa.data.*;
import net.minecraft.entity.*;
import net.minecraft.registry.*;

import java.util.concurrent.*;

public class JAAVAAEntityTypeTagProvider extends FabricTagProvider.FabricValueLookupTagProvider<EntityType<?>> {
    public JAAVAAEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENTITY_TYPE, registriesFuture, entityType -> entityType.getRegistryEntry().registryKey());
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(JAAVAATags.Entity.QUICKSAND_WALKABLE_MOBS).add(
                        EntityType.ALLAY,
                        EntityType.ARMADILLO,
                        EntityType.CAMEL,
                        EntityType.CAVE_SPIDER,
                        EntityType.SPIDER,
                        EntityType.HUSK
                );
        valueLookupBuilder(JAAVAATags.Entity.CAPTURABLE_MOBS_WOOD) //Small Peaceful Mobs
                .add(
                        EntityType.ALLAY,
                        EntityType.AXOLOTL,
                        EntityType.BAT,
                        EntityType.BEE,
                        EntityType.CAT,
                        EntityType.CHICKEN,
                        EntityType.COD,
                        EntityType.FOX,
                        EntityType.FROG,
                        EntityType.OCELOT,
                        EntityType.PARROT,
                        EntityType.PUFFERFISH,
                        EntityType.RABBIT,
                        EntityType.SALMON,
                        EntityType.TADPOLE,
                        EntityType.TROPICAL_FISH,
                        EntityType.TURTLE,
                        EntityType.WOLF
                );
        valueLookupBuilder(JAAVAATags.Entity.CAPTURABLE_MOBS_STONE) //Basic Peaceful Mobs
                .addOptionalTag(JAAVAATags.Entity.CAPTURABLE_MOBS_WOOD)
                .add(
                        EntityType.CAMEL,
                        EntityType.COW,
                        EntityType.DOLPHIN,
                        EntityType.DONKEY,
                        EntityType.GLOW_SQUID,
                        EntityType.GOAT,
                        EntityType.HORSE,
                        EntityType.LLAMA,
                        EntityType.MOOSHROOM,
                        EntityType.MULE,
                        EntityType.PANDA,
                        EntityType.PIG,
                        EntityType.POLAR_BEAR,
                        EntityType.SHEEP,
                        EntityType.SNIFFER,
                        EntityType.SNOW_GOLEM,
                        EntityType.SQUID,
                        EntityType.TRADER_LLAMA
                );
        valueLookupBuilder(JAAVAATags.Entity.CAPTURABLE_MOBS_IRON) //Basic Hostile Mobs + All (large) Peaceful Mobs
                .addOptionalTag(JAAVAATags.Entity.CAPTURABLE_MOBS_STONE)
                .add(
                        EntityType.CAVE_SPIDER,
                        EntityType.CREEPER,
                        EntityType.ENDERMAN,
                        EntityType.ENDERMITE,
                        EntityType.GUARDIAN,
                        EntityType.PHANTOM,
                        EntityType.PILLAGER,
                        EntityType.SILVERFISH,
                        EntityType.SKELETON,
                        EntityType.SKELETON_HORSE,
                        EntityType.SLIME,
                        EntityType.SPIDER,
                        EntityType.WITCH,
                        EntityType.ZOMBIE,
                        EntityType.ZOMBIE_HORSE,
                        EntityType.ZOMBIE_VILLAGER
                );
        valueLookupBuilder(JAAVAATags.Entity.CAPTURABLE_MOBS_DIAMOND) //All Hostile Mobs + Villagers
                .addOptionalTag(JAAVAATags.Entity.CAPTURABLE_MOBS_IRON)
                .add(
                        EntityType.BLAZE,
                        EntityType.BOGGED,
                        EntityType.BREEZE,
                        EntityType.DROWNED,
                        EntityType.EVOKER,
                        EntityType.GHAST,
                        EntityType.HOGLIN,
                        EntityType.HUSK,
                        EntityType.ILLUSIONER,
                        EntityType.IRON_GOLEM,
                        EntityType.MAGMA_CUBE,
                        EntityType.PIGLIN,
                        EntityType.PIGLIN_BRUTE,
                        EntityType.RAVAGER,
                        EntityType.SHULKER,
                        EntityType.STRAY,
                        EntityType.STRIDER,
                        EntityType.VEX,
                        EntityType.VILLAGER,
                        EntityType.VINDICATOR,
                        EntityType.WANDERING_TRADER,
                        EntityType.WITHER_SKELETON,
                        EntityType.ZOGLIN,
                        EntityType.ZOMBIFIED_PIGLIN
                );
        valueLookupBuilder(JAAVAATags.Entity.CAPTURABLE_MOBS_NETHERITE) //All Mobs including Bosses
                .addOptionalTag(JAAVAATags.Entity.CAPTURABLE_MOBS_DIAMOND)
                .add(
                        EntityType.CREAKING,
                        EntityType.ELDER_GUARDIAN,
                        EntityType.ENDER_DRAGON,
                        EntityType.GIANT,
                        EntityType.WARDEN,
                        EntityType.WITHER
                );
    }
}
