/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Aetherium is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/aetherium
 */
package com.falconerd.eitr.block.fluid;

import com.falconerd.eitr.Eitr;
import com.falconerd.eitr.fluid.ModFluids;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Since this is the only fluid in this mod, we should be alright to not
 * abstract things away
 */
public class BlockEitr extends BlockFluidClassic {
    public static FluidStack stack = new FluidStack(ModFluids.eitr, 1000);

    public BlockEitr(String name) {
        super(ModFluids.eitr, Material.WATER);
        setRegistryName(Eitr.MODID + ":" + name);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
