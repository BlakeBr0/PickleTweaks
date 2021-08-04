package com.blakebr0.pickletweaks.network;

import com.blakebr0.pickletweaks.network.message.ToggleMagnetMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("pickletweaks", "pickletweaks"), () -> "1.0", (s) -> true, (s) -> true);
    private static int id = 0;

    public static void onCommonSetup() {
        INSTANCE.registerMessage(id(), ToggleMagnetMessage.class, ToggleMagnetMessage::write, ToggleMagnetMessage::read, ToggleMagnetMessage::onMessage);
    }

    private static int id() {
        return id++;
    }
}
