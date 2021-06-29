package com.blakebr0.pickletweaks.lib;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
    NIGHT_VISION_GOGGLES("pickletweaks:night_vision_goggles", 15, new int[] { 2, 5, 6, 2 }, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.of(Tags.Items.INGOTS_IRON);
    }),
    REINFORCED_NIGHT_VISION_GOGGLES("pickletweaks:reinforced_night_vision_goggles", 30, new int[] { 4, 5, 6, 2 }, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> {
        return Ingredient.of(Tags.Items.GEMS_DIAMOND);
    }),
    FLINT("pickletweaks:flint", 15, new int[] { 1, 4, 5, 2 }, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.FLINT);
    }),
    EMERALD("pickletweaks:emerald", 27, new int[] { 4, 6, 9, 4 }, 12, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.of(Tags.Items.GEMS_EMERALD);
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
