package com.blakebr0.pickletweaks.feature.item;

import java.util.List;
import java.util.Random;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWateringCan extends ItemBase implements IEnableable {

	private boolean water = false;
	private long ticks;

	public ItemWateringCan() {
		super("pt.watering_can");
		this.setCreativeTab(PickleTweaks.tab);
		this.setMaxStackSize(1);
	}

	public ItemStack initTags(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
			tag.setBoolean("Water", false);
		}

		return stack;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			items.add(initTags(new ItemStack(this, 1, 0)));
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (selected) {
			ticks++;
			if (ticks % 5 == 0) {
				water = true;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		RayTraceResult raytraceresult = this.rayTrace(world, player, true);
		if (raytraceresult == null) {
			return new ActionResult(EnumActionResult.FAIL, stack);
		}

		if (world.getBlockState(raytraceresult.getBlockPos()).getMaterial() == Material.WATER && ((Integer) world.getBlockState(raytraceresult.getBlockPos()).getValue(BlockLiquid.LEVEL)).intValue() == 0) {
			initTags(stack);
			stack.getTagCompound().setBoolean("Water", true);
			return new ActionResult(EnumActionResult.FAIL, stack);
		}
		return new ActionResult(EnumActionResult.FAIL, stack);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		}

		if (stack.getTagCompound() == null) {
			return EnumActionResult.FAIL;
		}

		if (!stack.getTagCompound().getBoolean("Water")) {
			return EnumActionResult.FAIL;
		}

		Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1));
		for (BlockPos aoePos : blocks) {
			IBlockState aoeState = world.getBlockState(aoePos);
			if (aoeState.getBlock() instanceof BlockFarmland) {
				int moisture = ((Integer) aoeState.getValue(BlockFarmland.MOISTURE)).intValue();
				if (moisture < 7) {
					world.setBlockState(aoePos, aoeState.withProperty(BlockFarmland.MOISTURE, Integer.valueOf(7)), 2);
				}
			}
		}

		Random rand = new Random();
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				double d0 = pos.add(x, 0, z).getX() + rand.nextFloat();
				double d1 = pos.add(x, 0, z).getY() + 1.0D;
				double d2 = pos.add(x, 0, z).getZ() + rand.nextFloat();

				IBlockState state = world.getBlockState(pos);
				if ((state.isFullCube()) || ((state.getBlock() instanceof BlockFarmland))) {
					d1 += 0.3D;
				}
				world.spawnParticle(EnumParticleTypes.WATER_DROP, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[5]);
			}
		}

		if (!world.isRemote && water) {
			water = false;
			int chance = Utils.randInt(1, 100);
			if (chance <= 25) {
				for (BlockPos aoePos : blocks) {
					Block plant = world.getBlockState(aoePos).getBlock();
					if (plant instanceof IGrowable || plant instanceof IPlantable || plant == Blocks.MYCELIUM || plant == Blocks.CHORUS_FLOWER) {
						world.scheduleBlockUpdate(aoePos, plant, 0, 1);
					}
				}
				return EnumActionResult.FAIL;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		initTags(stack);
		if (stack.getTagCompound().getBoolean("Water")) {
			tooltip.add(Utils.localize("tooltip.pt.filled"));
		} else {
			tooltip.add(Utils.localize("tooltip.pt.empty"));
		}
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confWateringCan;
	}
}
