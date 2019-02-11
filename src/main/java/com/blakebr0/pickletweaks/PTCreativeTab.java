package com.blakebr0.pickletweaks;

import com.blakebr0.pickletweaks.registry.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PTCreativeTab extends CreativeTabs {

	public PTCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		if (ModItems.itemWateringCan.isEnabled()) {
			return new ItemStack(ModItems.itemWateringCan);
		}
		
		return new ItemStack(Items.DIAMOND);
	}
}
