package com.blakebr0.pickletweaks.proxy;

import com.blakebr0.cucumber.item.color.ItemDyeColorHandler;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom;
import com.blakebr0.pickletweaks.registry.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		if (ModConfig.confDyePowder) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemDyeColorHandler(), ModItems.itemDyePowder);
		}
		if (ModConfig.confRepairKits) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
				com.blakebr0.pickletweaks.feature.item.ItemRepairKit.Kit kit = ItemRepairKit.kits.get(stack.getMetadata());
				return kit != null ? kit.color : 0xFFFFFF;
			}, ModItems.itemRepairKit);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
				com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom.Kit kit = ItemRepairKitCustom.kits.get(stack.getMetadata());
				return kit != null ? kit.color : 0xFFFFFF;
			}, ModItems.itemRepairKitCustom);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}
