package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyAxolotlModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.AxolotlEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;

public class SquishAxolotlRenderer extends EntityRenderer<AxolotlEntity> {

    private final EntityRenderer<AxolotlEntity> adult;
    private final BabyRenderer baby;

    public SquishAxolotlRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new AxolotlEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(AxolotlEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(AxolotlEntity entity) {
        return entity.isBaby() ? baby.getTexture(entity) : adult.getTexture(entity);
    }

    private static Identifier babyFromVanillaAxolotlTexture(Identifier vanillaTexture) {
        String path = vanillaTexture.getPath();
        String prefix = "textures/entity/axolotl/";
        String file = path.startsWith(prefix) ? path.substring(prefix.length()) : path;
        
        file = file.replace(".png", "_baby.png");
        return SquishId.of(Squish.MOD_ID, "textures/entity/baby/axolotl/" + file);
    }

    private final class BabyRenderer extends MobEntityRenderer<AxolotlEntity, BabyAxolotlModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyAxolotlModel(ctx.getPart(SquishModelLayers.BABY_AXOLOTL)), 0.25f);
        }

        @Override
        public Identifier getTexture(AxolotlEntity entity) {
            return babyFromVanillaAxolotlTexture(adult.getTexture(entity));
        }

        @Override
        protected void scale(AxolotlEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
