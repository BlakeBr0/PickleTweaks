package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.cucumber.registry.Ore;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.block.BlockColoredCobblestone;
import com.blakebr0.pickletweaks.feature.block.itemblock.ItemBlockColoredCobblestone;

public class ModBlocks {
	
	public static final BlockColoredCobblestone COLORED_COBBLESTONE = new BlockColoredCobblestone();

	public static void init() {
		final ModRegistry registry = PickleTweaks.REGISTRY;

		registry.register(COLORED_COBBLESTONE, "colored_cobblestone", new ItemBlockColoredCobblestone(COLORED_COBBLESTONE), Ore.of("cobblestone"));
	}
}
