package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.entity.passive.RabbitEntity;

public class BabyRabbitEntityModel extends RabbitEntityModel<RabbitEntity> {

    public BabyRabbitEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 8).cuboid(-2.0F, -2.0F, -3.0F, 4.0F, 3.0F, 6.0F),
                ModelTransform.of(0.0F, 23.0F, 1.6F, -0.5236F, 0.0F, 0.0F)
        );

        root.addChild("tail",
                ModelPartBuilder.create()
                        .uv(0, 21).cuboid(-1.4F, -2.0268F, -1.0177F, 3.0F, 3.0F, 3.0F),
                ModelTransform.of(-0.1F, 20.8F, 3.6F, -0.5236F, 0.0F, 0.0F)
        );

        root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2.5F, -3.0F, -3.0F, 5.0F, 4.0F, 4.0F),
                ModelTransform.pivot(0.0F, 18.0F, -1.0F)
        );

        root.addChild("right_ear",
                ModelPartBuilder.create()
                        .uv(18, 0).cuboid(-1.0F, -3.5F, -0.5F, 2.0F, 4.0F, 1.0F),
                ModelTransform.pivot(-1.5F, 14.5F, -1.5F)
        );

        root.addChild("left_ear",
                ModelPartBuilder.create()
                        .uv(24, 0).cuboid(-1.0F, -3.5F, -0.5F, 2.0F, 4.0F, 1.0F),
                ModelTransform.pivot(1.5F, 14.5F, -1.5F)
        );

        root.addChild("nose",
                ModelPartBuilder.create()
                        .uv(0, 27).cuboid(-0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F),
                ModelTransform.pivot(0.0F, 18.0F, -1.0F)
        );

        root.addChild("left_front_leg",
                ModelPartBuilder.create()
                        .uv(18, 8).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
                ModelTransform.pivot(1.0F, 21.5F, -2.6F)
        );

        root.addChild("right_front_leg",
                ModelPartBuilder.create()
                        .uv(14, 8).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
                ModelTransform.pivot(-1.0F, 21.5F, -2.6F)
        );

        root.addChild("left_hind_leg",
                ModelPartBuilder.create()
                        .uv(8, 17).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F),
                ModelTransform.pivot(1.5F, 23.5F, 2.5F)
        );

        root.addChild("right_hind_leg",
                ModelPartBuilder.create()
                        .uv(12, 17).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F),
                ModelTransform.pivot(-1.5F, 23.5F, 2.5F)
        );

        root.addChild("left_haunch",
                ModelPartBuilder.create()
                        .uv(10, 17).cuboid(-2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 3.0F),
                ModelTransform.of(2.5F, 24.0F, 3.0F, 0.0F, -0.7854F, 0.0F)
        );

        root.addChild("right_haunch",
                ModelPartBuilder.create()
                        .uv(0, 17).cuboid(-2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 3.0F),
                ModelTransform.of(-2.0F, 24.0F, 1.6F, 0.0F, 0.7854F, 0.0F)
        );

        root.addChild("left_hind_foot",
                ModelPartBuilder.create()
                        .uv(22, 8).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F),
                ModelTransform.pivot(1.5F, 23.5F, 3.0F)
        );

        root.addChild("right_hind_foot",
                ModelPartBuilder.create()
                        .uv(22, 11).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F),
                ModelTransform.pivot(-1.5F, 23.5F, 3.0F)
        );

        return TexturedModelData.of(data, 32, 32);
    }
}