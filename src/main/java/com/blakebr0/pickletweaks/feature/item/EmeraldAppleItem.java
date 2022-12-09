package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EmeraldAppleItem extends BaseItem {
	public static final FoodProperties FOOD = new FoodProperties.Builder().nutrition(10).saturationMod(2.0F).alwaysEat().build();

	public EmeraldAppleItem() {
		super(p -> p.food(FOOD).rarity(Rarity.EPIC));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		MobEffectInstance potion;
		int duration = 0;

		potion = entity.getEffect(MobEffects.DAMAGE_BOOST);
		if (potion != null && potion.getAmplifier() >= 0)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration + 5000, 0));

		potion = entity.getEffect(MobEffects.REGENERATION);
		if (potion != null && potion.getAmplifier() >= 2)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration + 400, 2));

		potion = entity.getEffect(MobEffects.ABSORPTION);
		if (potion != null && potion.getAmplifier() >= 4)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration + 5000, 4));

		potion = entity.getEffect(MobEffects.FIRE_RESISTANCE);
		if (potion != null && potion.getAmplifier() >= 0)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration + 5000, 0));

		potion = entity.getEffect(MobEffects.DAMAGE_RESISTANCE);
		if (potion != null && potion.getAmplifier() >= 1)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration + 5000, 1));

		return entity.eat(level, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		if (Screen.hasShiftDown()) {
			tooltip.add(ModTooltips.GIVES_BUFFS.build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, MobEffects.DAMAGE_BOOST.getDisplayName(), "I").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, MobEffects.REGENERATION.getDisplayName(), "III").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, MobEffects.ABSORPTION.getDisplayName(), "IV").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, MobEffects.FIRE_RESISTANCE.getDisplayName(), "I").build());
			tooltip.add(ModTooltips.BUFF_LIST_ITEM.args(Colors.WHITE, MobEffects.DAMAGE_RESISTANCE.getDisplayName(), "II").build());
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO.build());
		}
	}
}
