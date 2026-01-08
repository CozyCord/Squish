package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyCowEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.Identifier;

public class BabyAwareCowEntityRenderer extends CowEntityRenderer {

    private final CowEntityModel<CowEntity> adultModel;
    private final BabyCowEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareCowEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyCowEntityModel(ctx.getPart(SquishModelLayers.BABY_COW));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.25f, this.adultShadow * 0.5f);
    }

    @Override
    public Identifier getTexture(CowEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);

        return switch (BabyTextureUtil.biomeBand(entity)) {
            case COLD -> BabyTextureUtil.squishBaby("cow_cold_baby.png");
            case WARM -> BabyTextureUtil.squishBaby("cow_warm_baby.png");
            case TEMPERATE -> BabyTextureUtil.squishBaby("cow_temperate_baby.png");
        };
    }

    @Override
    public void render(CowEntity cow, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {

        if (cow.isBaby()) {
            this.model = this.babyModel;
            this.shadowRadius = this.babyShadow;
        } else {
            this.model = this.adultModel;
            this.shadowRadius = this.adultShadow;
        }

        super.render(cow, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}