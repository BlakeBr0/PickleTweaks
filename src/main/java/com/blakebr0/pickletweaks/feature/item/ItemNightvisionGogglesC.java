package com.blakebr0.pickletweaks.feature.item;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemNightvisionGogglesC extends ItemArmor implements IEnableable {

	public final static ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("PT:GOGGLES_C", "pickletweaks:nightvision_goggles_c", 55, new int[] { 4, 10, 12, 4 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

	public ItemNightvisionGogglesC() {
		super(MATERIAL, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName("pt.nightvision_goggles_c");
		this.setCreativeTab(PickleTweaks.tab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confNightvisionGoggles;
	}
}
