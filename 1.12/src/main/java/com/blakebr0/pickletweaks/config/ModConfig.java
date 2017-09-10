package com.blakebr0.pickletweaks.config;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverride;
import com.blakebr0.pickletweaks.tweaks.TweakBlockHardness;
import com.blakebr0.pickletweaks.tweaks.TweakBlockHarvest;
import com.blakebr0.pickletweaks.tweaks.TweakFlintDrop;
import com.blakebr0.pickletweaks.tweaks.TweakStackSize;
import com.blakebr0.pickletweaks.tweaks.TweakToolHarvest;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig {

	public static Configuration config;
	
	public static boolean confFlintDrop;
	
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
	public static boolean confFlintTools;
	
	public static boolean confHammerToPlateRecipes;
	public static int confMagnetRange;
	public static boolean confRightClickHarvest;
	
	public static Set<String> confUselessTools = new HashSet<String>();    
    public static Set<String> confUselessHoes = new HashSet<String>();    
    public static Set<String> confUselessWeapons = new HashSet<String>();
	
    public static boolean confGridRepair;
    public static int confRepairCost;
    public static boolean confRequires3x3;
    
	public static boolean confCoin;
    
	@SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.getModID().equals(PickleTweaks.MOD_ID)){
            ModConfig.pre();
            ModConfig.post();
        }
    }
	
	public static void init(File file){
        config = new Configuration(file);
        config.load();
        
        pre();
	}
	
	public static void pre(){
		
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
		confPaxels = config.getBoolean("paxels", category, true, "Should the paxels be enabled?");
		confFlintTools = config.getBoolean("flint_tools", category, true, "Should the Flint Tools be enabled?");
		
		confHammerToPlateRecipes = config.getBoolean("hammer_plate_recipes", category, true, "Should the hammering ingots into plates recipes be enabled?");
		confMagnetRange = config.getInt("magnet_range", category, 7, 1, 16, "The radius in which the Magnet shall succ.");
		confRightClickHarvest = config.getBoolean("right_click_harvest", category, true, "Should right click crop harvesting be enabled?");
		
		category = "tweaks";
		config.addCustomCategoryComment(category, "All the different things this mod tweaks.");
		
		confFlintDrop = config.getBoolean("flint_drop", category, true, "Should Flint dropped from Gravel be replaced with the item defined in 'flint_drop_item'?");		
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
        
        category = "zebras";
        config.setCategoryComment(category, "Random stuff that is disabled by default.");
        
		confCoin = config.getBoolean("coins", category, false, "Should the Coins be enabled?");

        if(config.hasChanged()){
        	config.save();
        }
	}
	
    public static void post(){
    	
    	FeatureToolInfo.configure(config);
    	
    	GridRepairOverride.configure(config);
    	
    	TweakFlintDrop.configure(config);
    	TweakStackSize.configure(config);
    	TweakBlockHardness.configure(config);
    	TweakBlockHarvest.configure(config);
    	TweakToolHarvest.configure(config);
    	
        if(config.hasChanged()){
        	config.save();
        }
    }
}
