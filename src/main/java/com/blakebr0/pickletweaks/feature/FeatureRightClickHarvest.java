package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.List;

public final class FeatureRightClickHarvest {
	private static final Method GET_SEED;

	static {
		GET_SEED = ObfuscationReflectionHelper.findMethod(CropsBlock.class, "getBaseSeedId");
	}

	@SubscribeEvent
	public void onRightClickCrop(RightClickBlock event) {
		if (!ModConfigs.ENABLE_RIGHT_CLICK_HARVEST.get()) return;
		if (event.getPlayer() == null) return;
		if (event.getHand() != Hand.MAIN_HAND) return;

		World world = event.getWorld();
		if (!world.isClientSide()) {
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if (block instanceof CropsBlock) {
				CropsBlock crop = (CropsBlock) block;
				Item seed = getSeed(crop);
				if (crop.isMaxAge(state) && seed != null) {
					List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, pos, world.getBlockEntity(pos));
					for (ItemStack drop : drops) {
						Item item = drop.getItem();
						if (!drop.isEmpty() && item == seed) {
							drop.shrink(1);
							break;
						}
					}

					for (ItemStack drop : drops) {
						if (!drop.isEmpty()) {
							Block.popResource(world, pos, drop);
						}
					}

					world.setBlockAndUpdate(pos, crop.getStateForAge(0));
				}
			}
		}
	}

	private Item getSeed(Block block) {
		try {
			return (Item) GET_SEED.invoke(block);
		} catch (Exception e) {
			PickleTweaks.LOGGER.error("Unable to get seed from crop {}", e.getLocalizedMessage());
		}

		return null;
	}
}
