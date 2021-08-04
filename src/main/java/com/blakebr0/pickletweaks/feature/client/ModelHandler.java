package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.init.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class ModelHandler {
    public static void onClientSetup() {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_GLASS.get(), RenderType.translucent());
    }
}
