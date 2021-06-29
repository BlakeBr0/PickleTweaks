package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class CoalPieceItem extends BaseItem implements IEnableable {
	public CoalPieceItem(Function<Properties, Properties> properties) {
		super(properties);
	}
	
	@Override
	public int getBurnTime(ItemStack stack) {
		return 200;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_COAL_PIECES.get();
	}
}
