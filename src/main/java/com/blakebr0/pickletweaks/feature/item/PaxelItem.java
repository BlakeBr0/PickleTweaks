package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;

public class PaxelItem extends ToolItem implements IEnableable {
    private static final Map<Block, BlockState> PATH_STUFF = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));

    public PaxelItem(IItemTier tier, Function<Properties, Properties> properties) {
		super(4.0F, -3.2F, tier, new HashSet<>(), properties.apply(new Properties()
				.defaultMaxDamage((int) (tier.getMaxUses() * 1.5))
				.addToolType(ToolType.PICKAXE, tier.getHarvestLevel())
				.addToolType(ToolType.SHOVEL, tier.getHarvestLevel())
				.addToolType(ToolType.AXE, tier.getHarvestLevel())
		));
	}

	@Override
	public boolean canHarvestBlock(BlockState state) {
		Block block = state.getBlock();
		int i = this.getTier().getHarvestLevel();
		if (state.getHarvestTool() == ToolType.PICKAXE)
			return i >= state.getHarvestLevel();

		Material material = state.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL
				|| block == Blocks.SNOW || block == Blocks.SNOW_BLOCK;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
				&& material != Material.WOOD && material != Material.PLANTS && material != Material.TALL_PLANTS
				&& material != Material.BAMBOO ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		if (context.getFace() != Direction.DOWN && world.getBlockState(pos.up()).isAir(world, pos.up())) {
			BlockState state = PATH_STUFF.get(world.getBlockState(pos).getBlock());
			if (state != null) {
				PlayerEntity player = context.getPlayer();
				world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(pos, state, 11);
					if (player != null) {
						context.getItem().damageItem(1, player, entity -> {
							entity.sendBreakAnimation(context.getHand());
						});
					}
				}

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	@Override
	public boolean isEnabled() {
		return !ModConfigs.isLoaded() || ModConfigs.ENABLE_PAXELS.get();
	}
}
