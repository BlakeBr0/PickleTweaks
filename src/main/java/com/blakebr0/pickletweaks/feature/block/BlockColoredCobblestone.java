package com.blakebr0.pickletweaks.feature.block;

import com.blakebr0.cucumber.block.BlockBase;
import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.iface.IModelHelper;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.registry.ModBlocks;

import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockColoredCobblestone extends BlockColored implements IEnableable, IModelHelper {

	public BlockColoredCobblestone() {
		super(Material.ROCK);
		this.setUnlocalizedName("pt.colored_cobblestone");
		this.setCreativeTab(PickleTweaks.CREATIVE_TAB);
		this.setSoundType(SoundType.STONE);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void initModels() {
		for (EnumDyeColor color : COLOR.getAllowedValues()) { 
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), color.getMetadata(), ResourceHelper.getModelResource(PickleTweaks.MOD_ID, "colored_cobblestone", "inventory"));
		}
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confColoredCobblestone;
	}

	public static void addToChisel() {
		COLOR.getAllowedValues().forEach(color -> {			
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("group", "cobblestone");
			tag.setTag("stack", new ItemStack(ModBlocks.COLORED_COBBLESTONE, 1, color.getMetadata()).serializeNBT());
			tag.setString("block", ModBlocks.COLORED_COBBLESTONE.getRegistryName().toString());
			tag.setInteger("meta", color.getMetadata());
			FMLInterModComms.sendMessage("chisel", "add_variation", tag);
		});
	}
}
