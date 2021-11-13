package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModelHandler {
    public static final ModelLayerLocation NIGHT_VISION_GOGGLES_LAYER = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "pickletweaks:night_vision_goggles");

    @SubscribeEvent
    public void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NIGHT_VISION_GOGGLES_LAYER, NightVisionGogglesModel::createBodyLayer);
    }
}
