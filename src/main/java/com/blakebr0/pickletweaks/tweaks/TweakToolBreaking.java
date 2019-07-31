package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ListIterator;

public class TweakToolBreaking {
	// TODO: Convert to PlayerInteractEvent.LeftClickBlock
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageable() && (item instanceof ToolItem || item instanceof SwordItem)) {
			if (isBroken(stack)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakBlock(BlockEvent.BreakEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ItemStack stack = event.getPlayer().getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageable() && (item instanceof ToolItem || item instanceof SwordItem)) {
			if (isBroken(stack)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onUseHoe(UseHoeEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ItemStack stack = event.getContext().getItem();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageable() && item instanceof HoeItem) {
			if (isBroken(stack)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onAttackEntity(AttackEntityEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		if (stack.isDamageable()) {
			Item item = stack.getItem();
			if (item instanceof ToolItem || item instanceof SwordItem || item instanceof HoeItem) {
				if (isBroken(stack)) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageable() && item instanceof ShootableItem) {
			if (isBroken(stack)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageable() && item instanceof ShovelItem) {
			if (isBroken(stack)) {
				event.setCanceled(true);
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ListIterator<ITextComponent> itr = event.getToolTip().listIterator();
		ItemStack stack = event.getItemStack();

		if (stack.isDamageable()) {
			Item item = stack.getItem();
			if (item instanceof ToolItem || item instanceof SwordItem || item instanceof HoeItem || item instanceof ShootableItem) {
				if (isBroken(stack)) {
					itr.next();
					itr.add(ModTooltips.BROKEN.color(TextFormatting.RED).build());
				}
			}
		}
	}

	public static boolean isBroken(ItemStack stack) {
		return stack.getMaxDamage() > 2 && stack.getDamage() >= stack.getMaxDamage() - 2;
	}
}
