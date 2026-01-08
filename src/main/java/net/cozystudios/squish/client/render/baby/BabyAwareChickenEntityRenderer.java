package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyChickenEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;

public class BabyAwareChickenEntityRenderer extends ChickenEntityRenderer {

    private final ChickenEntityModel<ChickenEntity> adultModel;
    private final BabyChickenEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareChickenEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyChickenEntityModel(ctx.getPart(SquishModelLayers.BABY_CHICKEN));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.15f, this.adultShadow * 0.6f);
    }

    @Override
    public Identifier getTexture(ChickenEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);

        return switch (BabyTextureUtil.biomeBand(entity)) {
            case COLD -> BabyTextureUtil.squishBaby("chicken_cold_baby.png");
            case WARM -> BabyTextureUtil.squishBaby("chicken_warm_baby.png");
            case TEMPERATE -> BabyTextureUtil.squishBaby("chicken_temperate_baby.png");
        };
    }

    @Override
    public void render(ChickenEntity entity, float yaw, float tickDelta, MatrixStack matrices,
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