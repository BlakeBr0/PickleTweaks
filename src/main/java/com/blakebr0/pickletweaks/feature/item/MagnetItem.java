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

import net.minecraft.item.Item.Properties;

public class MagnetItem extends BaseItem implements IEnableable {
	public MagnetItem(Function<Properties, Properties> properties) {
		super(properties.compose(p -> p.stacksTo(1)));
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return NBTHelper.getBoolean(stack, "Enabled");
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

		NBTHelper.flipBoolean(stack, "Enabled");

		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
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
			List<ItemEntity> items = world.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(range));
			for (ItemEntity item : items) {
				if (!item.isAlive() || NBTHelper.getBoolean(stack, "PreventRemoteMovement"))
					continue;

				if (item.getThrower() != null && item.getThrower().equals(entity.getUUID()) && item.hasPickUpDelay())
					continue;

				if (!world.isClientSide()) {
					item.setNoPickUpDelay();
					item.setPos(entity.getX(), entity.getY(), entity.getZ());
				}
			}

			List<ExperienceOrbEntity> xporbs = world.getEntitiesOfClass(ExperienceOrbEntity.class, entity.getBoundingBox().inflate(range));
			for (ExperienceOrbEntity orb : xporbs) {
				if (!world.isClientSide()) {
					orb.setPos(entity.getX(), entity.getY(), entity.getZ());
				}
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return ModConfigs.ENABLE_MAGNET.get();
	}
}
