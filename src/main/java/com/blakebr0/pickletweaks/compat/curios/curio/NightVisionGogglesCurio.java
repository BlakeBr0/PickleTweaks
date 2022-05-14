package com.blakebr0.pickletweaks.compat.curios.curio;

import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public record NightVisionGogglesCurio(ItemStack stack) implements ICurio {
    @Override
    public ItemStack getStack() {
        return this.stack;
    }

    @Override
    public boolean canEquipFromUse(SlotContext context) {
        return true;
    }
}
