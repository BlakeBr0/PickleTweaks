package com.blakebr0.pickletweaks.registry;

import com.blakebr0.cucumber.item.tool.BaseAxeItem;
import com.blakebr0.cucumber.item.tool.BaseHoeItem;
import com.blakebr0.cucumber.item.tool.BasePickaxeItem;
import com.blakebr0.cucumber.item.tool.BaseShearsItem;
import com.blakebr0.cucumber.item.tool.BaseShovelItem;
import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.pickletweaks.feature.item.CoalPieceItem;
import com.blakebr0.pickletweaks.feature.item.DiamondAppleItem;
import com.blakebr0.pickletweaks.feature.item.EmeraldAppleItem;
import com.blakebr0.pickletweaks.feature.item.GrassFiberItem;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.feature.item.MeshItem;
import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import com.blakebr0.pickletweaks.feature.item.PaxelItem;
import com.blakebr0.pickletweaks.feature.item.WateringCanItem;
import com.blakebr0.pickletweaks.lib.ModArmorMaterial;
import com.blakebr0.pickletweaks.lib.ModItemTier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public class ModItems {
	public static final List<BlockItem> BLOCK_ITEMS = new ArrayList<>();

	public static final RegistryObject<CoalPieceItem> COAL_PIECE = get("coal_piece");
	public static final RegistryObject<CoalPieceItem> CHARCOAL_PIECE = get("charcoal_piece");
	public static final RegistryObject<DiamondAppleItem> DIAMOND_APPLE = get("diamond_apple");
	public static final RegistryObject<EmeraldAppleItem> EMERALD_APPLE = get("emerald_apple");
	public static final RegistryObject<WateringCanItem> WATERING_CAN = get("watering_can");
	public static final RegistryObject<GrassFiberItem> GRASS_FIBER = get("grass_fiber");
	public static final RegistryObject<MeshItem> GRASS_FIBER_MESH = get("grass_fiber_mesh");
	public static final RegistryObject<MeshItem> MESH = get("mesh");
	public static final RegistryObject<MeshItem> REINFORCED_MESH = get("reinforced_mesh");
	public static final RegistryObject<MagnetItem> MAGNET = get("magnet");
	public static final RegistryObject<NightVisionGogglesItem> NIGHT_VISION_GOGGLES = get("night_vision_goggles");
	public static final RegistryObject<NightVisionGogglesItem> REINFORCED_NIGHT_VISION_GOGGLES = get("reinforced_night_vision_goggles");

	public static final RegistryObject<BaseSwordItem> FLINT_SWORD = get("flint_sword");
	public static final RegistryObject<BasePickaxeItem> FLINT_PICKAXE = get("flint_pickaxe");
	public static final RegistryObject<BaseShovelItem> FLINT_SHOVEL = get("flint_shovel");
	public static final RegistryObject<BaseAxeItem> FLINT_AXE = get("flint_axe");
	public static final RegistryObject<BaseHoeItem> FLINT_HOE = get("flint_hoe");
	public static final RegistryObject<BaseShearsItem> FLINT_SHEARS = get("flint_shears");

	public static final RegistryObject<PaxelItem> WOODEN_PAXEL = get("wooden_paxel");
	public static final RegistryObject<PaxelItem> STONE_PAXEL = get("stone_paxel");
	public static final RegistryObject<PaxelItem> IRON_PAXEL = get("iron_paxel");
	public static final RegistryObject<PaxelItem> GOLDEN_PAXEL = get("golden_paxel");
	public static final RegistryObject<PaxelItem> DIAMOND_PAXEL = get("diamond_paxel");
	public static final RegistryObject<PaxelItem> FLINT_PAXEL = get("flint_paxel");

	// Thermal Foundation tool material stats
	// https://github.com/CoFH/ThermalFoundation/blob/master/src/main/java/cofh/thermalfoundation/init/TFEquipment.java
//	public static final ToolMaterial MATERIAL_ALUMINUM = EnumHelper.addToolMaterial("PT:ALUMINUM", 1, 225, 10.0F, 1.0F, 14);
//	public static final ToolMaterial MATERIAL_COPPER = EnumHelper.addToolMaterial("PT:COPPER", 1, 175, 4.0F, 0.75F, 6);
//	public static final ToolMaterial MATERIAL_TIN = EnumHelper.addToolMaterial("PT:TIN", 1, 200, 4.5F, 1.0F, 7);
//	public static final ToolMaterial MATERIAL_BRONZE = EnumHelper.addToolMaterial("PT:BRONZE", 2, 500, 6.0F, 2.0F, 15);
//	public static final ToolMaterial MATERIAL_LEAD = EnumHelper.addToolMaterial("PT:LEAD", 1, 150, 5.0F, 1.0F, 9);
//	public static final ToolMaterial MATERIAL_SILVER = EnumHelper.addToolMaterial("PT:SILVER", 2, 200, 6.0F, 1.5F, 20);
//	public static final ToolMaterial MATERIAL_NICKEL = EnumHelper.addToolMaterial("PT:NICKEL", 2, 300, 6.5F, 2.5F, 18);
//	public static final ToolMaterial MATERIAL_INVAR = EnumHelper.addToolMaterial("PT:INVAR", 2, 450, 7.0F, 3.0F, 16);
//	public static final ToolMaterial MATERIAL_CONSTANTAN = EnumHelper.addToolMaterial("PT:CONSTANTAN", 2, 275, 6.0F, 1.5F, 20);
//	public static final ToolMaterial MATERIAL_ELECTRUM = EnumHelper.addToolMaterial("PT:ELECTRUM", 0, 100, 14.0F, 0.5F, 30);
//	public static final ToolMaterial MATERIAL_STEEL = EnumHelper.addToolMaterial("PT:STEEL", 2, 500, 6.5F, 2.5F, 10);
//	public static final ToolMaterial MATERIAL_PLATINUM = EnumHelper.addToolMaterial("PT:PLATINUM", 4, 1700, 9.0F, 4.0F, 9);
//
//	public static PaxelItem itemAluminumPaxel = new PaxelItem("aluminum_paxel", MATERIAL_ALUMINUM, "ingotAluminum");
//	public static PaxelItem itemCopperPaxel = new PaxelItem("copper_paxel", MATERIAL_COPPER, "ingotCopper");
//	public static PaxelItem itemTinPaxel = new PaxelItem("tin_paxel", MATERIAL_TIN, "ingotTin");
//	public static PaxelItem itemBronzePaxel = new PaxelItem("bronze_paxel", MATERIAL_BRONZE, "ingotBronze");
//	public static PaxelItem itemLeadPaxel = new PaxelItem("lead_paxel", MATERIAL_LEAD, "ingotLead");
//	public static PaxelItem itemSilverPaxel = new PaxelItem("silver_paxel", MATERIAL_SILVER, "ingotSilver");
//	public static PaxelItem itemNickelPaxel = new PaxelItem("nickel_paxel", MATERIAL_NICKEL, "ingotNickel");
//	public static PaxelItem itemInvarPaxel = new PaxelItem("invar_paxel", MATERIAL_INVAR, "ingotInvar");
//	public static PaxelItem itemConstantanPaxel = new PaxelItem("constantan_paxel", MATERIAL_CONSTANTAN, "ingotConstantan");
//	public static PaxelItem itemElectrumPaxel = new PaxelItem("electrum_paxel", MATERIAL_ELECTRUM, "ingotElectrum");
//	public static PaxelItem itemSteelPaxel = new PaxelItem("steel_paxel", MATERIAL_STEEL, "ingotSteel");
//	public static PaxelItem itemPlatinumPaxel = new PaxelItem("platinum_paxel", MATERIAL_PLATINUM, "ingotPlatinum");

	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		BLOCK_ITEMS.forEach(registry::register);

		registry.registerAll(
				new CoalPieceItem(p -> p.group(ITEM_GROUP)).setRegistryName("coal_piece"),
				new CoalPieceItem(p -> p.group(ITEM_GROUP)).setRegistryName("charcoal_piece"),
				new DiamondAppleItem(p -> p.group(ITEM_GROUP)).setRegistryName("diamond_apple"),
				new EmeraldAppleItem(p -> p.group(ITEM_GROUP)).setRegistryName("emerald_apple"),
				new WateringCanItem(p -> p.group(ITEM_GROUP)).setRegistryName("watering_can"),
				new GrassFiberItem(p -> p.group(ITEM_GROUP)).setRegistryName("grass_fiber"),
				new MeshItem(20, p -> p.group(ITEM_GROUP)).setRegistryName("grass_fiber_mesh"),
				new MeshItem(64, p -> p.group(ITEM_GROUP)).setRegistryName("mesh"),
				new MeshItem(512, p -> p.group(ITEM_GROUP)).setRegistryName("reinforced_mesh"),
				new MagnetItem(p -> p.group(ITEM_GROUP)).setRegistryName("magnet"),
				new NightVisionGogglesItem(ModArmorMaterial.NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP)).setRegistryName("night_vision_goggles"),
				new NightVisionGogglesItem(ModArmorMaterial.REINFORCED_NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP)).setRegistryName("reinforced_night_vision_goggles"),

				new BaseSwordItem(ModItemTier.FLINT, p -> p.group(ITEM_GROUP)).setRegistryName("flint_sword"),
				new BasePickaxeItem(ModItemTier.FLINT, p -> p.group(ITEM_GROUP)).setRegistryName("flint_pickaxe"),
				new BaseShovelItem(ModItemTier.FLINT, p -> p.group(ITEM_GROUP)).setRegistryName("flint_shovel"),
				new BaseAxeItem(ModItemTier.FLINT, p -> p.group(ITEM_GROUP)).setRegistryName("flint_axe"),
				new BaseHoeItem(ModItemTier.FLINT, p -> p.group(ITEM_GROUP)).setRegistryName("flint_hoe"),
				new BaseShearsItem(p -> p.group(ITEM_GROUP).maxDamage(100)).setRegistryName("flint_shears"),

				new PaxelItem(ItemTier.WOOD, p -> p.group(ITEM_GROUP)).setRegistryName("wooden_paxel"),
				new PaxelItem(ItemTier.STONE, p -> p.group(ITEM_GROUP)).setRegistryName("stone_paxel"),
				new PaxelItem(ItemTier.IRON, p -> p.group(ITEM_GROUP)).setRegistryName("iron_paxel"),
				new PaxelItem(ItemTier.GOLD, p -> p.group(ITEM_GROUP)).setRegistryName("golden_paxel"),
				new PaxelItem(ItemTier.DIAMOND, p -> p.group(ITEM_GROUP)).setRegistryName("diamond_paxel"),
				new PaxelItem(ModItemTier.FLINT, p -> p.group(ITEM_GROUP)).setRegistryName("flint_paxel")
		);

//			registry.register(itemAluminumPaxel, "aluminum_paxel");
//			registry.register(itemCopperPaxel, "copper_paxel");
//			registry.register(itemTinPaxel, "tin_paxel");
//			registry.register(itemBronzePaxel, "bronze_paxel");
//			registry.register(itemLeadPaxel, "lead_paxel");
//			registry.register(itemSilverPaxel, "silver_paxel");
//			registry.register(itemNickelPaxel, "nickel_paxel");
//			registry.register(itemInvarPaxel, "invar_paxel");
//			registry.register(itemConstantanPaxel, "constantan_paxel");
//			registry.register(itemElectrumPaxel, "electrum_paxel");
//			registry.register(itemSteelPaxel, "steel_paxel");
//			registry.register(itemPlatinumPaxel, "platinum_paxel");
	}

	private static <T extends Item> RegistryObject<T> get(String name) {
		return RegistryObject.of("pickletweaks:" + name, () -> Item.class);
	}
}
