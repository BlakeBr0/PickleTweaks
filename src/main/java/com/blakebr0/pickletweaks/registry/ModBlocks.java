package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.block.ColoredCobblestoneBlock;
import com.blakebr0.pickletweaks.feature.block.DarkGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

@ObjectHolder(PickleTweaks.MOD_ID)
public class ModBlocks {
	@ObjectHolder("white_cobblestone")
	public static final ColoredCobblestoneBlock WHITE_COBBLESTONE = null;
	@ObjectHolder("orange_cobblestone")
	public static final ColoredCobblestoneBlock ORANGE_COBBLESTONE = null;
	@ObjectHolder("magenta_cobblestone")
	public static final ColoredCobblestoneBlock MAGENTA_COBBLESTONE = null;
	@ObjectHolder("light_blue_cobblestone")
	public static final ColoredCobblestoneBlock LIGHT_BLUE_COBBLESTONE = null;
	@ObjectHolder("yellow_cobblestone")
	public static final ColoredCobblestoneBlock YELLOW_COBBLESTONE = null;
	@ObjectHolder("lime_cobblestone")
	public static final ColoredCobblestoneBlock LIME_COBBLESTONE = null;
	@ObjectHolder("pink_cobblestone")
	public static final ColoredCobblestoneBlock PINK_COBBLESTONE = null;
	@ObjectHolder("gray_cobblestone")
	public static final ColoredCobblestoneBlock GRAY_COBBLESTONE = null;
	@ObjectHolder("light_gray_cobblestone")
	public static final ColoredCobblestoneBlock LIGHT_GRAY_COBBLESTONE = null;
	@ObjectHolder("cyan_cobblestone")
	public static final ColoredCobblestoneBlock CYAN_COBBLESTONE = null;
	@ObjectHolder("purple_cobblestone")
	public static final ColoredCobblestoneBlock PURPLE_COBBLESTONE = null;
	@ObjectHolder("blue_cobblestone")
	public static final ColoredCobblestoneBlock BLUE_COBBLESTONE = null;
	@ObjectHolder("brown_cobblestone")
	public static final ColoredCobblestoneBlock BROWN_COBBLESTONE = null;
	@ObjectHolder("green_cobblestone")
	public static final ColoredCobblestoneBlock GREEN_COBBLESTONE = null;
	@ObjectHolder("red_cobblestone")
	public static final ColoredCobblestoneBlock RED_COBBLESTONE = null;
	@ObjectHolder("black_cobblestone")
	public static final ColoredCobblestoneBlock BLACK_COBBLESTONE = null;

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		Block whiteCobblestone = new ColoredCobblestoneBlock(16383998);
		Block orangeCobblestone = new ColoredCobblestoneBlock(16351261);
		Block magentaCobblestone = new ColoredCobblestoneBlock(13061821);
		Block lightBlueCobblestone = new ColoredCobblestoneBlock(3847130);
		Block yellowCobblestone = new ColoredCobblestoneBlock(16701501);
		Block limeCobblestone = new ColoredCobblestoneBlock(8439583);
		Block pinkCobblestone = new ColoredCobblestoneBlock(15961002);
		Block grayCobblestone = new ColoredCobblestoneBlock(4673362);
		Block lightGrayCobblestone = new ColoredCobblestoneBlock(10329495);
		Block cyanCobblestone = new ColoredCobblestoneBlock(1481884);
		Block purpleCobblestone = new ColoredCobblestoneBlock(8991416);
		Block blueCobblestone = new ColoredCobblestoneBlock(3949738);
		Block brownCobblestone = new ColoredCobblestoneBlock(8606770);
		Block greenCobblestone = new ColoredCobblestoneBlock(6192150);
		Block redCobblestone = new ColoredCobblestoneBlock(11546150);
		Block blackCobblestone = new ColoredCobblestoneBlock(1908001);

		Block darkGlass = new DarkGlassBlock();

		register(registry, whiteCobblestone.setRegistryName("white_cobblestone"));
		register(registry, orangeCobblestone.setRegistryName("orange_cobblestone"));
		register(registry, magentaCobblestone.setRegistryName("magenta_cobblestone"));
		register(registry, lightBlueCobblestone.setRegistryName("light_blue_cobblestone"));
		register(registry, yellowCobblestone.setRegistryName("yellow_cobblestone"));
		register(registry, limeCobblestone.setRegistryName("lime_cobblestone"));
		register(registry, pinkCobblestone.setRegistryName("pink_cobblestone"));
		register(registry, grayCobblestone.setRegistryName("gray_cobblestone"));
		register(registry, lightGrayCobblestone.setRegistryName("light_gray_cobblestone"));
		register(registry, cyanCobblestone.setRegistryName("cyan_cobblestone"));
		register(registry, purpleCobblestone.setRegistryName("purple_cobblestone"));
		register(registry, blueCobblestone.setRegistryName("blue_cobblestone"));
		register(registry, brownCobblestone.setRegistryName("brown_cobblestone"));
		register(registry, greenCobblestone.setRegistryName("green_cobblestone"));
		register(registry, redCobblestone.setRegistryName("red_cobblestone"));
		register(registry, blackCobblestone.setRegistryName("black_cobblestone"));

		register(registry, darkGlass.setRegistryName("dark_glass"));
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
