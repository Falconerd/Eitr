package com.falconerd.eitr.block.insulated_tank;

import com.falconerd.eitr.block.BlockBase;
import com.falconerd.eitr.block.BlockTileEntity;
import com.falconerd.eitr.util.ChatHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.ItemFluidContainer;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

/**
 * Created by falconerd on 2017/01/24.
 */
public class BlockInsulatedTank extends BlockTileEntity<TileEntityInsulatedTank> {
    public BlockInsulatedTank() {
        super(Material.ROCK, "insulated_tank");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only apply on server side
        if (worldIn.isRemote) return true;
        // Only apply to main hand
        if (hand == EnumHand.OFF_HAND) return true;
        // Only apply to items which can handle fluids (Bucket)
        ItemStack itemStack = playerIn.inventory.getCurrentItem();
        if (!itemStack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) return true;
        // Define the tile entity and check if it's null
        TileEntityInsulatedTank tileEntity = getTileEntity(worldIn, pos);
        if (tileEntity == null) return true;

        // Check if we are trying to add or remove fluid
        if (FluidUtil.getFluidContained(itemStack) == null) {
            // We have no fluid in our item container
            FluidActionResult result = FluidUtil.tryFillContainerAndStow(itemStack, FluidUtil.getFluidHandler(worldIn, pos, null), playerIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 1000, playerIn);
            if (result.isSuccess()) {
                itemStack.setCount(itemStack.getCount() - 1);
                // If we used our last bucket in this stack, we need to manually
                // add fluid for some reason
                if (itemStack.getCount() == 0) {
                    ItemHandlerHelper.giveItemToPlayer(playerIn, result.getResult());
                }
            }
        } else {
            // We have fluid in our item container
            FluidActionResult result = FluidUtil.tryEmptyContainerAndStow(itemStack, FluidUtil.getFluidHandler(worldIn, pos, null), playerIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 1000, playerIn);
            // Replace the current item with an empty version
            if (result.isSuccess()) {
                itemStack.setCount(itemStack.getCount() - 1);
                ItemHandlerHelper.giveItemToPlayer(playerIn, result.getResult());
            }
        }

        ChatHelper.sendMessage(String.format("Fluid amount: %s", tileEntity.fluidTank.getFluidAmount()));
        return true;
    }

    @Override
    public Class<TileEntityInsulatedTank> getTileEntityClass() {
        return TileEntityInsulatedTank.class;
    }

    @Override
    public TileEntityInsulatedTank createTileEntity(World world, IBlockState state) {
        return new TileEntityInsulatedTank();
    }
}
