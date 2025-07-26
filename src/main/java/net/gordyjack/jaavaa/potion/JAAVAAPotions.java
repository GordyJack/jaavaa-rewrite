package net.gordyjack.jaavaa.potion;

import net.gordyjack.jaavaa.*;
import net.minecraft.entity.effect.*;
import net.minecraft.potion.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;

import java.util.*;

import static net.gordyjack.jaavaa.utils.JAAVAAUtils.minutes;
import static net.gordyjack.jaavaa.utils.JAAVAAUtils.seconds;

public final class JAAVAAPotions {
    public static final List<RegistryEntry<Potion>> POTION_ENTRIES = new ArrayList<>();
    private static final int INFINITY = StatusEffectInstance.INFINITE;

    // Upgraded Vanilla Potions
    // Instant Damage Potion
    public static final RegistryEntry<Potion> HARMING_3_POTION = registerPotion("harming_3",
            new Potion("harming", new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 2)));
    public static final RegistryEntry<Potion> HARMING_4_POTION = registerPotion("harming_4",
            new Potion("harming", new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 3)));
    public static final RegistryEntry<Potion> HARMING_5_POTION = registerPotion("harming_5",
            new Potion("harming", new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 4)));
    // Instant Health Potion
    public static final RegistryEntry<Potion> HEALING_3_POTION = registerPotion("healing_3",
            new Potion("healing", new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2)));
    public static final RegistryEntry<Potion> HEALING_4_POTION = registerPotion("healing_4",
            new Potion("healing", new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 3)));
    public static final RegistryEntry<Potion> HEALING_5_POTION = registerPotion("healing_5",
            new Potion("healing", new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 4)));
    // Jump Boost Potion
    private static final int LEAPING_SHORT = minutes(3), LEAPING_LONG = minutes(8);
    public static final RegistryEntry<Potion> LEAPING_2_POTION_LONG = registerPotion("leaping_2_long",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_LONG, 1)));
    public static final RegistryEntry<Potion> LEAPING_3_POTION = registerPotion("leaping_3",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_SHORT, 2)));
    public static final RegistryEntry<Potion> LEAPING_3_POTION_LONG = registerPotion("leaping_3_long",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_LONG, 2)));
    public static final RegistryEntry<Potion> LEAPING_4_POTION = registerPotion("leaping_4",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_SHORT, 3)));
    public static final RegistryEntry<Potion> LEAPING_4_POTION_LONG = registerPotion("leaping_4_long",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_LONG, 3)));
    public static final RegistryEntry<Potion> LEAPING_5_POTION = registerPotion("leaping_5",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_SHORT, 4)));
    public static final RegistryEntry<Potion> LEAPING_5_POTION_LONG = registerPotion("leaping_5_long",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LEAPING_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_LEAPING_POTION = registerPotion("infinite_leaping",
            new Potion("infinite_leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_LEAPING_POTION = registerPotion("eternal_leaping",
            new Potion("eternal_leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, INFINITY, 5, false, false)));

    // Poison Potion
    private static final int POISON_SHORT = seconds(15), POISON_LONG = minutes(1);
    public static final RegistryEntry<Potion> POISON_2_POTION_LONG = registerPotion("poison_2_long",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_LONG, 1)));
    public static final RegistryEntry<Potion> POISON_3_POTION = registerPotion("poison_3",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_SHORT, 2)));
    public static final RegistryEntry<Potion> POISON_3_POTION_LONG = registerPotion("poison_3_long",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_LONG, 2)));
    public static final RegistryEntry<Potion> POISON_4_POTION = registerPotion("poison_4",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_SHORT, 3)));
    public static final RegistryEntry<Potion> POISON_4_POTION_LONG = registerPotion("poison_4_long",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_LONG, 3)));
    public static final RegistryEntry<Potion> POISON_5_POTION = registerPotion("poison_5",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_SHORT, 4)));
    public static final RegistryEntry<Potion> POISON_5_POTION_LONG = registerPotion("poison_5_long",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, POISON_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_POISON_POTION = registerPotion("infinite_poison",
            new Potion("infinite_poison", new StatusEffectInstance(StatusEffects.POISON, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_POISON_POTION = registerPotion("eternal_poison",
            new Potion("eternal_poison", new StatusEffectInstance(StatusEffects.POISON, INFINITY, 5, false, false)));

    // Regeneration Potion
    private static final int REGENERATION_SHORT = 900, REGENERATION_LONG = 1800;
    public static final RegistryEntry<Potion> REGENERATION_2_POTION_LONG = registerPotion("regeneration_2_long",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_LONG, 1)));
    public static final RegistryEntry<Potion> REGENERATION_3_POTION = registerPotion("regeneration_3",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_SHORT, 2)));
    public static final RegistryEntry<Potion> REGENERATION_3_POTION_LONG = registerPotion("regeneration_3_long",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_LONG, 2)));
    public static final RegistryEntry<Potion> REGENERATION_4_POTION = registerPotion("regeneration_4",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_SHORT, 3)));
    public static final RegistryEntry<Potion> REGENERATION_4_POTION_LONG = registerPotion("regeneration_4_long",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_LONG, 3)));
    public static final RegistryEntry<Potion> REGENERATION_5_POTION = registerPotion("regeneration_5",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_SHORT, 4)));
    public static final RegistryEntry<Potion> REGENERATION_5_POTION_LONG = registerPotion("regeneration_5_long",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, REGENERATION_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_REGENERATION_POTION = registerPotion("infinite_regeneration",
            new Potion("infinite_regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_REGENERATION_POTION = registerPotion("eternal_regeneration",
            new Potion("eternal_regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, INFINITY, 5, false, false)));
    // Slowness Potion
    private static final int SLOWNESS_SHORT = 400, SLOWNESS_LONG = 1200;
    public static final RegistryEntry<Potion> SLOWNESS_2_POTION = registerPotion("slowness_2",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_SHORT, 1)));
    public static final RegistryEntry<Potion> SLOWNESS_2_POTION_LONG = registerPotion("slowness_2_long",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_LONG, 1)));
    public static final RegistryEntry<Potion> SLOWNESS_3_POTION = registerPotion("slowness_3",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_SHORT, 2)));
    public static final RegistryEntry<Potion> SLOWNESS_3_POTION_LONG = registerPotion("slowness_3_long",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_LONG, 2)));
    public static final RegistryEntry<Potion> SLOWNESS_4_POTION_LONG = registerPotion("slowness_4_long",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_LONG, 3)));
    public static final RegistryEntry<Potion> SLOWNESS_5_POTION = registerPotion("slowness_5",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_SHORT, 4)));
    public static final RegistryEntry<Potion> SLOWNESS_5_POTION_LONG = registerPotion("slowness_5_long",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_SLOWNESS_POTION = registerPotion("infinite_slowness",
            new Potion("infinite_slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_SLOWNESS_POTION = registerPotion("eternal_slowness",
            new Potion("eternal_slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, INFINITY, 5, false, false)));

    // Speed Potion
    private static final int SWIFTNESS_SHORT = 1800, SWIFTNESS_LONG = 4800;
    public static final RegistryEntry<Potion> SWIFTNESS_2_POTION_LONG = registerPotion("swiftness_2_long",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_LONG, 1)));
    public static final RegistryEntry<Potion> SWIFTNESS_3_POTION = registerPotion("swiftness_3",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_SHORT, 2)));
    public static final RegistryEntry<Potion> SWIFTNESS_3_POTION_LONG = registerPotion("swiftness_3_long",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_LONG, 2)));
    public static final RegistryEntry<Potion> SWIFTNESS_4_POTION = registerPotion("swiftness_4",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_SHORT, 3)));
    public static final RegistryEntry<Potion> SWIFTNESS_4_POTION_LONG = registerPotion("swiftness_4_long",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_LONG, 3)));
    public static final RegistryEntry<Potion> SWIFTNESS_5_POTION = registerPotion("swiftness_5",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_SHORT, 4)));
    public static final RegistryEntry<Potion> SWIFTNESS_5_POTION_LONG = registerPotion("swiftness_5_long",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, SWIFTNESS_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_SWIFTNESS_POTION = registerPotion("infinite_swiftness",
            new Potion("infinite_swiftness", new StatusEffectInstance(StatusEffects.SPEED, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_SWIFTNESS_POTION = registerPotion("eternal_swiftness",
            new Potion("eternal_swiftness", new StatusEffectInstance(StatusEffects.SPEED, INFINITY, 5, false, false)));
    // Strength Potion
    private static final int STRENGTH_SHORT = 1800, STRENGTH_LONG = 4800;
    public static final RegistryEntry<Potion> STRENGTH_2_POTION_LONG = registerPotion("strength_2_long",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_LONG, 1)));
    public static final RegistryEntry<Potion> STRENGTH_3_POTION = registerPotion("strength_3",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_SHORT, 2)));
    public static final RegistryEntry<Potion> STRENGTH_3_POTION_LONG = registerPotion("strength_3_long",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_LONG, 2)));
    public static final RegistryEntry<Potion> STRENGTH_4_POTION = registerPotion("strength_4",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_SHORT, 3)));
    public static final RegistryEntry<Potion> STRENGTH_4_POTION_LONG = registerPotion("strength_4_long",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_LONG, 3)));
    public static final RegistryEntry<Potion> STRENGTH_5_POTION = registerPotion("strength_5",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_SHORT, 4)));
    public static final RegistryEntry<Potion> STRENGTH_5_POTION_LONG = registerPotion("strength_5_long",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_STRENGTH_POTION = registerPotion("infinite_strength",
            new Potion("infinite_strength", new StatusEffectInstance(StatusEffects.STRENGTH, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_STRENGTH_POTION = registerPotion("eternal_strength",
            new Potion("eternal_strength", new StatusEffectInstance(StatusEffects.STRENGTH, INFINITY, 5, false, false)));

    // Weakness Potion
    private static final int WEAKNESS_SHORT = 900, WEAKNESS_LONG = 2400;
    public static final RegistryEntry<Potion> WEAKNESS_2_POTION = registerPotion("weakness_2",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_SHORT, 1)));
    public static final RegistryEntry<Potion> WEAKNESS_2_POTION_LONG = registerPotion("weakness_2_long",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_LONG, 1)));
    public static final RegistryEntry<Potion> WEAKNESS_3_POTION = registerPotion("weakness_3",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_SHORT, 2)));
    public static final RegistryEntry<Potion> WEAKNESS_3_POTION_LONG = registerPotion("weakness_3_long",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_LONG, 2)));
    public static final RegistryEntry<Potion> WEAKNESS_4_POTION = registerPotion("weakness_4",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_SHORT, 3)));
    public static final RegistryEntry<Potion> WEAKNESS_4_POTION_LONG = registerPotion("weakness_4_long",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_LONG, 3)));
    public static final RegistryEntry<Potion> WEAKNESS_5_POTION = registerPotion("weakness_5",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_SHORT, 4)));
    public static final RegistryEntry<Potion> WEAKNESS_5_POTION_LONG = registerPotion("weakness_5_long",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, WEAKNESS_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_WEAKNESS_POTION = registerPotion("infinite_weakness",
            new Potion("infinite_weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_WEAKNESS_POTION = registerPotion("eternal_weakness",
            new Potion("eternal_weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, INFINITY, 5, false, false)));

    // Mod Potions
    // Positive Effects
    // Absorbtion Potion
    private static final int ABSORPTION_SHORT = minutes(3), ABSORPTION_LONG = minutes(8);
    public static final RegistryEntry<Potion> ABSORPTION_1_POTION = registerPotion("absorption_1",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_SHORT, 0)));
    public static final RegistryEntry<Potion> ABSORPTION_1_POTION_LONG = registerPotion("absorption_1_long",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_LONG, 0)));
    public static final RegistryEntry<Potion> ABSORPTION_2_POTION = registerPotion("absorption_2",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_SHORT, 1)));
    public static final RegistryEntry<Potion> ABSORPTION_2_POTION_LONG = registerPotion("absorption_2_long",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_LONG, 1)));
    public static final RegistryEntry<Potion> ABSORPTION_3_POTION = registerPotion("absorption_3",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_SHORT, 2)));
    public static final RegistryEntry<Potion> ABSORPTION_3_POTION_LONG = registerPotion("absorption_3_long",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_LONG, 2)));
    public static final RegistryEntry<Potion> ABSORPTION_4_POTION = registerPotion("absorption_4",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_SHORT, 3)));
    public static final RegistryEntry<Potion> ABSORPTION_4_POTION_LONG = registerPotion("absorption_4_long",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_LONG, 3)));
    public static final RegistryEntry<Potion> ABSORPTION_5_POTION = registerPotion("absorption_5",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_SHORT, 4)));
    public static final RegistryEntry<Potion> ABSORPTION_5_POTION_LONG = registerPotion("absorption_5_long",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_ABSORPTION_POTION = registerPotion("infinite_absorption",
            new Potion("infinite_absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_ABSORPTION_POTION = registerPotion("eternal_absorption",
            new Potion("eternal_absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, INFINITY, 5, false, false)));

    // Glowing Potion
    public static final RegistryEntry<Potion> GLOWING_1_POTION = registerPotion("glowing_1",
            new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, minutes(5), 0)));
    public static final RegistryEntry<Potion> GLOWING_1_POTION_LONG = registerPotion("glowing_1_long",
            new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, minutes(10), 0)));
    public static final RegistryEntry<Potion> INFINITE_GLOWING_POTION = registerPotion("infinite_glowing",
            new Potion("infinite_glowing", new StatusEffectInstance(StatusEffects.GLOWING, INFINITY, 0, false, false)));

    // Haste Potion
    private static final int HASTE_SHORT = minutes(10), HASTE_LONG = minutes(30);
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
    public static final RegistryEntry<Potion> INFINITE_HASTE_POTION = registerPotion("infinite_haste",
            new Potion("infinite_haste", new StatusEffectInstance(StatusEffects.HASTE, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_HASTE_POTION = registerPotion("eternal_haste",
            new Potion("eternal_haste", new StatusEffectInstance(StatusEffects.HASTE, INFINITY, 5, false, false)));

    // Health Boost Potion
    private static final int HEALTH_BOOST_SHORT = minutes(3), HEALTH_BOOST_LONG = minutes(8);
    public static final RegistryEntry<Potion> HEALTH_BOOST_1_POTION = registerPotion("health_boost_1",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_SHORT, 0)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_1_POTION_LONG = registerPotion("health_boost_1_long",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_LONG, 0)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_2_POTION = registerPotion("health_boost_2",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_SHORT, 1)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_2_POTION_LONG = registerPotion("health_boost_2_long",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_LONG, 1)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_3_POTION = registerPotion("health_boost_3",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_SHORT, 2)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_3_POTION_LONG = registerPotion("health_boost_3_long",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_LONG, 2)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_4_POTION = registerPotion("health_boost_4",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_SHORT, 3)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_4_POTION_LONG = registerPotion("health_boost_4_long",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_LONG, 3)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_5_POTION = registerPotion("health_boost_5",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_SHORT, 4)));
    public static final RegistryEntry<Potion> HEALTH_BOOST_5_POTION_LONG = registerPotion("health_boost_5_long",
            new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, HEALTH_BOOST_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_HEALTH_BOOST_POTION = registerPotion("infinite_health_boost",
            new Potion("infinite_health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_HEALTH_BOOST_POTION = registerPotion("eternal_health_boost",
            new Potion("eternal_health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, INFINITY, 5, false, false)));

    // Resistance Potion
    private static final int RESISTANCE_SHORT = minutes(3), RESISTANCE_LONG = minutes(8);
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
    public static final RegistryEntry<Potion> INFINITE_RESISTANCE_POTION = registerPotion("infinite_resistance",
            new Potion("infinite_resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_RESISTANCE_POTION = registerPotion("eternal_resistance",
            new Potion("eternal_resistance", new StatusEffectInstance(StatusEffects.RESISTANCE, INFINITY, 5, false, false)));

    // Saturation Potion
    // TODO: Might need to add a custom StatusEffect for Saturation to work properly.
    private static final int SATURATION_SHORT = minutes(10), SATURATION_LONG = minutes(30);
    public static final RegistryEntry<Potion> SATURATION_1_POTION = registerPotion("saturation_1",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_SHORT, 0)));
    public static final RegistryEntry<Potion> SATURATION_1_POTION_LONG = registerPotion("saturation_1_long",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_LONG, 0)));
    public static final RegistryEntry<Potion> SATURATION_2_POTION = registerPotion("saturation_2",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_SHORT, 1)));
    public static final RegistryEntry<Potion> SATURATION_2_POTION_LONG = registerPotion("saturation_2_long",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_LONG, 1)));
    public static final RegistryEntry<Potion> SATURATION_3_POTION = registerPotion("saturation_3",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_SHORT, 2)));
    public static final RegistryEntry<Potion> SATURATION_3_POTION_LONG = registerPotion("saturation_3_long",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_LONG, 2)));
    public static final RegistryEntry<Potion> SATURATION_4_POTION = registerPotion("saturation_4",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_SHORT, 3)));
    public static final RegistryEntry<Potion> SATURATION_4_POTION_LONG = registerPotion("saturation_4_long",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_LONG, 3)));
    public static final RegistryEntry<Potion> SATURATION_5_POTION = registerPotion("saturation_5",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_SHORT, 4)));
    public static final RegistryEntry<Potion> SATURATION_5_POTION_LONG = registerPotion("saturation_5_long",
            new Potion("saturation", new StatusEffectInstance(StatusEffects.SATURATION, SATURATION_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_SATURATION_POTION = registerPotion("infinite_saturation",
            new Potion("infinite_saturation", new StatusEffectInstance(StatusEffects.SATURATION, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_SATURATION_POTION = registerPotion("eternal_saturation",
            new Potion("eternal_saturation", new StatusEffectInstance(StatusEffects.SATURATION, INFINITY, 5, false, false)));

    // Negative Effects
    // Bad Luck Potion
    public static final RegistryEntry<Potion> BAD_LUCK_1_POTION = registerPotion("bad_luck_1",
            new Potion("bad_luck", new StatusEffectInstance(StatusEffects.UNLUCK, minutes(1.5), 0)));
    public static final RegistryEntry<Potion> BAD_LUCK_1_POTION_LONG = registerPotion("bad_luck_1_long",
            new Potion("bad_luck", new StatusEffectInstance(StatusEffects.UNLUCK, minutes(4), 0)));
    public static final RegistryEntry<Potion> INFINITE_BAD_LUCK_POTION = registerPotion("infinite_bad_luck",
            new Potion("infinite_bad_luck", new StatusEffectInstance(StatusEffects.UNLUCK, INFINITY, 0, false, false)));

    // Blindness Potion
    public static final RegistryEntry<Potion> BLINDNESS_1_POTION = registerPotion("blindness_1",
            new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, minutes(0.5), 0)));
    public static final RegistryEntry<Potion> BLINDNESS_1_POTION_LONG = registerPotion("blindness_1_long",
            new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, minutes(2), 0)));
    public static final RegistryEntry<Potion> INFINITE_BLINDNESS_POTION = registerPotion("infinite_blindness",
            new Potion("infinite_blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, INFINITY, 0, false, false)));

    // Darkness Potion
    public static final RegistryEntry<Potion> DARKNESS_1_POTION = registerPotion("darkness_1",
            new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, minutes(0.5), 0)));
    public static final RegistryEntry<Potion> DARKNESS_1_POTION_LONG = registerPotion("darkness_1_long",
            new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, minutes(2), 0)));
    public static final RegistryEntry<Potion> INFINITE_DARKNESS_POTION = registerPotion("infinite_darkness",
            new Potion("infinite_darkness", new StatusEffectInstance(StatusEffects.DARKNESS, INFINITY, 0, false, false)));

    // Fatal Poison Potion
    private static final int FATAL_POISON_SHORT = seconds(5), FATAL_POISON_LONG = seconds(15);
    public static final RegistryEntry<Potion> FATAL_POISON_1_POTION = registerPotion("fatal_poison_1",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_SHORT, 0)));
    public static final RegistryEntry<Potion> FATAL_POISON_1_POTION_LONG = registerPotion("fatal_poison_1_long",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_LONG, 0)));
    public static final RegistryEntry<Potion> FATAL_POISON_2_POTION = registerPotion("fatal_poison_2",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_SHORT, 1)));
    public static final RegistryEntry<Potion> FATAL_POISON_2_POTION_LONG = registerPotion("fatal_poison_2_long",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_LONG, 1)));
    public static final RegistryEntry<Potion> FATAL_POISON_3_POTION = registerPotion("fatal_poison_3",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_SHORT, 2)));
    public static final RegistryEntry<Potion> FATAL_POISON_3_POTION_LONG = registerPotion("fatal_poison_3_long",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_LONG, 2)));
    public static final RegistryEntry<Potion> FATAL_POISON_4_POTION = registerPotion("fatal_poison_4",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_SHORT, 3)));
    public static final RegistryEntry<Potion> FATAL_POISON_4_POTION_LONG = registerPotion("fatal_poison_4_long",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_LONG, 3)));
    public static final RegistryEntry<Potion> FATAL_POISON_5_POTION = registerPotion("fatal_poison_5",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_SHORT, 4)));
    public static final RegistryEntry<Potion> FATAL_POISON_5_POTION_LONG = registerPotion("fatal_poison_5_long",
            new Potion("fatal_poison", new StatusEffectInstance(StatusEffects.POISON, FATAL_POISON_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_FATAL_POISON_POTION = registerPotion("infinite_fatal_poison",
            new Potion("infinite_fatal_poison", new StatusEffectInstance(StatusEffects.POISON, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_FATAL_POISON_POTION = registerPotion("eternal_fatal_poison",
            new Potion("eternal_fatal_poison", new StatusEffectInstance(StatusEffects.POISON, INFINITY, 5, false, false)));

    // Hunger Potion
    private static final int HUNGER_SHORT = minutes(1.5), HUNGER_LONG = minutes(4);
    public static final RegistryEntry<Potion> HUNGER_1_POTION = registerPotion("hunger_1",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_SHORT, 0)));
    public static final RegistryEntry<Potion> HUNGER_1_POTION_LONG = registerPotion("hunger_1_long",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_LONG, 0)));
    public static final RegistryEntry<Potion> HUNGER_2_POTION = registerPotion("hunger_2",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_SHORT, 1)));
    public static final RegistryEntry<Potion> HUNGER_2_POTION_LONG = registerPotion("hunger_2_long",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_LONG, 1)));
    public static final RegistryEntry<Potion> HUNGER_3_POTION = registerPotion("hunger_3",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_SHORT, 2)));
    public static final RegistryEntry<Potion> HUNGER_3_POTION_LONG = registerPotion("hunger_3_long",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_LONG, 2)));
    public static final RegistryEntry<Potion> HUNGER_4_POTION = registerPotion("hunger_4",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_SHORT, 3)));
    public static final RegistryEntry<Potion> HUNGER_4_POTION_LONG = registerPotion("hunger_4_long",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_LONG, 3)));
    public static final RegistryEntry<Potion> HUNGER_5_POTION = registerPotion("hunger_5",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_SHORT, 4)));
    public static final RegistryEntry<Potion> HUNGER_5_POTION_LONG = registerPotion("hunger_5_long",
            new Potion("hunger", new StatusEffectInstance(StatusEffects.HUNGER, HUNGER_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_HUNGER_POTION = registerPotion("infinite_hunger",
            new Potion("infinite_hunger", new StatusEffectInstance(StatusEffects.HUNGER, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_HUNGER_POTION = registerPotion("eternal_hunger",
            new Potion("eternal_hunger", new StatusEffectInstance(StatusEffects.HUNGER, INFINITY, 5, false, false)));

    //Levitation Potion
    private static final int LEVITATION_SHORT = seconds(15), LEVITATION_LONG = minutes(1);
    public static final RegistryEntry<Potion> LEVITATION_1_POTION = registerPotion("levitation_1",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_SHORT, 0)));
    public static final RegistryEntry<Potion> LEVITATION_1_POTION_LONG = registerPotion("levitation_1_long",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_LONG, 0)));
    public static final RegistryEntry<Potion> LEVITATION_2_POTION = registerPotion("levitation_2",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_SHORT, 1)));
    public static final RegistryEntry<Potion> LEVITATION_2_POTION_LONG = registerPotion("levitation_2_long",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_LONG, 1)));
    public static final RegistryEntry<Potion> LEVITATION_3_POTION = registerPotion("levitation_3",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_SHORT, 2)));
    public static final RegistryEntry<Potion> LEVITATION_3_POTION_LONG = registerPotion("levitation_3_long",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_LONG, 2)));
    public static final RegistryEntry<Potion> LEVITATION_4_POTION = registerPotion("levitation_4",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_SHORT, 3)));
    public static final RegistryEntry<Potion> LEVITATION_4_POTION_LONG = registerPotion("levitation_4_long",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_LONG, 3)));
    public static final RegistryEntry<Potion> LEVITATION_5_POTION = registerPotion("levitation_5",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_SHORT, 4)));
    public static final RegistryEntry<Potion> LEVITATION_5_POTION_LONG = registerPotion("levitation_5_long",
            new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_LEVITATION_POTION = registerPotion("infinite_levitation",
            new Potion("infinite_levitation", new StatusEffectInstance(StatusEffects.LEVITATION, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_LEVITATION_POTION = registerPotion("eternal_levitation",
            new Potion("eternal_levitation", new StatusEffectInstance(StatusEffects.LEVITATION, INFINITY, 5, false, false)));

    // Mining Fatigue Potion
    private static final int MINING_FATIGUE_SHORT = minutes(1.5), MINING_FATIGUE_LONG = minutes(4);
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
    public static final RegistryEntry<Potion> INFINITE_MINING_FATIGUE_POTION = registerPotion("infinite_mining_fatigue",
            new Potion("infinite_mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_MINING_FATIGUE_POTION = registerPotion("eternal_mining_fatigue",
            new Potion("eternal_mining_fatigue", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, INFINITY, 5, false, false)));

    // Nausea Potion
    public static final RegistryEntry<Potion> NAUSEA_1_POTION = registerPotion("nausea_1",
            new Potion("nausea", new StatusEffectInstance(StatusEffects.NAUSEA, minutes(0.5), 0)));
    public static final RegistryEntry<Potion> NAUSEA_1_POTION_LONG = registerPotion("nausea_1_long",
            new Potion("nausea", new StatusEffectInstance(StatusEffects.NAUSEA, minutes(2), 0)));
    public static final RegistryEntry<Potion> INFINITE_NAUSEA_POTION = registerPotion("infinite_nausea",
            new Potion("infinite_nausea", new StatusEffectInstance(StatusEffects.NAUSEA, INFINITY, 0, false, false)));

    // Wither Potion
    private static final int WITHER_SHORT = seconds(15), WITHER_LONG = minutes(1);
    public static final RegistryEntry<Potion> WITHER_1_POTION = registerPotion("decay_1",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_SHORT, 0)));
    public static final RegistryEntry<Potion> WITHER_1_POTION_LONG = registerPotion("decay_1_long",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_LONG, 0)));
    public static final RegistryEntry<Potion> WITHER_2_POTION = registerPotion("decay_2",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_SHORT, 1)));
    public static final RegistryEntry<Potion> WITHER_2_POTION_LONG = registerPotion("decay_2_long",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_LONG, 1)));
    public static final RegistryEntry<Potion> WITHER_3_POTION = registerPotion("decay_3",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_SHORT, 2)));
    public static final RegistryEntry<Potion> WITHER_3_POTION_LONG = registerPotion("decay_3_long",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_LONG, 2)));
    public static final RegistryEntry<Potion> WITHER_4_POTION = registerPotion("decay_4",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_SHORT, 3)));
    public static final RegistryEntry<Potion> WITHER_4_POTION_LONG = registerPotion("decay_4_long",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_LONG, 3)));
    public static final RegistryEntry<Potion> WITHER_5_POTION = registerPotion("decay_5",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_SHORT, 4)));
    public static final RegistryEntry<Potion> WITHER_5_POTION_LONG = registerPotion("decay_5_long",
            new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, WITHER_LONG, 4)));
    public static final RegistryEntry<Potion> INFINITE_WITHER_POTION = registerPotion("infinite_decay",
            new Potion("infinite_decay", new StatusEffectInstance(StatusEffects.WITHER, INFINITY, 0, false, false)));
    public static final RegistryEntry<Potion> ETERNAL_WITHER_POTION = registerPotion("eternal_decay",
            new Potion("eternal_decay", new StatusEffectInstance(StatusEffects.WITHER, INFINITY, 5, false, false)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        var potionEntry = Registry.registerReference(Registries.POTION, JAAVAA.id(name), potion);
        POTION_ENTRIES.add(potionEntry);
        return potionEntry;
    }
    public static void init() {
        JAAVAA.log("Initializing Potions");
    }
}