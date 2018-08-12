package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom;
import com.blakebr0.pickletweaks.feature.reinforcement.ReinforcementRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

	public static final String[] MATERIALS = new String[] { "Iron", "Gold", "Copper", "Tin", "Silver", "Lead",
			"Aluminum", "Nickel", "Platinum", "Iridium", "Mithril", "Steel", "Electrum", "Invar", "Bronze",
			"Constantan", "Signalum", "Lumium", "Enderium" };

	public static void init() {
		if (ModConfig.confGridRepair) {
			ForgeRegistries.RECIPES.register(new GridRepairRecipe());
		}
		
		if (ModConfig.confReinforcement) {
			//ForgeRegistries.RECIPES.register(new ReinforcementRecipe());
		}
		
		ItemRepairKit.initRecipes();
		ItemRepairKitCustom.initRecipes();
	}

	public static void postInit() {
		if (ModConfig.confHammerToPlateRecipes && ModConfig.confHammer) {
			for (int i = 0; i < MATERIALS.length; i++) {
				if (!OreDictionary.getOres("ingot" + MATERIALS[i], false).isEmpty() && !OreDictionary.getOres("plate" + MATERIALS[i], false).isEmpty()) {
					String ingot = "ingot" + MATERIALS[i];
					ItemStack plate = OreDictionary.getOres("plate" + MATERIALS[i]).get(0).copy();
					RecipeHelper.addShapelessRecipe(plate, new ItemStack(ModItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE), ingot, ingot);
				}
			}
		}
	}
}
