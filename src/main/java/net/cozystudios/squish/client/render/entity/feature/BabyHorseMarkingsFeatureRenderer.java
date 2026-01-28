package net.cozystudios.squish.client.render.entity.feature;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.baby.BabyHorseModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;
import net.minecraft.util.Identifier;

public class BabyHorseMarkingsFeatureRenderer extends FeatureRenderer<HorseEntity, BabyHorseModel> {

    public BabyHorseMarkingsFeatureRenderer(FeatureRendererContext<HorseEntity, BabyHorseModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       HorseEntity entity, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entity.isInvisible()) return;

        HorseMarking marking = entity.getMarking();
        if (marking == HorseMarking.NONE) return;

        Identifier markingTexture = getMarkingTexture(marking);
        if (markingTexture == null) return;

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(markingTexture));

        
        //? if <=1.20.4 {
        /*this.getContextModel().render(
                matrices,
                vc,
                light,
                net.minecraft.client.render.entity.LivingEntityRenderer.getOverlay(entity, 0.0F),
                1.0F, 1.0F, 1.0F, 1.0F
        );
        */
        //?} else {
        this.getContextModel().render(
                matrices,
                vc,
                light,
                net.minecraft.client.render.entity.LivingEntityRenderer.getOverlay(entity, 0.0F),
                0xFFFFFF
        );
        //?}
    }

    private Identifier getMarkingTexture(HorseMarking marking) {
        return switch (marking) {
            case WHITE -> SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_markings_white_baby.png");
            case WHITE_FIELD -> SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_markings_whitefield_baby.png");
            case WHITE_DOTS -> SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_markings_whitedots_baby.png");
            case BLACK_DOTS -> SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_markings_blackdots_baby.png");
            default -> null;
        };
    }
}
