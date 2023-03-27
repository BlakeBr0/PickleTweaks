package com.blakebr0.pickletweaks.feature.client.handler;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.network.message.ToggleMagnetMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ToggleMagnetInInventoryHandler {
    @SubscribeEvent
    public void onMouseClicked(ScreenEvent.MouseButtonPressed.Pre event) {
        var mc = Minecraft.getInstance();
        var screen = event.getScreen();

        if (screen instanceof AbstractContainerScreen<?> container && event.getButton() == 1) {
            var slot = container.getSlotUnderMouse();
            var player = mc.player;

            if (player == null)
                return;

            var held = container.getMenu().getCarried();

            if (slot != null && held.isEmpty() && (slot.container == player.getInventory() || isCuriosScreen(container))) {
                var stack = slot.getItem();

                if (stack.getItem() instanceof MagnetItem) {
                    NetworkHandler.INSTANCE.sendToServer(new ToggleMagnetMessage(slot.index));

                    player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean isCuriosScreen(AbstractContainerScreen<?> screen) {
        return ModConfigs.isCuriosInstalled() && screen.getClass().getName().equals("top.theillusivec4.curios.client.gui.CuriosScreen");
    }
}
