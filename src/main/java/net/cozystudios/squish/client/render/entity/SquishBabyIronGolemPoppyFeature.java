package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.client.model.baby.BabyIronGolemModel;
import net.cozystudios.squish.registry.entity.BabyIronGolemEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class SquishBabyIronGolemPoppyFeature extends FeatureRenderer<BabyIronGolemEntity, BabyIronGolemModel> {

    private static final BlockState POPPY_STATE = Blocks.POPPY.getDefaultState();

    public SquishBabyIronGolemPoppyFeature(FeatureRendererContext<BabyIronGolemEntity, BabyIronGolemModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       BabyIronGolemEntity entity, float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress, float headYaw, float headPitch) {

        matrices.push();

        this.getContextModel().getPart().rotate(matrices);

        this.getContextModel().getHead().rotate(matrices);

        this.getContextModel().getPoppyAnchor().rotate(matrices);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));

        matrices.scale(0.45f, 0.45f, 0.45f);

        MinecraftClient.getInstance()
                .getBlockRenderManager()
                .renderBlockAsEntity(POPPY_STATE, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
    }
}
