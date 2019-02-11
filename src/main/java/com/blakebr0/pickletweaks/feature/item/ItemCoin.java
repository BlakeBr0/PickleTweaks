package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;

public class ItemCoin extends ItemMeta implements IEnableable {
	
	public static ItemStack itemWoodenCoin;
	public static ItemStack itemStoneCoin;
	public static ItemStack itemIronCoin;
	public static ItemStack itemGoldCoin;
	public static ItemStack itemDiamondCoin;
	public static ItemStack itemEmeraldCoin;
	
	public ItemCoin() {
		super("pt.coin", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.CREATIVE_TAB);
		this.setHasSubtypes(true);
	}

	@Override
	public void init() {
		itemWoodenCoin = addItem(0, "wooden");
		itemStoneCoin = addItem(1, "stone");
		itemIronCoin = addItem(2, "iron");
		itemGoldCoin = addItem(3, "gold");
		itemDiamondCoin = addItem(4, "diamond");
		itemEmeraldCoin = addItem(5, "emerald");
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confCoin;
	}
}
