package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jspecify.annotations.Nullable;

public class CoalPieceItem extends BaseItem {
	public CoalPieceItem(Identifier id) {
		super(id);
	}

	@Override
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> type, FuelValues values) {
		return 200;
	}
}
