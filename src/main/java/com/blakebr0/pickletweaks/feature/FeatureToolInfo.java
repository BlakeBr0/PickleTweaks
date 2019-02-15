package com.blakebr0.pickletweaks.feature;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureToolInfo {

	public static final String[] DEFAULT_VALUES = new String[] { "0=Stone", "1=Iron", "2=Diamond", "3=Obsidian" };
	public static Map<Integer, String> names = new HashMap<Integer, String>();

	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("features");
		String[] values = config.get(category.getName(), "mining_level_names", DEFAULT_VALUES).getStringList();
		category.get("mining_level_names").setComment("Here you can define custom names for the mining levels displayed in the 'tool_info_tooltip'."
						+ "\n- Syntax: level=name" + "\n- 'name' should be exactly how you want it to appear ingame."
						+ "\n- Example: 0=Literal Trash"
						+ "\n- Levels not defined here will show the numerical value.");

		for (String value : values) {
			String[] parts = value.split("=");

			if (parts.length != 2) {
				continue;
			}

			int level;
			String name = parts[1];

			try {
				level = Integer.valueOf(parts[0]);
			} catch (NumberFormatException e) {
				continue;
			}

			names.put(level, name);
		}
	}

	@SubscribeEvent
	public void onBlockBroken(BreakEvent event) {
		if (!ModConfig.confToolInfoTooltip) {
			return;
		}
		
		if (!((event.getState().getBlockHardness(event.getWorld(), event.getPos())) > 0)) {
			return;
		}

		ItemStack stack = event.getPlayer().getHeldItemMainhand();

		if (!stack.isEmpty() && stack.getItem() instanceof ItemTool) {
			NBTTagCompound tag = stack.getOrCreateSubCompound(PickleTweaks.MOD_ID);
			tag.setInteger("BlocksBroken", tag.getInteger("BlocksBroken") + 1);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addToolInfoTooltip(ItemTooltipEvent event) {
		if (!ModConfig.confToolInfoTooltip) return;

		ItemStack stack = event.getItemStack();
		ListIterator<String> tooltip = event.getToolTip().listIterator();

		if (stack.getItem() instanceof ItemTool) {
			ItemTool tool = (ItemTool) stack.getItem();
			boolean shift = false;
			if (Keyboard.isCreated()) {
				shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
			}

			while (tooltip.hasNext()) {
				tooltip.next();
				if (shift) {
					String toolClass = tool.getToolClasses(stack).toArray().length > 0
							? tool.getToolClasses(stack).toArray()[0].toString()
							: "";
					tooltip.add(Utils.localize("tooltip.pt.mining_level") + " " + Colors.WHITE
							+ getMiningLevelName(stack, toolClass));
					tooltip.add(Utils.localize("tooltip.pt.mining_speed") + " " + Colors.WHITE + getMiningSpeed(tool));
					tooltip.add(Utils.localize("tooltip.pt.durability") + " " + Colors.WHITE + getDurability(stack));
					tooltip.add(Utils.localize("tooltip.pt.blocks_broken") + " " + Colors.WHITE + getBlocksBroken(stack));
				} else {
					tooltip.add(Utils.localize("tooltip.pt.hold_shift_for_info"));
				}
				break;
			}
		}
	}

	public String getMiningLevelName(ItemStack stack, String toolClass) {
		ItemTool tool = (ItemTool) stack.getItem();
		int level;
		if (toolClass.equals("")) {
			if (getToolMaterial(tool) != null) {
				level = getToolMaterial(tool).getHarvestLevel();
				if (names.containsKey(level)) {
					return names.get(level);
				}
			}

			return "?";
		}
		
		level = tool.getHarvestLevel(stack, toolClass, null, null);
		if (names.containsKey(level)) {
			return names.get(level);
		}

		return level + "";
	}

	public ToolMaterial getToolMaterial(ItemTool item) {
		return item.toolMaterial;
	}

	public float getMiningSpeed(ItemTool item) {
		ToolMaterial mat = getToolMaterial(item);
		if (mat == null) {
			return -1;
		}
		
		return mat.getEfficiencyOnProperMaterial();
	}

	public String getDurability(ItemStack stack) {
		if (stack.getMaxDamage() == -1) {
			return Utils.localize("tooltip.pt.unbreakable");
		}
		
		int durability = stack.getMaxDamage() - stack.getItemDamage();
		return durability + Colors.GRAY + "/" + Colors.WHITE + stack.getMaxDamage();
	}

	public int getBlocksBroken(ItemStack stack) {
		NBTTagCompound tag = stack.getSubCompound(PickleTweaks.MOD_ID);
		if (tag != null && tag.hasKey("BlocksBroken")) {
			return tag.getInteger("BlocksBroken");
		}
		
		return 0;
	}
}
