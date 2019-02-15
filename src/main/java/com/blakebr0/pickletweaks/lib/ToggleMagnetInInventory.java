package com.blakebr0.pickletweaks.lib;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.item.ItemMagnet;
import com.blakebr0.pickletweaks.network.MessageToggleMagnet;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Mouse;

@Mod.EventBusSubscriber(modid = PickleTweaks.MOD_ID, value = Side.CLIENT)
public class ToggleMagnetInInventory {

    @SubscribeEvent
    public static void onRightClick(GuiScreenEvent.MouseInputEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen gui = mc.currentScreen;
        if (gui instanceof GuiContainer && isRightClickDown()) {
            GuiContainer container = (GuiContainer) gui;
            Slot under = container.getSlotUnderMouse();
            ItemStack held = mc.player.inventory.getItemStack();

            if (under != null && held.isEmpty() && under.inventory == mc.player.inventory) {
                ItemStack stack = under.getStack();
                if (stack.getItem() instanceof ItemMagnet) {
                    System.out.println("hello");
                    NetworkHandler.INSTANCE.sendToServer(new MessageToggleMagnet(under.slotNumber));
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean isRightClickDown() {
        try {
            return Mouse.getEventButton() == 1 && Mouse.isButtonDown(1);
        } catch (Exception e) {
            return false;
        }
    }
}
