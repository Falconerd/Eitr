/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.util;

import com.falconerd.eitr.api.IEitrConsumer;
import com.falconerd.eitr.api.IEitrHolder;
import com.falconerd.eitr.api.IEitrProducer;
import com.falconerd.eitr.capability.EitrCapabilities;
import com.falconerd.eitr.pipes.IEitrTransferer;
import net.minecraft.tileentity.TileEntity;

public class CapabilityHelper {

    public static Boolean tileEntityHasAnyEitrCapability(TileEntity tileEntity) {
        final IEitrHolder holder = tileEntity.getCapability(EitrCapabilities.CAPABILITY_HOLDER, null);
        final IEitrTransferer transferer = tileEntity.getCapability(EitrCapabilities.CAPABILITY_TRANSFERER, null);
        final IEitrProducer producer = tileEntity.getCapability(EitrCapabilities.CAPABILITY_PRODUCER, null);
        final IEitrConsumer consumer = tileEntity.getCapability(EitrCapabilities.CAPABILITY_CONSUMER, null);
        return (transferer != null || holder != null || producer != null || consumer != null);
    }
}
