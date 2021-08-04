package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

public class GridRepairHelper {
	public static double getMaterialValue(ItemStack tool, ItemStack mat) {
		GridRepairOverride.Override override = GridRepairOverride.getOverride(tool, mat);
		boolean hasOverride = override != null;
		if (checkDefault(tool, mat) || hasOverride) {
			boolean overrideMode = ModConfigs.GRID_REPAIR_OVERRIDE_MODE.get();
			if (overrideMode && !hasOverride && GridRepairOverride.hasToolOverride(tool))
				return 0;

			return hasOverride ? override.multi : 1.0;
		}

		return 0;
	}

	public static boolean checkDefault(ItemStack tool, ItemStack mat) {
		boolean disabled = ModConfigs.GRID_REPAIR_DISABLE_DEFAULTS.get();
		return !disabled && tool.getItem().isValidRepairItem(tool, mat);
	}

	public static boolean isBlacklisted(Item item) {
		ResourceLocation id = item.getRegistryName();
		if (id == null)
			return false;

		return ModConfigs.GRID_REPAIR_BLACKLIST.get().contains(id.toString());
	}
}
