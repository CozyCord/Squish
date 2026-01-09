package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyWolfModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class SquishWolfRenderer extends EntityRenderer<WolfEntity> {

    private static final Identifier BABY_TEXTURE =
            new Identifier(Squish.MOD_ID, "textures/entity/baby/wolf.png");

    private final EntityRenderer<WolfEntity> adult;
    private final BabyRenderer baby;

    public SquishWolfRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new WolfEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(WolfEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(WolfEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<WolfEntity, BabyWolfModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyWolfModel(ctx.getPart(SquishModelLayers.BABY_WOLF)), 0.25f);
        }

        @Override
        public Identifier getTexture(WolfEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(WolfEntity entity, MatrixStack matrices, float amount) {
            matrices.scale(0.90f, 0.90f, 0.90f);
        }
    }
}