package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;
import com.blakebr0.pickletweaks.feature.item.ItemCoalPiece;
import com.blakebr0.pickletweaks.feature.item.ItemCoin;
import com.blakebr0.pickletweaks.feature.item.ItemDiamondApple;
import com.blakebr0.pickletweaks.feature.item.ItemDyePowder;
import com.blakebr0.pickletweaks.feature.item.ItemEmeraldApple;
import com.blakebr0.pickletweaks.feature.item.ItemHammer;
import com.blakebr0.pickletweaks.feature.item.ItemMagnet;
import com.blakebr0.pickletweaks.feature.item.ItemMesh;
import com.blakebr0.pickletweaks.feature.item.ItemMortarAndPestle;
import com.blakebr0.pickletweaks.feature.item.ItemNightvisionGoggles;
import com.blakebr0.pickletweaks.feature.item.ItemPaxel;
import com.blakebr0.pickletweaks.feature.item.ItemRepairKit;
import com.blakebr0.pickletweaks.feature.item.ItemWateringCan;
import com.blakebr0.pickletweaks.feature.item.flint.ItemModAxe;
import com.blakebr0.pickletweaks.feature.item.flint.ItemModHoe;
import com.blakebr0.pickletweaks.feature.item.flint.ItemModPickaxe;
import com.blakebr0.pickletweaks.feature.item.flint.ItemModShovel;
import com.blakebr0.pickletweaks.feature.item.flint.ItemModSword;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	
	public static ItemCoalPiece itemCoalPiece = new ItemCoalPiece();
	public static ItemDiamondApple itemDiamondApple = new ItemDiamondApple();
	public static ItemEmeraldApple itemEmeraldApple = new ItemEmeraldApple();
	public static ItemWateringCan itemWateringCan = new ItemWateringCan();
	public static ItemHammer itemHammer = new ItemHammer();
	public static ItemMesh itemMesh = new ItemMesh("mesh", 63);
	public static ItemMesh itemReinforcedMesh = new ItemMesh("reinforced_mesh", 511);
	public static ItemMagnet itemMagnet = new ItemMagnet();
	public static ItemNightvisionGoggles itemNightvisionGoggles = new ItemNightvisionGoggles();
	public static ItemCoin itemCoin = new ItemCoin();
	public static ItemMortarAndPestle itemMortarAndPestle = new ItemMortarAndPestle();
	public static ItemDyePowder itemDyePowder = new ItemDyePowder();
	public static ItemRepairKit itemRepairKit = new ItemRepairKit();
	
	public static ItemPaxel itemWoodenPaxel = new ItemPaxel("wooden_paxel", ToolMaterial.WOOD);
	public static ItemPaxel itemStonePaxel = new ItemPaxel("stone_paxel", ToolMaterial.STONE);
	public static ItemPaxel itemIronPaxel = new ItemPaxel("iron_paxel", ToolMaterial.IRON);
	public static ItemPaxel itemGoldenPaxel = new ItemPaxel("golden_paxel", ToolMaterial.GOLD);
	public static ItemPaxel itemDiamondPaxel = new ItemPaxel("diamond_paxel", ToolMaterial.DIAMOND);
	
	public static final ToolMaterial MATERIAL_FLINT = EnumHelper.addToolMaterial("PT:FLINT", 1, 157, 3.8F, 1.0F, 10); 
	public static ItemModSword itemFlintSword = new ItemModSword("flint_sword", MATERIAL_FLINT);
	public static ItemModPickaxe itemFlintPickaxe = new ItemModPickaxe("flint_pickaxe", MATERIAL_FLINT);
	public static ItemModShovel itemFlintShovel = new ItemModShovel("flint_shovel", MATERIAL_FLINT);
	public static ItemModAxe itemFlintAxe = new ItemModAxe("flint_axe", MATERIAL_FLINT);
	public static ItemModHoe itemFlintHoe = new ItemModHoe("flint_hoe", MATERIAL_FLINT);
	public static ItemPaxel itemFlintPaxel = new ItemPaxel("flint_paxel", MATERIAL_FLINT);
	
	public static void init() {
		final ModRegistry registry = PickleTweaks.REGISTRY;
		
		registry.register(itemCoalPiece, "coal_piece");		
		registry.register(itemDiamondApple, "diamond_apple");
		registry.register(itemEmeraldApple, "emerald_apple");		
		registry.register(itemWateringCan, "watering_can");		
		registry.register(itemHammer, "hammer");		
		registry.register(itemMesh, "mesh");
		registry.register(itemReinforcedMesh, "reinforced_mesh");		
		registry.register(itemMagnet, "magnet");
		registry.register(itemNightvisionGoggles, "nightvision_goggles");		
		registry.register(itemCoin, "coin");
		registry.register(itemMortarAndPestle, "mortar_and_pestle");
		registry.register(itemDyePowder, "dye_powder");
		registry.register(itemRepairKit, "repair_kit");
		
		registry.register(itemWoodenPaxel, "wooden_paxel", StackHelper.to(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE));
		registry.register(itemStonePaxel, "stone_paxel", StackHelper.to(Blocks.COBBLESTONE));
		registry.register(itemIronPaxel, "iron_paxel", StackHelper.to(Items.IRON_INGOT));
		registry.register(itemGoldenPaxel, "golden_paxel", StackHelper.to(Items.GOLD_INGOT));
		registry.register(itemDiamondPaxel, "diamond_paxel", StackHelper.to(Items.DIAMOND));
		
		if (ModConfig.confFlintTools) {
			registry.register(itemFlintSword, "flint_sword", StackHelper.to(Items.FLINT));
			registry.register(itemFlintPickaxe, "flint_pickaxe", StackHelper.to(Items.FLINT));
			registry.register(itemFlintShovel, "flint_shovel", StackHelper.to(Items.FLINT));
			registry.register(itemFlintAxe, "flint_axe", StackHelper.to(Items.FLINT));
			registry.register(itemFlintHoe, "flint_hoe", StackHelper.to(Items.FLINT));
			registry.register(itemFlintPaxel, "flint_paxel", StackHelper.to(Items.FLINT));
		}
	}
}
