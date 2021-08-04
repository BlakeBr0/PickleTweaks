package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseGlassBlock;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class DarkGlassBlock extends BaseGlassBlock implements IEnableable {
	public DarkGlassBlock() {
		super(Material.GLASS, SoundType.STONE, 1.0F, 1.0F);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
		return 255;
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_DARK_GLASS.get();
	}
}
