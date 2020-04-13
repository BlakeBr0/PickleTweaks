package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SmoothGlowstoneBlock extends BaseBlock {
    public SmoothGlowstoneBlock() {
        super(Material.GOURD, p -> p
                .hardnessAndResistance(0.3F)
                .sound(SoundType.GLASS)
                .lightValue(15)
        );
    }
}
