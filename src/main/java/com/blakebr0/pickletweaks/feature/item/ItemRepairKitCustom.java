package com.blakebr0.pickletweaks.feature.item;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairHelper;
import com.blakebr0.pickletweaks.feature.crafting.GridRepairOverride;
import com.blakebr0.pickletweaks.registry.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRepairKitCustom extends ItemMeta implements IEnableable {
	
	public static Map<Integer, Kit> kits = new HashMap<>();
	private Configuration config = ModConfig.instance.config;

	public ItemRepairKitCustom() {
		super("pt.repair_kit_custom", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.tab);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String name = items.containsKey(stack.getMetadata()) ? items.get(stack.getMetadata()).getName().replace("_", " ") : "Dummy";
		return WordUtils.capitalize(name) + " " + Utils.localize("item.pt.repair_kit.name");
	}
	
	public static void configure(Configuration config) {
		ConfigCategory category = config.getCategory("repair_kit");
		String[] values = config.get(category.getName(), "_custom_repair_kits", new String[0]).getStringList();
		category.get("_custom_repair_kits").setComment("Here you can add your own custom Repair Kits." 
						+ "\n- Syntax: meta=name-color-representativeitem"
						+ "\n- Example: 12=super_kit-123456-minecraft:iron_ingot"
						+ "\n- 'meta' must be different for each, and should not be changed."
						+ "\n- 'name' should be lower case with underscores for spaces. Repair Kit is added automatically."
						+ "\n- Example: 'lots_of_spaghetti' would show 'Lots Of Spaghetti Repair Kit'."
						+ "\n- 'color' the color of the Repair Kit as a hex value. http://htmlcolorcodes.com/"
						+ "\n- Example: 123456 would color it as whatever that color is."
						+ "\n- 'representativeitem' is an item id or ore dictionary entry. This is the material the Repair Kit represents, and what it will require to craft."
						+ "\n- Examples: 'minecraft:stone' for stone, 'ore:ingotIron' for the ore dictionary entry 'ingotIron'."
						+ "\n- Note: you can also specify meta for item ids, by adding them to the end of the item id."
						+ "\n- Example: minecraft:stone:3 for a meta of 3.");

		for (String value : values) {
			String[] parts = value.split("=");

			if (parts.length != 2) {
				PickleTweaks.LOGGER.error("Invalid custom repair kit syntax length: " + value);
				continue;
			}
			
			String[] info = parts[1].split("-");

			int meta;
			String name = info[0];
			String representative = info[2];
			int color;

			try {
				meta = Integer.valueOf(parts[0]);
				color = Integer.parseInt(info[1], 16);
			} catch (NumberFormatException e) {
				PickleTweaks.LOGGER.error("Invalid custom repair kit syntax ints: " + value);
				continue;
			}
			
			if (!representative.startsWith("ore:")) {
				String[] matParts = representative.split(":");
				Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(matParts[0], matParts[1]));
				if (item == null) {
					continue;
				}
				int matMeta = 0;
				if (matParts.length == 3) {
					try {
						matMeta = Integer.valueOf(matParts[2]);
					} catch (NumberFormatException e) {
						PickleTweaks.LOGGER.error("Invalid custom repair kit repair material meta: " + value);
						continue;
					}
				}
				kits.put(meta, new Kit(meta, name, color, StackHelper.to(item, 1, matMeta)));
			} else {
				kits.put(meta, new Kit(meta, name, color, representative));
			}
		}
	}

	@Override
	public void init() {
		for (Map.Entry<Integer, Kit> kit : kits.entrySet()) {
			addKit(kit.getValue());
		}
	}
	
	@Override
	public void initModels() {
		for (Map.Entry<Integer, MetaItem> item : items.entrySet()) {
			ModelLoader.setCustomModelResourceLocation(this, item.getKey(), new ModelResourceLocation(PickleTweaks.MOD_ID + ":repair_kit", "inventory"));
		}
	}
	
	public static void initRecipes() {
		if (!ModConfig.confRepairKits || !ModConfig.confHammer) { return; }
		for (Map.Entry<Integer, Kit> kit : kits.entrySet()) {
			Kit kitten = kit.getValue();
			Object rep = kitten.representative;
			if (rep instanceof ItemStack) {
				ItemStack stack = (ItemStack) rep;
				if (!stack.isEmpty()) {
					RecipeHelper.addShapedRecipe(StackHelper.to(ModItems.itemRepairKitCustom, 2, kit.getKey()), "SMS", "MHM", "SMS", 'S', "stickWood", 'M', stack, 'H', StackHelper.to(ModItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE));
				}
			} else if (rep instanceof String) {
				String ore = (String) rep;
				if (ore.startsWith("ore:")) {
					ore = ore.substring(4);
					if (OreDictionary.doesOreNameExist(ore)) {
						RecipeHelper.addShapedRecipe(StackHelper.to(ModItems.itemRepairKitCustom, 2, kit.getKey()), "SMS", "MHM", "SMS", 'S', "stickWood", 'M', ore, 'H', StackHelper.to(ModItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE));
					}
				}
			}
		}
	}
	
	public ItemStack addKit(Kit kit) {
		return addKit(kit, true);
	}
	
	public ItemStack addKit(Kit kit, boolean also) {
		kits.put(kit.meta, kit);
		return addItem(kit.meta, kit.name);
	}
	
	public Kit getKit(ItemStack stack) {
		return kits.get(stack.getMetadata());
	}
	
	@Override
	public boolean isEnabled() {
		return ModConfig.confRepairKits;
	}
	
	public static boolean isKitValid(ItemStack tool, Kit kit) {
		Object rep = kit.representative;
		if (rep instanceof String) {
			String ore = ((String) rep).substring(4);
			if (OreDictionary.doesOreNameExist(ore)) {
				return OreDictionary.getOres(ore).stream().anyMatch(repair -> GridRepairHelper.checkMaterial(tool, repair));
			}
		} else if (rep instanceof ItemStack) {
			ItemStack stack = (ItemStack) rep;
			return GridRepairHelper.checkMaterial(tool, stack);
		}
		return false;
	}
	
	public static class Kit {
		
		public int meta;
		public String name;
		public int color;
		public Object representative;
		
		public Kit(int meta, String name, int color, Object representative) {
			this.meta = meta;
			this.name = name;
			this.color = color;
			this.representative = representative;
		}
	}
}
