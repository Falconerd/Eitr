/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.pipes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.*;

public class TileEntityPipe extends TileEntity {

    EnumPipeMode mode = EnumPipeMode.INPUT;

    /* D-U-N-S-W-E */
    private HashMap<EnumFacing, EnumPipeConnection> connections = new HashMap<>();

    public TileEntityPipe() {
        super();
        connections.put(EnumFacing.DOWN, EnumPipeConnection.NONE);
        connections.put(EnumFacing.UP, EnumPipeConnection.NONE);
        connections.put(EnumFacing.NORTH, EnumPipeConnection.NONE);
        connections.put(EnumFacing.SOUTH, EnumPipeConnection.NONE);
        connections.put(EnumFacing.WEST, EnumPipeConnection.NONE);
        connections.put(EnumFacing.EAST, EnumPipeConnection.NONE);
    }

    /**
     * This is a {@link LinkedHashSet} containing the coordinates of all blocks
     * which either handle Fluid or Eitr and are connected to these pipes.
     */
    Set<BlockPos> networkedBlocks = new LinkedHashSet<BlockPos>();

    public void updateConnections(IBlockAccess world) {
        connections.replace(EnumFacing.DOWN, getConnectionType(world, getPos().down(), EnumFacing.UP));
        connections.replace(EnumFacing.UP, getConnectionType(world, getPos().down(), EnumFacing.DOWN));
        connections.replace(EnumFacing.NORTH, getConnectionType(world, getPos().up(), EnumFacing.SOUTH));
        connections.replace(EnumFacing.SOUTH, getConnectionType(world, getPos().north(), EnumFacing.NORTH));
        connections.replace(EnumFacing.WEST, getConnectionType(world, getPos().south(), EnumFacing.EAST));
        connections.replace(EnumFacing.EAST, getConnectionType(world, getPos().west(), EnumFacing.WEST));
    }

    private EnumPipeConnection getConnectionType(IBlockAccess world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity == null) return EnumPipeConnection.NONE;
        if (tileEntity instanceof TileEntityPipe) return EnumPipeConnection.PIPE;
        // @TODO Check Eitr capability
        if (tileEntity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) return EnumPipeConnection.BLOCK;
        return EnumPipeConnection.NONE;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("down", connections.get(EnumFacing.DOWN).ordinal());
        compound.setInteger("up", connections.get(EnumFacing.UP).ordinal());
        compound.setInteger("north", connections.get(EnumFacing.NORTH).ordinal());
        compound.setInteger("south", connections.get(EnumFacing.SOUTH).ordinal());
        compound.setInteger("west", connections.get(EnumFacing.WEST).ordinal());
        compound.setInteger("east", connections.get(EnumFacing.EAST).ordinal());
        compound.setInteger("mode", mode.ordinal());

        /*
         * We need to save a list of the networked blocks.
         * We'll save them as separate Int Arrays with a prepend of "block"
         * We'll give each one a unique id to identify them later or something
         */
        NBTTagList networkedBlocksTagList = new NBTTagList();
        int index = 0;
        for (BlockPos position : networkedBlocks) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setIntArray("block" + index++, new int[]{ position.getX(), position.getY(), position.getZ() });
            networkedBlocksTagList.appendTag(tag);
        }

        compound.setTag("networkedBlocksTagList", networkedBlocksTagList);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        connections.replace(EnumFacing.DOWN, EnumPipeConnection.VALUES[compound.getInteger("down")]);
        connections.replace(EnumFacing.UP, EnumPipeConnection.VALUES[compound.getInteger("up")]);
        connections.replace(EnumFacing.NORTH, EnumPipeConnection.VALUES[compound.getInteger("north")]);
        connections.replace(EnumFacing.SOUTH, EnumPipeConnection.VALUES[compound.getInteger("south")]);
        connections.replace(EnumFacing.EAST, EnumPipeConnection.VALUES[compound.getInteger("west")]);
        connections.replace(EnumFacing.WEST, EnumPipeConnection.VALUES[compound.getInteger("east")]);
        mode = EnumPipeMode.VALUES[compound.getInteger("mode")];

        NBTTagList networkedBlocksTagList = compound.getTagList("networkedBlocksTagList", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < networkedBlocksTagList.tagCount(); i++) {
            NBTTagCompound tag = networkedBlocksTagList.getCompoundTagAt(i);
            int[] position = tag.getIntArray("block" + i);
            networkedBlocks.add(new BlockPos(position[0], position[1], position[2]));
        }
    }

    /**
     * This method tells all the surrounding blocks to also update their
     * connection list.
     * @TODO Clean up this method
     */
    void tellNeighboursToUpdateConnections(World worldIn) {
        TileEntity tileEntity = worldIn.getTileEntity(getPos().up());
        if (tileEntity != null && tileEntity instanceof TileEntityPipe) ((TileEntityPipe) tileEntity).updateConnections(worldIn);
        tileEntity = worldIn.getTileEntity(getPos().down());
        if (tileEntity != null && tileEntity instanceof TileEntityPipe) ((TileEntityPipe) tileEntity).updateConnections(worldIn);
        tileEntity = worldIn.getTileEntity(getPos().north());
        if (tileEntity != null && tileEntity instanceof TileEntityPipe) ((TileEntityPipe) tileEntity).updateConnections(worldIn);
        tileEntity = worldIn.getTileEntity(getPos().east());
        if (tileEntity != null && tileEntity instanceof TileEntityPipe) ((TileEntityPipe) tileEntity).updateConnections(worldIn);
        tileEntity = worldIn.getTileEntity(getPos().south());
        if (tileEntity != null && tileEntity instanceof TileEntityPipe) ((TileEntityPipe) tileEntity).updateConnections(worldIn);
        tileEntity = worldIn.getTileEntity(getPos().west());
        if (tileEntity != null && tileEntity instanceof TileEntityPipe) ((TileEntityPipe) tileEntity).updateConnections(worldIn);
    }

    /**
     * This method returns the next pipe mode.
     * @TODO Create a helper for Enum cycling using ordinals
     */
    EnumPipeMode cycleMode() {
        if (mode.ordinal() == EnumPipeMode.VALUES.length - 1) {
            return EnumPipeMode.VALUES[0];
        } else {
            return EnumPipeMode.VALUES[mode.ordinal() + 1];
        }
    }

    void updateNetwork(World world) {
        networkedBlocks = findNetworkedBlocks(world);
        for (BlockPos block : networkedBlocks) {
            System.out.printf("Fluid/Eitr block at %s %s %s", block.getX(), block.getY(), block.getZ());
        }
    }

    Set<BlockPos> findNetworkedBlocks(World world) {
        Set<BlockPos> blocks = new LinkedHashSet<>();
        Set<BlockPos> visited = new LinkedHashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        // Let's add this pipe as the starting point of the network.
        queue.add(getPos());

        // While we have any block positions in the queue
        while (!queue.isEmpty()) {
            // Remove a block position and set it as current
            BlockPos current = queue.poll();

            // Make sure it hasn't been visited
            if (!visited.contains(current)) {
                // Add it to the visited list
                visited.add(current);
                // Get the current Tile Entity
                TileEntity tileEntity = world.getTileEntity(current);
                if (tileEntity == null || !(tileEntity instanceof TileEntityPipe)) continue;
                // We want to get it's connections
                // and see if any of those are pipes or blocks.
                ((TileEntityPipe) tileEntity).connections.forEach((side, connection) -> {
                    // If the connection is a block, let's add it to the
                    // network
                    if (connection == EnumPipeConnection.BLOCK) {
                        // If this block hasn't already been added
                        if (!blocks.contains(current.offset(side))) {
                            // Add the blockpos
                            // @TODO Maybe need to add a check here
                            blocks.add(current.offset(side));
                        }
                    } else if (connection == EnumPipeConnection.PIPE) {
                        if (!visited.contains(current.offset(side))) {
                            queue.add(current.offset(side));
                        }
                    }
                });
            }
        }
        return blocks;
    }
}
