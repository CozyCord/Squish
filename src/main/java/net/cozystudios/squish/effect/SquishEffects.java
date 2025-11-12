package net.cozystudios.squish.effect;

import net.cozystudios.squish.Squish;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SquishEffects {
    public static final StatusEffect SUGAR_RUSH = Registry.register(
            Registries.STATUS_EFFECT,
            new Identifier(Squish.MOD_ID, "sugar_rush"),
            new SugarRushStatusEffect()
    );

    public static void register() {
    }
}