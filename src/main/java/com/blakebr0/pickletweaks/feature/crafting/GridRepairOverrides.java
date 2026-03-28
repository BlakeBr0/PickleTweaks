package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class GridRepairOverrides {
	private static final List<Override> OVERRIDES = new ArrayList<>();

	public static void onCommonSetup() {
		var values = ModConfigs.GRID_REPAIR_OVERRIDES.get();

		for (var value : values) {
			var parts = value.split("=");

			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid repair material syntax, incorrect amount of arguments (must be 2): {}", value);
				continue;
			}

			var tool = OverrideIngredient.parse(parts[0]);
			if (tool == null)
				continue;

			var material = OverrideIngredient.parse(parts[1]);
			if (material == null)
				continue;

			double multi = parseMulti(parts[1]);

			OVERRIDES.add(new Override(tool, material, multi));
		}
	}
	
	public static Override getOverride(ItemStack tool, ItemStack material) {
		for (var entry : OVERRIDES) {
			if (entry.tool.is(tool) && entry.material.is(material))
				return entry;
		}

		return null;
	}
	
	public static boolean hasToolOverride(ItemStack tool) {
		return OVERRIDES.stream().anyMatch(o -> o.tool.is(tool));
	}

	private static double parseMulti(String value) {
		if (value.contains("@")) {
			var parts = value.split("@");

			try {
				return Double.parseDouble(parts[1]);
			} catch (NumberFormatException e) {
				PickleTweaks.LOGGER.error("Invalid repair material multiplier: {}", value);
			}
		}

		return 1.0;
	}
	
	public static class Override {
		public OverrideIngredient tool;
		public OverrideIngredient material;
		public double multi;
		
		public Override(OverrideIngredient tool, OverrideIngredient material) {
			this(tool, material, 1.0);
		}
		
		public Override(OverrideIngredient tool, OverrideIngredient material, double multi) {
			this.tool = tool;
			this.material = material;
			this.multi = multi;
		}
	}

	public enum OverrideIngredientType {
		ITEM,
		TAG
	}

	public static class OverrideIngredient {
		private final Item item;
		private final TagKey<Item> tag;
		private final OverrideIngredientType type;

		private OverrideIngredient(Item item, TagKey<Item> tag, OverrideIngredientType type) {
			this.item = item;
			this.tag = tag;
			this.type = type;
		}

		public boolean is(ItemStack stack) {
			return switch (this.type) {
				case ITEM -> stack.is(this.item);
				case TAG -> stack.is(this.tag);
			};
		}

		public static OverrideIngredient item(Item item) {
			return new OverrideIngredient(item, null, OverrideIngredientType.ITEM);
		}

		public static OverrideIngredient tag(TagKey<Item> tag) {
			return new OverrideIngredient(null, tag, OverrideIngredientType.TAG);
		}

		public static OverrideIngredient parse(String value) {
			var parts = value.split(":");

			if (parts.length == 3) { // tag case
				if (value.startsWith("tag:")) {
					var tag = Identifier.tryParse(value.substring(4).split("@")[0]);
					if (tag == null) {
						PickleTweaks.LOGGER.error("Invalid repair material tag: {}", value);
						return null;
					}

					return OverrideIngredient.tag(ItemTags.create(tag));
				} else {
					PickleTweaks.LOGGER.error("Invalid repair material prefix (should be 'tag:'): {}", value);
				}
			} else if (parts.length == 2) { // item case
				var id = Identifier.tryParse(value.split("@")[0]);
				if (id == null) {
					PickleTweaks.LOGGER.error("Invalid repair material item: {}", value);
					return null;
				}

				var item = BuiltInRegistries.ITEM.get(id);
				if (item.isEmpty() || item.get().value() == Items.AIR) {
					PickleTweaks.LOGGER.error("Invalid repair material item is null: {}", value);
					return null;
				}

				return OverrideIngredient.item(item.get().value());
			} else {
				PickleTweaks.LOGGER.error("Invalid repair material syntax: {}", value);
			}

			return null;
		}
	}
}
