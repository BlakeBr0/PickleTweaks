package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.helper.ConfigHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.FeatureBowInfo;
import com.blakebr0.pickletweaks.feature.FeatureRightClickHarvest;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverrides;
import com.blakebr0.pickletweaks.feature.handler.ColorHandler;
import com.blakebr0.pickletweaks.feature.handler.NightVisionGogglesHandler;
import com.blakebr0.pickletweaks.feature.handler.ToggleMagnetInInventoryHandler;
import com.blakebr0.pickletweaks.init.ModBlocks;
import com.blakebr0.pickletweaks.init.ModItems;
import com.blakebr0.pickletweaks.init.ModRecipeSerializers;
import com.blakebr0.pickletweaks.lib.ModItemTier;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PickleTweaks.MOD_ID)
public final class PickleTweaks {
	public static final String MOD_ID = "pickletweaks";
	public static final String NAME = "Pickle Tweaks";

	public static final CreativeModeTab CREATIVE_TAB = new PTCreativeTab();
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	public PickleTweaks() {
		var bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);
		bus.register(new ModRecipeSerializers());

		ModBlocks.REGISTRY.register(bus);
		ModItems.REGISTRY.register(bus);

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
}
