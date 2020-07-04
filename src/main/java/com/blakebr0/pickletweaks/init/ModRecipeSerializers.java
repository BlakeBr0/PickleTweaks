package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class ModRecipeSerializers {
	public static final IRecipeSerializer<GridRepairRecipe> CRAFTING_GRID_REPAIR = new GridRepairRecipe.Serializer();

	@SubscribeEvent
	public void onRegisterSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();

		registry.register(CRAFTING_GRID_REPAIR.setRegistryName(new ResourceLocation(PickleTweaks.MOD_ID, "grid_repair")));
	}
}
