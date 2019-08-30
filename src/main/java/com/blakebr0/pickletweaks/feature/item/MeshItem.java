package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ReusableItem;
import com.blakebr0.pickletweaks.config.ModConfigs;

import java.util.function.Function;

public class MeshItem extends ReusableItem implements IEnableable {
	public MeshItem(int uses, Function<Properties, Properties> properties) {
		super(uses, properties);
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_MESHES.get();
	}
}
