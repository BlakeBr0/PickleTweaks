package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class GridRepairRecipe extends Impl<IRecipe> implements IRecipe {
	
	public GridRepairRecipe() {
		this.setRegistryName("grid_repair");
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		return !getRepairOutput(inv).isEmpty();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return getRepairOutput(inv);
	}
	
	public ItemStack getRepairOutput(InventoryCrafting inv) {
		if (ModConfig.confRequires3x3) {
			if (inv.getWidth() < 3 || inv.getHeight() < 3) {
				return ItemStack.EMPTY;
			}
		}
		
		ItemStack tool = ItemStack.EMPTY;
		boolean foundTool = false;
		NonNullList<ItemStack> inputs = NonNullList.<ItemStack>create();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack slotStack = inv.getStackInSlot(i);
			if (slotStack.isEmpty()) {
				continue;
			}
			
			slotStack = slotStack.copy();
			slotStack.setCount(1);
			
			if (!foundTool && slotStack.isItemStackDamageable()) {
				tool = slotStack;
				foundTool = true;
				continue;
			} else {
				inputs.add(slotStack);
			}
		}
		
		if (tool.isEmpty()) {
			return ItemStack.EMPTY;
		}
		
		if (!ModConfig.confAllowArmor && tool.getItem() instanceof ItemArmor) {
			return ItemStack.EMPTY;
		}
		
		if (!tool.isItemDamaged()) {
			return ItemStack.EMPTY;
		}
		
		if (inputs.isEmpty()) {
			return ItemStack.EMPTY;
		}
		
		int matCount = 0;
		for (ItemStack mat : inputs) {
			if (tool.getItem().getIsRepairable(tool, mat) || GridRepairOverride.hasOverride(tool, mat)) {
				matCount++;
			} else {
				return ItemStack.EMPTY;
			}
		}
		
		int damage = tool.getMaxDamage() / ModConfig.confRepairCost;
		if (damage * matCount > tool.getItemDamage() + damage) {
			return ItemStack.EMPTY;
		}
		
		tool.setItemDamage(tool.getItemDamage() - (damage * matCount));
		
		return tool;
	}

	@Override
	public boolean canFit(int width, int height) {
		return ModConfig.confRequires3x3 ? (width >= 3 && height >= 3) : true;
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
