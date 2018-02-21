package com.blakebr0.pickletweaks.tweaks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class TweakBlockHardness {

	public static void configure(Configuration config) {

		ConfigCategory category = config.getCategory("tweaks");
		String[] values = config.get(category.getName(), "block_hardness", new String[0]).getStringList();
		category.get("block_hardness").setComment("Here you can override the hardness of blocks." 
						+ "\n- Syntax: modid:blockid=hardness"
						+ "\n- Example: minecraft:stone=100" 
						+ "\nYou can also override using OreDictionary entries."
						+ "\n- Syntax: ore:orevalue=hardness" 
						+ "\n- Example: ore:oreCopper=25");

		for (String value : values) {
			String[] parts = value.split("=");

			String blockName = parts[0];
			float hardness;

			if (parts.length != 2) {
				continue;
			}

			try {
				hardness = Float.valueOf(parts[1]);
			} catch (NumberFormatException e) {
				continue;
			}

			if (blockName.startsWith("ore:")) {
				for (ItemStack stack : OreDictionary.getOres(blockName.substring(4))) {
					int[] metadata = new int[] { stack.getMetadata() };
					if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
						metadata = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
					}
					Block oreBlock = Block.getBlockFromItem(stack.getItem());
					for (int metar : metadata) {
						if (oreBlock != Blocks.AIR) {
							oreBlock.setHardness(hardness);
						}
					}
				}
				continue;
			}

			if (!ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(blockName))) {
				continue;
			}

			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));

			block.setHardness(hardness);
		}
	}
}
