package com.blakebr0.pickletweaks.feature.handler;

import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.network.message.ToggleMagnetMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ToggleMagnetInInventoryHandler {
    @SubscribeEvent
    public void onMouseClicked(GuiScreenEvent.MouseClickedEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Screen gui = event.getGui();
        if (gui instanceof ContainerScreen<?> && event.getButton() == 1) {
            ContainerScreen<?> container = (ContainerScreen<?>) gui;
            Slot slot = container.getSlotUnderMouse();
            ClientPlayerEntity player = mc.player;
            if (player == null)
                return;

            ItemStack held = player.inventory.getItemStack();
            if (slot != null && held.isEmpty() && slot.inventory == player.inventory) {
                ItemStack stack = slot.getStack();
                if (stack.getItem() instanceof MagnetItem) {
                    NetworkHandler.INSTANCE.sendToServer(new ToggleMagnetMessage(slot.slotNumber));
                    event.setCanceled(true);
                }
            }
        }
    }
}
