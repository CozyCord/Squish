package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.HorseEntity;

public class BabyHorseModel extends SinglePartEntityModel<HorseEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_hind_leg;
    private final ModelPart left_upper_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart right_upper_leg;

    public BabyHorseModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.left_upper_leg = root.getChild("left_upper_leg");
        this.right_hind_leg = root.getChild("right_hind_leg");
        this.right_upper_leg = root.getChild("right_upper_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -12.0F, -9.0F, 6.0F, 4.0F, 9.0F, new Dilation(0.0F))
                .uv(30, 0).cuboid(-2.0F, -8.0F, -4.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 13.0F, -5.0F, 0.5236F, 0.0F, 0.0F));

        head.addChild("right_ear_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.109F, -2.5489F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.7F, -12.0F, -1.5F, 0.0F, 0.0F, -0.3142F));

        head.addChild("left_ear_r1", ModelPartBuilder.create().uv(0, 4).cuboid(-1.0748F, -2.5159F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.7F, -12.0F, -1.5F, 0.0F, 0.0F, 0.2531F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 13).cuboid(-4.0F, -3.5F, -7.0F, 8.0F, 7.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.5F, -1.0F));

        body.addChild("tail_r1", ModelPartBuilder.create().uv(24, 34).cuboid(-1.5F, -0.5251F, 0.7678F, 3.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, 6.0F, -0.7854F, 0.0F, 0.0F));

        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(12, 46).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.4F, 16.0F, 4.4F));

        modelPartData.addChild("left_upper_leg", ModelPartBuilder.create().uv(12, 34).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.4F, 16.0F, -6.4F));

        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 46).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.4F, 16.0F, 4.4F));

        modelPartData.addChild("right_upper_leg", ModelPartBuilder.create().uv(0, 34).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.4F, 16.0F, -6.4F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(HorseEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);
        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_upper_leg, this.left_upper_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 1.0F
        );
    }
}
