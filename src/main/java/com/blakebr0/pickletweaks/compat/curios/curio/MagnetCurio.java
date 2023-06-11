package com.blakebr0.pickletweaks.compat.curios.curio;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public record MagnetCurio(ItemStack stack) implements ICurio {
    @Override
    public ItemStack getStack() {
        return this.stack;
    }

    @Override
    public void curioTick(SlotContext context) {
        var entity = context.entity();
        if (entity instanceof Player player) {
            this.stack.inventoryTick(player.level(), player, -1, false);
        }
    }
}
