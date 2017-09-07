package com.blakebr0.pickletweaks.items.flint;

import com.blakebr0.pickletweaks.PickleTweaks;

import net.minecraft.item.ItemSpade;

public class ItemModShovel extends ItemSpade {

	public ItemModShovel(String name, ToolMaterial material){
		super(material);
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
	}
}
