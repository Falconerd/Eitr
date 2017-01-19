/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Aetherium is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/aetherium
 */
package com.falconerd.eitr;

import com.falconerd.eitr.block.ModBlocks;
import com.falconerd.eitr.fluid.ModFluids;
import com.falconerd.eitr.item.ModItems;
import com.falconerd.eitr.proxy.CommonProxy;
import com.falconerd.eitr.recipe.ModRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Eitr.MODID, name = Eitr.NAME, version = Eitr.VERSION, acceptedMinecraftVersions = "[1.11.2]")
public class Eitr {
    public static final String MODID = "eitr";
    public static final String NAME = "Eitr";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static Eitr instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModFluids.init();
        ModItems.init();
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = "com.falconerd.eitr.proxy.CommonProxy", clientSide = "com.falconerd.eitr.proxy.ClientProxy")
    public static CommonProxy proxy;
}
