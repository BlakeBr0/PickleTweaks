package com.blakebr0.pickletweaks.feature.item;

import java.util.List;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMesh extends ItemBase implements IEnableable {

	public ItemMesh(String name, int uses) {
		super("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
		this.setMaxDamage(uses);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemstack) {
		ItemStack stack = itemstack.copy();

		stack.setItemDamage(stack.getItemDamage() + 1);
		stack.setCount(1);

		return stack;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		int damage = stack.getMaxDamage() - stack.getItemDamage() + 1;
		tooltip.add(Utils.localize("tooltip.pt.uses_left") + " " + damage);
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confMesh;
	}
}
