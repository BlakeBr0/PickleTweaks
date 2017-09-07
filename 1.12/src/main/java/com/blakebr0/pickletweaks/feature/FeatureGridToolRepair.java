package com.blakebr0.pickletweaks.feature;

import com.blakebr0.cucumber.helper.StackHelper;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class FeatureGridToolRepair {

	public void onCrafted(ItemCraftedEvent event){
		IInventory inv = event.craftMatrix;
		ItemStack stack = ItemStack.EMPTY;
		ItemStack toolStack = ItemStack.EMPTY;
		NonNullList<ItemStack> stacks = NonNullList.<ItemStack>create();
		int repair = 0;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			stack = inv.getStackInSlot(i);
			if(!StackHelper.isNull(stack)){
				if(StackHelper.isNull(toolStack) && stack.getItem() instanceof ItemTool){
					toolStack = stack;
				} else {
					stacks.add(stack);
				}
			}
		}
		ItemTool tool = null;
		if(!StackHelper.isNull(toolStack)){
			tool = (ItemTool)toolStack.getItem();
			for(ItemStack mat : stacks){
				if(tool.getIsRepairable(toolStack, mat)){
					repair++;
				}
			}
		} else {
			return;
		}
		ItemStack result = toolStack.copy();
		int repairAmount = tool.getMaxDamage() / 4;
		result.setItemDamage(result.getItemDamage() - repair * repairAmount);
	}
}
