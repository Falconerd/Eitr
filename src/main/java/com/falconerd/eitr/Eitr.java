/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr;

import com.falconerd.eitr.api.IEitrConsumer;
import com.falconerd.eitr.api.IEitrHolder;
import com.falconerd.eitr.api.IEitrProducer;
import com.falconerd.eitr.api.implementation.BaseEitrContainer;
import com.falconerd.eitr.block.ModBlocks;
import com.falconerd.eitr.capability.EitrCapabilities;
import com.falconerd.eitr.item.ModItems;
import com.falconerd.eitr.proxy.CommonProxy;
import com.falconerd.eitr.recipe.ModRecipes;
import net.minecraftforge.common.capabilities.CapabilityManager;
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

    @SidedProxy(serverSide = "com.falconerd.eitr.proxy.CommonProxy", clientSide = "com.falconerd.eitr.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IEitrProducer.class, new EitrCapabilities.CapabilityEitrProducer<IEitrProducer>(), BaseEitrContainer.class);
        CapabilityManager.INSTANCE.register(IEitrConsumer.class, new EitrCapabilities.CapabilityEitrConsumer<IEitrConsumer>(), BaseEitrContainer.class);
        CapabilityManager.INSTANCE.register(IEitrHolder.class, new EitrCapabilities.CapabilityEitrHolder<IEitrHolder>(), BaseEitrContainer.class);
        ModItems.init();
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
