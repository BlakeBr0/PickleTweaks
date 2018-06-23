package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;

public class GridRepairHelper {

	public static boolean checkMaterial(ItemStack tool, ItemStack mat) {
		return getMaterialValue(tool, mat) > 0;
	}
	
	public static double getMaterialValue(ItemStack tool, ItemStack mat) {
		GridRepairOverride.Override override = GridRepairOverride.getOverride(tool, mat);
		boolean hasOverride = override != null;
		if (checkDefault(tool, mat) || hasOverride) {
			if (ModConfig.confOverrideMode && !hasOverride && GridRepairOverride.hasToolOverride(tool)) {
				return 0;
			}
			return hasOverride ? override.multi : 1.0;
		}
		return 0;
	}
	
	public static boolean checkDefault(ItemStack tool, ItemStack mat) {
		return !ModConfig.confDisableDefaults && tool.getItem().getIsRepairable(tool, mat);
	}
}
