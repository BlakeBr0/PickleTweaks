package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.feature.item.PaxelItem;
import com.blakebr0.pickletweaks.lib.ModItemTier;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class EmeraldPaxelItem extends PaxelItem implements IEnableable {
    public EmeraldPaxelItem(Function<Properties, Properties> properties) {
        super(ModItemTier.EMERALD, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_PAXELS.get() && ModConfigs.ENABLE_EMERALD_GEAR.get();
    }
}
