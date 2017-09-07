package com.blakebr0.pickletweaks.feature.item;

import java.util.List;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHammer extends ItemBase implements IEnableable {
	
	public ItemHammer(){
		super("pt.hammer");
		this.setCreativeTab(PickleTweaks.tab);
		this.setMaxDamage(191);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack){
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack stack){
		ItemStack newStack = stack.copy();
		
		newStack.setCount(1);
		newStack.setItemDamage(newStack.getItemDamage() + 1);
		
		return newStack;
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced){
    	int damage = stack.getMaxDamage() - stack.getItemDamage() + 1;
    	tooltip.add(Utils.localize("tooltip.pt.uses_left") + " " + damage);
    }

	@Override
	public boolean isEnabled(){
		return ModConfig.confHammer;
	}
}
