package com.blakebr0.pickletweaks.feature;

import java.util.ListIterator;

import org.lwjgl.input.Keyboard;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureHoeInfo {

	@SubscribeEvent
	public void onRightClickBlock(UseHoeEvent event) {
		if (!ModConfig.confHoeInfoTooltip) {
			return;
		}
		
		if (event.getEntityPlayer() == null) {
			return;
		}

		Block block = event.getWorld().getBlockState(event.getPos()).getBlock();

		if (event.getResult() == Result.ALLOW || block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
			NBTTagCompound tag = event.getCurrent().getOrCreateSubCompound(PickleTweaks.MOD_ID);
			tag.setInteger("BlocksTilled", tag.getInteger("BlocksTilled") + 1);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addHoeInfoTooltip(ItemTooltipEvent event) {
		if (!ModConfig.confHoeInfoTooltip) {
			return;
		}
		
		if (event.getEntityPlayer() == null) {
			return;
		}

		ItemStack stack = event.getItemStack();
		ListIterator<String> tooltip = event.getToolTip().listIterator();

		if (stack.getItem() instanceof ItemHoe) {
			boolean shift = false;
			if (Keyboard.isCreated()) {
				shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
			}

			while (tooltip.hasNext()) {
				tooltip.next();
				if (shift) {
					tooltip.add(Utils.localize("tooltip.pt.durability") + " " + getDurability(stack));
					tooltip.add(Utils.localize("tooltip.pt.blocks_tilled") + " " + getBlocksTilled(stack));
				} else {
					tooltip.add(Utils.localize("tooltip.pt.hold_shift_for_info"));
				}
				break;
			}
		}
	}

	public String getDurability(ItemStack stack) {
		if (stack.getMaxDamage() == -1) {
			return Utils.localize("tooltip.pt.unbreakable");
		}
		
		int durability = stack.getMaxDamage() - stack.getItemDamage();
		return durability + Colors.GRAY + "/" + Colors.WHITE + stack.getMaxDamage();
	}

	public int getBlocksTilled(ItemStack stack) {
		NBTTagCompound tag = stack.getSubCompound(PickleTweaks.MOD_ID);
		if (tag != null && tag.hasKey("BlocksTilled")) {
			return tag.getInteger("BlocksTilled");
		}
		
		return 0;
	}
}
