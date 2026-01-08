package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.passive.CowEntity;

public class BabyCowEntityModel extends CowEntityModel<CowEntity> {

    public BabyCowEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 18)
                        .cuboid(-3.0F, -4.569F, -4.8333F, 6.0F, 6.0F, 5.0F)
                        .uv(8, 29)
                        .cuboid(3.0F, -5.569F, -3.8333F, 1.0F, 2.0F, 1.0F)
                        .uv(4, 29)
                        .mirrored()
                        .cuboid(-4.0F, -5.569F, -3.8333F, 1.0F, 2.0F, 1.0F)
                        .mirrored(false)
                        .uv(12, 29)
                        .cuboid(-2.0F, -1.569F, -5.8333F, 4.0F, 3.0F, 1.0F),
                ModelTransform.pivot(0.0F, 13.569F, -5.1667F)
        );

        root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-7.0F, -7.0F, -1.0F, 8.0F, 6.0F, 12.0F),
                ModelTransform.pivot(3.0F, 19.0F, -5.0F)
        );

        root.addChild("right_front_leg",
                ModelPartBuilder.create()
                        .uv(22, 18)
                        .cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F),
                ModelTransform.pivot(-2.5F, 18.0F, -3.5F)
        );

        root.addChild("left_front_leg",
                ModelPartBuilder.create()
                        .uv(34, 18)
                        .cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F),
                ModelTransform.pivot(2.5F, 18.0F, -3.5F)
        );

        root.addChild("right_hind_leg",
                ModelPartBuilder.create()
                        .uv(22, 27)
                        .cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F),
                ModelTransform.pivot(-2.5F, 18.0F, 3.5F)
        );

        root.addChild("left_hind_leg",
                ModelPartBuilder.create()
                        .uv(34, 27)
                        .cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F),
                ModelTransform.pivot(2.5F, 18.0F, 3.5F)
        );

        return TexturedModelData.of(modelData, 64, 64);
    }
}