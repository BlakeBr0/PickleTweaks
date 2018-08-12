package com.blakebr0.pickletweaks.feature.reinforcement;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ReinforcementHandler {

	public static final Map<ItemStack, Integer> REINFORCEMENTS = new HashMap<>();
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (event.getEntityPlayer() == null) return;

		ItemStack stack = event.getItemStack();
		if (!ReinforcementHelper.isReinforceable(stack.getItem())) return;

		ListIterator<String> itr = event.getToolTip().listIterator();
		if (ReinforcementHelper.isReinforced(stack)) {
			while (itr.hasNext()) {
				itr.next();
				itr.add(Utils.localize("tooltip.pt.reinforced", ReinforcementHelper.getReinforcement(stack), ModConfig.confMaxReinforcement));
				break;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemTooltipAsWell(ItemTooltipEvent event) {
		if (event.getEntityPlayer() == null) return;

		ItemStack stack = event.getItemStack();
		if (!ReinforcementHelper.isReinforcement(stack)) return;

		ListIterator<String> itr = event.getToolTip().listIterator();
		while (itr.hasNext()) {
			itr.next();
			itr.add(Utils.localize("tooltip.pt.reinforce", Colors.WHITE + ReinforcementHelper.getReinforcementValue(stack)));
			break;
		}
	}
	
	@SubscribeEvent
	public void onBreakBlock(BlockEvent.BreakEvent event) {
		if (event.getPlayer() == null) return;
		
		ItemStack stack = event.getPlayer().getHeldItemMainhand();
		if (stack.isEmpty()) return;
		if (!blockBreakTool(stack.getItem())) return;
		if (!isBlockHardEnough(event.getState(), event.getWorld(), event.getPos())) return;
		
		if (ReinforcementHelper.isReinforced(stack) && stack.isItemDamaged()) {
			int extra = ReinforcementHelper.useReinforcement(stack, stack.getItem() instanceof ItemSword ? 2 : 1);
			stack.setItemDamage(ReinforcementHelper.getDurability(stack) + extra);
		}
	}
	
	@SubscribeEvent
	public void onHitEntity(LivingHurtEvent event) {
		DamageSource source = event.getSource();
		
		if (!source.getDamageType().equals("player")) return;
		if (!(source.getTrueSource() instanceof EntityPlayer)) return;

		EntityPlayer player = (EntityPlayer) source.getTrueSource();
		ItemStack stack = player.getHeldItemMainhand();

		if (stack.isEmpty()) return;
		if (!hitEntityTool(stack.getItem())) return;

		if (ReinforcementHelper.isReinforced(stack) && stack.isItemDamaged()) {
			int extra = ReinforcementHelper.useReinforcement(stack, stack.getItem() instanceof ItemTool ? 2 : 1);
			stack.setItemDamage(ReinforcementHelper.getDurability(stack) + extra);
		}
	}
	
	//@SubscribeEvent // TODO: fix this
	public void onUseHoe(UseHoeEvent event) {
		if (event.getEntityPlayer() == null) return;

		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		if (stack.isEmpty()) return;
		if (!(stack.getItem() instanceof ItemHoe)) return;

		if (ReinforcementHelper.isReinforced(stack) && stack.isItemDamaged()) {
			int extra = ReinforcementHelper.useReinforcement(stack, 1);
			stack.setItemDamage(ReinforcementHelper.getDurability(stack) + extra);
		}
	}
	
	//@SubscribeEvent // TODO: fix this
	public void onUseBow(RightClickItem event) {
		if (event.getEntityPlayer() == null) return;

		ItemStack stack = event.getItemStack();
		if (stack.isEmpty()) return;
		if (!(stack.getItem() instanceof ItemBow)) return;

		if (ReinforcementHelper.isReinforced(stack) && stack.isItemDamaged()) {
			int extra = ReinforcementHelper.useReinforcement(stack, 1);
			stack.setItemDamage(ReinforcementHelper.getDurability(stack) + extra);
		}
	}
	
	private boolean isBlockHardEnough(IBlockState state, World world, BlockPos pos) {
		return state.getBlockHardness(world, pos) != 0;
	}
	
	private boolean isHoeWorking(World world, BlockPos pos,  Event.Result result) {
		if (result == Event.Result.ALLOW) return true;		
		Block block = world.getBlockState(pos).getBlock();
		return block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.GRASS_PATH;
	}
	
	private boolean blockBreakTool(Item tool) {
		return tool instanceof ItemTool || tool instanceof ItemSword;
	}
	
	private boolean hitEntityTool(Item tool) {
		return tool instanceof ItemTool || tool instanceof ItemSword || tool instanceof ItemHoe;
	}
}
