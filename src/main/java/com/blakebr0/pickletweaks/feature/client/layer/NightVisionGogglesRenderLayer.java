package com.blakebr0.pickletweaks.feature.client.layer;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

public class NightVisionGogglesRenderLayer<T extends HumanoidRenderState, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final Identifier TEXTURE_BASIC = PickleTweaks.resource("textures/models/armor/night_vision_goggles_layer_1.png");
    private static final Identifier TEXTURE_REINFORCED = PickleTweaks.resource("textures/models/armor/reinforced_night_vision_goggles_layer_1.png");

    private final EquipmentLayerRenderer equipmentLayerRenderer;

    public NightVisionGogglesRenderLayer(RenderLayerParent<T, M> parent, EquipmentLayerRenderer equipmentLayerRenderer) {
        super(parent);
        this.equipmentLayerRenderer = equipmentLayerRenderer;
    }

    @Override
    public void submit(PoseStack matrix, SubmitNodeCollector submitNodeCollector, int lightCoords, T state, float yRot, float xRot) {
        // TODO: curios goggle model?
//        CuriosCompat.findNightVisionGogglesCurio(state).ifPresent(curio -> {
//            var model = IClientItemExtensions.of(curio).getHumanoidArmorModel(entity, curio, EquipmentSlot.CHEST, null);
//            var texture = curio.is(ModItems.NIGHT_VISION_GOGGLES.get()) ? TEXTURE_BASIC : TEXTURE_REINFORCED;
//
//            this.renderArmorPiece(matrix, submitNodeCollector, curio, model, lightCoords, state);
//        });
    }

    private void renderArmorPiece(
            PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack itemStack, Model<T> model, int lightCoords, T state
    ) {
        Equippable equippable = itemStack.get(DataComponents.EQUIPPABLE);
        if (equippable != null) {
            this.equipmentLayerRenderer.renderLayers(EquipmentClientInfo.LayerType.HUMANOID, equippable.assetId().orElseThrow(), model, state, itemStack, poseStack, submitNodeCollector, lightCoords, state.outlineColor);
        }
    }
}
