package com.blakebr0.pickletweaks.lib;

import java.util.function.BooleanSupplier;

import com.blakebr0.pickletweaks.config.ModConfig;
import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class ConfigCondition implements IConditionFactory {

	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		String cat = JsonUtils.getString(json, "category");
		String prop = JsonUtils.getString(json, "property");
		return () -> ModConfig.config.getCategory(cat).get(prop).getBoolean();
	}
}
