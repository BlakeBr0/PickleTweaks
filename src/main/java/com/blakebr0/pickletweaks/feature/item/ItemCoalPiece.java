package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;

public class ItemCoalPiece extends ItemMeta implements IEnableable {
	
	public static ItemStack itemCoalPiece;
	public static ItemStack itemCharcoalPiece;
	
	public ItemCoalPiece() {
		super("pt.coal_piece", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.CREATIVE_TAB);
		this.setHasSubtypes(true);
	}
	
	@Override
	public void init() {
		itemCoalPiece = addItem(0, "coal");
		itemCharcoalPiece = addItem(1, "charcoal");
	}
	
	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;
	}
	
	@Override
	public boolean isEnabled() {
		return ModConfig.confCoalPiece;
	}
}
