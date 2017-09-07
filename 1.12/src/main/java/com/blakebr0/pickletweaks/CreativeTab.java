package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.registry.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

	public CreativeTab(String label){
		super(label);
	}

	@Override
	public ItemStack getTabIconItem(){
		if(ModItems.itemWateringCan.isEnabled()){
			return StackHelper.to(ModItems.itemWateringCan);
		}
		return StackHelper.to(Items.DIAMOND);
	}
}
