package net.cozystudios.squish.client.render.entity.feature;

import net.cozystudios.squish.client.model.baby.BabySheepModel;
import net.cozystudios.squish.client.render.entity.SquishSheepRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;

public class BabySheepWoolFeatureRenderer extends FeatureRenderer<SheepEntity, BabySheepModel> {

    public BabySheepWoolFeatureRenderer(FeatureRendererContext<SheepEntity, BabySheepModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       SheepEntity entity, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entity.isSheared() || entity.isInvisible()) return;

        DyeColor color = entity.getColor();
        float[] rgb = SheepEntity.getRgbColor(color);

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(SquishSheepRenderer.BABY_SHEEP_WOOL));

        this.getContextModel().render(
                matrices,
                vc,
                light,
                LivingEntityRenderer.getOverlay(entity, 0.0F),
                rgb[0], rgb[1], rgb[2], 1.0F
        );
    }
}
