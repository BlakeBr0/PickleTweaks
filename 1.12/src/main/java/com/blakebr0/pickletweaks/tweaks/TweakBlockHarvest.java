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
				+ "\n- Example: ore:oreCopper=2"
				+ "\nYou can also set the harvest tool (if required)."
				+ "\nTools are 'pickaxe' 'axe' 'shovel'."
				+ "\n- Syntax: modid:blockid:meta=harvestlevel-tool"
				+ "\n- Syntax ore:orevalue=harvestlevel-tool");
		
		for(String value : values){
			String[] parts = value.split("=");
					
			String blockName = parts[0];
			int meta;
			int level;
			
			String parts2[] = parts[1].split("-");
			String lvl, tool = null;
			
			if (parts2.length == 2) {
				lvl = parts2[0];
				tool = parts2[1];
			} else {
				lvl = parts2[0];
			}
			
			if(blockName.startsWith("ore:") && parts.length == 2){
				try {
					level = Integer.valueOf(lvl);
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
							if (tool == null) {
								tool = oreBlock.getHarvestTool(oreBlock.getStateFromMeta(metar));
							}
							oreBlock.setHarvestLevel(tool, level, oreBlock.getStateFromMeta(metar));
						}
					}
				}
				continue;
			}
					
			if (parts.length != 2){
				continue;
			}
			
			String[] part = parts[0].split(":");
			blockName = part[0] + ":" + part[1];
			
			try {
				meta = Integer.valueOf(part[2]);
				level = Integer.valueOf(lvl);
			} catch(NumberFormatException e){
				continue;
			}
			
			if(!ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(blockName))){
				continue;
			}
			
			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));
			
			if(meta == -1){
				if (tool == null) {
					tool = block.getHarvestTool(block.getDefaultState());
				}
				block.setHarvestLevel(tool, level);
			} else {
				if (tool == null) {
					tool = block.getHarvestTool(block.getStateFromMeta(meta));
				}
				block.setHarvestLevel(tool, level, block.getStateFromMeta(meta));
			}
		}
	}
}
