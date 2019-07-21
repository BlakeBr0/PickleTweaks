package com.blakebr0.pickletweaks.network;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageToggleMagnet implements IMessage {

    private int slot;

    public MessageToggleMagnet() { }

    public MessageToggleMagnet(int slot) {
        this.slot = slot;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slot = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.slot);
    }

    public static class Handler implements IMessageHandler<MessageToggleMagnet, IMessage> {

        @Override
        public IMessage onMessage(MessageToggleMagnet message, MessageContext ctx) {
            handle(message, ctx);
            return null;
        }

        private void handle(MessageToggleMagnet message, MessageContext ctx) {
            if (ctx.side.isServer()) {
                ItemStack stack = ctx.getServerHandler().player.openContainer.inventorySlots.get(message.slot).getStack();
                if (stack.getItem() instanceof MagnetItem) {
                    NBTHelper.flipBoolean(stack, "Enabled");
                }
            }
        }
    }
}
