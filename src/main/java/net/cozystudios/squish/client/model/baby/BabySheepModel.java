package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.SheepEntity;

public class BabySheepModel extends SinglePartEntityModel<SheepEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;

    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart left_hind_leg;

    public BabySheepModel(ModelPart root) {
        this.root = root;

        this.head = root.getChild("head");
        this.body = root.getChild("body");

        this.right_front_leg = root.getChild("right_front_leg");
        this.right_hind_leg  = root.getChild("right_hind_leg");
        this.left_hind_leg   = root.getChild("left_hind_leg");
        this.left_front_leg  = root.getChild("left_front_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head",
                ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -1.0F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.5F, 16.0F, -5.5F));

        modelPartData.addChild("body",
                ModelPartBuilder.create().uv(0, 10).cuboid(-5.0F, -4.0F, -1.0F, 6.0F, 4.0F, 9.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.0F, 19.0F, -3.5F));

        modelPartData.addChild("right_front_leg",
                ModelPartBuilder.create().uv(8, 23).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, 24.0F, -2.5F));

        modelPartData.addChild("right_hind_leg",
                ModelPartBuilder.create().uv(0, 23).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-2.0F, 24.0F, 2.5F));

        modelPartData.addChild("left_hind_leg",
                ModelPartBuilder.create().uv(24, 12).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.0F, 24.0F, 2.5F));

        modelPartData.addChild("left_front_leg",
                ModelPartBuilder.create().uv(24, 5).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.0F, 24.0F, -2.5F));

        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(SheepEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        BabyAnimUtil.animateQuadrupedLegsClamped(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount,
                1.0F
        );
    }
}