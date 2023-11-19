package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseWateringCanItem;
import com.blakebr0.pickletweaks.config.ModConfigs;

public class WateringCanItem extends BaseWateringCanItem {
	public WateringCanItem(int range, double chance) {
		super(range, chance);
	}

	@Override
	protected boolean allowFakePlayerWatering() {
		return ModConfigs.FAKE_PLAYER_WATERING.get();
	}
}
