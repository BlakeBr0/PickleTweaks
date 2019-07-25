package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;

import java.util.HashSet;
import java.util.function.Function;

public class PaxelItem extends ToolItem implements IEnableable {
	public PaxelItem(IItemTier tier, Function<Properties, Properties> properties) {
		super(4.0F, -3.2F, tier, new HashSet<>(), properties.apply(new Properties()
				.defaultMaxDamage((int) (tier.getMaxUses() * 1.5))
				.addToolType(ToolType.PICKAXE, tier.getHarvestLevel())
				.addToolType(ToolType.SHOVEL, tier.getHarvestLevel())
				.addToolType(ToolType.AXE, tier.getHarvestLevel())
		));
	}

	public boolean canHarvestBlock(BlockState state) {
		Block block = state.getBlock();
		int i = this.getTier().getHarvestLevel();
		if (state.getHarvestTool() == ToolType.PICKAXE)
			return i >= state.getHarvestLevel();

		Material material = state.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL
				|| block == Blocks.SNOW || block == Blocks.SNOW_BLOCK;
	}

	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
				&& material != Material.WOOD && material != Material.PLANTS && material != Material.TALL_PLANTS
				&& material != Material.BAMBOO ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public boolean isEnabled() {
		return !ModConfigs.isLoaded() || ModConfigs.ENABLE_PAXELS.get();
	}
}
