package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.MathHelper;

public class BabyWolfModel extends SinglePartEntityModel<WolfEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;

    public BabyWolfModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.right_front_leg = this.body.getChild("right_front_leg");
        this.left_front_leg = this.body.getChild("left_front_leg");
        this.left_hind_leg = this.body.getChild("left_hind_leg");
        this.right_hind_leg = this.body.getChild("right_hind_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 12).cuboid(-3.0F, -3.0F, -5.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 18.0F, 0.0F));

        head.addChild("snout",
                ModelPartBuilder.create()
                        .uv(17, 12).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.5F, 2.0F, -6.0F));

        head.addChild("left_ear",
                ModelPartBuilder.create()
                        .uv(0, 5).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.0F, -3.0F, -2.0F));

        head.addChild("right_ear",
                ModelPartBuilder.create()
                        .uv(20, 5).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, -3.0F, -2.0F));

        ModelPartData body = modelPartData.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-5.0F, -4.0F, -1.0F, 6.0F, 4.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.0F, 21.0F, -1.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -2.0F, 7.0F));
        tail.addChild("tail_r1",
                ModelPartBuilder.create()
                        .uv(16, 16).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        body.addChild("right_front_leg",
                ModelPartBuilder.create()
                        .uv(0, 22).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F, 0.0F, 0.0F));

        body.addChild("left_front_leg",
                ModelPartBuilder.create()
                        .uv(8, 22).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        body.addChild("left_hind_leg",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 6.0F));

        body.addChild("right_hind_leg",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F, 0.0F, 6.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(WolfEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        if (entity.isInSittingPose()) {
            applySittingPose(entity);
            return;
        }

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 0.9F
        );

        float basePitch;

        if (entity.hasAngerTime()) {
            basePitch = 0.15F;
            this.tail.yaw = 0.0F;
        }
        else if (entity.isTamed()) {
            basePitch = 0.25F;

            if (limbSwingAmount > 0.08F && entity.isOnGround()) {
                float strength = MathHelper.clamp(limbSwingAmount, 0.0F, 1.0F);
                this.tail.yaw = MathHelper.cos(limbSwing * 0.6662F) * 0.9F * strength;
            } else {
                this.tail.yaw = 0.0F;
            }
        }
        else {
            basePitch = 0.35F;
            BabyAnimUtil.wagTailInBursts(this.tail, entity, ageInTicks, basePitch, 0.35F);
            this.tail.pitch = basePitch;
            return;
        }

        this.tail.pitch = basePitch;

        if (!entity.isOnGround()) {
            this.tail.yaw = 0.0F;
        }
    }

    private void applySittingPose(WolfEntity entity) {
        final float BODY_LIFT_Y = 0.65F;
        final float HIND_LIFT_Y = 0.45F;

        this.body.pitch = -((float) Math.PI / 4F);
        this.body.pivotY -= BODY_LIFT_Y;

        float hindLegPitch = (float) Math.PI * 1.5F;
        this.right_hind_leg.pitch = hindLegPitch - this.body.pitch;
        this.left_hind_leg.pitch  = hindLegPitch - this.body.pitch;

        this.right_hind_leg.pivotY -= HIND_LIFT_Y;
        this.left_hind_leg.pivotY  -= HIND_LIFT_Y;

        float frontLegPitch = 5.811947F;
        this.right_front_leg.pitch = frontLegPitch - this.body.pitch;
        this.left_front_leg.pitch  = frontLegPitch - this.body.pitch;

        this.right_front_leg.pivotX += 0.01F;
        this.left_front_leg.pivotX  -= 0.01F;

        float sittingTailBase = 0.25F;
        if (entity.hasAngerTime()) {
            sittingTailBase = 0.10F;
        }

        this.tail.yaw = 0.0F;
        this.tail.pitch = sittingTailBase - this.body.pitch;
    }
}