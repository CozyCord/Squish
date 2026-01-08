package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.entity.passive.CatEntity;

public class BabyCatEntityModel extends CatEntityModel<CatEntity> {
    public BabyCatEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        return BabyFelineGeometry.createTexturedModelData();
    }
}