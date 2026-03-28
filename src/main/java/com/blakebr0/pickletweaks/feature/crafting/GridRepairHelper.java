package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.util.BlacklistUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GridRepairHelper {
	public static double getMaterialValue(ItemStack tool, ItemStack mat) {
		var override = GridRepairOverrides.getOverride(tool, mat);
		var hasOverride = override != null;

		if (isValidRepairItem(tool, mat) || hasOverride) {
			boolean overrideMode = ModConfigs.GRID_REPAIR_OVERRIDE_MODE.get();
			if (overrideMode && !hasOverride && GridRepairOverrides.hasToolOverride(tool))
				return 0;

			return hasOverride ? override.multi : 1.0;
		}

		return 0;
	}

	public static boolean isValidRepairItem(ItemStack tool, ItemStack mat) {
		boolean disabled = ModConfigs.GRID_REPAIR_DISABLE_DEFAULTS.get();
		return !disabled && tool.isValidRepairItem(mat);
	}

	public static boolean isBlacklisted(Item item) {
		return BlacklistUtils.contains(item, ModConfigs.GRID_REPAIR_BLACKLIST.get());
	}
}
