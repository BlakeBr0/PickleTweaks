package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class FeatureToolInfo {
	public static final String[] MINING_LEVEL_NAMES = new String[] { "Stone", "Iron", "Diamond", "Obsidian", "Netherite" };

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		var tooltip = event.getToolTip().listIterator();
		var item = event.getItemStack().getItem();

		if (item instanceof DiggerItem tool) {
			if (isBlacklisted(item))
				return;

			tooltip.next();

			tooltip.add(ModTooltips.MINING_LEVEL.args(getMiningLevel(tool)).build());
			tooltip.add(ModTooltips.MINING_SPEED.args(getMiningSpeed(tool)).build());
		}
	}

	private static String getMiningLevel(DiggerItem item) {
		int lvl = item.getTier().getLevel();
		return lvl >= 0 && lvl < MINING_LEVEL_NAMES.length ? MINING_LEVEL_NAMES[lvl] : String.valueOf(lvl);
	}

	private static float getMiningSpeed(DiggerItem item) {
		if (TweakToolUselessifier.isUselessTool(item))
			return 0F;

		return item.getTier().getSpeed();
	}

	private static boolean isBlacklisted(Item item) {
		var blacklist = ModConfigs.TOOL_INFO_TOOLTIP_BLACKLIST.get();

		var id = item.getRegistryName();
		if (id == null)
			return true;

		return blacklist.stream().anyMatch(s -> {
			var parts = s.split(":");

			if (parts.length != 2)
				return false;

			if ("*".equals(parts[1])) {
				return id.getNamespace().equals(parts[0]);
			}

			return id.toString().equals(s);
		});
	}
}
