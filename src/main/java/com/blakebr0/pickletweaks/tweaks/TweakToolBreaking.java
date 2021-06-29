package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.cucumber.event.ScytheHarvestCropEvent;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
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

public final class TweakToolBreaking {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageableItem() && isValidTool(item)) {
			if (isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakBlock(BlockEvent.BreakEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageableItem() && isValidTool(item)) {
			if (isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onUseHoe(UseHoeEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = event.getContext().getItemInHand();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageableItem() && item instanceof HoeItem) {
			if (isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onAttackEntity(AttackEntityEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (stack.isDamageableItem()) {
			Item item = stack.getItem();
			if (isValidTool(item) && isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageableItem() && (item instanceof ShootableItem || item instanceof ShearsItem)) {
			if (isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageableItem() && (item instanceof ShovelItem || item instanceof AxeItem)) {
			if (isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		Item item = stack.getItem();
		if (stack.isDamageableItem() && item instanceof ShearsItem) {
			if (isBroken(stack)) {
				sendBrokenMessage(player, stack);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onScytheHarvestCrop(ScytheHarvestCropEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		PlayerEntity player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		if (stack.isDamageableItem() && isBroken(stack)) {
			sendBrokenMessage(player, stack);
			event.setCanceled(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		ListIterator<ITextComponent> tooltip = event.getToolTip().listIterator();
		ItemStack stack = event.getItemStack();

		if (stack.isDamageableItem()) {
			Item item = stack.getItem();
			if (isValidTool(item) || item instanceof ShootableItem) {
				if (isBroken(stack)) {
					tooltip.next();
					tooltip.add(ModTooltips.BROKEN.color(TextFormatting.RED).build());
				}
			}
		}
	}

	public static boolean isValidTool(Item item) {
		return item instanceof ToolItem || item instanceof SwordItem || item instanceof ShearsItem;
	}

	public static boolean isBroken(ItemStack stack) {
		return stack.getMaxDamage() > 2 && stack.getDamageValue() >= stack.getMaxDamage() - 2;
	}

	private static void sendBrokenMessage(PlayerEntity player, ItemStack stack) {
		player.displayClientMessage(ModTooltips.YOUR_ITEM_IS_BROKEN.args(stack.getHoverName()).color(TextFormatting.WHITE).build(), true);
	}
}
