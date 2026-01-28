package net.cozystudios.squish.client.render.entity.feature;

import net.cozystudios.squish.client.model.baby.BabyWolfModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

//? if <=1.20.4 {
/*import net.minecraft.util.math.MathHelper;
*/
//?}

public class BabyWolfCollarFeatureRenderer extends FeatureRenderer<WolfEntity, BabyWolfModel> {

    private static final Identifier BABY_COLLAR_TEX =
            SquishId.of("textures/entity/baby/wolf_collar_baby.png");

    public BabyWolfCollarFeatureRenderer(FeatureRendererContext<WolfEntity, BabyWolfModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       WolfEntity entity, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (!entity.isTamed() || entity.isInvisible()) return;

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(BABY_COLLAR_TEX));

        //? if <=1.20.4 {
        /*float[] rgb = entity.getCollarColor().getColorComponents();
        float r = MathHelper.clamp(rgb[0], 0.0F, 1.0F);
        float g = MathHelper.clamp(rgb[1], 0.0F, 1.0F);
        float b = MathHelper.clamp(rgb[2], 0.0F, 1.0F);
        this.getContextModel().render(
                matrices,
                vc,
                light,
                LivingEntityRenderer.getOverlay(entity, 0.0F),
                r, g, b, 1.0F
        );
        */
        //?} else {
        DyeColor dyeColor = entity.getCollarColor();
        int color = dyeColor.getEntityColor() | 0xFF000000;
        this.getContextModel().render(
                matrices,
                vc,
                light,
                LivingEntityRenderer.getOverlay(entity, 0.0F),
                color
        );
        //?}
    }
}
