package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.pickletweaks.feature.block.ColoredCobblestoneBlock;
import com.blakebr0.pickletweaks.feature.block.DarkGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public class ModBlocks {
	public static final ColoredCobblestoneBlock WHITE_COBBLESTONE = new ColoredCobblestoneBlock();
	public static final ColoredCobblestoneBlock ORANGE_COBBLESTONE = new ColoredCobblestoneBlock();
	public static final DarkGlassBlock DARK_GLASS = new DarkGlassBlock();

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		register(registry, WHITE_COBBLESTONE.setRegistryName("white_cobblestone"));
		register(registry, ORANGE_COBBLESTONE.setRegistryName("orange_cobblestone"));
		register(registry, DARK_GLASS.setRegistryName("dark_glass"));
	}

	private static void register(IForgeRegistry<Block> registry, Block block) {
		if (block.getRegistryName() != null) {
			BaseBlockItem item = new BaseBlockItem(block, p -> p.group(ITEM_GROUP));
			item.setRegistryName(block.getRegistryName());
			register(registry, block, item);
		}
	}

	private static void register(IForgeRegistry<Block> registry, Block block, BlockItem item) {
		registry.register(block);
		ModItems.BLOCK_ITEMS.add(item);
	}
}
