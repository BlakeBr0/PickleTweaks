package com.blakebr0.pickletweaks.feature.client.handler;

import com.blakebr0.pickletweaks.compat.curios.CuriosCompat;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.List;

public final class NightVisionGogglesHandler {
    public static final List<String> WEARERS = new ArrayList<>();

    public static String getPlayerKey(Player player) {
        return player.getGameProfile().name() + ":" + player.isLocalPlayer();
    }

    public static boolean hasGoggles(Player player) {
        var stack = player.getItemBySlot(EquipmentSlot.HEAD);

        return !stack.isEmpty() && (stack.is(ModItems.NIGHT_VISION_GOGGLES) || stack.is(ModItems.REINFORCED_NIGHT_VISION_GOGGLES))
                || (ModConfigs.isCuriosInstalled() && CuriosCompat.findNightVisionGogglesCurio(player).isPresent());
    }

    @SubscribeEvent
    public void onLivingTick(PlayerTickEvent.Pre event) {
        var player = event.getEntity();
        var key = getPlayerKey(player);
        var hasGoggles = hasGoggles(player);

        if (WEARERS.contains(key)) {
            if (hasGoggles) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 246, 0, true, false));
            } else {
                player.removeEffectNoUpdate(MobEffects.NIGHT_VISION);
                WEARERS.remove(key);
            }
        } else if (hasGoggles) {
            WEARERS.add(key);
        }
    }
}
