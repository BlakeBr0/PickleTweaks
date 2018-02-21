package com.blakebr0.pickletweaks.feature;

import java.lang.reflect.Method;
import java.util.ListIterator;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class FeatureRightClickHarvest {

	private static final Method GET_SEED;

	static {
		GET_SEED = ReflectionHelper.findMethod(BlockCrops.class, "getSeed", "func_149866_i");
	}

	@SubscribeEvent
	public void onRightClickCrop(RightClickBlock event) {
		if (!ModConfig.confRightClickHarvest) {
			return;
		}
		
		if (event.getEntityPlayer() == null) {
			return;
		}
		
		if (event.getHand() != EnumHand.MAIN_HAND) {
			return;
		}

		IBlockState state = event.getWorld().getBlockState(event.getPos());

		if (state.getBlock() instanceof BlockCrops) {
			BlockCrops crop = (BlockCrops) state.getBlock();
			if (crop.isMaxAge(state) && getSeed(crop) != null) {
				int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, event.getItemStack());
				NonNullList<ItemStack> drops = NonNullList.create();
				crop.getDrops(drops, event.getWorld(), event.getPos(), state, fortune);
				ListIterator<ItemStack> itr = drops.listIterator();
				while (itr.hasNext()) {
					ItemStack drop = itr.next();
					Item seed = drop.getItem();
					if (!drop.isEmpty() && seed != null && seed == getSeed(crop)) {
						drop.shrink(1);
						break;
					}
				}
				event.getEntityPlayer().swingArm(EnumHand.MAIN_HAND);
				
				if (!event.getWorld().isRemote) {
					for (ItemStack drop : drops) {
						if (!drop.isEmpty()) {
							crop.spawnAsEntity(event.getWorld(), event.getPos(), drop);
						}
					}
					ForgeEventFactory.fireBlockHarvesting(drops, event.getWorld(), event.getPos(), state, fortune, 1.0F, false, event.getEntityPlayer());
					event.getWorld().setBlockState(event.getPos(), crop.withAge(0));
				}
			}
		}
	}

	public Item getSeed(Block block) {
		try {
			return (Item) GET_SEED.invoke(block);
		} catch (Exception e) {
			PickleTweaks.LOGGER.error("Unable to load seed from crop {}", e.getLocalizedMessage());
		}
		return null;
	}
}
