package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.TurtleEntity;

public class BabyTurtleModel extends SinglePartEntityModel<TurtleEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart right_hind_leg;
    private final ModelPart right_upper_leg;
    private final ModelPart left_upper_leg;
    private final ModelPart left_hind_leg;

    public BabyTurtleModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.right_hind_leg = root.getChild("right_hind_leg");
        this.right_upper_leg = root.getChild("right_upper_leg");
        this.left_upper_leg = root.getChild("left_upper_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 6).cuboid(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 23.0F, -1.0F));

        
        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(8, 6).cuboid(-2.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 23.5F, 0.5F));

        modelPartData.addChild("right_upper_leg", ModelPartBuilder.create().uv(8, 6).cuboid(-2.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 23.5F, -2.5F));

        modelPartData.addChild("left_upper_leg", ModelPartBuilder.create().uv(8, 7).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 23.5F, -2.5F));

        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(8, 7).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 23.5F, 0.5F));

        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(TurtleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);
        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        
        double dx = entity.getX() - entity.prevX;
        double dz = entity.getZ() - entity.prevZ;
        boolean isMoving = (dx * dx + dz * dz) > 0.0000001;

        if (isMoving) {
            
            float paddle = net.minecraft.util.math.MathHelper.sin(ageInTicks * 0.4F) * 0.5F;

            
            this.right_upper_leg.yaw = paddle;
            this.left_upper_leg.yaw = -paddle;

            
            this.right_hind_leg.yaw = -paddle;
            this.left_hind_leg.yaw = paddle;
        }
    }
}
