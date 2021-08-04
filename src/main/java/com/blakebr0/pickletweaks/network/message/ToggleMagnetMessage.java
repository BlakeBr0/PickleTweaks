package com.blakebr0.pickletweaks.network.message;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleMagnetMessage {
    private final int slot;

    public ToggleMagnetMessage(int slot) {
        this.slot = slot;
    }

    public static ToggleMagnetMessage read(FriendlyByteBuf buffer) {
        return new ToggleMagnetMessage(buffer.readInt());
    }

    public static void write(ToggleMagnetMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slot);
    }

    public static void onMessage(ToggleMagnetMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Player player = context.get().getSender();
            if (player != null) {
                ItemStack stack = player.containerMenu.slots.get(message.slot).getItem();
                if (stack.getItem() instanceof MagnetItem) {
                    NBTHelper.flipBoolean(stack, "Enabled");
                }
            }
        });

        context.get().setPacketHandled(true);
    }
}
