package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DarkGlassBlock extends BaseGlassBlock {
	public DarkGlassBlock() {
		super(Material.GLASS, SoundType.STONE, 1.0F, 1.0F);
	}

	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 255;
	}
}
