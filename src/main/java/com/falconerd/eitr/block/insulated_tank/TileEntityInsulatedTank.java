package com.falconerd.eitr.block.insulated_tank;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;

/**
 * Created by falconerd on 2017/01/24.
 */
public class TileEntityInsulatedTank extends TileEntity {

    public FluidTank fluidTank = new FluidTank(16000);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) {
            return (T) fluidTank;
        }
        return super.getCapability(capability, facing);
    }
}
