package com.blakebr0.pickletweaks.feature;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureToolInfo {
	
	public static final String[] DEFAULT_VALUES = new String[]{ "0=Wood", "1=Stone", "2=Iron", "3=Diamond" };
	public static Map<Integer, String> names = new HashMap<Integer, String>();
	
	public static void configure(Configuration config){
		ConfigCategory category = config.getCategory("features");
		String[] values = config.get(category.getName(), "mining_level_names", DEFAULT_VALUES).getStringList();
		category.get("mining_level_names").setComment("Here you can define custom names for the mining levels displayed in the 'tool_info_tooltip'."
				+ "\n- Syntax: level=name"
				+ "\n- 'name' should be exactly how you want it to appear ingame."
				+ "\n- Example: 0=Literal Trash"
				+ "\n- Levels not defined here will show the numerical value.");
		
		for(String value : values){
			String[] parts = value.split("=");
			
			if(parts.length != 2){
				continue;
			}
			
			int level;
			String name = parts[1];
			
			try {
				level = Integer.valueOf(parts[0]);
			} catch(NumberFormatException e){
				continue;
			}
			
			names.put(level, name);
		}
	}
	
	@SubscribeEvent
	public void onBlockBroken(BreakEvent event){
		ItemStack stack = event.getPlayer().getHeldItemMainhand();
		if(!StackHelper.isNull(stack) && stack.getItem() != null){
			if(stack.getItem() instanceof ItemTool){
				NBTTagCompound tag = stack.getOrCreateSubCompound(PickleTweaks.MOD_ID);
				if(!((tag.getInteger("BlocksBroken") + 1) >= Integer.MAX_VALUE)){
					tag.setInteger("BlocksBroken", tag.getInteger("BlocksBroken") + 1);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addToolInfoTooltip(ItemTooltipEvent event){
        if(event.getEntityPlayer() == null){
            return;
        }
        
        ItemStack stack = event.getItemStack();
        ListIterator<String> tooltip = event.getToolTip().listIterator();
        
        if(stack.getItem() instanceof ItemTool){
            ItemTool tool = (ItemTool)stack.getItem();
            boolean shift = false;
            if(Keyboard.isCreated()){
                shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
            }
            
            while(tooltip.hasNext()){
            	tooltip.next();
            	if(shift){
            		String toolClass = tool.getToolClasses(stack).toArray().length > 0 ? tool.getToolClasses(stack).toArray()[0].toString() : "";
            		tooltip.add(Utils.localize("tooltip.pt.mining_level") + " " + getMiningLevelName(stack, toolClass, event.getEntityPlayer()));
            		tooltip.add(Utils.localize("tooltip.pt.mining_speed") + " " + getMiningSpeed(tool));
            		tooltip.add(Utils.localize("tooltip.pt.durability") + " " + getDurability(stack));
            		tooltip.add(Utils.localize("tooltip.pt.blocks_broken") + " " + getBlocksBroken(stack));
            	} else {
            		tooltip.add(Utils.localize("tooltip.pt.hold_shift_for_info"));
            	}
            	break;
            }
        }
	}
	
	public String getMiningLevelName(ItemStack stack, String toolClass, EntityPlayer player){
		ItemTool tool = (ItemTool)stack.getItem();
		int level;
		if(toolClass.equals("")){
			if(getToolMaterial(tool) != null){
				level = getToolMaterial(tool).getHarvestLevel();
				if(names.containsKey(level)){
					return names.get(level);
				}
			}
			return "?";
		}
		level = tool.getHarvestLevel(stack, toolClass, player, null);
		if(names.containsKey(level)){
			return names.get(level);
		}
		return level + "";
	}
	
	public ToolMaterial getToolMaterial(ItemTool item){
		String materialName = null;
		for(ToolMaterial mat : ToolMaterial.values()){
			// Because CoFH messes with the tool material name getter, we have to special case it.
			if(ForgeRegistries.ITEMS.getKey(item).getResourceDomain().equals("thermalfoundation")){
				if(mat.name().equals("TF:" + item.getToolMaterialName())){
					materialName = mat.name();
					break;
				}
			}
			if(mat.toString() == item.getToolMaterialName()){
				materialName = mat.name();
				break;
			}
		}
		if(materialName == null){
			return null;
		}
		return ToolMaterial.valueOf(materialName);
	}
	
	public float getMiningSpeed(ItemTool item){
		ToolMaterial mat = getToolMaterial(item);
		if(mat == null){
			return -1;
		}
		return mat.getEfficiencyOnProperMaterial();
	}
	
	public String getDurability(ItemStack stack){
		int durability = stack.getMaxDamage() - stack.getItemDamage();
		return durability + Colors.GRAY + "/" + Colors.WHITE + stack.getMaxDamage();
	}
	
	public int getBlocksBroken(ItemStack stack){
		NBTTagCompound tag = stack.getOrCreateSubCompound(PickleTweaks.MOD_ID);
		if(tag.hasKey("BlocksBroken")){
			if((tag.getInteger("BlocksBroken") + 1) >= Integer.MAX_VALUE){
				return Integer.MAX_VALUE;
			}
			return tag.getInteger("BlocksBroken");
		}
		return 0;
	}
}
