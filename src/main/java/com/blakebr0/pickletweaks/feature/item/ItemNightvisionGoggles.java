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

public class ItemNightvisionGoggles extends ItemArmor implements IEnableable {

	public final static ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("PT:GOGGLES", "pickletweaks:nightvision_goggles", 15, new int[] { 2, 5, 6, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

	public ItemNightvisionGoggles() {
		super(MATERIAL, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName("pt.nightvision_goggles");
		this.setCreativeTab(PickleTweaks.CREATIVE_TAB);
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

	public static class Handler {

		public static List<String> playersWithGoggles = new ArrayList<String>();

		public static String playerKey(EntityPlayer player) {
			return player.getGameProfile().getName() + ":" + player.getEntityWorld().isRemote;
		}

		public static boolean playerHasGoggles(EntityPlayer entity) {
			ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

			return !head.isEmpty() && (head.getItem() instanceof ItemNightvisionGoggles || head.getItem() instanceof ItemNightvisionGogglesC);
		}

		@SubscribeEvent
		public void giveNightvision(LivingUpdateEvent event) {
			if (event.getEntityLiving() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				String key = playerKey(player);

				Boolean has = this.playerHasGoggles(player);
				if (playersWithGoggles.contains(key)) {
					if (has) {
						player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 246, 0, true, false));
					} else {
						player.removeActivePotionEffect(MobEffects.NIGHT_VISION);
						playersWithGoggles.remove(key);
					}
				} else if (has) {
					playersWithGoggles.add(key);
				}
			}
		}
	}
}
