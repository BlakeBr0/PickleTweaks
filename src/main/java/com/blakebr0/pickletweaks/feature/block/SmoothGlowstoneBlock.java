package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SmoothGlowstoneBlock extends BaseBlock implements IEnableable {
    public SmoothGlowstoneBlock() {
        super(Material.GOURD, p -> p
                .hardnessAndResistance(0.3F)
                .sound(SoundType.GLASS)
                .setLightLevel(s -> 15)
        );
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SMOOTH_GLOWSTONE.get();
    }
}
