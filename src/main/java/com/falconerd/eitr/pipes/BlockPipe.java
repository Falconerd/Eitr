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
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPipe extends BlockTileEntity<TileEntityPipe> {

    static final PropertyBool UP = PropertyBool.create("up");
    static final PropertyBool DOWN = PropertyBool.create("down");
    static final PropertyBool NORTH = PropertyBool.create("north");
    static final PropertyBool EAST = PropertyBool.create("east");
    static final PropertyBool SOUTH = PropertyBool.create("south");
    static final PropertyBool WEST = PropertyBool.create("west");

    public BlockPipe() {
        super(Material.ROCK, "pipe");
        setDefaultState(blockState.getBaseState()
                .withProperty(UP, false)
                .withProperty(DOWN, false)
                .withProperty(NORTH, false)
                .withProperty(EAST, false)
                .withProperty(WEST, false)
        );
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        return tileEntity instanceof TileEntityPipe;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state
                .withProperty(UP, canConnectTo(worldIn, pos.up()))
                .withProperty(DOWN, this.canConnectTo(worldIn, pos.down()))
                .withProperty(NORTH, this.canConnectTo(worldIn, pos.north()))
                .withProperty(EAST, this.canConnectTo(worldIn, pos.east()))
                .withProperty(SOUTH, this.canConnectTo(worldIn, pos.south()))
                .withProperty(WEST, this.canConnectTo(worldIn, pos.west()));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, UP, DOWN, NORTH, EAST, SOUTH, WEST);
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
                tileEntityPipe.switchMode();
            }
        }

        return false;
    }
}
