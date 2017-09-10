package com.blakebr0.pickletweaks.feature.item;

import java.util.HashSet;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPaxel extends ItemTool implements IRepairMaterial, IEnableable {
	
	private ItemStack repairMaterial;

	public ItemPaxel(String name, ToolMaterial material) {
		super(4, -3.2F, material, new HashSet<>());
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
		this.setHarvestLevel("pickaxe", material.getHarvestLevel());
		this.setHarvestLevel("shovel", material.getHarvestLevel());
		this.setHarvestLevel("axe", material.getHarvestLevel());
		this.setMaxDamage((int) (material.getMaxUses() * 1.5));
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
        	   && material != Material.WOOD && material != Material.PLANTS && material != Material.VINE 
        	   ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return OreDictionary.itemMatches(getRepairMaterial(), repair, false);
    }

	@Override
	public void setRepairMaterial(ItemStack stack) {
		repairMaterial = stack;
	}

	@Override
	public ItemStack getRepairMaterial() {
		return repairMaterial;
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confPaxels;
	}
}
