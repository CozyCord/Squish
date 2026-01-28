package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyMuleModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.DonkeyEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.MuleEntity;
import net.minecraft.util.Identifier;

public class SquishMuleRenderer extends EntityRenderer<MuleEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/mule_baby.png");

    private final DonkeyEntityRenderer<MuleEntity> adult;
    private final BabyRenderer baby;

    public SquishMuleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new DonkeyEntityRenderer<MuleEntity>(ctx, 0.92F, EntityModelLayers.MULE);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(MuleEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MuleEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<MuleEntity, BabyMuleModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyMuleModel(ctx.getPart(SquishModelLayers.BABY_MULE)), 0.5f);
        }

        @Override
        public Identifier getTexture(MuleEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(MuleEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
