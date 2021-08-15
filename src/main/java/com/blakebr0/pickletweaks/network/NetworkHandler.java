package com.blakebr0.pickletweaks.network;

import com.blakebr0.cucumber.network.BaseNetworkHandler;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.network.message.ToggleMagnetMessage;
import net.minecraft.resources.ResourceLocation;

public class NetworkHandler {
    public static final BaseNetworkHandler INSTANCE = new BaseNetworkHandler(new ResourceLocation(PickleTweaks.MOD_ID, "main"));

    public static void onCommonSetup() {
        INSTANCE.register(ToggleMagnetMessage.class, new ToggleMagnetMessage());
    }
}
