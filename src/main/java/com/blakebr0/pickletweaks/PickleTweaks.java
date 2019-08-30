package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.FeatureBowInfo;
import com.blakebr0.pickletweaks.feature.FeatureToolInfo;
import com.blakebr0.pickletweaks.feature.handler.NightVisionGogglesHandler;
import com.blakebr0.pickletweaks.feature.handler.ToggleMagnetInInventoryHandler;
import com.blakebr0.pickletweaks.network.NetworkHandler;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import com.blakebr0.pickletweaks.registry.ModItems;
import com.blakebr0.pickletweaks.registry.ModRecipeSerializers;
import com.blakebr0.pickletweaks.tweaks.TweakToolBreaking;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
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
		MinecraftForge.EVENT_BUS.register(new TweakToolBreaking());

		DeferredWorkQueue.runLater(() -> {
			NetworkHandler.onCommonSetup();
		});
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
		MinecraftForge.EVENT_BUS.register(new FeatureToolInfo());
		MinecraftForge.EVENT_BUS.register(new FeatureBowInfo());
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onBlockColors(ColorHandlerEvent.Block event) {
		BlockColors colors = event.getBlockColors();

		colors.register(new IColored.BlockColors(),
				ModBlocks.WHITE_COBBLESTONE,
				ModBlocks.ORANGE_COBBLESTONE,
				ModBlocks.MAGENTA_COBBLESTONE,
				ModBlocks.LIGHT_BLUE_COBBLESTONE,
				ModBlocks.YELLOW_COBBLESTONE,
				ModBlocks.LIME_COBBLESTONE,
				ModBlocks.PINK_COBBLESTONE,
				ModBlocks.GRAY_COBBLESTONE,
				ModBlocks.LIGHT_GRAY_COBBLESTONE,
				ModBlocks.CYAN_COBBLESTONE,
				ModBlocks.PURPLE_COBBLESTONE,
				ModBlocks.BLUE_COBBLESTONE,
				ModBlocks.BROWN_COBBLESTONE,
				ModBlocks.GREEN_COBBLESTONE,
				ModBlocks.RED_COBBLESTONE,
				ModBlocks.BLACK_COBBLESTONE
		);
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onItemColors(ColorHandlerEvent.Item event) {
		ItemColors colors = event.getItemColors();

		colors.register(new IColored.ItemBlockColors(),
				ModBlocks.WHITE_COBBLESTONE,
				ModBlocks.ORANGE_COBBLESTONE,
				ModBlocks.MAGENTA_COBBLESTONE,
				ModBlocks.LIGHT_BLUE_COBBLESTONE,
				ModBlocks.YELLOW_COBBLESTONE,
				ModBlocks.LIME_COBBLESTONE,
				ModBlocks.PINK_COBBLESTONE,
				ModBlocks.GRAY_COBBLESTONE,
				ModBlocks.LIGHT_GRAY_COBBLESTONE,
				ModBlocks.CYAN_COBBLESTONE,
				ModBlocks.PURPLE_COBBLESTONE,
				ModBlocks.BLUE_COBBLESTONE,
				ModBlocks.BROWN_COBBLESTONE,
				ModBlocks.GREEN_COBBLESTONE,
				ModBlocks.RED_COBBLESTONE,
				ModBlocks.BLACK_COBBLESTONE
		);
	}
}
