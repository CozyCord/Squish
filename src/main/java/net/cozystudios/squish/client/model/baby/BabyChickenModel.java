package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.math.MathHelper;

public class BabyChickenModel extends SinglePartEntityModel<ChickenEntity> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart left_wing;
    private final ModelPart right_wing;

    private final ModelPart rightleg;
    private final ModelPart leftleg;

    public BabyChickenModel(ModelPart root) {
        this.root = root;

        this.body = root.getChild("body");
        this.left_wing = this.body.getChild("left_wing");
        this.right_wing = this.body.getChild("right_wing");

        this.rightleg = root.getChild("rightleg");
        this.leftleg = root.getChild("leftleg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData body = modelPartData.addChild("body",
                ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 20.0F, 0.0F));

        ModelPartData left_wing = body.addChild("left_wing", ModelPartBuilder.create(), ModelTransform.pivot(1.8F, -0.2F, 0.0F));
        left_wing.addChild("left_wing_r1",
                ModelPartBuilder.create().uv(8, 8).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)),
                ModelTransform.of(-0.5F, 0.5F, -1.0F, -1.5708F, 0.0F, 0.7854F));

        ModelPartData right_wing = body.addChild("right_wing", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -0.5F, 0.0F));
        right_wing.addChild("right_wing_r1",
                ModelPartBuilder.create().uv(6, 8).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 1.5F, -1.0F, -1.5708F, 0.0F, -0.7854F));

        body.addChild("beak",
                ModelPartBuilder.create().uv(10, 8).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 1.0F, -2.0F));

        modelPartData.addChild("rightleg",
                ModelPartBuilder.create().uv(0, 2).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                        .uv(0, 0).cuboid(-0.5F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-1.0F, 22.0F, 0.0F));

        modelPartData.addChild("leftleg",
                ModelPartBuilder.create().uv(0, 2).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                        .uv(0, 1).cuboid(-0.5F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.0F, 22.0F, 0.0F));

        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(ChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        BabyAnimUtil.applyHeadRotation(this.body, netHeadYaw, headPitch);

        BabyAnimUtil.animateChickenLegs(this.rightleg, this.leftleg, limbSwing, limbSwingAmount);

        if (!entity.isOnGround()) {
            float flap = MathHelper.sin(ageInTicks * 1.2F) * 0.9F;
            this.left_wing.roll = flap;
            this.right_wing.roll = -flap;
        } else {
            this.left_wing.roll = 0.0F;
            this.right_wing.roll = 0.0F;
        }
    }
}