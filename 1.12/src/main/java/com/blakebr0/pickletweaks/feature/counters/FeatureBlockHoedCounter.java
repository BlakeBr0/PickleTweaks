package com.blakebr0.pickletweaks.feature.counters;

import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
// TODO: put in hoe tooltip thingy
public class FeatureBlockHoedCounter {
	
//	@SubscribeEvent
/*	public void onBlockHoed(UseHoeEvent event){
		ItemStack stack = event.getEntityLiving().getHeldItemMainhand();
		if(stack != null && stack.getItem() != null){
			if(stack.getItem() instanceof ItemHoe || ToolTweaks.BLOCKHOED_WHITELIST.contains(stack.getItem())){
				NBTTagCompound tag = stack.getTagCompound();
				if(tag == null){
					tag = new NBTTagCompound();
					tag.setInteger("blocks_hoed", 0);
					stack.setTagCompound(tag);
				}
				tag.setInteger("blocks_hoed", tag.getInteger("blocks_hoed") + 1);
			}
		}
	}

 //   @SubscribeEvent
    public void onItemToolTip(ItemTooltipEvent event) {
        if(event.getEntityPlayer() == null){
            return;
        }
              
        if(!(event.getItemStack().getItem() instanceof ItemHoe) && !(ToolTweaks.BLOCKHOED_WHITELIST.contains(event.getItemStack().getItem()))){
        	return;
        }
   
		NBTTagCompound tag = event.getItemStack().getTagCompound();
		if(tag == null){
			tag = new NBTTagCompound();
			tag.setInteger("blocks_hoed", 0);
			event.getItemStack().setTagCompound(tag);
		}
        
        event.getToolTip().add("");
        event.getToolTip().add(TextFormatting.GRAY + new TextComponentTranslation("tooltip.blocks_hoed").getFormattedText() + TextFormatting.GRAY + ": " + TextFormatting.YELLOW + event.getItemStack().getTagCompound().getInteger("blocks_hoed"));
    }*/

}
