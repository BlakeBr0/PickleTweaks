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
import com.blakebr0.pickletweaks.feature.item.EmeraldArmorItem;
import com.blakebr0.pickletweaks.feature.item.FlintArmorItem;
import com.blakebr0.pickletweaks.feature.item.GrassFiberItem;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.feature.item.MeshItem;
import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import com.blakebr0.pickletweaks.feature.item.PaxelItem;
import com.blakebr0.pickletweaks.feature.item.WateringCanItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldAxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldHoeItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldPaxelItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldPickaxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldShovelItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldSwordItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintAxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintHoeItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintPaxelItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintPickaxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintShearsItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintShovelItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintSwordItem;
import com.blakebr0.pickletweaks.lib.ModArmorMaterial;
import com.blakebr0.pickletweaks.lib.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
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

	public static final CoalPieceItem COAL_PIECE = new CoalPieceItem(p -> p.group(ITEM_GROUP));
	public static final CoalPieceItem CHARCOAL_PIECE = new CoalPieceItem(p -> p.group(ITEM_GROUP));
	public static final DiamondAppleItem DIAMOND_APPLE = new DiamondAppleItem(p -> p.group(ITEM_GROUP));
	public static final EmeraldAppleItem EMERALD_APPLE = new EmeraldAppleItem(p -> p.group(ITEM_GROUP));
	public static final WateringCanItem WATERING_CAN = new WateringCanItem(p -> p.group(ITEM_GROUP));
	public static final GrassFiberItem GRASS_FIBER = new GrassFiberItem(p -> p.group(ITEM_GROUP));
	public static final MeshItem GRASS_FIBER_MESH = new MeshItem(20, p -> p.group(ITEM_GROUP));
	public static final MeshItem MESH = new MeshItem(64, p -> p.group(ITEM_GROUP));
	public static final MeshItem REINFORCED_MESH = new MeshItem(512, p -> p.group(ITEM_GROUP));
	public static final MagnetItem MAGNET = new MagnetItem(p -> p.group(ITEM_GROUP));
	public static final NightVisionGogglesItem NIGHT_VISION_GOGGLES = new NightVisionGogglesItem(ModArmorMaterial.NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP));
	public static final NightVisionGogglesItem REINFORCED_NIGHT_VISION_GOGGLES = new NightVisionGogglesItem(ModArmorMaterial.REINFORCED_NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP));

	public static final FlintSwordItem FLINT_SWORD = new FlintSwordItem(p -> p.group(ITEM_GROUP));
	public static final FlintPickaxeItem FLINT_PICKAXE = new FlintPickaxeItem(p -> p.group(ITEM_GROUP));
	public static final FlintShovelItem FLINT_SHOVEL = new FlintShovelItem(p -> p.group(ITEM_GROUP));
	public static final FlintAxeItem FLINT_AXE = new FlintAxeItem(p -> p.group(ITEM_GROUP));
	public static final FlintHoeItem FLINT_HOE = new FlintHoeItem(p -> p.group(ITEM_GROUP));
	public static final FlintShearsItem FLINT_SHEARS = new FlintShearsItem(p -> p.group(ITEM_GROUP).maxDamage(100));

	public static final EmeraldSwordItem EMERALD_SWORD = new EmeraldSwordItem(p -> p.group(ITEM_GROUP));
	public static final EmeraldPickaxeItem EMERALD_PICKAXE = new EmeraldPickaxeItem(p -> p.group(ITEM_GROUP));
	public static final EmeraldShovelItem EMERALD_SHOVEL = new EmeraldShovelItem(p -> p.group(ITEM_GROUP));
	public static final EmeraldAxeItem EMERALD_AXE = new EmeraldAxeItem(p -> p.group(ITEM_GROUP));
	public static final EmeraldHoeItem EMERALD_HOE = new EmeraldHoeItem(p -> p.group(ITEM_GROUP));

	public static final FlintArmorItem FLINT_HELMET = new FlintArmorItem(EquipmentSlotType.HEAD, p -> p.group(ITEM_GROUP));
	public static final FlintArmorItem FLINT_CHESTPLATE = new FlintArmorItem(EquipmentSlotType.CHEST, p -> p.group(ITEM_GROUP));
	public static final FlintArmorItem FLINT_LEGGINGS = new FlintArmorItem(EquipmentSlotType.LEGS, p -> p.group(ITEM_GROUP));
	public static final FlintArmorItem FLINT_BOOTS = new FlintArmorItem(EquipmentSlotType.FEET, p -> p.group(ITEM_GROUP));
	public static final EmeraldArmorItem EMERALD_HELMET = new EmeraldArmorItem(EquipmentSlotType.HEAD, p -> p.group(ITEM_GROUP));
	public static final EmeraldArmorItem EMERALD_CHESTPLATE = new EmeraldArmorItem(EquipmentSlotType.CHEST, p -> p.group(ITEM_GROUP));
	public static final EmeraldArmorItem EMERALD_LEGGINGS = new EmeraldArmorItem(EquipmentSlotType.LEGS, p -> p.group(ITEM_GROUP));
	public static final EmeraldArmorItem EMERALD_BOOTS = new EmeraldArmorItem(EquipmentSlotType.FEET, p -> p.group(ITEM_GROUP));

	public static final PaxelItem WOODEN_PAXEL = new PaxelItem(ItemTier.WOOD, p -> p.group(ITEM_GROUP));
	public static final PaxelItem STONE_PAXEL = new PaxelItem(ItemTier.STONE, p -> p.group(ITEM_GROUP));
	public static final PaxelItem IRON_PAXEL = new PaxelItem(ItemTier.IRON, p -> p.group(ITEM_GROUP));
	public static final PaxelItem GOLDEN_PAXEL = new PaxelItem(ItemTier.GOLD, p -> p.group(ITEM_GROUP));
	public static final PaxelItem DIAMOND_PAXEL = new PaxelItem(ItemTier.DIAMOND, p -> p.group(ITEM_GROUP));
	public static final PaxelItem FLINT_PAXEL = new FlintPaxelItem(p -> p.group(ITEM_GROUP));
	public static final PaxelItem EMERALD_PAXEL = new EmeraldPaxelItem(p -> p.group(ITEM_GROUP));

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

		registry.register(COAL_PIECE.setRegistryName("coal_piece"));
		registry.register(CHARCOAL_PIECE.setRegistryName("charcoal_piece"));
		registry.register(DIAMOND_APPLE.setRegistryName("diamond_apple"));
		registry.register(EMERALD_APPLE.setRegistryName("emerald_apple"));
		registry.register(WATERING_CAN.setRegistryName("watering_can"));
		registry.register(GRASS_FIBER.setRegistryName("grass_fiber"));
		registry.register(GRASS_FIBER_MESH.setRegistryName("grass_fiber_mesh"));
		registry.register(MESH.setRegistryName("mesh"));
		registry.register(REINFORCED_MESH.setRegistryName("reinforced_mesh"));
		registry.register(MAGNET.setRegistryName("magnet"));
		registry.register(NIGHT_VISION_GOGGLES.setRegistryName("night_vision_goggles"));
		registry.register(REINFORCED_NIGHT_VISION_GOGGLES.setRegistryName("reinforced_night_vision_goggles"));

		registry.register(FLINT_SWORD.setRegistryName("flint_sword"));
		registry.register(FLINT_PICKAXE.setRegistryName("flint_pickaxe"));
		registry.register(FLINT_SHOVEL.setRegistryName("flint_shovel"));
		registry.register(FLINT_AXE.setRegistryName("flint_axe"));
		registry.register(FLINT_HOE.setRegistryName("flint_hoe"));
		registry.register(FLINT_SHEARS.setRegistryName("flint_shears"));

		registry.register(EMERALD_SWORD.setRegistryName("emerald_sword"));
		registry.register(EMERALD_PICKAXE.setRegistryName("emerald_pickaxe"));
		registry.register(EMERALD_SHOVEL.setRegistryName("emerald_shovel"));
		registry.register(EMERALD_AXE.setRegistryName("emerald_axe"));
		registry.register(EMERALD_HOE.setRegistryName("emerald_hoe"));

		registry.register(FLINT_HELMET.setRegistryName("flint_helmet"));
		registry.register(FLINT_CHESTPLATE.setRegistryName("flint_chestplate"));
		registry.register(FLINT_LEGGINGS.setRegistryName("flint_leggings"));
		registry.register(FLINT_BOOTS.setRegistryName("flint_boots"));
		registry.register(EMERALD_HELMET.setRegistryName("emerald_helmet"));
		registry.register(EMERALD_CHESTPLATE.setRegistryName("emerald_chestplate"));
		registry.register(EMERALD_LEGGINGS.setRegistryName("emerald_leggings"));
		registry.register(EMERALD_BOOTS.setRegistryName("emerald_boots"));

		registry.register(WOODEN_PAXEL.setRegistryName("wooden_paxel"));
		registry.register(STONE_PAXEL.setRegistryName("stone_paxel"));
		registry.register(IRON_PAXEL.setRegistryName("iron_paxel"));
		registry.register(GOLDEN_PAXEL.setRegistryName("golden_paxel"));
		registry.register(DIAMOND_PAXEL.setRegistryName("diamond_paxel"));
		registry.register(FLINT_PAXEL.setRegistryName("flint_paxel"));
		registry.register(EMERALD_PAXEL.setRegistryName("emerald_paxel"));

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
}
