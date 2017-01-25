package com.falconerd.eitr.pipes;/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */

public enum EnumPipeMode {
    /**
     * INPUT means push INTO an adjacent machine
     */
    INPUT,
    /**
     * OUTPUT means pull FROM an adjacent machine
     */
    OUTPUT,
    /**
     * DISABLED does neither.
     */
    DISABLED;

    public static final EnumPipeMode VALUES[] = values();
}
