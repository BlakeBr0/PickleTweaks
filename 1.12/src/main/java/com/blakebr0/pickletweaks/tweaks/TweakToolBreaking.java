package com.blakebr0.pickletweaks.tweaks;

import java.util.ListIterator;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TweakToolBreaking {
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getEntityPlayer() == null) { return; }

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack.isEmpty()) { return; }
        if (!(stack.getItem() instanceof ItemTool) && !(stack.getItem() instanceof ItemSword)) { return; }

        if (stack.isItemStackDamageable()) {
        	if (stack.getItemDamage() >= stack.getMaxDamage() - (stack.getItem() instanceof ItemSword ? 1 : 0)) {
        		event.setNewSpeed(0.0F);
        	}
        }
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onUseHoe(UseHoeEvent event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getEntityPlayer() == null) { return; }
		
		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack.isEmpty()) { return; }
		if (!(stack.getItem() instanceof ItemHoe)) { return; }
		
		if (stack.isItemStackDamageable()) {
			if (stack.getItemDamage() >= stack.getMaxDamage()) {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onHitEntity(LivingHurtEvent event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (!event.getSource().getDamageType().equals("player")) { return; }
		if (!(event.getSource().getTrueSource() instanceof EntityPlayer)) { return; }
		
		EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
		ItemStack stack = player.getHeldItemMainhand();
		
		if (stack.isEmpty()) { return; }
		if (!(stack.getItem() instanceof ItemTool) 
        		&& !(stack.getItem() instanceof ItemSword)
        		&& !(stack.getItem() instanceof ItemHoe)) { return; }
		
		if (stack.isItemStackDamageable()) {
			int unbreaking = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
			if (stack.getItemDamage() >= stack.getMaxDamage() - (unbreaking > 0 ? 1 : 0)) {
				if (!(stack.getItem() instanceof ItemHoe)) {
					stack.setItemDamage(stack.getMaxDamage() - 1);
				}
				event.setAmount(0.0F);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getEntityPlayer() == null) { return; }
		
		ItemStack stack = event.getItemStack();
		if (!(stack.getItem() instanceof ItemTool) 
        		&& !(stack.getItem() instanceof ItemSword) 
        		&& !(stack.getItem() instanceof ItemHoe)) { return; }

		ListIterator<String> itr = event.getToolTip().listIterator();
		if (stack.isItemStackDamageable()) {
			if (stack.getItemDamage() >= stack.getMaxDamage() - (stack.getItem() instanceof ItemSword ? 1 : 0)) {
            	while (itr.hasNext()) {
            		itr.next();
            		itr.add(Colors.RED + Utils.localize("tooltip.pt.broken"));
                	break;
            	}
            	
                while (itr.hasNext()) {
                    if (itr.next().contains(I18n.translateToLocal("attribute.name.generic.attackDamage"))) {
                	    itr.set(" 0 " + Utils.localize("attribute.name.generic.attackDamage"));
                    }
                }
			}
		}
	}
}
