package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyIronGolemModel;
import net.cozystudios.squish.registry.entity.BabyIronGolemEntity;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SquishBabyIronGolemRenderer
        extends MobEntityRenderer<BabyIronGolemEntity, BabyIronGolemModel> {

    private static final Identifier TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/iron_golem.png");

    public SquishBabyIronGolemRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BabyIronGolemModel(ctx.getPart(SquishModelLayers.BABY_IRON_GOLEM)), 0.30f);

        this.addFeature(new SquishBabyIronGolemPoppyFeature(this));
    }

    @Override
    public Identifier getTexture(BabyIronGolemEntity entity) {
        return TEXTURE;
    }
}
