package com.blakebr0.pickletweaks.feature.reinforcement;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class ReinforcementRecipe extends Impl<IRecipe> implements IRecipe {
	
	public ReinforcementRecipe() {
		this.setRegistryName(PickleTweaks.MOD_ID, "reinforcement");
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		return !getReinforcementOutput(inv).isEmpty();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return getReinforcementOutput(inv);
	}
	
	public ItemStack getReinforcementOutput(InventoryCrafting inv) {
		ItemStack tool = ItemStack.EMPTY;
		boolean foundTool = false;
		NonNullList<ItemStack> inputs = NonNullList.<ItemStack>create();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack slotStack = inv.getStackInSlot(i);
			
			if (slotStack.isEmpty()) {
				continue;
			}
			
			ItemStack newSlotStack = slotStack.copy();
			newSlotStack.setCount(1);
			
			if (!foundTool && newSlotStack.isItemStackDamageable()) {
				tool = newSlotStack;
				foundTool = true;
				continue;
			} else {
				inputs.add(newSlotStack);
			}
		}
		
		if (tool.isEmpty()) {
			return ItemStack.EMPTY;
		}
		
		if (inputs.isEmpty()) {
			return ItemStack.EMPTY;
		}
		
		if (tool.getItem().hasContainerItem(tool)) {
			return ItemStack.EMPTY;
		}
		
		if (ModConfig.confBrokenTools && TweakToolBreaking.isBroken(tool, tool.getItem() instanceof ItemSword ? 1 : 0)) {
			return ItemStack.EMPTY;
		}
		
		if (ReinforcementBlacklist.isBlacklisted(tool.getItem())) {
			return ItemStack.EMPTY;
		}
		
		int reeCount = 0;
		boolean maxed = false;
		int currentCount = ReinforcementHelper.getReinforcement(tool);
		for (ItemStack mat : inputs) {
			if (maxed) return ItemStack.EMPTY;
			
			if (ReinforcementHelper.isReinforcement(mat)) {
				reeCount += ReinforcementHelper.getReinforcementValue(mat);
				
				if ((reeCount + currentCount) > ModConfig.confMaxReinforcement) {
					maxed = true;
				}
			} else {
				return ItemStack.EMPTY;
			}
		}
		
		if (reeCount == 0) {
			return ItemStack.EMPTY;
		}
		
		ReinforcementHelper.reinforce(tool, reeCount);
		
		return tool;
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean isHidden() {
		return true;
	}
}
