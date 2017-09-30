package com.blakebr0.pickletweaks.proxy;

import com.blakebr0.cucumber.item.color.ItemDyeColorHandler;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;
import com.blakebr0.pickletweaks.registry.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e){
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e){
		super.init(e);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemDyeColorHandler(), ModItems.itemDyePowder);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			return ItemRepairKit.kits.get(stack.getMetadata()).color;
		}, ModItems.itemRepairKit);
	}
		
	@Override
	public void postInit(FMLPostInitializationEvent e){
		super.postInit(e);
	}
}
