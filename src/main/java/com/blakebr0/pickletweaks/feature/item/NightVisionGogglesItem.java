package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.client.NightVisionGogglesModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;

import net.minecraft.world.item.Item.Properties;

public class NightVisionGogglesItem extends BaseArmorItem implements IEnableable {
	private HumanoidModel<?> model;

	public NightVisionGogglesItem(ArmorMaterial material, Function<Properties, Properties> properties) {
		super(material, EquipmentSlot.HEAD, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public HumanoidModel getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel _default) {
		if (this.model == null)
			this.model = new NightVisionGogglesModel();
		return this.model;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_NIGHT_VISION_GOGGLES.get();
	}
}
