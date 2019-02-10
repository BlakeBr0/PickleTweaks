package com.blakebr0.pickletweaks.feature.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockColoredCobblestone extends ItemBlock {

	public ItemBlockColoredCobblestone(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "_" + EnumDyeColor.byMetadata(stack.getMetadata()).getUnlocalizedName();
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
