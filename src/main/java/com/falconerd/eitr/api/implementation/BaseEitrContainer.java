/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.api.implementation;

import com.falconerd.eitr.api.IEitrConsumer;
import com.falconerd.eitr.api.IEitrHolder;
import com.falconerd.eitr.api.IEitrProducer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEitrContainer implements IEitrConsumer, IEitrProducer, IEitrHolder, INBTSerializable<NBTTagCompound> {

    private int stored;
    private int capacity;
    private int inputRate;
    private int outputRate;
    public BaseEitrContainer() {
        this(5000, 50, 50);
    }

    public BaseEitrContainer(int capacity, int inputRate, int outputRate) {
        this(0, capacity, inputRate, outputRate);
    }

    public BaseEitrContainer(int stored, int capacity, int inputRate, int outputRate) {
        this.stored = stored;
        this.capacity = capacity;
        this.inputRate = inputRate;
        this.outputRate = outputRate;
    }

    public BaseEitrContainer(NBTTagCompound tag) {
        deserializeNBT(tag);
    }

    @Override
    public int getStored() {
        return stored;
    }

    @Override
    public int giveEitr(int amount) {
        return stored += Math.min(getCapacity() - stored, Math.min(getInputRate(), amount));
    }

    @Override
    public int takeEitr(int amount) {
        return stored -= Math.min(stored, Math.min(getOutputRate(), amount));
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("eitr_stored", stored);
        tag.setInteger("eitr_capacity", capacity);
        tag.setInteger("eitr_input_rate", inputRate);
        tag.setInteger("eitr_output_rate", outputRate);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag) {
        stored = tag.getInteger("eitr_stored");

        if (tag.hasKey("eitr_capacity")) {
            capacity = tag.getInteger("eitr_capacity");
        }

        if (tag.hasKey("eitr_input_rate")) {
            inputRate = tag.getInteger("eitr_input_rate");
        }

        if (tag.hasKey("eitr_output_rate")) {
            outputRate = tag.getInteger("eitr_output_rate");
        }

        if (stored > getCapacity()) {
            stored = getCapacity();
        }
    }

    public BaseEitrContainer setCapacity(int value) {
        capacity = value;

        if (stored > value) {
            stored = value;
        }

        return this;
    }

    public int getInputRate() {
        return inputRate;
    }

    public BaseEitrContainer setInputRate(int rate) {
        inputRate = rate;
        return this;
    }

    public int getOutputRate() {
        return outputRate;
    }

    public BaseEitrContainer setOutputRate(int rate) {
        outputRate = rate;
        return this;
    }

    public BaseEitrContainer setTransferRate(int rate) {
        setInputRate(rate);
        setOutputRate(rate);
        return this;
    }

}
