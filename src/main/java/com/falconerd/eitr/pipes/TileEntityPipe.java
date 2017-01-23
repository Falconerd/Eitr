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
import com.falconerd.eitr.network.message.EnumPipeMode;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityPipe extends TileEntity {

    /**
     * A method for mapping int to EnumPipeMode
     */
    public static EnumPipeMode modeToInt(int value) {
        switch (value) {
            case 0:
                return EnumPipeMode.INPUT;
            case 1:
                return EnumPipeMode.OUTPUT;
            case 2:
                return EnumPipeMode.DISABLED;
            default:
                return EnumPipeMode.INPUT;
        }
    }

    public EnumPipeConnection up = EnumPipeConnection.NONE;
    public EnumPipeConnection down = EnumPipeConnection.NONE;
    public EnumPipeConnection north = EnumPipeConnection.NONE;
    public EnumPipeConnection south = EnumPipeConnection.NONE;
    public EnumPipeConnection east = EnumPipeConnection.NONE;
    public EnumPipeConnection west = EnumPipeConnection.NONE;

    public void updateNeighbours(IBlockAccess world) {
        up = getConnection(world, getPos().up(), EnumFacing.DOWN);
    }

    public EnumPipeConnection getConnection(IBlockAccess world, BlockPos pos, EnumFacing side) {
        if (world.getTileEntity(pos) != null) {
            if (world.getTileEntity(pos).hasCapability(isEitrCapab))
        }
    }

    private BaseEitrContainer instance;

    TileEntityPipe() {
        instance = new BaseEitrContainer();
    }

}
