package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;

import java.util.function.Function;

public class NightVisionGogglesItem extends BaseArmorItem implements IEnableable {
	public NightVisionGogglesItem(IArmorMaterial material, Function<Properties, Properties> properties) {
		super(material, EquipmentSlotType.HEAD, properties);
	}

	@Override
	public boolean isEnabled() {
		return !ModConfigs.isLoaded() || ModConfigs.ENABLE_NIGHT_VISION_GOGGLES.get();
	}
}
