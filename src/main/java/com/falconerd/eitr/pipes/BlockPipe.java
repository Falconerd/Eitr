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
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;

public class BlockPipe extends BlockTileEntity<TileEntityPipe> {

    public BlockPipe() {
        super(Material.ROCK, "pipe");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) return true;
        if (hand == EnumHand.OFF_HAND) return true;

        TileEntityPipe tileEntity = getTileEntity(worldIn, pos);
        if (tileEntity == null) return true;

        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            tileEntity.mode = tileEntity.cycleMode();
            ChatHelper.sendMessage(String.format("Mode: %s", tileEntity.mode));
            tileEntity.markDirty();
            // We should only need to update the network on the pipes which are
            // set to OUTPUT. The pipes set to INPUT are just indicators, they
            // cannot initiate any actions
            if (tileEntity.mode == EnumPipeMode.OUTPUT) {
                tileEntity.updateNetwork(worldIn);
                tileEntity.updateEndPoints();
            }
        }

//        ChatHelper.sendMessage(String.format("%s, %s, %s, %s, %s, %s, %s", tileEntity.up, tileEntity.down, tileEntity.north, tileEntity.east, tileEntity.south, tileEntity.west, tileEntity.mode));

        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.isRemote) return;
        TileEntityPipe tileEntity = getTileEntity(worldIn, pos);
        if (tileEntity == null) return;

        tileEntity.updateConnections(worldIn);
        tileEntity.tellNeighboursToUpdateConnections(worldIn);

    }

    @Override
    public Class<TileEntityPipe> getTileEntityClass() {
        return TileEntityPipe.class;
    }

    @Nullable
    @Override
    public TileEntityPipe createTileEntity(World world, IBlockState state) {
        return new TileEntityPipe();
    }
}
