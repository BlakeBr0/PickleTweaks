package com.blakebr0.pickletweaks.feature.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;

public class ColorHandler {
    public static void onClientSetup() {
        Minecraft minecraft = Minecraft.getInstance();
        BlockColors blockColors = minecraft.getBlockColors();
        ItemColors itemColors = minecraft.getItemColors();

        onBlockColors(blockColors);
        onItemColors(itemColors);
    }

    private static void onBlockColors(BlockColors colors) {
        colors.register(new IColored.BlockColors(),
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

    private static void onItemColors(ItemColors colors) {
        colors.register(new IColored.ItemBlockColors(),
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
