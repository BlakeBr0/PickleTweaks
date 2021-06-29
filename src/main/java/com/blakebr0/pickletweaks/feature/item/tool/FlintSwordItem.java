package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class FlintSwordItem extends BaseSwordItem implements IEnableable {
    public FlintSwordItem(Function<Properties, Properties> properties) {
        super(ModItemTier.FLINT, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
