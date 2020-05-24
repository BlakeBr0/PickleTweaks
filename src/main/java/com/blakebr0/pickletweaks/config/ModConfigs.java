package com.blakebr0.pickletweaks.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ModConfigs {
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    public static final ForgeConfigSpec.BooleanValue ENABLE_TOOL_INFO_TOOLTIP;
    public static final ForgeConfigSpec.BooleanValue ENABLE_WAILA_HARVEST_LEVEL;

    // Client
    static {
        final ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();

        client.comment("General configuration options").push("General");
        client.pop();

        client.comment("Disable features.").push("Features");
        ENABLE_TOOL_INFO_TOOLTIP = client
                .comment("Tool Information Tooltips enabled?")
                .translation("configGui.pickletweaks.enable_tool_info_tooltip")
                .define("toolInfoTooltip", true);
        ENABLE_WAILA_HARVEST_LEVEL = client
                .comment("Waila harvest level tooltip enabled?")
                .translation("configGui.pickletweaks.enable_waila_harvest_level")
                .define("wailaHarvestLevel", true);
        client.pop();

        CLIENT = client.build();
    }

    public static final ForgeConfigSpec.DoubleValue MAGNET_RANGE;

    public static final ForgeConfigSpec.BooleanValue ENABLE_COLORED_COBBLESTONE;
    public static final ForgeConfigSpec.BooleanValue ENABLE_DARK_GLASS;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SMOOTH_GLOWSTONE;

    public static final ForgeConfigSpec.BooleanValue ENABLE_COAL_PIECES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_APPLES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_WATERING_CAN;
    public static final ForgeConfigSpec.BooleanValue ENABLE_MESHES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_MAGNET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_NIGHT_VISION_GOGGLES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_PAXELS;
    public static final ForgeConfigSpec.BooleanValue ENABLE_FLINT_GEAR;
    public static final ForgeConfigSpec.BooleanValue ENABLE_EMERALD_GEAR;
    public static final ForgeConfigSpec.BooleanValue ENABLE_RIGHT_CLICK_HARVEST;

    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_ENABLED;
    public static final ForgeConfigSpec.IntValue GRID_REPAIR_COST;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_DISABLE_DEFAULTS;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_OVERRIDE_MODE;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_CHEAP_SHOVEL;
    public static final ForgeConfigSpec.ConfigValue<List<String>> GRID_REPAIR_BLACKLIST;
    public static final ForgeConfigSpec.ConfigValue<List<String>> GRID_REPAIR_OVERRIDES;

    public static final ForgeConfigSpec.BooleanValue ENABLE_TOOL_BREAKING_TWEAK;
    public static final ForgeConfigSpec.ConfigValue<List<String>> USELESS_TOOLS;

    // Common
    static {
        final ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        common.comment("General configuration options.").push("General");
        MAGNET_RANGE = common
                .comment("The effective radius of the Magnet.")
                .translation("configGui.pickletweaks.magnet_range")
                .defineInRange("magnetRange", 7.0, 1.0, 16.0);
        common.pop();

        common.comment("Disable features.").push("Features");
        ENABLE_COLORED_COBBLESTONE = common
                .comment("Colored Cobblestone enabled?")
                .translation("configGui.pickletweaks.enable_colored_cobblestone")
                .define("coloredCobblestone", true);
        ENABLE_DARK_GLASS = common
                .comment("Dark Glass enabled?")
                .translation("configGui.pickletweaks.enable_dark_glass")
                .define("darkGlass", true);
        ENABLE_SMOOTH_GLOWSTONE = common
                .comment("Smooth Glowstone enabled?")
                .translation("configGui.pickletweaks.enable_smooth_glowstone")
                .define("smoothGlowstone", true);

        ENABLE_COAL_PIECES = common
                .comment("Coal and Charcoal Piece enabled?")
                .translation("configGui.pickletweaks.enable_coal_piece")
                .define("coalPiece", true);
        ENABLE_APPLES = common
                .comment("Diamond and Emerald Apples enabled?")
                .translation("configGui.pickletweaks.enable_apples")
                .define("apples", true);
        ENABLE_WATERING_CAN = common
                .comment("Watering Can enabled?")
                .translation("configGui.pickletweaks.enable_watering_can")
                .define("wateringCan", true);
        ENABLE_MESHES = common
                .comment("Enable Meshes?")
                .translation("configGui.pickletweaks.enable_meshes")
                .define("meshes", true);
        ENABLE_MAGNET = common
                .comment("Enable Magnet?")
                .translation("configGui.pickletweaks.enable_magnet")
                .define("magnet", true);
        ENABLE_NIGHT_VISION_GOGGLES = common
                .comment("Enable Night Vision Goggles?")
                .translation("configGui.pickletweaks.enable_night_vision_goggles")
                .define("nightVisionGoggles", true);
        ENABLE_PAXELS = common
                .comment("Enable Paxels?")
                .translation("configGui.pickletweaks.enable_paxels")
                .define("paxels", true);
        ENABLE_FLINT_GEAR = common
                .comment("Enable Flint tools and armor?")
                .translation("configGui.pickletweaks.enable_flint_gear")
                .define("flintGear", true);
        ENABLE_EMERALD_GEAR = common
                .comment("Enable Emerald tools and armor?")
                .translation("configGui.pickletweaks.enable_emerald_gear")
                .define("emeraldGear", true);
        ENABLE_RIGHT_CLICK_HARVEST = common
                .comment("Enable right click crop harvesting?")
                .translation("configGui.pickletweaks.enable_right_click_harvest")
                .define("rightClickHarvest", true);
        common.pop();

        common.comment("Crafting grid tool repair configuration options.").push("Grid Repair");
        GRID_REPAIR_ENABLED = common
                .comment("Should Crafting Grid Tool Repairing be enabled?")
                .translation("configGui.pickletweaks.grid_repair_enabled")
                .define("enabled", true);
        GRID_REPAIR_COST = common
                .comment("How much material should be required to fully repair a tool.")
                .translation("configGui.pickletweaks.grid_repair_cost")
                .defineInRange("cost", 4, 1, 8);
        GRID_REPAIR_DISABLE_DEFAULTS = common
                .comment("Should default repair materials be disabled? Doing this makes it so ONLY the custom materials work.")
                .translation("configGui.pickletweaks.grid_repair_disable_defaults")
                .define("disableDefaults", false);
        GRID_REPAIR_OVERRIDE_MODE = common
                .comment("Enabling this makes it so custom repair materials override the default ones for the tools specified.")
                .translation("configGui.pickletweaks.grid_repair_override_mode")
                .define("overrideMode", false);
        GRID_REPAIR_CHEAP_SHOVEL = common
                .comment("Makes it so shovels need 50% less material to repair.")
                .translation("configGui.pickletweaks.grid_repair_cheap_shovel")
                .define("cheapShovel", true);
        GRID_REPAIR_BLACKLIST = common
                .comment("Tools that should not be repairable in the crafting grid." +
                        "\nEx: [\"minecraft:stone_pickaxe\", \"minecraft:stone_sword\"]")
                .translation("configGui.pickletweaks.grid_repair_blacklist")
                .define("blacklist", new ArrayList<>());
        GRID_REPAIR_OVERRIDES = common
                .comment("Here you can specify custom repair materials for tools." +
                        "\nEx: [\"minecraft:stone_shovel=minecraft:stick\"]" +
                        "\nYou can use tags for materials by doing tag:<tag-id>." +
                        "\nEx: [\"minecraft:stone_shovel=tag:forge:ingots/iron\"]" +
                        "\nYou can specify how effective the material is by appending @<multiplier>" +
                        "\nEx: [\"minecraft:stone_shovel=minecraft:stick@0.5\"]")
                .translation("configGui.pickletweaks.grid_repair_overrides")
                .define("overrides", new ArrayList<>());
        common.pop();

        common.comment("Disable and configure tweaks.").push("Tweaks");
        ENABLE_TOOL_BREAKING_TWEAK = common
                .comment("Enabled tools not breaking?")
                .translation("configGui.pickletweaks.enable_tool_breaking_tweak")
                .define("toolBreaking", true);
        USELESS_TOOLS = common
                .comment("Tools and weapons that should be ineffective." +
                        "\nEx: [\"minecraft:stone_pickaxe\", \"minecraft:stone_sword\"]")
                .translation("configGui.pickletweaks.useless_tools")
                .define("uselessTools", new ArrayList<>());
        common.pop();

        COMMON = common.build();
    }
}
