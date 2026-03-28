package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import com.blakebr0.pickletweaks.util.BlacklistUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public final class FeatureToolInfo {
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		var tooltip = event.getToolTip().listIterator();
		var stack = event.getItemStack();
		var item = stack.getItem();
		var tool = stack.get(DataComponents.TOOL);

		if (tool != null) {
			if (isBlacklisted(item))
				return;

			tooltip.next();

			tooltip.add(ModTooltips.MINING_SPEED.args(getMiningSpeed(stack, tool)).toComponent());
		}
	}

	private static float getMiningSpeed(ItemStack stack, Tool tool) {
		if (TweakToolUselessifier.isUselessTool(stack))
			return 0F;

		return tool.defaultMiningSpeed();
	}

	private static boolean isBlacklisted(Item item) {
		return BlacklistUtils.contains(item, ModConfigs.TOOL_INFO_TOOLTIP_BLACKLIST.get());
	}
}
