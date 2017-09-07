package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemAxe;

public class ItemModAxe extends ItemAxe {

	public ItemModAxe(String name, ToolMaterial material){
		super(material, material.getDamageVsEntity(), -3.2F);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
	}

}
