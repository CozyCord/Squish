package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.ChickenEntity;

public class BabyChickenModel extends SinglePartEntityModel<ChickenEntity> {

    private final ModelPart root;
    private final ModelPart bb_main;

    public BabyChickenModel(ModelPart root) {
        this.root = root;
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
    		ModelData modelData = new ModelData();
    		ModelPartData modelPartData = modelData.getRoot();
    		ModelPartData leftleg = modelPartData.addChild("leftleg", ModelPartBuilder.create().uv(0, 2).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
    		.uv(0, 1).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 24.0F, 0.0F));

    		ModelPartData rightleg = modelPartData.addChild("rightleg", ModelPartBuilder.create().uv(0, 2).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 24.0F, 0.0F));

    		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
    		.uv(10, 8).cuboid(-1.0F, -4.0F, -3.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

    		ModelPartData left_wing_r1 = bb_main.addChild("left_wing_r1", ModelPartBuilder.create().uv(8, 8).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.3F, -3.7F, -1.0F, -1.5708F, 0.0F, 0.7854F));

    		ModelPartData right_wing_r1 = bb_main.addChild("right_wing_r1", ModelPartBuilder.create().uv(6, 8).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -3.0F, -1.0F, -1.5708F, 0.0F, -0.7854F));
    		return TexturedModelData.of(modelData, 16, 16);
    	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(ChickenEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}