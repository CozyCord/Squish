package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public final class BabyAnimUtil {
    private BabyAnimUtil() {}

    public static final float DEG_TO_RAD = (float)(Math.PI / 180.0);

    public static void reset(ModelPart root) {
        root.traverse().forEach(ModelPart::resetTransform);
    }

    public static void applyHeadRotation(ModelPart head, float netHeadYaw, float headPitch) {
        head.yaw   += netHeadYaw * DEG_TO_RAD;
        head.pitch += headPitch  * DEG_TO_RAD;
    }

    public static void animateQuadrupedLegs(
            ModelPart rightFront, ModelPart leftFront,
            ModelPart rightHind, ModelPart leftHind,
            float limbSwing, float limbSwingAmount,
            float swingScale
    ) {
        float swing = limbSwing * 0.6662F;
        float amount = MathHelper.clamp(limbSwingAmount, 0.0F, 1.0F) * swingScale;

        float rf = MathHelper.cos(swing) * 1.4F * amount;
        float lf = MathHelper.cos(swing + (float)Math.PI) * 1.4F * amount;

        rightFront.pitch = rf;
        leftFront.pitch  = lf;

        rightHind.pitch = lf;
        leftHind.pitch  = rf;
    }

    public static void animateQuadrupedLegsClamped(
            ModelPart rightFront, ModelPart leftFront,
            ModelPart rightHind, ModelPart leftHind,
            float limbSwing, float limbSwingAmount,
            float swingScale
    ) {
        animateQuadrupedLegs(
                rightFront, leftFront,
                rightHind, leftHind,
                limbSwing, limbSwingAmount,
                swingScale
        );
    }

    public static void animateChickenLegs(ModelPart rightLeg, ModelPart leftLeg, float limbSwing, float limbSwingAmount) {
        float swing = limbSwing * 0.6662F;
        float amount = MathHelper.clamp(limbSwingAmount, 0.0F, 1.0F);

        rightLeg.pitch = MathHelper.cos(swing) * 1.4F * amount;
        leftLeg.pitch  = MathHelper.cos(swing + (float)Math.PI) * 1.4F * amount;
    }

    public static void wagTailInBursts(ModelPart tail, Entity entity, float ageInTicks, float basePitch, float yawStrength) {
        float t = ageInTicks + (entity.getId() * 17.0F);
        float period = 80.0F;
        float active = 12.0F;

        float m = t % period;
        if (m < active) {
            float w = MathHelper.sin(m * 0.9F) * yawStrength;
            tail.yaw = w;
            tail.pitch = basePitch;
        } else {
            tail.yaw = 0.0F;
            tail.pitch = basePitch;
        }
    }
}