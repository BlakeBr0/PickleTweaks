package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.pickletweaks.lib.TFToolHelper;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TweakToolHarvest {
	
	public static void configure(Configuration config){
		ConfigCategory category = config.getCategory("tweaks");
		String[] values = config.get(category.getName(), "mining_level", new String[0]).getStringList();
		category.get("mining_level").setComment("Here you can override the mining level of tools."
				+ "\n- Syntax: modid:itemid=harvestlevel-toolclass"
				+ "\n- Example: minecraft:stone_pickaxe=3-pickaxe"
				+ "\n- 'toolclass' can be 'pickaxe', 'axe', 'shovel', or 'null'.");
		
		for(String value : values){
			String[] parts = value.split("=");
			
			if(parts.length != 2){
				continue;
			}
			
			String itemName = parts[0];
			String[] part = parts[1].split("-");
			int level;
			String tool = part[1];
			
			try {
				level = Integer.valueOf(part[0]);
			} catch(NumberFormatException e){
				continue;
			}
			
			if(!ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName))){
				continue;
			}
			
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
			
			if (itemName.startsWith("thermalfoundation") && Loader.isModLoaded("thermalfoundation")) {
				TFToolHelper.setHarvestLevel(item, level);
			} else {
				item.setHarvestLevel(tool, level);
			}
		}
	}
}
