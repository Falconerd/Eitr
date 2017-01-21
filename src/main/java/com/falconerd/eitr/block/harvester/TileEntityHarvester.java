/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.block.harvester;

import com.falconerd.eitr.api.IEitrProducer;
import com.falconerd.eitr.api.implementation.BaseEitrContainer;
import com.falconerd.eitr.capability.EitrCapabilities;
import com.falconerd.eitr.util.Chat;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityHarvester extends TileEntity implements ITickable {
    private BaseEitrContainer instance;

    public TileEntityHarvester() {
        instance = new BaseEitrContainer();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        instance = new BaseEitrContainer(compound.getCompoundTag("eitr_container"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("eitr_container", instance.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == EitrCapabilities.CAPABILITY_CONSUMER || capability == EitrCapabilities.CAPABILITY_HOLDER || capability == EitrCapabilities.CAPABILITY_PRODUCER) {
            return (T) instance;
        }

        return super.getCapability(capability, side);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing side) {
        if (capability == EitrCapabilities.CAPABILITY_CONSUMER || capability == EitrCapabilities.CAPABILITY_HOLDER || capability == EitrCapabilities.CAPABILITY_PRODUCER) {
            return true;
        }

        return super.hasCapability(capability, side);
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {
            final IEitrProducer isProducer = getCapability(EitrCapabilities.CAPABILITY_PRODUCER, null);
            if (isProducer != null) {
                Chat.sendMessage("NOT NULL!");
            } else {
                Chat.sendMessage("NULL");
            }
        }
    }
}
