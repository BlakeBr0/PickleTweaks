package com.blakebr0.pickletweaks.init;

import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.block.ColoredCobblestoneBlock;
import com.blakebr0.pickletweaks.feature.block.DarkGlassBlock;
import com.blakebr0.pickletweaks.feature.block.SmoothGlowstoneBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public final class ModBlocks {
	public static final Map<RegistryObject<Block>, Supplier<Block>> ENTRIES = new LinkedHashMap<>();

	public static final RegistryObject<Block> WHITE_COBBLESTONE = register("white_cobblestone", () -> new ColoredCobblestoneBlock(16383998));
	public static final RegistryObject<Block> ORANGE_COBBLESTONE = register("orange_cobblestone", () -> new ColoredCobblestoneBlock(16351261));
	public static final RegistryObject<Block> MAGENTA_COBBLESTONE = register("magenta_cobblestone", () -> new ColoredCobblestoneBlock(13061821));
	public static final RegistryObject<Block> LIGHT_BLUE_COBBLESTONE = register("light_blue_cobblestone", () -> new ColoredCobblestoneBlock(3847130));
	public static final RegistryObject<Block> YELLOW_COBBLESTONE = register("yellow_cobblestone", () -> new ColoredCobblestoneBlock(16701501));
	public static final RegistryObject<Block> LIME_COBBLESTONE = register("lime_cobblestone", () -> new ColoredCobblestoneBlock(8439583));
	public static final RegistryObject<Block> PINK_COBBLESTONE = register("pink_cobblestone", () -> new ColoredCobblestoneBlock(15961002));
	public static final RegistryObject<Block> GRAY_COBBLESTONE = register("gray_cobblestone", () -> new ColoredCobblestoneBlock(4673362));
	public static final RegistryObject<Block> LIGHT_GRAY_COBBLESTONE = register("light_gray_cobblestone", () -> new ColoredCobblestoneBlock(10329495));
	public static final RegistryObject<Block> CYAN_COBBLESTONE = register("cyan_cobblestone", () -> new ColoredCobblestoneBlock(1481884));
	public static final RegistryObject<Block> PURPLE_COBBLESTONE = register("purple_cobblestone", () -> new ColoredCobblestoneBlock(8991416));
	public static final RegistryObject<Block> BLUE_COBBLESTONE = register("blue_cobblestone", () -> new ColoredCobblestoneBlock(3949738));
	public static final RegistryObject<Block> BROWN_COBBLESTONE = register("brown_cobblestone", () -> new ColoredCobblestoneBlock(8606770));
	public static final RegistryObject<Block> GREEN_COBBLESTONE = register("green_cobblestone", () -> new ColoredCobblestoneBlock(6192150));
	public static final RegistryObject<Block> RED_COBBLESTONE = register("red_cobblestone", () -> new ColoredCobblestoneBlock(11546150));
	public static final RegistryObject<Block> BLACK_COBBLESTONE = register("black_cobblestone", () -> new ColoredCobblestoneBlock(1908001));

	public static final RegistryObject<Block> DARK_GLASS = register("dark_glass", DarkGlassBlock::new);
	public static final RegistryObject<Block> SMOOTH_GLOWSTONE = register("smooth_glowstone", SmoothGlowstoneBlock::new);

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		ENTRIES.forEach((reg, block) -> {
			registry.register(block.get());
			reg.updateReference(registry);
		});
	}

	private static RegistryObject<Block> register(String name, Supplier<Block> block) {
		return register(name, block, b -> () -> new BaseBlockItem(b.get(), p -> p.tab(ITEM_GROUP)));
	}

	private static RegistryObject<Block> register(String name, Supplier<Block> block, Function<RegistryObject<Block>, Supplier<? extends BlockItem>> item) {
		ResourceLocation loc = new ResourceLocation(PickleTweaks.MOD_ID, name);
		RegistryObject<Block> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
		ENTRIES.put(reg, () -> block.get().setRegistryName(loc));
		ModItems.BLOCK_ENTRIES.add(() -> item.apply(reg).get().setRegistryName(loc));
		return reg;
	}
}
