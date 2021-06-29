package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.ScytheItem;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class FlintScytheItem extends ScytheItem {
    public FlintScytheItem(Function<Properties, Properties> properties) {
        super(ModItemTier.FLINT, 1, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SCYTHES.get() && ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
