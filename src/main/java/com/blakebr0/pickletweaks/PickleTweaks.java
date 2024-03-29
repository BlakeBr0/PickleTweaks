package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.helper.ConfigHelper;
import com.blakebr0.pickletweaks.compat.curios.CuriosCompat;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.FeatureBowInfo;
import com.blakebr0.pickletweaks.feature.FeatureRightClickHarvest;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.client.handler.ColorHandler;
import com.blakebr0.pickletweaks.feature.client.handler.NightVisionGogglesHandler;
import com.blakebr0.pickletweaks.feature.client.handler.ToggleMagnetInInventoryHandler;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverrides;
import com.blakebr0.pickletweaks.init.ModBlocks;
import com.blakebr0.pickletweaks.init.ModCreativeModeTabs;
import com.blakebr0.pickletweaks.init.ModItems;
import com.blakebr0.pickletweaks.init.ModRecipeSerializers;
import com.blakebr0.pickletweaks.lib.ModItemTier;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(PickleTweaks.MOD_ID)
public final class PickleTweaks {
	public static final String MOD_ID = "pickletweaks";
	public static final String NAME = "Pickle Tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public PickleTweaks() {
		var bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);

		ModBlocks.REGISTRY.register(bus);
		ModItems.REGISTRY.register(bus);
		ModCreativeModeTabs.REGISTRY.register(bus);
		ModRecipeSerializers.REGISTRY.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.register(new ColorHandler());
			bus.register(new ModelHandler());
		});

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

		if (ModConfigs.isCuriosInstalled()) {
			MinecraftForge.EVENT_BUS.register(new CuriosCompat());
		}

		ModItemTier.onCommonSetup();

		event.enqueueWork(() -> {
			NetworkHandler.onCommonSetup();
			GridRepairOverrides.onCommonSetup();
		});
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ToggleMagnetInInventoryHandler());
		MinecraftForge.EVENT_BUS.register(new FeatureToolInfo());
		MinecraftForge.EVENT_BUS.register(new FeatureBowInfo());
	}

	@SubscribeEvent
	public void onInterModEnqueue(InterModEnqueueEvent event) {
		if (ModConfigs.isCuriosInstalled()) {
			CuriosCompat.onInterModEnqueue(event);
		}
	}
}
