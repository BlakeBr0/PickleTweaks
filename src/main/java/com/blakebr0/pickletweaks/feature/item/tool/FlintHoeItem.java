package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BaseHoeItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class FlintHoeItem extends BaseHoeItem implements IEnableable {
    public FlintHoeItem(Function<Properties, Properties> properties) {
        super(ModItemTier.FLINT, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
