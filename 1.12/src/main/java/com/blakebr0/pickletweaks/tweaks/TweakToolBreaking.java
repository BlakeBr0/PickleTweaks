package com.blakebr0.pickletweaks.tweaks;

import java.util.ListIterator;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TweakToolBreaking {
	
	@SubscribeEvent
	public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer() == null) { return; }

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack == null) { return; }
        if (!(stack.getItem() instanceof ItemTool) 
        		&& !(stack.getItem() instanceof ItemSword) 
        		&& !(stack.getItem() instanceof ItemHoe)) { return; }

        if (stack.isItemStackDamageable()) {
        	if (stack.getItemDamage() >= stack.getMaxDamage()) {
        		event.setNewSpeed(0.0F);
        	}
        }
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (event.getEntityPlayer() == null) { return; }
		
		ItemStack stack = event.getItemStack();
		if (!(stack.getItem() instanceof ItemTool) 
        		&& !(stack.getItem() instanceof ItemSword) 
        		&& !(stack.getItem() instanceof ItemHoe)) { return; }

		ListIterator<String> itr = event.getToolTip().listIterator();
		if (stack.isItemStackDamageable()) {
			if (stack.getItemDamage() >= stack.getMaxDamage()) {
            	while (itr.hasNext()) {
            		itr.next();
            		itr.add(Colors.RED + Colors.ITALICS + Utils.localize("tooltip.pt.broken"));
                	break;
            	}
			}
		}
	}
}
