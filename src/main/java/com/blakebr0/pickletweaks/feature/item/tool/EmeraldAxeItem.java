package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BaseAxeItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.world.item.Item.Properties;

public class EmeraldAxeItem extends BaseAxeItem implements IEnableable {
    public EmeraldAxeItem(Function<Properties, Properties> properties) {
        super(ModItemTier.EMERALD, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_EMERALD_GEAR.get();
    }
}
