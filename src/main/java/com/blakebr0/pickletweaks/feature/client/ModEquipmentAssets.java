package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.pickletweaks.PickleTweaks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public final class ModEquipmentAssets {
    public static final ResourceKey<EquipmentAsset> FLINT = ResourceKey.create(EquipmentAssets.ROOT_ID, PickleTweaks.resource("flint"));
    public static final ResourceKey<EquipmentAsset> EMERALD = ResourceKey.create(EquipmentAssets.ROOT_ID, PickleTweaks.resource("emerald"));
    public static final ResourceKey<EquipmentAsset> NIGHT_VISION_GOGGLES = ResourceKey.create(EquipmentAssets.ROOT_ID, PickleTweaks.resource("night_vision_goggles"));
    public static final ResourceKey<EquipmentAsset> REINFORCED_NIGHT_VISION_GOGGLES = ResourceKey.create(EquipmentAssets.ROOT_ID, PickleTweaks.resource("reinforced_night_vision_goggles"));
}
