package com.blakebr0.pickletweaks.feature.client.handler;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.init.ModDataComponentTypes;
import com.blakebr0.pickletweaks.network.payload.ToggleMagnetPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public final class ToggleMagnetInInventoryHandler {
    @SubscribeEvent
    public void onMouseClicked(ScreenEvent.MouseButtonPressed.Pre event) {
        var screen = event.getScreen();

        if (screen instanceof AbstractContainerScreen<?> container && event.getButton() == 1) {
            var mc = Minecraft.getInstance();
            var slot = container.getHoveredSlot();
            var player = mc.player;

            if (player == null)
                return;

            var held = container.getMenu().getCarried();

            if (slot != null && held.isEmpty() && (slot.container == player.getInventory() || isCuriosScreen(container))) {
                var stack = slot.getItem();

                if (stack.getItem() instanceof MagnetItem) {
                    ClientPacketDistributor.sendToServer(new ToggleMagnetPayload(slot.index));

                    player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, stack.getOrDefault(ModDataComponentTypes.MAGNET_ACTIVE, false) ? 0.5F : 1.0F);

                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean isCuriosScreen(AbstractContainerScreen<?> screen) {
        return ModConfigs.isCuriosInstalled() && screen.getClass().getName().equals("top.theillusivec4.curios.client.gui.CuriosScreen");
    }
}
