package com.blakebr0.pickletweaks.lib;

import com.blakebr0.pickletweaks.PickleTweaks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModTags {
    public static final TagKey<Block> INCORRECT_FOR_FLINT_TOOL = BlockTags.create(PickleTweaks.resource("incorrect_for_flint_tool"));
    public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = BlockTags.create(PickleTweaks.resource("incorrect_for_emerald_tool"));

    public static final TagKey<Item> FLINT_TOOL_MATERIALS = ItemTags.create(PickleTweaks.resource("flint_tool_materials"));
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS = ItemTags.create(PickleTweaks.resource("emerald_tool_materials"));
    public static final TagKey<Item> REPAIRS_FLINT_ARMOR = ItemTags.create(PickleTweaks.resource("repairs_flint_armor"));
    public static final TagKey<Item> REPAIRS_EMERALD_ARMOR = ItemTags.create(PickleTweaks.resource("repairs_emerald_armor"));
    public static final TagKey<Item> REPAIRS_NIGHT_VISION_GOGGLES = ItemTags.create(PickleTweaks.resource("repairs_night_vision_goggles"));
    public static final TagKey<Item> REPAIRS_REINFORCED_NIGHT_VISION_GOGGLES = ItemTags.create(PickleTweaks.resource("repairs_reinforced_night_vision_goggles"));
}
