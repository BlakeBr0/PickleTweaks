package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;
import java.util.function.Function;

public class NightVisionGogglesItem extends BaseArmorItem implements IEnableable {
	public NightVisionGogglesItem(ArmorMaterial material, Function<Properties, Properties> properties) {
		super(material, EquipmentSlot.HEAD, properties);
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(ItemRenderProperties.INSTANCE);
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_NIGHT_VISION_GOGGLES.get();
	}

	static class ItemRenderProperties implements IClientItemExtensions {
		public static final ItemRenderProperties INSTANCE = new ItemRenderProperties();

		private NightVisionGogglesModel model;

		@Override
		public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> _default) {
			if (this.model == null) {
				var layer = Minecraft.getInstance().getEntityModels().bakeLayer(ModelHandler.NIGHT_VISION_GOGGLES_LAYER);

				this.model = new NightVisionGogglesModel(layer);
			}

			return this.model;
		}
	}
}
