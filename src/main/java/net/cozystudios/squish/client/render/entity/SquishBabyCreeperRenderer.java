package net.cozystudios.squish.client.render.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.client.model.baby.BabyCreeperModel;
import net.cozystudios.squish.entity.BabyCreeperEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class SquishBabyCreeperRenderer extends MobEntityRenderer<BabyCreeperEntity, BabyCreeperModel> {

    private static final Identifier TEXTURE =
            new Identifier(Squish.MOD_ID, "textures/entity/baby/creeper.png");

    private static final Vec3d MODEL_CENTER_OFFSET = new Vec3d(0, 0.0, 0);

    public SquishBabyCreeperRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BabyCreeperModel(ctx.getPart(SquishModelLayers.BABY_CREEPER)), 0.25f);
    }

    @Override
    public Identifier getTexture(BabyCreeperEntity entity) {
        return TEXTURE;
    }

    @Override
    public Vec3d getPositionOffset(BabyCreeperEntity entity, float tickDelta) {
        float yawDeg = MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw());
        float yawRad = yawDeg * ((float) Math.PI / 180F);

        double sin = MathHelper.sin(yawRad);
        double cos = MathHelper.cos(yawRad);

        double x = MODEL_CENTER_OFFSET.x * cos - MODEL_CENTER_OFFSET.z * sin;
        double z = MODEL_CENTER_OFFSET.x * sin + MODEL_CENTER_OFFSET.z * cos;

        return new Vec3d(x, MODEL_CENTER_OFFSET.y, z);
    }
}