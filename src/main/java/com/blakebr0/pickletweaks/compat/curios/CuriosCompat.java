package com.blakebr0.pickletweaks.compat.curios;

import com.blakebr0.pickletweaks.compat.curios.curio.MagnetCurio;
import com.blakebr0.pickletweaks.compat.curios.curio.NightVisionGogglesCurio;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import com.blakebr0.pickletweaks.init.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Optional;

public final class CuriosCompat {
    public static void onInterModEnqueue(InterModEnqueueEvent event) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }

    public static Optional<ItemStack> findNightVisionGogglesCurio(LivingEntity entity) {
        return CuriosApi.getCuriosHelper()
                .findFirstCurio(entity, stack -> stack.is(ModItems.NIGHT_VISION_GOGGLES.get()) || stack.is(ModItems.REINFORCED_NIGHT_VISION_GOGGLES.get()))
                .map(SlotResult::stack);
    }

    public static Optional<ItemStack> findMagnetCurio(LivingEntity entity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, ModItems.MAGNET.get()).map(SlotResult::stack);
    }

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        var stack = event.getObject();

        if (stack.getItem() instanceof MagnetItem) {
            var curio = new MagnetCurio(stack);

            event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
                final LazyOptional<ICurio> capability = LazyOptional.of(() -> curio);

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                    return CuriosCapability.ITEM.orEmpty(cap, capability);
                }
            });
        }

        if (stack.getItem() instanceof NightVisionGogglesItem) {
            var curio = new NightVisionGogglesCurio(stack);

            event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
                final LazyOptional<ICurio> capability = LazyOptional.of(() -> curio);

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                    return CuriosCapability.ITEM.orEmpty(cap, capability);
                }
            });
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        var entity = event.getEntityLiving();

        if (entity instanceof Player player) {
            findNightVisionGogglesCurio(player).ifPresent(stack -> {
                stack.hurtAndBreak(1, entity, e -> {
                    e.broadcastBreakEvent(EquipmentSlot.HEAD);
                });
            });
        }
    }
}
