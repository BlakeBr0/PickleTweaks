package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ListIterator;

public final class FeatureToolInfo {
	public static final String[] MINING_LEVEL_NAMES = new String[] { "Stone", "Iron", "Diamond", "Obsidian" };

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		ListIterator<ITextComponent> tooltip = event.getToolTip().listIterator();
		Item item = event.getItemStack().getItem();
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;

			tooltip.next();

			tooltip.add(ModTooltips.MINING_LEVEL.args(getMiningLevel(tool)).build());
			tooltip.add(ModTooltips.MINING_SPEED.args(getMiningSpeed(tool)).build());
		}
	}

	private static String getMiningLevel(ToolItem item) {
		int lvl = item.getTier().getHarvestLevel();
		return lvl >= 0 && lvl < MINING_LEVEL_NAMES.length ? MINING_LEVEL_NAMES[lvl] : String.valueOf(lvl);
	}

	private static float getMiningSpeed(ToolItem item) {
		if (TweakToolUselessifier.isUselessTool(item))
			return 0F;

		return item.getTier().getEfficiency();
	}
}
