package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabySheepModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;

public class SquishSheepRenderer extends EntityRenderer<SheepEntity> {

    private static final Identifier BABY_TEXTURE =
            new Identifier(Squish.MOD_ID, "textures/entity/baby/sheep.png");

    private final EntityRenderer<SheepEntity> adult;
    private final BabyRenderer baby;

    public SquishSheepRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new SheepEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(SheepEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(SheepEntity entity) {
        return entity.isBaby() ? BABY_TEXTURE : adult.getTexture(entity);
    }

    private static final class BabyRenderer extends MobEntityRenderer<SheepEntity, BabySheepModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabySheepModel(ctx.getPart(SquishModelLayers.BABY_SHEEP)), 0.30f);
        }

        @Override
        public Identifier getTexture(SheepEntity entity) {
            return BABY_TEXTURE;
        }

        @Override
        protected void scale(SheepEntity entity, MatrixStack matrices, float amount) {
        }
    }
}