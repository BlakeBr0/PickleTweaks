package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemMagnet extends ItemBase implements IEnableable {

	public ItemMagnet() {
		super("pt.magnet");
		this.setCreativeTab(PickleTweaks.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	public ItemStack initTags(ItemStack stack) {
		if (stack.getTagCompound() == null) {
			NBTHelper.setBoolean(stack, "Enabled", false);
		}

		return stack;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return NBTHelper.getBoolean(stack, "Enabled");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		initTags(stack);
		NBTHelper.flipBoolean(stack, "Enabled");

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		initTags(stack);
		if (NBTHelper.getBoolean(stack, "Enabled")) {
			tooltip.add(Utils.localize("tooltip.pt.enabled"));
		} else {
			tooltip.add(Utils.localize("tooltip.pt.disabled"));
		}
	}

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if (entity != null && entity instanceof EntityPlayer) {
            if (NBTHelper.getBoolean(stack, "Enabled")) {
				List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, entity.getEntityBoundingBox().grow((double) ModConfig.confMagnetRange));
				for (EntityItem item : items) {
					if (item.isDead || NBTHelper.getBoolean(stack, "PreventRemoteMovement"))
						continue;

					if (item.getThrower() != null && item.getThrower().equals(entity.getName()) && item.delayBeforeCanPickup > 0)
						continue;

					if (!world.isRemote) {
						item.setPickupDelay(0);
						item.setPosition(entity.posX, entity.posY, entity.posZ);
					}
				}

				List<EntityXPOrb> xporb = world.getEntitiesWithinAABB(EntityXPOrb.class, entity.getEntityBoundingBox().grow((double) ModConfig.confMagnetRange));
				for (EntityXPOrb orb : xporb) {
					if (!world.isRemote) {
						orb.setPosition(entity.posX, entity.posY, entity.posZ);
					}
				}
            }
        }
    }

    @Override
	public boolean isEnabled() {
		return ModConfig.confMagnet;
	}
}
