package com.blakebr0.pickletweaks.feature.counters;

import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
// TODO: put into sword & bow tooltip thingies
public class FeatureKillCounter {
	
//	@SubscribeEvent
/*	public void onEntityKilled(LivingDeathEvent event){
        DamageSource source = event.getSource();
        Entity entity = source.getTrueSource();
        if(entity != null && entity instanceof EntityPlayer){
        	EntityPlayer player = (EntityPlayer)entity;
            ItemStack stack = player.getHeldItemMainhand();
            if(stack != null && stack.getItem() != null){
	            if(stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow || ToolTweaks.KILLS_WHITELIST.contains(stack.getItem())){
	            	NBTTagCompound tag = stack.getTagCompound();
	            	if(tag == null){
	            		tag = new NBTTagCompound();
	            		tag.setInteger("kills", 0);
	            		stack.setTagCompound(tag);
	            	}
	            	tag.setInteger("kills", tag.getInteger("kills") + 1);
	            }
            }
        }
	}

 //   @SubscribeEvent
    public void onItemToolTip(ItemTooltipEvent event) {
        if(event.getEntityPlayer() == null){
            return;
        }
              
        if(!(event.getItemStack().getItem() instanceof ItemSword) && !(event.getItemStack().getItem() instanceof ItemBow) && !(ToolTweaks.KILLS_WHITELIST.contains(event.getItemStack().getItem()))){
        	return;
        }
   
		NBTTagCompound tag = event.getItemStack().getTagCompound();
		if(tag == null){
			tag = new NBTTagCompound();
			tag.setInteger("kills", 0);
			event.getItemStack().setTagCompound(tag);
		}
        
        event.getToolTip().add("");
        event.getToolTip().add(TextFormatting.GRAY + new TextComponentTranslation("tooltip.kills").getFormattedText() + TextFormatting.GRAY + ": " + TextFormatting.YELLOW + event.getItemStack().getTagCompound().getInteger("kills"));
    }*/
}
