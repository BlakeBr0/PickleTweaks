package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ColoredCobblestoneBlock extends BaseBlock implements IEnableable, IColored {
	private final int color;

	public ColoredCobblestoneBlock(int color) {
		super(Material.ROCK, p -> p
				.hardnessAndResistance(2.0F, 10.0F)
				.sound(SoundType.STONE)
				.setRequiresTool()
		);

		this.color = color;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_COLORED_COBBLESTONE.get();
	}

	@Override
	public int getColor(int i) {
		return this.color;
	}
}
