package com.blakebr0.pickletweaks.proxy;

import com.blakebr0.pickletweaks.GuiHandler;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.FeatureRightClickHarvest;
import com.blakebr0.pickletweaks.feature.FeatureSwordInfo;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.counters.FeatureBlockHoedCounter;
import com.blakebr0.pickletweaks.feature.counters.FeatureKillCounter;
import com.blakebr0.pickletweaks.feature.item.ItemMagnet;
import com.blakebr0.pickletweaks.feature.item.ItemNightvisionGoggles;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import com.blakebr0.pickletweaks.registry.ModItems;
import com.blakebr0.pickletweaks.registry.ModRecipes;
import com.blakebr0.pickletweaks.tweaks.TweakHoeUselessifier;
import com.blakebr0.pickletweaks.tweaks.TweakFlintDrop;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import com.blakebr0.pickletweaks.tweaks.TweakWeaponUselessifier;
import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){
		ModConfig.init(e.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		MinecraftForge.EVENT_BUS.register(PickleTweaks.REGISTRY);
		
		ModBlocks.init();
		ModItems.init();
		
		MinecraftForge.EVENT_BUS.register(new ItemMagnet.Handler());
		MinecraftForge.EVENT_BUS.register(new ItemNightvisionGoggles.Handler());
		
		MinecraftForge.EVENT_BUS.register(new FeatureToolInfo());
		MinecraftForge.EVENT_BUS.register(new FeatureSwordInfo());
		MinecraftForge.EVENT_BUS.register(new FeatureRightClickHarvest());
	}
	
	public void init(FMLInitializationEvent e){
		ModRecipes.init();
		FMLInterModComms.sendMessage("waila", "register", "com.blakebr0.pickletweaks.feature.FeatureWailaTooltips.callbackRegister");
		NetworkRegistry.INSTANCE.registerGuiHandler(PickleTweaks.instance, new GuiHandler());

	}

	public void postInit(FMLPostInitializationEvent e){
		ModConfig.post();
		ModRecipes.post();
		
		ToolTweaks.findToolsFromConfig();
		
//		if(ModConfig.COUNTER_TOOLTIPS){
//			MinecraftForge.EVENT_BUS.register(new FeatureKillCounter());
//			MinecraftForge.EVENT_BUS.register(new FeatureBlockHoedCounter());
//		}
		
		MinecraftForge.EVENT_BUS.register(new TweakFlintDrop());
		MinecraftForge.EVENT_BUS.register(new TweakToolUselessifier());
		MinecraftForge.EVENT_BUS.register(new TweakHoeUselessifier());
		MinecraftForge.EVENT_BUS.register(new TweakWeaponUselessifier());
	}
}
