package com.blakebr0.pickletweaks.feature.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.pickletweaks.init.ModBlocks;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ColorHandler {
    @SubscribeEvent
    public void onBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register(new IColored.BlockColors(),
                ModBlocks.WHITE_COBBLESTONE.get(),
                ModBlocks.ORANGE_COBBLESTONE.get(),
                ModBlocks.MAGENTA_COBBLESTONE.get(),
                ModBlocks.LIGHT_BLUE_COBBLESTONE.get(),
                ModBlocks.YELLOW_COBBLESTONE.get(),
                ModBlocks.LIME_COBBLESTONE.get(),
                ModBlocks.PINK_COBBLESTONE.get(),
                ModBlocks.GRAY_COBBLESTONE.get(),
                ModBlocks.LIGHT_GRAY_COBBLESTONE.get(),
                ModBlocks.CYAN_COBBLESTONE.get(),
                ModBlocks.PURPLE_COBBLESTONE.get(),
                ModBlocks.BLUE_COBBLESTONE.get(),
                ModBlocks.BROWN_COBBLESTONE.get(),
                ModBlocks.GREEN_COBBLESTONE.get(),
                ModBlocks.RED_COBBLESTONE.get(),
                ModBlocks.BLACK_COBBLESTONE.get()
        );
    }

    @SubscribeEvent
    public void onItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register(new IColored.ItemBlockColors(),
                ModBlocks.WHITE_COBBLESTONE.get(),
                ModBlocks.ORANGE_COBBLESTONE.get(),
                ModBlocks.MAGENTA_COBBLESTONE.get(),
                ModBlocks.LIGHT_BLUE_COBBLESTONE.get(),
                ModBlocks.YELLOW_COBBLESTONE.get(),
                ModBlocks.LIME_COBBLESTONE.get(),
                ModBlocks.PINK_COBBLESTONE.get(),
                ModBlocks.GRAY_COBBLESTONE.get(),
                ModBlocks.LIGHT_GRAY_COBBLESTONE.get(),
                ModBlocks.CYAN_COBBLESTONE.get(),
                ModBlocks.PURPLE_COBBLESTONE.get(),
                ModBlocks.BLUE_COBBLESTONE.get(),
                ModBlocks.BROWN_COBBLESTONE.get(),
                ModBlocks.GREEN_COBBLESTONE.get(),
                ModBlocks.RED_COBBLESTONE.get(),
                ModBlocks.BLACK_COBBLESTONE.get()
        );
    }
}
