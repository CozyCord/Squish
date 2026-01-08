package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyOcelotEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.OcelotEntityRenderer;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.util.Identifier;

public class BabyAwareOcelotEntityRenderer extends OcelotEntityRenderer {

    private final OcelotEntityModel<OcelotEntity> adultModel;
    private final BabyOcelotEntityModel babyModel;

    private final float adultShadow;
    private final float babyShadow;

    public BabyAwareOcelotEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adultModel = this.model;
        this.babyModel = new BabyOcelotEntityModel(ctx.getPart(SquishModelLayers.BABY_OCELOT));

        this.adultShadow = this.shadowRadius;
        this.babyShadow = Math.max(0.2f, this.adultShadow * 0.55f);
    }

    @Override
    public Identifier getTexture(OcelotEntity entity) {
        if (!entity.isBaby()) return super.getTexture(entity);
        return BabyTextureUtil.squishBaby("ocelot_baby.png");
    }

    @Override
    public void render(OcelotEntity entity, float yaw, float tickDelta, MatrixStack matrices,
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