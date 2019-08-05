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
	public static final ColoredCobblestoneBlock WHITE_COBBLESTONE = new ColoredCobblestoneBlock(16383998);
	public static final ColoredCobblestoneBlock ORANGE_COBBLESTONE = new ColoredCobblestoneBlock(16351261);
	public static final ColoredCobblestoneBlock MAGENTA_COBBLESTONE = new ColoredCobblestoneBlock(13061821);
	public static final ColoredCobblestoneBlock LIGHT_BLUE_COBBLESTONE = new ColoredCobblestoneBlock(3847130);
	public static final ColoredCobblestoneBlock YELLOW_COBBLESTONE = new ColoredCobblestoneBlock(16701501);
	public static final ColoredCobblestoneBlock LIME_COBBLESTONE = new ColoredCobblestoneBlock(8439583);
	public static final ColoredCobblestoneBlock PINK_COBBLESTONE = new ColoredCobblestoneBlock(15961002);
	public static final ColoredCobblestoneBlock GRAY_COBBLESTONE = new ColoredCobblestoneBlock(4673362);
	public static final ColoredCobblestoneBlock LIGHT_GRAY_COBBLESTONE = new ColoredCobblestoneBlock(10329495);
	public static final ColoredCobblestoneBlock CYAN_COBBLESTONE = new ColoredCobblestoneBlock(1481884);
	public static final ColoredCobblestoneBlock PURPLE_COBBLESTONE = new ColoredCobblestoneBlock(8991416);
	public static final ColoredCobblestoneBlock BLUE_COBBLESTONE = new ColoredCobblestoneBlock(3949738);
	public static final ColoredCobblestoneBlock BROWN_COBBLESTONE = new ColoredCobblestoneBlock(8606770);
	public static final ColoredCobblestoneBlock GREEN_COBBLESTONE = new ColoredCobblestoneBlock(6192150);
	public static final ColoredCobblestoneBlock RED_COBBLESTONE = new ColoredCobblestoneBlock(11546150);
	public static final ColoredCobblestoneBlock BLACK_COBBLESTONE = new ColoredCobblestoneBlock(1908001);

	public static final DarkGlassBlock DARK_GLASS = new DarkGlassBlock();

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		register(registry, WHITE_COBBLESTONE.setRegistryName("white_cobblestone"));
		register(registry, ORANGE_COBBLESTONE.setRegistryName("orange_cobblestone"));
		register(registry, MAGENTA_COBBLESTONE.setRegistryName("magenta_cobblestone"));
		register(registry, LIGHT_BLUE_COBBLESTONE.setRegistryName("light_blue_cobblestone"));
		register(registry, YELLOW_COBBLESTONE.setRegistryName("yellow_cobblestone"));
		register(registry, LIME_COBBLESTONE.setRegistryName("lime_cobblestone"));
		register(registry, PINK_COBBLESTONE.setRegistryName("pink_cobblestone"));
		register(registry, GRAY_COBBLESTONE.setRegistryName("gray_cobblestone"));
		register(registry, LIGHT_GRAY_COBBLESTONE.setRegistryName("light_gray_cobblestone"));
		register(registry, CYAN_COBBLESTONE.setRegistryName("cyan_cobblestone"));
		register(registry, PURPLE_COBBLESTONE.setRegistryName("purple_cobblestone"));
		register(registry, BLUE_COBBLESTONE.setRegistryName("blue_cobblestone"));
		register(registry, BROWN_COBBLESTONE.setRegistryName("brown_cobblestone"));
		register(registry, GREEN_COBBLESTONE.setRegistryName("green_cobblestone"));
		register(registry, RED_COBBLESTONE.setRegistryName("red_cobblestone"));
		register(registry, BLACK_COBBLESTONE.setRegistryName("black_cobblestone"));

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
