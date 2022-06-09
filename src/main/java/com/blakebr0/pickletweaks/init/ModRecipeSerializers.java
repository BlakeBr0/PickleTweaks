package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class ModRecipeSerializers {
	public static final RecipeSerializer<GridRepairRecipe> CRAFTING_GRID_REPAIR = new GridRepairRecipe.Serializer();

	@SubscribeEvent
	public void onRegisterSerializers(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, registry -> {
			registry.register(new ResourceLocation(PickleTweaks.MOD_ID, "grid_repair"), CRAFTING_GRID_REPAIR);
		});
	}
}
