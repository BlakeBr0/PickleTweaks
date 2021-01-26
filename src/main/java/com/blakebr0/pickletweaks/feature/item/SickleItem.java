package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BaseSickleItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class SickleItem extends BaseSickleItem implements IEnableable {
    public SickleItem(IItemTier tier, int range, Function<Properties, Properties> properties) {
        super(tier, 4.0F, -3.0F, range, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SICKLES.get();
    }
}
