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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class NightVisionGogglesItem extends BaseArmorItem implements IEnableable {
	private BipedModel<?> model;

	public NightVisionGogglesItem(IArmorMaterial material, Function<Properties, Properties> properties) {
		super(material, EquipmentSlotType.HEAD, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public BipedModel getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlotType slot, BipedModel _default) {
		if (this.model == null)
			this.model = new NightVisionGogglesModel();
		return this.model;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_NIGHT_VISION_GOGGLES.get();
	}
}
