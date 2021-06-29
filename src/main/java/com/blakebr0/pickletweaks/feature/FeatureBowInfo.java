package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ListIterator;

public final class FeatureBowInfo {
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		ItemStack stack = event.getItemStack();
		ListIterator<ITextComponent> tooltip = event.getToolTip().listIterator();

		Item item = stack.getItem();
		if (item instanceof ShootableItem) {
			PlayerEntity player = event.getPlayer();
			ShootableItem shootable = (ShootableItem) item;

			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0) {
				while (tooltip.hasNext()) {
					ITextComponent next = tooltip.next();

					if (next instanceof TranslationTextComponent) {
						String key = ((TranslationTextComponent) next).getKey();

						if ("enchantment.minecraft.infinity".equals(key)) {
							TextFormatting formatting = getAmmo(player, shootable) > 0 ? TextFormatting.GREEN : TextFormatting.RED;

							tooltip.set(new StringTextComponent(next.getString()).withStyle(formatting));
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

	public static int getAmmo(PlayerEntity player, ShootableItem item) {
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
