package com.blakebr0.pickletweaks.init;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.util.FeatureFlagDisplayItemGenerator;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModFeatureFlags;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModCreativeModeTabs {
    @SubscribeEvent
    public void onRegisterCreativeModeTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(PickleTweaks.MOD_ID, "creative_mode_tab"), (builder) -> {
            var displayItems = FeatureFlagDisplayItemGenerator.create((flagSet, output, hasPermission) -> {
                var stack = ItemStack.EMPTY;

                output.accept(ModBlocks.WHITE_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.ORANGE_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.MAGENTA_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.LIGHT_BLUE_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.YELLOW_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.LIME_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.PINK_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.GRAY_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.LIGHT_GRAY_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.CYAN_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.PURPLE_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.BLUE_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.BROWN_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.GREEN_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.RED_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);
                output.accept(ModBlocks.BLACK_COBBLESTONE, ModFeatureFlags.COLORED_COBBLESTONE);

                output.accept(ModBlocks.SMOOTH_GLOWSTONE, ModFeatureFlags.SMOOTH_GLOWSTONE);

                output.accept(ModItems.COAL_PIECE, ModFeatureFlags.COAL_PIECES);
                output.accept(ModItems.CHARCOAL_PIECE, ModFeatureFlags.COAL_PIECES);
                output.accept(ModItems.DIAMOND_APPLE, ModFeatureFlags.APPLES);
                output.accept(ModItems.EMERALD_APPLE, ModFeatureFlags.APPLES);

                stack = new ItemStack(ModItems.WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                output.accept(stack, ModFeatureFlags.WATERING_CANS);

                stack = new ItemStack(ModItems.REINFORCED_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                output.accept(stack, ModFeatureFlags.WATERING_CANS);

                output.accept(ModItems.GRASS_FIBER, ModFeatureFlags.MESHES);
                output.accept(ModItems.GRASS_FIBER_MESH, ModFeatureFlags.MESHES);
                output.accept(ModItems.MESH, ModFeatureFlags.MESHES);
                output.accept(ModItems.REINFORCED_MESH, ModFeatureFlags.MESHES);
                output.accept(ModItems.MAGNET, ModFeatureFlags.MAGNET);
                output.accept(ModItems.NIGHT_VISION_GOGGLES, ModFeatureFlags.NIGHT_VISION_GOGGLES);
                output.accept(ModItems.REINFORCED_NIGHT_VISION_GOGGLES, ModFeatureFlags.NIGHT_VISION_GOGGLES);

                output.accept(ModItems.FLINT_SWORD, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_PICKAXE, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_SHOVEL, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_AXE, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_HOE, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_SHEARS, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.EMERALD_SWORD, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_PICKAXE, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_SHOVEL, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_AXE, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_HOE, ModFeatureFlags.EMERALD_GEAR);

                output.accept(ModItems.FLINT_HELMET, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_CHESTPLATE, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_LEGGINGS, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.FLINT_BOOTS, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.EMERALD_HELMET, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_CHESTPLATE, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_LEGGINGS, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.EMERALD_BOOTS, ModFeatureFlags.EMERALD_GEAR);

                output.accept(ModItems.WOODEN_PAXEL, ModFeatureFlags.PAXELS);
                output.accept(ModItems.STONE_PAXEL, ModFeatureFlags.PAXELS);
                output.accept(ModItems.IRON_PAXEL, ModFeatureFlags.PAXELS);
                output.accept(ModItems.GOLDEN_PAXEL, ModFeatureFlags.PAXELS);
                output.accept(ModItems.DIAMOND_PAXEL, ModFeatureFlags.PAXELS);
                output.accept(ModItems.FLINT_PAXEL, ModFeatureFlags.PAXELS, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.EMERALD_PAXEL, ModFeatureFlags.PAXELS, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.NETHERITE_PAXEL, ModFeatureFlags.PAXELS);

                output.accept(ModItems.WOODEN_SICKLE, ModFeatureFlags.SICKLES);
                output.accept(ModItems.STONE_SICKLE, ModFeatureFlags.SICKLES);
                output.accept(ModItems.IRON_SICKLE, ModFeatureFlags.SICKLES);
                output.accept(ModItems.GOLDEN_SICKLE, ModFeatureFlags.SICKLES);
                output.accept(ModItems.DIAMOND_SICKLE, ModFeatureFlags.SICKLES);
                output.accept(ModItems.FLINT_SICKLE, ModFeatureFlags.SICKLES, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.EMERALD_SICKLE, ModFeatureFlags.SICKLES, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.NETHERITE_SICKLE, ModFeatureFlags.SICKLES);

                output.accept(ModItems.WOODEN_SCYTHE, ModFeatureFlags.SCYTHES);
                output.accept(ModItems.STONE_SCYTHE, ModFeatureFlags.SCYTHES);
                output.accept(ModItems.IRON_SCYTHE, ModFeatureFlags.SCYTHES);
                output.accept(ModItems.GOLDEN_SCYTHE, ModFeatureFlags.SCYTHES);
                output.accept(ModItems.DIAMOND_SCYTHE, ModFeatureFlags.SCYTHES);
                output.accept(ModItems.FLINT_SCYTHE, ModFeatureFlags.SCYTHES, ModFeatureFlags.FLINT_GEAR);
                output.accept(ModItems.EMERALD_SCYTHE, ModFeatureFlags.SCYTHES, ModFeatureFlags.EMERALD_GEAR);
                output.accept(ModItems.NETHERITE_SCYTHE, ModFeatureFlags.SCYTHES);
            });

            builder.title(Component.translatable("itemGroup.pickletweaks"))
                    .icon(() -> new ItemStack(ModItems.WATERING_CAN.get()))
                    .displayItems(displayItems);
        });
    }
}
