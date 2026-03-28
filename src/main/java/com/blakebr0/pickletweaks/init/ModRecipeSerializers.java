package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(Registries.RECIPE_SERIALIZER, PickleTweaks.MOD_ID);

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> CRAFTING_GRID_REPAIR = REGISTRY.register("grid_repair", () -> GridRepairRecipe.SERIALIZER);
}
