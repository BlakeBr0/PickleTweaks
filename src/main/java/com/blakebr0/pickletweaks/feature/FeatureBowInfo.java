package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class FeatureBowInfo {
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		var stack = event.getItemStack();
		var tooltip = event.getToolTip().listIterator();
		var item = stack.getItem();

		if (item instanceof ProjectileWeaponItem shootable) {
			var player = event.getPlayer();

			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0) {
				while (tooltip.hasNext()) {
					var line = tooltip.next();

					if (line instanceof TranslatableComponent component) {
						var key = component.getKey();

						if ("enchantment.minecraft.infinity".equals(key)) {
							var formatting = getAmmo(player, shootable) > 0 ? ChatFormatting.GREEN : ChatFormatting.RED;

							tooltip.set(new TextComponent(line.getString()).withStyle(formatting));
						}
					}
				}
			} else {
				tooltip.next();

				var ammo = getAmmo(player, shootable);

				tooltip.add(ModTooltips.AMMO.args(ammo).build());
			}
		}
	}

	public static int getAmmo(Player player, ProjectileWeaponItem item) {
		var ammo = 0;

		if (player == null)
			return ammo;

		var offHand = player.getInventory().offhand.get(0);
		if (item.getSupportedHeldProjectiles().test(offHand))
			ammo += offHand.getCount();

		for (var stack : player.getInventory().items) {
			if (item.getSupportedHeldProjectiles().test(stack))
				ammo += stack.getCount();
		}

		return ammo;
	}
}
