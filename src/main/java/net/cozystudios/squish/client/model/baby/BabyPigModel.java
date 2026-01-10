package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.PigEntity;

public class BabyPigModel extends SinglePartEntityModel<PigEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;

    private final ModelPart right_hind_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;

    public BabyPigModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");

        this.right_hind_leg = this.body.getChild("right_hind_leg");
        this.left_hind_leg = this.body.getChild("left_hind_leg");
        this.right_front_leg = this.body.getChild("right_front_leg");
        this.left_front_leg = this.body.getChild("left_front_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head",
                ModelPartBuilder.create().uv(0, 15).cuboid(-3.5F, -3.0F, -6.0F, 7.0F, 6.0F, 6.0F, new Dilation(0.0F))
                        .uv(6, 27).cuboid(-1.5F, 0.0F, -7.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 17.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body",
                ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -6.0F, -1.0F, 7.0F, 6.0F, 9.0F, new Dilation(0.0F)),
                ModelTransform.pivot(2.5F, 22.0F, -2.0F));

        body.addChild("right_hind_leg",
                ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-5.0F, 0.0F, 7.0F));

        body.addChild("left_hind_leg",
                ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 7.0F));

        body.addChild("right_front_leg",
                ModelPartBuilder.create().uv(23, 4).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-5.0F, 0.0F, 0.0F));

        body.addChild("left_front_leg",
                ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(PigEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        BabyAnimUtil.animateQuadrupedLegs(
                this.right_front_leg, this.left_front_leg,
                this.right_hind_leg, this.left_hind_leg,
                limbSwing, limbSwingAmount, 0.9F
        );
    }
}