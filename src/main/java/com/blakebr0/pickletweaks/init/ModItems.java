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
import com.blakebr0.pickletweaks.feature.item.WateringCanItem;
import com.blakebr0.pickletweaks.lib.ModArmorMaterials;
import com.blakebr0.pickletweaks.lib.ModToolMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, PickleTweaks.MOD_ID);

	// register block items here for class load order purposes
	static {
		ModBlocks.BLOCK_ITEMS.forEach(REGISTRY::register);
	}

	public static final DeferredHolder<Item, Item> COAL_PIECE = REGISTRY.register("coal_piece", CoalPieceItem::new);
	public static final DeferredHolder<Item, Item> CHARCOAL_PIECE = REGISTRY.register("charcoal_piece", CoalPieceItem::new);
	public static final DeferredHolder<Item, Item> DIAMOND_APPLE = REGISTRY.register("diamond_apple", DiamondAppleItem::new);
	public static final DeferredHolder<Item, Item> EMERALD_APPLE = REGISTRY.register("emerald_apple", EmeraldAppleItem::new);
	public static final DeferredHolder<Item, Item> WATERING_CAN = REGISTRY.register("watering_can", id -> new WateringCanItem(id, 3, 0.25));
	public static final DeferredHolder<Item, Item> REINFORCED_WATERING_CAN = REGISTRY.register("reinforced_watering_can", id -> new WateringCanItem(id, 7, 0.35));
	public static final DeferredHolder<Item, Item> GRASS_FIBER = REGISTRY.register("grass_fiber", BaseItem::new);
	public static final DeferredHolder<Item, Item> GRASS_FIBER_MESH = REGISTRY.register("grass_fiber_mesh", id -> new BaseReusableItem(id, 20));
	public static final DeferredHolder<Item, Item> MESH = REGISTRY.register("mesh", id -> new BaseReusableItem(id, 64));
	public static final DeferredHolder<Item, Item> REINFORCED_MESH = REGISTRY.register("reinforced_mesh", id -> new BaseReusableItem(id, 512));
	public static final DeferredHolder<Item, Item> MAGNET = REGISTRY.register("magnet", MagnetItem::new);
	public static final DeferredHolder<Item, Item> NIGHT_VISION_GOGGLES = REGISTRY.register("night_vision_goggles", id -> new BaseArmorItem(id, ModArmorMaterials.NIGHT_VISION_GOGGLES, ArmorType.HELMET));
	public static final DeferredHolder<Item, Item> REINFORCED_NIGHT_VISION_GOGGLES = REGISTRY.register("reinforced_night_vision_goggles", id -> new BaseArmorItem(id, ModArmorMaterials.REINFORCED_NIGHT_VISION_GOGGLES, ArmorType.HELMET));

	public static final DeferredHolder<Item, Item> FLINT_SWORD = REGISTRY.register("flint_sword", id -> new BaseSwordItem(id, ModToolMaterials.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_PICKAXE = REGISTRY.register("flint_pickaxe", id -> new BasePickaxeItem(id, ModToolMaterials.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_SHOVEL = REGISTRY.register("flint_shovel", id -> new BaseShovelItem(id, ModToolMaterials.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_AXE = REGISTRY.register("flint_axe", id -> new BaseAxeItem(id, ModToolMaterials.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_HOE = REGISTRY.register("flint_hoe", id -> new BaseHoeItem(id, ModToolMaterials.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_SHEARS = REGISTRY.register("flint_shears", id -> new BaseShearsItem(id, p -> p.durability(100)));

	public static final DeferredHolder<Item, Item> EMERALD_SWORD = REGISTRY.register("emerald_sword", id -> new BaseSwordItem(id, ModToolMaterials.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_PICKAXE = REGISTRY.register("emerald_pickaxe", id -> new BasePickaxeItem(id, ModToolMaterials.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_SHOVEL = REGISTRY.register("emerald_shovel", id -> new BaseShovelItem(id, ModToolMaterials.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_AXE = REGISTRY.register("emerald_axe", id -> new BaseAxeItem(id, ModToolMaterials.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_HOE = REGISTRY.register("emerald_hoe", id -> new BaseHoeItem(id, ModToolMaterials.EMERALD));

	public static final DeferredHolder<Item, Item> FLINT_HELMET = REGISTRY.register("flint_helmet", id -> new BaseArmorItem(id, ModArmorMaterials.FLINT, ArmorType.HELMET));
	public static final DeferredHolder<Item, Item> FLINT_CHESTPLATE = REGISTRY.register("flint_chestplate", id -> new BaseArmorItem(id, ModArmorMaterials.FLINT, ArmorType.CHESTPLATE));
	public static final DeferredHolder<Item, Item> FLINT_LEGGINGS = REGISTRY.register("flint_leggings", id -> new BaseArmorItem(id, ModArmorMaterials.FLINT, ArmorType.LEGGINGS));
	public static final DeferredHolder<Item, Item> FLINT_BOOTS = REGISTRY.register("flint_boots", id -> new BaseArmorItem(id, ModArmorMaterials.FLINT, ArmorType.BOOTS));
	public static final DeferredHolder<Item, Item> EMERALD_HELMET = REGISTRY.register("emerald_helmet", id -> new BaseArmorItem(id, ModArmorMaterials.EMERALD, ArmorType.HELMET));
	public static final DeferredHolder<Item, Item> EMERALD_CHESTPLATE = REGISTRY.register("emerald_chestplate", id -> new BaseArmorItem(id, ModArmorMaterials.EMERALD, ArmorType.CHESTPLATE));
	public static final DeferredHolder<Item, Item> EMERALD_LEGGINGS = REGISTRY.register("emerald_leggings", id -> new BaseArmorItem(id, ModArmorMaterials.EMERALD, ArmorType.LEGGINGS));
	public static final DeferredHolder<Item, Item> EMERALD_BOOTS = REGISTRY.register("emerald_boots", id -> new BaseArmorItem(id, ModArmorMaterials.EMERALD, ArmorType.BOOTS));

	public static final DeferredHolder<Item, Item> WOODEN_PAXEL = REGISTRY.register("wooden_paxel", id -> new BasePaxelItem(id, ToolMaterial.WOOD));
	public static final DeferredHolder<Item, Item> STONE_PAXEL = REGISTRY.register("stone_paxel", id -> new BasePaxelItem(id, ToolMaterial.STONE));
	public static final DeferredHolder<Item, Item> IRON_PAXEL = REGISTRY.register("iron_paxel", id -> new BasePaxelItem(id, ToolMaterial.IRON));
	public static final DeferredHolder<Item, Item> GOLDEN_PAXEL = REGISTRY.register("golden_paxel", id -> new BasePaxelItem(id, ToolMaterial.GOLD));
	public static final DeferredHolder<Item, Item> DIAMOND_PAXEL = REGISTRY.register("diamond_paxel", id -> new BasePaxelItem(id, ToolMaterial.DIAMOND));
	public static final DeferredHolder<Item, Item> FLINT_PAXEL = REGISTRY.register("flint_paxel", id -> new BasePaxelItem(id, ModToolMaterials.FLINT));
	public static final DeferredHolder<Item, Item> EMERALD_PAXEL = REGISTRY.register("emerald_paxel", id -> new BasePaxelItem(id, ModToolMaterials.EMERALD));
	public static final DeferredHolder<Item, Item> NETHERITE_PAXEL = REGISTRY.register("netherite_paxel", id -> new BasePaxelItem(id, ToolMaterial.NETHERITE));

	public static final DeferredHolder<Item, Item> WOODEN_SICKLE = REGISTRY.register("wooden_sickle", id -> new BaseSickleItem(id, ToolMaterial.WOOD, 1));
	public static final DeferredHolder<Item, Item> STONE_SICKLE = REGISTRY.register("stone_sickle", id -> new BaseSickleItem(id, ToolMaterial.STONE, 1));
	public static final DeferredHolder<Item, Item> IRON_SICKLE = REGISTRY.register("iron_sickle", id -> new BaseSickleItem(id, ToolMaterial.IRON, 2));
	public static final DeferredHolder<Item, Item> GOLDEN_SICKLE = REGISTRY.register("golden_sickle", id -> new BaseSickleItem(id, ToolMaterial.GOLD, 2));
	public static final DeferredHolder<Item, Item> DIAMOND_SICKLE = REGISTRY.register("diamond_sickle", id -> new BaseSickleItem(id, ToolMaterial.DIAMOND, 3));
	public static final DeferredHolder<Item, Item> FLINT_SICKLE = REGISTRY.register("flint_sickle", id -> new BaseSickleItem(id, ModToolMaterials.FLINT, 1));
	public static final DeferredHolder<Item, Item> EMERALD_SICKLE = REGISTRY.register("emerald_sickle", id ->  new BaseSickleItem(id, ModToolMaterials.EMERALD, 3));
	public static final DeferredHolder<Item, Item> NETHERITE_SICKLE = REGISTRY.register("netherite_sickle", id -> new BaseSickleItem(id, ToolMaterial.NETHERITE, 3));

	public static final DeferredHolder<Item, Item> WOODEN_SCYTHE = REGISTRY.register("wooden_scythe", id -> new BaseScytheItem(id, ToolMaterial.WOOD, 1));
	public static final DeferredHolder<Item, Item> STONE_SCYTHE = REGISTRY.register("stone_scythe", id -> new BaseScytheItem(id, ToolMaterial.STONE, 1));
	public static final DeferredHolder<Item, Item> IRON_SCYTHE = REGISTRY.register("iron_scythe", id -> new BaseScytheItem(id, ToolMaterial.IRON, 2));
	public static final DeferredHolder<Item, Item> GOLDEN_SCYTHE = REGISTRY.register("golden_scythe", id -> new BaseScytheItem(id, ToolMaterial.GOLD, 2));
	public static final DeferredHolder<Item, Item> DIAMOND_SCYTHE = REGISTRY.register("diamond_scythe", id -> new BaseScytheItem(id, ToolMaterial.DIAMOND, 3));
	public static final DeferredHolder<Item, Item> FLINT_SCYTHE = REGISTRY.register("flint_scythe", id -> new BaseScytheItem(id, ModToolMaterials.FLINT, 1));
	public static final DeferredHolder<Item, Item> EMERALD_SCYTHE = REGISTRY.register("emerald_scythe", id -> new BaseScytheItem(id, ModToolMaterials.EMERALD, 3));
	public static final DeferredHolder<Item, Item> NETHERITE_SCYTHE = REGISTRY.register("netherite_scythe", id -> new BaseScytheItem(id, ToolMaterial.NETHERITE, 3));
}
