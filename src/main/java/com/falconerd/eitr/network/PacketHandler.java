/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.network;

import com.falconerd.eitr.Eitr;
import com.falconerd.eitr.network.message.MessageInsulatedTank;
import com.falconerd.eitr.network.message.MessageSetPipeMode;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Eitr.MODID);

    private static int id = 0;

    public static void init() {
        INSTANCE.registerMessage(MessageSetPipeMode.class, MessageSetPipeMode.class, id++, Side.SERVER);
//        INSTANCE.registerMessage(MessageInsulatedTank.class, MessageInsulatedTank.InsulatedTankMessageHandler, id++, Side.SERVER);
//        INSTANCE.registerMessage(MessageSyncTileEntity.class, MessageSyncTileEntity.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageInsulatedTank.Handler.class, MessageInsulatedTank.class, id++, Side.CLIENT);
    }
}
