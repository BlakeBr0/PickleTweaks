package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.ListIterator;

public final class FeatureToolInfo {
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		ListIterator<ITextComponent> tooltip = event.getToolTip().listIterator();
		Item item = event.getItemStack().getItem();

		if (item instanceof ToolItem) {
			if (isBlacklisted(item))
				return;

			ToolItem tool = (ToolItem) item;

			tooltip.next();

			tooltip.add(ModTooltips.MINING_LEVEL.args(getMiningLevel(tool)).build());
			tooltip.add(ModTooltips.MINING_SPEED.args(getMiningSpeed(tool)).build());
		}
	}

	private static String getMiningLevel(ToolItem item) {
		int lvl = item.getTier().getLevel();
		List<String> names = ModConfigs.HARVEST_LEVEL_NAMES.get();

		return lvl >= 0 && lvl < names.size() ? names.get(lvl) : String.valueOf(lvl);
	}

	private static float getMiningSpeed(ToolItem item) {
		if (TweakToolUselessifier.isUselessTool(item))
			return 0F;

		return item.getTier().getSpeed();
	}

	private static boolean isBlacklisted(Item item) {
		List<String> blacklist = ModConfigs.TOOL_INFO_TOOLTIP_BLACKLIST.get();

		ResourceLocation id = item.getRegistryName();
		if (id == null)
			return true;

		return blacklist.stream().anyMatch(s -> {
			String[] parts = s.split(":");

			if (parts.length != 2)
				return false;

			if ("*".equals(parts[1])) {
				return id.getNamespace().equals(parts[0]);
			}

			return id.toString().equals(s);
		});
	}
}
