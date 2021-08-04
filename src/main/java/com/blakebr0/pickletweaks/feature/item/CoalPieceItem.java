package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Function;

public class CoalPieceItem extends BaseItem implements IEnableable {
	public CoalPieceItem(Function<Properties, Properties> properties) {
		super(properties);
	}

	@Override
	public int getBurnTime(ItemStack stack, RecipeType<?> type) {
		return 200;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_COAL_PIECES.get();
	}
}
