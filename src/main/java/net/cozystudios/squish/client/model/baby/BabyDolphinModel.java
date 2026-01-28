package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.util.math.MathHelper;

public class BabyDolphinModel extends SinglePartEntityModel<DolphinEntity> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart fin;
    private final ModelPart left_fin;
    private final ModelPart right_fin;
    private final ModelPart head;

    public BabyDolphinModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        
        this.tail = this.body.getChild("tail");
        this.fin = this.body.getChild("fin");
        this.left_fin = this.body.getChild("left_fin");
        this.right_fin = this.body.getChild("right_fin");
        this.head = this.body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(20, 0).cuboid(-3.0F, -2.5F, -4.0F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.5F, 0.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(22, 13).cuboid(-4.0F, -0.5F, 5.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 4.0F));

        ModelPartData fin = body.addChild("fin", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, -1.5F, -1.5F));
        ModelPartData fin_r1 = fin.addChild("fin_r1", ModelPartBuilder.create().uv(42, 0).cuboid(0.0F, -1.701F, -0.25F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

        ModelPartData left_fin = body.addChild("left_fin", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 1.0F, -2.5F));
        ModelPartData left_fin_r1 = left_fin.addChild("left_fin_r1", ModelPartBuilder.create().uv(34, 18).cuboid(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData right_fin = body.addChild("right_fin", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 1.0F, -2.5F));
        ModelPartData right_fin_r1 = right_fin.addChild("right_fin_r1", ModelPartBuilder.create().uv(48, 18).cuboid(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, 0.0F, -6.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.0F, -3.0F, -4.0F, 6.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, -4.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(DolphinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        
        
        this.body.pitch = headPitch * ((float)Math.PI / 180F);
        this.body.yaw = netHeadYaw * ((float)Math.PI / 180F);

        
        boolean isMoving = entity.getVelocity().lengthSquared() > 0.01;

        if (isMoving) {
            
            this.body.pitch += -0.05F - 0.05F * MathHelper.cos(ageInTicks * 0.3F);
            
            this.tail.pitch = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
        }

        
        float finFlap = MathHelper.sin(ageInTicks * 0.5F) * 0.1F;
        this.left_fin.roll = -0.1F + finFlap;
        this.right_fin.roll = 0.1F - finFlap;
    }
}
