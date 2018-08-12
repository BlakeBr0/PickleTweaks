package com.blakebr0.pickletweaks.feature.reinforcement;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverride.Override;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ReinforcementOverride {
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("tool_reinforcement");
		String[] values = config.get(category.getName(), "custom_reinforcements", new String[0]).getStringList();
		category.get("custom_reinforcements").setComment("Here you can add your own reinforcement materials to tool reinforcement."
				+ "\n- Syntax: (material=value) modid:itemid:meta=value"
				+ "\n- Example: minecraft:apple=20"
				+ "\n- Note: if you don't put a meta value it will default to 0.");
		
		for (String value : values) {
			String[] parts = value.split("=");
			
			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid reinforcement material syntax: {}", value);
				continue;
			}
			
			ItemStack material;
			int val;
			
			try {
				val = Integer.valueOf(parts[1]);
			} catch (NumberFormatException e) {
				PickleTweaks.LOGGER.error("Invalid reinforcement value: {}", value);
				continue;
			}
			
			String[] matName = parts[0].split(":");
			if (matName.length == 3) {
				int meta;
				try {
					meta = Integer.valueOf(matName[2]);
				} catch (NumberFormatException e) {
					PickleTweaks.LOGGER.error("Invalid reinforcement material syntax metadata: {}", value);
					continue;
				}
				Item matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matName[0], matName[1]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid reinforcement material syntax, material item is null: {}", value);
					continue;
				}
				material = new ItemStack(matItem, 1, meta);
			} else if (matName.length == 2) {
				Item matItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matName[0], matName[1]));
				if (matItem == null) {
					PickleTweaks.LOGGER.error("Invalid reinforcement material syntax, material item is null: {}", value);
					continue;
				}
				material = new ItemStack(matItem);
			} else {
				PickleTweaks.LOGGER.error("Invalid reinforcement material syntax, material item is invalid: {}", value);
				continue;
			}
			
			ReinforcementHandler.REINFORCEMENTS.put(material, val);
		}
	}
}
