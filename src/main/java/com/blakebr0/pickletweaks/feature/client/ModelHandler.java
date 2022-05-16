package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.feature.client.layer.NightVisionGogglesRenderLayer;
import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class ModelHandler {
    public static final ModelLayerLocation NIGHT_VISION_GOGGLES_LAYER = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "pickletweaks:night_vision_goggles");

    @SubscribeEvent
    public void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NIGHT_VISION_GOGGLES_LAYER, NightVisionGogglesModel::createBodyLayer);
    }

    @SubscribeEvent
    public void onAddLayers(EntityRenderersEvent.AddLayers event) {
        if (ModList.get().isLoaded("curios")) {
            addLayerToPlayerSkin(event, "default");
            addLayerToPlayerSkin(event, "slim");
        }
    }

    @SuppressWarnings("unchecked rawtypes")
    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, String skinName) {
        var renderer = event.getSkin(skinName);

        if (renderer != null) {
            renderer.addLayer(new NightVisionGogglesRenderLayer(renderer));
        }
    }
}
