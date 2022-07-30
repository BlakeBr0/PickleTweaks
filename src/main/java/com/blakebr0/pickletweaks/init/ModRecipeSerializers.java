package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PickleTweaks.MOD_ID);

	public static final RegistryObject<RecipeSerializer<?>> CRAFTING_GRID_REPAIR = register("grid_repair", GridRepairRecipe.Serializer::new);

	private static RegistryObject<RecipeSerializer<?>> register(String name, Supplier<RecipeSerializer<?>> serializer) {
		return REGISTRY.register(name, serializer);
	}
}
