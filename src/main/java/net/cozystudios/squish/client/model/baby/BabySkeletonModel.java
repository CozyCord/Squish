package net.cozystudios.squish.client.model.baby;

import net.cozystudios.squish.registry.entity.BabySkeletonEntity;
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

public class BabySkeletonModel extends EntityModel<BabySkeletonEntity> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;

    public BabySkeletonModel(ModelPart root) {
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

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));
        head.addChild("head_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 17.0F, 0.0F));
        body.addChild("body_r1", ModelPartBuilder.create().uv(0, 12).cuboid(-2.0F, -5.0F, -1.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 20).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData rightLeg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.3F, 18.0F, 0.0F));
        rightLeg.addChild("right_leg_r1", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData leftLeg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(-1.3F, 18.0F, 0.0F));
        leftLeg.addChild("left_leg_r1", ModelPartBuilder.create().uv(12, 12).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData rightArm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 12.0F, 0.0F));
        rightArm.addChild("right_arm_r1", ModelPartBuilder.create().uv(20, 12).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData leftArm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 12.0F, 0.0F));
        leftArm.addChild("left_arm_r1", ModelPartBuilder.create().uv(8, 20).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(BabySkeletonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.head.pitch = headPitch * ((float) Math.PI / 180F);

        if (entity.isInSittingPose()) {
            this.head.pivotY = 18.0F;
            this.body.pivotY = 23.0F;
            this.leftArm.pivotY = 18.0F;
            this.rightArm.pivotY = 18.0F;

            this.leftLeg.pivotY = 23.0F;
            this.rightLeg.pivotY = 23.0F;
            this.leftLeg.pitch = -1.5707963F;
            this.rightLeg.pitch = -1.5707963F;

            this.leftArm.pitch = -0.5F;
            this.rightArm.pitch = -0.5F;
        } else {
            this.head.pivotY = 12.0F;
            this.body.pivotY = 17.0F;
            this.leftArm.pivotY = 12.0F;
            this.rightArm.pivotY = 12.0F;
            this.leftLeg.pivotY = 18.0F;
            this.rightLeg.pivotY = 18.0F;

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
    */
    //?}
}
