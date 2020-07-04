//package com.blakebr0.pickletweaks.feature;
//
//import com.blakebr0.pickletweaks.config.ModConfigs;
//import com.blakebr0.pickletweaks.lib.ModTooltips;
//import mcp.mobius.waila.api.IComponentProvider;
//import mcp.mobius.waila.api.IDataAccessor;
//import mcp.mobius.waila.api.IPluginConfig;
//import mcp.mobius.waila.api.IRegistrar;
//import mcp.mobius.waila.api.IWailaPlugin;
//import mcp.mobius.waila.api.TooltipPosition;
//import mcp.mobius.waila.api.WailaPlugin;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.util.text.ITextComponent;
//
//import java.util.List;
//
//@WailaPlugin
//public class FeatureWailaPlugin implements IWailaPlugin {
//	@Override
//	public void register(IRegistrar registrar) {
//	    if (ModConfigs.ENABLE_WAILA_HARVEST_LEVEL.get()) {
//            registrar.registerComponentProvider(new IComponentProvider() {
//                @Override
//                public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
//                    BlockState state = accessor.getBlockState();
//                    Block block = accessor.getBlock();
//                    if (block != Blocks.AIR) {
//                        if (block.getHarvestLevel(state) > -1) {
//                            String name = getHarvestLevelName(block, state);
//                            tooltip.add(ModTooltips.HARVEST_LEVEL.args(name).build());
//                        }
//                    }
//                }
//            }, TooltipPosition.BODY, Block.class);
//        }
//	}
//
//    public String getHarvestLevelName(Block block, BlockState state) {
//        int level = block.getHarvestLevel(state);
//        if (level < FeatureToolInfo.MINING_LEVEL_NAMES.length)
//            return FeatureToolInfo.MINING_LEVEL_NAMES[level];
//
//        return String.valueOf(level);
//    }
//}
