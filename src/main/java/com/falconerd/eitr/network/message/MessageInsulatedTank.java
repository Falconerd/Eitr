package com.falconerd.eitr.network.message;

import com.falconerd.eitr.util.ChatHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by falconerd on 2017/01/25.
 */
public class MessageInsulatedTank implements IMessage {
    public MessageInsulatedTank() {}

    private int toSend;
    public MessageInsulatedTank(int amount) {
        toSend = amount;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(toSend);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        toSend = buf.readInt();
    }

    public class Handler implements IMessageHandler<MessageInsulatedTank, IMessage> {
        @Override
        public IMessage onMessage(MessageInsulatedTank message, MessageContext ctx) {
            IThreadListener mainThread = ctx.side.isClient() ? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().playerEntity.world;
            mainThread.addScheduledTask(() -> ChatHelper.sendMessage(String.format("Message: %s", message)));
            return null;
        }
    }
}
