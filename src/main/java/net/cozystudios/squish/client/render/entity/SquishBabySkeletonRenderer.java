package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabySkeletonModel;
import net.cozystudios.squish.registry.entity.BabySkeletonEntity;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SquishBabySkeletonRenderer extends MobEntityRenderer<BabySkeletonEntity, BabySkeletonModel> {

    private static final Identifier TEXTURE = SquishId.of("squish", "textures/entity/baby/baby_skeleton.png");

    public SquishBabySkeletonRenderer(EntityRendererFactory.Context context) {
        super(context, new BabySkeletonModel(context.getPart(SquishModelLayers.BABY_SKELETON)), 0.3f);
    }

    @Override
    public Identifier getTexture(BabySkeletonEntity entity) {
        return TEXTURE;
    }
}
