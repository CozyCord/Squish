package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabySheepEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;

public class BabyAwareSheepEntityRenderer extends SheepEntityRenderer {

    private final SheepEntityModel<SheepEntity> adultModel;
    private final BabySheepEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareSheepEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabySheepEntityModel(ctx.getPart(SquishModelLayers.BABY_SHEEP));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.2f, this.adultShadow * 0.5f);

        this.features.removeIf(f -> f.getClass().getName().toLowerCase().contains("wool"));
        this.addFeature(new BabyAwareSheepWoolFeatureRenderer(this, ctx));
    }

    @Override
    public Identifier getTexture(SheepEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);
        return BabyTextureUtil.squishBaby("sheep_baby.png");
    }

    @Override
    public void render(SheepEntity entity, float yaw, float tickDelta, MatrixStack matrices,
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