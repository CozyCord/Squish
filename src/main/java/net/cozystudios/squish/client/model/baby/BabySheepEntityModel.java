package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.entity.passive.SheepEntity;

public class BabySheepEntityModel extends SheepEntityModel<SheepEntity> {

    public BabySheepEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 10).cuboid(-3.0F, -2.0F, -4.5F, 6.0F, 4.0F, 9.0F),
                ModelTransform.pivot(0.0F, 17.0F, 0.5F)
        );

        root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2.5F, -4.5F, -3.5F, 5.0F, 5.0F, 5.0F),
                ModelTransform.pivot(0.0F, 15.5F, -2.5F)
        );

        root.addChild("right_hind_leg",
                ModelPartBuilder.create()
                        .uv(0, 23).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                ModelTransform.pivot(-2.0F, 19.0F, 3.0F)
        );

        root.addChild("left_hind_leg",
                ModelPartBuilder.create()
                        .uv(24, 12).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                ModelTransform.pivot(2.0F, 19.0F, 3.0F)
        );

        root.addChild("right_front_leg",
                ModelPartBuilder.create()
                        .uv(8, 23).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                ModelTransform.pivot(-2.0F, 19.0F, -2.0F)
        );

        root.addChild("left_front_leg",
                ModelPartBuilder.create()
                        .uv(24, 5).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                ModelTransform.pivot(2.0F, 19.0F, -2.0F)
        );

        root.addChild("wool", ModelPartBuilder.create(), ModelTransform.NONE);

        return TexturedModelData.of(data, 64, 32);
    }
}