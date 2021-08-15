package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

import java.util.function.Function;

public class NightVisionGogglesItem extends BaseArmorItem implements IEnableable {
	public NightVisionGogglesItem(ArmorMaterial material, Function<Properties, Properties> properties) {
		super(material, EquipmentSlot.HEAD, properties);
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_NIGHT_VISION_GOGGLES.get();
	}
}
