package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseGlassBlock;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DarkGlassBlock extends BaseGlassBlock implements IEnableable {
	public DarkGlassBlock() {
		super(Material.GLASS, SoundType.STONE, 1.0F, 1.0F);
	}

	@Override
	public int getLightBlock(BlockState state, IBlockReader world, BlockPos pos) {
		return 255;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_DARK_GLASS.get();
	}
}
