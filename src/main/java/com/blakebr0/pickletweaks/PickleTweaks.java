package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.helper.CompoundTagHelper;
import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.handler.NightVisionGogglesHandler;
import com.blakebr0.pickletweaks.feature.handler.ToggleMagnetInInventoryHandler;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import com.blakebr0.pickletweaks.registry.ModItems;
import com.blakebr0.pickletweaks.registry.ModRecipeSerializers;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PickleTweaks.MOD_ID)
public class PickleTweaks {
	public static final String MOD_ID = "pickletweaks";
	public static final String NAME = "Pickle Tweaks";

	public static final ItemGroup ITEM_GROUP = new PTItemGroup();
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final CompoundTagHelper TAG_HELPER = NBTHelper.newCompoundTagHelper(MOD_ID);

	public PickleTweaks() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);
		bus.register(new ModBlocks());
		bus.register(new ModItems());
		bus.register(new ModRecipeSerializers());
		bus.register(new ModConfigs());

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new NightVisionGogglesHandler());

		NetworkHandler.onCommonSetup();
	}

	@SubscribeEvent
	public void onInterModEnqueue(InterModEnqueueEvent event) {

	}

	@SubscribeEvent
	public void onInterModProcess(InterModProcessEvent event) {

	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ToggleMagnetInInventoryHandler());
	}
}
