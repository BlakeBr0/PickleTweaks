package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

public final class FeatureRightClickHarvest {
	private static final Method GET_SEED;

	static {
		GET_SEED = ObfuscationReflectionHelper.findMethod(CropBlock.class, "m_6404_");
	}

	@SubscribeEvent
	public void onRightClickCrop(RightClickBlock event) {
		if (!ModConfigs.ENABLE_RIGHT_CLICK_HARVEST.get())
			return;

		if (event.getEntity() == null)
			return;

		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;

		var level = event.getLevel();

		if (!level.isClientSide()) {
			var pos = event.getPos();
			var state = level.getBlockState(pos);
			var block = state.getBlock();

			if (block instanceof CropBlock crop) {
				var seed = getSeed(crop);

				if (crop.isMaxAge(state) && seed != null) {
					var drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos));

					for (var drop : drops) {
						var item = drop.getItem();

						if (!drop.isEmpty() && item == seed) {
							drop.shrink(1);
							break;
						}
					}

					for (var drop : drops) {
						if (!drop.isEmpty()) {
							Block.popResource(level, pos, drop);
						}
					}

					level.setBlockAndUpdate(pos, crop.getStateForAge(0));
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
