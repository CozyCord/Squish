package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.CowEntity;

public class BabyCowModel extends SinglePartEntityModel<CowEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;

    private final ModelPart right_hind_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;

    public BabyCowModel(ModelPart root) {
        this.root = root;

        this.head = root.getChild("head");
        this.body = root.getChild("body");

        this.right_hind_leg = root.getChild("right_hind_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.left_front_leg = root.getChild("left_front_leg");
        this.right_front_leg = root.getChild("right_front_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head",
                ModelPartBuilder.create().uv(0, 18).cuboid(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 12.0F, -5.0F));

        head.addChild("nose",
                ModelPartBuilder.create().uv(12, 29).cuboid(-3.0F, -3.0F, -1.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.0F, 3.0F, -5.0F));

        head.addChild("right_horn",
                ModelPartBuilder.create().uv(8, 29).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-4.0F, -2.0F, -3.0F));

        head.addChild("left_horn",
                ModelPartBuilder.create().uv(4, 29).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(3.0F, -2.0F, -3.0F));

        modelPartData.addChild("body",
                ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -6.0F, -1.0F, 8.0F, 6.0F, 12.0F, new Dilation(0.0F)),
                ModelTransform.pivot(3.0F, 18.0F, -5.0F));

        modelPartData.addChild("right_hind_leg",
                ModelPartBuilder.create().uv(22, 27).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.5F, 18.0F, 3.5F));

        modelPartData.addChild("left_hind_leg",
                ModelPartBuilder.create().uv(34, 27).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.5F, 18.0F, 3.5F));

        modelPartData.addChild("left_front_leg",
                ModelPartBuilder.create().uv(34, 18).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.5F, 18.0F, -3.5F));

        modelPartData.addChild("right_front_leg",
                ModelPartBuilder.create().uv(22, 18).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.5F, 18.0F, -3.5F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(CowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 1.0F
        );
    }
}