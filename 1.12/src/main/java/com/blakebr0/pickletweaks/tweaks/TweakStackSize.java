package com.blakebr0.pickletweaks.tweaks;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TweakStackSize {

	public static final String[] DEFAULT_VALUES = new String[]{ "minecraft:sign=64", "minecraft:bucket=64", "minecraft:snowball=64", "minecraft:ender_pearl=64", "minecraft:egg=64", "minecraft:minecart=16" };
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("tweaks");
		String[] values = config.get(category.getName(), "max_stack_size", DEFAULT_VALUES).getStringList();
		category.get("max_stack_size").setComment("Here you can override the max stack size of an itemstack."
				+ "\n- Syntax: modid:itemid=stacksize"
				+ "\n- Example: minecraft:egg=64");
		
		for (String value : values) {
			String[] parts = value.split("=");
			
			if (parts.length != 2) {
				continue;
			}
			
			String itemName = parts[0];
			int size;
			
			try {
				size = Integer.valueOf(parts[1]);
			} catch (NumberFormatException e) {
				continue;
			}
			
			if (!ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName))) {
				continue;
			}
			
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
			
			item.setMaxStackSize(size);
		}
	}
}
