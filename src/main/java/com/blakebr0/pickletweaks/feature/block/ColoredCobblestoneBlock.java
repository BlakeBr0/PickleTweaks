package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.pickletweaks.PickleTweaks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ColoredCobblestoneBlock extends BaseBlock {
	public ColoredCobblestoneBlock() {
		super(Material.ROCK, SoundType.STONE, 2.0F, 10.0F);
	}
}
