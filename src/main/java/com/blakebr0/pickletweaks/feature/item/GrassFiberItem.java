package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.pickletweaks.config.ModConfigs;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class GrassFiberItem extends BaseItem implements IEnableable {
	public GrassFiberItem(Function<Properties, Properties> properties) {
		super(properties);
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_MESHES.get();
	}
}
