package com.blakebr0.pickletweaks.tweaks;

import java.util.ListIterator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TweakFlintDrop {
	
	public static Item flintReplacement = null;
	public static int replacementMeta = 0;
	
	public static void configure(Configuration config){
		
		ConfigCategory category = config.getCategory("tweaks");
		String value = config.get(category.getName(), "flint_drop_item", "minecraft:gravel:0").getString();
		category.get("flint_drop_item").setComment("Define the item that should replace Flint from Gravel."
				+ "\n- Syntax: modid:itemid:meta");
		
		String[] parts = value.split(":");
		
		if(parts.length != 3){
			return;
		}
		
		String itemName = parts[0] + ":" + parts[1];
		int meta;
		
		try {
			meta = Integer.valueOf(parts[2]);
		} catch(NumberFormatException e){
			return;
		}
		
		if(!ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName))){
			return;
		}
		
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
		
		flintReplacement = item;
		replacementMeta = meta;
	}
	
    @SubscribeEvent
    public void onBlockHarvested(HarvestDropsEvent event){
        if(event.getState() != null && event.getState().getBlock() == Blocks.GRAVEL){
        	int itemsToAdd = 0;
            ListIterator<ItemStack> drops = event.getDrops().listIterator();
            boolean gravel = false;
            while(drops.hasNext()){
                Item item = drops.next().getItem();
                if(item == Items.FLINT){
                    drops.remove();
                    itemsToAdd++;
                } else if(item == Item.getItemFromBlock(Blocks.GRAVEL)){
                    gravel = true;
                }
            }
            if(!gravel && itemsToAdd > 0){
            	if(flintReplacement != null){
                    event.getDrops().add(new ItemStack(flintReplacement, itemsToAdd, replacementMeta));
            	}
            }
        }
    }
}
