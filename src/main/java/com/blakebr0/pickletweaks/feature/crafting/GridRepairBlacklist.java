package com.blakebr0.pickletweaks.feature.crafting;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class GridRepairBlacklist {
	
	public static ArrayList<Item> blacklist = new ArrayList<>();
	
//	public static void configure(Configuration config) {
//		ConfigCategory category = config.getCategory("grid_repair");
//		String[] values = config.get(category.getName(), "_tool_blacklist", new String[0]).getStringList();
//		category.get("_tool_blacklist").setComment("Here you can blacklist tools from grid repairing."
//				+ "\n- Syntax: modid:itemid"
//				+ "\n- Example: minecraft:wooden_pickaxe");
//
//		for (String value : values) {
//			if (!ForgeRegistries.ITEMS.containsKey(new ResourceLocation(value))) {
//				continue;
//			}
//
//			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(value));
//
//			blacklist.add(item);
//		}
//	}
	
	public static boolean isBlacklisted(Item tool) {
		return blacklist.contains(tool);
	}
}
