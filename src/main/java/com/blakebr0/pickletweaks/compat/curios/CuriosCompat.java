package com.blakebr0.pickletweaks.compat.curios;

import com.blakebr0.pickletweaks.compat.curios.curio.MagnetCurio;
import com.blakebr0.pickletweaks.compat.curios.curio.NightVisionGogglesCurio;
import com.blakebr0.pickletweaks.init.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

public final class CuriosCompat {
    public static Optional<ItemStack> findNightVisionGogglesCurio(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity).flatMap(inventory -> inventory
                .findFirstCurio(stack -> stack.is(ModItems.NIGHT_VISION_GOGGLES.get()) || stack.is(ModItems.REINFORCED_NIGHT_VISION_GOGGLES.get()))
                .map(SlotResult::stack));
    }

    public static Optional<ItemStack> findMagnetCurio(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity).flatMap(inventory -> inventory
                .findFirstCurio(ModItems.MAGNET.get()).map(SlotResult::stack));
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(CuriosCapability.ITEM, (stack, _) -> new MagnetCurio(stack), ModItems.MAGNET.get());
        event.registerItem(CuriosCapability.ITEM, (stack, _) -> new NightVisionGogglesCurio(stack),
                ModItems.NIGHT_VISION_GOGGLES.get(), ModItems.REINFORCED_NIGHT_VISION_GOGGLES.get());
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Pre event) {
        var entity = event.getEntity();

        if (entity instanceof Player player) {
            findNightVisionGogglesCurio(player)
                    .ifPresent(stack -> stack.hurtAndBreak(1, entity, EquipmentSlot.HEAD));
        }
    }
}
