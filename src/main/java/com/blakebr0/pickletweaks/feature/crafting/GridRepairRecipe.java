package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.registry.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GridRepairRecipe extends ShapelessRecipe {
	public GridRepairRecipe(ResourceLocation id) {
		super(id, "", ItemStack.EMPTY, NonNullList.create());
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		if (!ModConfigs.GRID_REPAIR_ENABLED.get())
			return ItemStack.EMPTY;

		ItemStack tool = ItemStack.EMPTY;
		boolean foundTool = false;
		NonNullList<ItemStack> inputs = NonNullList.create();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack slotStack = inv.getStackInSlot(i);

			if (slotStack.isEmpty())
				continue;

			ItemStack newSlotStack = StackHelper.withSize(slotStack.copy(), 1, false);
			if (!foundTool && newSlotStack.isDamageable()) {
				tool = newSlotStack;
				foundTool = true;
			} else {
				inputs.add(newSlotStack);
			}
		}

		if (tool.isEmpty()) {
			return ItemStack.EMPTY;
		}

		if (!tool.isDamaged()) {
			return ItemStack.EMPTY;
		}

		if (inputs.isEmpty()) {
			return ItemStack.EMPTY;
		}

		if (tool.getItem().hasContainerItem(tool)) {
			return ItemStack.EMPTY;
		}

		if (GridRepairHelper.isBlacklisted(tool.getItem())) {
			return ItemStack.EMPTY;
		}

		int repairCost = ModConfigs.GRID_REPAIR_COST.get();

		boolean cheaperShovel = ModConfigs.GRID_REPAIR_CHEAP_SHOVEL.get();
		if (cheaperShovel && tool.getItem() instanceof ShovelItem) {
			repairCost = Math.max(1, repairCost / 2);
		}

		int damage = tool.getMaxDamage() / repairCost;

		double matCount = 0;
		boolean maxed = false;

		for (ItemStack mat : inputs) {
			if (maxed) return ItemStack.EMPTY;

			if (!mat.hasContainerItem()) {
				double matValue = GridRepairHelper.getMaterialValue(tool, mat);
				if (matValue == 0) return ItemStack.EMPTY;

				matCount += matValue;

				if (tool.getDamage() - (damage * matCount) <= 0) {
					maxed = true;
				}
			} else {
				return ItemStack.EMPTY;
			}
		}

		tool.setDamage(tool.getDamage() - (int) (damage * matCount));

		return tool;
	}

	@Override
	public boolean matches(CraftingInventory inv, World world) {
		return !this.getCraftingResult(inv).isEmpty();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean isDynamic() {
		return true;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.CRAFTING_GRID_REPAIR;
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GridRepairRecipe> {
		@Override
		public GridRepairRecipe read(ResourceLocation recipeId, JsonObject json) {
			return new GridRepairRecipe(recipeId);
		}

		@Override
		public GridRepairRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			return new GridRepairRecipe(recipeId);
		}

		@Override
		public void write(PacketBuffer buffer, GridRepairRecipe recipe) {

		}
	}
}
