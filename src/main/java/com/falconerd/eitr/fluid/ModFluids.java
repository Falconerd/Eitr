/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Aetherium is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/aetherium
 */
package com.falconerd.eitr.fluid;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModFluids {
    public static Fluid eitr;

    public static void init() {
        eitr = register(new FluidEitr());
    }

    private static <T extends Fluid> T register(T fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
//        GameRegistry.register(itemBlock);
        return fluid;
    }

//        FluidRegistry.registerFluid(new FluidEitr());
//        FluidRegistry.addBucketForFluid(new FluidEitr());
}
