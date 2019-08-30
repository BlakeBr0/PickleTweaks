package com.blakebr0.pickletweaks.registry;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class ModRecipeSerializers {
	@ObjectHolder("pickletweaks:grid_repair")
	public static final IRecipeSerializer<GridRepairRecipe> CRAFTING_GRID_REPAIR = null;

	@SubscribeEvent
	public void onRegisterSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();

		IRecipeSerializer<?> gridRepair = new GridRepairRecipe.Serializer();

		registry.register(gridRepair.setRegistryName(new ResourceLocation(PickleTweaks.MOD_ID, "grid_repair")));
	}
}
