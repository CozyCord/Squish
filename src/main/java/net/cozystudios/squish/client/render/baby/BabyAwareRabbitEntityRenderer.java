package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyRabbitEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.Identifier;

public class BabyAwareRabbitEntityRenderer extends RabbitEntityRenderer {

    private final RabbitEntityModel<RabbitEntity> adultModel;
    private final BabyRabbitEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareRabbitEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyRabbitEntityModel(ctx.getPart(SquishModelLayers.BABY_RABBIT));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.15f, this.adultShadow * 0.5f);
    }

    @Override
    public Identifier getTexture(RabbitEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);

        String adultBase = BabyTextureUtil.baseName(super.getTexture(entity));

        return switch (adultBase) {
            case "black" -> BabyTextureUtil.squishBaby("rabbit_black_baby.png");
            case "brown" -> BabyTextureUtil.squishBaby("rabbit_brown_baby.png");
            case "caerbannog" -> BabyTextureUtil.squishBaby("rabbit_caerbannog_baby.png");
            case "gold" -> BabyTextureUtil.squishBaby("rabbit_gold_baby.png");
            case "salt" -> BabyTextureUtil.squishBaby("rabbit_salt_baby.png");
            case "toast" -> BabyTextureUtil.squishBaby("rabbit_toast_baby.png");
            case "white" -> BabyTextureUtil.squishBaby("rabbit_white_baby.png");
            case "white_splotched" -> BabyTextureUtil.squishBaby("rabbit_white_splotched_baby.png");
            default -> BabyTextureUtil.squishBaby("rabbit_brown_baby.png");
        };
    }

    @Override
    public void render(RabbitEntity entity, float yaw, float tickDelta, MatrixStack matrices,
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