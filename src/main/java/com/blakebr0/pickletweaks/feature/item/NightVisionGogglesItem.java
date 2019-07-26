package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.client.NightVisionGogglesModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class NightVisionGogglesItem extends BaseArmorItem implements IEnableable {
	public NightVisionGogglesItem(IArmorMaterial material, Function<Properties, Properties> properties) {
		super(material, EquipmentSlotType.HEAD, properties);
	}

	@Override
	public BipedModel getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlotType slot, BipedModel _default) {
		return new NightVisionGogglesModel();
	}

	@Override
	public boolean isEnabled() {
		return !ModConfigs.isLoaded() || ModConfigs.ENABLE_NIGHT_VISION_GOGGLES.get();
	}
}
