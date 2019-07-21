package com.blakebr0.pickletweaks.proxy;

import com.blakebr0.cucumber.item.color.ItemDyeColorHandler;
import com.blakebr0.pickletweaks.config.ModConfigold;
import com.blakebr0.pickletweaks.feature.item.ItemReinforcement;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom;
import com.blakebr0.pickletweaks.registry.ModBlocks;
import com.blakebr0.pickletweaks.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.EnumDyeColor;
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
		ItemColors colors = Minecraft.getMinecraft().getItemColors();
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		
		if (ModConfigold.confDyePowder) {
			colors.registerItemColorHandler(new ItemDyeColorHandler(), ModItems.itemDyePowder);
		}
		
		if (ModConfigold.confRepairKits) {
			colors.registerItemColorHandler((stack, tintIndex) -> {
				com.blakebr0.pickletweaks.feature.item.ItemRepairKit.Kit kit = ItemRepairKit.kits.get(stack.getMetadata());
				return kit != null && tintIndex == 0 ? kit.color : -1;
			}, ModItems.itemRepairKit);
			
			colors.registerItemColorHandler((stack, tintIndex) -> {
				com.blakebr0.pickletweaks.feature.item.ItemRepairKitCustom.Kit kit = ItemRepairKitCustom.kits.get(stack.getMetadata());
				return kit != null && tintIndex == 0 ? kit.color : -1;
			}, ModItems.itemRepairKitCustom);
		}
		
		if (ModConfigold.confReinforcements) {
			colors.registerItemColorHandler((stack, tintIndex) -> {
				return tintIndex == 0 ? ItemReinforcement.TYPES.get(stack.getMetadata()).color : -1;
			}, ModItems.itemReinforcement);
		}
		
		if (ModConfigold.confColoredCobblestone) {
			blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> {
				return EnumDyeColor.byMetadata(state.getBlock().getMetaFromState(state)).getColorValue();
			}, ModBlocks.COLORED_COBBLESTONE);
			
			colors.registerItemColorHandler(new ItemDyeColorHandler(), ModBlocks.COLORED_COBBLESTONE);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}
