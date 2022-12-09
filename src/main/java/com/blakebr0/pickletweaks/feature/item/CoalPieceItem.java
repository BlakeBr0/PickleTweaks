package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class CoalPieceItem extends BaseItem {
	public CoalPieceItem() {
		super();
	}

	@Override
	public int getBurnTime(ItemStack stack, RecipeType<?> type) {
		return 200;
	}
}
