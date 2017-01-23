package com.falconerd.eitr.network.message;/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */

public enum EnumPipeMode {
    /**
     * INPUT means input INTO an adjacent machine
     */
    INPUT,
    /**
     * OUTPUT means pull FROM an adjacent machine
     */
    OUTPUT,
    /**
     * DISABLED does neither.
     */
    DISABLED
}
