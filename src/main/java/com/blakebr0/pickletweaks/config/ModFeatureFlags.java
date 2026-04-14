package com.blakebr0.pickletweaks.config;

import com.blakebr0.cucumber.util.FeatureFlag;
import com.blakebr0.cucumber.util.FeatureFlags;
import com.blakebr0.pickletweaks.PickleTweaks;

@FeatureFlags
public final class ModFeatureFlags {
    public static final FeatureFlag APPLES = FeatureFlag.create(PickleTweaks.resource("apples"), ModConfigs.ENABLE_APPLES);
    public static final FeatureFlag BOW_AMMO_TOOLTIP = FeatureFlag.create(PickleTweaks.resource("bow_ammo_tooltip"), ModConfigs.ENABLE_BOW_AMMO_TOOLTIP);
    public static final FeatureFlag COAL_PIECES = FeatureFlag.create(PickleTweaks.resource("coal_pieces"), ModConfigs.ENABLE_COAL_PIECES);
    public static final FeatureFlag COLORED_COBBLESTONE = FeatureFlag.create(PickleTweaks.resource("colored_cobblestone"), ModConfigs.ENABLE_COLORED_COBBLESTONE);
    public static final FeatureFlag EMERALD_GEAR = FeatureFlag.create(PickleTweaks.resource("emerald_gear"), ModConfigs.ENABLE_EMERALD_GEAR);
    public static final FeatureFlag FLINT_GEAR = FeatureFlag.create(PickleTweaks.resource("flint_gear"), ModConfigs.ENABLE_FLINT_GEAR);
    public static final FeatureFlag GRID_REPAIR = FeatureFlag.create(PickleTweaks.resource("grid_repair"), ModConfigs.GRID_REPAIR_ENABLED);
    public static final FeatureFlag MAGNET = FeatureFlag.create(PickleTweaks.resource("magnet"), ModConfigs.ENABLE_MAGNET);
    public static final FeatureFlag MESHES = FeatureFlag.create(PickleTweaks.resource("meshes"), ModConfigs.ENABLE_MESHES);
    public static final FeatureFlag NIGHT_VISION_GOGGLES = FeatureFlag.create(PickleTweaks.resource("night_vision_goggles"), ModConfigs.ENABLE_NIGHT_VISION_GOGGLES);
    public static final FeatureFlag PAXELS = FeatureFlag.create(PickleTweaks.resource("paxels"), ModConfigs.ENABLE_PAXELS);
    public static final FeatureFlag RIGHT_CLICK_HARVEST = FeatureFlag.create(PickleTweaks.resource("right_click_harvest"), ModConfigs.ENABLE_RIGHT_CLICK_HARVEST);
    public static final FeatureFlag SCYTHES = FeatureFlag.create(PickleTweaks.resource("scythes"), ModConfigs.ENABLE_SCYTHES);
    public static final FeatureFlag SICKLES = FeatureFlag.create(PickleTweaks.resource("sickles"), ModConfigs.ENABLE_SICKLES);
    public static final FeatureFlag SMOOTH_GLOWSTONE = FeatureFlag.create(PickleTweaks.resource("smooth_glowstone"), ModConfigs.ENABLE_WATERING_CAN);
    public static final FeatureFlag TOOL_BREAKING = FeatureFlag.create(PickleTweaks.resource("tool_breaking"), ModConfigs.ENABLE_TOOL_BREAKING_TWEAK);
    public static final FeatureFlag WATERING_CANS = FeatureFlag.create(PickleTweaks.resource("watering_cans"), ModConfigs.ENABLE_WATERING_CAN);
}
