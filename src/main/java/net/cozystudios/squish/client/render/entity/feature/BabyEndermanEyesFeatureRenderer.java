package net.cozystudios.squish.client.render.entity.feature;

import net.cozystudios.squish.client.model.baby.BabyEndermanModel;
import net.cozystudios.squish.registry.entity.BabyEndermanEntity;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

public class BabyEndermanEyesFeatureRenderer extends EyesFeatureRenderer<BabyEndermanEntity, BabyEndermanModel> {

    private static final RenderLayer EYES = RenderLayer.getEyes(SquishId.of("squish", "textures/entity/baby/baby_enderman_eyes.png"));

    public BabyEndermanEyesFeatureRenderer(FeatureRendererContext<BabyEndermanEntity, BabyEndermanModel> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return EYES;
    }
}
