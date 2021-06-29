package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.init.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class ModelHandler {
    public static void onClientSetup() {
        RenderTypeLookup.setRenderLayer(ModBlocks.DARK_GLASS.get(), RenderType.translucent());
    }
}
