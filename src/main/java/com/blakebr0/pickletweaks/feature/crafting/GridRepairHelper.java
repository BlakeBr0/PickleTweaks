package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.item.ItemStack;

public class GridRepairHelper {
	public static double getMaterialValue(ItemStack tool, ItemStack mat) {
		GridRepairOverride.Override override = GridRepairOverride.getOverride(tool, mat);
		boolean hasOverride = override != null;
		if (checkDefault(tool, mat) || hasOverride) {
			boolean overrideMode = ModConfigs.GRID_REPAIR_OVERRIDE_MODE.get();
			if (overrideMode && !hasOverride && GridRepairOverride.hasToolOverride(tool)) {
				return 0;
			}

			return hasOverride ? override.multi : 1.0;
		}

		return 0;
	}

	public static boolean checkDefault(ItemStack tool, ItemStack mat) {
		boolean enabled = ModConfigs.GRID_REPAIR_DISABLE_DEFAULTS.get();
		return !enabled && tool.getItem().getIsRepairable(tool, mat);
	}
}
