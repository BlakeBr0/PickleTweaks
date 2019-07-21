package com.blakebr0.pickletweaks.feature.handler;

import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class NightVisionGogglesHandler {
    public static final List<String> WEARERS = new ArrayList<>();

    public static String getPlayerKey(PlayerEntity player) {
        return player.getGameProfile().getName() + ":" + player.getEntityWorld().isRemote;
    }

    public static boolean hasGoggles(PlayerEntity entity) {
        ItemStack stack = entity.getItemStackFromSlot(EquipmentSlotType.HEAD);

        return !stack.isEmpty() && stack.getItem() instanceof NightVisionGogglesItem;
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            String key = getPlayerKey(player);

            boolean hasGoggles = hasGoggles(player);
            if (WEARERS.contains(key)) {
                if (hasGoggles) {
                    player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 246, 0, true, false));
                } else {
                    player.removeActivePotionEffect(Effects.NIGHT_VISION);
                    WEARERS.remove(key);
                }
            } else if (hasGoggles) {
                WEARERS.add(key);
            }
        }
    }
}
