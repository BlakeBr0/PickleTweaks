package com.blakebr0.pickletweaks.network;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageToggleMagnet {
    private int slot;

    public MessageToggleMagnet(int slot) {
        this.slot = slot;
    }

    public static MessageToggleMagnet read(PacketBuffer buffer) {
        return new MessageToggleMagnet(buffer.readInt());
    }

    public static void write(MessageToggleMagnet message, PacketBuffer buffer) {
        buffer.writeInt(message.slot);
    }

    public static void onMessage(MessageToggleMagnet message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            PlayerEntity player = context.get().getSender();
            if (player != null) {
                ItemStack stack = player.openContainer.inventorySlots.get(message.slot).getStack();
                if (stack.getItem() instanceof MagnetItem) {
                    NBTHelper.flipBoolean(stack, "Enabled");
                }
            }
        });

        context.get().setPacketHandled(true);
    }
}
