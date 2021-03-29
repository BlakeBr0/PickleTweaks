package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Function;

public class MagnetItem extends BaseItem implements IEnableable {
	public MagnetItem(Function<Properties, Properties> properties) {
		super(properties.compose(p -> p.maxStackSize(1)));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return NBTHelper.getBoolean(stack, "Enabled");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

		NBTHelper.flipBoolean(stack, "Enabled");

		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
		if (NBTHelper.getBoolean(stack, "Enabled")) {
			tooltip.add(ModTooltips.ENABLED.build());
		} else {
			tooltip.add(ModTooltips.DISABLED.build());
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof PlayerEntity && NBTHelper.getBoolean(stack, "Enabled")) {
			double range = ModConfigs.MAGNET_RANGE.get();
			List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, entity.getBoundingBox().grow(range));
			for (ItemEntity item : items) {
				if (!item.isAlive() || NBTHelper.getBoolean(stack, "PreventRemoteMovement"))
					continue;

				if (item.getThrowerId() != null && item.getThrowerId().equals(entity.getUniqueID()) && item.cannotPickup())
					continue;

				if (!world.isRemote()) {
					item.setNoPickupDelay();
					item.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
				}
			}

			List<ExperienceOrbEntity> xporbs = world.getEntitiesWithinAABB(ExperienceOrbEntity.class, entity.getBoundingBox().grow(range));
			for (ExperienceOrbEntity orb : xporbs) {
				if (!world.isRemote()) {
					orb.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
				}
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_MAGNET.get();
	}
}
