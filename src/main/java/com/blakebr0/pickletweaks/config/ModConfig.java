package com.blakebr0.pickletweaks.config;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.FeatureRightClickHarvest;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairBlacklist;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverride;
import com.blakebr0.pickletweaks.feature.item.ItemReinforcement;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom;
import com.blakebr0.pickletweaks.feature.reinforcement.ReinforcementBlacklist;
import com.blakebr0.pickletweaks.feature.reinforcement.ReinforcementOverride;
import com.blakebr0.pickletweaks.tweaks.TweakBlockHardness;
import com.blakebr0.pickletweaks.tweaks.TweakBlockHarvest;
import com.blakebr0.pickletweaks.tweaks.TweakBlockResistance;
import com.blakebr0.pickletweaks.tweaks.TweakFlintDrop;
import com.blakebr0.pickletweaks.tweaks.TweakStackSize;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;
import com.blakebr0.pickletweaks.tweaks.TweakToolHarvest;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig {

	public static Configuration config;
	public static ModConfig instance;

	public static boolean confCoalPiece;
	public static boolean confApples;
	public static boolean confWateringCan;
	public static boolean confHammer;
	public static boolean confMesh;
	public static boolean confMagnet;
	public static boolean confNightvisionGoggles;
	public static boolean confDyePowder;
	public static boolean confMortarAndPestle;
	public static boolean confPaxels;
	public static boolean confModSupportPaxels;
	public static boolean confFlintTools;

	public static boolean confToolInfoTooltip;
	public static boolean confSwordInfoTooltip;
	public static boolean confHoeInfoTooltip;
	public static boolean confBowInfoTooltip;
	public static boolean confHammerToPlateRecipes;
	public static int confMagnetRange;
	public static boolean confRightClickHarvest;
	public static boolean confWailaHarvestLevel;

	public static boolean confFlintDrop;
	public static boolean confBrokenTools;

	public static Set<String> confUselessTools = new HashSet<String>();
	public static Set<String> confUselessHoes = new HashSet<String>();
	public static Set<String> confUselessWeapons = new HashSet<String>();

	public static boolean confGridRepair;
	public static int confRepairCost;
	public static boolean confRequires3x3;
	public static boolean confAllowArmor;
	public static boolean confDisableDefaults;
	public static boolean confOverrideMode;
	public static boolean confCheaperShovel;
	
	public static boolean confReinforcement = false;
	public static int confMaxReinforcement = 1;

	public static boolean confRepairKits;

	public static boolean confReinforcements = false;
	
	public static boolean confCoin;
	public static boolean confPPM4;
	public static boolean confSharpeningKits;

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(PickleTweaks.MOD_ID)) {
			ModConfig.preInit();
			ModConfig.postInit();
		}
	}

	public static void init(File file) {
		config = new Configuration(file);
		config.load();

		preInit();
	}

	public static void preInit() {
		String category;

		category = "features";
		config.addCustomCategoryComment(category, "All the different features this mod adds.");
		confCoalPiece = config.getBoolean("coal_piece", category, true, "Should Coal and Charcoal Pieces be enabled?");
		confApples = config.getBoolean("apples", category, true, "Should Diamond and Emerald Apples be enabled?");
		confWateringCan = config.getBoolean("watering_can", category, true, "Should the Watering Can be enabled?");
		confHammer = config.getBoolean("hammer", category, true, "Should the Hammer be enabled?");
		confMesh = config.getBoolean("mesh", category, true, "Should the Mesh and Reinforced Mesh be enabled?");
		confMagnet = config.getBoolean("magnet", category, true, "Should the Magnet be enabled?");
		confNightvisionGoggles = config.getBoolean("nightvision_goggles", category, true, "Should Night Vision Goggles be enabled?");
		confDyePowder = config.getBoolean("dye_powder", category, true, "Should the Dye Powders be enabled?");
		confMortarAndPestle = config.getBoolean("mortar_and_pestle", category, true, "Should the Mortar And Pestle be enabled?");
		confFlintTools = config.getBoolean("flint_tools", category, true, "Should the Flint Tools be enabled?");

		confToolInfoTooltip = config.getBoolean("tool_info_tooltip", category, true, "Should the tool information tooltips be enabled?");
		confSwordInfoTooltip = config.getBoolean("sword_info_tooltip", category, true, "Should the sword information tooltips be enabled?");
		confHoeInfoTooltip = config.getBoolean("hoe_info_tooltip", category, true, "Should the hoe information tooltips be enabled?");
		confBowInfoTooltip = config.getBoolean("bow_info_tooltip", category, true, "Should the bow information tooltips be enabled?");
		confHammerToPlateRecipes = config.getBoolean("hammer_plate_recipes", category, true, "Should the hammering ingots into plates recipes be enabled?");
		confMagnetRange = config.getInt("magnet_range", category, 7, 1, 16, "The radius in which the Magnet shall succ.");
		confRightClickHarvest = config.getBoolean("right_click_harvest", category, true, "Should right click crop harvesting be enabled?");
		confWailaHarvestLevel = config.getBoolean("waila_harvest_level", category, true, "Adds a harvest level tooltip to WAILA, using the names from mining_level_names.");

		category = "tweaks";
		config.addCustomCategoryComment(category, "All the different things this mod tweaks.");
		confFlintDrop = config.getBoolean("flint_drop", category, true, "Should Flint dropped from Gravel be replaced with the item defined in 'flint_drop_item'?");
		confBrokenTools = config.getBoolean("tools_dont_break", category, true, "Once tools have 1 use left, they become ineffective.");
		String[] tools = config.getStringList("useless_tools", category, new String[0], "All tools listed will not be able to mine blocks." 
						+ "\n- Syntax: modid:itemid"
						+ "\n- Example: minecraft:stone_pickaxe");
		confUselessTools.addAll(Arrays.asList(tools));
		String[] hoes = config.getStringList("useless_hoes", category, new String[0], "All hoes listed will not be able to till blocks." 
						+ "\n- Syntax: modid:itemid"
						+ "\n- Example: minecraft:stone_hoe");
		confUselessHoes.addAll(Arrays.asList(hoes));
		String[] weapons = config.getStringList("useless_weapons", category, new String[0], "All weapons listed will not be able to deal damage." 
						+ "\n- Syntax: modid:itemid"
						+ "\n- Example: minecraft:stone_sword");
		confUselessWeapons.addAll(Arrays.asList(weapons));

		category = "grid_repair";
		config.setCategoryComment(category, "Crafting Grid Tool Repair settings.");
		confGridRepair = config.getBoolean("grid_repair", category, true, "Should Crafting Grid Tool Repairing be enabled?");
		confRepairCost = config.getInt("repair_cost", category, 4, 1, 8, "How much material should be required to fully repair a tool.");
		confRequires3x3 = config.getBoolean("requires_3x3", category, false, "Should the player need atleast a 3x3 table to repair?");
		confAllowArmor = config.getBoolean("allow_armor", category, false, "Should you be able to repair armor as well?");
		confDisableDefaults = config.getBoolean("disable_defaults", category, false, "Should default repair materials be disabled? Doing this makes it so ONLY the custom materials work.");
		confOverrideMode = config.getBoolean("override_mode", category, false, "Enabling this makes it so custom repair materials override the default ones for the tools specified.");
		confCheaperShovel = config.getBoolean("cheaper_shovel", category, true, "Makes it so shovels need 50% less material to repair.");
		
		//category = "tool_reinforcement";
		//config.setCategoryComment(category, "Tool Reinforcement settings.");
		//confReinforcement = config.getBoolean("tool_reinforcement", category, true, "Should Tool Reinforcement be enabled?");
		//confMaxReinforcement = config.getInt("max_reinforcement", category, 500, 1, Integer.MAX_VALUE, "The maximum reinforcement value a tool can have.");
		
		category = "repair_kit";
		config.setCategoryComment(category, "Add and remove repair kits.");
		confRepairKits = config.getBoolean("_enable_repair_kits", category, true, "Should repair kits be enabled?");
		ItemRepairKitCustom.configure(config);

		//category = "reinforcements";
		//config.setCategoryComment(category, "Add and remove reinforcements.");
		//confReinforcements = config.getBoolean("reinforcement", category, true, "Should reinforcements be enabled?");
		//ItemReinforcement.configure(config);
		
		category = "paxels";
		config.setCategoryComment(category, "Paxel settings.");
		confPaxels = config.getBoolean("paxels", category, true, "Should the paxels be enabled?");
		confModSupportPaxels = config.getBoolean("mod_support_paxels", category, true, "Should the mod support paxels be enabled? Requires 'paxels' to be enabled.");

		category = "zebras";
		config.setCategoryComment(category, "Random stuff that is disabled by default.");

		confCoin = config.getBoolean("coins", category, false, "Should the Coins be enabled?");
		confPPM4 = config.getBoolean("ppm4", category, false, "Random items for ppm4 that are useless to anyone else.");
		confSharpeningKits = config.getBoolean("sharpening_kits", category, false, "Disable the ability to use sharpening kits as modifiers in tinkers.");
		
		if (config.hasChanged()) {
			config.save();
		}
	}

	public static void postInit() {
		FeatureToolInfo.configure(config);
		FeatureRightClickHarvest.configure(config);

		GridRepairOverride.configure(config);
		GridRepairBlacklist.configure(config);
		
		//ReinforcementOverride.configure(config);
		//ReinforcementBlacklist.configure(config);

		TweakFlintDrop.configure(config);
		TweakStackSize.configure(config);
		TweakBlockHardness.configure(config);
		TweakBlockHarvest.configure(config);
		TweakBlockResistance.configure(config);
		TweakToolBreaking.configure(config);
		TweakToolHarvest.configure(config);

		if (config.hasChanged()) {
			config.save();
		}
	}
}
