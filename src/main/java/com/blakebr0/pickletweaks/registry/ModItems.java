package com.blakebr0.pickletweaks.registry;

import com.blakebr0.pickletweaks.PickleTweaks;
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
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public class ModItems {
	public static final List<BlockItem> BLOCK_ITEMS = new ArrayList<>();

	@ObjectHolder("pickletweaks:emerald_apple")
	public static final EmeraldAppleItem EMERALD_APPLE = null;

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

		Item coalPiece = new CoalPieceItem(p -> p.group(ITEM_GROUP));
		Item charcoalPiece = new CoalPieceItem(p -> p.group(ITEM_GROUP));
		Item diamondApple = new DiamondAppleItem(p -> p.group(ITEM_GROUP));
		Item emeraldApple = new EmeraldAppleItem(p -> p.group(ITEM_GROUP));
		Item wateringCan = new WateringCanItem(p -> p.group(ITEM_GROUP));
		Item grassFiber = new GrassFiberItem(p -> p.group(ITEM_GROUP));
		Item grassFiberMesh = new MeshItem(20, p -> p.group(ITEM_GROUP));
		Item mesh = new MeshItem(64, p -> p.group(ITEM_GROUP));
		Item reinforcedMesh = new MeshItem(512, p -> p.group(ITEM_GROUP));
		Item magnet = new MagnetItem(p -> p.group(ITEM_GROUP));
		Item nightVisionGoggles = new NightVisionGogglesItem(ModArmorMaterial.NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP));
		Item reinforcedNightVisionGoggles = new NightVisionGogglesItem(ModArmorMaterial.REINFORCED_NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP));

		Item flintSword = new FlintSwordItem(p -> p.group(ITEM_GROUP));
		Item flintPickaxe = new FlintPickaxeItem(p -> p.group(ITEM_GROUP));
		Item flintShovel = new FlintShovelItem(p -> p.group(ITEM_GROUP));
		Item flintAxe = new FlintAxeItem(p -> p.group(ITEM_GROUP));
		Item flintHoe = new FlintHoeItem(p -> p.group(ITEM_GROUP));
		Item flintShears = new FlintShearsItem(p -> p.group(ITEM_GROUP).maxDamage(100));

		Item emeraldSword = new EmeraldSwordItem(p -> p.group(ITEM_GROUP));
		Item emeraldPickaxe = new EmeraldPickaxeItem(p -> p.group(ITEM_GROUP));
		Item emeraldShovel = new EmeraldShovelItem(p -> p.group(ITEM_GROUP));
		Item emeraldAxe = new EmeraldAxeItem(p -> p.group(ITEM_GROUP));
		Item emeraldHoe = new EmeraldHoeItem(p -> p.group(ITEM_GROUP));

		Item flintHelmet = new FlintArmorItem(EquipmentSlotType.HEAD, p -> p.group(ITEM_GROUP));
		Item flintChestplate = new FlintArmorItem(EquipmentSlotType.CHEST, p -> p.group(ITEM_GROUP));
		Item flintLeggings = new FlintArmorItem(EquipmentSlotType.LEGS, p -> p.group(ITEM_GROUP));
		Item flintBoots = new FlintArmorItem(EquipmentSlotType.FEET, p -> p.group(ITEM_GROUP));
		Item emeraldHelmet = new EmeraldArmorItem(EquipmentSlotType.HEAD, p -> p.group(ITEM_GROUP));
		Item emeraldChestplate = new EmeraldArmorItem(EquipmentSlotType.CHEST, p -> p.group(ITEM_GROUP));
		Item emeraldLeggings = new EmeraldArmorItem(EquipmentSlotType.LEGS, p -> p.group(ITEM_GROUP));
		Item emeraldBoots = new EmeraldArmorItem(EquipmentSlotType.FEET, p -> p.group(ITEM_GROUP));

		Item woodenPaxel = new PaxelItem(ItemTier.WOOD, p -> p.group(ITEM_GROUP));
		Item stonePaxel = new PaxelItem(ItemTier.STONE, p -> p.group(ITEM_GROUP));
		Item ironPaxel  = new PaxelItem(ItemTier.IRON, p -> p.group(ITEM_GROUP));
		Item goldenPaxel = new PaxelItem(ItemTier.GOLD, p -> p.group(ITEM_GROUP));
		Item diamondPaxel = new PaxelItem(ItemTier.DIAMOND, p -> p.group(ITEM_GROUP));
		Item flintPaxel = new FlintPaxelItem(p -> p.group(ITEM_GROUP));
		Item emeraldPaxel = new EmeraldPaxelItem(p -> p.group(ITEM_GROUP));

		registry.register(coalPiece.setRegistryName("coal_piece"));
		registry.register(charcoalPiece.setRegistryName("charcoal_piece"));
		registry.register(diamondApple.setRegistryName("diamond_apple"));
		registry.register(emeraldApple.setRegistryName("emerald_apple"));
		registry.register(wateringCan.setRegistryName("watering_can"));
		registry.register(grassFiber.setRegistryName("grass_fiber"));
		registry.register(grassFiberMesh.setRegistryName("grass_fiber_mesh"));
		registry.register(mesh.setRegistryName("mesh"));
		registry.register(reinforcedMesh.setRegistryName("reinforced_mesh"));
		registry.register(magnet.setRegistryName("magnet"));
		registry.register(nightVisionGoggles.setRegistryName("night_vision_goggles"));
		registry.register(reinforcedNightVisionGoggles.setRegistryName("reinforced_night_vision_goggles"));

		registry.register(flintSword.setRegistryName("flint_sword"));
		registry.register(flintPickaxe.setRegistryName("flint_pickaxe"));
		registry.register(flintShovel.setRegistryName("flint_shovel"));
		registry.register(flintAxe.setRegistryName("flint_axe"));
		registry.register(flintHoe.setRegistryName("flint_hoe"));
		registry.register(flintShears.setRegistryName("flint_shears"));

		registry.register(emeraldSword.setRegistryName("emerald_sword"));
		registry.register(emeraldPickaxe.setRegistryName("emerald_pickaxe"));
		registry.register(emeraldShovel.setRegistryName("emerald_shovel"));
		registry.register(emeraldAxe.setRegistryName("emerald_axe"));
		registry.register(emeraldHoe.setRegistryName("emerald_hoe"));

		registry.register(flintHelmet.setRegistryName("flint_helmet"));
		registry.register(flintChestplate.setRegistryName("flint_chestplate"));
		registry.register(flintLeggings.setRegistryName("flint_leggings"));
		registry.register(flintBoots.setRegistryName("flint_boots"));
		registry.register(emeraldHelmet.setRegistryName("emerald_helmet"));
		registry.register(emeraldChestplate.setRegistryName("emerald_chestplate"));
		registry.register(emeraldLeggings.setRegistryName("emerald_leggings"));
		registry.register(emeraldBoots.setRegistryName("emerald_boots"));

		registry.register(woodenPaxel.setRegistryName("wooden_paxel"));
		registry.register(stonePaxel.setRegistryName("stone_paxel"));
		registry.register(ironPaxel.setRegistryName("iron_paxel"));
		registry.register(goldenPaxel.setRegistryName("golden_paxel"));
		registry.register(diamondPaxel.setRegistryName("diamond_paxel"));
		registry.register(flintPaxel.setRegistryName("flint_paxel"));
		registry.register(emeraldPaxel.setRegistryName("emerald_paxel"));

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
