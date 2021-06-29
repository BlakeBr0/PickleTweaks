package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.init.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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

import java.util.Map;
import java.util.stream.Collectors;

public class GridRepairRecipe extends ShapelessRecipe {
	public GridRepairRecipe(ResourceLocation id) {
		super(id, "", ItemStack.EMPTY, NonNullList.create());
	}

	@Override
	public ItemStack assemble(CraftingInventory inv) {
		if (!ModConfigs.GRID_REPAIR_ENABLED.get())
			return ItemStack.EMPTY;

		ItemStack tool = ItemStack.EMPTY;
		NonNullList<ItemStack> inputs = NonNullList.create();

		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack slotStack = inv.getItem(i);

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

				if (tool.getDamageValue() - (damage * matCount) <= 0) {
					maxed = true;
				}
			} else {
				return ItemStack.EMPTY;
			}
		}

		tool = StackHelper.withSize(tool, 1, false);

		tool.setDamageValue(tool.getDamageValue() - (int) (damage * matCount));

		// Strip enchantments. Vanilla implementation here: {@link net.minecraft.item.crafting.RepairItemRecipe#getCraftingResult(CraftingInventory inv)}.
		if (ModConfigs.GRID_REPAIR_STRIP_ENCHANTMENTS.get()) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(tool)
					.entrySet()
					.stream()
					.filter(x -> x.getKey().isCurse())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			EnchantmentHelper.setEnchantments(enchantments, tool);
		}

		return tool;
	}

	@Override
	public boolean matches(CraftingInventory inv, World world) {
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
	public IRecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.CRAFTING_GRID_REPAIR;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
		return NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GridRepairRecipe> {
		@Override
		public GridRepairRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new GridRepairRecipe(recipeId);
		}

		@Override
		public GridRepairRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			return new GridRepairRecipe(recipeId);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, GridRepairRecipe recipe) { }
	}
}

