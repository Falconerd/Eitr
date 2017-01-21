/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.network.message;

import com.falconerd.eitr.pipes.TileEntityPipe;
import com.falconerd.eitr.util.ChatHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetPipeMode implements IMessage, IMessageHandler<MessageSetPipeMode, IMessage> {

    public int x;
    public int y;
    public int z;
    public int m;

    public MessageSetPipeMode(TileEntityPipe tileEntityPipe, int mode) {
        x = tileEntityPipe.getPos().getX();
        y = tileEntityPipe.getPos().getY();
        z = tileEntityPipe.getPos().getZ();
        m = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        m = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(m);
    }

    @Override
    public IMessage onMessage(MessageSetPipeMode message, MessageContext ctx) {
        TileEntityPipe tileEntityPipe = (TileEntityPipe) FMLClientHandler.instance().getClient().world.getTileEntity(new BlockPos(message.x, message.y, message.z));
        if (tileEntityPipe != null) {
            ChatHelper.sendMessage("Setting mode on pipe to: " + message.m);
        } else {
            ChatHelper.sendMessage("null");
        }

        return null;
    }
}
