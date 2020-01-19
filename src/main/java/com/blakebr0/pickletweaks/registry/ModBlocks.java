package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.block.ColoredCobblestoneBlock;
import com.blakebr0.pickletweaks.feature.block.DarkGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public class ModBlocks {
	public static final List<Supplier<? extends Block>> ENTRIES = new ArrayList<>();

	public static final RegistryObject<ColoredCobblestoneBlock> WHITE_COBBLESTONE = register("white_cobblestone", () -> new ColoredCobblestoneBlock(16383998));
	public static final RegistryObject<ColoredCobblestoneBlock> ORANGE_COBBLESTONE = register("orange_cobblestone", () -> new ColoredCobblestoneBlock(16351261));
	public static final RegistryObject<ColoredCobblestoneBlock> MAGENTA_COBBLESTONE = register("magenta_cobblestone", () -> new ColoredCobblestoneBlock(13061821));
	public static final RegistryObject<ColoredCobblestoneBlock> LIGHT_BLUE_COBBLESTONE = register("light_blue_cobblestone", () -> new ColoredCobblestoneBlock(3847130));
	public static final RegistryObject<ColoredCobblestoneBlock> YELLOW_COBBLESTONE = register("yellow_cobblestone", () -> new ColoredCobblestoneBlock(16701501));
	public static final RegistryObject<ColoredCobblestoneBlock> LIME_COBBLESTONE = register("lime_cobblestone", () -> new ColoredCobblestoneBlock(8439583));
	public static final RegistryObject<ColoredCobblestoneBlock> PINK_COBBLESTONE = register("pink_cobblestone", () -> new ColoredCobblestoneBlock(15961002));
	public static final RegistryObject<ColoredCobblestoneBlock> GRAY_COBBLESTONE = register("gray_cobblestone", () -> new ColoredCobblestoneBlock(4673362));
	public static final RegistryObject<ColoredCobblestoneBlock> LIGHT_GRAY_COBBLESTONE = register("light_gray_cobblestone", () -> new ColoredCobblestoneBlock(10329495));
	public static final RegistryObject<ColoredCobblestoneBlock> CYAN_COBBLESTONE = register("cyan_cobblestone", () -> new ColoredCobblestoneBlock(1481884));
	public static final RegistryObject<ColoredCobblestoneBlock> PURPLE_COBBLESTONE = register("purple_cobblestone", () -> new ColoredCobblestoneBlock(8991416));
	public static final RegistryObject<ColoredCobblestoneBlock> BLUE_COBBLESTONE = register("blue_cobblestone", () -> new ColoredCobblestoneBlock(3949738));
	public static final RegistryObject<ColoredCobblestoneBlock> BROWN_COBBLESTONE = register("brown_cobblestone", () -> new ColoredCobblestoneBlock(8606770));
	public static final RegistryObject<ColoredCobblestoneBlock> GREEN_COBBLESTONE = register("green_cobblestone", () -> new ColoredCobblestoneBlock(6192150));
	public static final RegistryObject<ColoredCobblestoneBlock> RED_COBBLESTONE = register("red_cobblestone", () -> new ColoredCobblestoneBlock(11546150));
	public static final RegistryObject<ColoredCobblestoneBlock> BLACK_COBBLESTONE = register("black_cobblestone", () -> new ColoredCobblestoneBlock(1908001));

	public static final RegistryObject<DarkGlassBlock> DARK_GLASS = register("dark_glass", DarkGlassBlock::new);

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		ENTRIES.stream().map(Supplier::get).forEach(registry::register);
	}

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
		return register(name, block, b -> () -> new BaseBlockItem(b.get(), p -> p.group(ITEM_GROUP)));
	}

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> item) {
		ResourceLocation loc = new ResourceLocation(PickleTweaks.MOD_ID, name);
		ENTRIES.add(() -> block.get().setRegistryName(loc));
		RegistryObject<T> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
		ModItems.BLOCK_ENTRIES.add(() -> item.apply(reg).get().setRegistryName(loc));
		return reg;
	}
}
