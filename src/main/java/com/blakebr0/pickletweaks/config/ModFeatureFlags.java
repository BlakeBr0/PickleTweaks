package com.blakebr0.pickletweaks.config;

import com.blakebr0.cucumber.util.FeatureFlag;
import com.blakebr0.cucumber.util.FeatureFlags;
import com.blakebr0.pickletweaks.PickleTweaks;
import net.minecraft.resources.ResourceLocation;

@FeatureFlags
public final class ModFeatureFlags {
    public static final FeatureFlag APPLES = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "apples"), ModConfigs.ENABLE_APPLES);
    public static final FeatureFlag COAL_PIECES = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "coal_pieces"), ModConfigs.ENABLE_COAL_PIECES);
    public static final FeatureFlag COLORED_COBBLESTONE = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "colored_cobblestone"), ModConfigs.ENABLE_COLORED_COBBLESTONE);
    public static final FeatureFlag EMERALD_GEAR = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "emerald_gear"), ModConfigs.ENABLE_EMERALD_GEAR);
    public static final FeatureFlag FLINT_GEAR = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "flint_gear"), ModConfigs.ENABLE_FLINT_GEAR);
    public static final FeatureFlag GRID_REPAIR = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "grid_repair"), ModConfigs.GRID_REPAIR_ENABLED);
    public static final FeatureFlag MAGNET = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "magnet"), ModConfigs.ENABLE_MAGNET);
    public static final FeatureFlag MESHES = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "meshes"), ModConfigs.ENABLE_MESHES);
    public static final FeatureFlag NIGHT_VISION_GOGGLES = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "night_vision_goggles"), ModConfigs.ENABLE_NIGHT_VISION_GOGGLES);
    public static final FeatureFlag PAXELS = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "paxels"), ModConfigs.ENABLE_PAXELS);
    public static final FeatureFlag RIGHT_CLICK_HARVEST = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "right_click_harvest"), ModConfigs.ENABLE_RIGHT_CLICK_HARVEST);
    public static final FeatureFlag SCYTHES = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "scythes"), ModConfigs.ENABLE_SCYTHES);
    public static final FeatureFlag SICKLES = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "sickles"), ModConfigs.ENABLE_SICKLES);
    public static final FeatureFlag SMOOTH_GLOWSTONE = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "smooth_glowstone"), ModConfigs.ENABLE_WATERING_CAN);
    public static final FeatureFlag TOOL_BREAKING = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "tool_breaking"), ModConfigs.ENABLE_TOOL_BREAKING_TWEAK);
    public static final FeatureFlag TOOL_INFO_TOOLTIP = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "tool_info_tooltip"), ModConfigs.ENABLE_TOOL_INFO_TOOLTIP);
    public static final FeatureFlag WATERING_CAN = FeatureFlag.create(new ResourceLocation(PickleTweaks.MOD_ID, "watering_can"), ModConfigs.ENABLE_WATERING_CAN);
}
