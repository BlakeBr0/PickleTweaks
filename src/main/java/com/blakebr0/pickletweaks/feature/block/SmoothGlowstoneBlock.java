package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SmoothGlowstoneBlock extends BaseBlock implements IEnableable {
    public SmoothGlowstoneBlock() {
        super(Material.VEGETABLE, p -> p
                .strength(0.3F)
                .sound(SoundType.GLASS)
                .lightLevel(s -> 15)
        );
    }

    @Override
    public boolean isEnabled() {
        return ModConfigs.ENABLE_SMOOTH_GLOWSTONE.get();
    }
}
