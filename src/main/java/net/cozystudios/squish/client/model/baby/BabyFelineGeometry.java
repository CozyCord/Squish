package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;

public final class BabyFelineGeometry {
    private BabyFelineGeometry() {}

    public static TexturedModelData createTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-2.5F, -3.0F, -2.875F, 5.0F, 4.0F, 4.0F)
                        .uv(18, 0).cuboid(-2.0F, -4.0F, -0.875F, 1.0F, 1.0F, 2.0F)
                        .uv(24, 0).cuboid(1.0F, -4.0F, -0.875F, 1.0F, 1.0F, 2.0F)
                        .uv(18, 3).cuboid(-1.5F, -1.0F, -3.875F, 3.0F, 2.0F, 1.0F),
                ModelTransform.pivot(0.0F, 20.0F, -3.125F)
        );

        root.addChild("left_front_leg",
                ModelPartBuilder.create()
                        .uv(18, 18).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
                ModelTransform.pivot(1.0F, 22.0F, -1.5F)
        );

        root.addChild("right_front_leg",
                ModelPartBuilder.create()
                        .uv(12, 18).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
                ModelTransform.pivot(-1.0F, 22.0F, -1.5F)
        );

        root.addChild("left_hind_leg",
                ModelPartBuilder.create()
                        .uv(18, 22).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
                ModelTransform.pivot(1.0F, 22.0F, 2.5F)
        );

        root.addChild("right_hind_leg",
                ModelPartBuilder.create()
                        .uv(12, 22).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
                ModelTransform.pivot(-1.0F, 22.0F, 2.5F)
        );

        root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 8).cuboid(-2.0F, -1.5F, -3.5F, 4.0F, 3.0F, 7.0F),
                ModelTransform.pivot(0.0F, 20.5F, 0.5F)
        );

        root.addChild("tail1",
                ModelPartBuilder.create()
                        .uv(0, 18).cuboid(-0.5F, -0.107F, 0.0849F, 1.0F, 1.0F, 5.0F),
                ModelTransform.of(0.0F, 19.107F, 3.9151F, -0.567232F, 0.0F, 0.0F)
        );

        root.addChild("tail2", ModelPartBuilder.create(), ModelTransform.NONE);

        return TexturedModelData.of(data, 32, 32);
    }
}