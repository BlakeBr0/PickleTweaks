package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.cucumber.event.ScytheHarvestCropEvent;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.ShearsItem;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public final class TweakToolBreaking {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (stack.isDamageableItem() && isValidTool(stack)) {
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

		var player = event.getPlayer();
		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (stack.isDamageableItem() && isValidTool(stack)) {
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

		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

        if (stack.isDamageableItem() && isValidTool(stack) && isBroken(stack)) {
            sendBrokenMessage(player, stack);
            event.setCanceled(true);
        }
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		var item = stack.getItem();
		if (stack.isDamageableItem() && (item instanceof ProjectileWeaponItem || item instanceof ShearsItem)) {
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

		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		if (stack.isDamageableItem() && isValidTool(stack)) {
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

		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		var item = stack.getItem();
		if (stack.isDamageableItem() && item instanceof ShearsItem && isBroken(stack)) {
			sendBrokenMessage(player, stack);
			event.setCanceled(true);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onScytheHarvestCrop(ScytheHarvestCropEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		var player = event.getPlayer();
		if (player.getAbilities().instabuild)
			return;

		var stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		if (stack.isDamageableItem() && isBroken(stack)) {
			sendBrokenMessage(player, stack);
			event.setCanceled(true);
		}
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_BREAKING_TWEAK.get())
			return;

		var tooltip = event.getToolTip().listIterator();
		var stack = event.getItemStack();

		if (stack.isDamageableItem()) {
			var item = stack.getItem();

			if (isValidTool(stack) || item instanceof ProjectileWeaponItem) {
				if (isBroken(stack)) {
					tooltip.next();
					tooltip.add(ModTooltips.BROKEN.color(ChatFormatting.RED).toComponent());
				}
			}
		}
	}

	public static boolean isValidTool(ItemStack stack) {
		return stack.is(ItemTags.SWORDS)
				|| stack.is(ItemTags.PICKAXES)
				|| stack.is(ItemTags.SHOVELS)
				|| stack.is(ItemTags.AXES)
				|| stack.is(ItemTags.HOES);
	}

	public static boolean isBroken(ItemStack stack) {
		return stack.getMaxDamage() > 2 && stack.getDamageValue() >= stack.getMaxDamage() - 2;
	}

	private static void sendBrokenMessage(Player player, ItemStack stack) {
		player.sendOverlayMessage(ModTooltips.YOUR_ITEM_IS_BROKEN.args(stack.getHoverName()).color(ChatFormatting.WHITE).toComponent());
	}
}
