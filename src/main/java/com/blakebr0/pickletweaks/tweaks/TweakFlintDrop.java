/*
package com.blakebr0.pickletweaks.tweaks;

import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ListIterator;

public class TweakFlintDrop {
	@SubscribeEvent
	public void onBlockHarvested(HarvestDropsEvent event) {
		if (ModConfigs.ENABLE_FLINT_DROP_TWEAK.get() && event.getState() != null && event.getState().getBlock() == Blocks.GRAVEL) {
			int itemsToAdd = 0;
			ListIterator<ItemStack> drops = event.getDrops().listIterator();
			boolean gravel = false;
			while (drops.hasNext()) {
				Item item = drops.next().getItem();
				if (item == Items.FLINT) {
					drops.remove();
					itemsToAdd++;
				} else if (item == Item.getItemFromBlock(Blocks.GRAVEL)) {
					gravel = true;
				}
			}
			
			if (!gravel && itemsToAdd > 0) {
				if (flintReplacement != null) {
					event.getDrops().add(new ItemStack(flintReplacement, itemsToAdd, replacementMeta));
				}
			}
		}
	}
}
*/
