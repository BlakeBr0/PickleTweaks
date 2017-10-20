package com.blakebr0.pickletweaks.tweaks;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolModifyEvent;
import slimeknights.tconstruct.tools.modifiers.ModFortify;
import slimeknights.tconstruct.tools.modifiers.ModFortifyDisplay;

public class TweakSharpeningKit {
	
	@SubscribeEvent
	public void cheaterProtection(ToolModifyEvent event) {
		if (event.getModifiers().stream().anyMatch(e -> e instanceof ModFortify || e instanceof ModFortifyDisplay)) {
			event.setCanceled("You cannot fortify your tools you cheater.");
		}
	}
}
