package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemModHoe extends ItemHoe implements IRepairMaterial {

	private ItemStack repairMaterial;
	private String oreRepairMaterial = null;
	
	public ItemModHoe(String name, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
	}
	
	public ItemModHoe(String name, ToolMaterial material, String ore) {
		this(name, material);
		this.oreRepairMaterial = ore;
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return oreRepairMaterial != null
				? OreDictionary.getOres(oreRepairMaterial).stream().anyMatch(stack -> stack.isItemEqual(repair))
				: OreDictionary.itemMatches(getRepairMaterial(), repair, false);    
	}

	@Override
	public ItemStack getRepairMaterial() {
		return this.repairMaterial;
	}

	@Override
	public void setRepairMaterial(ItemStack stack) {
		this.repairMaterial = stack;
	}
}
