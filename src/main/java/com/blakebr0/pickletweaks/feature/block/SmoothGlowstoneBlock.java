package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BaseBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SmoothGlowstoneBlock extends BaseBlock {
    public SmoothGlowstoneBlock() {
        super(Material.VEGETABLE, p -> p
                .strength(0.3F)
                .sound(SoundType.GLASS)
                .lightLevel(s -> 15)
        );
    }
}
