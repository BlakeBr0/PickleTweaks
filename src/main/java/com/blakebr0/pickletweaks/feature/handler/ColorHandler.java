package com.blakebr0.pickletweaks.feature.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ColorHandler {
    @SubscribeEvent
    public void onBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();

        colors.register(new IColored.BlockColors(),
                ModBlocks.WHITE_COBBLESTONE,
                ModBlocks.ORANGE_COBBLESTONE,
                ModBlocks.MAGENTA_COBBLESTONE,
                ModBlocks.LIGHT_BLUE_COBBLESTONE,
                ModBlocks.YELLOW_COBBLESTONE,
                ModBlocks.LIME_COBBLESTONE,
                ModBlocks.PINK_COBBLESTONE,
                ModBlocks.GRAY_COBBLESTONE,
                ModBlocks.LIGHT_GRAY_COBBLESTONE,
                ModBlocks.CYAN_COBBLESTONE,
                ModBlocks.PURPLE_COBBLESTONE,
                ModBlocks.BLUE_COBBLESTONE,
                ModBlocks.BROWN_COBBLESTONE,
                ModBlocks.GREEN_COBBLESTONE,
                ModBlocks.RED_COBBLESTONE,
                ModBlocks.BLACK_COBBLESTONE
        );
    }

    @SubscribeEvent
    public void onItemColors(ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();

        colors.register(new IColored.ItemBlockColors(),
                ModBlocks.WHITE_COBBLESTONE,
                ModBlocks.ORANGE_COBBLESTONE,
                ModBlocks.MAGENTA_COBBLESTONE,
                ModBlocks.LIGHT_BLUE_COBBLESTONE,
                ModBlocks.YELLOW_COBBLESTONE,
                ModBlocks.LIME_COBBLESTONE,
                ModBlocks.PINK_COBBLESTONE,
                ModBlocks.GRAY_COBBLESTONE,
                ModBlocks.LIGHT_GRAY_COBBLESTONE,
                ModBlocks.CYAN_COBBLESTONE,
                ModBlocks.PURPLE_COBBLESTONE,
                ModBlocks.BLUE_COBBLESTONE,
                ModBlocks.BROWN_COBBLESTONE,
                ModBlocks.GREEN_COBBLESTONE,
                ModBlocks.RED_COBBLESTONE,
                ModBlocks.BLACK_COBBLESTONE
        );
    }
}
