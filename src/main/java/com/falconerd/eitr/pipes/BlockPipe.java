/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.pipes;

import com.falconerd.eitr.block.BlockTileEntity;
import com.falconerd.eitr.util.ChatHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * A lot of this class was sourced from the mod Embers by Elucent.
 * Source @ https://github.com/RootsTeam/Embers
 */
public class BlockPipe extends BlockTileEntity<TileEntityPipe> {

    public BlockPipe(Material material, String name) {
        super(material, name);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public Class<TileEntityPipe> getTileEntityClass() {
        return TileEntityPipe.class;
    }

    @Override
    public TileEntityPipe createTileEntity(World world, IBlockState state) {
        return new TileEntityPipe();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND) {

            TileEntityPipe tileEntityPipe = (TileEntityPipe) worldIn.getTileEntity(pos);

            if (tileEntityPipe != null) {
//                tileEntityPipe.switchMode();
                ChatHelper.sendMessage("I would like to switch modes, but there's no method for it yet!");
            }
        }

        return false;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        if (world.getTileEntity(pos) != null) {
            ((TileEntityPipe) world.getTileEntity(pos)).updateNeighbours(world);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (worldIn.getTileEntity(pos) != null) {
            ((TileEntityPipe) worldIn.getTileEntity(pos)).updateNeighbours(worldIn);
            worldIn.notifyBlockUpdate(pos, state, worldIn.getBlockState(pos), 3);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getTileEntity(pos) != null) {
            ((TileEntityPipe) worldIn.getTileEntity(pos)).updateNeighbours(worldIn);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        double x1 = 0.375;
        double y1 = 0.375;
        double z1 = 0.375;
        double x2 = 0.625;
        double y2 = 0.625;
        double z2 = 0.625;

        if (source.getTileEntity(pos) instanceof TileEntityPipe) {
            TileEntityPipe pipe = ((TileEntityPipe) source.getTileEntity(pos));
            if (pipe.up != EnumPipeConnection.NONE) {
                y2 = 1;
            }
            if (pipe.down != EnumPipeConnection.NONE) {
                y1 = 0;
            }
            if (pipe.north != EnumPipeConnection.NONE) {
                z1 = 0;
            }
            if (pipe.south != EnumPipeConnection.NONE) {
                z2 = 1;
            }
            if (pipe.west != EnumPipeConnection.NONE) {
                x1 = 0;
            }
            if (pipe.east != EnumPipeConnection.NONE) {
                x2 = 1;
            }
        }

        return new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        if (worldIn.getTileEntity(pos.up()) instanceof TileEntityPipe){
            ((TileEntityPipe)worldIn.getTileEntity(pos.up())).updateNeighbours(worldIn);
        }
        if (worldIn.getTileEntity(pos.down()) instanceof TileEntityPipe){
            ((TileEntityPipe)worldIn.getTileEntity(pos.down())).updateNeighbours(worldIn);
        }
        if (worldIn.getTileEntity(pos.north()) instanceof TileEntityPipe){
            ((TileEntityPipe)worldIn.getTileEntity(pos.north())).updateNeighbours(worldIn);
        }
        if (worldIn.getTileEntity(pos.south()) instanceof TileEntityPipe){
            ((TileEntityPipe)worldIn.getTileEntity(pos.south())).updateNeighbours(worldIn);
        }
        if (worldIn.getTileEntity(pos.west()) instanceof TileEntityPipe){
            ((TileEntityPipe)worldIn.getTileEntity(pos.west())).updateNeighbours(worldIn);
        }
        if (worldIn.getTileEntity(pos.east()) instanceof TileEntityPipe){
            ((TileEntityPipe)worldIn.getTileEntity(pos.east())).updateNeighbours(worldIn);
        }
    }
}
