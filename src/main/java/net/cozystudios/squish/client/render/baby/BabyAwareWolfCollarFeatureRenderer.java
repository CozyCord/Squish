package net.cozystudios.squish.client.render.baby;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class BabyAwareWolfCollarFeatureRenderer
        extends FeatureRenderer<WolfEntity, WolfEntityModel<WolfEntity>> {

    private static final Identifier ADULT_COLLAR = new Identifier("minecraft", "textures/entity/wolf/wolf_collar.png");
    private static final Identifier BABY_COLLAR  = BabyTextureUtil.squishBaby("wolf_collar_baby.png");

    public BabyAwareWolfCollarFeatureRenderer(FeatureRendererContext<WolfEntity, WolfEntityModel<WolfEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       WolfEntity wolf, float limbAngle, float limbDistance, float tickDelta,
                       float animationProgress, float headYaw, float headPitch) {

        if (!wolf.isTamed() || wolf.isInvisible()) return;

        Identifier tex = wolf.isBaby() ? BABY_COLLAR : ADULT_COLLAR;

        DyeColor dye = wolf.getCollarColor();
        float[] c = dye.getColorComponents();

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(tex));
        this.getContextModel().render(matrices, vc, light, OverlayTexture.DEFAULT_UV, c[0], c[1], c[2], 1.0F);
    }
}