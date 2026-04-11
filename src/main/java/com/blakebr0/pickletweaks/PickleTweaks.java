package com.blakebr0.pickletweaks;

import com.blakebr0.pickletweaks.compat.curios.CuriosCompat;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.FeatureBowInfo;
import com.blakebr0.pickletweaks.feature.FeatureRightClickHarvest;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.client.ModClientExtensions;
import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.client.handler.TintSourceHandler;
import com.blakebr0.pickletweaks.feature.client.handler.NightVisionGogglesHandler;
import com.blakebr0.pickletweaks.feature.client.handler.ToggleMagnetInInventoryHandler;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverrides;
import com.blakebr0.pickletweaks.handler.RegisterCapabilityHandler;
import com.blakebr0.pickletweaks.init.ModBlocks;
import com.blakebr0.pickletweaks.init.ModCreativeModeTabs;
import com.blakebr0.pickletweaks.init.ModDataComponentTypes;
import com.blakebr0.pickletweaks.init.ModItems;
import com.blakebr0.pickletweaks.init.ModRecipeSerializers;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(PickleTweaks.MOD_ID)
public final class PickleTweaks {
	public static final String MOD_ID = "pickletweaks";
	public static final String NAME = "Pickle Tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public PickleTweaks(IEventBus bus, ModContainer mod) {
		bus.register(this);

		ModBlocks.REGISTRY.register(bus);
		ModDataComponentTypes.REGISTRY.register(bus);
		ModItems.REGISTRY.register(bus);
		ModCreativeModeTabs.REGISTRY.register(bus);
		ModRecipeSerializers.REGISTRY.register(bus);

		bus.register(new NetworkHandler());
		bus.register(new RegisterCapabilityHandler());

		if (FMLEnvironment.getDist() == Dist.CLIENT) {
			bus.register(new TintSourceHandler());
			bus.register(new ModelHandler());
			bus.register(new ModClientExtensions());
		}

		mod.registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		mod.registerConfig(ModConfig.Type.STARTUP, ModConfigs.COMMON, "pickletweaks-common.toml");
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		NeoForge.EVENT_BUS.register(new NightVisionGogglesHandler());
		NeoForge.EVENT_BUS.register(new FeatureRightClickHarvest());
		NeoForge.EVENT_BUS.register(new TweakToolBreaking());
		NeoForge.EVENT_BUS.register(new TweakToolUselessifier());

		if (ModConfigs.isCuriosInstalled()) {
			NeoForge.EVENT_BUS.register(new CuriosCompat());
		}

		event.enqueueWork(() -> {
			GridRepairOverrides.onCommonSetup();
		});
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		NeoForge.EVENT_BUS.register(new ToggleMagnetInInventoryHandler());
		NeoForge.EVENT_BUS.register(new FeatureToolInfo());
		NeoForge.EVENT_BUS.register(new FeatureBowInfo());
	}

	public static Identifier resource(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
