package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;

public class ItemMortarAndPestle extends ItemBase implements IEnableable {

	public ItemMortarAndPestle() {
		super("pt.mortar_and_pestle");
		this.setCreativeTab(PickleTweaks.tab);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack stack = itemstack.copy();

        stack.setCount(1);
        
		return stack;
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confMortarAndPestle;
	}
}
