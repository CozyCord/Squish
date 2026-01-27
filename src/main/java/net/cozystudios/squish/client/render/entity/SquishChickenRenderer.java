package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.util.SquishId;
import net.cozystudios.squish.client.model.baby.BabyChickenModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;

public class SquishChickenRenderer extends EntityRenderer<ChickenEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/chicken.png");

    private final EntityRenderer<ChickenEntity> adult;
    private final BabyRenderer baby;

    public SquishChickenRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new ChickenEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(ChickenEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ChickenEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<ChickenEntity, BabyChickenModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyChickenModel(ctx.getPart(SquishModelLayers.BABY_CHICKEN)), 0.20f);
        }

        @Override
        public Identifier getTexture(ChickenEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(ChickenEntity entity, MatrixStack matrices, float amount) {
        }
    }
}