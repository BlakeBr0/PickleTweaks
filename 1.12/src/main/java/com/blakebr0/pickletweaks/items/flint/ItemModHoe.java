package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemHoe;

public class ItemModHoe extends ItemHoe {

	public ItemModHoe(String name, ToolMaterial material){
		super(material);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
	}
}
