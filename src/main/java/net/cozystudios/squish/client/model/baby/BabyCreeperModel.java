package net.cozystudios.squish.client.model.baby;

import net.cozystudios.squish.registry.entity.BabyCreeperEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

public class BabyCreeperModel extends SinglePartEntityModel<BabyCreeperEntity> {

    private final ModelPart root;
    private final ModelPart upper_legs;
    private final ModelPart hind_legs;
    private final ModelPart body;
    private final ModelPart head;

    private static final float DEG_TO_RAD = ((float) Math.PI / 180F);

    private static final float SIT_BODY_PITCH = 0.28F;
    private static final float SIT_BODY_LIFT_Y = 1.6F;
    private static final float SIT_BODY_SHIFT_Z = -0.5F;

    private static final float SIT_HIND_LEGS_LIFT_Y = 1.6F;
    private static final float SIT_UPPER_LEGS_LIFT_Y = 1F;
    private static final float SIT_UPPER_LEGS_PITCH = -1.15F;
    private static final float SIT_HIND_LEGS_PITCH  = 1.35F;

    private static final float SIT_HEAD_LIFT_Y = 1.6F;
    private static final float SIT_HEAD_PULLBACK_Z = -0.5F;
    private static final float SIT_HEAD_EXTRA_PITCH = 0.10F;

    public BabyCreeperModel(ModelPart root) {
        this.root = root;
        this.upper_legs = root.getChild("upper_legs");
        this.hind_legs = root.getChild("hind_legs");
        this.body = root.getChild("body");
        this.head = root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("upper_legs",
                ModelPartBuilder.create()
                        .uv(12, 8).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 22.0F, -1.0F));

        modelPartData.addChild("hind_legs",
                ModelPartBuilder.create()
                        .uv(12, 11).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 22.0F, 1.0F));

        modelPartData.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 8).cuboid(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 19.0F, 0.0F));

        modelPartData.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2.0F, -3.9F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 16.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(BabyCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        this.root.yaw = (float) Math.PI;

        this.head.yaw = (float) Math.PI + (netHeadYaw * DEG_TO_RAD);
        this.head.pitch = headPitch * DEG_TO_RAD;

        if (entity.isInSittingPose()) {
            applySittingPose();
            return;
        }

        float speed = 0.6662F;
        float amp = 1.1F * limbSwingAmount;

        this.upper_legs.pitch = MathHelper.cos(limbSwing * speed) * amp;
        this.hind_legs.pitch  = MathHelper.cos(limbSwing * speed + (float) Math.PI) * amp;
    }

    private void applySittingPose() {
        this.body.pivotY += SIT_BODY_LIFT_Y;
        this.body.pivotZ += SIT_BODY_SHIFT_Z;
        this.body.pitch = SIT_BODY_PITCH;

        this.upper_legs.pivotY += SIT_UPPER_LEGS_LIFT_Y;
        this.hind_legs.pivotY  += SIT_HIND_LEGS_LIFT_Y;

        this.upper_legs.pitch = SIT_UPPER_LEGS_PITCH;
        this.hind_legs.pitch  = SIT_HIND_LEGS_PITCH;

        this.head.pivotY += SIT_HEAD_LIFT_Y;
        this.head.pivotZ += SIT_HEAD_PULLBACK_Z;
        this.head.pitch += SIT_HEAD_EXTRA_PITCH;

        this.upper_legs.yaw = 0.0F;
        this.hind_legs.yaw  = 0.0F;
        this.upper_legs.roll = 0.0F;
        this.hind_legs.roll  = 0.0F;
    }
}
