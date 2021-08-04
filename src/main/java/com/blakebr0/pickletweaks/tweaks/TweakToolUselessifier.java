package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.cucumber.event.ScytheHarvestCropEvent;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.ListIterator;

public final class TweakToolUselessifier {
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getPlayer();
        if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setNewSpeed(0);
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        if (!(source.getMsgId().equals("player")))
			return;

        Entity trueSource = source.getEntity();
        if (!(trueSource instanceof Player))
			return;

		Player player = (Player) trueSource;
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}



	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		Player player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		Player player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
		Player player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onScytheHarvestCrop(ScytheHarvestCropEvent event) {
		Player player = event.getPlayer();
		if (player.abilities.instabuild)
			return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		Item item = event.getItemStack().getItem();
		if (isUselessTool(item)) {
			List<Component> tooltip = event.getToolTip();
			ListIterator<Component> lines = tooltip.listIterator();
			while (lines.hasNext()) {
				String s = Localizable.of("attribute.name.generic.attackDamage").buildString();
				if (lines.next().getString().contains(s)) {
					lines.set(new TextComponent(" 0 ").append(s).withStyle(ChatFormatting.DARK_GREEN));
				}
			}

			if (item instanceof HoeItem) {
				tooltip.add(ModTooltips.USELESS_HOE_1.color(ChatFormatting.RED).build());
			} else if (item instanceof SwordItem) {
				tooltip.add(ModTooltips.USELESS_WEAPON_1.color(ChatFormatting.RED).build());
			} else if (item instanceof ProjectileWeaponItem) {
				tooltip.add(ModTooltips.USELESS_BOW_1.color(ChatFormatting.RED).build());
			} else {
				tooltip.add(ModTooltips.USELESS_TOOL_1.color(ChatFormatting.RED).build());
			}

			tooltip.add(ModTooltips.USELESS_TOOL_2.color(ChatFormatting.RED).build());
		}
	}

	public static boolean isUselessTool(Item item) {
		if (item == null)
			return false;

		ResourceLocation id = item.getRegistryName();
		if (id == null)
			return false;

		if (!ModConfigs.USELESS_TOOLS.get().contains(id.toString()))
			return false;

        return item instanceof TieredItem || item instanceof ProjectileWeaponItem || item instanceof ShearsItem;
    }
}
