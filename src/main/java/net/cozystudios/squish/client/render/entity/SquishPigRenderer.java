package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.util.SquishId;
import net.cozystudios.squish.client.model.baby.BabyPigModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.Identifier;

public class SquishPigRenderer extends MobEntityRenderer<PigEntity, net.minecraft.client.render.entity.model.EntityModel<PigEntity>> {

    private static final Identifier BABY_TEXTURE =
            SquishId.of(Squish.MOD_ID, "textures/entity/baby/pig.png");

    private final PigEntityModel<PigEntity> adultModel;
    private final BabyPigModel babyModel;

    private final PigEntityRenderer vanillaTextureHelper;

    public SquishPigRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, null, 0.5f);

        this.adultModel = new PigEntityModel<>(ctx.getPart(EntityModelLayers.PIG));
        this.babyModel = new BabyPigModel(ctx.getPart(SquishModelLayers.BABY_PIG));
        this.vanillaTextureHelper = new PigEntityRenderer(ctx);

        this.model = this.adultModel;
    }

    @Override
    public Identifier getTexture(PigEntity entity) {
        if (entity.isBaby()) {
            return BABY_TEXTURE;
        }
        return vanillaTextureHelper.getTexture(entity);
    }

    @Override
    public void render(PigEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       net.minecraft.client.render.VertexConsumerProvider vertexConsumers, int light) {

        var old = this.model;
        this.model = entity.isBaby() ? this.babyModel : this.adultModel;

        boolean oldChild = false;
        if (this.model instanceof net.minecraft.client.render.entity.model.AnimalModel<?> am) {
            oldChild = am.child;
            am.child = false;
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

        if (this.model instanceof net.minecraft.client.render.entity.model.AnimalModel<?> am) {
            am.child = oldChild;
        }

        this.model = old;
    }

    @Override
    protected void scale(PigEntity entity, MatrixStack matrices, float amount) {
    }
}