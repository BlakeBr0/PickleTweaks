package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BasePaxelItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.world.item.Tier;

import java.util.function.Function;

public class PaxelItem extends BasePaxelItem implements IEnableable {
    public PaxelItem(Tier tier, Function<Properties, Properties> properties) {
		super(tier, properties);
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_PAXELS.get();
	}
}
