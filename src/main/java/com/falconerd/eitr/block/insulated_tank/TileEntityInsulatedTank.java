package com.falconerd.eitr.block.insulated_tank;

import com.falconerd.eitr.util.ChatHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.TileFluidHandler;

/**
 * Created by falconerd on 2017/01/25.
 */
public class TileEntityInsulatedTank extends TileFluidHandler {
    static int capacity = Fluid.BUCKET_VOLUME * 16;

    public TileEntityInsulatedTank() {
        super();
        tank = new FluidTank(capacity);
        tank.setTileEntity(this);
        tank.setCanFill(true);
        tank.setCanDrain(true);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        if (tank.getFluidAmount() > 0) {
            tag.setInteger("amount", tank.getFluidAmount());
            tag.setString("fluid", tank.getFluid().getFluid().getName());
        }
        return super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        ChatHelper.sendMessage(String.format("fluid_tag: %s, amount_tag: %s", tag.getString("fluid"), tag.getInteger("amount")));
        tank.setFluid(FluidRegistry.getFluidStack(tag.getString("fluid"), tag.getInteger("amount")));
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }
}
