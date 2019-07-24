package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.pickletweaks.feature.block.ColoredCobblestoneBlock;
import com.blakebr0.pickletweaks.feature.block.DarkGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistry;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public class ModBlocks {
	public static final RegistryObject<ColoredCobblestoneBlock> WHITE_COBBLESTONE = get("white_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> ORANGE_COBBLESTONE = get("orange_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> MAGENTA_COBBLESTONE = get("magenta_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> LIGHT_BLUE_COBBLESTONE = get("light_blue_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> YELLOW_COBBLESTONE = get("yellow_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> LIME_COBBLESTONE = get("lime_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> PINK_COBBLESTONE = get("pink_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> GRAY_COBBLESTONE = get("gray_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> LIGHT_GRAY_COBBLESTONE = get("light_gray_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> CYAN_COBBLESTONE = get("cyan_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> PURPLE_COBBLESTONE = get("purple_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> BLUE_COBBLESTONE = get("blue_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> BROWN_COBBLESTONE = get("brown_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> GREEN_COBBLESTONE = get("green_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> RED_COBBLESTONE = get("red_cobblestone");
	public static final RegistryObject<ColoredCobblestoneBlock> BLACK_COBBLESTONE = get("black_cobblestone");

	public static final RegistryObject<DarkGlassBlock> DARK_GLASS = get("dark_glass");

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registerAll(registry,
				new ColoredCobblestoneBlock().setRegistryName("white_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("orange_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("magenta_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("light_blue_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("yellow_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("lime_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("pink_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("gray_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("light_gray_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("cyan_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("purple_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("blue_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("brown_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("green_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("red_cobblestone"),
				new ColoredCobblestoneBlock().setRegistryName("black_cobblestone"),

				new DarkGlassBlock().setRegistryName("dark_glass")
		);
	}

	private static void registerAll(IForgeRegistry<Block> registry, Block... blocks) {
		for (Block block : blocks)
			register(registry, block);
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

	private static <T extends Block> RegistryObject<T> get(String name) {
		return RegistryObject.of("pickletweaks:" + name, () -> Block.class);
	}
}
