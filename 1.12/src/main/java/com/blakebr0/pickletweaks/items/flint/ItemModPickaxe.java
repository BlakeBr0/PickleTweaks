package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe {

	public ItemModPickaxe(String name, ToolMaterial material){
		super(material);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
	}
}
