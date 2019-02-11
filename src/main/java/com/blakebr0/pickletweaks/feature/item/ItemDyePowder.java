package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemDyePowder extends ItemMeta implements IEnableable {

	public ItemDyePowder() {
		super("pt.dye_powder", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.CREATIVE_TAB);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if (target instanceof EntitySheep) {
			EntitySheep sheep = (EntitySheep) target;
            EnumDyeColor color = EnumDyeColor.byMetadata(stack.getMetadata());
            	
            if (!sheep.getSheared() && sheep.getFleeceColor() != color) {
            	sheep.setFleeceColor(color);
            	
            	if (!player.capabilities.isCreativeMode) {
                	stack.shrink(1);
            	}
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
	
	public static class Handler {
		
		@SubscribeEvent
		public void onEntityInteract(EntityInteract event) {
			EntityPlayer player = event.getEntityPlayer();
			Entity target = event.getTarget();
			ItemStack stack = player.getHeldItem(event.getHand());
			
			if (target instanceof EntityWolf) {
				EntityWolf wolf = (EntityWolf) target;

				if (wolf.isTamed() && !stack.isEmpty() && stack.getItem() instanceof ItemDyePowder) {
					EnumDyeColor color = EnumDyeColor.byMetadata(stack.getMetadata());
			
					if (wolf.getCollarColor() != color) {
						wolf.setCollarColor(color);
						
						if (!player.capabilities.isCreativeMode) {
							stack.shrink(1);
						}
						
						event.setCancellationResult(EnumActionResult.SUCCESS);
						event.setCanceled(true);
					}
				}
			}
		}
	}
}
