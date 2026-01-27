package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.util.SquishId;
import net.cozystudios.squish.client.model.baby.BabyCatModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Identifier;

public class SquishCatRenderer extends EntityRenderer<CatEntity> {

    private final CatEntityRenderer adult;
    private final BabyRenderer baby;

    public SquishCatRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new CatEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(CatEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(CatEntity entity) {
        return entity.isBaby() ? baby.getTexture(entity) : adult.getTexture(entity);
    }

    private static Identifier babyFromVanillaCatTexture(Identifier vanillaCatTexture) {
        String path = vanillaCatTexture.getPath();
        String prefix = "textures/entity/cat/";
        String file = path.startsWith(prefix) ? path.substring(prefix.length()) : path;
        return SquishId.of(Squish.MOD_ID, "textures/entity/baby/cat/" + file);
    }

    private final class BabyRenderer extends MobEntityRenderer<CatEntity, BabyCatModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyCatModel(ctx.getPart(SquishModelLayers.BABY_CAT)), 0.25f);
        }

        @Override
        public Identifier getTexture(CatEntity entity) {
            return babyFromVanillaCatTexture(adult.getTexture(entity));
        }

        @Override
        protected void scale(CatEntity entity, MatrixStack matrices, float amount) {
            matrices.scale(0.85f, 0.85f, 0.85f);
        }
    }
}