package net.cozystudios.squish.client.model;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.baby.BabyCatModel;
import net.cozystudios.squish.client.model.baby.BabyChickenModel;
import net.cozystudios.squish.client.model.baby.BabyCowModel;
import net.cozystudios.squish.client.model.baby.BabyPigModel;
import net.cozystudios.squish.client.model.baby.BabyRabbitModel;
import net.cozystudios.squish.client.model.baby.BabySheepModel;
import net.cozystudios.squish.client.model.baby.BabyWolfModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public final class SquishModelLayers {

    public static final EntityModelLayer BABY_CAT     = layer("baby_cat");
    public static final EntityModelLayer BABY_CHICKEN = layer("baby_chicken");
    public static final EntityModelLayer BABY_COW     = layer("baby_cow");
    public static final EntityModelLayer BABY_PIG     = layer("baby_pig");
    public static final EntityModelLayer BABY_RABBIT  = layer("baby_rabbit");
    public static final EntityModelLayer BABY_SHEEP   = layer("baby_sheep");
    public static final EntityModelLayer BABY_WOLF    = layer("baby_wolf");

    private static boolean REGISTERED = false;

    private SquishModelLayers() {}

    private static EntityModelLayer layer(String name) {
        return new EntityModelLayer(new Identifier(Squish.MOD_ID, name), "main");
    }

    public static void register() {
        if (REGISTERED) return;
        REGISTERED = true;

        EntityModelLayerRegistry.registerModelLayer(BABY_CAT, BabyCatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_CHICKEN, BabyChickenModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_COW, BabyCowModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_PIG, BabyPigModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_RABBIT, BabyRabbitModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_SHEEP, BabySheepModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_WOLF, BabyWolfModel::getTexturedModelData);
    }
}