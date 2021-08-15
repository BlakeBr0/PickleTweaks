package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class NightVisionGogglesLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(PickleTweaks.MOD_ID, "textures/models/armor/night_vision_goggles_layer_1");
    private final NightVisionGogglesModel model;

    public NightVisionGogglesLayer(RenderLayerParent<T, M> parent) {
        super(parent);

        var layer = Minecraft.getInstance().getEntityModels().bakeLayer(ModelHandler.NIGHT_VISION_GOGGLES_LAYER);

        this.model = new NightVisionGogglesModel(layer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int lightness, T player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        stack.pushPose();

        this.getParentModel().head.translateAndRotate(stack);

        renderColoredCutoutModel(this.model, TEXTURE, stack, buffer, lightness, player, 1.0f, 1.0f, 1.0f);

        stack.popPose();
    }
}
