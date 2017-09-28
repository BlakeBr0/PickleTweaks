package com.blakebr0.pickletweaks.feature;

import java.util.List;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.config.ModConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class FeatureWailaTooltips implements IWailaDataProvider {

	public static void callbackRegister(IWailaRegistrar registrar) {
		if (!ModConfig.confWailaHarvestLevel) { return; }
		registrar.registerBodyProvider(new FeatureWailaTooltips(), Block.class);
	}

	@Override
	public List<String> getWailaBody(ItemStack arg0, List<String> arg1, IWailaDataAccessor arg2, IWailaConfigHandler arg3) {
		Block block = arg2.getBlock();
		IBlockState state = arg2.getBlockState();
		if (block != null && state != null) {
			if (block.getHarvestLevel(state) > -1) {
				arg1.add(Utils.localize("tooltip.pt.harvest_level") + " " + getHarvestLevelName(block, state));
			}
		}
		return arg1;
	}
	
	public String getHarvestLevelName(Block block, IBlockState state) {
		int level = block.getHarvestLevel(state);
		if (FeatureToolInfo.names.containsKey(level)) {
			return FeatureToolInfo.names.get(level);
		}
		return level + "";
	}
}
