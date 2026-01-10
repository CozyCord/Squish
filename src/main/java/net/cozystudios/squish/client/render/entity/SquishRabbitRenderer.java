package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyRabbitModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.Identifier;

public class SquishRabbitRenderer extends EntityRenderer<RabbitEntity> {

    private final RabbitEntityRenderer adult;
    private final BabyRenderer baby;

    public SquishRabbitRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new RabbitEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(RabbitEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(RabbitEntity entity) {
        return entity.isBaby() ? baby.getTexture(entity) : adult.getTexture(entity);
    }

    private static Identifier babyFromVanillaRabbitTexture(Identifier vanillaRabbitTexture) {
        String path = vanillaRabbitTexture.getPath();
        String prefix = "textures/entity/rabbit/";
        String file = path.startsWith(prefix) ? path.substring(prefix.length()) : path;
        return new Identifier(Squish.MOD_ID, "textures/entity/baby/rabbit/" + file);
    }

    private final class BabyRenderer extends MobEntityRenderer<RabbitEntity, BabyRabbitModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyRabbitModel(ctx.getPart(SquishModelLayers.BABY_RABBIT)), 0.20f);
        }

        @Override
        public Identifier getTexture(RabbitEntity entity) {
            return babyFromVanillaRabbitTexture(adult.getTexture(entity));
        }

        @Override
        protected void scale(RabbitEntity entity, MatrixStack matrices, float amount) {
            matrices.scale(0.55f, 0.55f, 0.55f);
        }
    }
}