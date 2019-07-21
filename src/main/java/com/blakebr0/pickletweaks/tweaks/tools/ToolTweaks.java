package com.blakebr0.pickletweaks.tweaks.tools;

import java.util.HashSet;
import java.util.Set;

import com.blakebr0.pickletweaks.config.ModConfigold;

import net.minecraft.item.Item;

public class ToolTweaks {
	
	public static Set<Item> uselessTools = new HashSet<Item>();
	public static Set<Item> uselessHoes = new HashSet<Item>();
	public static Set<Item> uselessWeapons = new HashSet<Item>();
	
    public static void findToolsFromConfig() {
    	
    	for (String identifier : ModConfigold.confUselessTools) {
            Item item = Item.getByNameOrId(identifier);
            uselessTools.add((Item) item);
    	}
    	
    	for (String identifier : ModConfigold.confUselessHoes) {
            Item item = Item.getByNameOrId(identifier);
            uselessHoes.add((Item) item);
    	}
    	
    	for (String identifier : ModConfigold.confUselessWeapons) {
            Item item = Item.getByNameOrId(identifier);
            uselessWeapons.add((Item) item);
		}
	}
}
