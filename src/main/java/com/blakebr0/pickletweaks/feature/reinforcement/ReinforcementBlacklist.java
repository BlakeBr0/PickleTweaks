package com.blakebr0.pickletweaks.feature.reinforcement;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ReinforcementBlacklist {

	public static ArrayList<Item> blacklist = new ArrayList<>();
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("tool_reinforcement");
		String[] values = config.get(category.getName(), "tool_blacklist", new String[0]).getStringList();
		category.get("tool_blacklist").setComment("Here you can blacklist tools from reinforcement."
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
	
	public static boolean isBlacklisted(Item tool) {
		return blacklist.contains(tool);
	}
}
