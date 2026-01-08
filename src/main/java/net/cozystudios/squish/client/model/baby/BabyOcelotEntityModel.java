package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.entity.passive.OcelotEntity;

public class BabyOcelotEntityModel extends OcelotEntityModel<OcelotEntity> {
    public BabyOcelotEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        return BabyFelineGeometry.createTexturedModelData();
    }
}