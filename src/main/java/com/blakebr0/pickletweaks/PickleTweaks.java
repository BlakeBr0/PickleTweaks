package com.blakebr0.pickletweaks;

import com.blakebr0.cucumber.helper.CompoundTagHelper;
import com.blakebr0.cucumber.helper.NBTHelper;
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
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
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
		MinecraftForge.EVENT_BUS.register(new FeatureToolInfo());
		MinecraftForge.EVENT_BUS.register(new FeatureBowInfo());
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onBlockColors(ColorHandlerEvent.Block event) {
		BlockColors colors = event.getBlockColors();

		ModBlocks.WHITE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.ORANGE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.MAGENTA_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.LIGHT_BLUE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.YELLOW_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.LIME_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.PINK_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.GRAY_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.LIGHT_GRAY_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.CYAN_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.PURPLE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.BLUE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.BROWN_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.GREEN_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.RED_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
		ModBlocks.BLACK_COBBLESTONE.ifPresent(b -> colors.register(new IColored.BlockColors(), b));
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onItemColors(ColorHandlerEvent.Item event) {
		ItemColors colors = event.getItemColors();

		ModBlocks.WHITE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.ORANGE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.MAGENTA_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.LIGHT_BLUE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.YELLOW_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.LIME_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.PINK_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.GRAY_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.LIGHT_GRAY_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.CYAN_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.PURPLE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.BLUE_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.BROWN_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.GREEN_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.RED_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
		ModBlocks.BLACK_COBBLESTONE.ifPresent(b -> colors.register(new IColored.ItemBlockColors(), b));
	}
}
