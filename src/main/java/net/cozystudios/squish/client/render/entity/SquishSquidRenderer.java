package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabySquidModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SquidEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class SquishSquidRenderer extends EntityRenderer<SquidEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/squid_baby.png");

    private final SquidEntityRenderer<SquidEntity> adult;
    private final BabyRenderer baby;

    public SquishSquidRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new SquidEntityRenderer<>(ctx, new SquidEntityModel<>(ctx.getPart(EntityModelLayers.SQUID)));
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(SquidEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(SquidEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<SquidEntity, BabySquidModel<SquidEntity>> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabySquidModel<>(ctx.getPart(SquishModelLayers.BABY_SQUID)), 0.35f);
        }

        @Override
        public Identifier getTexture(SquidEntity entity) {
            return BABY_TEXTURE;
        }

        //? if >1.20.4 {
        @Override
        protected void setupTransforms(SquidEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, float scale) {
        //?} else {
        /*@Override
        protected void setupTransforms(SquidEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        */
        //?}
            float tiltAngle = MathHelper.lerp(tickDelta, entity.prevTiltAngle, entity.tiltAngle);
            float rollAngle = MathHelper.lerp(tickDelta, entity.prevRollAngle, entity.rollAngle);

            matrices.translate(0.0F, 0.5F, 0.0F);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - bodyYaw));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(tiltAngle));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rollAngle));
            matrices.translate(0.0F, -1.2F, 0.0F);
        }

        @Override
        protected void scale(SquidEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
