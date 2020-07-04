package com.blakebr0.pickletweaks;

import com.blakebr0.pickletweaks.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class PTItemGroup extends ItemGroup {
	public PTItemGroup() {
		super(PickleTweaks.MOD_ID);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.EMERALD_APPLE.get());
	}
}
