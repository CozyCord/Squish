package net.cozystudios.squish.client.model.baby;

import net.cozystudios.squish.entity.BabyIronGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

public class BabyIronGolemModel extends SinglePartEntityModel<BabyIronGolemEntity> {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart poppy_anchor;

    private static final float DEG_TO_RAD = ((float) Math.PI / 180F);

    public BabyIronGolemModel(ModelPart root) {
        this.root = root;

        this.head = root.getChild("head");
        this.nose = this.head.getChild("nose");
        this.body = root.getChild("body");
        this.body2 = root.getChild("body2");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
        this.poppy_anchor = this.head.getChild("poppy_anchor");
    }

    public ModelPart getHead() {
        return this.head;
    }

    public ModelPart getPoppyAnchor() {
        return this.poppy_anchor;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head",
                ModelPartBuilder.create().uv(0, 6)
                        .cuboid(-1.5F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 17.0F, 0.0F));

        head.addChild("poppy_anchor",
                ModelPartBuilder.create(),
                ModelTransform.pivot(-3F, -3F, 4F));

        head.addChild("nose",
                ModelPartBuilder.create()
                        .uv(0, 18)
                        .cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -0.5F, -1.0F));

        modelPartData.addChild("body",
                ModelPartBuilder.create().uv(0, 0)
                        .cuboid(-2.0F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 18.5F, 0.0F));

        modelPartData.addChild("body2",
                ModelPartBuilder.create().uv(10, 6)
                        .cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 20.0F, 0.0F));

        modelPartData.addChild("right_arm",
                ModelPartBuilder.create().uv(10, 9)
                        .cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, 17.0F, 0.0F));

        modelPartData.addChild("left_arm",
                ModelPartBuilder.create().uv(0, 11)
                        .cuboid(0.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.0F, 17.0F, 0.0F));

        modelPartData.addChild("left_leg",
                ModelPartBuilder.create().uv(4, 11)
                        .cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.0F, 21.0F, 0.0F));

        modelPartData.addChild("right_leg",
                ModelPartBuilder.create().uv(14, 0)
                        .cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-1.0F, 21.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(BabyIronGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        this.root.yaw = (float) Math.PI;

        this.head.yaw = (float) Math.PI + (netHeadYaw * DEG_TO_RAD);
        this.head.pitch = headPitch * DEG_TO_RAD;


        if (entity.isInSittingPose()) {
            applySittingPose();
            return;
        }

        float speed = 0.6662F;
        float ampLeg = 1.05F * limbSwingAmount;

        this.left_leg.pitch  = MathHelper.cos(limbSwing * speed) * ampLeg;
        this.right_leg.pitch = MathHelper.cos(limbSwing * speed + (float) Math.PI) * ampLeg;

        float ampArm = 0.6F * limbSwingAmount;
        this.left_arm.pitch  = MathHelper.cos(limbSwing * speed + (float) Math.PI) * ampArm;
        this.right_arm.pitch = MathHelper.cos(limbSwing * speed) * ampArm;
    }

    private void applySittingPose() {

        this.root.pivotY += 1.0F;

        this.body.pitch = 0.35F;
        this.body.pivotY += 1.2F;
        this.body.pivotZ -= 0.6F;

        this.body2.pitch = this.body.pitch;
        this.body2.pivotY += 1.2F;
        this.body2.pivotZ -= 0.6F;

        this.left_leg.pitch = 1.15F;
        this.right_leg.pitch = 1.15F;

        this.left_leg.pivotY += 0.8F;
        this.right_leg.pivotY += 0.8F;

        this.left_arm.pitch = -0.35F;
        this.right_arm.pitch = -0.35F;

        this.head.pivotY += 0.6F;
        this.head.pivotZ -= 0.25F;
        this.head.pitch += 0.10F;
    }
}