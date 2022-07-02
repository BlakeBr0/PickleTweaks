package com.blakebr0.pickletweaks.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public final class ModConfigs {
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    public static final ForgeConfigSpec.ConfigValue<List<String>> HARVEST_LEVEL_NAMES;

    public static final ForgeConfigSpec.BooleanValue ENABLE_TOOL_INFO_TOOLTIP;

    // Client
    static {
        final var client = new ForgeConfigSpec.Builder();

        client.comment("General configuration options").push("General");
        HARVEST_LEVEL_NAMES = client
                .comment("The harvest level names used by the Tool Info Tweak tooltips.")
                .define("harvestLevelNames", Lists.newArrayList("Stone", "Iron", "Diamond", "Obsidian", "Netherite"));
        client.pop();

        client.comment("Disable features.").push("Features");
        ENABLE_TOOL_INFO_TOOLTIP = client
                .comment("Tool Information Tooltips enabled?")
                .define("toolInfoTooltip", true);
        client.pop();

        CLIENT = client.build();
    }

    public static final ForgeConfigSpec.DoubleValue MAGNET_RANGE;
    public static final ForgeConfigSpec.BooleanValue FAKE_PLAYER_WATERING;
    public static final ForgeConfigSpec.ConfigValue<List<String>> TOOL_INFO_TOOLTIP_BLACKLIST;

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
    public static final ForgeConfigSpec.BooleanValue ENABLE_SICKLES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SCYTHES;

    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_ENABLED;
    public static final ForgeConfigSpec.IntValue GRID_REPAIR_COST;
    public static final ForgeConfigSpec.IntValue GRID_REPAIR_ENCHANTMENT_COST;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_STRIP_ENCHANTMENTS;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_DISABLE_DEFAULTS;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_OVERRIDE_MODE;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_CHEAP_SHOVEL;
    public static final ForgeConfigSpec.BooleanValue GRID_REPAIR_CHEAP_SHEARS;
    public static final ForgeConfigSpec.ConfigValue<List<String>> GRID_REPAIR_BLACKLIST;
    public static final ForgeConfigSpec.ConfigValue<List<String>> GRID_REPAIR_OVERRIDES;

    public static final ForgeConfigSpec.BooleanValue ENABLE_TOOL_BREAKING_TWEAK;
    public static final ForgeConfigSpec.ConfigValue<List<String>> USELESS_TOOLS;

    // Common
    static {
        final var common = new ForgeConfigSpec.Builder();

        common.comment("General configuration options.").push("General");
        MAGNET_RANGE = common
                .comment("The effective radius of the Magnet.")
                .defineInRange("magnetRange", 7.0, 1.0, 16.0);
        FAKE_PLAYER_WATERING = common
                .comment("Should fake players be able to use watering cans?")
                .define("fakePlayerWatering", true);
        TOOL_INFO_TOOLTIP_BLACKLIST = common
                .comment("""
                        Here you can specify a list of tools that shouldn't have tool info tooltips.
                        Ex. ["minecraft:iron_pickaxe"]
                        You can also blacklist entire mods by doing <mod-id>:*
                        Ex. ["pickletweaks:*"]""")
                .define("toolInfoTooltipBlacklist", Lists.newArrayList("silentgear:*"));
        common.pop();

        common.comment("Disable features.").push("Features");
        ENABLE_COLORED_COBBLESTONE = common
                .comment("Colored Cobblestone enabled?")
                .define("coloredCobblestone", true);
        ENABLE_DARK_GLASS = common
                .comment("Dark Glass enabled?")
                .define("darkGlass", true);
        ENABLE_SMOOTH_GLOWSTONE = common
                .comment("Smooth Glowstone enabled?")
                .define("smoothGlowstone", true);
        ENABLE_COAL_PIECES = common
                .comment("Coal and Charcoal Piece enabled?")
                .define("coalPiece", true);
        ENABLE_APPLES = common
                .comment("Diamond and Emerald Apples enabled?")
                .define("apples", true);
        ENABLE_WATERING_CAN = common
                .comment("Watering Can enabled?")
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
                .comment("Enable Sickles?")
                .define("sickles", true);
        ENABLE_SCYTHES = common
                .comment("Enable Scythes?")
                .define("scythes", true);
        common.pop();

        common.comment("Crafting grid tool repair configuration options.").push("Grid Repair");
        GRID_REPAIR_ENABLED = common
                .comment("Should Crafting Grid Tool Repairing be enabled?")
                .define("enabled", true);
        GRID_REPAIR_COST = common
                .comment("How much material required to fully repair a tool.")
                .defineInRange("cost", 3, 1, 8);
        GRID_REPAIR_ENCHANTMENT_COST = common
                .comment("How much additional material required to repair enchanted tools.")
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
                .comment("Tools that should not be repairable in the crafting grid." +
                        "\nEx: [\"minecraft:stone_pickaxe\", \"minecraft:stone_sword\"]")
                .define("blacklist", new ArrayList<>());
        GRID_REPAIR_OVERRIDES = common
                .comment("""
                        Here you can specify custom repair materials for tools.
                        Ex: ["minecraft:stone_shovel=minecraft:stick"]
                        You can use tags for materials by doing tag:<tag-id>.
                        Ex: ["minecraft:stone_shovel=tag:forge:ingots/iron"]
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
                        Ex: ["minecraft:stone_pickaxe", "minecraft:stone_sword"]""")
                .define("uselessTools", new ArrayList<>());
        common.pop();

        COMMON = common.build();
    }

    public static boolean isCuriosInstalled() {
        return ModList.get().isLoaded("curios");
    }
}
