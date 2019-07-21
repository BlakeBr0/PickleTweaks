package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.item.IItemTier;
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

	@Override
	public boolean isEnabled() {
		return true;
//		return ModConfigs.ENABLE_PAXELS.get();
	}
}
