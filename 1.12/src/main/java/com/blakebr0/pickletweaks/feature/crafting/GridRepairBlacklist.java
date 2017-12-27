package com.blakebr0.pickletweaks.feature.crafting;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GridRepairBlacklist {
	
	public static ArrayList<Item> blacklist = new ArrayList<>();
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("grid_repair");
		String[] values = config.get(category.getName(), "_tool_blacklist", new String[0]).getStringList();
		category.get("_tool_blacklist").setComment("Here you can blacklist tools from grid repairing."
				+ "\n- Syntax: modid:itemid"
				+ "\n- Example: minecraft:wooden_pickaxe");
		
		for (String value : values) {			
			if (!ForgeRegistries.ITEMS.containsKey(new ResourceLocation(value))) {
				continue;
			}
			
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(value));
			
			blacklist.add(item);
		}
	}
}
