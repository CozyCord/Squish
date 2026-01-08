package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.entity.passive.ChickenEntity;

public class BabyChickenEntityModel extends ChickenEntityModel<ChickenEntity> {

    public BabyChickenEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2.0F, -2.25F, -0.75F, 4.0F, 4.0F, 4.0F)
                        .uv(10, 8).cuboid(-1.0F, -0.25F, -1.75F, 2.0F, 1.0F, 1.0F),
                ModelTransform.pivot(0.0F, 20.25F, -1.25F)
        );

        root.addChild("left_leg",
                ModelPartBuilder.create()
                        .uv(2, 2).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                        .uv(0, 1).cuboid(-0.5F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F),
                ModelTransform.pivot(1.0F, 22.0F, 0.5F)
        );

        root.addChild("right_leg",
                ModelPartBuilder.create()
                        .uv(0, 2).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                        .uv(0, 0).cuboid(-0.5F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F),
                ModelTransform.pivot(-1.0F, 22.0F, 0.5F)
        );

        root.addChild("right_wing",
                ModelPartBuilder.create()
                        .uv(6, 8).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F),
                ModelTransform.pivot(2.0F, 20.0F, 0.0F)
        );

        root.addChild("left_wing",
                ModelPartBuilder.create()
                        .uv(4, 8).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F),
                ModelTransform.pivot(-2.0F, 20.0F, 0.0F)
        );

        root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 8).cuboid(-1.5F, -3.0F, -2.5F, 3.0F, 3.0F, 3.0F),
                ModelTransform.pivot(0.0F, 19.0F, -2.0F)
        );

        root.addChild("beak",
                ModelPartBuilder.create()
                        .uv(14, 0).cuboid(-0.5F, -1.0F, -3.5F, 1.0F, 1.0F, 1.0F),
                ModelTransform.pivot(0.0F, 18.0F, -2.0F)
        );

        root.addChild("red_thing",
                ModelPartBuilder.create()
                        .uv(14, 2).cuboid(-0.5F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F),
                ModelTransform.pivot(0.0F, 18.0F, -2.0F)
        );

        return TexturedModelData.of(data, 16, 16);
    }
}