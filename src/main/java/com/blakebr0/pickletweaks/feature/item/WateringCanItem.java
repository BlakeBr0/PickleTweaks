package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseWateringCanItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.resources.Identifier;

public class WateringCanItem extends BaseWateringCanItem {
	public WateringCanItem(Identifier id, int range, double chance) {
		super(id, range, chance);
	}

	@Override
	protected boolean allowFakePlayerWatering() {
		return ModConfigs.FAKE_PLAYER_WATERING.get();
	}
}
