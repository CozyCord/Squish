package net.cozystudios.squish.client.render.baby;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;

public class BabyAwareSheepWoolFeatureRenderer
        extends FeatureRenderer<SheepEntity, SheepEntityModel<SheepEntity>> {

    private static final Identifier ADULT_WOOL = new Identifier("minecraft", "textures/entity/sheep/sheep_fur.png");
    private static final Identifier BABY_WOOL  = BabyTextureUtil.squishBaby("sheep_wool_baby.png");

    private final SheepWoolEntityModel<SheepEntity> woolModel;

    public BabyAwareSheepWoolFeatureRenderer(FeatureRendererContext<SheepEntity, SheepEntityModel<SheepEntity>> context,
                                             EntityRendererFactory.Context factoryCtx) {
        super(context);
        this.woolModel = new SheepWoolEntityModel<>(factoryCtx.getPart(EntityModelLayers.SHEEP_FUR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       SheepEntity sheep, float limbAngle, float limbDistance, float tickDelta,
                       float animationProgress, float headYaw, float headPitch) {

        if (sheep.isSheared() || sheep.isInvisible()) return;

        Identifier tex = sheep.isBaby() ? BABY_WOOL : ADULT_WOOL;

        this.getContextModel().copyStateTo(this.woolModel);
        this.woolModel.animateModel(sheep, limbAngle, limbDistance, tickDelta);
        this.woolModel.setAngles(sheep, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        float[] rgb = SheepEntity.getRgbColor(sheep.getColor());
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(tex));
        this.woolModel.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
    }
}