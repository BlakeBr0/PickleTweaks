package com.blakebr0.pickletweaks.feature;

import com.blakebr0.cucumber.helper.CropHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class FeatureRightClickHarvest {
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
			var entity = event.getEntity();
			var hand = event.getHand();

			if (block instanceof CropBlock crop) {
				var seed = CropHelper.getSeedsItem(crop);

				if (crop.isMaxAge(state) && seed != null) {
					handleDrops(state, level, pos, seed);

					entity.swing(hand, true);

					var sound = block.getSoundType(state, level, pos, entity).getBreakSound();

					level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.setBlockAndUpdate(pos, crop.getStateForAge(0));
				}
			}

			if (block instanceof NetherWartBlock && state.getValue(NetherWartBlock.AGE) == 3) {
				handleDrops(state, level, pos, Items.NETHER_WART);

				entity.swing(hand, true);

				var sound = block.getSoundType(state, level, pos, entity).getBreakSound();

				level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.setBlockAndUpdate(pos, state.setValue(NetherWartBlock.AGE, 0));
			}
		}
	}

	private static void handleDrops(BlockState state, Level level, BlockPos pos, ItemLike seed) {
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
	}
}
