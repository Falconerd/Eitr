/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.block.harvester;

import com.falconerd.eitr.api.IEitrHolder;
import com.falconerd.eitr.block.BlockTileEntity;
import com.falconerd.eitr.capability.EitrCapabilities;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockHarvester extends BlockTileEntity<TileEntityHarvester> {

    private final int MESSAGE_ID = 14940016; // ?

    public BlockHarvester() {
        super(Material.ROCK, "harvester");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            final TileEntity tile = worldIn.getTileEntity(pos);

            if (tile.hasCapability(EitrCapabilities.CAPABILITY_HOLDER, side)) {
                final IEitrHolder holder = tile.getCapability(EitrCapabilities.CAPABILITY_HOLDER, side);
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString(Integer.toString(holder.getStored())), MESSAGE_ID);
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
    }

    @Override
    public Class<TileEntityHarvester> getTileEntityClass() {
        return TileEntityHarvester.class;
    }

    @Override
    public TileEntityHarvester createTileEntity(World world, IBlockState state) {
        return new TileEntityHarvester();
    }
}
