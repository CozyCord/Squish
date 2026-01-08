package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyCatEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Identifier;

public class BabyAwareCatEntityRenderer extends CatEntityRenderer {

    private final CatEntityModel<CatEntity> adultModel;
    private final BabyCatEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareCatEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyCatEntityModel(ctx.getPart(SquishModelLayers.BABY_CAT));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.2f, this.adultShadow * 0.55f);

        this.features.removeIf(f -> f.getClass().getName().toLowerCase().contains("collar"));
        this.addFeature(new BabyAwareCatCollarFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(CatEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);

        String base = BabyTextureUtil.baseName(super.getTexture(entity));

        return switch (base) {
            case "black" -> BabyTextureUtil.squishBaby("cat_black_baby.png");
            case "british_shorthair" -> BabyTextureUtil.squishBaby("cat_british_shorthair_baby.png");
            case "calico" -> BabyTextureUtil.squishBaby("cat_calico_baby.png");
            case "persian" -> BabyTextureUtil.squishBaby("cat_persian_baby.png");
            case "ragdoll" -> BabyTextureUtil.squishBaby("cat_ragdoll_baby.png");
            case "red" -> BabyTextureUtil.squishBaby("cat_red_baby.png");
            case "siamese" -> BabyTextureUtil.squishBaby("cat_siamese_baby.png");
            case "tabby" -> BabyTextureUtil.squishBaby("cat_tabby_baby.png");
            case "jellie" -> BabyTextureUtil.squishBaby("cat_jellie_baby.png");
            case "white" -> BabyTextureUtil.squishBaby("cat_white_baby.png");
            default -> BabyTextureUtil.squishBaby("cat_tabby_baby.png");
        };
    }

    @Override
    public void render(CatEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {

        if (entity.isBaby()) {
            this.model = this.babyModel;
            this.shadowRadius = this.babyShadow;
        } else {
            this.model = this.adultModel;
            this.shadowRadius = this.adultShadow;
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}