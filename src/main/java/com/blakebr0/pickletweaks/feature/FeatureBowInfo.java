package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ListIterator;

public class FeatureBowInfo {
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get()) return;

		ItemStack stack = event.getItemStack();
		ListIterator<ITextComponent> tooltip = event.getToolTip().listIterator();

		Item item = stack.getItem();
		if (item instanceof ShootableItem) {
			tooltip.next();

      		tooltip.add(ModTooltips.AMMO.args(this.getAmmo(event.getEntityPlayer())).build());
		}
	}

	public int getAmmo(PlayerEntity player) {
		int ammo = 0;

		if (player == null) return ammo;

		ItemStack offHand = player.inventory.offHandInventory.get(0);
		if (offHand.getItem() instanceof ArrowItem) {
			ammo += offHand.getCount();
		}

		for (ItemStack stack : player.inventory.mainInventory) {
			if (stack.getItem() instanceof ArrowItem) {
				ammo += stack.getCount();
			}
		}

		return ammo;
	}
}
