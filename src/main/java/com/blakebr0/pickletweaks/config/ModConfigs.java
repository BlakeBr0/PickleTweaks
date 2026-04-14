package com.blakebr0.pickletweaks.config;

import com.google.common.collect.Lists;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public final class ModConfigs {
    public static final ModConfigSpec CLIENT;
    public static final ModConfigSpec COMMON;

    public static final ModConfigSpec.BooleanValue ENABLE_BOW_AMMO_TOOLTIP;

    // Client
    static {
        final var client = new ModConfigSpec.Builder();

        client.comment("Disable features.").push("Features");
        ENABLE_BOW_AMMO_TOOLTIP = client
                .comment("Bow Ammo Tooltips enabled?")
                .define("bowAmmoTooltip", true);
        client.pop();

        CLIENT = client.build();
    }

    public static final ModConfigSpec.DoubleValue MAGNET_RANGE;
    public static final ModConfigSpec.BooleanValue FAKE_PLAYER_WATERING;

    public static final ModConfigSpec.BooleanValue ENABLE_COLORED_COBBLESTONE;
    public static final ModConfigSpec.BooleanValue ENABLE_SMOOTH_GLOWSTONE;
    public static final ModConfigSpec.BooleanValue ENABLE_COAL_PIECES;
    public static final ModConfigSpec.BooleanValue ENABLE_APPLES;
    public static final ModConfigSpec.BooleanValue ENABLE_WATERING_CAN;
    public static final ModConfigSpec.BooleanValue ENABLE_MESHES;
    public static final ModConfigSpec.BooleanValue ENABLE_MAGNET;
    public static final ModConfigSpec.BooleanValue ENABLE_NIGHT_VISION_GOGGLES;
    public static final ModConfigSpec.BooleanValue ENABLE_PAXELS;
    public static final ModConfigSpec.BooleanValue ENABLE_FLINT_GEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_EMERALD_GEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_RIGHT_CLICK_HARVEST;
    public static final ModConfigSpec.BooleanValue ENABLE_SICKLES;
    public static final ModConfigSpec.BooleanValue ENABLE_SCYTHES;

    public static final ModConfigSpec.BooleanValue GRID_REPAIR_ENABLED;
    public static final ModConfigSpec.IntValue GRID_REPAIR_COST;
    public static final ModConfigSpec.IntValue GRID_REPAIR_ENCHANTMENT_COST;
    public static final ModConfigSpec.BooleanValue GRID_REPAIR_STRIP_ENCHANTMENTS;
    public static final ModConfigSpec.BooleanValue GRID_REPAIR_DISABLE_DEFAULTS;
    public static final ModConfigSpec.BooleanValue GRID_REPAIR_OVERRIDE_MODE;
    public static final ModConfigSpec.BooleanValue GRID_REPAIR_CHEAP_SHOVEL;
    public static final ModConfigSpec.BooleanValue GRID_REPAIR_CHEAP_SHEARS;
    public static final ModConfigSpec.ConfigValue<List<String>> GRID_REPAIR_BLACKLIST;
    public static final ModConfigSpec.ConfigValue<List<String>> GRID_REPAIR_OVERRIDES;

    public static final ModConfigSpec.BooleanValue ENABLE_TOOL_BREAKING_TWEAK;
    public static final ModConfigSpec.ConfigValue<List<String>> USELESS_TOOLS;

    // Common
    static {
        final var common = new ModConfigSpec.Builder();

        common.comment("General configuration options.").push("General");
        MAGNET_RANGE = common
                .comment("The effective radius of the Magnet.")
                .defineInRange("magnetRange", 7.0, 1.0, 16.0);
        FAKE_PLAYER_WATERING = common
                .comment("Should fake players be able to use watering cans?")
                .define("fakePlayerWatering", true);
        common.pop();

        common.comment("Disable specific features.").push("Features");
        ENABLE_COLORED_COBBLESTONE = common
                .comment("Enable Colored Cobblestone?")
                .define("coloredCobblestone", true);
        ENABLE_SMOOTH_GLOWSTONE = common
                .comment("Enable Smooth Glowstone?")
                .define("smoothGlowstone", true);
        ENABLE_COAL_PIECES = common
                .comment("Enable Coal and Charcoal Piece?")
                .define("coalPiece", true);
        ENABLE_APPLES = common
                .comment("Enable Diamond and Emerald Apples?")
                .define("apples", true);
        ENABLE_WATERING_CAN = common
                .comment("Enable Watering Can?")
                .define("wateringCan", true);
        ENABLE_MESHES = common
                .comment("Enable Meshes?")
                .define("meshes", true);
        ENABLE_MAGNET = common
                .comment("Enable Magnet?")
                .define("magnet", true);
        ENABLE_NIGHT_VISION_GOGGLES = common
                .comment("Enable Night Vision Goggles?")
                .define("nightVisionGoggles", true);
        ENABLE_PAXELS = common
                .comment("Enable Paxels?")
                .define("paxels", true);
        ENABLE_FLINT_GEAR = common
                .comment("Enable Flint tools and armor?")
                .define("flintGear", true);
        ENABLE_EMERALD_GEAR = common
                .comment("Enable Emerald tools and armor?")
                .define("emeraldGear", true);
        ENABLE_RIGHT_CLICK_HARVEST = common
                .comment("Enable right click crop harvesting?")
                .define("rightClickHarvest", true);
        ENABLE_SICKLES = common
                .comment("""
                        Enable Sickles?
                        Disabled automatically if Mystical Agriculture is installed.""")
                .define("sickles", true);
        ENABLE_SCYTHES = common
                .comment("""
                        Enable Scythes?
                        Disabled automatically if Mystical Agriculture is installed.""")
                .define("scythes", true);
        common.pop();

        common.comment("Crafting grid tool repair configuration options.").push("Grid Repair");
        GRID_REPAIR_ENABLED = common
                .comment("Should Crafting Grid Tool Repairing be enabled?")
                .define("enabled", true);
        GRID_REPAIR_COST = common
                .comment("How many materials are required to fully repair a tool.")
                .defineInRange("cost", 3, 1, 8);
        GRID_REPAIR_ENCHANTMENT_COST = common
                .comment("How much additional material is required to repair enchanted tools.")
                .defineInRange("enchantmentCost", 1, 0, 8);
        GRID_REPAIR_STRIP_ENCHANTMENTS = common
                .comment("Crafting grid repair removes all non-curse enchantments.")
                .define("stripEnchantments", false);
        GRID_REPAIR_DISABLE_DEFAULTS = common
                .comment("Should default repair materials be disabled? Doing this makes it so ONLY the custom materials work.")
                .define("disableDefaults", false);
        GRID_REPAIR_OVERRIDE_MODE = common
                .comment("Enabling this makes it so custom repair materials override the default ones for the tools specified.")
                .define("overrideMode", false);
        GRID_REPAIR_CHEAP_SHOVEL = common
                .comment("Makes it so shovels need 50% less material to repair.")
                .define("cheapShovel", true);
        GRID_REPAIR_CHEAP_SHEARS = common
                .comment("Makes it so shears need 50% less material to repair.")
                .define("cheapShears", true);
        GRID_REPAIR_BLACKLIST = common
                .comment("""
                        Tools that should not be repairable in the crafting grid.
                        Ex: ["minecraft:stone_pickaxe", "minecraft:stone_sword"]
                        You can use tags for tools by doing tag:<tag-id>.
                        Ex: ["tag:minecraft:tools"]
                        You can also blacklist entire mods by doing <mod-id>:*
                        Ex. ["pickletweaks:*"]""")
                .define("blacklist", new ArrayList<>());
        GRID_REPAIR_OVERRIDES = common
                .comment("""
                        Here you can specify custom repair materials for tools.
                        Ex: ["minecraft:stone_shovel=minecraft:stick"]
                        You can use tags for materials by doing tag:<tag-id>.
                        Ex: ["minecraft:stone_shovel=tag:c:ingots/iron"]
                        You can also use tags for tools by doing tag:<tag-id>.
                        Ex: ["tag:minecraft:tools=tag:c:ingots/iron"]
                        You can specify how effective the material is by appending @<multiplier>
                        Ex: ["minecraft:stone_shovel=minecraft:stick@0.5"]""")
                .define("overrides", Lists.newArrayList("minecraft:shears=minecraft:iron_ingot", "pickletweaks:flint_shears=minecraft:flint"));
        common.pop();

        common.comment("Disable and configure tweaks.").push("Tweaks");
        ENABLE_TOOL_BREAKING_TWEAK = common
                .comment("Enabled tools not breaking?")
                .define("toolBreaking", true);
        USELESS_TOOLS = common
                .comment("""
                        Tools and weapons that should be ineffective.
                        Ex: ["minecraft:stone_pickaxe", "minecraft:stone_sword"]
                        You can use tags for tools by doing tag:<tag-id>.
                        Ex: ["tag:minecraft:tools"]
                        You can also whitelist entire mods by doing <mod-id>:*
                        Ex. ["pickletweaks:*"]""")
                .define("uselessTools", new ArrayList<>());
        common.pop();

        COMMON = common.build();
    }

    public static boolean isCuriosInstalled() {
        return ModList.get().isLoaded("curios");
    }

    public static boolean isMysticalAgricultureLoaded() {
        return ModList.get().isLoaded("mysticalagriculture");
    }
}
