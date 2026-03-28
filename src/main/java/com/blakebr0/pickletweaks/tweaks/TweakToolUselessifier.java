package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.cucumber.event.ScytheHarvestCropEvent;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import com.blakebr0.pickletweaks.util.BlacklistUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.ShearsItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class TweakToolUselessifier {
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        var player = event.getEntity();
        if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack)) {
			event.setNewSpeed(0);
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingIncomingDamageEvent event) {
        var source = event.getSource();
        if (!(source.getMsgId().equals("player")))
			return;

        var trueSource = source.getEntity();
        if (!(trueSource instanceof Player player))
			return;

		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
		var player = event.getEntity();
		if (player.getAbilities().instabuild)
			return;

		var stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onScytheHarvestCrop(ScytheHarvestCropEvent event) {
		var player = event.getPlayer();
		if (player.getAbilities().instabuild)
			return;

		var stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		var stack = event.getItemStack();

		if (isUselessTool(stack)) {
			var item = stack.getItem();
			var tooltip = event.getToolTip();
			var lines = tooltip.listIterator();

			while (lines.hasNext()) {
				var s = Component.translatable("attribute.name.generic.attackDamage").getString();
				if (lines.next().getString().contains(s)) {
					lines.set(Component.literal(" 0 ").append(s).withStyle(ChatFormatting.DARK_GREEN));
				}
			}

			if (item instanceof HoeItem) {
				tooltip.add(ModTooltips.USELESS_HOE_1.color(ChatFormatting.RED).toComponent());
			} else if (stack.is(ItemTags.SWORDS)) {
				tooltip.add(ModTooltips.USELESS_WEAPON_1.color(ChatFormatting.RED).toComponent());
			} else if (item instanceof ProjectileWeaponItem) {
				tooltip.add(ModTooltips.USELESS_BOW_1.color(ChatFormatting.RED).toComponent());
			} else {
				tooltip.add(ModTooltips.USELESS_TOOL_1.color(ChatFormatting.RED).toComponent());
			}

			tooltip.add(ModTooltips.USELESS_TOOL_2.color(ChatFormatting.RED).toComponent());
		}
	}

	public static boolean isUselessTool(ItemStack stack) {
		var item = stack.getItem();

		// this is a whitelist, ignore anyone who says otherwise
		if (!BlacklistUtils.contains(item, ModConfigs.USELESS_TOOLS.get()))
			return false;

        return stack.is(ItemTags.SWORDS)
				|| stack.is(ItemTags.PICKAXES)
				|| stack.is(ItemTags.AXES)
				|| stack.is(ItemTags.SHOVELS)
				|| stack.is(ItemTags.HOES)
				|| item instanceof ProjectileWeaponItem
				|| item instanceof ShearsItem;
    }
}
