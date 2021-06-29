package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class PaxelItem extends ToolItem implements IEnableable {
	private static final Set<Material> EFFECTIVE_ON_MATERIALS = Sets.newHashSet(Material.WOOD, Material.NETHER_WOOD, Material.PLANT, Material.REPLACEABLE_PLANT, Material.BAMBOO, Material.VEGETABLE);
    private static final Map<Block, BlockState> PATH_STUFF = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.defaultBlockState()));

    public PaxelItem(IItemTier tier, Function<Properties, Properties> properties) {
		super(4.0F, -3.2F, tier, new HashSet<>(), properties.apply(new Properties()
				.defaultDurability((int) (tier.getUses() * 1.5))
				.addToolType(ToolType.PICKAXE, tier.getLevel())
				.addToolType(ToolType.SHOVEL, tier.getLevel())
				.addToolType(ToolType.AXE, tier.getLevel())
		));
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isEnabled()) {
			super.fillItemCategory(group, items);
		}
    }

	@Override
	public boolean isCorrectToolForDrops(BlockState state) {
		int i = this.getTier().getLevel();
		if (state.getHarvestTool() == ToolType.PICKAXE)
			return i >= state.getHarvestLevel();

		Material material = state.getMaterial();
		return material == Material.STONE || material == Material.METAL || material == Material.HEAVY_METAL
				|| state.is(Blocks.SNOW) || state.is(Blocks.SNOW_BLOCK);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.METAL && material != Material.HEAVY_METAL && material != Material.STONE
				&& !EFFECTIVE_ON_MATERIALS.contains(material)
				? super.getDestroySpeed(stack, state)
				: this.speed;
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		PlayerEntity player = context.getPlayer();
		ItemStack stack = context.getItemInHand();

		BlockState state = world.getBlockState(pos);
		BlockState modifiedState = state.getToolModifiedState(world, pos, player, stack, ToolType.AXE);

		if (modifiedState != null) {
			world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);

			if (!world.isClientSide()) {
				world.setBlock(pos, modifiedState, 11);

				if (player != null) {
					stack.hurtAndBreak(1, player, entity -> {
						entity.broadcastBreakEvent(context.getHand());
					});
				}
			}

			return ActionResultType.sidedSuccess(world.isClientSide());
		} else if (context.getClickedFace() != Direction.DOWN && world.getBlockState(pos.above()).isAir(world, pos.above())) {
			BlockState pathState = PATH_STUFF.get(state.getBlock());
			if (pathState != null) {
				world.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (!world.isClientSide()) {
					world.setBlock(pos, pathState, 11);

					if (player != null) {
						stack.hurtAndBreak(1, player, entity -> {
							entity.broadcastBreakEvent(context.getHand());
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
		return ModConfigs.ENABLE_PAXELS.get();
	}
}
