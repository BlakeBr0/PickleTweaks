package com.blakebr0.pickletweaks.feature.crafting;

import java.util.HashMap;
import java.util.Map;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GridRepairOverride {
	
	public static Map<ItemStack, ItemStack> overrides = new HashMap<>();
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("grid_repair");
		String[] values = config.get(category.getName(), "_repair_materials", new String[0]).getStringList();
		category.get("_repair_materials").setComment("Here you can set your own repair materials to tools.");
		
		for (String value : values) {
			String[] parts = value.split("=");
			
			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid repair material syntax: {}", value);
				continue;
			}
			
			ItemStack tool;
			ItemStack mat;
			
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
				tool = StackHelper.to(toolItem);
			}
			
			String[] matName = parts[1].split(":");
			if (matName.length == 3) {
				int meta;
				try {
					meta = Integer.valueOf(matName[2]);
				} catch (NumberFormatException e) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax metadata: {}", value);
					continue;
				}
				Item matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matName[0], matName[1]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is null: {}", value);
					continue;
				}
				mat = StackHelper.to(matItem, 1, meta);
			} else if (matName.length == 2) {
				Item matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matName[0], matName[1]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is null: {}", value);
					continue;
				}
				mat = StackHelper.to(matItem);
			} else {
				PickleTweaks.LOGGER.error("Invalid repair material syntax, material item is invalid: {}", value);
				continue;
			}
			
			overrides.put(tool, mat);
		}
	}
	
	public static boolean hasOverride(ItemStack tool, ItemStack mat) {
		for (Map.Entry<ItemStack, ItemStack> entry : overrides.entrySet()) {
			if (entry.getKey().isItemEqualIgnoreDurability(tool) && entry.getValue().isItemEqual(mat)){
				return true;
			}
		}
		return false;
	}
}
