package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairRecipe;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
	
	public static final String[] MATERIALS = new String[]{ "Iron", "Gold", "Copper", "Tin", "Silver", "Lead", "Aluminum", "Nickel", "Platinum", "Iridium", "Mithril", "Steel", "Electrum", "Invar", "Bronze", "Constantan", "Signalum", "Lumium", "Enderium" };
	
	public static void init(){
		// They're all in JSON files!
		if (ModConfig.confGridRepair) {
			ForgeRegistries.RECIPES.register(new GridRepairRecipe()); // TODO: Cucumber registration
		}
		ItemRepairKit.initRecipes();
	}
	
	public static void post(){
		if(ModConfig.confHammerToPlateRecipes && ModConfig.confHammer){
			for(int i = 0; i < MATERIALS.length; i++){
				if(OreDictionary.getOres("ingot" + MATERIALS[i]).size() > 0 && OreDictionary.getOres("plate" + MATERIALS[i]).size() > 0){
					String ingot = "ingot" + MATERIALS[i];
					ItemStack plate = OreDictionary.getOres("plate" + MATERIALS[i]).get(0).copy();
					RecipeHelper.addShapelessRecipe(plate, StackHelper.to(ModItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE), ingot, ingot);
				}
			}
		}
	}
}
