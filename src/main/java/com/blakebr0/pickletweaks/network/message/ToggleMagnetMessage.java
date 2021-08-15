package com.blakebr0.pickletweaks.network.message;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.network.message.Message;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleMagnetMessage extends Message<ToggleMagnetMessage> {
    private final int slot;

    public ToggleMagnetMessage() {
        this.slot = -1;
    }

    public ToggleMagnetMessage(int slot) {
        this.slot = slot;
    }

    public ToggleMagnetMessage read(FriendlyByteBuf buffer) {
        return new ToggleMagnetMessage(buffer.readInt());
    }

    public void write(ToggleMagnetMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slot);
    }

    public void onMessage(ToggleMagnetMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            var player = context.get().getSender();

            if (player != null) {
                var stack = player.containerMenu.slots.get(message.slot).getItem();

                if (stack.getItem() instanceof MagnetItem) {
                    NBTHelper.flipBoolean(stack, "Enabled");
                }
            }
        });

        context.get().setPacketHandled(true);
    }
}
