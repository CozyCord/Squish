package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyDolphinModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.DolphinEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.util.Identifier;

public class SquishDolphinRenderer extends EntityRenderer<DolphinEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/dolphin_baby.png");

    private final EntityRenderer<DolphinEntity> adult;
    private final BabyRenderer baby;

    public SquishDolphinRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new DolphinEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(DolphinEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(DolphinEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<DolphinEntity, BabyDolphinModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyDolphinModel(ctx.getPart(SquishModelLayers.BABY_DOLPHIN)), 0.4f);
        }

        @Override
        public Identifier getTexture(DolphinEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(DolphinEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
