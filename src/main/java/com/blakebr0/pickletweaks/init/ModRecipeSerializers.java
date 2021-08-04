package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class ModRecipeSerializers {
	public static final RecipeSerializer<GridRepairRecipe> CRAFTING_GRID_REPAIR = new GridRepairRecipe.Serializer();

	@SubscribeEvent
	public void onRegisterSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
		IForgeRegistry<RecipeSerializer<?>> registry = event.getRegistry();

		registry.register(CRAFTING_GRID_REPAIR.setRegistryName(new ResourceLocation(PickleTweaks.MOD_ID, "grid_repair")));
	}
}
