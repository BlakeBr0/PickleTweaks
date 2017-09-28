package com.blakebr0.pickletweaks.feature.item.flint;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemModHoe extends ItemHoe implements IRepairMaterial {

	private ItemStack repairMaterial;
	
	public ItemModHoe(String name, ToolMaterial material) {
		super(material);
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
