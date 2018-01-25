package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.model.ModelLoader;

public class ItemDyePowder extends ItemMeta implements IEnableable {

	public ItemDyePowder() {
		super("pt.dye_powder", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.tab);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if (target instanceof EntitySheep) {
			EntitySheep sheep = (EntitySheep) target;
            EnumDyeColor color = EnumDyeColor.byMetadata(stack.getMetadata());
            	
            if (!sheep.getSheared() && sheep.getFleeceColor() != color) {
            	sheep.setFleeceColor(color);
            	stack.shrink(1);
            }

            return true;
		} else {
			return false;
		}
	}

	@Override
	public void init() {
        String[] dyes = {
            "Black",
            "Red",
            "Green",
            "Brown",
            "Blue",
            "Purple",
            "Cyan",
            "LightGray",
            "Gray",
            "Pink",
            "Lime",
            "Yellow",
            "LightBlue",
            "Magenta",
            "Orange",
            "White"
        };
        
		for (EnumDyeColor color : EnumDyeColor.values()) {
			String name = color.getUnlocalizedName();
			addItem(color.getMetadata(), name, "dye" + dyes[color.getDyeDamage()]);
		}
	}
	
	@Override
	public void initModels() {
		for (int i = 0; i < 16; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation("pickletweaks:dye_powder", "inventory"));
		}
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confDyePowder;
	}
}
