package com.blakebr0.pickletweaks.feature.crafting;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class GridRepairOverride {
	
	public static final String[] DEFAULT_VALUES = new String[] { "minecraft:wooden_hoe=ore:plankWood", "minecraft:stone_hoe=minecraft:cobblestone", "minecraft:iron_hoe=minecraft:iron_ingot", "minecraft:golden_hoe=minecraft:gold_ingot", "minecraft:diamond_hoe=minecraft:diamond" };
	public static List<Override> overrides = new ArrayList<>();
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("grid_repair");
		String[] values = config.get(category.getName(), "_repair_materials", DEFAULT_VALUES).getStringList();
		category.get("_repair_materials").setComment("Here you can add your own repair materials to tools."
				+ "\n- Syntax: (tool=material) modid:itemid=modid:itemid:meta"
				+ "\n- Example: minecraft:stone_pickaxe=minecraft:apple:0"
				+ "\n- Note: the tool doesn't have a meta. If no meta is put for the material it will automatically become 0."
				+ "\n- You can also use the OreDictionary for repair materials."
				+ "\n- Example: ore:ingotIron would make all items in ingotIron a valid material."
				+ "\n- Note: additions using OreDictionary seem to require a restart."
				+ "\n- You can also specify an effectiveness multiplier by adding @<multiplier> to the repair material."
				+ "\n- Example: adding @0.5 after the repair material would make that material only 50 percent effective.");
		
		for (String value : values) {
			String[] parts = value.split("=");
			
			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid repair material syntax: {}", value);
				continue;
			}
			
			ItemStack tool;
			ItemStack mat;
			String ore;
			double multi = 1.0;
			
			String[] toolName = parts[0].split(":");
			if (toolName.length != 2) {
				PickleTweaks.LOGGER.error("Invalid repair material syntax: {}", value);
				continue;
			} else {
				Item toolItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(toolName[0], toolName[1]));
				if (toolItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, tool item is null: {}", value);
					continue;
				}
				tool = new ItemStack(toolItem);
			}
			
			String[] matName = parts[1].split(":");
			if (matName.length == 3) {
				int meta;
				if (matName[2].contains("@")) {
					String metaThings[] = matName[2].split("@");
					try {
						meta = Integer.valueOf(metaThings[0]);
						multi = Double.valueOf(metaThings[1]);
					} catch (NumberFormatException e) {
						PickleTweaks.LOGGER.error("Invalid repair material syntax metadata and mutliplier: {}", value);
						continue;
					}
				} else {
					try {
						meta = Integer.valueOf(matName[2]);
					} catch (NumberFormatException e) {
						PickleTweaks.LOGGER.error("Invalid repair material syntax metadata: {}", value);
						continue;
					}
				}
				Item matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matName[0], matName[1]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is null: {}", value);
					continue;
				}
				mat = new ItemStack(matItem, 1, meta);
			} else if (matName.length == 2) {
				if (parts[1].startsWith("ore:")) {
					ore = parts[1].substring(4);
					if (ore.contains("@")) {
						String metaParts[] = ore.split("@");
						try {
							multi = Double.valueOf(metaParts[1]);
						} catch (NumberFormatException e) {
							PickleTweaks.LOGGER.error("Invalid repair material syntax mutliplier: {}", value);
							continue;
						}
						overrides.add(new Override(tool, metaParts[0], multi));
						continue;
					}
					overrides.add(new Override(tool, ore));
					continue;
				}
				Item matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matName[0], matName[1]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is null: {}", value);
					continue;
				}
				mat = new ItemStack(matItem);
			} else {
				PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is invalid: {}", value);
				continue;
			}
			
			overrides.add(new Override(tool, mat, multi));
		}
	}
	
	public static Override getOverride(ItemStack tool, ItemStack mat) {
		for (Override entry : overrides) {
			if (entry.tool.isItemEqualIgnoreDurability(tool)) {
				if (entry.mat instanceof ItemStack) {
					ItemStack stack = (ItemStack) entry.mat;
					if (!stack.isItemEqual(mat)) {
						continue;
					}
					return entry;
				} else if (entry.mat instanceof String) {
					String ore = (String) entry.mat;
					if (OreDictionary.doesOreNameExist(ore)) {
						if (!OreDictionary.getOres(ore).stream().anyMatch(is -> is.getMetadata() == OreDictionary.WILDCARD_VALUE && is.getItem() == mat.getItem() || is.isItemEqual(mat))) {
							continue;
						}
						return entry;
					}
				}
			}
		}
		return null;
	}
	
	public static boolean hasToolOverride(ItemStack tool) {
		return overrides.stream().anyMatch(o -> o.tool.isItemEqualIgnoreDurability(tool));
	}
	
	public static class Override {
		
		public ItemStack tool;
		public Object mat;
		public double multi;
		
		public Override(ItemStack tool, Object mat) {
			this(tool, mat, 1.0);
		}
		
		public Override(ItemStack tool, Object mat, double multi) {
			this.tool = tool;
			this.mat = mat;
			this.multi = multi;
		}
	}
}
