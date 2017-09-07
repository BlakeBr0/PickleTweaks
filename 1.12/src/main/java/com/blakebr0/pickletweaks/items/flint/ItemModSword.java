package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword {

	public ItemModSword(String name, ToolMaterial material){
		super(material);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
	}
}
