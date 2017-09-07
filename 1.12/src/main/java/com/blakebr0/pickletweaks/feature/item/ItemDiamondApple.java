package com.blakebr0.pickletweaks.feature.item;

import java.util.List;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDiamondApple extends ItemFood implements IEnableable {

	public ItemDiamondApple(){
		super(6, 1.5F, false);
		this.setUnlocalizedName("pt.diamond_apple");
		this.setCreativeTab(PickleTweaks.tab);
		this.setAlwaysEdible();
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
		PotionEffect potion;
		int duration = 0;
		
		potion = player.getActivePotionEffect(MobEffects.REGENERATION);
        if(potion != null && potion.getAmplifier() >= 1){
        	duration = potion.getDuration();
        }
    	player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, duration + 400, 1));

		potion = player.getActivePotionEffect(MobEffects.ABSORPTION);
        if(potion != null && potion.getAmplifier() >= 2){
        	duration = potion.getDuration();
        }
    	player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, duration + 5000, 2));
    	
		potion = player.getActivePotionEffect(MobEffects.FIRE_RESISTANCE);
        if(potion != null && potion.getAmplifier() >= 0){
        	duration = potion.getDuration();
        }
    	player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, duration + 5000, 0));

		potion = player.getActivePotionEffect(MobEffects.RESISTANCE);
        if(potion != null && potion.getAmplifier() >= 0){
        	duration = potion.getDuration();
        }
        player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, duration + 5000, 0));
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag){
		if(Utils.isShiftKeyDown()){
			tooltip.add(Utils.localize("tooltip.pt.gives_buffs"));
			tooltip.add(" - " + Colors.WHITE + Utils.localize(MobEffects.REGENERATION.getName()) + " II");
			tooltip.add(" - " + Colors.WHITE + Utils.localize(MobEffects.ABSORPTION.getName()) + " III");
			tooltip.add(" - " + Colors.WHITE + Utils.localize(MobEffects.FIRE_RESISTANCE.getName()) + " I");
			tooltip.add(" - " + Colors.WHITE + Utils.localize(MobEffects.RESISTANCE.getName()) + " I");
		} else {
			tooltip.add(Utils.localize("tooltip.pt.hold_shift_for_info"));
		}
	}

	@Override
	public boolean isEnabled(){
		return ModConfig.confApples;
	}
}
