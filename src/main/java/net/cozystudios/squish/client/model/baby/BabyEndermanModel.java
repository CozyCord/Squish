package net.cozystudios.squish.client.model.baby;

import net.cozystudios.squish.registry.entity.BabyEndermanEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BabyEndermanModel extends EntityModel<BabyEndermanEntity> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;

    public BabyEndermanModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        // Model is 16 units tall, grounded at Y=24
        // Head: Y=8 to Y=14 (top of head to neck)
        modelPartData.addChild("head", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 14.0F, 0.0F));

        // Body: Y=14 to Y=19
        modelPartData.addChild("body", ModelPartBuilder.create()
                        .uv(0, 12).cuboid(-3.0F, 0.0F, -2.0F, 6.0F, 5.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 14.0F, 0.0F));

        // Left leg: Y=19 to Y=24 (ground)
        modelPartData.addChild("left_leg", ModelPartBuilder.create()
                        .uv(0, 21).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-1.5F, 19.0F, 0.0F));

        // Right leg: Y=19 to Y=24 (ground)
        modelPartData.addChild("right_leg", ModelPartBuilder.create()
                        .uv(0, 21).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.5F, 19.0F, 0.0F));

        // Left arm: Y=14 to Y=22
        modelPartData.addChild("left_arm", ModelPartBuilder.create()
                        .uv(20, 12).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-3.5F, 14.0F, 0.0F));

        // Right arm: Y=14 to Y=22
        modelPartData.addChild("right_arm", ModelPartBuilder.create()
                        .uv(20, 12).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(3.5F, 14.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(BabyEndermanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.head.pitch = headPitch * ((float) Math.PI / 180F);

        // Sitting animation - lower body by 4.5 units to sit just above ground
        if (entity.isInSittingPose()) {
            // Lower upper body parts by 4.5 units (raised 0.5 from ground)
            this.head.pivotY = 18.5F;
            this.body.pivotY = 18.5F;
            this.leftArm.pivotY = 18.5F;
            this.rightArm.pivotY = 18.5F;

            // Move legs down and point forward
            this.leftLeg.pivotY = 23.5F;
            this.rightLeg.pivotY = 23.5F;
            this.leftLeg.pitch = -1.5707963F;
            this.rightLeg.pitch = -1.5707963F;

            // Rotate arms forward so they don't clip into the ground
            this.leftArm.pitch = -0.5F;
            this.rightArm.pitch = -0.5F;
        } else {
            // Reset to standing positions
            this.head.pivotY = 14.0F;
            this.body.pivotY = 14.0F;
            this.leftArm.pivotY = 14.0F;
            this.rightArm.pivotY = 14.0F;
            this.leftLeg.pivotY = 19.0F;
            this.rightLeg.pivotY = 19.0F;

            // Walking animations
            this.rightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.leftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

            this.rightArm.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;
            this.leftArm.pitch = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
        }
    }

    //? if >1.20.4 {
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        head.render(matrices, vertexConsumer, light, overlay, color);
        body.render(matrices, vertexConsumer, light, overlay, color);
        leftLeg.render(matrices, vertexConsumer, light, overlay, color);
        rightLeg.render(matrices, vertexConsumer, light, overlay, color);
        leftArm.render(matrices, vertexConsumer, light, overlay, color);
        rightArm.render(matrices, vertexConsumer, light, overlay, color);
    }
    //?} else {
    /*@Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leftLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        rightLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        rightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
    *///?}
}
