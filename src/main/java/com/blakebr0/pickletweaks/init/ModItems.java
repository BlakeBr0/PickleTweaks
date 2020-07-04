package com.blakebr0.pickletweaks.init;

import com.blakebr0.cucumber.item.BaseItem;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.blakebr0.pickletweaks.PickleTweaks.ITEM_GROUP;

public final class ModItems {
	public static final List<Supplier<Item>> BLOCK_ENTRIES = new ArrayList<>();
	public static final Map<RegistryObject<Item>, Supplier<Item>> ENTRIES = new LinkedHashMap<>();

	public static final RegistryObject<Item> COAL_PIECE = register("coal_piece", () -> new CoalPieceItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> CHARCOAL_PIECE = register("charcoal_piece", () -> new CoalPieceItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> DIAMOND_APPLE = register("diamond_apple", () -> new DiamondAppleItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_APPLE = register("emerald_apple", () -> new EmeraldAppleItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> WATERING_CAN = register("watering_can", () -> new WateringCanItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> GRASS_FIBER = register("grass_fiber", () -> new GrassFiberItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> GRASS_FIBER_MESH = register("grass_fiber_mesh", () -> new MeshItem(20, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> MESH = register("mesh", () -> new MeshItem(64, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> REINFORCED_MESH = register("reinforced_mesh", () -> new MeshItem(512, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> MAGNET = register("magnet", () -> new MagnetItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> NIGHT_VISION_GOGGLES = register("night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterial.NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> REINFORCED_NIGHT_VISION_GOGGLES = register("reinforced_night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterial.REINFORCED_NIGHT_VISION_GOGGLES, p -> p.group(ITEM_GROUP)));

	public static final RegistryObject<Item> FLINT_SWORD = register("flint_sword", () -> new FlintSwordItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_PICKAXE = register("flint_pickaxe", () -> new FlintPickaxeItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_SHOVEL = register("flint_shovel", () -> new FlintShovelItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_AXE = register("flint_axe", () -> new FlintAxeItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_HOE = register("flint_hoe", () -> new FlintHoeItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_SHEARS = register("flint_shears", () -> new FlintShearsItem(p -> p.group(ITEM_GROUP)));

	public static final RegistryObject<Item> EMERALD_SWORD = register("emerald_sword", () -> new EmeraldSwordItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_PICKAXE = register("emerald_pickaxe", () -> new EmeraldPickaxeItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_SHOVEL = register("emerald_shovel", () -> new EmeraldShovelItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_AXE = register("emerald_axe", () -> new EmeraldAxeItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_HOE = register("emerald_hoe", () -> new EmeraldHoeItem(p -> p.group(ITEM_GROUP)));

	public static final RegistryObject<Item> FLINT_HELMET = register("flint_helmet", () -> new FlintArmorItem(EquipmentSlotType.HEAD, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_CHESTPLATE = register("flint_chestplate", () -> new FlintArmorItem(EquipmentSlotType.CHEST, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_LEGGINGS = register("flint_leggings", () -> new FlintArmorItem(EquipmentSlotType.LEGS, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_BOOTS = register("flint_boots", () -> new FlintArmorItem(EquipmentSlotType.FEET, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_HELMET = register("emerald_helmet", () -> new EmeraldArmorItem(EquipmentSlotType.HEAD, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_CHESTPLATE = register("emerald_chestplate", () -> new EmeraldArmorItem(EquipmentSlotType.CHEST, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_LEGGINGS = register("emerald_leggings", () -> new EmeraldArmorItem(EquipmentSlotType.LEGS, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_BOOTS = register("emerald_boots", () -> new EmeraldArmorItem(EquipmentSlotType.FEET, p -> p.group(ITEM_GROUP)));

	public static final RegistryObject<Item> WOODEN_PAXEL = register("wooden_paxel", () -> new PaxelItem(ItemTier.WOOD, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> STONE_PAXEL = register("stone_paxel", () -> new PaxelItem(ItemTier.STONE, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> IRON_PAXEL = register("iron_paxel", () -> new PaxelItem(ItemTier.IRON, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> GOLDEN_PAXEL = register("golden_paxel", () -> new PaxelItem(ItemTier.GOLD, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> DIAMOND_PAXEL = register("diamond_paxel", () -> new PaxelItem(ItemTier.DIAMOND, p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINT_PAXEL = register("flint_paxel", () -> new FlintPaxelItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> EMERALD_PAXEL = register("emerald_paxel", () -> new EmeraldPaxelItem(p -> p.group(ITEM_GROUP)));
	public static final RegistryObject<Item> NETHERITE_PAXEL = register("netherite_paxel", () -> new PaxelItem(ItemTier.NETHERITE, p -> p.group(ITEM_GROUP)));

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

		BLOCK_ENTRIES.stream().map(Supplier::get).forEach(registry::register);
		ENTRIES.forEach((reg, item) -> {
			registry.register(item.get());
			reg.updateReference(registry);
		});
	}

	private static RegistryObject<Item> register(String name) {
		return register(name, () -> new BaseItem(p -> p.group(ITEM_GROUP)));
	}

	private static RegistryObject<Item> register(String name, Supplier<? extends Item> item) {
		ResourceLocation loc = new ResourceLocation(PickleTweaks.MOD_ID, name);
		RegistryObject<Item> reg = RegistryObject.of(loc, ForgeRegistries.ITEMS);
		ENTRIES.put(reg, () -> item.get().setRegistryName(loc));
		return reg;
	}
}
