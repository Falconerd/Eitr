/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Aetherium is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/aetherium
 */
package com.falconerd.eitr.fluid;

import com.falconerd.eitr.Eitr;
import com.falconerd.eitr.block.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.Color;

public class FluidEitr extends Fluid {
    public FluidEitr() {
        super("eitr", new ResourceLocation(Eitr.MODID + ":blocks/eitr_still"), new ResourceLocation(Eitr.MODID + ":blocks/eitr_flowing"));
        setViscosity(5000);
        setDensity(5000);
        setLuminosity(15);
        setTemperature(0);
        setBlock(ModBlocks.eitr);
        setUnlocalizedName("eitr");
    }

    @Override
    public int getColor() {
        return Color.CYAN.getRGB();
    }
}
