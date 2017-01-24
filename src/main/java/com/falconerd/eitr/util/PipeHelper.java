package com.falconerd.eitr.util;

import com.falconerd.eitr.pipes.EnumPipeConnection;
import com.falconerd.eitr.pipes.TileEntityPipe;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by falconerd on 2017/01/23.
 */
public class PipeHelper {

    public static EnumPipeConnection intToEnumPipeConnection(int value) {
        switch (value) {
            case 1:
                return EnumPipeConnection.PIPE;
            case 2:
                return EnumPipeConnection.BLOCK;
            default:
                return EnumPipeConnection.NONE;
        }
    }

    public static void mapNetwork(BlockPos pos, World world) {
        Set<BlockPos> machines = findMachines(pos, world);

        for (BlockPos machine : machines) {
            System.out.printf("Machine at %s %s %s", machine.getX(), machine.getY(), machine.getZ());
        }
    }

    public static Set<BlockPos> findMachines(BlockPos pos, World world) {
        Set<BlockPos> machines = new LinkedHashSet<BlockPos>();
        Set<BlockPos> visited = new HashSet<BlockPos>();
        Queue<BlockPos> queue = new LinkedList<BlockPos>();

        for (EnumFacing side : EnumFacing.values()) {
            TileEntity tileEntity = world.getTileEntity(pos.offset(side));

            if (tileEntity != null) {
                if (tileEntity instanceof TileEntityPipe) {
                    queue.add(tileEntity.getPos());
                }
            }
        }

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();

            if (!visited.contains(current)) {
                visited.add(current);

                TileEntity currentTileEntity = world.getTileEntity(current);

                if (currentTileEntity != null) {
                    if (currentTileEntity instanceof TileEntityPipe) {
                        for (EnumFacing side : ((TileEntityPipe) currentTileEntity).getConnections(EnumPipeConnection.BLOCK)) {
                            if (!machines.contains(current.offset(side))) {
                                TileEntity currentConnectedTileEntity = world.getTileEntity(current.offset(side));
                                if (currentConnectedTileEntity != null) {
                                    machines.add(currentConnectedTileEntity.getPos());
                                }
                            }
                        }

                        for (EnumFacing side : ((TileEntityPipe) currentTileEntity).getConnections(EnumPipeConnection.PIPE)) {
                            if (!visited.contains(current.offset(side))) {
                                TileEntity currentConnectedTileEntity = world.getTileEntity(current.offset(side));
                                if (currentConnectedTileEntity != null) {
                                    queue.add(currentConnectedTileEntity.getPos());
                                }
                            }
                        }
                    }
                }
            }
        }

        return machines;
    }

}
