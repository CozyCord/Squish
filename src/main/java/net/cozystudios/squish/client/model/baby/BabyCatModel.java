package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.CatEntity;

public class BabyCatModel extends SinglePartEntityModel<CatEntity> {

    private final ModelPart root;
    private final ModelPart bb_main;

    public BabyCatModel(ModelPart root) {
        this.root = root;
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
    		ModelData modelData = new ModelData();
    		ModelPartData modelPartData = modelData.getRoot();
    		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -7.0F, -6.5F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F))
    		.uv(0, 8).cuboid(-1.5F, -5.0F, -3.5F, 4.0F, 3.0F, 7.0F, new Dilation(0.0F))
    		.uv(18, 3).cuboid(-1.0F, -5.0F, -7.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
    		.uv(18, 22).cuboid(1.0F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(18, 18).cuboid(1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(12, 22).cuboid(-1.0F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(12, 18).cuboid(-1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(24, 0).cuboid(1.5F, -8.0F, -4.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
    		.uv(18, 0).cuboid(-1.5F, -8.0F, -4.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

    		ModelPartData tail_r1 = bb_main.addChild("tail_r1", ModelPartBuilder.create().uv(0, 18).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, 4.0F, -0.3927F, 0.0F, 0.0F));
    		return TexturedModelData.of(modelData, 32, 32);
    	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(CatEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}