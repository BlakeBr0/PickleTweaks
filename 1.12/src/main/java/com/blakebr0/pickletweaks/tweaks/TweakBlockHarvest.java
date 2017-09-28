package com.blakebr0.pickletweaks.tweaks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class TweakBlockHarvest {

	public static void configure(Configuration config){
		
		ConfigCategory category = config.getCategory("tweaks");
		String[] values = config.get(category.getName(), "harvest_level", new String[0]).getStringList();
		category.get("harvest_level").setComment("Here you can override the mining level of blocks."
				+ "\n- Syntax: modid:blockid:meta=harvestlevel"
				+ "\n- Example: minecraft:stone:0=3"
				+ "\n- 'meta' can be set to -1 to apply to all metas."
				+ "\nYou can also override using OreDictionary entries."
				+ "\n- Syntax: ore:orevalue=harvestlevel"
				+ "\n- Example: ore:oreCopper=2");
		
		for(String value : values){
			String[] parts = value.split("=");
					
			String blockName = parts[0];
			int meta;
			int level;
			
			if(blockName.startsWith("ore:") && parts.length == 2){
				try {
					level = Integer.valueOf(parts[1]);
				} catch(NumberFormatException e){
					continue;
				}
				
				for(ItemStack stack : OreDictionary.getOres(blockName.substring(4))){
					int[] metadata = new int[]{ stack.getMetadata() };
					if(stack.getMetadata() == OreDictionary.WILDCARD_VALUE){
						metadata = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
					}
					Block oreBlock = Block.getBlockFromItem(stack.getItem());
					for(int metar : metadata){
						if(oreBlock != Blocks.AIR){
							oreBlock.setHarvestLevel(oreBlock.getHarvestTool(oreBlock.getStateFromMeta(metar)), level, oreBlock.getStateFromMeta(metar));
						}
					}
				}
				continue;
			}
					
			if(parts.length != 2){
				continue;
			}
			
			String[] part = parts[0].split(":");
			blockName = part[0] + ":" + part[1];
			
			try {
				meta = Integer.valueOf(part[2]);
				level = Integer.valueOf(parts[1]);
			} catch(NumberFormatException e){
				continue;
			}
			
			if(!ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(blockName))){
				continue;
			}
			
			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));
			
			if(meta == -1){
				block.setHarvestLevel(block.getHarvestTool(block.getDefaultState()), level);
			} else {
				block.setHarvestLevel(block.getHarvestTool(block.getStateFromMeta(meta)), level, block.getStateFromMeta(meta));
			}
		}
	}
}
