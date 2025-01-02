package net.gordyjack.jaavaa.potion;

import net.gordyjack.jaavaa.*;
import net.minecraft.entity.effect.*;
import net.minecraft.potion.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;

import java.util.*;

public class JAAVAAPotions {
    public static final List<RegistryEntry<Potion>> POTION_ENTRIES = new ArrayList<>();
    private static final int ETERNAL = StatusEffectInstance.INFINITE;

    public static final RegistryEntry<Potion> GLOWING_1_POTION = registerPotion("glowing_1",
            new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, minutes(5.0f), 0)));
    public static final RegistryEntry<Potion> GLOWING_1_POTION_LONG = registerPotion("glowing_1_long",
            new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, minutes(10.0f), 0)));
    public static final RegistryEntry<Potion> ETERNAL_GLOWING_POTION = registerPotion("eternal_glowing",
            new Potion("eternal_glowing", new StatusEffectInstance(StatusEffects.GLOWING, ETERNAL, 0, false, false, false)));

    private static final int HASTE_SHORT = minutes(10.0f), HASTE_LONG = minutes(30.0f);
    public static final RegistryEntry<Potion> HASTE_1_POTION = registerPotion("haste_1",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_SHORT, 0)));
    public static final RegistryEntry<Potion> HASTE_1_POTION_LONG = registerPotion("haste_1_long",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_LONG, 0)));
    public static final RegistryEntry<Potion> HASTE_2_POTION = registerPotion("haste_2",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_SHORT, 1)));
    public static final RegistryEntry<Potion> HASTE_2_POTION_LONG = registerPotion("haste_2_long",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_LONG, 1)));
    public static final RegistryEntry<Potion> HASTE_3_POTION = registerPotion("haste_3",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_SHORT, 2)));
    public static final RegistryEntry<Potion> HASTE_3_POTION_LONG = registerPotion("haste_3_long",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_LONG, 2)));
    public static final RegistryEntry<Potion> HASTE_4_POTION = registerPotion("haste_4",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_SHORT, 3)));
    public static final RegistryEntry<Potion> HASTE_4_POTION_LONG = registerPotion("haste_4_long",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_LONG, 3)));
    public static final RegistryEntry<Potion> HASTE_5_POTION = registerPotion("haste_5",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_SHORT, 4)));
    public static final RegistryEntry<Potion> HASTE_5_POTION_LONG = registerPotion("haste_5_long",
            new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, HASTE_LONG, 4)));
    public static final RegistryEntry<Potion> ETERNAL_HASTE_POTION = registerPotion("eternal_haste",
            new Potion("eternal_haste", new StatusEffectInstance(StatusEffects.HASTE, ETERNAL, 1, false, false, false)));

    private static final int MINING_FATIGUE_SHORT = minutes(1.5f), MINING_FATIGUE_LONG = minutes(4.0f);
    public static final RegistryEntry<Potion> MINING_FATIGUE_1_POTION = registerPotion("mining_fatigue_1",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_SHORT, 0)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_1_POTION_LONG = registerPotion("mining_fatigue_1_long",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_LONG, 0)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_2_POTION = registerPotion("mining_fatigue_2",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_SHORT, 1)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_2_POTION_LONG = registerPotion("mining_fatigue_2_long",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_LONG, 1)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_3_POTION = registerPotion("mining_fatigue_3",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_SHORT, 2)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_3_POTION_LONG = registerPotion("mining_fatigue_3_long",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_LONG, 2)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_4_POTION = registerPotion("mining_fatigue_4",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_SHORT, 3)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_4_POTION_LONG = registerPotion("mining_fatigue_4_long",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_LONG, 3)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_5_POTION = registerPotion("mining_fatigue_5",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_SHORT, 4)));
    public static final RegistryEntry<Potion> MINING_FATIGUE_5_POTION_LONG = registerPotion("mining_fatigue_5_long",
            new Potion("mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, MINING_FATIGUE_LONG, 4)));
    public static final RegistryEntry<Potion> ETERNAL_MINING_FATIGUE_POTION = registerPotion("eternal_mining_fatigue",
            new Potion("eternal_mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, ETERNAL, 4, false, false, false)));

    private static final int RESISTANCE_SHORT = minutes(3.0f), RESISTANCE_LONG = minutes(8.0f);
    public static final RegistryEntry<Potion> RESISTANCE_1_POTION = registerPotion("resistance_1",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_SHORT, 0)));
    public static final RegistryEntry<Potion> RESISTANCE_1_POTION_LONG = registerPotion("resistance_1_long",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_LONG, 0)));
    public static final RegistryEntry<Potion> RESISTANCE_2_POTION = registerPotion("resistance_2",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_SHORT, 1)));
    public static final RegistryEntry<Potion> RESISTANCE_2_POTION_LONG = registerPotion("resistance_2_long",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_LONG, 1)));
    public static final RegistryEntry<Potion> RESISTANCE_3_POTION = registerPotion("resistance_3",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_SHORT, 2)));
    public static final RegistryEntry<Potion> RESISTANCE_3_POTION_LONG = registerPotion("resistance_3_long",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_LONG, 2)));
    public static final RegistryEntry<Potion> RESISTANCE_4_POTION = registerPotion("resistance_4",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_SHORT, 3)));
    public static final RegistryEntry<Potion> RESISTANCE_4_POTION_LONG = registerPotion("resistance_4_long",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_LONG, 3)));
    public static final RegistryEntry<Potion> RESISTANCE_5_POTION = registerPotion("resistance_5",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_SHORT, 4)));
    public static final RegistryEntry<Potion> RESISTANCE_5_POTION_LONG = registerPotion("resistance_5_long",
            new Potion("resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, RESISTANCE_LONG, 4)));
    public static final RegistryEntry<Potion> ETERNAL_RESISTANCE_POTION = registerPotion("eternal_resistance",
            new Potion("eternal_resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, ETERNAL, 4, false, false, false)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        var potionEntry = Registry.registerReference(Registries.POTION, JAAVAA.id(name), potion);
        POTION_ENTRIES.add(potionEntry);
        return potionEntry;
    }
    private static int minutes(float minutes) {
        return Math.round(minutes * 60 * 20);
    }
    public static void init() {
        JAAVAA.log("Initializing Potions");
    }
}
