package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;

public class GridRepairHelper {

	public static boolean checkMaterial(ItemStack tool, ItemStack mat) {
		boolean override = GridRepairOverride.hasOverride(tool, mat);
		if (checkDefault(tool, mat) || override) {
			if (ModConfig.confOverrideMode && !override && GridRepairOverride.hasToolOverride(tool)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean checkDefault(ItemStack tool, ItemStack mat) {
		return !ModConfig.confDisableDefaults && tool.getItem().getIsRepairable(tool, mat);
	}
}
