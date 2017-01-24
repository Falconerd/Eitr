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
import com.falconerd.eitr.network.message.EnumPipeMode;
import com.falconerd.eitr.util.CapabilityHelper;
import com.falconerd.eitr.util.ChatHelper;
import com.falconerd.eitr.util.PipeHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;
import java.util.*;

public class TileEntityPipe extends TileEntity {

    EnumPipeMode mode = EnumPipeMode.INPUT;

    int modeToInt(EnumPipeMode m) {
        switch (m) {
            case INPUT:
                return 0;
            case OUTPUT:
                return 1;
            case DISABLED:
                return 2;
            default:
                return -1;

        }
    }

    public EnumPipeConnection up = EnumPipeConnection.NONE;
    public EnumPipeConnection down = EnumPipeConnection.NONE;
    public EnumPipeConnection north = EnumPipeConnection.NONE;
    public EnumPipeConnection east = EnumPipeConnection.NONE;
    public EnumPipeConnection south = EnumPipeConnection.NONE;
    public EnumPipeConnection west = EnumPipeConnection.NONE;

    public void updateNeighbours(IBlockAccess world) {
        up = getConnection(world, getPos().up(), EnumFacing.DOWN);
        down = getConnection(world, getPos().down(), EnumFacing.UP);
        north = getConnection(world, getPos().north(), EnumFacing.SOUTH);
        east = getConnection(world, getPos().east(), EnumFacing.WEST);
        south = getConnection(world, getPos().south(), EnumFacing.NORTH);
        west = getConnection(world, getPos().west(), EnumFacing.EAST);
    }

    private EnumPipeConnection getConnection(IBlockAccess world, BlockPos pos, EnumFacing side) {
        if (world.getTileEntity(pos) instanceof TileEntityPipe) {
            return EnumPipeConnection.PIPE;
        } else if (world.getTileEntity(pos) != null) {
            if (CapabilityHelper.tileEntityHasAnyEitrCapability(world.getTileEntity(pos)) || world.getTileEntity(pos).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
                return EnumPipeConnection.BLOCK;
            }
        }
        return EnumPipeConnection.NONE;
    }
    
    public ArrayList<EnumFacing> getConnections(EnumPipeConnection type) {
        Map<EnumFacing, EnumPipeConnection> connections = new HashMap<EnumFacing, EnumPipeConnection>();
        ArrayList<EnumFacing> result = new ArrayList<EnumFacing>();
        connections.put(EnumFacing.DOWN, up);
        connections.put(EnumFacing.UP, down);
        connections.put(EnumFacing.NORTH, north);
        connections.put(EnumFacing.EAST, east);
        connections.put(EnumFacing.SOUTH, south);
        connections.put(EnumFacing.WEST, west);

        for (Map.Entry<EnumFacing, EnumPipeConnection> entry : connections.entrySet()) {
            if (entry.getValue() == type) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("up", up.ordinal());
        System.out.println("Saving NBT");
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        System.out.printf("\n>: %s\n", PipeHelper.intToEnumPipeConnection(compound.getInteger("up")));
    }

    private BaseEitrContainer instance;

    TileEntityPipe() {
        instance = new BaseEitrContainer();
    }

    public EnumPipeMode cycleMode() {
        switch (mode) {
            case INPUT:
                return mode = EnumPipeMode.OUTPUT;
            case OUTPUT:
                return mode = EnumPipeMode.DISABLED;
            case DISABLED:
                return mode = EnumPipeMode.INPUT;
            default:
                return mode = EnumPipeMode.INPUT;
        }
    }
}
