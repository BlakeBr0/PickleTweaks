package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ColoredCobblestoneBlock extends BaseBlock implements IEnableable {
	public ColoredCobblestoneBlock() {
		super(Material.ROCK, SoundType.STONE, 2.0F, 10.0F);
	}

	@Override
	public boolean isEnabled() {
		return !ModConfigs.isLoaded() || ModConfigs.ENABLE_COLORED_COBBLESTONE.get();
	}
}
