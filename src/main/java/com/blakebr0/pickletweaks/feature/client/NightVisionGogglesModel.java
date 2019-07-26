package com.blakebr0.pickletweaks.feature.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class NightVisionGogglesModel extends BipedModel<LivingEntity> {
    private final RendererModel main;
    private final RendererModel lens;
    private final RendererModel frame;

    public NightVisionGogglesModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.main = new RendererModel(this);
        this.main.setRotationPoint(0.0F, 0F, 0.0F);
        this.main.cubeList.add(new ModelBox(this.main, 0, 7, -8.0F, -7.0F, 1.0F, 1, 1, 6, 0.0F, false));
        this.main.cubeList.add(new ModelBox(this.main, 0, 7, 7.0F, -7.0F, 1.0F, 1, 1, 6, 0.0F, false));
        this.main.cubeList.add(new ModelBox(this.main, 0, 7, -7.0F, -7.0F, 7.0F, 14, 1, 1, 0.0F, false));
        this.main.cubeList.add(new ModelBox(this.main, 0, 7, -8.0F, -7.0F, -6.0F, 1, 2, 7, 0.0F, false));
        this.main.cubeList.add(new ModelBox(this.main, 0, 7, 7.0F, -7.0F, -6.0F, 1, 2, 7, 0.0F, false));

        this.lens = new RendererModel(this);
        this.lens.setRotationPoint(0.0F, 0F, 0.0F);

        RendererModel left = new RendererModel(this);
        left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lens.addChild(left);
        left.cubeList.add(new ModelBox(left, 6, 0, -6.0F, -9.0F, -7.0F, 4, 5, 1, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 6, 0, -2.0F, -8.0F, -7.0F, 1, 3, 1, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 6, 0, -7.0F, -8.0F, -7.0F, 1, 4, 1, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 6, 0, -6.0F, -4.0F, -7.0F, 3, 1, 1, 0.0F, false));

        RendererModel right = new RendererModel(this);
        right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lens.addChild(right);
        right.cubeList.add(new ModelBox(right, 6, 0, 6.0F, -8.0F, -7.0F, 1, 4, 1, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 6, 0, 1.0F, -8.0F, -7.0F, 1, 3, 1, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 6, 0, 2.0F, -9.0F, -7.0F, 4, 5, 1, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 6, 0, 3.0F, -4.0F, -7.0F, 3, 1, 1, 0.0F, false));

        this.frame = new RendererModel(this);
        this.frame.setRotationPoint(0.0F, 0F, 0.0F);
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -1.0F, -8.0F, -8.0F, 2, 3, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -2.0F, -9.0F, -8.0F, 4, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -2.0F, -5.0F, -8.0F, 4, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, 2.0F, -10.0F, -8.0F, 4, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, 3.0F, -3.0F, -8.0F, 3, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -6.0F, -10.0F, -8.0F, 4, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -6.0F, -3.0F, -8.0F, 3, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -7.0F, -9.0F, -8.0F, 1, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, 6.0F, -9.0F, -8.0F, 1, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -8.0F, -8.0F, -8.0F, 1, 4, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, 7.0F, -8.0F, -8.0F, 1, 4, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, 6.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -7.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, -3.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F, false));
        this.frame.cubeList.add(new ModelBox(this.frame, 0, 13, 2.0F, -4.0F, -8.0F, 1, 1, 2, 0.0F, false));
    }

    @Override
    public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.rotate(this.main, entity, f3, f4);
        this.rotate(this.lens, entity, f3, f4);
        this.rotate(this.frame, entity, f3, f4);

        GlStateManager.pushMatrix();
        GlStateManager.scalef(0.6F, 0.6F, 0.6F);
        this.main.render(f5);
        this.lens.render(f5);
        this.frame.render(f5);
        GlStateManager.popMatrix();
    }

    private void rotate(RendererModel model, LivingEntity entity, float netHeadYaw, float headPitch) {
        boolean flag = entity.getTicksElytraFlying() > 4;
        boolean flag1 = entity.func_213314_bj();
        model.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            model.rotateAngleX = (-(float)Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                model.rotateAngleX = this.func_205060_a(model.rotateAngleX, (-(float)Math.PI / 4F), this.swimAnimation);
            } else {
                model.rotateAngleX = this.func_205060_a(model.rotateAngleX, headPitch * ((float)Math.PI / 180F), this.swimAnimation);
            }
        } else {
            model.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        }
    }
}