package net.gordyjack.jaavaa.potion;

import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.potion.custom.*;
import net.minecraft.entity.effect.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;

import java.util.*;

public class JAAVAAStatusEffects {
    public static final List<RegistryEntry<StatusEffect>> EFFECTS = new ArrayList<>();
    public static final RegistryEntry<StatusEffect> IMPENDING_DOOM =
            register("impending_doom", new ImpendingDoomEffect());

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect effect) {
        RegistryEntry<StatusEffect> statusEffectReference =
                Registry.registerReference(Registries.STATUS_EFFECT, JAAVAA.id(name), effect);
        EFFECTS.add(statusEffectReference);
        return statusEffectReference;
    }
    public static void init() {
        JAAVAA.log("Initializing JAAVAA status effects");
    }
}
