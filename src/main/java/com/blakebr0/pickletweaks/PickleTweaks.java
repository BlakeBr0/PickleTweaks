package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.helper.ConfigHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.FeatureBowInfo;
import com.blakebr0.pickletweaks.feature.FeatureRightClickHarvest;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.handler.ColorHandler;
import com.blakebr0.pickletweaks.feature.handler.NightVisionGogglesHandler;
import com.blakebr0.pickletweaks.feature.handler.ToggleMagnetInInventoryHandler;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import com.blakebr0.pickletweaks.registry.ModItems;
import com.blakebr0.pickletweaks.registry.ModRecipeSerializers;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PickleTweaks.MOD_ID)
public class PickleTweaks {
	public static final String MOD_ID = "pickletweaks";
	public static final String NAME = "Pickle Tweaks";

	public static final ItemGroup ITEM_GROUP = new PTItemGroup();
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public PickleTweaks() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);
		bus.register(new ModBlocks());
		bus.register(new ModItems());
		bus.register(new ModRecipeSerializers());

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		ConfigHelper.load(ModConfigs.COMMON, "pickletweaks-common.toml");
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new NightVisionGogglesHandler());
		MinecraftForge.EVENT_BUS.register(new FeatureRightClickHarvest());
		MinecraftForge.EVENT_BUS.register(new TweakToolBreaking());
		MinecraftForge.EVENT_BUS.register(new TweakToolUselessifier());

		DeferredWorkQueue.runLater(() -> {
			NetworkHandler.onCommonSetup();
		});
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ToggleMagnetInInventoryHandler());
		MinecraftForge.EVENT_BUS.register(new FeatureToolInfo());
		MinecraftForge.EVENT_BUS.register(new FeatureBowInfo());

		ColorHandler.onClientSetup();
		ModelHandler.onClientSetup();
	}
}
