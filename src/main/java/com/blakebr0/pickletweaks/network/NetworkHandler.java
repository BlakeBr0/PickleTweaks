package com.blakebr0.pickletweaks.network;

import com.blakebr0.pickletweaks.PickleTweaks;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(PickleTweaks.MOD_ID);

    public static void init() {
        INSTANCE.registerMessage(MessageToggleMagnet.Handler.class, MessageToggleMagnet.class, 0, Side.SERVER);
    }
}
