package com.blakebr0.pickletweaks.init;

import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.item.BaseReusableItem;
import com.blakebr0.cucumber.item.tool.BaseAxeItem;
import com.blakebr0.cucumber.item.tool.BaseHoeItem;
import com.blakebr0.cucumber.item.tool.BasePaxelItem;
import com.blakebr0.cucumber.item.tool.BasePickaxeItem;
import com.blakebr0.cucumber.item.tool.BaseScytheItem;
import com.blakebr0.cucumber.item.tool.BaseShearsItem;
import com.blakebr0.cucumber.item.tool.BaseShovelItem;
import com.blakebr0.cucumber.item.tool.BaseSickleItem;
import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.item.CoalPieceItem;
import com.blakebr0.pickletweaks.feature.item.DiamondAppleItem;
import com.blakebr0.pickletweaks.feature.item.EmeraldAppleItem;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import com.blakebr0.pickletweaks.feature.item.WateringCanItem;
import com.blakebr0.pickletweaks.lib.ModArmorMaterial;
import com.blakebr0.pickletweaks.lib.ModItemTier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, PickleTweaks.MOD_ID);

	// register block items here for class load order purposes
	static {
		ModBlocks.BLOCK_ITEMS.forEach(REGISTRY::register);
	}

	public static final RegistryObject<Item> COAL_PIECE = register("coal_piece", CoalPieceItem::new);
	public static final RegistryObject<Item> CHARCOAL_PIECE = register("charcoal_piece", CoalPieceItem::new);
	public static final RegistryObject<Item> DIAMOND_APPLE = register("diamond_apple", DiamondAppleItem::new);
	public static final RegistryObject<Item> EMERALD_APPLE = register("emerald_apple", EmeraldAppleItem::new);
	public static final RegistryObject<Item> WATERING_CAN = register("watering_can", () -> new WateringCanItem(3, 0.25));
	public static final RegistryObject<Item> REINFORCED_WATERING_CAN = register("reinforced_watering_can", () -> new WateringCanItem(7, 0.35));
	public static final RegistryObject<Item> GRASS_FIBER = register("grass_fiber");
	public static final RegistryObject<Item> GRASS_FIBER_MESH = register("grass_fiber_mesh", () -> new BaseReusableItem(20));
	public static final RegistryObject<Item> MESH = register("mesh", () -> new BaseReusableItem(64));
	public static final RegistryObject<Item> REINFORCED_MESH = register("reinforced_mesh", () -> new BaseReusableItem(512));
	public static final RegistryObject<Item> MAGNET = register("magnet", MagnetItem::new);
	public static final RegistryObject<Item> NIGHT_VISION_GOGGLES = register("night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterial.NIGHT_VISION_GOGGLES));
	public static final RegistryObject<Item> REINFORCED_NIGHT_VISION_GOGGLES = register("reinforced_night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterial.REINFORCED_NIGHT_VISION_GOGGLES));

	public static final RegistryObject<Item> FLINT_SWORD = register("flint_sword", () -> new BaseSwordItem(ModItemTier.FLINT));
	public static final RegistryObject<Item> FLINT_PICKAXE = register("flint_pickaxe", () -> new BasePickaxeItem(ModItemTier.FLINT));
	public static final RegistryObject<Item> FLINT_SHOVEL = register("flint_shovel", () -> new BaseShovelItem(ModItemTier.FLINT));
	public static final RegistryObject<Item> FLINT_AXE = register("flint_axe", () -> new BaseAxeItem(ModItemTier.FLINT));
	public static final RegistryObject<Item> FLINT_HOE = register("flint_hoe", () -> new BaseHoeItem(ModItemTier.FLINT));
	public static final RegistryObject<Item> FLINT_SHEARS = register("flint_shears", () -> new BaseShearsItem(p -> p.durability(100)));

	public static final RegistryObject<Item> EMERALD_SWORD = register("emerald_sword", () -> new BaseSwordItem(ModItemTier.EMERALD));
	public static final RegistryObject<Item> EMERALD_PICKAXE = register("emerald_pickaxe", () -> new BasePickaxeItem(ModItemTier.EMERALD));
	public static final RegistryObject<Item> EMERALD_SHOVEL = register("emerald_shovel", () -> new BaseShovelItem(ModItemTier.EMERALD));
	public static final RegistryObject<Item> EMERALD_AXE = register("emerald_axe", () -> new BaseAxeItem(ModItemTier.EMERALD));
	public static final RegistryObject<Item> EMERALD_HOE = register("emerald_hoe", () -> new BaseHoeItem(ModItemTier.EMERALD));

	public static final RegistryObject<Item> FLINT_HELMET = register("flint_helmet", () -> new BaseArmorItem(ModArmorMaterial.FLINT, ArmorItem.Type.HELMET));
	public static final RegistryObject<Item> FLINT_CHESTPLATE = register("flint_chestplate", () -> new BaseArmorItem(ModArmorMaterial.FLINT, ArmorItem.Type.CHESTPLATE));
	public static final RegistryObject<Item> FLINT_LEGGINGS = register("flint_leggings", () -> new BaseArmorItem(ModArmorMaterial.FLINT, ArmorItem.Type.LEGGINGS));
	public static final RegistryObject<Item> FLINT_BOOTS = register("flint_boots", () -> new BaseArmorItem(ModArmorMaterial.FLINT, ArmorItem.Type.BOOTS));
	public static final RegistryObject<Item> EMERALD_HELMET = register("emerald_helmet", () -> new BaseArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.HELMET));
	public static final RegistryObject<Item> EMERALD_CHESTPLATE = register("emerald_chestplate", () -> new BaseArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.CHESTPLATE));
	public static final RegistryObject<Item> EMERALD_LEGGINGS = register("emerald_leggings", () -> new BaseArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.LEGGINGS));
	public static final RegistryObject<Item> EMERALD_BOOTS = register("emerald_boots", () -> new BaseArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.BOOTS));

	public static final RegistryObject<Item> WOODEN_PAXEL = register("wooden_paxel", () -> new BasePaxelItem(Tiers.WOOD));
	public static final RegistryObject<Item> STONE_PAXEL = register("stone_paxel", () -> new BasePaxelItem(Tiers.STONE));
	public static final RegistryObject<Item> IRON_PAXEL = register("iron_paxel", () -> new BasePaxelItem(Tiers.IRON));
	public static final RegistryObject<Item> GOLDEN_PAXEL = register("golden_paxel", () -> new BasePaxelItem(Tiers.GOLD));
	public static final RegistryObject<Item> DIAMOND_PAXEL = register("diamond_paxel", () -> new BasePaxelItem(Tiers.DIAMOND));
	public static final RegistryObject<Item> FLINT_PAXEL = register("flint_paxel", () -> new BasePaxelItem(ModItemTier.FLINT));
	public static final RegistryObject<Item> EMERALD_PAXEL = register("emerald_paxel", () -> new BasePaxelItem(ModItemTier.EMERALD));
	public static final RegistryObject<Item> NETHERITE_PAXEL = register("netherite_paxel", () -> new BasePaxelItem(Tiers.NETHERITE));

	public static final RegistryObject<Item> WOODEN_SICKLE = register("wooden_sickle", () -> new BaseSickleItem(Tiers.WOOD, 1));
	public static final RegistryObject<Item> STONE_SICKLE = register("stone_sickle", () -> new BaseSickleItem(Tiers.STONE, 1));
	public static final RegistryObject<Item> IRON_SICKLE = register("iron_sickle", () -> new BaseSickleItem(Tiers.IRON, 2));
	public static final RegistryObject<Item> GOLDEN_SICKLE = register("golden_sickle", () -> new BaseSickleItem(Tiers.GOLD, 2));
	public static final RegistryObject<Item> DIAMOND_SICKLE = register("diamond_sickle", () -> new BaseSickleItem(Tiers.DIAMOND, 3));
	public static final RegistryObject<Item> FLINT_SICKLE = register("flint_sickle", () -> new BaseSickleItem(ModItemTier.FLINT, 1));
	public static final RegistryObject<Item> EMERALD_SICKLE = register("emerald_sickle", () ->  new BaseSickleItem(ModItemTier.EMERALD, 3));
	public static final RegistryObject<Item> NETHERITE_SICKLE = register("netherite_sickle", () -> new BaseSickleItem(Tiers.NETHERITE, 3));

	public static final RegistryObject<Item> WOODEN_SCYTHE = register("wooden_scythe", () -> new BaseScytheItem(Tiers.WOOD, 1));
	public static final RegistryObject<Item> STONE_SCYTHE = register("stone_scythe", () -> new BaseScytheItem(Tiers.STONE, 1));
	public static final RegistryObject<Item> IRON_SCYTHE = register("iron_scythe", () -> new BaseScytheItem(Tiers.IRON, 2));
	public static final RegistryObject<Item> GOLDEN_SCYTHE = register("golden_scythe", () -> new BaseScytheItem(Tiers.GOLD, 2));
	public static final RegistryObject<Item> DIAMOND_SCYTHE = register("diamond_scythe", () -> new BaseScytheItem(Tiers.DIAMOND, 3));
	public static final RegistryObject<Item> FLINT_SCYTHE = register("flint_scythe", () -> new BaseScytheItem(ModItemTier.FLINT, 1));
	public static final RegistryObject<Item> EMERALD_SCYTHE = register("emerald_scythe", () -> new BaseScytheItem(ModItemTier.EMERALD, 3));
	public static final RegistryObject<Item> NETHERITE_SCYTHE = register("netherite_scythe", () -> new BaseScytheItem(Tiers.NETHERITE, 3));

	private static RegistryObject<Item> register(String name) {
		return register(name, BaseItem::new);
	}

	private static RegistryObject<Item> register(String name, Supplier<? extends Item> item) {
		return REGISTRY.register(name, item);
	}
}
