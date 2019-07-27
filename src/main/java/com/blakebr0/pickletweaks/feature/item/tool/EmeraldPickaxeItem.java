package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BasePickaxeItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

public class EmeraldPickaxeItem extends BasePickaxeItem implements IEnableable {
    public EmeraldPickaxeItem(Function<Properties, Properties> properties) {
        super(ModItemTier.EMERALD, properties);
    }

    @Override
    public boolean isEnabled() {
        return !ModConfigs.isLoaded() || ModConfigs.ENABLE_EMERALD_GEAR.get();
    }
}
