package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.feature.client.layer.NightVisionGogglesLayer;
import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import com.blakebr0.pickletweaks.init.ModBlocks;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModelHandler {
    public static final ModelLayerLocation NIGHT_VISION_GOGGLES_LAYER = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "pickletweaks:night_vision_goggles");

    @SubscribeEvent
    public void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NIGHT_VISION_GOGGLES_LAYER, NightVisionGogglesModel::createBodyLayer);
    }

    @SubscribeEvent
    public void onAddLayers(EntityRenderersEvent.AddLayers event) {
        addLayerToEntity(event, EntityType.ARMOR_STAND);
        addLayerToEntity(event, EntityType.ZOMBIE);
        addLayerToEntity(event, EntityType.SKELETON);
        addLayerToEntity(event, EntityType.HUSK);
        addLayerToEntity(event, EntityType.DROWNED);
        addLayerToEntity(event, EntityType.STRAY);

        addLayerToPlayerSkin(event, "default");
        addLayerToPlayerSkin(event, "slim");
    }

    public static void onClientSetup() {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_GLASS.get(), RenderType.translucent());
    }

    @SuppressWarnings("unchecked rawtypes")
    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, String skinName) {
        EntityRenderer<? extends Player> skin = event.getSkin(skinName);

        if (skin instanceof LivingEntityRenderer renderer) {
            renderer.addLayer(new NightVisionGogglesLayer<>(renderer));
        }
    }

    private static <T extends LivingEntity, M extends HumanoidModel<T>, R extends LivingEntityRenderer<T, M>> void addLayerToEntity(
            EntityRenderersEvent.AddLayers event, EntityType<? extends T> type)
    {
        R renderer = event.getRenderer(type);

        if (renderer != null) {
            renderer.addLayer(new NightVisionGogglesLayer<>(renderer));
        }
    }
}
