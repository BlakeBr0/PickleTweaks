package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.SickleItem;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.world.item.Item.Properties;

public class FlintSickleItem extends SickleItem {
    public FlintSickleItem(Function<Properties, Properties> properties) {
        super(ModItemTier.FLINT, 1, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SICKLES.get() && ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
