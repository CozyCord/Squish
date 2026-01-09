package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.WolfEntity;

public class BabyWolfModel extends SinglePartEntityModel<WolfEntity> {

    private final ModelPart root;
    private final ModelPart bb_main;

    public BabyWolfModel(ModelPart root) {
        this.root = root;
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
    		ModelData modelData = new ModelData();
    		ModelPartData modelPartData = modelData.getRoot();
    		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, -9.0F, -5.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F))
    		.uv(17, 12).cuboid(-1.5F, -6.0F, -7.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(0, 5).cuboid(1.0F, -11.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
    		.uv(20, 5).cuboid(-3.0F, -11.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(-3.0F, -7.0F, -2.0F, 6.0F, 4.0F, 8.0F, new Dilation(0.0F))
    		.uv(0, 22).cuboid(-3.0F, -3.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(-3.0F, -3.0F, 4.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
    		.uv(8, 22).cuboid(1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(1.0F, -3.0F, 4.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

    		ModelPartData tail_r1 = bb_main.addChild("tail_r1", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 6.0F, -0.0436F, 0.0F, 0.0F));
    		return TexturedModelData.of(modelData, 32, 32);
    	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(WolfEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}