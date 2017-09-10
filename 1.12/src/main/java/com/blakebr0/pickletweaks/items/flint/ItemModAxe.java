package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemModAxe extends ItemAxe implements IRepairMaterial {

	private ItemStack repairMaterial;
	
	public ItemModAxe(String name, ToolMaterial material){
		super(material, material.getDamageVsEntity(), -3.2F);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
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
