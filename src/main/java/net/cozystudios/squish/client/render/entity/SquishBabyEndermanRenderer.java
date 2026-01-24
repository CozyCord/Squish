package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyEndermanModel;
import net.cozystudios.squish.registry.entity.BabyEndermanEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.cozystudios.squish.client.render.entity.feature.BabyEndermanEyesFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SquishBabyEndermanRenderer extends MobEntityRenderer<BabyEndermanEntity, BabyEndermanModel> {

    private static final Identifier TEXTURE = new Identifier("squish", "textures/entity/baby/baby_enderman.png");

    public SquishBabyEndermanRenderer(EntityRendererFactory.Context context) {
        super(context, new BabyEndermanModel(context.getPart(SquishModelLayers.BABY_ENDERMAN)), 0.3f);
        this.addFeature(new BabyEndermanEyesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(BabyEndermanEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabyEndermanEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
