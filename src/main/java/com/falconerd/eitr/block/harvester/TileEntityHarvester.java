/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.block.harvester;

import com.falconerd.eitr.api.IEitrHolder;
import com.falconerd.eitr.api.IEitrProducer;
import com.falconerd.eitr.api.implementation.BaseEitrContainer;
import com.falconerd.eitr.capability.EitrCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityHarvester extends TileEntity implements ITickable {
    private BaseEitrContainer instance;

    public TileEntityHarvester() {
        instance = new BaseEitrContainer();
        instance.setCapacity(2000);
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
        if (capability == EitrCapabilities.CAPABILITY_PRODUCER || capability == EitrCapabilities.CAPABILITY_HOLDER) {
            return (T) instance;
        }

        return super.getCapability(capability, side);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing side) {
        if (capability == EitrCapabilities.CAPABILITY_PRODUCER || capability == EitrCapabilities.CAPABILITY_HOLDER) {
            return true;
        }

        return super.hasCapability(capability, side);
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {
            final IEitrProducer isProducer = getCapability(EitrCapabilities.CAPABILITY_PRODUCER, null);
            final IEitrHolder isHolder = getCapability(EitrCapabilities.CAPABILITY_HOLDER, null);

            if (isProducer != null && isHolder != null) {
                // Check if we can actually produce Eitr somehow
                // Increase the Eitr in this block if the conditions are met...
                // @TODO
                // Conditions should be: The chunk this TE is in should have
                // some Eitr left in it. Eitr should be drained out of each
                // chunk at a constant rate and only recharge during the night.
                // For now we will just leave this as constantly increasing in
                // Eitr until it's capacity is reached.
                instance.increaseEitr(40);
            }
        }
    }
}
