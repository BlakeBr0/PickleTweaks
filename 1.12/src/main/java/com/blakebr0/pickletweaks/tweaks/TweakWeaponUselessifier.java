package com.blakebr0.pickletweaks.tweaks;

import java.util.ListIterator;

import com.blakebr0.pickletweaks.tweaks.tools.ToolTweaks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
// TODO: work on this
public class TweakWeaponUselessifier {
    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed event){
        if(event.getEntityPlayer() == null){
            return;
        }

        ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
        if(stack == null){
            return;
        }

        if(isUselessTool(stack.getItem())){
            event.setNewSpeed(0);
        }
    }
	
    @SubscribeEvent
    public void onHurt(LivingHurtEvent event){
        if(!(event.getSource().damageType.equals("player"))){
            return;
        }

        if(!(event.getSource().getTrueSource() instanceof EntityPlayer)){
            return;
        }
        
        EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

        ItemStack stack = player.getHeldItemMainhand();
        if(stack == null){
            return;
        }
        
        if(isUselessTool(stack.getItem())){
            event.setCanceled(true);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onItemToolTip(ItemTooltipEvent event) {
        if(event.getEntityPlayer() == null){
            return;
        }

        if(!ToolTweaks.uselessWeapons.contains(event.getItemStack().getItem())){
        	return;
        }
        
        ListIterator<String> tooltip = event.getToolTip().listIterator();
        while(tooltip.hasNext()){
            if(tooltip.next().contains(I18n.translateToLocal("attribute.name.generic.attackDamage"))){
                tooltip.set(" 0 " + new TextComponentTranslation("attribute.name.generic.attackDamage").getFormattedText());
            }
        }
        
        if(isUselessTool(event.getItemStack().getItem())) {
            event.getToolTip().add(TextFormatting.RED + new TextComponentTranslation("tooltip.useless_weapon_1").getFormattedText());
            event.getToolTip().add(TextFormatting.RED + new TextComponentTranslation("tooltip.useless_tool_2").getFormattedText());
        }
    }
    
    @SubscribeEvent
    public void onRightClickBlock(RightClickBlock event){
    	if(event.getEntityPlayer() == null){
    		return;
    	}
    	
    	ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
    	if(stack == null){
    		return;
    	}
    	
    	if(isUselessTool(stack.getItem())){
    		event.setCanceled(true);
    	}
    }

    public static boolean isUselessTool(Item item){
        if(item == null){
            return false;
        }

        if(!ToolTweaks.uselessWeapons.contains(item)){
        	return false;
        }
        
        return true;
    }
}
