package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.CatEntity;

public class BabyCatModel extends SinglePartEntityModel<CatEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_hind_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;

    public BabyCatModel(ModelPart root) {
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

        ModelPartData head = modelPartData.addChild("head",
                ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.5F, 19.0F, -2.5F));

        head.addChild("nose",
                ModelPartBuilder.create().uv(18, 3).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.5F, 2.0F, -4.0F));

        head.addChild("left_ear",
                ModelPartBuilder.create().uv(24, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.0F, -2.0F, -1.0F));

        head.addChild("right_ear",
                ModelPartBuilder.create().uv(18, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, -2.0F, -1.0F));

        ModelPartData body = modelPartData.addChild("body",
                ModelPartBuilder.create().uv(0, 8).cuboid(-3.0F, -3.0F, -1.0F, 4.0F, 3.0F, 7.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.5F, 22.0F, -2.5F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, -2.5F, 5.9F));

        tail.addChild("tail_r1",
                ModelPartBuilder.create().uv(0, 18).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)),
                ModelTransform.of(-0.5F, 1.0F, 0.6F, -0.3927F, 0.0F, 0.0F));

        body.addChild("right_hind_leg",
                ModelPartBuilder.create().uv(12, 18).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, 0.0F, 4.5F));

        body.addChild("left_hind_leg",
                ModelPartBuilder.create().uv(18, 18).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 4.5F));

        body.addChild("right_front_leg",
                ModelPartBuilder.create().uv(12, 22).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, 0.0F, 0.5F));

        body.addChild("left_front_leg",
                ModelPartBuilder.create().uv(18, 22).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.5F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(CatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 0.9F
        );

        if (entity.isInSittingPose()) {
            this.tail.yaw = 0.0F;
            this.tail.pitch = 0.35F;
        } else {
            BabyAnimUtil.wagTailInBursts(this.tail, entity, ageInTicks, 0.0F, 0.55F);
        }
    }
}