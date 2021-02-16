package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.ScytheItem;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

public class EmeraldScytheItem extends ScytheItem {
    public EmeraldScytheItem(Function<Properties, Properties> properties) {
        super(ModItemTier.EMERALD, 3, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SCYTHES.get() && ModConfigs.ENABLE_EMERALD_GEAR.get();
    }
}