package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class EmeraldAppleItem extends BaseItem implements IEnableable {
	public static final Food FOOD = new Food.Builder().nutrition(10).saturationMod(2.0F).alwaysEat().build();

	public EmeraldAppleItem(Function<Properties, Properties> properties) {
		super(properties.compose(p -> p.food(FOOD).rarity(Rarity.EPIC)));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		EffectInstance potion;
		int duration = 0;

		potion = entity.getEffect(Effects.DAMAGE_BOOST);
		if (potion != null && potion.getAmplifier() >= 0)
			duration = potion.getDuration();
		entity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, duration + 5000, 0));

		potion = entity.getEffect(Effects.REGENERATION);
		if (potion != null && potion.getAmplifier() >= 2)
			duration = potion.getDuration();
		entity.addEffect(new EffectInstance(Effects.REGENERATION, duration + 400, 2));

		potion = entity.getEffect(Effects.ABSORPTION);
		if (potion != null && potion.getAmplifier() >= 4)
			duration = potion.getDuration();
		entity.addEffect(new EffectInstance(Effects.ABSORPTION, duration + 5000, 4));

		potion = entity.getEffect(Effects.FIRE_RESISTANCE);
		if (potion != null && potion.getAmplifier() >= 0)
			duration = potion.getDuration();
		entity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, duration + 5000, 0));

		potion = entity.getEffect(Effects.DAMAGE_RESISTANCE);
		if (potion != null && potion.getAmplifier() >= 1)
			duration = potion.getDuration();
		entity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, duration + 5000, 1));

		return entity.eat(world, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (Screen.hasShiftDown()) {
			tooltip.add(ModTooltips.GIVES_BUFFS.build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, Effects.DAMAGE_BOOST.getDisplayName(), "I").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, Effects.REGENERATION.getDisplayName(), "III").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, Effects.ABSORPTION.getDisplayName(), "IV").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, Effects.FIRE_RESISTANCE.getDisplayName(), "I").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, Effects.DAMAGE_RESISTANCE.getDisplayName(), "II").build());
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO.build());
		}
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_APPLES.get();
	}
}
