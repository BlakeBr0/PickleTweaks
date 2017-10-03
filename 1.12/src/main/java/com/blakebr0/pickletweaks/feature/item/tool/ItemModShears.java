package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemModShears extends ItemShears implements IRepairMaterial {
	
	private ItemStack repairMaterial;
	
	public ItemModShears(String name, ToolMaterial material) {
		this.setCreativeTab(PickleTweaks.tab);
		this.setUnlocalizedName("pt." + name);
		this.setMaxDamage(material.getMaxUses());
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return OreDictionary.itemMatches(getRepairMaterial(), repair, false);
	}

	@Override
	public ItemStack getRepairMaterial() {
		return repairMaterial;
	}

	@Override
	public void setRepairMaterial(ItemStack stack) {
		repairMaterial = stack;		
	}
}
