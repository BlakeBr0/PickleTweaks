package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class GridRepairOverrides {
	private static final List<Override> OVERRIDES = new ArrayList<>();

	public static void onCommonSetup() {
		var values = ModConfigs.GRID_REPAIR_OVERRIDES.get();

		for (var value : values) {
			var parts = value.split("=");

			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid repair material syntax: {}", value);
				continue;
			}

			ItemStack tool;
			ItemStack mat;
			String tag;
			double multi = 1.0;

			var toolName = parts[0].split(":");
			if (toolName.length != 2) {
				PickleTweaks.LOGGER.error("Invalid repair material syntax: {}", value);
				continue;
			} else {
				var toolItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(toolName[0], toolName[1]));
				if (toolItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, tool item is null: {}", value);
					continue;
				}

				tool = new ItemStack(toolItem);
			}

			var matName = parts[1].split(":");
			if (matName.length == 3) {
				if (matName[2].contains("@")) {
					var matParts = matName[2].split("@");

					try {
						multi = Double.parseDouble(matParts[1]);
					} catch (NumberFormatException e) {
						PickleTweaks.LOGGER.error("Invalid repair material multiplier: {}", value);
						continue;
					}
				}

				if (parts[1].startsWith("tag:")) {
					tag = parts[1].substring(4).split("@")[0];

					OVERRIDES.add(new Override(tool, tag, multi));
					continue;
				}

				var matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(parts[1].split("@")[0]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is null: {}", value);
					continue;
				}

				mat = new ItemStack(matItem, 1);
			} else if (matName.length == 2) {
				if (parts[1].contains("@")) {
					var matParts = parts[1].split("@");

					try {
						multi = Double.parseDouble(matParts[1]);
					} catch (NumberFormatException e) {
						PickleTweaks.LOGGER.error("Invalid repair material multiplier: {}", value);
						continue;
					}
				}

				var matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(parts[1].split("@")[0]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is null: {}", value);
					continue;
				}

				mat = new ItemStack(matItem);
			} else {
				PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is invalid: {}", value);
				continue;
			}

			OVERRIDES.add(new Override(tool, mat, multi));
		}
	}
	
	public static Override getOverride(ItemStack tool, ItemStack material) {
		for (var entry : OVERRIDES) {
			if (entry.tool.is(tool.getItem())) {
				if (entry.material instanceof ItemStack stack) {
					if (!stack.is(material.getItem()))
						continue;

					return entry;
				} else if (entry.material instanceof String tagId) {
					var tag = ItemTags.create(new ResourceLocation(tagId));

					if (material.is(tag))
						return entry;
				}
			}
		}

		return null;
	}
	
	public static boolean hasToolOverride(ItemStack tool) {
		return OVERRIDES.stream().anyMatch(o -> o.tool.is(tool.getItem()));
	}
	
	public static class Override {
		public ItemStack tool;
		public Object material;
		public double multi;
		
		public Override(ItemStack tool, Object material) {
			this(tool, material, 1.0);
		}
		
		public Override(ItemStack tool, Object material, double multi) {
			this.tool = tool;
			this.material = material;
			this.multi = multi;
		}
	}
}
