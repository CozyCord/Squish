package net.cozystudios.squish.client.model;

import net.cozystudios.squish.Squish;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public final class SquishModelLayers {
    private SquishModelLayers() {}

    public static final EntityModelLayer BABY_COW =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_cow"), "main");

    public static final EntityModelLayer BABY_CHICKEN =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_chicken"), "main");

    public static final EntityModelLayer BABY_PIG =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_pig"), "main");

    public static final EntityModelLayer BABY_RABBIT =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_rabbit"), "main");

    public static final EntityModelLayer BABY_WOLF =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_wolf"), "main");

    public static final EntityModelLayer BABY_CAT =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_cat"), "main");

    public static final EntityModelLayer BABY_OCELOT =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_ocelot"), "main");

    public static final EntityModelLayer BABY_SHEEP =
            new EntityModelLayer(new Identifier(Squish.MOD_ID, "baby_sheep"), "main");

}