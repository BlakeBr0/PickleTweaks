package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ListIterator;

public final class FeatureBowInfo {
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		ItemStack stack = event.getItemStack();
		ListIterator<Component> tooltip = event.getToolTip().listIterator();

		Item item = stack.getItem();
		if (item instanceof ProjectileWeaponItem) {
			Player player = event.getPlayer();
			ProjectileWeaponItem shootable = (ProjectileWeaponItem) item;

			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0) {
				while (tooltip.hasNext()) {
					Component next = tooltip.next();

					if (next instanceof TranslatableComponent) {
						String key = ((TranslatableComponent) next).getKey();

						if ("enchantment.minecraft.infinity".equals(key)) {
							ChatFormatting formatting = getAmmo(player, shootable) > 0 ? ChatFormatting.GREEN : ChatFormatting.RED;

							tooltip.set(new TextComponent(next.getString()).withStyle(formatting));
						}
					}
				}
			} else {
				tooltip.next();

				int ammo = getAmmo(player, shootable);

				tooltip.add(ModTooltips.AMMO.args(ammo).build());
			}
		}
	}

	public static int getAmmo(Player player, ProjectileWeaponItem item) {
		int ammo = 0;

		if (player == null)
			return ammo;

		ItemStack offHand = player.inventory.offhand.get(0);
		if (item.getSupportedHeldProjectiles().test(offHand))
			ammo += offHand.getCount();

		for (ItemStack stack : player.inventory.items) {
			if (item.getSupportedHeldProjectiles().test(stack))
				ammo += stack.getCount();
		}

		return ammo;
	}
}
