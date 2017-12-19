package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;

public class GridRepairHelper {

	public static boolean checkMaterial(ItemStack tool, ItemStack mat) {
		if (!ModConfig.confDisableDefaults && tool.getItem().getIsRepairable(tool, mat)) {
			return true;
		} else if (GridRepairOverride.hasOverride(tool, mat)) {
			return true;
		}
		return false;
	}
}
