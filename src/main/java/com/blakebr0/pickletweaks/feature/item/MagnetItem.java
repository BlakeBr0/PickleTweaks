package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Function;

public class MagnetItem extends BaseItem implements IEnableable {
	public MagnetItem(Function<Properties, Properties> properties) {
		super(properties.compose(p -> p.stacksTo(1)));
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return NBTHelper.getBoolean(stack, "Enabled");
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		var stack = player.getItemInHand(hand);

		player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

		NBTHelper.flipBoolean(stack, "Enabled");

		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag advanced) {
		if (NBTHelper.getBoolean(stack, "Enabled")) {
			tooltip.add(ModTooltips.ENABLED.build());
		} else {
			tooltip.add(ModTooltips.DISABLED.build());
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof Player && NBTHelper.getBoolean(stack, "Enabled")) {
			double range = ModConfigs.MAGNET_RANGE.get();
			var items = world.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(range));

			for (var item : items) {
				if (!item.isAlive() || NBTHelper.getBoolean(stack, "PreventRemoteMovement"))
					continue;

				if (item.getThrower() != null && item.getThrower().equals(entity.getUUID()) && item.hasPickUpDelay())
					continue;

				if (!world.isClientSide()) {
					item.setNoPickUpDelay();
					item.setPos(entity.getX(), entity.getY(), entity.getZ());
				}
			}

			var orbs = world.getEntitiesOfClass(ExperienceOrb.class, entity.getBoundingBox().inflate(range));

			for (ExperienceOrb orb : orbs) {
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
