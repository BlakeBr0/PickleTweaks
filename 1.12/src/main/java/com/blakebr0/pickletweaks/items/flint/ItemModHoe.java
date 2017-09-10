package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemModHoe extends ItemHoe implements IRepairMaterial {

	private ItemStack repairMaterial;
	
	public ItemModHoe(String name, ToolMaterial material){
		super(material);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
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
