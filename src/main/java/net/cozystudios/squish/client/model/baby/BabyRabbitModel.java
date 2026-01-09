package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.RabbitEntity;

public class BabyRabbitModel extends SinglePartEntityModel<RabbitEntity> {

    private final ModelPart root;
    private final ModelPart bb_main;

    public BabyRabbitModel(ModelPart root) {
        this.root = root;
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
    		ModelData modelData = new ModelData();
    		ModelPartData modelPartData = modelData.getRoot();
    		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -9.0F, -5.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F))
    		.uv(24, 0).cuboid(0.5F, -13.0F, -3.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
    		.uv(18, 0).cuboid(-2.5F, -13.0F, -3.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
    		.uv(18, 8).cuboid(0.5F, -3.0F, -2.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
    		.uv(14, 8).cuboid(-1.5F, -3.0F, -2.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

    		ModelPartData right_back_leg_r1 = bb_main.addChild("right_back_leg_r1", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

    		ModelPartData left_back_leg_r1 = bb_main.addChild("left_back_leg_r1", ModelPartBuilder.create().uv(10, 17).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

    		ModelPartData tail_r1 = bb_main.addChild("tail_r1", ModelPartBuilder.create().uv(0, 21).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.0F, 2.0F, -0.4102F, 0.0F, 0.0F));

    		ModelPartData body_r1 = bb_main.addChild("body_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-3.0F, -3.0F, -1.0F, 4.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -3.0F, -3.0F, -0.4102F, 0.0F, 0.0F));
    		return TexturedModelData.of(modelData, 32, 32);
    	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(RabbitEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}