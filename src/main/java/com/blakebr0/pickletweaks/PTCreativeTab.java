package com.blakebr0.pickletweaks;

import com.blakebr0.pickletweaks.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PTCreativeTab extends CreativeModeTab {
	public PTCreativeTab() {
		super(PickleTweaks.MOD_ID);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ModItems.EMERALD_APPLE.get());
	}
}
