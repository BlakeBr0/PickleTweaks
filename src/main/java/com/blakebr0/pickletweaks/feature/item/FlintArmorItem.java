package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.function.Function;

public class FlintArmorItem extends BaseArmorItem implements IEnableable {
    public FlintArmorItem(EquipmentSlotType slot, Function<Properties, Properties> properties) {
        super(ModArmorMaterial.FLINT, slot, properties);
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_FLINT_GEAR.get();
    }
}
