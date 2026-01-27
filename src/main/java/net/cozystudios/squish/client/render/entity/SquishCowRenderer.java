package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.util.SquishId;
import net.cozystudios.squish.client.model.baby.BabyCowModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.Identifier;

public class SquishCowRenderer extends EntityRenderer<CowEntity> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/cow.png");

    private final EntityRenderer<CowEntity> adult;
    private final BabyRenderer baby;

    public SquishCowRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new CowEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(CowEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(CowEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<CowEntity, BabyCowModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyCowModel(ctx.getPart(SquishModelLayers.BABY_COW)), 0.45f);
        }

        @Override
        public Identifier getTexture(CowEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(CowEntity entity, MatrixStack matrices, float amount) {
        }
    }
}