package net.cozystudios.squish.sound;

import net.cozystudios.squish.Squish;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SquishSounds {
    public static final SoundEvent SUGAR_POP =
            register("sugar_pop");

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Squish.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void register() {
    }
}