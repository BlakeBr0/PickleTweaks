package com.blakebr0.pickletweaks.lib;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;
import java.util.function.Supplier;

import static com.blakebr0.pickletweaks.PickleTweaks.MOD_ID;

public enum ModItemTier implements Tier {
    FLINT(1, 157, 4.5F, 1.75F, 7, () -> {
        return Ingredient.of(Items.FLINT);
    }),
    EMERALD(3, 1324, 9.0F, 4.0F, 12, () -> {
        return Ingredient.of(Tags.Items.GEMS_EMERALD);
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    public static void onCommonSetup() {
        TierSortingRegistry.registerTier(FLINT, new ResourceLocation(MOD_ID, "flint"), List.of(Tiers.STONE), List.of());
        TierSortingRegistry.registerTier(EMERALD, new ResourceLocation(MOD_ID, "emerald"), List.of(Tiers.DIAMOND), List.of());
    }
}
