package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCoalPiece extends ItemMeta implements IEnableable {
	
	public static ItemStack itemCoalPiece;
	public static ItemStack itemCharcoalPiece;
	
	public ItemCoalPiece(){
		super("pt.coal_piece", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.tab);
		this.setHasSubtypes(true);
	}
	
	@Override
	public void init(){
		itemCoalPiece = addItem(0, "coal");
		itemCharcoalPiece = addItem(1, "charcoal");
		
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	@Override
	public boolean isEnabled(){
		return ModConfig.confCoalPiece;
	}
	
	public class FuelHandler implements IFuelHandler {

		@Override
		public int getBurnTime(ItemStack fuel) {
			if(fuel.getItem() instanceof ItemCoalPiece){
				return 200;
			}
			return 0;
		}
	}
}
