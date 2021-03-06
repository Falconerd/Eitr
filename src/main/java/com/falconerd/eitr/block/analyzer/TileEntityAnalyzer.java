/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.block.analyzer;

import com.falconerd.eitr.api.implementation.BaseEitrContainer;
import com.falconerd.eitr.capability.EitrCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityAnalyzer extends TileEntity {
    private BaseEitrContainer container;

    public TileEntityAnalyzer() {
        container = new BaseEitrContainer();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        container = new BaseEitrContainer(compound.getCompoundTag("eitr_container"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("eitr_container", container.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == EitrCapabilities.CAPABILITY_CONSUMER || capability == EitrCapabilities.CAPABILITY_HOLDER || capability == EitrCapabilities.CAPABILITY_PRODUCER) {
            return (T) container;
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
}
