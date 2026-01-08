package net.cozystudios.squish.client.render.baby;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class BabyAwareCatCollarFeatureRenderer
        extends FeatureRenderer<CatEntity, CatEntityModel<CatEntity>> {

    private static final Identifier ADULT_COLLAR = new Identifier("minecraft", "textures/entity/cat/collar.png");
    private static final Identifier BABY_COLLAR  = BabyTextureUtil.squishBaby("cat_collar_baby.png");

    public BabyAwareCatCollarFeatureRenderer(FeatureRendererContext<CatEntity, CatEntityModel<CatEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       CatEntity cat, float limbAngle, float limbDistance, float tickDelta,
                       float animationProgress, float headYaw, float headPitch) {

        if (!cat.isTamed() || cat.isInvisible()) return;

        Identifier tex = cat.isBaby() ? BABY_COLLAR : ADULT_COLLAR;

        DyeColor dye = cat.getCollarColor();
        float[] c = dye.getColorComponents();

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(tex));
        this.getContextModel().render(matrices, vc, light, OverlayTexture.DEFAULT_UV, c[0], c[1], c[2], 1.0F);
    }
}