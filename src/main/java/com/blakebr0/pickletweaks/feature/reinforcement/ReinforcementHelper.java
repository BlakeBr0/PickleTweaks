package com.blakebr0.pickletweaks.feature.reinforcement;

import java.util.Map;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class ReinforcementHelper {
	
	public static void reinforce(ItemStack stack, int reinforce) {
		int current = getReinforcement(stack);
		int add = Math.min(current + reinforce, ModConfig.confMaxReinforcement);
		PickleTweaks.TAG_HELPER.setInt(stack, "Reinforcement", add);
		
		if (current == 0) {
			PickleTweaks.TAG_HELPER.setInt(stack, "Damage", stack.getItemDamage());
		}
	}
	
	/**
	 * Uses reinforcement value
	 * @param stack tool
	 * @param remove to remove
	 * @return any extra durability not covered by the reinforcement
	 */
	public static int useReinforcement(ItemStack stack, int remove) {
		int current = getReinforcement(stack);
		int remaining = current - remove;
		
		if (remaining > 0) {
			PickleTweaks.TAG_HELPER.setInt(stack, "Reinforcement", remaining);
		} else {
			PickleTweaks.TAG_HELPER.removeTag(stack, "Reinforcement");
			PickleTweaks.TAG_HELPER.removeTag(stack, "Damage");
		}
		
		return remaining > 0 ? 0 : -remaining;
	}
	
	/**
	 * Gets the current reinforcement of a tool
	 * @param stack tool stack
	 * @return reinforcement amount
	 */
	public static int getReinforcement(ItemStack stack) {
		return PickleTweaks.TAG_HELPER.getInt(stack, "Reinforcement");
	}

	public static boolean isReinforced(ItemStack stack) {
		return PickleTweaks.TAG_HELPER.hasKey(stack, "Reinforcement");
	}
	
	/**
	 * Gets the reinforcement value of a reinforcement item
	 * Note: doesnt check if its valid so do that yourself please haha!
	 * @param stack reinforcement stack
	 * @return reinforcment amount
	 */
	public static int getReinforcementValue(ItemStack stack) {
		for (Map.Entry<ItemStack, Integer> entry : ReinforcementHandler.REINFORCEMENTS.entrySet()) {
			if (entry.getKey().isItemEqual(stack)) {
				return entry.getValue();
			}
		}
		
		return 0;
	}
	
	public static boolean isReinforcement(ItemStack stack) {
		return ReinforcementHandler.REINFORCEMENTS.keySet().stream().anyMatch(o -> o.isItemEqual(stack));
	}
	
	public static boolean isReinforceable(Item tool) {
		return tool instanceof ItemTool || tool instanceof ItemSword || tool instanceof ItemHoe || tool instanceof ItemBow;
	}
	
	public static int getDurability(ItemStack stack) {
		return PickleTweaks.TAG_HELPER.getInt(stack, "Damage");
	}
}
