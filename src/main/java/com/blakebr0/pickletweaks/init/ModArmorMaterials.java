package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public final class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> REGISTRY = DeferredRegister.create(Registries.ARMOR_MATERIAL, PickleTweaks.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> FLINT = REGISTRY.register("flint", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 4);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            12, SoundEvents.ARMOR_EQUIP_CHAIN,
            () -> Ingredient.of(Items.FLINT),
            List.of(
                    new ArmorMaterial.Layer(PickleTweaks.resource("flint"), "", false)
            ),
            0.0F, 0.0F
    ));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> EMERALD = REGISTRY.register("emerald", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            12, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Tags.Items.GEMS_EMERALD),
            List.of(
                    new ArmorMaterial.Layer(PickleTweaks.resource("emerald"), "", false)
            ),
            2.0F, 0.0F
    ));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> NIGHT_VISION_GOGGLES = REGISTRY.register("night_vision_goggles", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            9, SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(Tags.Items.INGOTS_IRON),
            List.of(
                    new ArmorMaterial.Layer(PickleTweaks.resource("night_vision_goggles"), "", false)
            ),
            0.0F, 0.0F
    ));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> REINFORCED_NIGHT_VISION_GOGGLES = REGISTRY.register("reinforced_night_vision_goggles", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
            }),
            9, SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(Tags.Items.GEMS_DIAMOND),
            List.of(
                    new ArmorMaterial.Layer(PickleTweaks.resource("reinforced_night_vision_goggles"), "", false)
            ),
            0.5F, 0.0F
    ));
}
