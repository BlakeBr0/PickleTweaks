package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.PaxelItem;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.world.item.Item.Properties;

public class FlintPaxelItem extends PaxelItem implements IEnableable {
    public FlintPaxelItem(Function<Properties, Properties> properties) {
        super(ModItemTier.FLINT, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_PAXELS.get() && ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
