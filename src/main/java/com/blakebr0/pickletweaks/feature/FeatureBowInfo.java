package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public final class FeatureBowInfo {
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_BOW_AMMO_TOOLTIP.get())
			return;

		var stack = event.getItemStack();
		var tooltip = event.getToolTip().listIterator();
		var item = stack.getItem();

		if (item instanceof ProjectileWeaponItem shootable) {
			var player = event.getEntity();
			var registries = event.getContext().registries();
			if (registries == null)
				return;

			var infinity = registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.INFINITY);

			if (EnchantmentHelper.getTagEnchantmentLevel(infinity, stack) > 0) {
				while (tooltip.hasNext()) {
					var line = tooltip.next();

					if (line.getContents() instanceof TranslatableContents contents) {
						var key = contents.getKey();

						if ("enchantment.minecraft.infinity".equals(key)) {
							var formatting = getAmmo(player, shootable, stack) > 0 ? ChatFormatting.GREEN : ChatFormatting.RED;

							tooltip.set(Component.literal(line.getString()).withStyle(formatting));
						}
					}
				}
			} else {
				tooltip.next();

				var ammo = getAmmo(player, shootable, stack);

				tooltip.add(ModTooltips.AMMO.args(ammo).toComponent());
			}
		}
	}

	public static int getAmmo(Player player, ProjectileWeaponItem item, ItemStack stack) {
		var ammo = 0;

		if (player == null)
			return ammo;

		var offHand = player.getInventory().getItem(Inventory.SLOT_OFFHAND);
		if (item.getSupportedHeldProjectiles(stack).test(offHand))
			ammo += offHand.getCount();

		for (var inventoryStack : player.getInventory().getNonEquipmentItems()) {
			if (item.getSupportedHeldProjectiles(stack).test(inventoryStack))
				ammo += inventoryStack.getCount();
		}

		return ammo;
	}
}
