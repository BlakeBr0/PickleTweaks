package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

@WailaPlugin
public class FeatureWailaPlugin implements IWailaPlugin {
	@Override
	public void register(IRegistrar registrar) {
	    if (ModConfigs.ENABLE_WAILA_HARVEST_LEVEL.get()) {
            registrar.registerComponentProvider(new IComponentProvider() {
                @Override
                public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                BlockState state = accessor.getBlockState();
                if (state.requiresCorrectToolForDrops()) {
                    String name = getHarvestLevelName(state);
                    tooltip.add(ModTooltips.HARVEST_LEVEL.args(name).build());
                }
                }
            }, TooltipPosition.BODY, Block.class);
        }
	}

    private static String getHarvestLevelName(BlockState state) {
        int level = Math.max(0, state.getHarvestLevel());
        List<String> names = ModConfigs.HARVEST_LEVEL_NAMES.get();

        if (level < names.size())
            return names.get(level);

        return String.valueOf(level);
    }
}
