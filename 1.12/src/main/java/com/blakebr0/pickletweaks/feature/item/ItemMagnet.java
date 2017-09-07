package com.blakebr0.pickletweaks.feature.item;

import java.util.List;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ItemMagnet extends ItemBase {
	
	public ItemMagnet(){
		super("pt.magnet");
		this.setCreativeTab(PickleTweaks.tab);
		this.setMaxStackSize(1);
	}
	
	public ItemStack initTags(ItemStack stack){
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null){
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
			tag.setBoolean("Enabled", false);
		}
		
		return stack;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack){
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null){
			if(tag.hasKey("Enabled")){
				return tag.getBoolean("Enabled");
			}
		}
		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
		ItemStack stack = player.getHeldItem(hand);
		
		initTags(stack);
		stack.getTagCompound().setBoolean("Enabled", !stack.getTagCompound().getBoolean("Enabled"));

		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
	public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced){
		initTags(stack);
		if(stack.getTagCompound().getBoolean("Enabled")){
			tooltip.add(Utils.localize("tooltip.pt.enabled"));
		} else {
			tooltip.add(Utils.localize("tooltip.pt.disabled"));
		}
	}
	
	public static class Handler {
		
		public double getRange(){
			return (double)ModConfig.confMagnetRange;
		}
		
		@SubscribeEvent
		public void onPlayerTick(PlayerTickEvent event){
			EntityPlayer player = event.player;
			World world = player.getEntityWorld();
			
			if(world.isRemote){
				return;
			}
			
			if(player != null){
				InventoryPlayer inv = player.inventory;
				for(int i = 0; i < inv.getSizeInventory(); i++){
					ItemStack stack = inv.getStackInSlot(i);
					if(!StackHelper.isNull(stack) && stack.getItem() instanceof ItemMagnet){
						if(stack.hasTagCompound()){
							if(stack.getTagCompound().hasKey("Enabled")){
								if(!stack.getTagCompound().getBoolean("Enabled")){
									return;
								}
							}
						} else {
							return;
						}
						double range = getRange();
						List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().grow(range, range, range));
						for(EntityItem item : items){
							item.setPickupDelay(0);
							item.setPosition(player.posX, player.posY, player.posZ);
						}
						List<EntityXPOrb> xporb = world.getEntitiesWithinAABB(EntityXPOrb.class, player.getEntityBoundingBox().grow(range, range, range));
						for(EntityXPOrb orb : xporb){
							orb.setPosition(player.posX, player.posY, player.posZ);
						}
					}
				}
			}
		}
	}
}
