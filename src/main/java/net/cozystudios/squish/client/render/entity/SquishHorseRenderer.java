package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyHorseModel;
import net.cozystudios.squish.util.SquishId;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.Identifier;

public class SquishHorseRenderer extends EntityRenderer<HorseEntity> {

    private final EntityRenderer<HorseEntity> adult;
    private final BabyRenderer baby;

    public SquishHorseRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.adult = new HorseEntityRenderer(ctx);
        this.baby = new BabyRenderer(ctx);
    }

    @Override
    public void render(HorseEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) baby.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        else adult.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(HorseEntity entity) {
        return entity.isBaby() ? baby.getTexture(entity) : adult.getTexture(entity);
    }

    private static Identifier babyFromVanillaHorseTexture(Identifier vanillaTexture) {
        String path = vanillaTexture.getPath();
        String prefix = "textures/entity/horse/";

        if (path.startsWith(prefix)) {
            String file = path.substring(prefix.length());

            
            if (file.startsWith("horse_skeleton.png")) {
                return SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_skeleton_baby.png");
            } else if (file.startsWith("horse_zombie.png")) {
                return SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_zombie_baby.png");
            }

            
            String baseName = file.replace(".png", "");
            return SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/" + baseName + "_baby.png");
        }

        return SquishId.of(Squish.MOD_ID, "textures/entity/baby/horse/horse_white_baby.png");
    }

    private final class BabyRenderer extends MobEntityRenderer<HorseEntity, BabyHorseModel> {
        BabyRenderer(EntityRendererFactory.Context ctx) {
            super(ctx, new BabyHorseModel(ctx.getPart(SquishModelLayers.BABY_HORSE)), 0.5f);
            this.addFeature(new net.cozystudios.squish.client.render.entity.feature.BabyHorseMarkingsFeatureRenderer(this));
        }

        @Override
        public Identifier getTexture(HorseEntity entity) {
            return babyFromVanillaHorseTexture(adult.getTexture(entity));
        }

        @Override
        protected void scale(HorseEntity entity, MatrixStack matrices, float amount) {
        }
    }
}
