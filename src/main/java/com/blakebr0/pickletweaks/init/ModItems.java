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
import com.blakebr0.pickletweaks.feature.item.ScytheItem;
import com.blakebr0.pickletweaks.feature.item.SickleItem;
import com.blakebr0.pickletweaks.feature.item.WateringCanItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldAxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldHoeItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldPaxelItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldPickaxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldScytheItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldShovelItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldSickleItem;
import com.blakebr0.pickletweaks.feature.item.tool.EmeraldSwordItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintAxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintHoeItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintPaxelItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintPickaxeItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintScytheItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintShearsItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintShovelItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintSickleItem;
import com.blakebr0.pickletweaks.feature.item.tool.FlintSwordItem;
import com.blakebr0.pickletweaks.lib.ModArmorMaterial;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.blakebr0.pickletweaks.PickleTweaks.CREATIVE_TAB;

public final class ModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Item.class, PickleTweaks.MOD_ID);

	public static final RegistryObject<Item> COAL_PIECE = register("coal_piece", () -> new CoalPieceItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> CHARCOAL_PIECE = register("charcoal_piece", () -> new CoalPieceItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> DIAMOND_APPLE = register("diamond_apple", () -> new DiamondAppleItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_APPLE = register("emerald_apple", () -> new EmeraldAppleItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> WATERING_CAN = register("watering_can", () -> new WateringCanItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> REINFORCED_WATERING_CAN = register("reinforced_watering_can", () -> new WateringCanItem(7, 0.35, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> GRASS_FIBER = register("grass_fiber", () -> new GrassFiberItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> GRASS_FIBER_MESH = register("grass_fiber_mesh", () -> new MeshItem(20, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> MESH = register("mesh", () -> new MeshItem(64, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> REINFORCED_MESH = register("reinforced_mesh", () -> new MeshItem(512, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> MAGNET = register("magnet", () -> new MagnetItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> NIGHT_VISION_GOGGLES = register("night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterial.NIGHT_VISION_GOGGLES, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> REINFORCED_NIGHT_VISION_GOGGLES = register("reinforced_night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterial.REINFORCED_NIGHT_VISION_GOGGLES, p -> p.tab(CREATIVE_TAB)));

	public static final RegistryObject<Item> FLINT_SWORD = register("flint_sword", () -> new FlintSwordItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_PICKAXE = register("flint_pickaxe", () -> new FlintPickaxeItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_SHOVEL = register("flint_shovel", () -> new FlintShovelItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_AXE = register("flint_axe", () -> new FlintAxeItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_HOE = register("flint_hoe", () -> new FlintHoeItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_SHEARS = register("flint_shears", () -> new FlintShearsItem(p -> p.tab(CREATIVE_TAB)));

	public static final RegistryObject<Item> EMERALD_SWORD = register("emerald_sword", () -> new EmeraldSwordItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_PICKAXE = register("emerald_pickaxe", () -> new EmeraldPickaxeItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_SHOVEL = register("emerald_shovel", () -> new EmeraldShovelItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_AXE = register("emerald_axe", () -> new EmeraldAxeItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_HOE = register("emerald_hoe", () -> new EmeraldHoeItem(p -> p.tab(CREATIVE_TAB)));

	public static final RegistryObject<Item> FLINT_HELMET = register("flint_helmet", () -> new FlintArmorItem(EquipmentSlot.HEAD, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_CHESTPLATE = register("flint_chestplate", () -> new FlintArmorItem(EquipmentSlot.CHEST, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_LEGGINGS = register("flint_leggings", () -> new FlintArmorItem(EquipmentSlot.LEGS, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_BOOTS = register("flint_boots", () -> new FlintArmorItem(EquipmentSlot.FEET, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_HELMET = register("emerald_helmet", () -> new EmeraldArmorItem(EquipmentSlot.HEAD, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_CHESTPLATE = register("emerald_chestplate", () -> new EmeraldArmorItem(EquipmentSlot.CHEST, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_LEGGINGS = register("emerald_leggings", () -> new EmeraldArmorItem(EquipmentSlot.LEGS, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_BOOTS = register("emerald_boots", () -> new EmeraldArmorItem(EquipmentSlot.FEET, p -> p.tab(CREATIVE_TAB)));

	public static final RegistryObject<Item> WOODEN_PAXEL = register("wooden_paxel", () -> new PaxelItem(Tiers.WOOD, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> STONE_PAXEL = register("stone_paxel", () -> new PaxelItem(Tiers.STONE, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> IRON_PAXEL = register("iron_paxel", () -> new PaxelItem(Tiers.IRON, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> GOLDEN_PAXEL = register("golden_paxel", () -> new PaxelItem(Tiers.GOLD, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> DIAMOND_PAXEL = register("diamond_paxel", () -> new PaxelItem(Tiers.DIAMOND, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_PAXEL = register("flint_paxel", () -> new FlintPaxelItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_PAXEL = register("emerald_paxel", () -> new EmeraldPaxelItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> NETHERITE_PAXEL = register("netherite_paxel", () -> new PaxelItem(Tiers.NETHERITE, p -> p.tab(CREATIVE_TAB)));

	public static final RegistryObject<Item> WOODEN_SICKLE = register("wooden_sickle", () -> new SickleItem(Tiers.WOOD, 1, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> STONE_SICKLE = register("stone_sickle", () -> new SickleItem(Tiers.STONE, 1, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> IRON_SICKLE = register("iron_sickle", () -> new SickleItem(Tiers.IRON, 2, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> GOLDEN_SICKLE = register("golden_sickle", () -> new SickleItem(Tiers.GOLD, 2, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> DIAMOND_SICKLE = register("diamond_sickle", () -> new SickleItem(Tiers.DIAMOND, 3, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_SICKLE = register("flint_sickle", () -> new FlintSickleItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_SICKLE = register("emerald_sickle", () ->  new EmeraldSickleItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> NETHERITE_SICKLE = register("netherite_sickle", () -> new SickleItem(Tiers.NETHERITE, 3, p -> p.tab(CREATIVE_TAB)));

	public static final RegistryObject<Item> WOODEN_SCYTHE = register("wooden_scythe", () -> new ScytheItem(Tiers.WOOD, 1, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> STONE_SCYTHE = register("stone_scythe", () -> new ScytheItem(Tiers.STONE, 1, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> IRON_SCYTHE = register("iron_scythe", () -> new ScytheItem(Tiers.IRON, 2, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> GOLDEN_SCYTHE = register("golden_scythe", () -> new ScytheItem(Tiers.GOLD, 2, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> DIAMOND_SCYTHE = register("diamond_scythe", () -> new ScytheItem(Tiers.DIAMOND, 3, p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> FLINT_SCYTHE = register("flint_scythe", () -> new FlintScytheItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_SCYTHE = register("emerald_scythe", () -> new EmeraldScytheItem(p -> p.tab(CREATIVE_TAB)));
	public static final RegistryObject<Item> NETHERITE_SCYTHE = register("netherite_scythe", () -> new ScytheItem(Tiers.NETHERITE, 3, p -> p.tab(CREATIVE_TAB)));

	private static RegistryObject<Item> register(String name) {
		return register(name, () -> new BaseItem(p -> p.tab(CREATIVE_TAB)));
	}

	private static RegistryObject<Item> register(String name, Supplier<? extends Item> item) {
		return REGISTRY.register(name, item);
	}
}
