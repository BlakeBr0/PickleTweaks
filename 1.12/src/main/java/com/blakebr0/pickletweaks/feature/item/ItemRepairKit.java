package com.blakebr0.pickletweaks.feature.item;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRepairKit extends ItemMeta implements IEnableable {
	
	public static Map<Integer, Kit> kits = new HashMap<>();
	private Configuration config = ModConfig.instance.config;

	public ItemRepairKit() {
		super("pt.repair_kit", PickleTweaks.REGISTRY);
		this.setCreativeTab(PickleTweaks.tab);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String name = items.containsKey(stack.getMetadata()) ? items.get(stack.getMetadata()).getName().replace("_", " ") : "Dummy";
		return WordUtils.capitalize(name) + " " + Utils.localize("item.pt.repair_kit.name");
	}

	@Override
	public void init() {
		addKit(new Kit(0, "wood", 0x6B511F, StackHelper.to(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)));
		addKit(new Kit(1, "stone", 0x7F7F7F, StackHelper.to(Blocks.COBBLESTONE)));
		addKit(new Kit(2, "iron", 0xC1C1C1, "ingotIron"));
		addKit(new Kit(3, "gold", 0xBCBF4D, "ingotGold"));
		addKit(new Kit(4, "diamond", 0x27B29A, "gemDiamond"));
		addKit(new Kit(5, "flint", 0x333333, StackHelper.to(Items.FLINT)));
	}
	
	@Override
	public void initModels() {
		for (Map.Entry<Integer, MetaItem> item : items.entrySet()) {
			ModelLoader.setCustomModelResourceLocation(this, item.getKey(), new ModelResourceLocation(PickleTweaks.MOD_ID + ":repair_kit", "inventory"));
		}
	}
	
	public ItemStack addKit(Kit kit) {
		return addKit(kit, true);
	}
	
	public ItemStack addKit(Kit kit, boolean also) {
		boolean enabled = config.get("repair_kit", kit.name, true).getBoolean() && also;
		if (enabled) {
			kits.put(kit.meta, kit);
		}
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
		Item item = tool.getItem();
		if (rep instanceof String) {
			String ore = (String) rep;
			if (OreDictionary.doesOreNameExist(ore)) {
				return OreDictionary.getOres(ore).stream().anyMatch(repair -> item.getIsRepairable(tool, repair));
			}
		} else if (rep instanceof ItemStack) {
			ItemStack stack = (ItemStack) rep;
			return item.getIsRepairable(tool, stack);
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
