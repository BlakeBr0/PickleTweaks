package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.cucumber.event.ScytheHarvestCropEvent;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
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
        PlayerEntity player = event.getPlayer();
        if (player == null)
			return;

		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setNewSpeed(0);
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        if (!(source.getDamageType().equals("player")))
			return;

        Entity trueSource = source.getTrueSource();
        if (!(trueSource instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) trueSource;
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}



	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity player = event.getPlayer();
		if (player == null)
			return;

		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		PlayerEntity player = event.getPlayer();
		if (player == null)
			return;

		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
		PlayerEntity player = event.getPlayer();
		if (player == null)
			return;

		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty())
			return;

		if (isUselessTool(stack.getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onScytheHarvestCrop(ScytheHarvestCropEvent event) {
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
			List<ITextComponent> tooltip = event.getToolTip();
			ListIterator<ITextComponent> lines = tooltip.listIterator();
			while (lines.hasNext()) {
				String s = Localizable.of("attribute.name.generic.attackDamage").buildString();
				if (lines.next().getString().contains(s)) {
					lines.set(new StringTextComponent(" 0 ").appendString(s).mergeStyle(TextFormatting.DARK_GREEN));
				}
			}

			if (item instanceof HoeItem) {
				tooltip.add(ModTooltips.USELESS_HOE_1.color(TextFormatting.RED).build());
			} else if (item instanceof SwordItem) {
				tooltip.add(ModTooltips.USELESS_WEAPON_1.color(TextFormatting.RED).build());
			} else if (item instanceof ShootableItem) {
				tooltip.add(ModTooltips.USELESS_BOW_1.color(TextFormatting.RED).build());
			} else {
				tooltip.add(ModTooltips.USELESS_TOOL_1.color(TextFormatting.RED).build());
			}

			tooltip.add(ModTooltips.USELESS_TOOL_2.color(TextFormatting.RED).build());
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

        return item instanceof TieredItem || item instanceof ShootableItem || item instanceof ShearsItem;
    }
}
