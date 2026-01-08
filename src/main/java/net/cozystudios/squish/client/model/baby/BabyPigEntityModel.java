package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.passive.PigEntity;

public class BabyPigEntityModel extends PigEntityModel<PigEntity> {

    public BabyPigEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-3.5F, -3.0F, -4.5F, 7.0F, 6.0F, 9.0F),
                ModelTransform.pivot(0.0F, 19.0F, 0.5F)
        );

        root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 15).cuboid(-3.51F, -5.0F, -5.0F, 7.0F, 6.0F, 6.0F)
                        .uv(6, 27).cuboid(-1.5F, -2.0F, -6.0F, 3.0F, 2.0F, 1.0F),
                ModelTransform.pivot(0.0F, 19.0F, -2.0F)
        );

        root.addChild("left_front_leg",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                ModelTransform.pivot(2.5F, 22.0F, -3.0F)
        );

        root.addChild("right_front_leg",
                ModelPartBuilder.create()
                        .uv(23, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                ModelTransform.pivot(-2.5F, 22.0F, -3.0F)
        );

        root.addChild("left_hind_leg",
                ModelPartBuilder.create()
                        .uv(0, 4).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                ModelTransform.pivot(2.5F, 22.0F, 4.0F)
        );

        root.addChild("right_hind_leg",
                ModelPartBuilder.create()
                        .uv(23, 4).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                ModelTransform.pivot(-2.5F, 22.0F, 4.0F)
        );

        return TexturedModelData.of(data, 32, 32);
    }
}