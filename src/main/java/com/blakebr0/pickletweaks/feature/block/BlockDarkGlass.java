package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockDarkGlass extends BlockBreakable implements IEnableable {

	public BlockDarkGlass() {
		super(Material.GLASS, false);
		this.setUnlocalizedName("pt.dark_glass");
		this.setCreativeTab(PickleTweaks.tab);
		this.setSoundType(SoundType.GLASS);
		this.setHardness(1.0F);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 255;
	}
	
	@Override
	public boolean isEnabled() {
		return ModConfig.confDarkGlass;
	}
}
