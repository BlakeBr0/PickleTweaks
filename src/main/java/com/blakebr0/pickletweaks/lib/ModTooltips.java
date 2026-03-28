package com.blakebr0.pickletweaks.lib;

import com.blakebr0.cucumber.util.Tooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;

public final class ModTooltips {
    public static final Tooltip GIVES_BUFFS = new Tooltip("tooltip.pickletweaks.gives_buffs");
    public static final Tooltip DISABLED = new Tooltip("tooltip.pickletweaks.disabled");
    public static final Tooltip ENABLED = new Tooltip("tooltip.pickletweaks.enabled");
    public static final Tooltip MINING_SPEED = new Tooltip("tooltip.pickletweaks.mining_speed");
    public static final Tooltip AMMO = new Tooltip("tooltip.pickletweaks.ammo");
    public static final Tooltip BROKEN = new Tooltip("tooltip.pickletweaks.broken");
    public static final Tooltip USELESS_TOOL_1 = new Tooltip("tooltip.pickletweaks.useless_tool_1");
    public static final Tooltip USELESS_TOOL_2 = new Tooltip("tooltip.pickletweaks.useless_tool_2");
    public static final Tooltip USELESS_WEAPON_1 = new Tooltip("tooltip.pickletweaks.useless_weapon_1");
    public static final Tooltip USELESS_HOE_1 = new Tooltip("tooltip.pickletweaks.useless_hoe_1");
    public static final Tooltip USELESS_BOW_1 = new Tooltip("tooltip.pickletweaks.useless_bow_1");
    public static final Tooltip YOUR_ITEM_IS_BROKEN = new Tooltip("tooltip.pickletweaks.your_item_is_broken");

    public static Component createMobEffectLine(Holder<MobEffect> effect, String level, String duration) {
        return Component.literal(" - %s %s (%s)".formatted(effect.value().getDisplayName().getString(), level, duration)).withStyle(ChatFormatting.GRAY);
    }
}
