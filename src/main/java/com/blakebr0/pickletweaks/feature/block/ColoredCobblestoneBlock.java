package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.iface.IColored;
import net.minecraft.world.level.block.SoundType;

public class ColoredCobblestoneBlock extends BaseBlock implements IColored {
	private final int color;

	public ColoredCobblestoneBlock(int color) {
		super(p -> p
				.strength(2.0F, 10.0F)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops()
		);

		this.color = color;
	}

	@Override
	public int getColor(int i) {
		return this.color;
	}
}
