package com.blakebr0.pickletweaks.tweaks;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TweakToolBreaking {
	
	public static Map<Item, Integer> overrides = new HashMap<>();
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("tweaks");
		String[] values = config.get(category.getName(), "tool_breaking_thresholds", new String[0]).getStringList();
		category.get("tool_breaking_thresholds").setComment("Here you can define custom tool breaking thresholds for tools." 
						+ "\n- Syntax: modid:itemid=threshold"
						+ "\n- Example: minecraft:iron_pickaxe=20"
						+ "\n- This makes it so Iron Pickaxes become useless with 20 uses left."
						+ "\n- This config is mostly meant for things like TF hammers that use more than 1 durability at a time, if they don't already work fine.");

		for (String value : values) {
			String[] parts = value.split("=");

			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid tool breaking threshold syntax length: " + value);
				continue;
			}
			
			String[] name = parts[0].split(":");
			int threshold;

			try {
				threshold = Integer.valueOf(parts[1]);
			} catch (NumberFormatException e) {
				PickleTweaks.LOGGER.error("Invalid tool breaking threshold value: " + value);
				continue;
			}
			
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(name[0], name[1]));
			if (item == null) {
				continue;
			}
			
			overrides.put(item, threshold);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getEntityPlayer() == null) { return; }

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack.isEmpty()) { return; }
        if (!(stack.getItem() instanceof ItemTool) && !(stack.getItem() instanceof ItemSword)) { return; }

        if (stack.isItemStackDamageable()) {
        	if (isBroken(stack, (stack.getItem() instanceof ItemSword ? 1 : 0))) {
        		event.setNewSpeed(0.0F);
        		//event.setNewSpeed(0.5F);
        	}
        }
	}
	
	// TODO: interesting
//	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getHarvester() == null) { return; }
		
		ItemStack stack = event.getHarvester().getHeldItemMainhand();
		if (stack.isEmpty()) { return; }
		if (stack.isItemStackDamageable()) {
			if (isBroken(stack, (stack.getItem() instanceof ItemSword ? 1 : 0))) {
				event.getDrops().clear();
			}
		}
	}
	// TODO: figure out how to make this work somehow
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakBlock(BlockEvent.BreakEvent event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getPlayer() == null) { return; }

		ItemStack stack = event.getPlayer().getHeldItemMainhand();
		if (stack.isEmpty()) { return; }
        if (!(stack.getItem() instanceof ItemTool) && !(stack.getItem() instanceof ItemSword)) { return; }

        if (stack.isItemStackDamageable()) {
        	if (isBroken(stack, (stack.getItem() instanceof ItemSword ? 1 : 0))) {
        		event.setCanceled(true);
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
			if (isBroken(stack, 0)) {
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
			if (isBroken(stack, (unbreaking > 0 ? 1 : 0))) {
				if (overrides.containsKey(stack.getItem())) {
					stack.setItemDamage(stack.getMaxDamage() - overrides.get(stack.getItem()));
				} else {
					stack.setItemDamage(stack.getMaxDamage() - 1);
				}
				event.setAmount(0.5F);
			}
		}
	}
	
	@SubscribeEvent
	public void onUseBow(RightClickItem event) {
		if (!ModConfig.confBrokenTools) { return; }
		if (event.getEntityPlayer() == null) { return; }
		
		ItemStack stack = event.getItemStack();
		if (stack.isEmpty()) { return; }
		if (!(stack.getItem() instanceof ItemBow)) { return; }
		
		if (stack.isItemStackDamageable()) {
			if (isBroken(stack, 0)) {
				event.setCanceled(true);
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
        		&& !(stack.getItem() instanceof ItemHoe)
        		&& !(stack.getItem() instanceof ItemBow)) { return; }

		ListIterator<String> itr = event.getToolTip().listIterator();
		if (stack.isItemStackDamageable()) {
			if (isBroken(stack, (stack.getItem() instanceof ItemSword ? 1 : 0))) {
            	while (itr.hasNext()) {
            		itr.next();
            		itr.add(Colors.RED + Utils.localize("tooltip.pt.broken"));
                	break;
            	}
            	
                while (itr.hasNext()) {
                    if (itr.next().contains(I18n.translateToLocal("attribute.name.generic.attackDamage"))) {
                	    itr.set(" 0.5 " + Utils.localize("attribute.name.generic.attackDamage"));
                    }
                }
			}
		}
	}
	
	public static boolean isBroken(ItemStack stack, int offset) {
		if (overrides.containsKey(stack.getItem())) {
			offset += overrides.get(stack.getItem());
		}
		return stack.getItemDamage() >= (stack.getMaxDamage() - offset);
	}
}
