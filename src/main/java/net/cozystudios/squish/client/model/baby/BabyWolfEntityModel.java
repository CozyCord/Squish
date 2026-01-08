package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;

public class BabyWolfEntityModel extends WolfEntityModel<WolfEntity> {

    public BabyWolfEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData head = root.addChild(
                "head",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 18.25F, -4.0F)
        );

        ModelPartData realHead = head.addChild(
                "real_head",
                ModelPartBuilder.create()
                        .uv(0, 12).cuboid(-3.0F, -3.25F, -3.0F, 6.0F, 5.0F, 5.0F)
                        .uv(17, 12).cuboid(-1.5F, -0.25F, -5.0F, 3.0F, 2.0F, 2.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );

        realHead.addChild(
                "right_ear",
                ModelPartBuilder.create().uv(0, 5).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F),
                ModelTransform.pivot(-2.0F, -4.25F, -0.5F)
        );

        realHead.addChild(
                "left_ear",
                ModelPartBuilder.create().uv(20, 5).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F),
                ModelTransform.pivot(2.0F, -4.25F, -0.5F)
        );

        root.addChild(
                "body",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 8.0F),
                ModelTransform.pivot(0.0F, 19.0F, 0.0F)
        );

        root.addChild(
                "upper_body",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 19.0F, 0.0F)
        );

        root.addChild(
                "right_hind_leg",
                ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                ModelTransform.pivot(-1.5F, 21.0F, 3.0F)
        );

        root.addChild(
                "left_hind_leg",
                ModelPartBuilder.create().uv(8, 22).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                ModelTransform.pivot(1.5F, 21.0F, 3.0F)
        );

        root.addChild(
                "right_front_leg",
                ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                ModelTransform.pivot(-1.5F, 21.0F, -3.0F)
        );

        root.addChild(
                "left_front_leg",
                ModelPartBuilder.create().uv(20, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                ModelTransform.pivot(1.5F, 21.0F, -3.0F)
        );

        ModelPartData tail = root.addChild(
                "tail",
                ModelPartBuilder.create(),
                ModelTransform.of(0.0F, 18.5F, 3.75F, 0.9599F, 0.0F, 0.0F)
        );

        tail.addChild(
                "real_tail",
                ModelPartBuilder.create()
                        .uv(18, 16).cuboid(-1.0F, -0.5F, -1.25F, 2.0F, 6.0F, 2.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 32, 32);
    }
}