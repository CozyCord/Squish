package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.AxolotlEntity;

public class BabyAxolotlModel extends SinglePartEntityModel<AxolotlEntity> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart left_upper_fin;
    private final ModelPart left_hind_fin;
    private final ModelPart right_hind_fin;
    private final ModelPart right_upper_fin;

    public BabyAxolotlModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.left_upper_fin = root.getChild("left_upper_fin");
        this.left_hind_fin = root.getChild("left_hind_fin");
        this.right_hind_fin = root.getChild("right_hind_fin");
        this.right_upper_fin = root.getChild("right_upper_fin");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 8).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(20, 4).cuboid(-6.0F, -1.0F, -1.0F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 9).cuboid(3.0F, -1.0F, -1.0F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-3.0F, -3.0F, -1.0F, 6.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, -4.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -2.5F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(1.0F, -1.0F, -2.5F, 0.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 22.0F, -0.5F));

        ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(10, 9).cuboid(1.0F, -1.5F, -0.5F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 22.5F, 2.5F));

        
        ModelPartData left_upper_fin = modelPartData.addChild("left_upper_fin", ModelPartBuilder.create().uv(21, 14).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 23.5F, -1.5F));

        ModelPartData left_hind_fin = modelPartData.addChild("left_hind_fin", ModelPartBuilder.create().uv(21, 14).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 23.5F, 1.5F));

        ModelPartData right_hind_fin = modelPartData.addChild("right_hind_fin", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 23.5F, 1.5F));

        right_hind_fin.addChild("right_hind_fin_r1", ModelPartBuilder.create().uv(21, 14).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData right_upper_fin = modelPartData.addChild("right_upper_fin", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 23.5F, -1.5F));

        right_upper_fin.addChild("right_upper_fin_r1", ModelPartBuilder.create().uv(21, 14).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 0.01F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(AxolotlEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);
        BabyAnimUtil.applyHeadRotation(this.head, netHeadYaw, headPitch);

        
        double dx = entity.getX() - entity.prevX;
        double dz = entity.getZ() - entity.prevZ;
        boolean isMoving = (dx * dx + dz * dz) > 0.0000001;

        if (isMoving) {
            
            float tailWag = net.minecraft.util.math.MathHelper.sin(ageInTicks * 0.4F) * 0.4F;
            this.tail.yaw = tailWag;

            
            float paddle = net.minecraft.util.math.MathHelper.sin(ageInTicks * 0.25F) * 0.5F;

            
            this.left_upper_fin.yaw = paddle;
            this.right_upper_fin.yaw = -paddle;

            
            this.left_hind_fin.yaw = -paddle;
            this.right_hind_fin.yaw = paddle;
        }
    }
}
