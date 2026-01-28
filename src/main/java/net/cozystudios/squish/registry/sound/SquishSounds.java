package net.cozystudios.squish.registry.sound;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SquishSounds {
    public static final SoundEvent SUGAR_POP = register("sugar_pop");

    // Baby Cat sounds
    public static final SoundEvent BABY_CAT_AMBIENT = register("entity.baby_cat.ambient");
    public static final SoundEvent BABY_CAT_DEATH = register("entity.baby_cat.death");
    public static final SoundEvent BABY_CAT_HURT = register("entity.baby_cat.hurt");

    // Baby Chicken sounds
    public static final SoundEvent BABY_CHICKEN_AMBIENT = register("entity.baby_chicken.ambient");
    public static final SoundEvent BABY_CHICKEN_DEATH = register("entity.baby_chicken.death");
    public static final SoundEvent BABY_CHICKEN_HURT = register("entity.baby_chicken.hurt");

    // Baby Horse sounds
    public static final SoundEvent BABY_HORSE_AMBIENT = register("entity.baby_horse.ambient");
    public static final SoundEvent BABY_HORSE_ANGRY = register("entity.baby_horse.angry");
    public static final SoundEvent BABY_HORSE_DEATH = register("entity.baby_horse.death");
    public static final SoundEvent BABY_HORSE_EAT = register("entity.baby_horse.eat");
    public static final SoundEvent BABY_HORSE_HURT = register("entity.baby_horse.hurt");
    public static final SoundEvent BABY_HORSE_LAND = register("entity.baby_horse.land");
    public static final SoundEvent BABY_HORSE_STEP = register("entity.baby_horse.step");

    // Baby Pig sounds
    public static final SoundEvent BABY_PIG_AMBIENT = register("entity.baby_pig.ambient");
    public static final SoundEvent BABY_PIG_DEATH = register("entity.baby_pig.death");
    public static final SoundEvent BABY_PIG_HURT = register("entity.baby_pig.hurt");
    public static final SoundEvent BABY_PIG_STEP = register("entity.baby_pig.step");

    // Baby Turtle sounds
    public static final SoundEvent BABY_TURTLE_DEATH = register("entity.baby_turtle.death");
    public static final SoundEvent BABY_TURTLE_EGG_HATCHED = register("entity.baby_turtle.egg_hatched");
    public static final SoundEvent BABY_TURTLE_HURT = register("entity.baby_turtle.hurt");
    public static final SoundEvent BABY_TURTLE_SHAMBLE = register("entity.baby_turtle.shamble");

    // Baby Wolf sounds
    public static final SoundEvent BABY_WOLF_AMBIENT = register("entity.baby_wolf.ambient");
    public static final SoundEvent BABY_WOLF_ANGRY = register("entity.baby_wolf.angry");
    public static final SoundEvent BABY_WOLF_DEATH = register("entity.baby_wolf.death");
    public static final SoundEvent BABY_WOLF_HURT = register("entity.baby_wolf.hurt");
    public static final SoundEvent BABY_WOLF_PANT = register("entity.baby_wolf.pant");
    public static final SoundEvent BABY_WOLF_STEP = register("entity.baby_wolf.step");
    public static final SoundEvent BABY_WOLF_WHINE = register("entity.baby_wolf.whine");

    private static SoundEvent register(String id) {
        Identifier identifier = SquishId.of(Squish.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void register() {
    }
}
