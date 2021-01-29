package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BaseScytheItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class ScytheItem extends BaseScytheItem implements IEnableable {
    public ScytheItem(IItemTier tier, int range, Function<Properties, Properties> properties) {
        super(tier, 4, -2.8F, range, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SCYTHES.get();
    }
}
