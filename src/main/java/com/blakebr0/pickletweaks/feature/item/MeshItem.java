package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseReusableItem;
import com.blakebr0.pickletweaks.config.ModConfigs;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class MeshItem extends BaseReusableItem implements IEnableable {
	public MeshItem(int uses, Function<Properties, Properties> properties) {
		super(uses, properties);
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_MESHES.get();
	}
}
