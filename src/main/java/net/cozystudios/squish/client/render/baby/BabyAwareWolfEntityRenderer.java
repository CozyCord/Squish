package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyWolfEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class BabyAwareWolfEntityRenderer extends WolfEntityRenderer {

    private final WolfEntityModel<WolfEntity> adultModel;
    private final BabyWolfEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareWolfEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyWolfEntityModel(ctx.getPart(SquishModelLayers.BABY_WOLF));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.25f, this.adultShadow * 0.55f);

        this.features.removeIf(f -> f.getClass().getName().toLowerCase().contains("collar"));
        this.addFeature(new BabyAwareWolfCollarFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(WolfEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);

        BabyTextureUtil.TempBand band = BabyTextureUtil.biomeBand(entity);
        boolean angry = entity.hasAngerTime();
        boolean tame = entity.isTamed();

        String[] cold = new String[] { "wolf_snowy", "wolf_striped", "wolf_spotted" };
        String[] warm = new String[] { "wolf_rusty", "wolf_woods", "wolf_chestnut" };
        String[] temp = new String[] { "wolf_ashen", "wolf_black", "wolf_spotted", "wolf_striped" };

        String[] pickFrom = switch (band) {
            case COLD -> cold;
            case WARM -> warm;
            case TEMPERATE -> temp;
        };

        String base = pickFrom[BabyTextureUtil.stableIndex(entity, pickFrom.length)];

        if (angry) {
            return BabyTextureUtil.squishBaby(base + "_angry_baby.png");
        }
        if (tame) {
            return BabyTextureUtil.squishBaby(base + "_tame_baby.png");
        }
        return BabyTextureUtil.squishBaby(base + "_baby.png");
    }

    @Override
    public void render(WolfEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {

        if (entity.isBaby()) {
            this.model = this.babyModel;
            this.shadowRadius = this.babyShadow;
        } else {
            this.model = this.adultModel;
            this.shadowRadius = this.adultShadow;
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}