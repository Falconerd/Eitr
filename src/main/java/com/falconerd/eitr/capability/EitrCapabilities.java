/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.capability;

import com.falconerd.eitr.api.IEitrConsumer;
import com.falconerd.eitr.api.IEitrHolder;
import com.falconerd.eitr.api.IEitrProducer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;

/**
 * Capability system based on Tesla API. Pretty much exactly the same, actually.
 * Hooray MIT License.
 */
public class EitrCapabilities {

    @CapabilityInject(IEitrConsumer.class)
    public static Capability<IEitrConsumer> CAPABILITY_CONSUMER = null;

    @CapabilityInject(IEitrProducer.class)
    public static Capability<IEitrProducer> CAPABILITY_PRODUCER = null;

    @CapabilityInject(IEitrHolder.class)
    public static Capability<IEitrHolder> CAPABILITY_HOLDER = null;

    public static class CapabilityEitrConsumer<T extends IEitrConsumer> implements IStorage<IEitrConsumer> {

        @Override
        public NBTBase writeNBT(Capability<IEitrConsumer> capability, IEitrConsumer instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IEitrConsumer> capability, IEitrConsumer instance, EnumFacing side, NBTBase nbt) {

        }
    }

    public static class CapabilityEitrProducer<T extends IEitrProducer> implements IStorage<IEitrProducer> {

        @Override
        public NBTBase writeNBT(Capability<IEitrProducer> capability, IEitrProducer instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IEitrProducer> capability, IEitrProducer instance, EnumFacing side, NBTBase nbt) {

        }
    }

    public static class CapabilityEitrHolder<T extends IEitrHolder> implements IStorage<IEitrHolder> {

        @Override
        public NBTBase writeNBT(Capability<IEitrHolder> capability, IEitrHolder instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IEitrHolder> capability, IEitrHolder instance, EnumFacing side, NBTBase nbt) {

        }
    }
}
