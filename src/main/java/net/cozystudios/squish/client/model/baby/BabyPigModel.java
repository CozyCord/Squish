package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.PigEntity;

public class BabyPigModel extends SinglePartEntityModel<PigEntity> {

    private final ModelPart root;
    private final ModelPart bb_main;

    public BabyPigModel(ModelPart root) {
        this.root = root;
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
    		ModelData modelData = new ModelData();
    		ModelPartData modelPartData = modelData.getRoot();
    		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 15).cuboid(-3.5F, -10.0F, -6.0F, 7.0F, 6.0F, 6.0F, new Dilation(0.0F))
    		.uv(6, 27).cuboid(-1.5F, -7.0F, -7.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(-3.5F, -8.0F, -3.0F, 7.0F, 6.0F, 9.0F, new Dilation(0.0F))
    		.uv(0, 4).cuboid(1.5F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(1.5F, -2.0F, 4.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(23, 4).cuboid(-3.5F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(-3.5F, -2.0F, 4.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    		return TexturedModelData.of(modelData, 32, 32);
    	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(PigEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}