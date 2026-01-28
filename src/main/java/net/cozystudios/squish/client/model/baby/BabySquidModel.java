package net.cozystudios.squish.client.model.baby;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.math.MathHelper;

public class BabySquidModel<T extends Entity> extends SinglePartEntityModel<T> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart tentacle_one_r1;
    private final ModelPart tentacle_two_r1;
    private final ModelPart tentacle_three_r1;
    private final ModelPart tentacle_four_r1;
    private final ModelPart tentacle_five_r1;
    private final ModelPart tentacle_six_r1;
    private final ModelPart tentacle_seven_r1;
    private final ModelPart tentacle_eight_r1;

    private static final float BASE_PITCH = -1.5708F;

    public BabySquidModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.tentacle_one_r1 = this.body.getChild("tentacle_one").getChild("tentacle_one_r1");
        this.tentacle_two_r1 = this.body.getChild("tentacle_two").getChild("tentacle_two_r1");
        this.tentacle_three_r1 = this.body.getChild("tentacle_three").getChild("tentacle_three_r1");
        this.tentacle_four_r1 = this.body.getChild("tentacle_four").getChild("tentacle_four_r1");
        this.tentacle_five_r1 = this.body.getChild("tentacle_five").getChild("tentacle_five_r1");
        this.tentacle_six_r1 = this.body.getChild("tentacle_six").getChild("tentacle_six_r1");
        this.tentacle_seven_r1 = this.body.getChild("tentacle_seven").getChild("tentacle_seven_r1");
        this.tentacle_eight_r1 = this.body.getChild("tentacle_eight").getChild("tentacle_eight_r1");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 13.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData tentacle_one = body.addChild("tentacle_one", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.9F, 5.0F));
        ModelPartData tentacle_one_r1 = tentacle_one.addChild("tentacle_one_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData tentacle_two = body.addChild("tentacle_two", ModelPartBuilder.create(), ModelTransform.pivot(2.1707F, 1.9991F, 5.0F));
        ModelPartData tentacle_two_r1 = tentacle_two.addChild("tentacle_two_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -0.99F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -0.7854F));

        ModelPartData tentacle_three = body.addChild("tentacle_three", ModelPartBuilder.create(), ModelTransform.pivot(2.9F, -0.1F, 5.0F));
        ModelPartData tentacle_three_r1 = tentacle_three.addChild("tentacle_three_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData tentacle_four = body.addChild("tentacle_four", ModelPartBuilder.create(), ModelTransform.pivot(2.0293F, -2.2293F, 5.0F));
        ModelPartData tentacle_four_r1 = tentacle_four.addChild("tentacle_four_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -0.99F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -2.3562F));

        ModelPartData tentacle_five = body.addChild("tentacle_five", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.9F, 5.0F));
        ModelPartData tentacle_five_r1 = tentacle_five.addChild("tentacle_five_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData tentacle_six = body.addChild("tentacle_six", ModelPartBuilder.create(), ModelTransform.pivot(-2.0719F, -2.2435F, 5.0F));
        ModelPartData tentacle_six_r1 = tentacle_six.addChild("tentacle_six_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -0.99F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -0.7854F));

        ModelPartData tentacle_seven = body.addChild("tentacle_seven", ModelPartBuilder.create(), ModelTransform.pivot(-2.9F, -0.1F, 5.0F));
        ModelPartData tentacle_seven_r1 = tentacle_seven.addChild("tentacle_seven_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData tentacle_eight = body.addChild("tentacle_eight", ModelPartBuilder.create(), ModelTransform.pivot(-2.2134F, 2.0133F, 5.0F));
        ModelPartData tentacle_eight_r1 = tentacle_eight.addChild("tentacle_eight_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -6.0F, -0.99F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -2.3562F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        BabyAnimUtil.reset(this.root);

        float tentacleAngle = 0.0F;
        if (entity instanceof SquidEntity squid) {
            tentacleAngle = MathHelper.lerp(limbSwingAmount, squid.prevTentacleAngle, squid.tentacleAngle);
        }

        this.tentacle_one_r1.pitch = BASE_PITCH - tentacleAngle;
        this.tentacle_two_r1.pitch = BASE_PITCH - tentacleAngle;
        this.tentacle_three_r1.pitch = BASE_PITCH - tentacleAngle;
        this.tentacle_four_r1.pitch = BASE_PITCH - tentacleAngle;
        this.tentacle_five_r1.pitch = BASE_PITCH + tentacleAngle;
        this.tentacle_six_r1.pitch = BASE_PITCH + tentacleAngle;
        this.tentacle_seven_r1.pitch = BASE_PITCH + tentacleAngle;
        this.tentacle_eight_r1.pitch = BASE_PITCH + tentacleAngle;
    }
}
