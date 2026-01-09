package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.CowEntity;

public class BabyCowModel extends SinglePartEntityModel<CowEntity> {

    private final ModelPart root;
    private final ModelPart bb_main;

    public BabyCowModel(ModelPart root) {
        this.root = root;
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
    		ModelData modelData = new ModelData();
    		ModelPartData modelPartData = modelData.getRoot();
    		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 18).cuboid(-3.0F, -15.0F, -10.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F))
    		.uv(12, 29).cuboid(-2.0F, -12.0F, -11.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
    		.uv(4, 29).cuboid(3.0F, -16.0F, -9.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
    		.uv(8, 29).cuboid(-4.0F, -16.0F, -9.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
    		.uv(0, 0).cuboid(-4.0F, -12.0F, -6.0F, 8.0F, 6.0F, 12.0F, new Dilation(0.0F))
    		.uv(22, 18).cuboid(-4.0F, -6.0F, -5.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
    		.uv(22, 27).cuboid(-4.0F, -6.0F, 2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
    		.uv(34, 18).cuboid(1.0F, -6.0F, -5.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
    		.uv(34, 27).cuboid(1.0F, -6.0F, 2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    		return TexturedModelData.of(modelData, 64, 64);
    	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(CowEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}