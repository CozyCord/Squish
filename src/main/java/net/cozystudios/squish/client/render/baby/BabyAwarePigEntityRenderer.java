package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyPigEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.Identifier;

public class BabyAwarePigEntityRenderer extends PigEntityRenderer {

    private final PigEntityModel<PigEntity> adultModel;
    private final BabyPigEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwarePigEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyPigEntityModel(ctx.getPart(SquishModelLayers.BABY_PIG));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.2f, this.adultShadow * 0.5f);
    }

    @Override
    public Identifier getTexture(PigEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);

        return switch (BabyTextureUtil.biomeBand(entity)) {
            case COLD -> BabyTextureUtil.squishBaby("pig_cold_baby.png");
            case WARM -> BabyTextureUtil.squishBaby("pig_warm_baby.png");
            case TEMPERATE -> BabyTextureUtil.squishBaby("pig_temperate_baby.png");
        };
    }

    @Override
    public void render(PigEntity entity, float yaw, float tickDelta, MatrixStack matrices,
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