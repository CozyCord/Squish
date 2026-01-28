package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyDonkeyModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.DonkeyEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.util.Identifier;

public class SquishDonkeyRenderer extends EntityRenderer<DonkeyEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/donkey_baby.png");

    private final DonkeyEntityRenderer<DonkeyEntity> adult;
    private final BabyRenderer baby;

    public SquishDonkeyRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new DonkeyEntityRenderer<DonkeyEntity>(ctx, 0.87F, EntityModelLayers.DONKEY);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(DonkeyEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(DonkeyEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<DonkeyEntity, BabyDonkeyModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyDonkeyModel(ctx.getPart(SquishModelLayers.BABY_DONKEY)), 0.5f);
        }

        @Override
        public Identifier getTexture(DonkeyEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(DonkeyEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
