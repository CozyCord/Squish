package net.cozystudios.squish.client.model;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.baby.*;
import net.cozystudios.squish.util.SquishId;
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
    public static final EntityModelLayer BABY_CREEPER = layer("baby_creeper");
    public static final EntityModelLayer BABY_IRON_GOLEM = layer("baby_iron_golem");
    public static final EntityModelLayer BABY_ENDERMAN = layer("baby_enderman");
    public static final EntityModelLayer BABY_AXOLOTL = layer("baby_axolotl");
    public static final EntityModelLayer BABY_DOLPHIN = layer("baby_dolphin");
    public static final EntityModelLayer BABY_HORSE = layer("baby_horse");
    public static final EntityModelLayer BABY_DONKEY = layer("baby_donkey");
    public static final EntityModelLayer BABY_MULE = layer("baby_mule");
    public static final EntityModelLayer BABY_SQUID = layer("baby_squid");
    public static final EntityModelLayer BABY_TURTLE = layer("baby_turtle");
    public static final EntityModelLayer BABY_SKELETON = layer("baby_skeleton");

    private static boolean REGISTERED = false;

    private SquishModelLayers() {}

    private static EntityModelLayer layer(String name) {
        return new EntityModelLayer(SquishId.of(Squish.MOD_ID, name), "main");
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
        EntityModelLayerRegistry.registerModelLayer(BABY_CREEPER, BabyCreeperModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_IRON_GOLEM, BabyIronGolemModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_ENDERMAN, BabyEndermanModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_AXOLOTL, BabyAxolotlModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_DOLPHIN, BabyDolphinModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_HORSE, BabyHorseModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_DONKEY, BabyDonkeyModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_MULE, BabyMuleModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_SQUID, BabySquidModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_TURTLE, BabyTurtleModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BABY_SKELETON, BabySkeletonModel::getTexturedModelData);
    }
}
