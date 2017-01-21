/*
 * Author: Falconerd
 * Date: 2017/01/20
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.mana;

public class Mana implements IMana {
    private float mana = 250f;

    public void consume(float points) {
        mana = Math.min(Math.abs(mana - points), 0);
    }

    public void fill(float points) {
        mana += points;
    }

    public void set(float points) {
        mana = points;
    }

    public float getMana() {
        return mana;
    }
}
