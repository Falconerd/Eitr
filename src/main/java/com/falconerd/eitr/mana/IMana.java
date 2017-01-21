package com.falconerd.eitr.mana;/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */

public interface IMana {
    void consume(float points);
    void fill(float points);
    void set(float points);
    float getMana();
}
