package com.blakebr0.pickletweaks.feature.item;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.reinforcement.ReinforcementHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class ItemReinforcement extends ItemMeta implements IEnableable {

	public static final String[] DEFAULT_VALUES = new String[] { "0=wood-6B511F-50", "1=stone-7F7F7F-100", "2=iron-C1C1C1-250", "3=gold-BCBF4D-350", "4=diamond-27B29A-500" };
	public static final Map<Integer, Reinforcement> TYPES = new HashMap<>();
	private Configuration config = ModConfig.instance.config;
	
	public ItemReinforcement() {
		super("pt.reinforcement", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.tab);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String name = items.containsKey(stack.getMetadata()) ? items.get(stack.getMetadata()).getName().replace("_", " ") : "Dummy";
		return WordUtils.capitalize(name) + " " + Utils.localize("item.pt.reinforcement.name");
	}
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("reinforcements");
		String[] values = config.get(category.getName(), "custom_reinforcements", DEFAULT_VALUES).getStringList();
		category.get("custom_reinforcements").setComment("Here you can add your own custom Reinforcements." 
						+ "\n- Syntax: meta=name-color-value"
						+ "\n- Example: 0=wood-123456-50"
						+ "\n- 'meta' must be different for each, and should not be changed."
						+ "\n- 'name' should be lower case with underscores for spaces. Reinforcement is added automatically."
						+ "\n- Example: 'do_do' would show 'Do Do Reinforcement'."
						+ "\n- 'color' the color of the Reinforcement as a hex value. http://htmlcolorcodes.com/"
						+ "\n- Example: 123456 would color it as whatever that color is."
						+ "\n- 'value' is the amount of reinforcement value the reinforcement should give.");

		for (String value : values) {
			String[] parts = value.split("=");

			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid custom reinforcement syntax length: " + value);
				continue;
			}
			
			String[] info = parts[1].split("-");

			int meta;
			String name = info[0];
			int color;
			int reinforce;

			try {
				meta = Integer.valueOf(parts[0]);
				color = Integer.parseInt(info[1], 16);
				reinforce = Integer.valueOf(info[2]);
			} catch (NumberFormatException e) {
				PickleTweaks.LOGGER.error("Invalid custom reinforcement syntax ints: " + value);
				continue;
			}
			
			TYPES.put(meta, new Reinforcement(meta, name, color, reinforce));
		}
	}
	
	@Override
	public void init() {
		for (Reinforcement reeee : TYPES.values()) {
			addItem(reeee.meta, reeee.name);
			ReinforcementHandler.REINFORCEMENTS.put(new ItemStack(this, 1, reeee.meta), reeee.value);
		}
	}

	@Override
	public void initModels() {
		if (TYPES.isEmpty()) {
			ModelLoader.setCustomModelResourceLocation(this, 0, ResourceHelper.getModelResource(PickleTweaks.MOD_ID, "reinforcement", "inventory"));
		} else {
			for (Map.Entry<Integer, MetaItem> item : items.entrySet()) {
				ModelLoader.setCustomModelResourceLocation(this, item.getKey(), ResourceHelper.getModelResource(PickleTweaks.MOD_ID, "reinforcement", "inventory"));
			}
		}
	}
	
	@Override
	public boolean isEnabled() {
		return ModConfig.confReinforcements;
	}
	
	public static class Reinforcement {
		public int meta;
		public String name;
		public int color;
		public int value;
		
		public Reinforcement(int meta, String name, int color, int value) {
			this.meta = meta;
			this.name = name;
			this.color = color;
			this.value = value;
		}
	}
}
