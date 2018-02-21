package com.blakebr0.pickletweaks.feature.item;

import java.util.HashSet;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPaxel extends ItemTool implements IRepairMaterial, IEnableable {

	private ItemStack repairMaterial;
	private String oreRepairMaterial = null;

	public ItemPaxel(String name, ToolMaterial material) {
		super(4, -3.2F, material, new HashSet<>());
		this.setUnlocalizedName("pt." + name);
		this.setCreativeTab(PickleTweaks.tab);
		this.setHarvestLevel("pickaxe", material.getHarvestLevel());
		this.setHarvestLevel("shovel", material.getHarvestLevel());
		this.setHarvestLevel("axe", material.getHarvestLevel());
		this.setMaxDamage((int) (material.getMaxUses() * 1.5));
	}

	public ItemPaxel(String name, ToolMaterial material, String repairMaterial) {
		this(name, material);
		this.oreRepairMaterial = repairMaterial;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
				&& material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
						? super.getStrVsBlock(stack, state)
						: this.efficiencyOnProperMaterial;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state) {
		Block block = state.getBlock();

		if (block == Blocks.OBSIDIAN) {
			return this.toolMaterial.getHarvestLevel() == 3;
		} else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE) {
			if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK) {
				if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE) {
					if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE) {
						if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE) {
							if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE) {
								Material material = state.getMaterial();

								if (material == Material.ROCK) {
									return true;
								} else if (material == Material.IRON) {
									return true;
								} else {
									return material == Material.ANVIL;
								}
							} else {
								return this.toolMaterial.getHarvestLevel() >= 2;
							}
						} else {
							return this.toolMaterial.getHarvestLevel() >= 1;
						}
					} else {
						return this.toolMaterial.getHarvestLevel() >= 1;
					}
				} else {
					return this.toolMaterial.getHarvestLevel() >= 2;
				}
			} else {
				return this.toolMaterial.getHarvestLevel() >= 2;
			}
		} else {
			return this.toolMaterial.getHarvestLevel() >= 2;
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return oreRepairMaterial != null
				? OreDictionary.getOres(oreRepairMaterial).stream().anyMatch(stack -> stack.isItemEqual(repair))
				: OreDictionary.itemMatches(getRepairMaterial(), repair, false);
	}

	@Override
	public void setRepairMaterial(ItemStack stack) {
		this.repairMaterial = stack;
	}

	@Override
	public ItemStack getRepairMaterial() {
		return this.repairMaterial;
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confPaxels;
	}
}
