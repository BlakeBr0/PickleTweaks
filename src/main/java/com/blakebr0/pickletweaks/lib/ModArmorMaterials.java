package com.blakebr0.pickletweaks.lib;

import com.blakebr0.pickletweaks.feature.client.ModEquipmentAssets;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public final class ModArmorMaterials {
    public static final ArmorMaterial FLINT = new ArmorMaterial(
            15,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS, 1);
                map.put(ArmorType.LEGGINGS, 5);
                map.put(ArmorType.CHESTPLATE, 4);
                map.put(ArmorType.HELMET, 2);
            }),
            12, SoundEvents.ARMOR_EQUIP_CHAIN,
            0.0F, 0.0F,
            ModTags.REPAIRS_FLINT_ARMOR,
            ModEquipmentAssets.FLINT
    );
    public static final ArmorMaterial EMERALD = new ArmorMaterial(
            27,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
            }),
            12, SoundEvents.ARMOR_EQUIP_LEATHER,
            2.0F, 0.0F,
            ModTags.REPAIRS_EMERALD_ARMOR,
            ModEquipmentAssets.EMERALD
    );
    public static final ArmorMaterial NIGHT_VISION_GOGGLES = new ArmorMaterial(
            15, Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.HELMET, 2);
            }),
            9, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 0.0F,
            ModTags.REPAIRS_NIGHT_VISION_GOGGLES,
            ModEquipmentAssets.NIGHT_VISION_GOGGLES
    );
    public static final ArmorMaterial REINFORCED_NIGHT_VISION_GOGGLES = new ArmorMaterial(
            30, Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.HELMET, 4);
            }),
            9, SoundEvents.ARMOR_EQUIP_IRON,
            0.5F, 0.0F,
            ModTags.REPAIRS_REINFORCED_NIGHT_VISION_GOGGLES,
            ModEquipmentAssets.REINFORCED_NIGHT_VISION_GOGGLES
    );
}
