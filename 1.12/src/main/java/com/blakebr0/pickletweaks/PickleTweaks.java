package com.blakebr0.pickletweaks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.pickletweaks.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PickleTweaks.MOD_ID, name = PickleTweaks.NAME, version = PickleTweaks.VERSION, dependencies = PickleTweaks.DEPENDENCIES, guiFactory = PickleTweaks.GUI_FACTORY)
public class PickleTweaks {
	
	public static final String MOD_ID = "pickletweaks";
	public static final String NAME = "PickleTweaks";
	public static final String VERSION = "${version}";
	public static final String DEPENDENCIES = "required-after:cucumber@[1.0.2,)";
	public static final String GUI_FACTORY = "com.blakebr0.pickletweaks.config.GuiFactory";
	
	public static final ModRegistry REGISTRY = new ModRegistry(MOD_ID);
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	@Instance(MOD_ID)
	public static PickleTweaks instance = new PickleTweaks();
	
	@SidedProxy(clientSide = "com.blakebr0.pickletweaks.proxy.ClientProxy",
				serverSide = "com.blakebr0.pickletweaks.proxy.ServerProxy")
	
	public static CommonProxy proxy;
	
	public static CreativeTabs tab = new CreativeTab("tab.pickletweaks");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
}
