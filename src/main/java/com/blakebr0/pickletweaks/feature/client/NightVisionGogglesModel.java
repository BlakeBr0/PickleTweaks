package com.blakebr0.pickletweaks.feature.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class NightVisionGogglesModel extends BipedModel<LivingEntity> {
    private final ModelRenderer main;
    private final ModelRenderer lens;
    private final ModelRenderer frame;

    public NightVisionGogglesModel() {
        super(RenderType::getEntityTranslucent, 0.0F, 0.0F, 64, 32);

        this.main = new ModelRenderer(this, 0, 7);
        this.main.setRotationPoint(0.0F, 0F, 0.0F);
        this.main.addBox(-8.0F, -7.0F, 1.0F, 1, 1, 6, 0.0F);
        this.main.addBox(7.0F, -7.0F, 1.0F, 1, 1, 6, 0.0F);
        this.main.addBox(-7.0F, -7.0F, 7.0F, 14, 1, 1, 0.0F);
        this.main.addBox(-8.0F, -7.0F, -6.0F, 1, 2, 7, 0.0F);
        this.main.addBox(7.0F, -7.0F, -6.0F, 1, 2, 7, 0.0F);

        this.lens = new ModelRenderer(this);
        this.lens.setRotationPoint(0.0F, 0F, 0.0F);

        ModelRenderer left = new ModelRenderer(this, 6, 0);
        left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lens.addChild(left);
        left.addBox(-6.0F, -9.0F, -7.0F, 4, 5, 1, 0.0F);
        left.addBox(-2.0F, -8.0F, -7.0F, 1, 3, 1, 0.0F);
        left.addBox(-7.0F, -8.0F, -7.0F, 1, 4, 1, 0.0F);
        left.addBox(-6.0F, -4.0F, -7.0F, 3, 1, 1, 0.0F);

        ModelRenderer right = new ModelRenderer(this, 6, 0);
        right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lens.addChild(right);
        right.addBox(6.0F, -8.0F, -7.0F, 1, 4, 1, 0.0F);
        right.addBox(1.0F, -8.0F, -7.0F, 1, 3, 1, 0.0F);
        right.addBox(2.0F, -9.0F, -7.0F, 4, 5, 1, 0.0F);
        right.addBox(3.0F, -4.0F, -7.0F, 3, 1, 1, 0.0F);

        this.frame = new ModelRenderer(this, 0, 13);
        this.frame.setRotationPoint(0.0F, 0F, 0.0F);
        this.frame.addBox(-1.0F, -8.0F, -8.0F, 2, 3, 2, 0.0F);
        this.frame.addBox(-2.0F, -9.0F, -8.0F, 4, 1, 2, 0.0F);
        this.frame.addBox(-2.0F, -5.0F, -8.0F, 4, 1, 2, 0.0F);
        this.frame.addBox(2.0F, -10.0F, -8.0F, 4, 1, 2, 0.0F);
        this.frame.addBox(3.0F, -3.0F, -8.0F, 3, 1, 2, 0.0F);
        this.frame.addBox(-6.0F, -10.0F, -8.0F, 4, 1, 2, 0.0F);
        this.frame.addBox(-6.0F, -3.0F, -8.0F, 3, 1, 2, 0.0F);
        this.frame.addBox(-7.0F, -9.0F, -8.0F, 1, 1, 2, 0.0F);
        this.frame.addBox(6.0F, -9.0F, -8.0F, 1, 1, 2, 0.0F);
        this.frame.addBox(-8.0F, -8.0F, -8.0F, 1, 4, 2, 0.0F);
        this.frame.addBox(7.0F, -8.0F, -8.0F, 1, 4, 2, 0.0F);
        this.frame.addBox(6.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
        this.frame.addBox(-7.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
        this.frame.addBox(-3.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
        this.frame.addBox(2.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F);
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay, float r, float g, float b, float a) {
        stack.push();
        stack.scale(0.6F, 0.6F, 0.6F);
        this.main.render(stack, buffer, light, overlay);
        this.lens.render(stack, buffer, light, overlay);
        this.frame.render(stack, buffer, light, overlay);
        stack.pop();
    }

    @Override
    public void setRotationAngles(LivingEntity entity, float f1, float f2, float f3, float netHeadYaw, float headPitch) {
        this.main.rotateAngleY = netHeadYaw * 0.017453292F;
        this.lens.rotateAngleY = netHeadYaw * 0.017453292F;
        this.frame.rotateAngleY = netHeadYaw * 0.017453292F;
        if (entity.getTicksElytraFlying() > 4) {
            this.main.rotateAngleX = -0.7853982F;
            this.lens.rotateAngleX = -0.7853982F;
            this.frame.rotateAngleX = -0.7853982F;
        } else if (this.swimAnimation > 0.0F) {
            if (entity.isActualySwimming()) {
                this.main.rotateAngleX = this.rotLerpRad(this.main.rotateAngleX, -0.7853982F, this.swimAnimation);
                this.lens.rotateAngleX = this.rotLerpRad(this.lens.rotateAngleX, -0.7853982F, this.swimAnimation);
                this.frame.rotateAngleX = this.rotLerpRad(this.frame.rotateAngleX, -0.7853982F, this.swimAnimation);
            } else {
                this.main.rotateAngleX = this.rotLerpRad(this.main.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
                this.lens.rotateAngleX = this.rotLerpRad(this.lens.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
                this.frame.rotateAngleX = this.rotLerpRad(this.frame.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
            }
        } else {
            this.main.rotateAngleX = headPitch * 0.017453292F;
            this.lens.rotateAngleX = headPitch * 0.017453292F;
            this.frame.rotateAngleX = headPitch * 0.017453292F;
        }
    }
}