package com.blakebr0.pickletweaks.feature.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

// TODO: 1.17 figure out and implement night vision goggles model
public class NightVisionGogglesModel extends HumanoidModel<LivingEntity> {
//    private final ModelPart main;
//    private final ModelPart lens;
//    private final ModelPart frame;

    public NightVisionGogglesModel() {
        super(null);
//        super(part, RenderType::entityTranslucent);

//        this.main = new ModelPart(this, 0, 7);
//        this.main.setPos(0.0F, 0.0F, 0.0F);
//        this.main.addBox(-8.0F, -7.0F, 1.0F, 1, 1, 6, 0.0F);
//        this.main.addBox(7.0F, -7.0F, 1.0F, 1, 1, 6, 0.0F);
//        this.main.addBox(-7.0F, -7.0F, 7.0F, 14, 1, 1, 0.0F);
//        this.main.addBox(-8.0F, -7.0F, -6.0F, 1, 2, 7, 0.0F);
//        this.main.addBox(7.0F, -7.0F, -6.0F, 1, 2, 7, 0.0F);
//
//        this.lens = new ModelPart(this);
//        this.lens.setPos(0.0F, 0F, 0.0F);
//
//        ModelPart left = new ModelPart(this, 6, 0);
//        left.setPos(0.0F, 0.0F, 0.0F);
//        left.addBox(-6.0F, -9.0F, -7.0F, 4, 5, 1, 0.0F);
//        left.addBox(-2.0F, -8.0F, -7.0F, 1, 3, 1, 0.0F);
//        left.addBox(-7.0F, -8.0F, -7.0F, 1, 4, 1, 0.0F);
//        left.addBox(-6.0F, -4.0F, -7.0F, 3, 1, 1, 0.0F);
//
//        this.lens.addChild(left);
//
//        ModelPart right = new ModelPart(this, 6, 0);
//        right.setPos(0.0F, 0.0F, 0.0F);
//        right.addBox(6.0F, -8.0F, -7.0F, 1, 4, 1, 0.0F);
//        right.addBox(1.0F, -8.0F, -7.0F, 1, 3, 1, 0.0F);
//        right.addBox(2.0F, -9.0F, -7.0F, 4, 5, 1, 0.0F);
//        right.addBox(3.0F, -4.0F, -7.0F, 3, 1, 1, 0.0F);
//
//        this.lens.addChild(right);
//
//        this.frame = new ModelPart(this, 0, 13);
//        this.frame.setPos(0.0F, 0.0F, 0.0F);
//        this.frame.addBox(-1.0F, -8.0F, -8.0F, 2, 3, 2, 0.0F);
//        this.frame.addBox(-2.0F, -9.0F, -8.0F, 4, 1, 2, 0.0F);
//        this.frame.addBox(-2.0F, -5.0F, -8.0F, 4, 1, 2, 0.0F);
//        this.frame.addBox(2.0F, -10.0F, -8.0F, 4, 1, 2, 0.0F);
//        this.frame.addBox(3.0F, -3.0F, -8.0F, 3, 1, 2, 0.0F);
//        this.frame.addBox(-6.0F, -10.0F, -8.0F, 4, 1, 2, 0.0F);
//        this.frame.addBox(-6.0F, -3.0F, -8.0F, 3, 1, 2, 0.0F);
//        this.frame.addBox(-7.0F, -9.0F, -8.0F, 1, 1, 2, 0.0F);
//        this.frame.addBox(6.0F, -9.0F, -8.0F, 1, 1, 2, 0.0F);
//        this.frame.addBox(-8.0F, -8.0F, -8.0F, 1, 4, 2, 0.0F);
//        this.frame.addBox(7.0F, -8.0F, -8.0F, 1, 4, 2, 0.0F);
//        this.frame.addBox(6.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
//        this.frame.addBox(-7.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
//        this.frame.addBox(-3.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
//        this.frame.addBox(2.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
    }

//    @Override
//    public void renderToBuffer(PoseStack stack, VertexConsumer buffer, int light, int overlay, float r, float g, float b, float a) {
//        stack.pushPose();
//        stack.scale(0.6F, 0.6F, 0.6F);
//
//        this.head.translateAndRotate(stack);
//
//        this.main.render(stack, buffer, light, overlay, r, g, b, a);
//        this.lens.render(stack, buffer, light, overlay, r, g, b, a);
//        this.frame.render(stack, buffer, light, overlay, r, g, b, a);
//
//        stack.popPose();
//    }
}