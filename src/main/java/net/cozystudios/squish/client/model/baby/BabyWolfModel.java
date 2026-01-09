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
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 12).cuboid(-5.0F, -5.0F, -1.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 20.0F, -4.0F));

        ModelPartData snout = head.addChild("snout", ModelPartBuilder.create().uv(17, 12).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 0.0F, -2.0F));

        ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create().uv(0, 5).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, 2.0F));

        ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create().uv(20, 5).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -5.0F, 2.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -4.0F, -1.0F, 6.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 21.0F, -1.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -1.0F, 7.0F));

        ModelPartData tail_r1 = tail.addChild("tail_r1", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData right_front_leg = body.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 3.0F, 0.0F));

        ModelPartData left_front_leg = body.addChild("left_front_leg", ModelPartBuilder.create().uv(8, 22).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

        ModelPartData left_hind_leg = body.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 6.0F));

        ModelPartData right_hind_leg = body.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 3.0F, 6.0F));
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

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 0.9F
        );

        this.tail.pitch = entity.getTailAngle();

        if (entity.isTamed() && !entity.hasAngerTime()) {
            float wag = MathHelper.cos(ageInTicks * 0.9F) * 0.15F;
            this.tail.yaw = wag;
        } else if (!entity.isOnGround()) {
            this.tail.yaw = 0.0F;
        } else if (!entity.isTamed()) {
            BabyAnimUtil.wagTailInBursts(this.tail, entity, ageInTicks, this.tail.pitch, 0.25F);
        }
    }
}
