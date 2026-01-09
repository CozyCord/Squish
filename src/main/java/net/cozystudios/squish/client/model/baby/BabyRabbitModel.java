package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.RabbitEntity;

public class BabyRabbitModel extends SinglePartEntityModel<RabbitEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_hind_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;

    public BabyRabbitModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.right_hind_leg = this.body.getChild("right_hind_leg");
        this.left_hind_leg = this.body.getChild("left_hind_leg");
        this.right_front_leg = this.body.getChild("right_front_leg");
        this.left_front_leg = this.body.getChild("left_front_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -1.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, 19.0F, -4.0F));

        ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create().uv(18, 0).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -4.0F, 2.0F));

        ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create().uv(24, 0).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 2.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 21.0F, -3.0F));

        ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-3.0F, -3.0F, -1.0F, 4.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4102F, 0.0F, 0.0F));

        ModelPartData right_hind_leg = body.addChild("right_hind_leg", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 3.0F, 3.0F));

        ModelPartData right_hind_leg_r1 = right_hind_leg.addChild("right_hind_leg_r1", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        ModelPartData left_hind_leg = body.addChild("left_hind_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 3.0F, 3.0F));

        ModelPartData left_hind_leg_r1 = left_hind_leg.addChild("left_hind_leg_r1", ModelPartBuilder.create().uv(10, 17).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 1.0F, 5.0F));

        ModelPartData tail_r1 = tail.addChild("tail_r1", ModelPartBuilder.create().uv(0, 21).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4102F, 0.0F, 0.0F));

        ModelPartData right_front_leg = body.addChild("right_front_leg", ModelPartBuilder.create().uv(14, 8).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 3.0F, 1.5F));

        ModelPartData left_front_leg = body.addChild("left_front_leg", ModelPartBuilder.create().uv(18, 8).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 3.0F, 1.5F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(RabbitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 0.8F
        );

        float basePitch = 0.0F;
        BabyAnimUtil.wagTailInBursts(this.tail, entity, ageInTicks, basePitch, 0.35F);
    }
}
