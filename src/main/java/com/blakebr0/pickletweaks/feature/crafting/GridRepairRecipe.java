package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.init.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.stream.Collectors;

public class GridRepairRecipe extends ShapelessRecipe {
	public GridRepairRecipe(ResourceLocation id) {
		super(id, "", CraftingBookCategory.EQUIPMENT, ItemStack.EMPTY, NonNullList.create());
	}

	@Override
	public ItemStack assemble(CraftingContainer inv) {
		if (!ModConfigs.GRID_REPAIR_ENABLED.get())
			return ItemStack.EMPTY;

		var tool = ItemStack.EMPTY;
		NonNullList<ItemStack> inputs = NonNullList.create();

		for (int i = 0; i < inv.getContainerSize(); i++) {
			var slotStack = inv.getItem(i);

			if (slotStack.isEmpty())
				continue;

			if (tool.isEmpty() && slotStack.isDamageableItem()) {
				tool = slotStack;
			} else {
				inputs.add(slotStack);
			}
		}

		if (tool.isEmpty() || !tool.isDamaged() || inputs.isEmpty()) {
			return ItemStack.EMPTY;
		}

		if (GridRepairHelper.isBlacklisted(tool.getItem())) {
			return ItemStack.EMPTY;
		}

		int repairCost = ModConfigs.GRID_REPAIR_COST.get();
		int enchantmentCost = ModConfigs.GRID_REPAIR_ENCHANTMENT_COST.get();
		boolean cheaperShovel = ModConfigs.GRID_REPAIR_CHEAP_SHOVEL.get();
		boolean cheaperShears = ModConfigs.GRID_REPAIR_CHEAP_SHEARS.get();

		if ((cheaperShovel && tool.getItem() instanceof ShovelItem) || (cheaperShears && tool.getItem() instanceof ShearsItem)) {
			repairCost = Math.max(1, repairCost / 2);
			enchantmentCost = Math.max(0, enchantmentCost / 2);
		}

		int damage = tool.getMaxDamage() / (repairCost + (tool.isEnchanted() ? enchantmentCost : 0));

		double matCount = 0;
		boolean maxed = false;

		for (var mat : inputs) {
			if (maxed) return ItemStack.EMPTY;

			if (!mat.hasCraftingRemainingItem()) {
				double matValue = GridRepairHelper.getMaterialValue(tool, mat);
				if (matValue == 0) return ItemStack.EMPTY;

				matCount += matValue;

				if (tool.getDamageValue() - (damage * matCount) <= 0) {
					maxed = true;
				}
			} else {
				return ItemStack.EMPTY;
			}
		}

		tool = StackHelper.withSize(tool, 1, false);

		tool.setDamageValue(tool.getDamageValue() - (int) (damage * matCount));

		if (ModConfigs.GRID_REPAIR_STRIP_ENCHANTMENTS.get()) {
			var enchantments = EnchantmentHelper.getEnchantments(tool)
					.entrySet()
					.stream()
					.filter(x -> x.getKey().isCurse())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			EnchantmentHelper.setEnchantments(enchantments, tool);
		}

		return tool;
	}

	@Override
	public boolean matches(CraftingContainer inv, Level level) {
		return !this.assemble(inv).isEmpty();
	}

	@Override
	public ItemStack getResultItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.CRAFTING_GRID_REPAIR.get();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
		return NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
	}

	public static class Serializer implements RecipeSerializer<GridRepairRecipe> {
		@Override
		public GridRepairRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new GridRepairRecipe(recipeId);
		}

		@Override
		public GridRepairRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			return new GridRepairRecipe(recipeId);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, GridRepairRecipe recipe) { }
	}
}

