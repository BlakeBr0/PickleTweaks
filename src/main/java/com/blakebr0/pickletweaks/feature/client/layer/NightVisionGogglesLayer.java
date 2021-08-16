package com.blakebr0.pickletweaks.feature.client.layer;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import com.blakebr0.pickletweaks.init.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class NightVisionGogglesLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation BASIC_TEXTURE = new ResourceLocation(PickleTweaks.MOD_ID, "textures/models/armor/night_vision_goggles_layer_1.png");
    private static final ResourceLocation REINFORCED_TEXTURE = new ResourceLocation(PickleTweaks.MOD_ID, "textures/models/armor/reinforced_night_vision_goggles_layer_1.png");

    private final NightVisionGogglesModel model;

    public NightVisionGogglesLayer(RenderLayerParent<T, M> parent) {
        super(parent);

        var layer = Minecraft.getInstance().getEntityModels().bakeLayer(ModelHandler.NIGHT_VISION_GOGGLES_LAYER);

        this.model = new NightVisionGogglesModel(layer);
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int lightness, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        var stack = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (stack.is(ModItems.NIGHT_VISION_GOGGLES.get())) {
            matrix.pushPose();

            this.getParentModel().head.translateAndRotate(matrix);
            matrix.scale(0.6F, 0.6F, 0.6F);

            renderColoredCutoutModel(this.model, BASIC_TEXTURE, matrix, buffer, lightness, entity, 1.0f, 1.0f, 1.0f);

            matrix.popPose();
        } else if (stack.is(ModItems.REINFORCED_NIGHT_VISION_GOGGLES.get())) {
            matrix.pushPose();

            this.getParentModel().head.translateAndRotate(matrix);
            matrix.scale(0.6F, 0.6F, 0.6F);

            renderColoredCutoutModel(this.model, REINFORCED_TEXTURE, matrix, buffer, lightness, entity, 1.0f, 1.0f, 1.0f);

            matrix.popPose();
        }
    }
}
