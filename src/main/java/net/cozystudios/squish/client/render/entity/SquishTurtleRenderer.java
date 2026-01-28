package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyTurtleModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.TurtleEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.util.Identifier;

public class SquishTurtleRenderer extends EntityRenderer<TurtleEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/turtle_baby.png");

    private final EntityRenderer<TurtleEntity> adult;
    private final BabyRenderer baby;

    public SquishTurtleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new TurtleEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(TurtleEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(TurtleEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<TurtleEntity, BabyTurtleModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyTurtleModel(ctx.getPart(SquishModelLayers.BABY_TURTLE)), 0.25f);
        }

        @Override
        public Identifier getTexture(TurtleEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(TurtleEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
