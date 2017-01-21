/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.pipes;

import com.falconerd.eitr.api.implementation.BaseEitrContainer;
import com.falconerd.eitr.capability.EitrCapabilities;
import com.falconerd.eitr.util.ChatHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileEntityPipe extends TileEntity {

    private BaseEitrContainer instance;

    TileEntityPipe() {
        instance = new BaseEitrContainer();
    }

    int mode = 0;

    public int getMode() {
        return mode;
    }

    public int setMode(int value) {
        return mode = value;
    }

    public void switchMode() {
        if (getMode() == 1) {
            setMode(0);
        } else {
            setMode(1);
        }
        ChatHelper.sendMessage("New mode is " + getMode());
        TileEntityPipe.this.markDirty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == EitrCapabilities.CAPABILITY_TRANSFERER) {
            return (T) instance;
        }

        return super.getCapability(capability, side);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing side) {
        if (capability == EitrCapabilities.CAPABILITY_TRANSFERER) {
            return true;
        }

        return super.hasCapability(capability, side);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        ChatHelper.sendMessage("Reading from NBT: " + compound.getInteger("mode"));
        setMode(compound.getInteger("mode"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("mode", getMode());

        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }
}
