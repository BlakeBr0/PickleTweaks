package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModelHandler {
    public static final ModelLayerLocation NIGHT_VISION_GOGGLES_LAYER = new ModelLayerLocation(Identifier.parse("minecraft:player"), "pickletweaks:night_vision_goggles");

    @SubscribeEvent
    public void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NIGHT_VISION_GOGGLES_LAYER, NightVisionGogglesModel::createBodyLayer);
    }

    @SubscribeEvent
    public void onAddLayers(EntityRenderersEvent.AddLayers event) {
//        if (ModConfigs.isCuriosInstalled()) {
//            addLayerToPlayerSkin(event, PlayerSkin.Model.WIDE);
//            addLayerToPlayerSkin(event, PlayerSkin.Model.SLIM);
//        }
    }

//    @SuppressWarnings("unchecked rawtypes")
//    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, PlayerSkin.Model skin) {
//        var renderer = event.getSkin(skin);
//
//        if (renderer instanceof LivingEntityRenderer<?,?> livingEntityRenderer) {
//            livingEntityRenderer.addLayer(new NightVisionGogglesRenderLayer(livingEntityRenderer));
//        }
//    }
}
