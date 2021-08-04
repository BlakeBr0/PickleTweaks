package com.blakebr0.pickletweaks.feature.item.tool;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.tool.BaseShearsItem;
import com.blakebr0.pickletweaks.config.ModConfigs;

import java.util.function.Function;

import net.minecraft.world.item.Item.Properties;

public class FlintShearsItem extends BaseShearsItem implements IEnableable {
    public FlintShearsItem(Function<Properties, Properties> properties) {
        super(properties.compose(p -> p.durability(100)));
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
