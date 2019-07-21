package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.pickletweaks.config.ModConfigold;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {


	public static void init() {
		if (ModConfigold.confGridRepair) {
			ForgeRegistries.RECIPES.register(new GridRepairRecipe());
		}
		
		if (ModConfigold.confReinforcement) {
			//ForgeRegistries.RECIPES.register(new ReinforcementRecipe());
		}
		
		ItemRepairKit.initRecipes();
		ItemRepairKitCustom.initRecipes();
	}
}
