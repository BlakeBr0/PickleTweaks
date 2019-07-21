/*
package com.blakebr0.pickletweaks.tweaks;

import java.util.ListIterator;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TweakWeaponUselessifier {
	@SubscribeEvent
	public void breakSpeed(PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer() == null) {
			return;
		}

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();

		if (stack.isEmpty()) {
			return;
		}

		if (isUselessTool(stack.getItem())) {
			event.setNewSpeed(0);
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (!(event.getSource().getDamageType().equals("player"))) {
			return;
		}
		
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
			return;
		}

		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		ItemStack stack = player.getHeldItemMainhand();

		if (stack.isEmpty()) {
			return;
		}

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event) {
		if (event.getEntityPlayer() == null) {
			return;
		}
		
		if (!ToolTweaks.uselessWeapons.contains(event.getItemStack().getItem())) {
			return;
		}

		ListIterator<String> tooltip = event.getToolTip().listIterator();
		while (tooltip.hasNext()) {
			if (tooltip.next().contains(I18n.translateToLocal("attribute.name.generic.attackDamage"))) {
				tooltip.set(" 0 " + Utils.localize("attribute.name.generic.attackDamage"));
			}
		}

		if (isUselessTool(event.getItemStack().getItem())) {
			event.getToolTip().add(TextFormatting.RED + Utils.localize("tooltip.useless_weapon_1"));
			event.getToolTip().add(TextFormatting.RED + Utils.localize("tooltip.useless_tool_2"));
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(RightClickBlock event) {
		if (event.getEntityPlayer() == null) {
			return;
		}

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();

		if (stack.isEmpty()) {
			return;
		}

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	public static boolean isUselessTool(Item item) {
		if (item == null) {
			return false;
		}
		
		if (!ToolTweaks.uselessWeapons.contains(item)) {
			return false;
		}

		return true;
	}
}
*/
